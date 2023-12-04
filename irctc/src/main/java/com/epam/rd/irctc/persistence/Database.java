package com.epam.rd.irctc.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Database {
	
	private static Logger logger;
	
	static {
		logger = Logger.getLogger(Database.class);
	}
	
	private Database() {}
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException exception) {
			logger.error(exception.getMessage());
		}
		
		Connection connection = null;
		
		String databasPropertiesFilePath = new File("C:\\Users\\Sachith_Janjirala\\eclipse-workspace\\irctc\\src\\main\\resources\\database.properties").getAbsolutePath();
		
		Properties dbProperites = new Properties();
		
		try(FileInputStream input = new FileInputStream(databasPropertiesFilePath)) {
			
			dbProperites.load(input);
			connection = DriverManager.getConnection(dbProperites.getProperty("url"),dbProperites.getProperty("user"),dbProperites.getProperty("password"));
		
		} catch(IOException e) {
			
			Logger logger = Logger.getLogger(Database.class);
			logger.debug(e.getMessage());
		}
		
		
		return connection;
	}
}

