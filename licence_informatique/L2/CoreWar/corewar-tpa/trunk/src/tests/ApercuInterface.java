package tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Aperçu de l'interface que nous souhaitons développer
 */
public class ApercuInterface {

    public static void main(String[] args) {

        JFrame master=new JFrame("CoreWar");


        //Partie Panels = memory
        ArrayList<JPanel> testPanels=new ArrayList<JPanel>();

        JPanel memoryView=new JPanel();

        memoryView.setPreferredSize(new Dimension(800,((1024*10)/80)+40));

        memoryView.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));

        memoryView.setBackground(Color.DARK_GRAY);

        for (int i = 0; i <1024 ; i++) {

            JPanel rectangle=new JPanel();
            rectangle.setBackground(Color.WHITE);
            rectangle.setPreferredSize(new Dimension(10,10));
            memoryView.add(rectangle);
        }


        /*Partie boutons*/
        JPanel buttons=new JPanel();

        buttons.add(new JButton("play"));

        buttons.add(new JButton("stop"));

        JPanel container=new JPanel();

        container.setBackground(Color.CYAN);



        /*
        Partie menu + FileChooser
         */

        JMenuBar menuBar=new JMenuBar();

        JMenu menu=new JMenu("Fichier");
        menuBar.add(menu);

        JMenuItem open=new JMenuItem("Ouvrir fichier");

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser testFileChooser=new JFileChooser();

                int returnVal=testFileChooser.showOpenDialog(null);

                if(returnVal==JFileChooser.APPROVE_OPTION){

                    File file=testFileChooser.getSelectedFile();

                    System.out.println(file);

                }else {

                    System.out.println("Pas de fichier sélectionné");

                }
            }
        });

        menu.add(open);




        container.setLayout(new BorderLayout());

        container.add(memoryView,BorderLayout.NORTH);

        container.add(buttons);

        master.setContentPane(container);

        master.setJMenuBar(menuBar);



        master.pack();
        master.setLocationRelativeTo(null);
        master.setVisible(true);

        master.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        master.setSize(new Dimension(800,280));

    }

}
