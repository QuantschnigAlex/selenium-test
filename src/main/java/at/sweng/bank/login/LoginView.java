package at.sweng.bank.login;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import at.sweng.bank.SystemDefaults;

public class LoginView {
	private Composite parent;
	private Composite cLogin;
	protected Text tUser;
	protected Text tPass;
	protected Button bOk;
	protected Button bCancel;
	protected CLabel lStatus;
	
	private final static int TOP_POS=30;	
	private final static int TEXT_WIDTH= 300;
	
	public LoginView( Composite p ) {
		parent = p;
	}
	
	public void create() {
		cLogin = new Composite(parent, SWT.NONE);
		cLogin.setLayout(new FormLayout());
		cLogin.setBackground(SystemDefaults.getDefaultBackgroundColor());
		createLogin( cLogin );
		createControls( cLogin );
		parent.layout();
	}
	
	public void createLogin( Composite c ) {
		//-------------------- 
		// Logo
		//--------------------
		CLabel labelLogo = new CLabel(c, SWT.NONE);
		labelLogo.setText("");
		labelLogo.setImage(JFaceResources.getImageRegistry().get("logo"));
		labelLogo.setBackground(SystemDefaults.getDefaultBackgroundColor());
				
		FormData f = new FormData();
		f.left = new FormAttachment(getCenterOffset(), 0);
		f.top  = new FormAttachment(TOP_POS);
		labelLogo.setLayoutData(f);
		
		// 
		// Überschrift
		//
		CLabel labelImg = new CLabel(c, SWT.NONE);
		labelImg.setText("Anmeldung");
		labelImg.setFont( JFaceResources.getFontRegistry().get("FONT_BOLD"));
		labelImg.setBackground(SystemDefaults.getDefaultBackgroundColor());		
		f = new FormData();
		f.top = new FormAttachment(labelLogo,  SystemDefaults.getDefaultBorderWidth(), SWT.BOTTOM);
		f.left = new FormAttachment(getCenterOffset(), 0);
		f.width = TEXT_WIDTH;
		labelImg.setLayoutData(f);

		//-------------------- 
		// Label name
		//--------------------
		CLabel labelName = new CLabel(c, SWT.NONE);
		labelName.setText("Username");
		labelName.setFont( JFaceResources.getFontRegistry().get("FONT_NORMAL"));
		labelName.setBackground(SystemDefaults.getDefaultBackgroundColor());
		f = new FormData();
		f.top = new FormAttachment(labelImg, SystemDefaults.getDefaultBorderWidth(), SWT.BOTTOM);
		f.left = new FormAttachment(getCenterOffset(), 0);
		labelName.setLayoutData(f);

		//-------------------- 
		// Textbox name
		//--------------------
		tUser = new Text(c, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		tUser.setFont( JFaceResources.getFontRegistry().get("FONT_NORMAL"));
		tUser.setBackground(SystemDefaults.getDefaultForegroundColor());
		tUser.setText ("");
		f = new FormData();
		f.top = new FormAttachment(labelName, SystemDefaults.getDefaultBorderWidth(), SWT.BOTTOM);
		f.left = new FormAttachment(getCenterOffset(), 0);
		f.width = TEXT_WIDTH;
		tUser.setLayoutData(f);
		tUser.setFocus();
		tUser.setData("widgetID", "userTextInput");

		//-------------------- 
		// Label password
		//--------------------
		labelName = new CLabel(c, SWT.NONE);
		labelName.setText("Passwort");
		labelName.setFont( JFaceResources.getFontRegistry().get("FONT_NORMAL"));
		labelName.setBackground(SystemDefaults.getDefaultBackgroundColor());		
		f = new FormData();
		f.top = new FormAttachment(tUser, SystemDefaults.getDefaultBorderWidth(), SWT.BOTTOM);
		f.left = new FormAttachment(getCenterOffset(), 0);
		labelName.setLayoutData(f);
		
		//-------------------- 
		// Textbox password
		//--------------------
		tPass = new Text(c, SWT.SINGLE | SWT.BORDER | SWT.LEFT | SWT.PASSWORD);
		tPass.setData(RWT.CUSTOM_VARIANT, "login-password");
		tPass.setFont( JFaceResources.getFontRegistry().get("FONT_NORMAL"));
		tPass.setBackground(SystemDefaults.getDefaultForegroundColor());
		tPass.setText ("");
		f = new FormData();
		f.top = new FormAttachment(labelName, SystemDefaults.getDefaultBorderWidth(), SWT.BOTTOM);
		f.left = new FormAttachment(getCenterOffset(), 0);
		f.width = TEXT_WIDTH;
		tPass.setLayoutData(f);
		
		lStatus = new CLabel(c, SWT.NONE);
		lStatus.setText("");
		lStatus.setFont( JFaceResources.getFontRegistry().get("FONT_BOLD"));
		lStatus.setForeground(SystemDefaults.getDefaultErrorColor());
		lStatus.setBackground(SystemDefaults.getDefaultBackgroundColor());
		f = new FormData();
		f.top = new FormAttachment(tPass, SystemDefaults.getDefaultBorderWidth(), SWT.BOTTOM);
		f.left = new FormAttachment(getCenterOffset(), 0);
		f.width = TEXT_WIDTH;
		lStatus.setLayoutData(f);
		
	}
	private void createControls(Composite c) {
		// -------------------------------------------------------------------------------
		// Controls
		// -------------------------------------------------------------------------------		
		bOk = new Button(c, SWT.BUTTON1);
		bOk.setData(RWT.CUSTOM_VARIANT, "login-button");
		bOk.setText("Login");	
		bOk.setEnabled(true);
		FormData f = new FormData();
		f.top = new FormAttachment(lStatus, SystemDefaults.getDefaultBorderWidth()*10, SWT.BOTTOM);
		f.left = new FormAttachment(getCenterOffset(), 0);
		bOk.setLayoutData(f);

		bCancel = new Button(c, SWT.BUTTON1);
		bCancel.setText("Abbrechen");	
		bCancel.setEnabled(true);
		f = new FormData();
		f.top = new FormAttachment(lStatus, SystemDefaults.getDefaultBorderWidth()*10, SWT.BOTTOM);
		f.left = new FormAttachment(bOk, 10, SWT.RIGHT);
		bCancel.setLayoutData(f);
	}
	
	private int getCenterOffset() {
		return 40;
	}
	
	public void setStatus(String message) {
		lStatus.setText(message);
	}
	
	public Composite getTopComposite() {
		return cLogin;
	}
	
	public void setVisible ( boolean what ) {
		cLogin.setVisible(what);
	}	
	
	public String getUserName() {
		return tUser.getText();
	}
	
	public String getPassword() {
		return tPass.getText();
	}
	
	public void disposeAll() {
		cLogin.setVisible(false);
	}
	
	public void clearFields() {
		tUser.setText("");
		tPass.setText("");
		lStatus.setText("");
	}
}