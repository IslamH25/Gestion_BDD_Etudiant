import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class WindowEtudiant extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldMatriculeEtu;
    private JLabel lblNewLabel_1;
    private JButton btnChercher;
    private JButton btnQuitter;
    private JScrollPane scrollPane;
    private JTable table;


    private Connection connection=null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private JTextField txtIdsection;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowEtudiant frame = new WindowEtudiant();
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
    public WindowEtudiant() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Etudiant","TPEtudiant");
            stmt = connection.createStatement();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 510);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnQuitter = new JButton("Close");
        btnQuitter.setForeground(new Color(0, 0, 128));
        btnQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (JOptionPane.showConfirmDialog(btnQuitter,"Are you sure ?", "AppBDDTP11",
                        JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        btnQuitter.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 17));
        btnQuitter.setBounds(674, 414, 100, 29);
        contentPane.add(btnQuitter);

        lblNewLabel_1 = new JLabel("Vous Pouvez Consulter Un Tuple de ta Table ETUDIANT en saisissant son matricule :");
        lblNewLabel_1.setForeground(new Color(51, 204, 153));
        lblNewLabel_1.setFont(new Font("Source Sans Pro Black", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1.setBounds(10, 98, 741, 60);
        contentPane.add(lblNewLabel_1);

        textFieldMatriculeEtu = new JTextField();
        textFieldMatriculeEtu.setToolTipText("");
        textFieldMatriculeEtu.setText("20190001");
        textFieldMatriculeEtu.setBackground(Color.LIGHT_GRAY);
        textFieldMatriculeEtu.setForeground(new Color(128, 128, 128));
        textFieldMatriculeEtu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        textFieldMatriculeEtu.setBounds(23, 237, 125, 37);
        contentPane.add(textFieldMatriculeEtu);
        textFieldMatriculeEtu.setColumns(10);

        btnChercher = new JButton("FIND");
        btnChercher.setBackground(Color.PINK);
        btnChercher.setForeground(Color.BLUE);
        btnChercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String sql = "Select * from BDDAdmin.Etudiant where matricule_etu= '"+textFieldMatriculeEtu.getText().toString()+"'";
                try {
                    stmt = connection.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        });
        btnChercher.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnChercher.setBounds(23, 282, 125, 37);
        contentPane.add(btnChercher);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(158, 237, 597, 82);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(153, 204, 255));
        table.setBorder(new EmptyBorder(4, 4, 4, 4));
        scrollPane.setViewportView(table);

        JLabel lblWelcomeToUser = new JLabel("Welcome To User Etudiant !");
        lblWelcomeToUser.setForeground(SystemColor.activeCaption);
        lblWelcomeToUser.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 23));
        lblWelcomeToUser.setBounds(148, 35, 496, 52);
        contentPane.add(lblWelcomeToUser);

        txtIdsection = new JTextField();
        txtIdsection.setFont(new Font("Rockwell", Font.PLAIN, 16));
        txtIdsection.setBackground(Color.LIGHT_GRAY);
        txtIdsection.setForeground(new Color(0, 128, 128));
        txtIdsection.setText("Matricule_etu:");
        txtIdsection.setBounds(23, 210, 113, 29);
        contentPane.add(txtIdsection);
        txtIdsection.setColumns(10);
    }
}
