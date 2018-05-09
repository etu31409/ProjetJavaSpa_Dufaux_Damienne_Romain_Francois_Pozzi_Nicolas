package exceptionPackage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextAreaException extends Exception{

        private String message;
        private JTextArea object;
        private static final Border border = BorderFactory.createLineBorder(Color.red);

        public TextAreaException(JTextArea object, String message){
            this.object = object;
            this.message = message;
            object.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }

        public String getMessage()
        {
            return message;
        }


}
