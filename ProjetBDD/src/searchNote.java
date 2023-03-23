import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class searchNote {
    JFrame frmNoteEtudiant;
    private JTextField Note;

    private JTable table_2;
    private Connection cn=null;
    private Statement stm=null;
    private ResultSet res=null;
    private JLabel lblInserer;
    private JLabel lblUneNote;
    private JLabel lblLesNomsEt;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    searchNote window = new searchNote();
                    window.frmNoteEtudiant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public searchNote() {
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
        frmNoteEtudiant = new JFrame();
        frmNoteEtudiant.setTitle("NOTE ETUDIANT");
        frmNoteEtudiant.getContentPane().setBackground(Color.DARK_GRAY);
        frmNoteEtudiant.setBounds(100, 100, 637, 382);
        frmNoteEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmNoteEtudiant.getContentPane().setLayout(null);

        Note = new JTextField();
        Note.setBackground(Color.LIGHT_GRAY);
        Note.setBounds(23, 144, 74, 33);
        frmNoteEtudiant.getContentPane().add(Note);
        Note.setColumns(10);



        table_2 = new JTable();
        table_2.setBackground(new Color(153, 204, 255));
        table_2.setBounds(137, 76, 435, 197);
        frmNoteEtudiant.getContentPane().add(table_2);

        JButton btnNewButton = new JButton("Afficher");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setForeground(new Color(0, 0, 51));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String note= Note.getText();
                String af="select e.nom_etu , e.prenom_etu from etudiant e ,EtudiantUnite etu where e.matricule_etu=etu.matricule_etu and note_examen='"+note+"'";
                try {
                    res=stm.executeQuery(af);
                    table_2.setModel(DbUtils.resultSetToTableModel(res));

                }
                catch (SQLException s){s.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(18, 188, 89, 33);
        frmNoteEtudiant.getContentPane().add(btnNewButton);

        lblInserer = new JLabel("Inserer \r\n");
        lblInserer.setForeground(new Color(51, 255, 204));
        lblInserer.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInserer.setBounds(27, 76, 70, 23);
        frmNoteEtudiant.getContentPane().add(lblInserer);

        lblUneNote = new JLabel("Une Note");
        lblUneNote.setForeground(new Color(51, 255, 204));
        lblUneNote.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblUneNote.setBounds(18, 110, 97, 23);
        frmNoteEtudiant.getContentPane().add(lblUneNote);

        lblLesNomsEt = new JLabel("les noms et pr\u00E9noms des \u00E9tudiants concern\u00E9es :");
        lblLesNomsEt.setBackground(new Color(240, 240, 240));
        lblLesNomsEt.setForeground(new Color(51, 255, 204));
        lblLesNomsEt.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblLesNomsEt.setBounds(137, 42, 435, 23);
        frmNoteEtudiant.getContentPane().add(lblLesNomsEt);
    }
}
