package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauSpinnerDate extends JPanel
{
    private JSpinner spinnerDate;
    private SpinnerDateModel spinnerDateModel;
    private Calendar calendar;

    public PanneauSpinnerDate() {
        this.setLayout(new BorderLayout());
        //Version avec un modelDate
        calendar = Calendar.getInstance();
        spinnerDateModel = new SpinnerDateModel();
        spinnerDateModel.setValue(calendar.getTime());
        spinnerDate = new JSpinner(spinnerDateModel);
        this.add(spinnerDate, BorderLayout.WEST);
    }

    public void reinitialiserChamps ()
    {
        calendar = Calendar.getInstance();
        spinnerDate.setValue(calendar.getTime());
    }

    public GregorianCalendar getDate ()
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime((Date) spinnerDate.getValue());
        return (GregorianCalendar) cal;
    }
}
