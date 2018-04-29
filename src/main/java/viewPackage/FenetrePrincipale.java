package viewPackage;

import controllerPackage.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame{
    private Controller controller;
    private Container frameContainer;
    private JMenuBar barMenu;
    private JMenu acceuil, recherches, medicaments, listing, supprimer;
    private JMenuItem nouvelAccueil, nouvellePrescription, rechercheFiches, quitter, supprimerFicheDeSoin,
            supprimerAnimale, listingFicheDeSoin, listingAnimaux, rechercheVeterinaires, rechercheProprietaires;
    private PanneauBienvenue panneauBienvenue;

    public FenetrePrincipale(){
        super("Fenetre principale");
        setBounds(300, 0, 1200, 825);
        this.addWindowListener( new WindowAdapter() { public void windowClosing(WindowEvent e) { System.exit(0); } } );
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        panneauBienvenue = new PanneauBienvenue();
        frameContainer.add(panneauBienvenue, BorderLayout.CENTER);
        barMenu = new JMenuBar();
        setJMenuBar(barMenu);

        acceuil = new JMenu("Acceuil");
        acceuil.setMnemonic('A');
        barMenu.add(acceuil);

        nouvelAccueil = new JMenuItem("Nouvel accueil");
        acceuil.add(nouvelAccueil);
        nouvelAccueil.addActionListener(new EcouteurBarMenu());
        quitter = new JMenuItem("Quitter");
        quitter.addActionListener(new ExitListener());
        quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        acceuil.add(quitter);

        recherches = new JMenu("Recherches");
        recherches.setMnemonic('R');
        barMenu.add(recherches);

        rechercheFiches = new JMenuItem("Fiches de soins");
        rechercheFiches.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheFiches);

        rechercheVeterinaires = new JMenuItem("Recherche Veterinaire");
        rechercheVeterinaires.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheVeterinaires);

        rechercheProprietaires = new JMenuItem("Recherche Propriétaire");
        rechercheProprietaires.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheProprietaires);

        medicaments = new JMenu("Medicaments");
        medicaments.setMnemonic('M');
        barMenu.add(medicaments);

        nouvellePrescription = new JMenuItem("Nouvelle prescription");
        medicaments.add(nouvellePrescription);
        nouvellePrescription.addActionListener(new EcouteurBarMenu());

        listing = new JMenu("Listings");
        listing.setMnemonic('L');
        barMenu.add(listing);

        supprimer = new JMenu("Supprimer");
        supprimer.setMnemonic('S');
        barMenu.add(supprimer);

        supprimerFicheDeSoin = new JMenuItem("Supprimer une fiche de soin");
        supprimer.add(supprimerFicheDeSoin);
        supprimerFicheDeSoin.addActionListener(new EcouteurBarMenu());

        supprimerAnimale = new JMenuItem("Supprimer un animal");
        supprimer.add(supprimerAnimale);
        supprimerAnimale.addActionListener(new EcouteurBarMenu());

        listingFicheDeSoin = new JMenuItem("Lister fiches de soins");
        listing.add(listingFicheDeSoin);
        listingFicheDeSoin.addActionListener(new EcouteurBarMenu());

        listingAnimaux = new JMenuItem("Lister tous les animaux");
        listing.add(listingAnimaux);
        listingAnimaux.addActionListener(new EcouteurBarMenu());

        controller = new Controller();
        setVisible(true);
    }

    private class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            System.exit(0);
        }
    }

    private class EcouteurBarMenu implements ActionListener
    {
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == nouvelAccueil){
                frameContainer.removeAll();
                //pour ajouter une barre de défilement (marche pas)
                frameContainer.add(new JScrollPane(new PanneauAccueil()), BorderLayout.CENTER);
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == rechercheFiches){
                frameContainer.removeAll();
                frameContainer.add(new PanneauRechercheListeSoins());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == nouvellePrescription){
                FenetrePrescription fenetrePrescription = new FenetrePrescription();
            }
            else if(event.getSource() == listingFicheDeSoin){
                frameContainer.removeAll();
                frameContainer.add(new PanneauListingFicheDeSoins());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == listingAnimaux){
                frameContainer.removeAll();
                frameContainer.add(new PanneauListingAnimal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == rechercheVeterinaires) {
                frameContainer.removeAll();
                //frameContainer.add(new PanneauRechercheVeterinaires(controller));
                frameContainer.repaint();
                frameContainer.validate();

            }
            else if(event.getSource() == rechercheProprietaires) {
                frameContainer.removeAll();
                frameContainer.add(new PanneauRechercheProprietaires(controller));
                frameContainer.repaint();
                frameContainer.repaint();
                frameContainer.validate();

            }
        }
    }
}
