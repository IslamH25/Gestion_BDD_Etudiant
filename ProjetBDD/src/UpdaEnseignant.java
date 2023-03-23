import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdaEnseignant extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JFrame frmAjouterUnEnseignant;


    private Connection cn=null;
    private Statement stm=null;
    private ResultSet res=null;
    private JTextField Matr;
    private JTextField Age;
    private JTextField Nnom;
    private JTextField Nprenom;
    private JLabel lblWelcomeToWindow;
    private JLabel lblInserer;
    private JLabel lblMatricule;
    private JLabel lblEnseignant;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdaEnseignant window = new UpdaEnseignant();
                    window.frmAjouterUnEnseignant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public UpdaEnseignant() {
        try {


            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn= DriverManager.getConnection("jdbc:oracle:thin:BDDAdmin/TPAdmin@localhost");
            stm=cn.createStatement();
        }

        catch (Exception e) {e.printStackTrace();}
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmAjouterUnEnseignant = new JFrame();
        frmAjouterUnEnseignant.getContentPane().setBackground(Color.DARK_GRAY);
        frmAjouterUnEnseignant.setTitle("Ajouter Un Enseignant");
        frmAjouterUnEnseignant.setBounds(100, 100, 664, 395);
        frmAjouterUnEnseignant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAjouterUnEnseignant.getContentPane().setLayout(null);

        Matr = new JTextField();
        Matr.setBackground(Color.LIGHT_GRAY);
        Matr.setBounds(321, 70, 101, 32);
        frmAjouterUnEnseignant.getContentPane().add(Matr);
        Matr.setColumns(10);

        JLabel lebel = new JLabel("Nouveau Age :");
        lebel.setForeground(Color.WHITE);
        lebel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lebel.setBounds(20, 266, 152, 19);
        frmAjouterUnEnseignant.getContentPane().add(lebel);

        Age = new JTextField();
        Age.setBackground(Color.LIGHT_GRAY);
        Age.setBounds(198, 261, 113, 32);
        frmAjouterUnEnseignant.getContentPane().add(Age);
        Age.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Nouveau Nom :");
        lblNewLabel_4.setBackground(Color.WHITE);
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4.setBounds(20, 148, 169, 25);
        frmAjouterUnEnseignant.getContentPane().add(lblNewLabel_4);

        Nnom = new JTextField();
        Nnom.setBackground(Color.LIGHT_GRAY);
        Nnom.setBounds(198, 146, 113, 32);
        frmAjouterUnEnseignant.getContentPane().add(Nnom);
        Nnom.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Nouveau Prenom :");
        lblNewLabel_6.setBackground(Color.WHITE);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_6.setForeground(Color.WHITE);
        lblNewLabel_6.setBounds(20, 206, 169, 25);
        frmAjouterUnEnseignant.getContentPane().add(lblNewLabel_6);

        Nprenom = new JTextField();
        Nprenom.setBackground(Color.LIGHT_GRAY);
        Nprenom.setBounds(198, 205, 113, 32);
        frmAjouterUnEnseignant.getContentPane().add(Nprenom);
        Nprenom.setColumns(10);

        JButton btnNewButton_2 = new JButton("Update");
        btnNewButton_2.setForeground(new Color(0, 0, 0));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                String age=Age.getText();
                String nnom=Nnom.getText();
                String nprenom=Nprenom.getText();

                String maj= "UPDATE Enseignant \r\n" +
                        "	  set nom_ens='"+nnom+"',prenom_ens='"+nprenom+"' ,age='"+age+"' \r\n"+
                        "	  WHERE matricule_ens='"+Matr.getText().toString()+"'";

                try {

                    stm = cn.prepareStatement(maj);
                    setRes(stm.executeQuery(maj));
                    JOptionPane.showMessageDialog (null, "Mise � jour effectue");
                }
                catch (SQLException c) {c.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Matrciule invalide ..ressayer encore");}
            }

        });
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton_2.setBackground(new Color(255, 228, 196));
        btnNewButton_2.setBounds(392, 205, 101, 33);
        frmAjouterUnEnseignant.getContentPane().add(btnNewButton_2);

        lblWelcomeToWindow = new JLabel("Welcome To Window User Enseignant !");
        lblWelcomeToWindow.setForeground(SystemColor.activeCaption);
        lblWelcomeToWindow.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 19));
        lblWelcomeToWindow.setBounds(37, 11, 577, 52);
        frmAjouterUnEnseignant.getContentPane().add(lblWelcomeToWindow);

        lblInserer = new JLabel("Inserer \r\n");
        lblInserer.setForeground(new Color(51, 255, 204));
        lblInserer.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInserer.setBounds(20, 74, 70, 23);
        frmAjouterUnEnseignant.getContentPane().add(lblInserer);

        lblMatricule = new JLabel("Matricule");
        lblMatricule.setForeground(new Color(51, 255, 204));
        lblMatricule.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblMatricule.setBounds(98, 74, 95, 23);
        frmAjouterUnEnseignant.getContentPane().add(lblMatricule);

        lblEnseignant = new JLabel("Enseignant");
        lblEnseignant.setForeground(new Color(51, 255, 204));
        lblEnseignant.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblEnseignant.setBounds(198, 74, 113, 23);
        frmAjouterUnEnseignant.getContentPane().add(lblEnseignant);
    }

    public ResultSet getRes() {
        return res;
    }

    public void setRes(ResultSet res) {
        this.res = res;
    }
}
