package viewPackage;

import controllerPackage.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class  FenetrePrincipale extends JFrame{
    private Controller controller;
    private Container frameContainer;
    private JMenuBar barMenu;
    private JMenu acceuil, recherches, soins, listing, supprimer;
    private JMenuItem nouvelAccueil, nouvelleFicheDeSoins, quitter, supprimerFicheDeSoin,
            supprimerAnimale, listingFicheDeSoin, listingAnimaux, rechercheVeterinaires, rechercheProprietaires, rechercheAnimaux;
    private JPanel panneauBienvenue;
    private FenetreFicheDeSoins fenetreFicheDeSoins;

    public  FenetrePrincipale(){
        super("S.P.A, Société Protectrice des Animaux");
        setBounds(100, 0, 1200, 750);
        this.addWindowListener( new WindowAdapter() { public void windowClosing(WindowEvent e) { System.exit(0); } } );
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        panneauBienvenue = new PanneauBienvenue().getPanneauContainerPrincipal();
        frameContainer.add(panneauBienvenue, BorderLayout.CENTER);
        barMenu = new JMenuBar();
        setJMenuBar(barMenu);

        acceuil = new JMenu("Accueil");
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

        rechercheVeterinaires = new JMenuItem("Recherche Vétérinaires");
        rechercheVeterinaires.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheVeterinaires);

        rechercheAnimaux = new JMenuItem("Recherche Animaux");
        rechercheAnimaux.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheAnimaux);

        rechercheProprietaires = new JMenuItem("Recherche Propriétaires");
        rechercheProprietaires.addActionListener(new EcouteurBarMenu());
        recherches.add(rechercheProprietaires);

        listing = new JMenu("Listings");
        listing.setMnemonic('L');
        barMenu.add(listing);

        listingFicheDeSoin = new JMenuItem("Listing fiches de soins");
        listing.add(listingFicheDeSoin);
        listingFicheDeSoin.addActionListener(new EcouteurBarMenu());

        listingAnimaux = new JMenuItem("Listing tous les animaux");
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

    public void accueil()
    {
        frameContainer.removeAll();
        frameContainer.setLayout(new FlowLayout());
        panneauBienvenue = new PanneauBienvenue().getPanneauContainerPrincipal();
        panneauBienvenue.setVisible(true);
        frameContainer.add(panneauBienvenue);
        frameContainer.repaint();
        FenetrePrincipale.this.setVisible(true);
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
                frameContainer.add(new PanneauAccueil(controller, FenetrePrincipale.this).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == nouvelleFicheDeSoins){
                frameContainer.removeAll();
                frameContainer.add(new PanneauFicheDeSoin(controller).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == listingFicheDeSoin){
                frameContainer.removeAll();
                PanneauChargement test = new PanneauChargement(new PanneauListingFicheDeSoins());
                frameContainer.add(test);
                //frameContainer.add(new PanneauListingFicheDeSoins());
                frameContainer.repaint();
                frameContainer.validate();
                test.getG().run();
            }
            else if(event.getSource() == listingAnimaux){
                frameContainer.removeAll();
                frameContainer.add(new PanneauListingAnimaux(controller).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == rechercheVeterinaires) {
                frameContainer.removeAll();
                frameContainer.add(new PanneauRechercheVeterinaires(controller, FenetrePrincipale.this).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == rechercheProprietaires) {
                frameContainer.removeAll();
                frameContainer.add(new PanneauRechercheProprietaires(controller).getPanneauContainerPrincipal());
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
