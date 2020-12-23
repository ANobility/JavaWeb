import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection connection = null;
    public Database(String name, String password) throws ClassNotFoundException,SQLException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bs", name, password);
    }

    public void insert(int userId,String userName,String userPwd) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("INSERT INTO userdata values (?,?,?)");
        prep.setInt(1, userId);
        prep.setString(2,userName);
        prep.setString(3,userPwd);
        prep.executeUpdate();
    }
    public void delete(int userId) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("DELETE FROM userdata WHERE user_id=?");
        prep.setInt(1, userId);
        prep.executeQuery();
    }
    public Userdata getUser(int userId) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("SELECT * from userdata where user_id=?");
        prep.setInt(1, userId);
        prep.executeQuery();
        ResultSet resultSet=prep.getResultSet();
        if (resultSet.next()){
            int id=resultSet.getInt("user_id");
            String name=resultSet.getString("user_name");
            String pwd =resultSet.getString("user_password");
            return new Userdata(id,name,pwd);
        }
        else {
            return null;
        }
    }
    public ArrayList<Userdata> getAllUser() throws SQLException {
        ArrayList<Userdata> UserList = new ArrayList<Userdata>();
        PreparedStatement prep = connection.prepareStatement("SELECT * from userdata ");
        prep.executeQuery();
        ResultSet resultSet=prep.getResultSet();
        while (resultSet.next()){
            int id=resultSet.getInt("user_id");
            String name=resultSet.getString("user_name");
            String pwd =resultSet.getString("user_password");
            UserList.add(new Userdata(id,name,pwd));
        }
        return UserList;

    }
    public void close() throws SQLException {
        connection.close();
    }
    public Userdata check(int userId,String userPwd) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("SELECT user_password from userdata where user_id=?");
        prep.setInt(1,userId);
        prep.executeQuery();
        ResultSet resultSet =prep.getResultSet();
        if(resultSet.next()){

            String pwd=resultSet.getString("user_password");
            if(userPwd.equals(pwd)){
                return getUser(userId);
            }
            else return null;
        }

        else return null;
    }
}
