package DatabaseQuery;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    
    private Connection con;      

    public DBQuery() {
        con = connectDB();
    }

    public DBQuery(Connection con) {
        this.con = con;
    }

    public Connection connectDB() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/music player?useSSL=false";
        String username = "root";
        String password = "linhdv";
        try {            
            con = DriverManager.getConnection(url, username, password);
        } catch ( SQLException  e) {            
        }
        return con;
    }    
    
        
    public ResultSet query(String sql) throws SQLException {           
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);        
        return resultSet;
    }
    
    public void setConnection(Connection con) {
        this.con = con;
    }
    
    public Connection getConnection() {
        return con;
    }
    public static void main(String[] args) {
        DBQuery db = new DBQuery();
        System.out.println(db.connectDB());
    }
}
