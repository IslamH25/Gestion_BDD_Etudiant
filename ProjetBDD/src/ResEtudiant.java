import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ResEtudiant {
    JFrame frame;
    private JTable table;
    private Connection cn=null;
    private Statement stm=null;
    private ResultSet res=null;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ResEtudiant window = new ResEtudiant();
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
    public ResEtudiant() {
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
        frame.setBounds(100, 100, 645, 324);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        table = new JTable();
        table.setBackground(new Color(135, 206, 250));
        table.setForeground(new Color(0, 0, 0));
        table.setBounds(70, 82, 468, 180);
        frame.getContentPane().add(table);

        JButton btnNewButton = new JButton("Afficher les r\u00E9sultats des \u00E9tudiants:");
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setForeground(new Color(60, 179, 113));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String af="SELECT E.nom_etu,  E.prenom_etu,  U.libelle,  ((ETU.note_cc + ETU.note_tp + ETU.note_examen)/3)\r\n"
                        + "   FROM Etudiant E, Unite U, EtudiantUnite ETU\r\n"
                        + "   WHERE E.matricule_etu = ETU.matricule_etu AND ETU.code_unite = U.code_unite";
                try {

                    res=stm.executeQuery(af);
                    table.setModel(DbUtils.resultSetToTableModel(res));
                }
                catch(SQLException E) {E.printStackTrace();
                }

            }});
        btnNewButton.setFont(new Font("Source Sans Pro Black", Font.BOLD, 18));
        btnNewButton.setBounds(70, 26, 468, 38);
        frame.getContentPane().add(btnNewButton);
    }

}
