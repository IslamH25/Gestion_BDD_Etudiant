import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowEnseignant  {
    private JFrame frmenseignant;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowEnseignant window = new WindowEnseignant();
                    window.getFrmenseignant().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public WindowEnseignant() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setFrmenseignant(new JFrame());
        getFrmenseignant().getContentPane().setForeground(new Color(0, 139, 139));
        getFrmenseignant().getContentPane().setFont(new Font("Tahoma", Font.BOLD, 19));
        getFrmenseignant().setBounds(100, 100, 902, 534);
        getFrmenseignant().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrmenseignant().getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("1- Inserer/Consulter un Tuple");
        btnNewButton.setForeground(Color.RED);
        btnNewButton.setFont(new Font("Source Sans Pro Black", Font.BOLD, 18));
        btnNewButton.setBackground(new Color(153, 102, 255));
        btnNewButton.setBounds(185, 176, 484, 40);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    inserconEnsei ens = new inserconEnsei () ;
                    ens .setVisible(true);
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        getFrmenseignant().getContentPane().add(btnNewButton);





        JButton jaune = new JButton("2- Update un Tuple");
        jaune.setForeground(new Color(0, 0, 0));
        jaune.setFont(new Font("Source Sans Pro Black", Font.BOLD, 18));
        jaune.setBackground(Color.ORANGE);
        jaune.setBounds(185, 274, 484, 40);
        jaune.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UpdaEnseignant enseig = new UpdaEnseignant () ;
                    enseig .frmAjouterUnEnseignant.setVisible(true);
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });







        getFrmenseignant().getContentPane().add(jaune);

        JLabel lblYouHaveTwo = new JLabel("You Have Two Choice : ");
        lblYouHaveTwo.setForeground(new Color(51, 255, 204));
        lblYouHaveTwo.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblYouHaveTwo.setBounds(10, 110, 301, 40);
        frmenseignant.getContentPane().add(lblYouHaveTwo);

        JLabel lblWindowInsertionIn = new JLabel("Window Select/Insertion In table ...User Enseignant");
        lblWindowInsertionIn.setForeground(SystemColor.activeCaption);
        lblWindowInsertionIn.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 20));
        lblWindowInsertionIn.setBounds(25, 11, 839, 52);
        frmenseignant.getContentPane().add(lblWindowInsertionIn);

    }

    public JFrame getFrmenseignant() {
        return frmenseignant;
    }

    public void setFrmenseignant(JFrame frmenseignant) {
        this.frmenseignant = frmenseignant;
        frmenseignant.getContentPane().setBackground(Color.DARK_GRAY);
    }
}
