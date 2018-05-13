package businessPackage;

import dataAcessPackage.DBDAOMedicament;
import dataAcessPackage.IMedicament;
import modelPackage.StatMedicament;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.Assert.*;

public class BusinessTest {

    private Business business;
    private IMedicament daoMedicament;
    private ArrayList<StatMedicament> listeResultatRechercheOrdonnances;
    private HashMap<String, Double> statistiques = new HashMap<>();
    private static Integer compteurGlobal = 0;

    @Before
    public void setUp() throws Exception {
        daoMedicament = new DBDAOMedicament();
        business = new Business();
        GregorianCalendar dateDebut = new GregorianCalendar(1950,01,01);
        GregorianCalendar dateFin = new GregorianCalendar();
        dateFin.setTime(GregorianCalendar.getInstance().getTime());

        listeResultatRechercheOrdonnances =
                daoMedicament.getMedicamentsEntreDeuxDates(dateDebut, dateFin);

        for (StatMedicament sm : listeResultatRechercheOrdonnances) {
            compteurGlobal++;
            if (!statistiques.containsKey(sm.getNomMedic())) {
                statistiques.put(sm.getNomMedic(), 1.);
            }
            else {
                statistiques.replace(sm.getNomMedic(), ((statistiques.get(sm.getNomMedic()))+1));
            }
        }
    }

    @Test
    public void calculPourcentageParMedicamentTestUn() throws Exception{
        Assert.assertEquals(0.083, business.calculPourcentageParMedicament(1. ,12), 0.001);
    }

    @Test (expected = IllegalArgumentException.class)
    public void calculPourcentageParMedicamentTestDeux() throws Exception{
       business.calculPourcentageParMedicament(1. ,0);
    }
}