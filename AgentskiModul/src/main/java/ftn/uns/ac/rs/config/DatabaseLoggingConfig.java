package ftn.uns.ac.rs.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class DatabaseLoggingConfig {

	/*
	 * private static BasicDataSource dataSource;
	 * 
	 * private DatabaseLoggingConfig() { }
	 * 
	 * public static Connection getConnection() throws SQLException { if (dataSource
	 * == null) { dataSource = new BasicDataSource(); dataSource.setUrl(
	 * "jdbc:mysql://localhost:3306/agentska?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
	 * ); dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	 * dataSource.setUsername("root"); dataSource.setPassword("rootPassword"); }
	 * return dataSource.getConnection(); }
	 */
   
   
   
   private static interface Singleton {
       final DatabaseLoggingConfig INSTANCE = new DatabaseLoggingConfig();
   }

   private final DataSource dataSource;

   private DatabaseLoggingConfig() {

       try {
           Class.forName("com.mysql.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
           System.exit(0);
       }

       Properties properties = new Properties();
       properties.setProperty("user", "root");
       properties.setProperty("password", "admin");

       GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
       DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
               "jdbc:mysql://localhost:3306/agentska?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", properties
       );
       new PoolableConnectionFactory(connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED);
       this.dataSource = new PoolingDataSource(pool);
   }

   public static Connection getDatabaseConnection() throws SQLException {
       return Singleton.INSTANCE.dataSource.getConnection();
   }
}