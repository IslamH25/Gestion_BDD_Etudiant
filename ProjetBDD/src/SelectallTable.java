import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SelectallTable extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JButton btnQuitter;

    private Connection connection=null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private JButton btnNewButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SelectallTable frame = new SelectallTable();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SelectallTable() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:"
                    + "@localhost:1521:XE","BDDAdmin","TPAdmin");
            stmt = connection.createStatement();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1176, 550);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);



        JButton btnEtudiant = new JButton("Select table Etudiant");
        btnEtudiant.setForeground(new Color(255, 0, 0));
        btnEtudiant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    rs = stmt.executeQuery(" SELECT *  FROM  Etudiant");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnEtudiant.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEtudiant.setBounds(10, 138, 556, 37);
        contentPane.add(btnEtudiant);

        JButton btnEnseignant = new JButton("Select table Enseignant");
        btnEnseignant.setForeground(new Color(102, 204, 0));
        btnEnseignant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    rs = stmt.executeQuery(" SELECT *  FROM  Enseignant");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnEnseignant.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEnseignant.setBounds(10, 200, 556, 36);
        contentPane.add(btnEnseignant);

        JButton btnUnite = new JButton("Select table Unite");
        btnUnite.setForeground(new Color(51, 102, 204));
        btnUnite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    rs = stmt.executeQuery(" SELECT *  FROM  Unite");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnUnite.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnUnite.setBounds(10, 258, 556, 37);
        contentPane.add(btnUnite);

        JButton btnEnseignantUnite = new JButton("Select table EtudiantUnite ");
        btnEnseignantUnite.setForeground(new Color(204, 102, 0));
        btnEnseignantUnite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    rs = stmt.executeQuery(" SELECT *  FROM  EtudiantUnite");
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnEnseignantUnite.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEnseignantUnite.setBounds(10, 314, 556, 37);
        contentPane.add(btnEnseignantUnite);

        btnQuitter = new JButton("Close");
        btnQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (JOptionPane.showConfirmDialog(btnQuitter,"Are you sure ?", "AppBDDTP11",
                        JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnQuitter.setBounds(222, 369, 127, 43);
        contentPane.add(btnQuitter);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(578, 82, 572, 269);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(153, 204, 255));
        scrollPane.setViewportView(table);

        JLabel lblWindowSelectTable = new JLabel("Window Select Table ...User BDDAdmin !");
        lblWindowSelectTable.setForeground(SystemColor.activeCaption);
        lblWindowSelectTable.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 20));
        lblWindowSelectTable.setBounds(256, 11, 723, 52);
        contentPane.add(lblWindowSelectTable);





    }
}
