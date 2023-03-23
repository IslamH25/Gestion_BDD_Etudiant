import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WindowBDDAdmin extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private Connection connection=null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowBDDAdmin frame = new WindowBDDAdmin();
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
    public WindowBDDAdmin() {
        setTitle("BDDAdmin");
        setFont(null);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:"
                    + "@localhost:1521:XE","BDDAdmin","TPAdmin");
            setStmt(connection.createStatement());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 960, 510);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton bleu = new JButton("Afficher toutes les tables au votre choix");
        bleu.setBackground(new Color(0, 191, 255));
        bleu.setForeground(new Color(0, 128, 128));
        bleu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    SelectallTable cnsltBDDAdmin = new SelectallTable () ;
                    cnsltBDDAdmin.setVisible(true);
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        bleu.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        bleu.setBounds(116, 42, 716, 32);
        contentPane.add(bleu);


        JButton btnInsert = new JButton("Inserer des tuples dans toutes les tables");
        btnInsert.setBackground(new Color(0, 191, 255));
        btnInsert.setForeground(new Color(0, 128, 128));
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    inserINtable InsrtBDDAdmin = new inserINtable () ;
                    InsrtBDDAdmin.setVisible(true);

                }catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnInsert.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        btnInsert.setBounds(116, 85, 716, 32);
        contentPane.add(btnInsert);

        JButton rose = new JButton("Update le libelle et le N\u00B0 d'heures d'une Unit\u00E9");
        rose.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        rose.setForeground(new Color(0, 128, 128));
        rose.setBackground(new Color(0, 191, 255));
        rose.setBounds(116, 128, 716, 32);
        rose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    changeLibelle L2 = new changeLibelle() ;
                    L2.frame.setVisible(true);

                }catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        contentPane.add(rose);



        JButton btnNewButton_1 = new JButton("Afficher les Etudiants qui ne sont pas inscrit dans une Unit\u00E9 donn\u00E9e");
        btnNewButton_1.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        btnNewButton_1.setBackground(new Color(0, 191, 255));
        btnNewButton_1.setForeground(new Color(0, 128, 128));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    NameEtudiant_NoInscrit et = new NameEtudiant_NoInscrit();
                    et.frmRechercher.setVisible(true);

                }catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton_1.setBounds(116, 214, 716, 32);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Afficher Les libell\u00E9s dont aucun etudiant n'est inscrit");
        btnNewButton_2.setBackground(new Color(0, 191, 255));
        btnNewButton_2.setForeground(new Color(0, 128, 128));
        btnNewButton_2.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    libelleNonInscrit L3 = new libelleNonInscrit() ;
                    L3.frame.setVisible(true);

                }catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton_2.setBounds(116, 257, 716, 29);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Afficher les Moyenne des \u00E9tudiants");
        btnNewButton_3.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        btnNewButton_3.setBackground(new Color(0, 191, 255));
        btnNewButton_3.setForeground(new Color(0, 128, 128));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResEtudiant r = new ResEtudiant();
                r.frame.setVisible(true);
            }
        });
        btnNewButton_3.setBounds(116, 297, 716, 32);
        contentPane.add(btnNewButton_3);



        JLabel lblBienvenueDansLa = new JLabel("Welcome To User BDDAdmin !");
        lblBienvenueDansLa.setForeground(SystemColor.activeCaption);
        lblBienvenueDansLa.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 20));
        lblBienvenueDansLa.setBounds(246, 0, 472, 52);
        contentPane.add(lblBienvenueDansLa);




    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
