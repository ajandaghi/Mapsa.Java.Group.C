package ir.mapsa.project.repository;

import ir.mapsa.project.entity.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements BaseRepository<Transaction> {

    private Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_project", "root", "AliJandaghi");
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private static Transaction getTransaction( ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setDate(resultSet.getDate("date"));
        transaction.setSenderCardNumber(resultSet.getString("senderCardNumber"));
        transaction.setRecieverCardNumber(resultSet.getString("receiverCardNumber"));
        transaction.setAmount(resultSet.getLong("amount"));

        return transaction;
    }
    private void executeUpdateQuery(String query,Transaction transaction) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, transaction.getDate());
                statement.setString(2, transaction.getSenderCardNumber());
                statement.setString(3, transaction.getRecieverCardNumber());
                statement.setLong(4, transaction.getAmount());

                if (transaction.getId() != null) {
                    statement.setLong(5, transaction.getId());
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Transaction transaction) throws Exception {
        executeUpdateQuery("insert into transaction(date, senderCardNumber, recieverCardNumber, amount) values (?,?,?,?)", transaction);

    }

    @Override
    public void update(Transaction transaction) throws Exception {
        executeUpdateQuery("update transaction set date=?, senderCardNumber=?, recieverCardNumber=?, amount=?\n" +
                "where id=?",transaction);
    }

    @Override
    public void removeById(Long id) throws Exception {
        if (this.findById(id) == null) {
            throw new IllegalArgumentException();
        }
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from transaction where id=?")) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Transaction findById(Long id) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from transaction where id=?")) {

                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Transaction transaction = getTransaction(resultSet);
                        return transaction;

                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception();
        }    }

    @Override
    public List<Transaction> getAll() throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from transaction")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Transaction> result = new ArrayList<>();
                    while (resultSet.next()) {
                        Transaction transaction = getTransaction(resultSet);
                        result.add(transaction);
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new Exception();
        }
    }
}

