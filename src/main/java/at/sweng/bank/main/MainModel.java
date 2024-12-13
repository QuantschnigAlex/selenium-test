package at.sweng.bank.main;

import java.util.List;

import org.sql2o.Connection;

import at.sweng.bank.database.Database;
import at.sweng.bank.login.User;
import at.sweng.bank.transfer.Transfer;

public class MainModel {
	User currentUser;
	
	protected void setUser(User user) {
		this.currentUser = user;
	}
	
	protected User getUser() {
		return currentUser;
	}
	
	public List<Transfer> getTransfers() {		
		String sql =
				"SELECT transfer_id, source_name, source_account, receiver_name, receiver_account, amount " +
				"FROM transfer " +
				"WHERE source_account = :account OR  receiver_account = :account";

		try(Connection con = Database.getInstance().getDatabase().open()) {
			List<Transfer> tList= con.createQuery(sql)
					.addParameter("account", currentUser.getAccount())
					.executeAndFetch(Transfer.class);
			return tList;
		}
	}
	
	public void saveTransfer(Transfer t) {
		String sql =
				"INSERT INTO transfer(source_name, source_account, receiver_name, receiver_account, amount) " +
				"VALUES(:sname, :saccount, :rname, :raccount, :amount)";

		try(Connection con = Database.getInstance().getDatabase().open()) {
			con.createQuery(sql)
					.addParameter("sname", t.getSourceName())
					.addParameter("saccount", t.getSourceAccount())
					.addParameter("rname", t.getReceiverName())
					.addParameter("raccount", t.getReceiverAccount())
					.addParameter("amount", t.getAmount())
					.executeUpdate();			
		}
	}
}