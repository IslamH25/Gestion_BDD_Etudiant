import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class AffparSection {
    JFrame frame;
    private JTextField Sect;
    private JTable table;
    private Connection cn=null;
    private Statement stm=null;
    private ResultSet res=null;
    private JLabel lblInserer;
    private JLabel lblIdSection;
    private JLabel lblLaListeDes;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AffparSection window = new AffparSection();
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
    public AffparSection() {
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
        frame.setBounds(100, 100, 628, 378);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        Sect = new JTextField();
        Sect.setBackground(Color.LIGHT_GRAY);
        Sect.setBounds(22, 173, 111, 34);
        frame.getContentPane().add(Sect);
        Sect.setColumns(10);

        JButton btnNewButton = new JButton("Apply");
        btnNewButton.setForeground(new Color(30, 144, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  section=Sect.getText();

                String af="select E.nom_etu,E.prenom_etu from etudiant E,Section S where  "
                        + "S.ID_section="+section+"AND E.ID_SECTION=S.ID_SECTION ";
                try {

                    res=stm.executeQuery(af);
                    table.setModel(DbUtils.resultSetToTableModel(res));

                }
                catch(SQLException E) {E.printStackTrace();
                    JOptionPane.showMessageDialog (null, "ID_section does not exist");

                }
            }});
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewButton.setBounds(22, 218, 111, 34);
        frame.getContentPane().add(btnNewButton);

        table = new JTable();
        table.setBounds(152, 96, 436, 168);
        frame.getContentPane().add(table);

        lblInserer = new JLabel("Inserer \r\n");
        lblInserer.setForeground(new Color(51, 255, 204));
        lblInserer.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInserer.setBounds(22, 106, 87, 34);
        frame.getContentPane().add(lblInserer);

        lblIdSection = new JLabel("Id_ section");
        lblIdSection.setForeground(new Color(51, 255, 204));
        lblIdSection.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblIdSection.setBounds(22, 130, 120, 34);
        frame.getContentPane().add(lblIdSection);

        lblLaListeDes = new JLabel("La liste des \u00E9tudiants par Section :");
        lblLaListeDes.setForeground(new Color(51, 255, 204));
        lblLaListeDes.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblLaListeDes.setBounds(152, 51, 394, 34);
        frame.getContentPane().add(lblLaListeDes);
    }

}
