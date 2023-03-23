import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class changeLibelle {

    JFrame frame;
    private JTextField CodU;
    private JTextField Nlib;
    private JTextField NbH;

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
                    changeLibelle window = new changeLibelle();
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
    public changeLibelle() {
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
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.setBounds(100, 100, 519, 294);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Inserer le code de l'unit\u00E9 \u00E1 changer :");
        lblNewLabel.setBackground(new Color(204, 204, 255));
        lblNewLabel.setForeground(new Color(250, 128, 114));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 37, 309, 30);
        frame.getContentPane().add(lblNewLabel);

        CodU = new JTextField();
        CodU.setBackground(new Color(204, 204, 255));
        CodU.setBounds(326, 37, 118, 30);
        frame.getContentPane().add(CodU);
        CodU.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("New Libelle :");
        lblNewLabel_1.setBackground(new Color(240, 240, 240));
        lblNewLabel_1.setForeground(new Color(250, 128, 114));
        lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 18));
        lblNewLabel_1.setBounds(32, 118, 134, 34);
        frame.getContentPane().add(lblNewLabel_1);

        Nlib = new JTextField();
        Nlib.setBackground(new Color(222, 184, 135));
        Nlib.setBounds(177, 118, 78, 30);
        frame.getContentPane().add(Nlib);
        Nlib.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("N\u00B0 d'heures  :");
        lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 18));
        lblNewLabel_2.setForeground(new Color(250, 128, 114));
        lblNewLabel_2.setBounds(32, 163, 166, 48);
        frame.getContentPane().add(lblNewLabel_2);

        NbH = new JTextField();
        NbH.setBackground(new Color(222, 184, 135));
        NbH.setBounds(177, 168, 78, 30);
        frame.getContentPane().add(NbH);
        NbH.setColumns(10);

        JButton btnNewButton = new JButton("Apply");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setForeground(new Color(250, 128, 114));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codu= CodU.getText();
                String nlib= Nlib.getText();
                String nbh= NbH.getText();
                String maj="update Unite set libelle='"+nlib+"', nbr_heures='"+nbh+"' where code_unite='"+codu+"'";
                try {

                    stm = cn.prepareStatement(maj);
                    setRes(stm.executeQuery(maj));
                    JOptionPane.showMessageDialog (null, "Updated");
                }
                catch (SQLException c)
                {
                    c.printStackTrace();
                }

            }

        });
        btnNewButton.setBounds(285, 143, 98, 34);
        frame.getContentPane().add(btnNewButton);
    }

    public ResultSet getRes() {
        return res;
    }

    public void setRes(ResultSet res) {
        this.res = res;
    }
}
