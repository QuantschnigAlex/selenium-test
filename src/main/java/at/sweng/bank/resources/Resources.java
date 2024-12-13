package at.sweng.bank.resources;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;

public class Resources {
	
	public Resources() {
		createImages();
		createFonts();
	}

	private void createImages() {
		ImageRegistry iRegistry = JFaceResources.getImageRegistry();
		
		iRegistry.put("list", ImageDescriptor.createFromFile(Resources.class, "list.png"));
		iRegistry.put("invoice", ImageDescriptor.createFromFile(Resources.class, "invoice.png"));
		iRegistry.put("logout", ImageDescriptor.createFromFile(Resources.class, "logout.png"));
		iRegistry.put("logo", ImageDescriptor.createFromFile(Resources.class, "logo_bank.png"));
		iRegistry.put("logo-s", ImageDescriptor.createFromFile(Resources.class, "logo_bank_s.png"));
	}

	// -------------------------------------------------------------------------------------------
	// - Fonts
	// -------------------------------------------------------------------------------------------
	private void createFonts() {
		FontData[] tFont;

		FontRegistry fRegistry = JFaceResources.getFontRegistry();
		tFont = fRegistry.get(JFaceResources.DEFAULT_FONT).getFontData();

		tFont = fRegistry.get(JFaceResources.TEXT_FONT).getFontData();
		tFont[0].setHeight(14);
		fRegistry.put("FONT_NORMAL", tFont);
		
		tFont = fRegistry.get(JFaceResources.TEXT_FONT).getFontData();
		tFont[0].setHeight(14);
		tFont[0].setStyle(SWT.BOLD);
		fRegistry.put("FONT_BOLD", tFont);
		
		tFont = fRegistry.get(JFaceResources.TEXT_FONT).getFontData();
		tFont[0].setHeight(16);
		fRegistry.put("FONT_STATUS", tFont);
	}
}