package ATM;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class JTextFieldLimit extends PlainDocument {
    private int limit;

    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        StringBuffer newstr = new StringBuffer(str);
        if (str == null) return;

        else if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, newstr.toString(), attr);
        }
        else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}