package com.tengen.shiftkeeper.common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class UserObject {

	private String name;
	private String displayName;
	private String email;
	
	public String getDisplayName() {
		return displayName;
	}
	@XmlElement(name = "display-name", required = false)
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getName() {
		return name;
	}
	@XmlAttribute(name = "name", required = true)
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	@XmlElement(name = "email", required = false)
	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserObject() {
	}
	
	public UserObject(String email) {
		this.email = email;
	}
	
	public UserObject(String name, String email) {
		this.email = email;
		this.name = name;
	}
}
