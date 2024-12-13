package at.sweng.bank.transfer;

public class Transfer {
	int transfer_id;
	String source_name;
	String source_account;
	String receiver_name;
	String receiver_account;	
	double amount;	

  public Transfer() {
  }
 
  public Transfer( String sourceName, String sourceAccount, String receiverName, String receiverAccount, double amount ) {
  	this.source_name = sourceName;
  	this.source_account = sourceAccount;
  	this.receiver_name = receiverName;
  	this.receiver_account = receiverAccount;
  	this.amount = amount;
  }
  
  public String getSourceName() {
  	return source_name;
  }
  
  public String getSourceAccount() {
  	return source_account;
  }
  
  public String getReceiverName() {
  	return 	receiver_name;
  }
	  
  public String getReceiverAccount() {
  	return receiver_account;
  }
  
  public double getAmount() {
	  return amount;
  }
 
  public String[] toArray() {
	  String[] row = new String[5];
	  row[0] = source_name;
	  row[1] = source_account;
	  row[2] = receiver_name;
	  row[3] = receiver_account;
	  row[4] = String.format("%.2f", amount);
	  return row;
  }
}