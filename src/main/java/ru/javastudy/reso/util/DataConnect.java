package ru.javastudy.reso.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * DataConnect
 *
 * @author Psigfry
 * psigfry.corp@yandex.ru
 * created 07/08/2022 - 19:23
 */


public class DataConnect {
	public static Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/resodb", "postgres", "1y1a8a72&K&y");
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}
	public static void close(Connection con){
		try {
			con.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
