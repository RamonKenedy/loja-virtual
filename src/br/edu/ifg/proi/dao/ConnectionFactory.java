package br.edu.ifg.proi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	// URL do MySQL: proi se refere ao nome da base de dados
	public static final String URL = "jdbc:mysql://localhost:3306/lojavirtual";
	
	public static final String USER = "root";
	
	public static final String PASSWORD = "leirra";

	public Connection getConnection() {
		try {
			// Carregando o driver do banco de dados
			Class.forName(DRIVER);
			
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
