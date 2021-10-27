package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.skillstorm.beans.Animal;

/**
 * Zoo DAO
 * 
 * @author Matthew
 *
 */
public class ZooDAO {

	//Database credentials
	private static final String url = "jdbc:mysql://localhost:3306/zoo";
	private static final String username = "root";
	private static final String password = "root";
	
	// 1. Load the driver into memory
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// CRUD operations (CREATE, READ, UPDATE, DELETE)
	
	/**
	 * Create function
	 * @param animal
	 * @return an animal
	 */
	public Animal create (Animal animal) {
		
		// 2. Make a connection object using the driver manager -- 5. Close the connection
			try (Connection conn = DriverManager.getConnection(url, username, password)) {
				
				// 3. Create a statement 
				
				//PreparedStatement is complied by JAVA, then sent to MySQL
				String sql = "INSERT INTO animals"
						+ " (name, scientific_name, life_span, weight_lb, diet, endangered_status, picture)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //Tells database to return generated keys
				
				//Set ? in sql 
				stmt.setString(1, animal.getName());
				stmt.setString(2, animal.getScientific());
				stmt.setInt(3, animal.getLifespan());
				stmt.setDouble(4, animal.getWeight());
				stmt.setString(5, animal.getDiet());
				stmt.setString(6, animal.getStatus());
				stmt.setString(7, animal.getPicurl());
				
				// 4. Execute the statement
				stmt.executeUpdate(); 
				
				// Getting back the auto-incremented id from database
				ResultSet keys = stmt.getGeneratedKeys();
				keys.next();
				
				int id = keys.getInt(1);
				
				animal.setId(id);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return animal;
	}
	
	/**
	 * Read function
	 * @return a set of animals
	 */
	public Set<Animal> findAll() {
		
		Set<Animal> allAnimals = new HashSet<>();
		
		// 2. Make a connection object using the driver manager -- 5. Close the connection
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			
			// 3. Create a statement 
			String sql = "SELECT * FROM animals";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			// 4. Execute the statement
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) {
				//Retrieve Animal		
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String scientific = rs.getString("scientific_name");
				int lifespan = rs.getInt("life_span");
				double weight = rs.getDouble("weight_lb");
				String diet = rs.getString("diet");
				String status = rs.getString("endangered_status");
				String picture = rs.getString("picture");
				
				//Create object
				Animal animal = new Animal(id, name, scientific, lifespan, weight, diet, status, picture);
				
				//Add to set
				allAnimals.add(animal);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Return back the updated movie with an ID
		return allAnimals;
	}
	
	/**
	 * Update function
	 * 
	 * @param animal
	 */
	public void update (Animal animal) {
		
		// 2. Make a connection object using the driver manager -- 5. Close the connection
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
					
			// 3. Create a statement 			
			String sql = "UPDATE animals "
					+ "SET name=?, scientific_name=?, life_span=?, weight_lb=?, "
					+ "diet=?, endangered_status=?, picture=? WHERE id = " + animal.getId();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, animal.getName());
			stmt.setString(2, animal.getScientific());
			stmt.setInt(3, animal.getLifespan());
			stmt.setDouble(4, animal.getWeight());
			stmt.setString(5, animal.getDiet());
			stmt.setString(6, animal.getStatus());
			stmt.setString(7, animal.getPicurl());
					
			// 4. Execute the statement
			stmt.executeUpdate(); 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Delete function
	 * @param animal
	 */
	public void delete (Animal animal) {
		
		// 2. Make a connection object using the driver manager -- 5. Close the connection
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
					
			// 3. Create a statement 			
			String sql = "DELETE FROM animals WHERE id = " + animal.getId();
			PreparedStatement stmt = conn.prepareStatement(sql);
					
			// 4. Execute the statement
			stmt.executeUpdate(); 
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
