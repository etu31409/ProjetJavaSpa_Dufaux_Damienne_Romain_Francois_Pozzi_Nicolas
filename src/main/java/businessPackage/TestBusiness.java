/*package businessPackage;

import dataAcessPackage.IMedicament;
import modelPackage.StatMedicament;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestBusiness {

    private Business business;
    private IMedicament daoMedicament;
    private ArrayList<StatMedicament> listeResultatRechercheOrdonnances;
    private HashMap<String, Double> statistiques = new HashMap<>();
    private static Integer compteurGlobal = 0;

    @Before
    public void setUp() throws Exception {
        Business business = new Business();
        GregorianCalendar dateDebut = new GregorianCalendar(1950,1,1);
        GregorianCalendar dateFin = new GregorianCalendar(2018,5,11);
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
    }*/

    /*@Test
    public void calculPourcentageParMedicamentTestUn(int base, int compteurGlobal, String isobetadaine) throws Exception{
        Assert.assertEquals(0.083, business.calculPourcentageParMedicament(1. ,12, "Isobetadaine"), 0.001);
    }

    @Test
    public void calculPourcentageParMedicamentTestDeux(int base, int compteurGlobal, String isobetadaine) throws Exception{
        Assert.assertEquals(0, business.calculPourcentageParMedicament(1. ,0, "Isobetadaine"), 0.001);
    }

    @Test
    public void calculPourcentageParMedicamentTestTrois(int base, int compteurGlobal, String isobetadaine) throws Exception{
        Assert.assertEquals(0, business.calculPourcentageParMedicament(1. ,12, "trucmuche"), 0.001);
    }

}*/
