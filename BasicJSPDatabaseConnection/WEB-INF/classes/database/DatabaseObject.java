package database;


public class DatabaseObject{
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	
	public DatabaseObject(){
		
	}
	
	public DatabaseObject(String firstName, String lastName, String phoneNumber, String emailAddress){
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmailAddress(){
		return this.emailAddress;
	}
	
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}
}