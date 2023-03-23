import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class NameEtudiant_NoInscrit {

    JFrame frmRechercher;
    private JTextField Unite;

    private JTable table_1;
    private Connection cn=null;
    private Statement stm=null;
    private ResultSet res=null;
    private JLabel lblInserer;
    private JLabel lblNom;
    private JLabel lblUnit;
    private JLabel lblLesNomsEt;
    private JLabel lblQuiNeSont;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NameEtudiant_NoInscrit window = new NameEtudiant_NoInscrit();
                    window.frmRechercher.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public NameEtudiant_NoInscrit() {
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
        frmRechercher = new JFrame();
        frmRechercher.getContentPane().setBackground(Color.DARK_GRAY);
        frmRechercher.setTitle("Rechercher ");
        frmRechercher.setBounds(100, 100, 636, 328);
        frmRechercher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmRechercher.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Afficher ");
        btnNewButton.setForeground(new Color(0, 0, 51));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String	unite=Unite.getText();
                String af="SELECT nom_etu, prenom_etu\r\n"
                        + "  FROM Etudiant\r\n"
                        + "  WHERE matricule_etu  NOT IN\r\n"
                        + " (SELECT  ET.matricule_etu FROM EtudiantUnite ET, unite U WHERE ET.code_unite = U.code_unite AND libelle='"+unite+"')";
                try {
                    res=stm.executeQuery(af);
                    table_1.setModel(DbUtils.resultSetToTableModel(res));

                }
                catch (SQLException s){s.printStackTrace();

                }
            }
        });
        btnNewButton.setBounds(10, 186, 102, 29);
        frmRechercher.getContentPane().add(btnNewButton);

        Unite = new JTextField();
        Unite.setBounds(10, 146, 102, 29);
        frmRechercher.getContentPane().add(Unite);
        Unite.setColumns(10);



        table_1 = new JTable();
        table_1.setBackground(new Color(153, 204, 255));
        table_1.setBounds(122, 67, 444, 182);
        frmRechercher.getContentPane().add(table_1);

        lblInserer = new JLabel("Inserer \r\n");
        lblInserer.setForeground(new Color(51, 255, 204));
        lblInserer.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInserer.setBounds(10, 67, 87, 34);
        frmRechercher.getContentPane().add(lblInserer);

        lblNom = new JLabel("Nom");
        lblNom.setForeground(new Color(51, 255, 204));
        lblNom.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblNom.setBounds(10, 94, 70, 23);
        frmRechercher.getContentPane().add(lblNom);

        lblUnit = new JLabel("De L'unit\u00E9");
        lblUnit.setForeground(new Color(51, 255, 204));
        lblUnit.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblUnit.setBounds(10, 112, 102, 23);
        frmRechercher.getContentPane().add(lblUnit);

        lblLesNomsEt = new JLabel("Les noms et pr\u00E9noms des \u00E9tudiants");
        lblLesNomsEt.setForeground(new Color(51, 255, 204));
        lblLesNomsEt.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblLesNomsEt.setBounds(122, 11, 600, 23);
        frmRechercher.getContentPane().add(lblLesNomsEt);

        lblQuiNeSont = new JLabel("qui ne sont pas inscrits dans l'unit\u00E9 :");
        lblQuiNeSont.setForeground(new Color(51, 255, 204));
        lblQuiNeSont.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblQuiNeSont.setBounds(122, 33, 600, 23);
        frmRechercher.getContentPane().add(lblQuiNeSont);
    }
}
