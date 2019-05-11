package connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connector {
    // Nombre de la base de datos
    public  String database;
    // Host
    public String hostname = "localhost";
    // Puerto
    public String port = "3336";
    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url;
    // Nombre de usuario
    public String username = "root";
    // Clave de usuario
    public String password = "fernanda123";
    
    public connector(String dbname){
        database = dbname;
    }
    
    public Connection connectBD(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
            conn = DriverManager.getConnection(url, username, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
