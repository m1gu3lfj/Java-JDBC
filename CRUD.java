package com.examples.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {
	String database = "jdbc:mysql://localhost:3306/miguelDB";
	String user = "root";
	String pass = "****";
	
	public CRUD() {	
	}
	
	public void createTable() throws SQLException {
		Connection c = DriverManager.getConnection(database, user, pass);
		String sql = "create table if not exists Jugador(id int, nombre varchar(20), fechan date, primary key(id))";
		Statement s = c.createStatement();
		s.execute(sql);
		s.close();
		c.close();
	}
	
	public void insertInto(int id, String nom, String fec) throws SQLException {
		Connection c = DriverManager.getConnection(database, user, pass);
		String sql = "insert into Jugador values(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, nom);
		ps.setString(3, fec);
		ps.execute();
		ps.close();
		c.close();
	}
	
	public void updateSet(int id, String nom, String fec) throws SQLException {
		Connection c = DriverManager.getConnection(database, user, pass);
		String sql = "update Jugador set nombre=?, fechan=? where id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, nom);
		ps.setString(2, fec);
		ps.setInt(3, id);
		ps.execute();
		ps.close();
		c.close();
	}
	
	public void deleteFrom(int id) throws SQLException {
		Connection c = DriverManager.getConnection(database, user, pass);
		String sql = "delete from Jugador where id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		ps.close();
		c.close();
	}
	
	public void selectFrom() throws SQLException {
		Connection c = DriverManager.getConnection(database, user, pass);
		String sql = "select * from Jugador";
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(sql);
		System.out.println("Id Nombre               Fecha Nac");
		while (rs.next()) {
			System.out.printf("%2d %-20s %s%n", rs.getInt(1) ,rs.getString(2) , rs.getString(3));
		}	
		rs.close();
		s.close();
		c.close();
	}
	
	/*
	public static void main(String[] args) throws SQLException {
		System.out.println("Ejemplo de uso de CRUD");
		CRUD oc = new CRUD();
		oc.createTable();
		oc.insertInto(1, "Joaco", "1987-03-11");
		oc.insertInto(5, "Sergio", "1981-12-22");
		oc.insertInto(7, "Dani", "1985-02-21");
		oc.insertInto(9, "Miguel", "1982-10-29");
		oc.insertInto(6, "Edu", "1991-01-03");
		oc.selectFrom();
	}
	*/
}
