package GUI;

import main.CoreWarGame;
import util.EcouteurModele;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Classe Welcome_Window, qui est la fenêtre pour entre les programmes RedCode
 */
public class Welcome_Window extends JDialog implements EcouteurModele {

    /**
     * Constante noText, qui est le texte qui s'affiche par défaut
     * dans les champs qui accueil les programmes RedCode
     */
    private static final String noText="Cliquez ici pour choisir un fichier";

    /**
     * Attribut model, objet de type CoreWarGame
     */
    private CoreWarGame model;

    /**
     * Attribut currentNameFile1, qui est le champ qui
     * accueil le premier programme
     */
    private JLabel currentNameFile1;

    /**
     * Attribut currentNameFile2, qui est le champ qui
     * accueil le second programme
     */
    private JLabel currentNameFile2;

    /**
     * Attribut beginGame, qui est le bouton qui lance
     * la fenêtre principale
     */
    private JButton beginGame;

    /**
     * Constructeur de la classe
     */
    public Welcome_Window(){
        super();

        this.model=new CoreWarGame(Welcome_Window.noText);

        this.build();
        this.addInteraction();

        this.setTitle("CoreWar");
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.setSize(new Dimension(500,300));

    }

    /**
     * Méthode build, qui construit les éléments dnas la fenêtre
     */
    public void build(){

        JLabel intro=new JLabel("Bienvenue dans le CoreWar! Veuillez séléctionner les fichiers des joueurs :");

        intro.setHorizontalAlignment(JLabel.CENTER);

        this.currentNameFile1=new JLabel(Welcome_Window.noText);
        this.currentNameFile2=new JLabel(Welcome_Window.noText);

        //this.currentNameFile1.setHorizontalAlignment();
       // this.currentNameFile2.setHorizontalAlignment();

        this.currentNameFile1.setBorder(new EmptyBorder(0,10,0,0));

        this.currentNameFile2.setBorder(new EmptyBorder(0,10,0,0));

        JPanel contentFile1=new JPanel(new BorderLayout());
        contentFile1.add(this.currentNameFile1);

        JPanel contentFile2=new JPanel(new BorderLayout());
        contentFile2.add(this.currentNameFile2);

        Border title=BorderFactory.createTitledBorder("Programme 1");

        contentFile1.setBorder(title);

        Border title2=BorderFactory.createTitledBorder("Programme 2");

        contentFile2.setBorder(title2);


        this.beginGame=new JButton("Commencer");

        JPanel SouthPan=new JPanel(new FlowLayout());

        SouthPan.add(this.beginGame);

        Container contentPane=this.getContentPane();

        contentPane.setLayout(new GridLayout(4,1));

        contentPane.add(intro,0);

        contentPane.add(contentFile1,1);

        contentPane.add(contentFile2,2);

        contentPane.add(SouthPan,3);


    }

    /**
     * Méthode addInteraction, qui ajoute une interaction avec les différents
     * éléments construit dans la méthode build
     */
    public void addInteraction(){

        this.model.ajoutEcouteur(this);

        this.currentNameFile1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser=new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));


                int returnVal=fileChooser.showOpenDialog(null);

                if(returnVal==JFileChooser.APPROVE_OPTION){

                    model.setNameFile1(fileChooser.getSelectedFile().toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                currentNameFile1.setCursor(new Cursor(Cursor.HAND_CURSOR));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                currentNameFile1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }
        });

        this.currentNameFile2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JFileChooser fileChooser=new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                int returnVal=fileChooser.showOpenDialog(null);

                if(returnVal==JFileChooser.APPROVE_OPTION) {

                    model.setNameFile2(fileChooser.getSelectedFile().toString());


                }
            }


            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                currentNameFile2.setCursor(new Cursor(Cursor.HAND_CURSOR));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                currentNameFile2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }
        });

        this.beginGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(model.getNameFile1().equals(Welcome_Window.noText) || model.getNameFile2().equals(Welcome_Window.noText)){

                    JOptionPane.showMessageDialog(null,"Erreur, il faut renseigner les deux fichiers.","Erreur",JOptionPane.ERROR_MESSAGE);

                }else{

                    Component component=(Component)e.getSource();
                    JDialog dialog=(JDialog)SwingUtilities.getRoot(component);

                    dialog.dispose();
                    new MemoryGUI(model);
                }
            }
        });
    }


    /**
     * Méthode modelMisAJour, qui change l'affichage du texte par défaut des champs
     * par le nom du fichier ajouté
     * @param src La source, si besoin
     */
    @Override
    public void modeleMisAJour(Object src){

        this.currentNameFile1.setText(this.model.getNameFile1());

        this.currentNameFile2.setText(this.model.getNameFile2());



    }


}
