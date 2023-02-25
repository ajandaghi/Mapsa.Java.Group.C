package ir.mapsa.project.repository;

import ir.mapsa.project.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements BaseRepository<Customer> {

    public void add(Customer customer) throws Exception {
        executeUpdateQuery("insert into customer(firstName, lastName, age, balance, cardNo) values (?,?,?,?,?)", customer);
    }

    private void executeUpdateQuery(String query,Customer customer) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setInt(3, customer.getAge());
                statement.setLong(4, customer.getBalance());
                statement.setString(5, customer.getCardNo());

                if (customer.getId() != null) {
                    statement.setLong(6, customer.getId());
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Customer customer) throws Exception {
        executeUpdateQuery("update customer set firstName=?, lastName=?, age=?,  balance=?, cardNo=?\n" +
                "where id=?",customer);
    }

    public void removeById(Long id) throws Exception {
        if (this.findById(id) == null) {
            throw new IllegalArgumentException();
        }
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from customer where id=?")) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public Customer findById(Long id) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from customer where id=?")) {

                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = getCustomer(resultSet);
                        return customer;

                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception();
        }
    }

    private static Customer getCustomer( ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("Id"));
        customer.setFirstName(resultSet.getString("firstName"));
        customer.setLastName(resultSet.getString("lastName"));
        customer.setAge(resultSet.getInt("age"));
        customer.setBalance(resultSet.getLong("balance"));
        customer.setCardNo(resultSet.getString("cardNo"));


        return customer;
    }

    private Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_project", "root", "AliJandaghi");
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getAll() throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from customer")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Customer> result = new ArrayList<>();
                    while (resultSet.next()) {
                        Customer customer = getCustomer(resultSet);
                        result.add(customer);
                    }
                    return result;
                }
            }
        } catch (SQLException e) {
            throw new Exception();
        }
    }
}
