package viewPackage;

import controllerPackage.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame{
    private Controller controller;
    private Container frameContainer;
    private JMenuBar barMenu;
    private JMenu acceuil, recherches, soins, listing, supprimer;
    private JMenuItem nouvelAccueil, nouvelleFicheDeSoins, quitter, supprimerFicheDeSoin,
            supprimerAnimale, listingFicheDeSoin, listingAnimaux, rechercheVeterinaires, rechercheProprietaires, rechercheAnimaux;
    private PanneauBienvenue panneauBienvenue;
    private
    FenetreFicheDeSoins fenetreFicheDeSoins;

    public FenetrePrincipale(){
        super("S.P.A, Société Protectrice des Animaux");
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

        soins = new JMenu("Soins");
        soins.setMnemonic('S');
        barMenu.add(soins);

        nouvelleFicheDeSoins = new JMenuItem("Nouvelle fiche de soins");
        soins.add(nouvelleFicheDeSoins);
        nouvelleFicheDeSoins.addActionListener(new EcouteurBarMenu());

        recherches = new JMenu("Recherches");
        recherches.setMnemonic('R');
        barMenu.add(recherches);

        rechercheVeterinaires = new JMenuItem("Recherche Veterinaire");
        rechercheVeterinaires.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheVeterinaires);

        rechercheAnimaux = new JMenuItem("Recherche Animaux");
        rechercheAnimaux.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheAnimaux);

        rechercheProprietaires = new JMenuItem("Recherche Propriétaire");
        rechercheProprietaires.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheProprietaires);

        listing = new JMenu("Listings");
        listing.setMnemonic('L');
        barMenu.add(listing);

        listingFicheDeSoin = new JMenuItem("Lister fiches de soins");
        listing.add(listingFicheDeSoin);
        listingFicheDeSoin.addActionListener(new EcouteurBarMenu());

        listingAnimaux = new JMenuItem("Lister tous les animaux");
        listing.add(listingAnimaux);
        listingAnimaux.addActionListener(new EcouteurBarMenu());

        supprimer = new JMenu("Suppressions");
        supprimer.setMnemonic('S');
        barMenu.add(supprimer);

        supprimerFicheDeSoin = new JMenuItem("Supprimer une fiche de soin");
        supprimer.add(supprimerFicheDeSoin);
        supprimerFicheDeSoin.addActionListener(new EcouteurBarMenu());

        supprimerAnimale = new JMenuItem("Supprimer un animal");
        supprimer.add(supprimerAnimale);
        supprimerAnimale.addActionListener(new EcouteurBarMenu());

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
                frameContainer.add(new PanneauAccueil(controller));
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == nouvelleFicheDeSoins){
                frameContainer.removeAll();
                frameContainer.add(new PanneauFicheDeSoins(fenetreFicheDeSoins, controller));
                frameContainer.repaint();
                frameContainer.validate();
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
                frameContainer.add(new PanneauRechercheVeterinaires(controller));
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == rechercheProprietaires) {
                frameContainer.removeAll();
                frameContainer.add(new PanneauRechercheProprietaires(controller));
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == rechercheAnimaux) {
                frameContainer.removeAll();
                frameContainer.add(new PanneauRechercheAnimaux(controller).getPanneauContainerPrincipal());
                frameContainer.setVisible(true);
                frameContainer.repaint();
                frameContainer.validate();
            }
        }
    }
}
