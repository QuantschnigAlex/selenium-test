package at.sweng.bank.main;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import at.sweng.bank.ApplicationEvent;
import at.sweng.bank.ApplicationEventListener;
import at.sweng.bank.login.User;
import at.sweng.bank.transfer.TransferDialog;

public class MainController {
	private MainView view;
	private MainModel model;
	private ApplicationEventListener listener;

	public MainController( Composite p ) {	
		view = new MainView( p );
		view.create();
		model = new MainModel();
		createListener( );
		view.refresh();		
	}
	
	private void createListener( ) {
		view.transferButton.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 8332878835653296981L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				openTransferDialog();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				openTransferDialog();
			}
		});
		
		view.reloadButton.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = 3280457269005062448L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				loadData();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				loadData();
			}
		});
		
		view.logoutButton.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = -7984008031247102611L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				listener.handle((ApplicationEvent.LOGOUT), 0);	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				listener.handle((ApplicationEvent.LOGOUT), 0);	
			}
		});
	}
	
	// load initial data from db.
	private void loadData() {
		view.clearTable();
		view.addTransfers(model.getTransfers());
	}
	
	private void openTransferDialog() {
		TransferDialog transferDialog = new TransferDialog(Display.getCurrent().getActiveShell(), model.getUser());
	    transferDialog.open();
	    
	    // Add listener to handle dialog closing
	    transferDialog.getShell().addListener(SWT.Dispose, event -> {
	        if (transferDialog.getReturnCode() == IDialogConstants.OK_ID) {
	            view.addTransfer(transferDialog.getTransfer());
	            model.saveTransfer(transferDialog.getTransfer());
	        }
	    });
	}

	public synchronized void setEventListener( ApplicationEventListener l) {
		listener = l;
	}
	
	public void setUser(User user) {
		model.setUser(user);
		view.setUserName(String.format("Kunde: <b>%s</b> Konto: <b>%s</b>",user.getFullname(), user.getAccount()));
		loadData();
	}
	
	public Composite getTopControl() {
		return view.getTopComposite();
	}
	
	public void refresh() {
		loadData();
		view.refresh();
	}
	
	public void dispose(  ) {
		view.dispose();
	}
	
	public void setVisible() {
		view.setVisible(true);	
		view.refresh();
	}
	
	public void setInVisible() {
		view.setVisible(false);	
	}
}