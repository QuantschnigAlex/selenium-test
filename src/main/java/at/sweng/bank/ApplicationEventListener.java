package at.sweng.bank;

public interface ApplicationEventListener {
	public void handle(ApplicationEvent event, int id);
}
