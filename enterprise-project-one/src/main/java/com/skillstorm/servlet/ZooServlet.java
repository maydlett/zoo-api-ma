package com.skillstorm.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.beans.Animal;
import com.skillstorm.data.ZooDAO;

/**
 * Zoo Servlet
 * 
 * @author Matthew
 *
 */
@WebServlet(urlPatterns = "/api/zoo")
public class ZooServlet extends HttpServlet{

	ZooDAO dao = new ZooDAO();
	
	/**
	 * Doget override
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Use the DAO to retrieve all the movies
		Set<Animal> allAnimals = dao.findAll();
				
		// Convert out list of movies to a json strong
		String json = new ObjectMapper().writeValueAsString(allAnimals);
				
		// Write are json string to out response 
		resp.getWriter().print(json);
				
		// Setting the content type to json
		resp.setContentType("application/json");
		
	}
	/**
	 * Dopost override
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Parse the body of the http request
		InputStream requestBody = req.getInputStream();
		
		//Convert the request body into a java object 
		Animal animal = new ObjectMapper().readValue(requestBody, Animal.class);
		
		//Updating the movie objecy to contain the generated id
		Animal animalUpdated = dao.create(animal);
		
		System.out.println(animalUpdated);
		
		//Returning back the updated movie
		resp.getWriter().print(new ObjectMapper().writeValueAsString(animalUpdated));
		
		//Set status code to 201 : CREATED
		resp.setStatus(201);
		
		// Setting the content type to json
		resp.setContentType("application/json");
		
	}
	
	/**
	 * Doput override
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Parse the body of the http request
		InputStream requestBody = req.getInputStream();
		
		//Convert the request body into a java object 
		Animal animal = new ObjectMapper().readValue(requestBody, Animal.class);
		System.out.println(animal);
		
		dao.update(animal);
	}
	
	/**
	 * Doelete override
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Parse the body of the http request
		InputStream requestBody = req.getInputStream();
		
		//Convert the request body into a java object 
		Animal animal = new ObjectMapper().readValue(requestBody, Animal.class);
		System.out.println(animal);
		
		dao.delete(animal);
	}
	
}
