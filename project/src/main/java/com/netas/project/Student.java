package com.netas.project;
import java.util.Objects;


public class Student {
private int id;
private String name,surname,phone_number,city,district,description;
public Student(int ID,String NAME,String SURNAME,String PHONE,String CITY,String DISTRICT,String DESCRIPTION){
	id = ID;
	name=NAME;
	surname=SURNAME;
	phone_number=PHONE;
	city= CITY;
	district =DISTRICT;
	description = DESCRIPTION;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public String getPhone_number() {
	return phone_number;
}
public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

public String toString() {
return "student with id: " + id + " name: " + name + " surname: " + surname + " city: " + city + " district: " + district + " phone: " + phone_number + " description: " + description; 
}

}
