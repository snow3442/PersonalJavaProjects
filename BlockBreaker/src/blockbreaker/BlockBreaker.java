package blockbreaker;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BlockBreaker {

    public static final String[] levels = {"1", "2"};
    public static boolean lv1Selected = false;
    public static boolean lv2Selected = false;

    //put logic here and it will be reflected in the painting
    public static void main(String[] args) {
        //Pop up Jpanel for game setting when clicking on Start menu item
        JFrame mainFrame = new JFrame("Block Breaker");
        //java executables will still be running even if we close the window
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(
                true);
        mainFrame.setSize(
                610, 600);
        mainFrame.setResizable(
                false);
        JComboBox<String> levelcombo = new JComboBox<>(levels);
        JTextField nameField = new JTextField(" ");
        JPanel startpanel = new JPanel(new GridLayout(0, 1));

        /*
        * GridLayout(int row, int col) Creates a grid layout with the specified number of 
        rows and columns. All components in the layout are given equal size. One, but not both, 
        of rows and cols can be zero, which means that any number of objects can be placed in 
        a row or in a column.
         */
        startpanel.add(
                new JLabel("Please choose the level of game:"));
        startpanel.add(levelcombo);
        startpanel.add(
                new JLabel("Please Enter Your Name:"));
        startpanel.add(nameField);
        //the following 2 lines creates Ok and Cancel options
        int result = JOptionPane.showConfirmDialog(null, startpanel, "Game Settings",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        int selectedLevel = Integer.parseInt(levelcombo.getSelectedItem().toString());
        if (result == 0) {
            if (selectedLevel == 2) {
                lv2Selected = true;
            } else {
                lv1Selected = true;
            }

            initComponent();
            //TODO: close mainframe here
        };

    }

    public static void initComponent() {

        JFrame frame = new JFrame("Block Breaker");
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        BlockBreakerPanel panel = new BlockBreakerPanel();
        frame.getContentPane().add(panel);
        //end of coding for menu       

        //java executables will still be running even if we close the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(
                true);
        frame.setSize(
                610, 600);
        frame.setResizable(
                false);
    }
}
