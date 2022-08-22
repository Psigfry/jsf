package ru.javastudy.reso.dao;

import ru.javastudy.reso.util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * LoginDAO
 *
 * @author Psigfry
 * psigfry.corp@yandex.ru
 * created 07/08/2022 - 19:20
 */


public class LoginDAO {
	public static boolean validate(String user, String password, String role){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select * from users where uname = ? and password = ?");
			ps.setString(1,user);
			ps.setString(2,password);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				System.out.println(resultSet.getBoolean(6));
				role = resultSet.getString(6);
				System.out.println(role);
				return true;
			}else
				System.out.println("Ne zashlo");

		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}

	public static String isAdmin(String user,String role){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select * from users where uname = ?");
			ps.setString(1,user);
			ResultSet resultSet = ps.executeQuery();
			if(resultSet.next()){
				role = resultSet.getString(4);
				return role;
			}

		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return "false";
		} finally {
			DataConnect.close(con);
		}
		return "false";
	}

}
