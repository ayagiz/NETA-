package com.netas.project.HSQLDB;
import java.util.ArrayList;
import java.util.List;

import com.netas.project.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class HSQLDB {
	private Connection con = null;
	
	//constructor connects to the database
	public HSQLDB(){
		 try {
	         //Registering the HSQLDB JDBC driver
	         Class.forName("org.hsqldb.jdbc.JDBCDriver");
	         //Creating the connection with HSQLDB
	         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/studentdb", "SA", "");
	         if (con!= null){
	            System.out.println("Connection created successfully");
	            
	         }else{
	            System.out.println("Problem with creating connection");
	         }
	      
	      }  catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	}
	public Student register(Student student) {
        Statement stmt = null; 
        int result = 0;
        int id = 1;
        ArrayList<Student> arr = get_students();
        if(arr != null && !arr.isEmpty()) {
        	for(int i = 0; i < arr.size() ; ++i)
        		if(arr.get(i).getId() == id)
        			++id;
        }
        student.setId(id);
	      try { 
	    	  System.out.println("----------------------------------------");
	    	  System.out.println(student);
	    	  System.out.println("-----------------------------------------");
	          stmt = con.createStatement(); 
	          result = stmt.executeUpdate("INSERT INTO STUDENT_TABLE VALUES (" + student.getId() + ",'"+ student.getName()+ "','" + student.getSurname()+"','" + student.getPhone_number() + "','" + student.getCity()+"','" + student.getDistrict() + "','" + student.getDescription()+"')"); 
	          con.commit(); 
		      System.out.println(result+" rows effected"); 
		      System.out.println("Rows inserted successfully");
	          return student;
	       }catch (Exception e) { 
	          e.printStackTrace(System.out); 
	       } 
	      return null;
	}
	public Student update(Student s) {
		Statement stmt = null;
	    int result = 0;
	      try {
	          stmt = con.createStatement();
	          result = stmt.executeUpdate(
	             "UPDATE STUDENT_TABLE SET NAME = '" +s.getName() + "' , SURNAME = '" + s.getSurname()+ "' , PHONE_NUMBER = '" + s.getPhone_number()+ "' , CITY = '" + s.getCity() + "' , DISTRICT = '" + s.getDistrict() + "' , DESCRIPTION = '" + s.getDescription()   +   "' WHERE id = " + s.getId());
		      System.out.println("Student with id " + s.getId() + " updated");
	          return s;
	      } catch (Exception e) {
	          e.printStackTrace(System.out);
	       }
	      return null;
	}
	public ArrayList<Student> get_students(){
		Statement stmt = null;
	    ResultSet result = null;
	    try {
	         stmt = con.createStatement();
	         result = stmt.executeQuery(
	            "SELECT * FROM STUDENT_TABLE ORDER BY ID ASC");
	         ArrayList<Student> students = new ArrayList<Student>();
	         while(result.next()){
	            students.add(new Student(result.getInt("ID"),result.getString("NAME"),result.getString("SURNAME"),result.getString("PHONE_NUMBER"),result.getString("CITY"),result.getString("DISTRICT"),result.getString("DESCRIPTION")));
	         }
	         return students;
	      } catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	    return null;
	}
	public void delete(int id) {
		Statement stmt = null;
	    int result = 0;
	      try {
	          stmt = con.createStatement();
	          result = stmt.executeUpdate(
	             "DELETE FROM STUDENT_TABLE   WHERE id=" + id);
	       } catch (Exception e) {
	       
	          e.printStackTrace(System.out);
	       }
	       System.out.println("Student with id " + id + " deleted.");
	}
	public Student get_byID(int id) {
		Statement stmt = null;
	    ResultSet result = null;
	    try {
	         stmt = con.createStatement();
	         result = stmt.executeQuery(
	            "SELECT * FROM STUDENT_TABLE WHERE ID = " + id );
	         if(result.next()){
	            return new Student(result.getInt("ID"),result.getString("NAME"),result.getString("SURNAME"),result.getString("PHONE_NUMBER"),result.getString("CITY"),result.getString("DISTRICT"),result.getString("DESCRIPTION"));
	         }
	      } catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
		return null;
	}
	
}
