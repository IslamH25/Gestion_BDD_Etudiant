import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class libelleNonInscrit extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
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
                    libelleNonInscrit window = new libelleNonInscrit();
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
    public libelleNonInscrit() {
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
        frame.setBounds(100, 100, 649, 299);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Afficher");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String af="SELECT libelle\r\n"
                        + "   FROM Unite  MINUS  \r\n"
                        + "   SELECT libelle  FROM Unite WHERE code_unite IN (SELECT code_unite FROM EtudiantUnite)";

                try {

                    res=stm.executeQuery(af);
                    table.setModel(DbUtils.resultSetToTableModel(res));

                }
                catch (SQLException s)
                {
                    s.printStackTrace();
                }
            }

        });
        btnNewButton.setForeground(new Color(0, 0, 51));
        btnNewButton.setBackground(new Color(192, 192, 192));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.setBounds(10, 119, 103, 53);
        frame.getContentPane().add(btnNewButton);

        table = new JTable();
        table.setBackground(new Color(153, 204, 255));
        table.setBounds(123, 81, 483, 132);
        frame.getContentPane().add(table);

        JLabel lblAfiicherLesLibells = new JLabel("Afficher les Libell\u00E9s des Unit\u00E9 d'enseignement");
        lblAfiicherLesLibells.setForeground(new Color(51, 255, 204));
        lblAfiicherLesLibells.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblAfiicherLesLibells.setBounds(123, 22, 457, 34);
        frame.getContentPane().add(lblAfiicherLesLibells);

        JLabel lblDenseignementDontAucun = new JLabel("d'enseignement dont aucun \u00E9tudiant n'est inscrit:");
        lblDenseignementDontAucun.setForeground(new Color(51, 255, 204));
        lblDenseignementDontAucun.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblDenseignementDontAucun.setBounds(123, 43, 483, 34);
        frame.getContentPane().add(lblDenseignementDontAucun);
    }
}
