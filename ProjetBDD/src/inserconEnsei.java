import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class inserconEnsei extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldMatriculeEns;
    private JTextField textFieldMatricule_ens;
    private JTextField textFieldNom_ens;
    private JTextField textFieldPrenom_ens;
    private JButton btnEnregistrer;
    private JButton btnQuitter;
    private JScrollPane scrollPane;
    private JTable table;

    private Connection connection=null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private JLabel lblNewLabel;
    private JLabel lblWelcomeToUser;
    private JTextField txtFieldAge;
    private JLabel lblNewLabel_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    inserconEnsei frame = new inserconEnsei();
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
    public inserconEnsei() {
        setTitle("Enseignant");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Enseignant","TPEnseignant");
            stmt = connection.createStatement();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 580);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

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
        btnQuitter.setBounds(671, 491, 103, 29);
        contentPane.add(btnQuitter);

        JLabel lblNewLabel_1 = new JLabel("Consulter Un Tuple de la Table Enseignant");
        lblNewLabel_1.setBackground(new Color(240, 240, 240));
        lblNewLabel_1.setForeground(new Color(51, 255, 204));
        lblNewLabel_1.setFont(new Font("Source Sans Pro Black", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1.setBounds(10, 47, 379, 52);
        contentPane.add(lblNewLabel_1);

        textFieldMatriculeEns = new JTextField();
        textFieldMatriculeEns.setBackground(Color.LIGHT_GRAY);
        textFieldMatriculeEns.setForeground(Color.WHITE);
        textFieldMatriculeEns.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldMatriculeEns.setBounds(10, 119, 130, 37);
        contentPane.add(textFieldMatriculeEns);
        textFieldMatriculeEns.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Matricule_ens :");
        lblNewLabel_5.setForeground(new Color(0, 0, 255));
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_5.setBounds(30, 280, 140, 29);
        contentPane.add(lblNewLabel_5);

        textFieldMatricule_ens = new JTextField();
        textFieldMatricule_ens.setText("200232039");
        textFieldMatricule_ens.setBackground(new Color(0, 153, 153));
        textFieldMatricule_ens.setForeground(Color.WHITE);
        textFieldMatricule_ens.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldMatricule_ens.setBounds(30, 323, 140, 29);
        contentPane.add(textFieldMatricule_ens);
        textFieldMatricule_ens.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Nom_ens :");
        lblNewLabel_6.setForeground(new Color(0, 0, 255));
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_6.setBounds(201, 280, 140, 29);
        contentPane.add(lblNewLabel_6);

        textFieldNom_ens = new JTextField();
        textFieldNom_ens.setText("HAMID");
        textFieldNom_ens.setBackground(new Color(0, 153, 153));
        textFieldNom_ens.setForeground(Color.WHITE);
        textFieldNom_ens.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldNom_ens.setBounds(201, 323, 140, 29);
        contentPane.add(textFieldNom_ens);
        textFieldNom_ens.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("Prenom_ens :");
        lblNewLabel_7.setForeground(new Color(0, 0, 255));
        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_7.setBounds(371, 281, 140, 27);
        contentPane.add(lblNewLabel_7);

        txtFieldAge = new JTextField();
        txtFieldAge.setBackground(new Color(0, 139, 139));
        txtFieldAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtFieldAge.setForeground(Color.WHITE);
        txtFieldAge.setText("30");
        txtFieldAge.setBounds(541, 323, 140, 29);
        contentPane.add(txtFieldAge);
        txtFieldAge.setColumns(10);

        textFieldPrenom_ens = new JTextField();
        textFieldPrenom_ens.setText("NABIL");
        textFieldPrenom_ens.setBackground(new Color(0, 153, 153));
        textFieldPrenom_ens.setForeground(Color.WHITE);
        textFieldPrenom_ens.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldPrenom_ens.setBounds(371, 323, 140, 29);
        contentPane.add(textFieldPrenom_ens);
        textFieldPrenom_ens.setColumns(10);

        btnEnregistrer = new JButton("Inserer");
        btnEnregistrer.setBackground(Color.PINK);
        btnEnregistrer.setForeground(new Color(0, 0, 51));
        btnEnregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String st = "INSERT INTO BDDAdmin.Enseignant VALUES ("+textFieldMatricule_ens.getText()+",'"+textFieldNom_ens.getText()+"','"+textFieldPrenom_ens.getText()+"','"+txtFieldAge.getText()+"')";
                try{
                    stmt = connection.prepareStatement(st);
                    rs = stmt.executeQuery(st);
                    JOptionPane.showMessageDialog (null, "Insertion r�eusite");
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog (null, "Insertion failed");
                }
            }
        });
        btnEnregistrer.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEnregistrer.setBounds(283, 415, 140, 37);
        contentPane.add(btnEnregistrer);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(150, 119, 586, 87);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(153, 204, 255));
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                }
        ));
        scrollPane.setViewportView(table);

        JLabel lblNewLabel_1_1 = new JLabel("Inserer un tuple dans la table Enseignant");
        lblNewLabel_1_1.setForeground(new Color(51, 255, 204));
        lblNewLabel_1_1.setFont(new Font("Source Sans Pro Black", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1_1.setBackground(SystemColor.menu);
        lblNewLabel_1_1.setBounds(10, 230, 379, 52);
        contentPane.add(lblNewLabel_1_1);

        JButton btnNewButton = new JButton("FIND");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String sql = "Select * from BDDAdmin.Enseignant where matricule_ens= '"+textFieldMatriculeEns.getText().toString()+"'";
                try {
                    stmt = connection.prepareStatement(sql);
                    rs = stmt.executeQuery(sql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        btnNewButton.setForeground(Color.BLUE);
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNewButton.setBackground(Color.PINK);
        btnNewButton.setBounds(10, 167, 130, 37);
        contentPane.add(btnNewButton);

        lblNewLabel = new JLabel("Matricule_ens");
        lblNewLabel.setForeground(Color.LIGHT_GRAY);
        lblNewLabel.setFont(new Font("Source Sans Pro Black", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel.setBackground(SystemColor.menu);
        lblNewLabel.setBounds(10, 96, 147, 23);
        contentPane.add(lblNewLabel);

        lblWelcomeToUser = new JLabel("Welcome To User Enseignant !");
        lblWelcomeToUser.setForeground(SystemColor.activeCaption);
        lblWelcomeToUser.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 20));
        lblWelcomeToUser.setBounds(150, 11, 531, 52);
        contentPane.add(lblWelcomeToUser);

        lblNewLabel_2 = new JLabel("Age :");
        lblNewLabel_2.setForeground(Color.BLUE);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setBounds(541, 282, 140, 27);
        contentPane.add(lblNewLabel_2);

    }

}
