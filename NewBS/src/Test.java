import java.sql.SQLException;

public class Test {
    public static  void main(String[] args) throws SQLException, ClassNotFoundException {
    Database database=new Database("gem","123456");

    database.close();
    }
}
