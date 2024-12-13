package at.sweng.bank.transfer;

import org.apache.commons.validator.routines.IBANValidator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import at.sweng.bank.SystemDefaults;
import at.sweng.bank.login.User;

public class TransferDialog extends RAPDialog{
	private static final long serialVersionUID = 2356606690071099765L;
	private Text tSenderName;
	private Text tSenderAccount;
	private Text tReceiverName;
	private Text tReceiverAccount;
	private Text tAmount;
	private CLabel lStatus;
	private Transfer transfer;
	private User user;
	
	public TransferDialog(Shell parentShell, User user) {
		super(parentShell);
		this.user = user;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		super.setShellStyle(SWT.TITLE);
		parent.setBackground(SystemDefaults.getDefaultBackgroundColor());
		
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout());
		container.setBackground(SystemDefaults.getDefaultBackgroundColor());
		
		CLabel label1 = new CLabel(container, SWT.NONE);
		label1.setText("Sendername");
		label1.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		label1.setBackground(SystemDefaults.getDefaultBackgroundColor());	
		GridData gd = new GridData ();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.LEFT;
		gd.verticalAlignment = SWT.CENTER;
		label1.setLayoutData (gd);
		
		tSenderName = new Text(container, SWT.BORDER);
		tSenderName.setText(user.getFullname());
		tSenderName.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		tSenderName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		tSenderName.setEditable(false);
		
		CLabel label2 = new CLabel(container, SWT.NONE);
		label2.setText("Sender-Konto");
		label2.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		label2.setBackground(SystemDefaults.getDefaultBackgroundColor());	
		gd = new GridData ();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.LEFT;
		gd.verticalAlignment = SWT.CENTER;
		label2.setLayoutData (gd);
		
		tSenderAccount = new Text(container, SWT.BORDER);
		tSenderAccount.setText(user.getAccount());
		tSenderAccount.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		tSenderAccount.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		tSenderAccount.setEditable(false);
		
		CLabel label3 = new CLabel(container, SWT.NONE);
		label3.setText("Empfängername");
		label3.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		label3.setBackground(SystemDefaults.getDefaultBackgroundColor());	
		gd = new GridData ();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.LEFT;
		gd.verticalAlignment = SWT.CENTER;
		label3.setLayoutData (gd);
		
		tReceiverName = new Text(container, SWT.BORDER);
		tReceiverName.setText("");
		tReceiverName.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		tReceiverName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		CLabel label4 = new CLabel(container, SWT.NONE);
		label4.setText("Empfänger-Konto");
		label4.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		label4.setBackground(SystemDefaults.getDefaultBackgroundColor());	
		gd = new GridData ();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.LEFT;
		gd.verticalAlignment = SWT.CENTER;
		label4.setLayoutData (gd);
		
		tReceiverAccount = new Text(container, SWT.BORDER);
		tReceiverAccount.setText("");
		tReceiverAccount.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		tReceiverAccount.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		CLabel label5 = new CLabel(container, SWT.NONE);
		label5.setText("Betrag");
		label5.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		label5.setBackground(SystemDefaults.getDefaultBackgroundColor());	
		gd = new GridData ();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.LEFT;
		gd.verticalAlignment = SWT.CENTER;
		label5.setLayoutData (gd);

		tAmount = new Text(container, SWT.BORDER);
		tAmount.setText("");
		tAmount.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		tAmount.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
				
		lStatus = new CLabel(container, SWT.NONE);
		lStatus.setText("");
		lStatus.setFont( JFaceResources.getFontRegistry().get("FONT_STATUSBAR"));
		lStatus.setBackground(SystemDefaults.getDefaultBackgroundColor());	
		lStatus.setForeground(SystemDefaults.getDefaultErrorColor());	
		gd = new GridData ();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.LEFT;
		gd.verticalAlignment = SWT.CENTER;
		gd.widthHint = 300;
		lStatus.setLayoutData (gd);
		
		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SystemDefaults.getDefaultBackgroundColor());
		createButton(parent, IDialogConstants.OK_ID, "Ok", false);
		createButton(parent, IDialogConstants.CANCEL_ID, "Abbrechen", false);
	}

	private void setStatus(String message) {
		lStatus.setText(message);
	}
	
	private boolean checkAmount() {
		double value;
		
		try {
			value = Double.parseDouble(tAmount.getText());
		}
		catch (NumberFormatException | NullPointerException e) {
			setStatus("Ungültiger Betrag!");
			return false;
		}

		return value > 0;
	}
	
	private boolean checkAccount(String account) {
		IBANValidator validator = new IBANValidator();
		return validator.isValid(account);
	}

	@Override
	protected void okPressed() {
		if (!checkAmount()) return;
		if (!checkAccount(tReceiverAccount.getText())) {
			setStatus("Ungültige Empfänger-IBAN!");
			return;
		}
		transfer = new Transfer(tSenderName.getText(), tSenderAccount.getText(), tReceiverName.getText(),
				tReceiverAccount.getText(), Double.parseDouble(tAmount.getText()));
		super.okPressed();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Neue Überweisung");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(500, 500);
	}
	
	public Transfer getTransfer() {
		return transfer;
	}
}