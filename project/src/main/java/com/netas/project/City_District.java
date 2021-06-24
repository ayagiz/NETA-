package com.netas.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//singleton class
public class City_District {
	private static City_District single_CityDistrict_instance = null;
	

	
	private ArrayList<ArrayList<String>> city_and_district_array;
	
	private City_District(){
		city_and_district_array = new ArrayList<ArrayList<String>>();
	}
	public static City_District getInstance() {
		if(single_CityDistrict_instance == null) {
			single_CityDistrict_instance = new City_District();
			single_CityDistrict_instance.load_data();
		}
		return single_CityDistrict_instance;
	}
	private void load_data() {
	    try {
	        File myObj = new File("city_district_relation.txt");
	        Scanner myReader = new Scanner(myObj);
	        myReader.nextLine();
	        int old_index = -1;
	        while (myReader.hasNextLine()) {
	          String[] data = myReader.nextLine().split(",");
	          int index = Integer.parseInt(data[0]) - 1;
	          if(old_index != index) {
	        	  single_CityDistrict_instance.city_and_district_array.add(new ArrayList<String>());
	        	  single_CityDistrict_instance.city_and_district_array.get(index).add(data[2]);
	        	  old_index = index;
	          }
	          single_CityDistrict_instance.city_and_district_array.get(index).add(data[3]);
	          
	        }
	        myReader.close();
	        
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	public ArrayList<ArrayList<String>> getData() {
		return single_CityDistrict_instance.city_and_district_array;
	}
}
