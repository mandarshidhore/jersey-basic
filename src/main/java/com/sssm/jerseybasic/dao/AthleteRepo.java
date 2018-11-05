package com.sssm.jerseybasic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sssm.jerseybasic.model.Athlete;

public class AthleteRepo {

	Connection con = null;
	private static final String dburl = "jdbc:mysql://localhost:3306/jerseybasic";

	public AthleteRepo() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dburl, "root", "swami");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Unable to connect to db");
		}
	}

	public List<Athlete> getAllAthletes() {
		List<Athlete> athletes = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from athlete");
			while (rs.next()) {
				Athlete athlete = new Athlete();
				athlete.setId(rs.getInt(1));
				athlete.setAge(rs.getInt(2));
				athlete.setName(rs.getString(3));
				athletes.add(athlete);
			}
		} catch (SQLException e) {
			System.err.println("Exception while executing query");
		}

		return athletes;
	}

}
