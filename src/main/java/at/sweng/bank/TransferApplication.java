package at.sweng.bank;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.Application.OperationMode;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.application.ExceptionHandler;
import org.eclipse.rap.rwt.client.WebClient;

public class TransferApplication implements ApplicationConfiguration {

	@Override
	public void configure(Application application) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(WebClient.PAGE_TITLE, "Simple Bank Transfer");
		//application.addStyleSheet(RWT.DEFAULT_THEME_ID, "themes/theme.css");
    	
		application.setOperationMode( OperationMode.SWT_COMPATIBILITY );
		application.addEntryPoint("/transfer", TransferEntryPoint.class, properties);

		application.setExceptionHandler( new ExceptionHandler() {
			@Override
			public void handleException( Throwable exception ) {				
				System.out.println("**********************************************");
				System.out.println("* Exception occurred: ");
				System.out.println("**********************************************");
				exception.printStackTrace();
			}
		});
	}
}