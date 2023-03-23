import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class searchEnseignant {
    JFrame frame;
    private JTextField nom;
    private JTable table;
    private Connection cn=null;
    private Statement stm=null;
    private ResultSet res=null;
    private JLabel lblInserer;
    private JLabel lblNom;
    private JLabel lblEnseignant;
    private JLabel lblLesUnitsEnseignes;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    searchEnseignant window = new searchEnseignant();
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
    public searchEnseignant() {
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
        frame.setBounds(100, 100, 635, 302);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        nom = new JTextField();
        nom.setBounds(20, 141, 86, 28);
        frame.getContentPane().add(nom);
        nom.setColumns(10);

        JButton btnNewButton = new JButton("Search");
        btnNewButton.setForeground(new Color(0, 0, 51));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Nom=nom.getText();
                String af="SELECT U.libelle\r\n"
                        + "   FROM  Unite U, Enseignant ens\r\n"
                        + "   WHERE ens.matricule_ens = U.matricule_ens AND ens.Nom_ens='"+Nom+"'";

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
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton.setBounds(20, 180, 86, 39);
        frame.getContentPane().add(btnNewButton);

        table = new JTable();
        table.setFont(new Font("Source Sans Pro", Font.PLAIN, 13));
        table.setBackground(new Color(153, 204, 204));
        table.setBounds(139, 70, 435, 149);
        frame.getContentPane().add(table);

        lblInserer = new JLabel("Inserer \r\n");
        lblInserer.setForeground(new Color(51, 255, 204));
        lblInserer.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInserer.setBounds(27, 70, 70, 23);
        frame.getContentPane().add(lblInserer);

        lblNom = new JLabel("Nom");
        lblNom.setForeground(new Color(51, 255, 204));
        lblNom.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblNom.setBounds(37, 93, 70, 23);
        frame.getContentPane().add(lblNom);

        lblEnseignant = new JLabel("Enseignant");
        lblEnseignant.setForeground(new Color(51, 255, 204));
        lblEnseignant.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblEnseignant.setBounds(10, 113, 102, 23);
        frame.getContentPane().add(lblEnseignant);

        lblLesUnitsEnseignes = new JLabel("Les unit\u00E9s enseign\u00E9es par un Enseignant :");
        lblLesUnitsEnseignes.setForeground(new Color(51, 255, 204));
        lblLesUnitsEnseignes.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblLesUnitsEnseignes.setBounds(140, 39, 379, 23);
        frame.getContentPane().add(lblLesUnitsEnseignes);
    }

}
