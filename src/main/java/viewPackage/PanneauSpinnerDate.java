package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PanneauSpinnerDate extends JPanel
{
    private JSpinner spinnerDate;
    private Calendar calendar;

    public PanneauSpinnerDate() {
        this.setLayout(new BorderLayout());

        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yy");
        spinnerDate = new JSpinner(new SpinnerDateModel());
        spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate, model.toPattern()));
        calendar = Calendar.getInstance();
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
