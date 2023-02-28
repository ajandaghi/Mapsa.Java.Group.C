package ir.mapsa.spring.java.ClassTodo.jdbc;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


    public class Repository<T>  implements BaseRepository<T> {

        private Class<T> type;
        private List<String> fields;
        private List<Method> methods;



    public Repository(Class<T> type) {
            this.type = type;

            fields= Arrays.stream(type.getDeclaredFields()).map(a->a.getName()).sorted().collect(Collectors.toList());

            methods=Arrays.stream(type.getDeclaredMethods()).sorted(Comparator.comparing(a->a.getName())).collect(Collectors.toList());


           // fields.forEach(System.out::println);
           // methods.forEach(System.out::println);


    }

        @Override
        public void add(T t) throws Exception {

            String query = "insert into " + type.getSimpleName().toLowerCase()+ "(";

            for (int i = 0; i < fields.size() ; i++) {
                if(!fields.get(i).contains("id"))
                query += fields.get(i) + ",";
            }

            query +=") values (";
            query=query.replace(",)",")");
            for (int i = 0; i < fields.size() ; i++) {
                if(!fields.get(i).contains("id"))
                    query += "?,";

            }
            query+=")"; query=query.replace(",)",")");
            executeUpdateQuery(query,t);
        }

        private void executeUpdateQuery(String query, T t) throws Exception {
            try (Connection connection = getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    int j=1;
                    for (int i = 0; i < methods.size(); i++) {
                        if ( !methods.get(i).getName().contains("getId")) {
                            if (methods.get(i).getName().contains("get")&&methods.get(i).getReturnType().equals(String.class)) {
                                statement.setString(j++, (String) methods.get(i).invoke(t, null));
                            } else if (methods.get(i).getReturnType().equals(Integer.class)) {
                                statement.setInt(j++, (Integer) methods.get(i).invoke(t, null));
                            }

                        }

                    }
                     for(int i=0;i<methods.size();i++) {
                         if (methods.get(i).getName().contains("getId") && methods.get(i).invoke(t, null) != null) {
                             statement.setLong(fields.size(), (Long) methods.get(i).invoke(t, null));
                             break;
                         }
                     }

                    statement.executeUpdate();


                }


                } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void update(T t) throws Exception {

            String query = "update " + type.getSimpleName().toLowerCase()+" SET ";

            for (int i = 0; i < fields.size() ; i++) {
                if(!fields.get(i).equals("id"))
                    query +=  fields.get(i) + " =? ,";
            }

           query+=",";
            query=query.replace(",,","");
            query+=" where id=?";
            executeUpdateQuery(query,t);

        }

        @Override
        public void removeById(Long id) throws Exception {
            if (this.findById(id) == null) {
                throw new IllegalArgumentException("Object-notfound");
            }
            try (Connection connection = getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("delete from "+type.getSimpleName().toLowerCase()+" where id=?")) {
                    statement.setLong(1, id);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public T findById(Long id) throws Exception {
            try (Connection connection = getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("select * from "+type.getSimpleName().toLowerCase()+" where id=?")) {

                    statement.setLong(1, id);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            T t = getObject(resultSet);
                            return t;

                        } else {
                            return null;
                        }
                    }
                }
            } catch (SQLException e) {
                throw new SQLException();
            }
        }

        private  T getObject(ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
            T t=type.getDeclaredConstructor().newInstance();

            for (int i=0;i<methods.size();i++){
                if(methods.get(i).getName().contains("set") && methods.get(i).getParameterTypes()[0].equals(String.class)){
                    methods.get(i).invoke(t,resultSet.getString(methods.get(i).getName().replace("set","").toLowerCase()));

                } else if (methods.get(i).getName().contains("set") && methods.get(i).getParameterTypes()[0].equals(Long.class)){
                    methods.get(i).invoke(t,resultSet.getLong(methods.get(i).getName().replace("set","").toLowerCase()));

                } else if (methods.get(i).getName().contains("set") && methods.get(i).getParameterTypes()[0].equals(Integer.class)){
                    methods.get(i).invoke(t,resultSet.getInt(methods.get(i).getName().replace("set","").toLowerCase()));

                }
            }

            return  t;
        }

        private Connection getConnection() throws Exception {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "AliJandaghi");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public List<T> getAll() throws Exception {
            try (Connection connection = getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement("select * from "+type.getSimpleName().toLowerCase()+"")) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        List<T> result = new ArrayList<>();
                        while (resultSet.next()) {
                            T t = getObject(resultSet);
                            result.add(t);
                        }
                        return result;
                    }
                }
            } catch (SQLException e) {
                throw new SQLException();
            }
        }

        public List<T> findByExample(T t) throws Exception {
            String query = "select * from " + type.getSimpleName().toLowerCase() + " where 1=1 ";
            int j = 1;
            List<T> result = new ArrayList<>();
            for (int i = 0; i < methods.size(); i++) {
                if (methods.get(i).getName().contains("get") && methods.get(i).invoke(t, null) != null) {
                    if(i==0)
                    query += "AND "+ methods.get(i).getName().replace("get", "").toLowerCase() + " =?";
                    else
                    query += " Or "+ methods.get(i).getName().replace("get", "").toLowerCase() + " =?";


                }

            }

            PreparedStatement statement = getConnection().prepareStatement(query);
            j = 1;
            for (int i = 0; i < methods.size(); i++) {
                if(methods.get(i).getName().contains("get")) {
                    if (methods.get(i).getReturnType().equals(String.class) && methods.get(i).invoke(t, null) != null) {
                        statement.setString(j++, (String) methods.get(i).invoke(t, null));
                    } else if (methods.get(i).getReturnType().equals(Integer.class) && methods.get(i).invoke(t, null) != null) {
                        statement.setInt(j++, (Integer) methods.get(i).invoke(t, null));
                    } else if (methods.get(i).getReturnType().equals(Long.class) && methods.get(i).invoke(t, null) != null) {
                        statement.setLong(j++, (Long) methods.get(i).invoke(t, null));
                    }
                }


            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T a = getObject(resultSet);
                result.add(a);
            }

              return result;
        }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }
}


