package at.sweng.bank.main;

import java.util.List;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import at.sweng.bank.SystemDefaults;
import at.sweng.bank.transfer.Transfer;

public class MainView {
	private Composite parent;
	private Table transferTable;
	private Composite cMain;
	private Composite cStatus;	

	protected Button transferButton;
	protected Button reloadButton;
	protected Button logoutButton;
	protected CLabel labelStatus;
	protected CLabel labelUser;
	protected CLabel labelLogo;
	protected CLabel labelSettings;
	
	public MainView( Composite p ) {
		parent = p;
	}
	
	public void create()  {
		createComposites();
		createStatusBar();
		cMain.layout();
	}
	
	private void createComposites() {
		cMain = new Composite(parent, SWT.NONE);
		cMain.setBackground(SystemDefaults.getDefaultBackgroundColor());
		cMain.setLayout(new FormLayout());
		
		cStatus = new Composite(cMain, SWT.NONE);
		cStatus.setBackground(SystemDefaults.getDefaultBackgroundColor());
		FormData f = new FormData();
		f.top = new FormAttachment(0,0);
		f.left = new FormAttachment(0, 0);
		f.right = new FormAttachment(100, 0);
		f.bottom = new FormAttachment(10, 0);
		cStatus.setLayoutData(f);		
		
		Composite cTransferList = new Composite(cMain, SWT.NONE);
		cTransferList.setBackground(SystemDefaults.getDefaultBackgroundColor());
		cTransferList.setLayout(new FillLayout());
		f = new FormData();
		f.top = new FormAttachment(cStatus,SystemDefaults.getDefaultBorderWidth(), SWT.BOTTOM);
		f.left = new FormAttachment(10, SystemDefaults.getDefaultBorderWidth());
		f.right = new FormAttachment(90, -SystemDefaults.getDefaultBorderWidth());
		f.bottom = new FormAttachment(95, -SystemDefaults.getDefaultBorderWidth());
		cTransferList.setLayoutData(f);		
		
		createTable(cTransferList);
	}
	
	private void createTable(Composite parent) {
		
		 transferTable = new Table(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		 transferTable.setHeaderVisible(true);
		 transferTable.setLinesVisible(true);

	        // Define columns
	        String[] columnHeaders = { "Sendername", "Sender-Konto", "Empfängername", "Empfänger-Konto", "Betrag" };
	        for (String header : columnHeaders) {
	            TableColumn column = new TableColumn(transferTable, SWT.NONE);
	            column.setText(header);
	            column.setWidth(250);
	        }
	}
	
	public void addTransfer(Transfer transfer) {
		TableItem item = new TableItem(transferTable, SWT.NONE);
		item.setText(transfer.toArray());
	}
	
	public void addTransfers(List<Transfer> transfers) {
		if (transfers == null) return;
		for (Transfer t : transfers) {
			addTransfer(t);
		}
	}
	
	void createStatusBar() {
		cStatus.setLayout(new GridLayout(6, false));

		labelLogo = new CLabel(cStatus, SWT.NONE);
		labelLogo.setText("");
		labelLogo.setFont( JFaceResources.getFontRegistry().get("FONT_STATUS"));
		labelLogo.setBackground(SystemDefaults.getDefaultBackgroundColor());	
		labelLogo.setImage(JFaceResources.getImageRegistry().get("logo-s") );
		GridData gd = new GridData ();
		gd.grabExcessHorizontalSpace = false;
		gd.horizontalAlignment = SWT.LEFT;
		gd.verticalAlignment = SWT.CENTER;
		labelLogo.setLayoutData (gd);
		
		// ---------------------------------------------------------------------------------
		//  Name of current user
		// ---------------------------------------------------------------------------------		
		labelUser = new CLabel(cStatus, SWT.BORDER);
		labelUser.setText("");
		labelUser.setFont(JFaceResources.getFontRegistry().get("FONT_STATUS"));
		labelUser.setBackground(SystemDefaults.getDefaultBackgroundColor());
		labelUser.setAlignment(SWT.LEFT);
		labelUser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		labelUser.setData( RWT.MARKUP_ENABLED, Boolean.TRUE );
				
		CLabel lEmpty = new CLabel(cStatus, SWT.BORDER);
		lEmpty.setText("");
		lEmpty.setFont( JFaceResources.getFontRegistry().get("FONT_STATUS"));
		lEmpty.setBackground(SystemDefaults.getDefaultBackgroundColor());
		gd = new GridData ();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.LEFT;
		gd.widthHint = 150;
		gd.verticalAlignment = SWT.CENTER;
		lEmpty.setLayoutData (gd);

		// ---------------------------------------------------
		// Reload Button
		// ---------------------------------------------------
		reloadButton = new Button(cStatus, SWT.BUTTON1);
		reloadButton.setText("Reload");
		reloadButton.setEnabled(true);
		gd = new GridData ();
		gd.horizontalAlignment = SWT.CENTER;
		gd.verticalAlignment = SWT.CENTER;
		reloadButton.setLayoutData (gd);
			
		// ---------------------------------------------------
		// Transfer Button
		// ---------------------------------------------------
		transferButton = new Button(cStatus, SWT.BUTTON1);
		transferButton.setText("Überweisung");
		transferButton.setEnabled(true);
		gd = new GridData ();
		gd.horizontalAlignment = SWT.CENTER;
		gd.verticalAlignment = SWT.CENTER;
		transferButton.setLayoutData (gd);
		
		// ---------------------------------------------------
		// Logout Button
		// ---------------------------------------------------
		logoutButton = new Button(cStatus, SWT.BUTTON1);
		logoutButton.setText("Logout");
		logoutButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));	
		logoutButton.setEnabled(true);
		gd = new GridData ();
		gd.horizontalAlignment = SWT.CENTER;
		gd.verticalAlignment = SWT.CENTER;
		logoutButton.setLayoutData (gd);
	}
	
	public Composite getTopComposite() {
		return cMain;
	}
	
	// Display Username
	public void setUserName(String message) {
		labelUser.setText(message);
	}
	
	public void setStatusMessage(String message) {
		labelStatus.setText(message);
	}
	
	public void clearTable() {
		transferTable.removeAll();
	}
	
	public void clearStatusMessage() {
		labelStatus.setText("");
	}
	
	public void refresh() {
		cMain.layout();
	}
	
	public void setVisible( boolean what ) {
		cMain.setVisible( what );
		cMain.layout();
	}
	
	public void dispose() {
		cMain.dispose();
	}
}