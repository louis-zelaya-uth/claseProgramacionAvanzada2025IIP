package bd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author louis
 */
public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3308/empresa_db";
    private static final String USUARIO = "claseviernes";
    private static final String PASSWORD = "a123456789A.";
    
    private static HikariDataSource dataSource;
    
    static{
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USUARIO);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10); //Maximo de conexiones simultaneas
        config.setMinimumIdle(5); //Minimo de Conexiones en espera
        config.setIdleTimeout(30000); //TIempo de inactividad antes de colocar la conexion en espera
        config.setMaxLifetime(200000); //Tiempo de vida máximo de una conexión
        config.setConnectionTimeout(100000); //Timeout para obtener conexion   
        
        dataSource = new HikariDataSource(config);
    }
    
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection(); //Devuelve Conexion
    }
    
    public static void closePool(){
        if(dataSource != null){
            dataSource.close();
        }
    }
    
}
