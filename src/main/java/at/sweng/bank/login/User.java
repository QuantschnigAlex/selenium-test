package at.sweng.bank.login;

public class User {
  int user_id;
  String username;
  String password;
  String email;
  String vorname;
  String nachname;
  String account;
  
  public User() {
  }
  
  public String getUsername() {
  	return username;
  }
  
  public String getPassword() {
  	return password;
  }
  
  public String getEmail() {
	  return email;
  }
  
  public String getVorname() {
	  return vorname;
  }

  public String getNachname() {
	  return nachname;
  }
  
  public String getAccount() {
	  return account;
  }
  
  public String getFullname() {
	  return String.format("%s %s", getVorname(), getNachname());
  }
}