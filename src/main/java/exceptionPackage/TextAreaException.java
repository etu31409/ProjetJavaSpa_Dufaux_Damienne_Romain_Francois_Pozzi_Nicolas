package exceptionPackage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextAreaException extends Exception{

        String message;
        JTextArea object;

        public TextAreaException(JTextArea object, String message){
            this.object = object;
            this.message = message;
            Border border = BorderFactory.createLineBorder(Color.red);
            object.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }

        public String getMessage()
        {
            return message;
        }


}
