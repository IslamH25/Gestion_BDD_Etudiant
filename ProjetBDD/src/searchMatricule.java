import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class searchMatricule {
    private Connection cn=null;
    private Statement stm=null;
    private ResultSet res=null;

    JFrame frame;
    private JTextField Mat;
    private JTable table;
    private JLabel lblInserer;
    private JLabel lblMatricule;
    private JLabel lblEtudiant;
    private JLabel lblLtudiantRecherch;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    searchMatricule window = new searchMatricule();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public searchMatricule() {
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
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setBounds(100, 100, 525, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        Mat = new JTextField();
        Mat.setBackground(Color.LIGHT_GRAY);
        Mat.setBounds(10, 139, 104, 23);
        frame.getContentPane().add(Mat);
        Mat.setColumns(10);

        JButton btnNewButton = new JButton("Afficher");
        btnNewButton.setBackground(new Color(240, 240, 240));
        btnNewButton.setForeground(new Color(0, 0, 51));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mat=Mat.getText();
                String af= "SELECT E.nom_etu,  E.prenom_etu,  U.libelle,  ((ETU.note_cc + ETU.note_tp + ETU.note_examen)/3)\r\n"
                        +  " FROM Etudiant E, Unite U, EtudiantUnite ETU \r\n"
                        + " WHERE E.matricule_etu ='"+mat+"'AND E.matricule_etu = ETU.matricule_etu AND ETU.code_unite = U.code_unite";
                try {

                    res=stm.executeQuery(af);
                    table.setModel(DbUtils.resultSetToTableModel(res));
                }
                catch(SQLException E)
                {
                    E.printStackTrace();
                }

            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBounds(10, 173, 104, 23);
        frame.getContentPane().add(btnNewButton);

        table = new JTable();
        table.setBackground(new Color(153, 204, 255));
        table.setBounds(124, 75, 375, 121);
        frame.getContentPane().add(table);

        lblInserer = new JLabel("Inserer \r\n");
        lblInserer.setForeground(new Color(51, 255, 204));
        lblInserer.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInserer.setBounds(10, 68, 78, 23);
        frame.getContentPane().add(lblInserer);

        lblMatricule = new JLabel("Matricule");
        lblMatricule.setForeground(new Color(51, 255, 204));
        lblMatricule.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblMatricule.setBounds(10, 91, 93, 23);
        frame.getContentPane().add(lblMatricule);

        lblEtudiant = new JLabel("Etudiant");
        lblEtudiant.setForeground(new Color(51, 255, 204));
        lblEtudiant.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblEtudiant.setBounds(10, 108, 94, 23);
        frame.getContentPane().add(lblEtudiant);

        lblLtudiantRecherch = new JLabel("L'\u00E9tudiant recherch\u00E9 :");
        lblLtudiantRecherch.setForeground(new Color(51, 255, 204));
        lblLtudiantRecherch.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblLtudiantRecherch.setBounds(124, 46, 316, 23);
        frame.getContentPane().add(lblLtudiantRecherch);
    }

}
