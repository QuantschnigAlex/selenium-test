package at.sweng.bank;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TransferEntryPoint extends AbstractEntryPoint {
	private Shell shell;

	@Override
	protected Shell createShell( Display display ) {
		shell = super.createShell( display );
		shell.setData( RWT.CUSTOM_VARIANT, "mainshell" );
		return shell;
	}
	
	@Override
	protected void createContents(Composite p) {  
		TransferMain aMain = new TransferMain(p );
		aMain.start();
	}	
}