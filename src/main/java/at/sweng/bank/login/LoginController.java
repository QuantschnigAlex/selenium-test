package at.sweng.bank.login;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import at.sweng.bank.ApplicationEvent;
import at.sweng.bank.ApplicationEventListener;

public class LoginController {
	private LoginView view;
	private LoginModel model;
	private ApplicationEventListener listener;

	public LoginController( Composite p) {
		model = new LoginModel();
		view = new LoginView( p );
		view.create( );
		createListener();
	}

	/*
	 *  set a ApplicationEventListener to handle the Login result
	 */
	public void setLoginEventListener( ApplicationEventListener l) {
		listener = l;
	}

	private void createListener() {
		
		view.bOk.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = -5373220735369559156L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				checkCustomerLogin();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				checkCustomerLogin();
			}
		});
		
		view.bCancel.addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = -5373220735369559157L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				view.clearFields();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				view.clearFields();
			}
		});
		
		view.tPass.addKeyListener(new KeyAdapter() {
			private static final long serialVersionUID = 4451901892165187042L;

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR ) {
					checkCustomerLogin();
				}
			}
		});	
	}
	
	private void checkCustomerLogin() {
		if ( model.verifyUser(view.getUserName(), view.getPassword()) ) {
			view.setStatus("\n\n");
			listener.handle((ApplicationEvent.LOGIN_OK), 0);
		}
		else {
			view.setStatus("Falscher Username oder Passwort");
		}
	}

	public Composite getTopControl() {
		return view.getTopComposite();
	}
		
	public void setVisible() {
		view.setVisible(true);	
	}
	
	public void setInvisible() {
		view.setVisible(false);
	}	
	
	public void disposeAll() {
		view.disposeAll();
		model.dispose();
	}

	public User getLogin() {
		return model.getLogin();		
	}

	public void clearFields() {
		view.clearFields();
	}
}