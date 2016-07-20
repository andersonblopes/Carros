package br.com.livro.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {

	public BaseDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() throws SQLException {
		String url = "jdbc:postgresql://192.168.0.200:5432/livro";
		Connection con = DriverManager.getConnection(url, "postgres", "st97ch19");
		return con;
	}

	public static void main(String[] args) throws SQLException {
		BaseDAO dao = new BaseDAO();
		//Testa a conexão
		Connection con = dao.getConnection();
		System.out.println(con);
	}

}
