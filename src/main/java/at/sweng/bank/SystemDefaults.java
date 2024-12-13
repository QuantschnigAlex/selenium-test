package at.sweng.bank;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class SystemDefaults {
	private static RGB colorBackground = new RGB(220, 230, 239);
	private static RGB colorForeground = new RGB(255, 255, 255);
	private static RGB colorError = new RGB(255, 0, 0);

	private static final int LAYOUT_BORDER_WIDTH = 1;
		
	// ---------------------------------------------------
	// Some Colors
	// ---------------------------------------------------
	public static Color getDefaultBackgroundColor() {
		return new Color(Display.getCurrent(), colorBackground);
	}
	
	public static Color getDefaultErrorColor() {
		return new Color(Display.getCurrent(), colorError);
	}
	
	public static Color getDefaultForegroundColor() {
		return new Color(Display.getCurrent(), colorForeground);
	}
	
	// ---------------------------------------------------
	// Layout Constants
	// ---------------------------------------------------
	public static int getDefaultBorderWidth() {
		return LAYOUT_BORDER_WIDTH;
	}
}