package at.sweng.bank.transfer;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

public class RAPDialog extends Dialog{
    public RAPDialog(Shell parentShell) {
        super(parentShell);
        setBlockOnOpen(false);
    }
    
    @Override
    public int open() {
        if (getShell() == null || getShell().isDisposed()) {
            create();
        }
        getShell().open();
        return OK;
    }
}
