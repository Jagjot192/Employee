package employee;
import java.sql.Connection;
import java.sql.Statement;
public class conn {

    Connection connection;
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    Statement statement;
    public conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement","root","Jagjot@1");
            statement = connection.createStatement();


        } catch(Exception e){
            e.printStackTrace();    

        }
    }

}
