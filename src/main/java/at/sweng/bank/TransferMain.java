package at.sweng.bank;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.service.UISessionEvent;
import org.eclipse.rap.rwt.service.UISessionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

import at.sweng.bank.login.LoginController;
import at.sweng.bank.main.MainController;
import at.sweng.bank.resources.Resources;

public class TransferMain implements ApplicationEventListener {
	private Composite parent;
	private Composite cMain;
	private LoginController lc = null;
	private StackLayout mainLayout;
	private Resources res;
	
	public TransferMain( Composite p ) {
		parent = p;
	}

	public void start() {  

		RWT.getUISession().addUISessionListener( new UISessionListener() {
			@Override
			public void beforeDestroy( UISessionEvent event ) {
				System.out.println("**********************************************");
				System.out.println("* BANK TRANSFER DISPOSING BROWSER ...");
				System.out.println("**********************************************");
			}
		});
		
		// Load some images and fonts
		res = new Resources();
		
		parent.setLayout( new FormLayout());
		cMain = new Composite(parent, SWT.NONE);
		FormData f = new FormData();
		f.top = new FormAttachment(0, 0);
		f.left = new FormAttachment(0, 0);
		f.right = new FormAttachment(100, 0);
		f.bottom = new FormAttachment(100, 0);
		cMain.setLayoutData(f);
		mainLayout = new StackLayout();
		cMain.setLayout(mainLayout);
		
		lc = new LoginController(cMain);
		lc.setLoginEventListener(this);
		mainLayout.topControl = lc.getTopControl();		
	}
	
	@Override
	public void handle(ApplicationEvent e, int id) {

		if (e == ApplicationEvent.LOGIN_OK) {
			lc.setInvisible();

			MainController sc = new MainController(cMain);
			sc.setEventListener(this);
			sc.setUser(lc.getLogin());

			mainLayout.topControl = sc.getTopControl();
			cMain.layout();

		} else if (e == ApplicationEvent.LOGOUT) {
			
			lc.clearFields();
			lc.setVisible();
			mainLayout.topControl = lc.getTopControl();
			cMain.layout();
		}
	}
}