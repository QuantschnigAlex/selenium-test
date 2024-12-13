package at.sweng.bank.database;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Database {
	private static Sql2o sql2o;
	
	private Database() {
        sql2o = new Sql2o("jdbc:h2:mem:testdb" + System.nanoTime() + ";DB_CLOSE_DELAY=-1", "sa", "sa");
		setup();
	}
	
	private static class DatabaseSingleton {
		private static final Database INSTANCE = new Database();
	}
	
	public static Database getInstance() {
		return DatabaseSingleton.INSTANCE;
	}
	
	public Sql2o getDatabase() {
		return sql2o;
	}
	
    private static void setup() {
    	try (Connection conn = sql2o.open()) {
    		conn.createQuery("CREATE TABLE tuser (" +
    				"user_id INT AUTO_INCREMENT PRIMARY KEY, " +
    				"username VARCHAR(30), " +
    				"password VARCHAR(50), " +
    				"vorname VARCHAR(50), " +
    				"nachname VARCHAR(50), " +
    				"email VARCHAR(50), " +
    				"account VARCHAR(30))")
    		.executeUpdate();
    	}
        
    	try (Connection conn = sql2o.open()) {
    		conn.createQuery("CREATE TABLE transfer (" +
    				"transfer_id INT AUTO_INCREMENT PRIMARY KEY, " +
    				"source_name VARCHAR(50), " +
    				"source_account VARCHAR(30), " +
    				"receiver_name VARCHAR(50), " +
    				"receiver_account VARCHAR(50), " +
    				"amount NUMERIC, " +
    				"timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")
    		.executeUpdate();
    	}
       	
       	try (Connection conn = sql2o.open()) {
    		conn.createQuery("INSERT INTO tuser(username, password, vorname, nachname, email, account)" +
    				"VALUES('max23','xG5v!3Qp','Max','Mustermann','x7qp9z@example.com','AT026000000001349870')," +
    				"('lisa','A1b2C3d4','Lisa','Schmidt','jd3k4m@domain.org','AT021420020010147558')," +
    				"('john','7Hjk!2Mnp','John','Doe','z8l5n2@gmail.com','DE02701500000000594937')," +
    				"('sarah','P@ssW0rd1','Sarah','Klein','kp1vx7@webmail.net','DE02370502990000684712')," +
    				"('peter','L9m&2xKa','Peter','Huber','r9yf6t@service.de','AT023200000000641605')," +
    				"('julia','Qz8@R3T7','Julia','Bauer','b3xw8p@provider.com','DE02700100800030876808')," +
  					"('thomas2','N1ceP@ss','Thomas','Meier','q5lzn4@mailbox.com','AT021200000703447144')," +
    				"('anna11','R@nd0mP4','Anna','Schulz','a4z7pl@example.com','DE02100100100006820101')," +
    				"('a','b','Anton','Benko','toni@example.com','AT022011100003429660')")
    		.executeUpdate();
    	}
    	
    	try (Connection conn = sql2o.open()) {
    		conn.createQuery("INSERT INTO transfer (source_account, source_name, receiver_name, receiver_account, amount)" +
    				"VALUES('AT021420020010147558','Lisa Schmidt','Anton Benko','AT022011100003429660',100.0), "+
    				"('AT021200000703447144','Thomas Meier','Anton Benko','AT022011100003429660',7812.90), " +
    				"('AT023200000000641605','Peter Huber','Lisa Schmidt','AT021420020010147558',3000), " +
    				"('DE02100100100006820101','Anna Schulz','Julia Bauer','DE02700100800030876808',650), " +
    				"('DE02701500000000594937','John Doe','Max Mustermann','AT026000000001349870',10.90)")
    		.executeUpdate();
    	}
    }
}