package viewPackage;

import controllerPackage.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class  FenetrePrincipale extends JFrame{
    private Controller controller;
    private Container frameContainer;
    private JMenuBar barMenu;
    private JMenu  spa, recherches, ajout, listing, statistique;
    private JMenuItem accueil, quitter, nouvelAnimal, nouvelleFicheDeSoin, listingFicheDeSoin, listingAnimaux, rechercheVeterinaires,
            rechercheProprietaires, rechercheAnimaux, statMedicaments;
    private JPanel panneauBienvenue;

    public  FenetrePrincipale(){
        super("S.P.A, Société Protectrice des Animaux");
        setBounds(100, 0, 1200, 750);
        this.addWindowListener( new WindowAdapter() { public void windowClosing(WindowEvent e) { System.exit(0); } } );
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

        PanneauChargement panneauChargement = new PanneauChargement(new PanneauBienvenue().getPanneauContainerPrincipal(), FenetrePrincipale.this);
        frameContainer.add(panneauChargement);
        panneauChargement.getG().start();
        barMenu = new JMenuBar();
        setJMenuBar(barMenu);

        spa = new JMenu("S.P.A.");
        barMenu.add(spa);
        spa.addActionListener(new EcouteurBarMenu());

        accueil = new JMenuItem("Accueil");
        accueil.addActionListener(new EcouteurBarMenu());
        spa.add(accueil);

        quitter = new JMenuItem("Quitter");
        quitter.addActionListener(new ExitListener());
        quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        spa.add(quitter);

        ajout = new JMenu("Ajouts");
        ajout.setMnemonic('A');
        barMenu.add(ajout);

        nouvelAnimal = new JMenuItem("Nouvel animal");
        ajout.add(nouvelAnimal);
        nouvelAnimal.addActionListener(new EcouteurBarMenu());

        nouvelleFicheDeSoin = new JMenuItem("Nouvelle fiche de soin");
        ajout.add(nouvelleFicheDeSoin);
        nouvelleFicheDeSoin.addActionListener(new EcouteurBarMenu());

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

        listingAnimaux = new JMenuItem("Listing tous les animaux");
        listing.add(listingAnimaux);
        listingAnimaux.addActionListener(new EcouteurBarMenu());

        listingFicheDeSoin = new JMenuItem("Listing fiches de soin");
        listing.add(listingFicheDeSoin);
        listingFicheDeSoin.addActionListener(new EcouteurBarMenu());

        statistique = new JMenu("Statistiques");
        statistique.setMnemonic('S');
        barMenu.add(statistique);

        statMedicaments = new JMenuItem("Médicaments");
        statistique.add(statMedicaments);
        statMedicaments.addActionListener(new EcouteurBarMenu());

        controller = new Controller();
        setVisible(true);
        barMenu.setVisible(false);
    }

    public void retourAccueil(){
        frameContainer.removeAll();
        frameContainer.setLayout(new BorderLayout());
        panneauBienvenue = new PanneauBienvenue().getPanneauContainerPrincipal();
        panneauBienvenue.setVisible(true);
        frameContainer.add(panneauBienvenue, BorderLayout.CENTER);
        frameContainer.repaint();
        FenetrePrincipale.this.setVisible(true);
    }

    public void afficherAccueil(){
        frameContainer.removeAll();
        frameContainer.add(new PanneauBienvenue().getPanneauContainerPrincipal());
        frameContainer.setVisible(true);
        frameContainer.repaint();
        frameContainer.validate();
    }

    private class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            System.exit(0);
        }
    }

    private class EcouteurBarMenu implements ActionListener
    {
        public void actionPerformed(ActionEvent event){
            if (event.getSource() == accueil){
                afficherAccueil();
            }
            else if(event.getSource() == nouvelAnimal){
                frameContainer.removeAll();
                frameContainer.add(new PanneauAnimal(controller, FenetrePrincipale.this).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == nouvelleFicheDeSoin){
                frameContainer.removeAll();
                frameContainer.add(new PanneauFicheDeSoin(controller, FenetrePrincipale.this).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == listingFicheDeSoin){
                frameContainer.removeAll();
                frameContainer.add(new PanneauListingFichesDeSoin(controller).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == listingAnimaux){
                frameContainer.removeAll();
                frameContainer.add(new PanneauListingAnimaux(controller, FenetrePrincipale.this).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == rechercheVeterinaires) {
                frameContainer.removeAll();
                frameContainer.add(new PanneauRechercheVeterinaires(controller).getPanneauContainerPrincipal());
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
                frameContainer.repaint();
                frameContainer.validate();
            }
            else if(event.getSource() == statMedicaments) {
                frameContainer.removeAll();
                frameContainer.add(new PanneauStatMedicaments(controller).getPanneauContainerPrincipal());
                frameContainer.repaint();
                frameContainer.validate();
            }
        }
    }

    public JMenuBar getBarMenu() {
        return barMenu;
    }
}
