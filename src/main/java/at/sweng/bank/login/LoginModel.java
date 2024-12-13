package at.sweng.bank.login;

import java.util.List;

import org.sql2o.Connection;

import at.sweng.bank.database.Database;

public class LoginModel {
	private User loginUser;
	protected boolean isLocked;
	protected boolean wrongVersion=false;
	
	public LoginModel() {
	}

	public boolean verifyUser(String userName, String passwd) {
		String sql =
				"SELECT user_id, username, password, email, vorname, nachname, account " +
				"FROM tuser " +
				"WHERE upper(username) like upper(:uname) AND password = :passwd";

		try(Connection con = Database.getInstance().getDatabase().open()) {
			List<User> uList= con.createQuery(sql)
					.addParameter("uname", userName)
					.addParameter("passwd", passwd)
					.executeAndFetch(User.class);
			
			if (!uList.isEmpty()) {
				loginUser = uList.get(0);
				return true;
			}
			else
				return false;
		}
	}

	public void dispose() {
	}

	public User getLogin() {
		return loginUser;
	}
}