package viewPackage;

import java.util.ArrayList;
import java.util.Arrays;

public class ValiditeNouvelAcceuil {
    private ArrayList<String> couleursPelage;
    private ArrayList<String> partiesDuCorps;

    public ValiditeNouvelAcceuil() {
        this.couleursPelage = new ArrayList<String>(Arrays.asList("Acajou", "Amande","Ambre","Améthyste","Anthracite",
                "Aquilain","Ardoise","Argent", "Aubergine","Aurore","Avocat","Azur","Beige", "Blanc","Blanc cassé",
                "Blé", "Bleu","Bleu acier","Bleu canard","Bleu ciel", "Bleu marine", "Bleu nuit","Bleu paon","Bleu pétrole",
                "Bordeaux","Brique", "Bronze","Brun", "Caca d'oie", "Cacao","Chocolat","Corail", "Crème","Cuivre","Fauve",
                "Gris", "Gris souris", "Indigo","Ivoire","Jaune","Jaune canari","Jaune citron","Kaki","Lavande","Lin",
                "Magenta","Mandarine","Marron","Mauve","Menthe","Moutarde","Noir","Noisette","Olive","Or","Orange","Pistache",
                "Prune","Rose","Rouge","Rouille","Roux", "Rubis", "Sable", "Saphir", "Saumon","Soufre","Taupe","Turquoise",
                "Vanille","Vermeil","Vert","Violet" ));
        this.partiesDuCorps = new ArrayList<String>(Arrays.asList("Abdomen","Aisselles","Aorte abdominale","Aorte thoracique",
                "Appendice","Aréole","Artère fémorale","Artère vertébrale","Avant-bras","Axis","Barbe","Bassin","Biceps brachial",
                "Bioceps fémoral","Bouche","Bras","Bulbe rachidien","Cage thoracique","Canine","Cerveau","Cervelet","Cheveux",
                "Cheville","Chiasma optique","Cils","Clavicule","Coccyx","Coeur","Col de l'utérus","Col du fémur","Colonne vertébrale",
                "Cordes vocales","Corps calleux","Cou","Coude","Crête iliaque","Cubitus","Cuisse","Dent","Diaphragme",
                "Disque intervertébral","Doigt","Dos","Duodénum","Encéphale","Epaule","Epididyme","Epiphyse","Estomac",
                "Épiglotte","Fémur","Fesses","Flore intestinale", "Foie","Follicule ovarien","Fornix","Front","Fundus",
                "Genou","Glande de Cowper","Gros intestin","Hanche","Humérus","Hymen","Hypophyse","Hypothalamus","Incisive",
                "Intestin grêle","Iris","Ischion","Jambe","Langue","Larynx","Lobe de l'oreille","Lobe frontal","Lobe occipital",
                "Lobe pariétal","Lobe temporal","Main","Mandibule","Membre inférieur","Menton","Moelle épinière","Molaires",
                "Mollet","Nez","Nombril","Nuque","Oeil","Oesophage","Omoplate","Ongle","Oreille","Orteils","Os de la main",
                "Ovaire","Patte","Pancréas","Paume de la main","Peau","Pénis","Péroné","Petites lèvres","Pharynx",
                "Pied","Poignet","Poils pubiens","Pouce", "Prémolaires","Pubis","Pupille","Radius","Rate","Rectum","Rein",
                "Rotule","Sacrum","Sartorius","Scrotum","Sein","Talon","Tempe","Tendon d'Achille","Testicule","Thorax",
                "Tibia","Trachée","Trompes de Fallope","Tronc","Uretère","Utérus","Vagin","Vessie","Vomer","Museau",
                "Aile","Queue","Bec"));
    }
    public ArrayList<String>getCouleursPelage(){
        return this.couleursPelage;
    }
}
