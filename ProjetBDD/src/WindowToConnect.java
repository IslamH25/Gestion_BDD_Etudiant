import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WindowToConnect {

    private Connection connection=null;
    private Statement stmt = null;
    private ResultSet rs = null;


    private JFrame frame;
    private JTextField txtUser;
    private JPasswordField textMDP;
    private JButton btnconnexion;

    //Launch the application.

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowToConnect window = new WindowToConnect();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    // Create the application.

    public WindowToConnect() {
        initialize();
    }


    //Initialize the contents of the frame.

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.getContentPane().setFont(new Font("Vladimir Script", Font.BOLD | Font.ITALIC, 39));
        frame.setBounds(100, 100, 780, 460);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lbfentreCnx = new JLabel("HOME CONNECT");
        lbfentreCnx.setForeground(SystemColor.activeCaption);
        lbfentreCnx.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 32));
        lbfentreCnx.setBounds(221, 90, 383, 52);
        frame.getContentPane().add(lbfentreCnx);

        JLabel lblUtilisateur = new JLabel("UserName :");
        lblUtilisateur.setForeground(Color.WHITE);
        lblUtilisateur.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUtilisateur.setBounds(80, 185, 116, 37);
        frame.getContentPane().add(lblUtilisateur);

        JLabel lblMdp = new JLabel("PassWords :");
        lblMdp.setForeground(Color.WHITE);
        lblMdp.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMdp.setBounds(80, 244, 142, 52);
        frame.getContentPane().add(lblMdp);

        txtUser = new JTextField();
        txtUser.setBackground(Color.LIGHT_GRAY);
        txtUser.setForeground(Color.DARK_GRAY);
        txtUser.setText("BDDAdmin");
        txtUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtUser.setBounds(221, 187, 133, 37);
        frame.getContentPane().add(txtUser);
        txtUser.setColumns(10);

        btnconnexion = new JButton("CONNECT");
        btnconnexion.setBackground(Color.LIGHT_GRAY);
        btnconnexion.setForeground(Color.BLUE);
        btnconnexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String user = txtUser.getText();
                @SuppressWarnings("deprecation")
                String MDP = textMDP.getText();
                int test=0; //pour verifier si le mdps est corr si test=0 alors rien a changer alors mdps/USER incorr

                if((user.equals("BDDAdmin")) && (MDP.equals("TPAdmin"))) {
                    test++;
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        connection = DriverManager.getConnection("jdbc:oracle:thin:"
                                + "@localhost:1521:XE","BDDAdmin","TPAdmin");
                        setStmt(connection.createStatement());
                        JOptionPane.showMessageDialog (null, "Connected in User BDDAdmin");
                        WindowBDDAdmin fenetreBDDAdmin = new WindowBDDAdmin () ;
                        fenetreBDDAdmin.setVisible(true);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }


                if(user.equals("Etudiant")&& (MDP.equals("TPEtudiant"))) {
                    test++;
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        connection = DriverManager.getConnection("jdbc:oracle:thin:"
                                + "Etudiant/TPEtudiant@localhost");
                        setStmt(connection.createStatement());
                        JOptionPane.showMessageDialog (null, "connected in User Etudiant");
                        WindowEtudiant fenetreEtud = new WindowEtudiant () ;
                        fenetreEtud.setVisible(true);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }


                if(user.equals("Enseignant")&& (MDP.equals("TPEnseignant"))) {
                    test++;
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        connection = DriverManager.getConnection("jdbc:oracle:thin:"
                                + "Enseignant/TPEnseignant@localhost");
                        setStmt(connection.createStatement());
                        JOptionPane.showMessageDialog (null, "connected in User Enseignant");
                        WindowEnseignant x = new WindowEnseignant ();
                        x.getFrmenseignant().setVisible(true);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                if(test==0)
                    JOptionPane.showMessageDialog(null, "Failed connection");
            }
        });
        btnconnexion.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnconnexion.setBounds(479, 215, 142, 37);
        frame.getContentPane().add(btnconnexion);

        textMDP = new JPasswordField();
        textMDP.setBackground(Color.LIGHT_GRAY);
        textMDP.setForeground(Color.BLACK);
        textMDP.setBounds(221, 255, 133, 37);
        frame.getContentPane().add(textMDP);

        Label label = new Label("");
        label.setBackground(SystemColor.activeCaption);
        label.setBounds(0, 0, 764, 74);
        frame.getContentPane().add(label);



    }
    public Statement getStmt() {return stmt;}
    public void setStmt(Statement stmt) {this.stmt = stmt;}
    public ResultSet getRs() {return rs;}
    public void setRs(ResultSet rs) {this.rs = rs;}
}
