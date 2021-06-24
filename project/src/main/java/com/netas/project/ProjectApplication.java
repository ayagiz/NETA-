package com.netas.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.netas.project.HSQLDB.HSQLDB;



@SpringBootApplication
@RestController
public class ProjectApplication {
	HSQLDB con = new HSQLDB();

	City_District city_district_data = City_District.getInstance();
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	 @GetMapping("/students/city_district_data")
	  ArrayList<ArrayList<String>> getCityDitrictData() {
	   return city_district_data.getData();
	  }
	 @GetMapping("/students")
	  ArrayList<Student> get_students() {
	   return con.get_students();
	  }
	  @GetMapping("/students/{id}")
	  public Student getClient(@PathVariable int id) {
		  return con.get_byID(id);
	  }
	 @PostMapping("/students")
	  Student newStudent(@RequestBody Student newStudent) {
	    return con.register(newStudent);
	  }
	@SuppressWarnings("resource")
	@PostMapping("/students/upload/{id}")
	 void uploadFile(@RequestParam("file") MultipartFile file,@PathVariable int id ) {
		 try {
			 System.out.println("Got the file with id " + id);
			 File f = new File("studentFile_" + id + ".txt");
			 if(!f.exists())
				 f.createNewFile();
			 System.out.println("you can find the uploaded file at " + f.getAbsolutePath());
			InputStream stream = file.getInputStream();
			String data = new String(stream.readAllBytes());
			
			FileWriter writer = new FileWriter("studentFile_" + id + ".txt");
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	  @PutMapping("/students/{id}")
	  Student replaceEmployee(@RequestBody Student newStudent, @PathVariable int id) {
	   return con.update(newStudent);
	  }	 
	  @DeleteMapping("/students/{id}")
	  void deleteStudent(@PathVariable int id) {
	    con.delete(id);
	  }
}
