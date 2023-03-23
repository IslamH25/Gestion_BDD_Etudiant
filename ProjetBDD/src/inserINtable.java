import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class inserINtable extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldMatriculeEtu;
    private JTextField textFieldNomEtu;
    private JTextField textFieldPrenomEtu;
    private JTextField textFieldNaissance;
    private JTextField textFieldAdresse;
    private JTextField textFieldMatricule_ens;
    private JTextField textFieldNom_ens;
    private JTextField textFieldPrenom_ens;
    private JTextField textFieldCodeUnit;
    private JTextField textFieldLibelle;
    private JTextField textFieldHeures;
    private JTextField textFieldMatEns;

    private Connection connection=null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private JTextField textFieldMatEtu;
    private JTextField textFieldCodeUnite4;
    private JTextField textFieldNoteCC;
    private JTextField textFieldNoteTP;
    private JTextField textFieldNoteExam;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField idsection;

    // Launch the application.

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    inserINtable frame = new inserINtable();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    // Create the application.

    public inserINtable() {
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
        setBounds(100, 100, 1084,788);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setToolTipText("Black");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);



        JLabel lblMatriculeetu = new JLabel("Matricule_etu:");
        lblMatriculeetu.setForeground(Color.WHITE);
        lblMatriculeetu.setFont(new Font("Georgia", Font.BOLD, 14));
        lblMatriculeetu.setBounds(5, 90, 137, 37);
        contentPane.add(lblMatriculeetu);

        textFieldMatriculeEtu = new JTextField();
        textFieldMatriculeEtu.setForeground(Color.GRAY);
        textFieldMatriculeEtu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldMatriculeEtu.setBounds(121, 96, 137, 34);
        contentPane.add(textFieldMatriculeEtu);
        textFieldMatriculeEtu.setColumns(10);

        JLabel lblNometu = new JLabel("Nom_etu :");
        lblNometu.setForeground(Color.WHITE);
        lblNometu.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNometu.setBounds(5, 138, 137, 37);
        contentPane.add(lblNometu);

        textFieldNomEtu = new JTextField();
        textFieldNomEtu.setForeground(Color.GRAY);
        textFieldNomEtu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNomEtu.setColumns(10);
        textFieldNomEtu.setBounds(121, 141, 137, 34);
        contentPane.add(textFieldNomEtu);

        JLabel lblPrenometu = new JLabel("Prenom_etu :");
        lblPrenometu.setForeground(Color.WHITE);
        lblPrenometu.setFont(new Font("Georgia", Font.BOLD, 14));
        lblPrenometu.setBounds(5, 186, 137, 37);
        contentPane.add(lblPrenometu);

        textFieldPrenomEtu = new JTextField();
        textFieldPrenomEtu.setForeground(Color.GRAY);
        textFieldPrenomEtu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldPrenomEtu.setColumns(10);
        textFieldPrenomEtu.setBounds(121, 187, 137, 34);
        contentPane.add(textFieldPrenomEtu);

        JLabel lblDatenaissance = new JLabel("Date_naissance:");
        lblDatenaissance.setForeground(Color.WHITE);
        lblDatenaissance.setFont(new Font("Georgia", Font.BOLD, 13));
        lblDatenaissance.setBounds(5, 234, 137, 37);
        contentPane.add(lblDatenaissance);

        textFieldNaissance = new JTextField();
        textFieldNaissance.setForeground(Color.GRAY);
        textFieldNaissance.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNaissance.setColumns(10);
        textFieldNaissance.setBounds(121, 235, 137, 34);
        contentPane.add(textFieldNaissance);

        JLabel lblAdresse = new JLabel("Adresse :");
        lblAdresse.setForeground(Color.WHITE);
        lblAdresse.setFont(new Font("Georgia", Font.BOLD, 14));
        lblAdresse.setBounds(5, 282, 137, 37);
        contentPane.add(lblAdresse);

        textFieldAdresse = new JTextField();
        textFieldAdresse.setForeground(Color.GRAY);
        textFieldAdresse.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldAdresse.setColumns(10);
        textFieldAdresse.setBounds(121, 282, 137, 34);
        contentPane.add(textFieldAdresse);


        idsection = new JTextField();
        idsection.setForeground(Color.GRAY);
        idsection.setFont(new Font("Tahoma", Font.PLAIN, 14));
        idsection.setColumns(10);
        idsection.setBounds(121,328, 137, 34);
        contentPane.add(idsection);

        JLabel lblidsection = new JLabel("ID Section :");
        lblidsection.setForeground(Color.WHITE);
        lblidsection.setFont(new Font("Georgia", Font.BOLD, 14));
        lblidsection.setBounds(5,327, 137, 37);
        contentPane.add(lblidsection);


        JButton btnEnregistrerEnseignant = new JButton("Apply");
        btnEnregistrerEnseignant.setForeground(Color.PINK);
        btnEnregistrerEnseignant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String st = "INSERT INTO Enseignant VALUES ("
                        +textFieldMatricule_ens.getText()+",'"+textFieldNom_ens.getText()+"','"
                        +textFieldPrenom_ens.getText()+"',"+textField.getText()+")";
                try {
                    stmt = connection.prepareStatement(st);
                    rs=stmt.executeQuery(st);
                    JOptionPane.showMessageDialog (null, "Insertion completed");
                } catch (SQLException exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog (null, "Insertion Failed");
                }
            }
        });
        btnEnregistrerEnseignant.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEnregistrerEnseignant.setBounds(518, 282, 138, 34);
        contentPane.add(btnEnregistrerEnseignant);

        JButton btnEnregistrerEtudiant = new JButton("Apply");
        btnEnregistrerEtudiant.setForeground(new Color(255, 0, 0));
        btnEnregistrerEtudiant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String rq = "INSERT INTO Etudiant VALUES ("+textFieldMatriculeEtu.getText()
                        +",'"+textFieldNomEtu.getText()+"','"+textFieldPrenomEtu.getText()
                        +"','"+textFieldNaissance.getText()+"','"+textFieldAdresse.getText()+"','"+idsection.getText()+"')";
                try {
                    stmt = connection.prepareStatement(rq);
                    rs=stmt.executeQuery(rq);
                    JOptionPane.showMessageDialog (null, "Insertion completed");
                } catch (SQLException exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog (null, "Insertion Failed");
                }
            }
        });

        btnEnregistrerEtudiant.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEnregistrerEtudiant.setBounds(121, 377, 137, 34);
        contentPane.add(btnEnregistrerEtudiant);

        JLabel lblMatriculeens = new JLabel("Matricule_ens:");
        lblMatriculeens.setForeground(Color.PINK);
        lblMatriculeens.setFont(new Font("Georgia", Font.BOLD, 15));
        lblMatriculeens.setBounds(397, 90, 137, 37);
        contentPane.add(lblMatriculeens);

        textFieldMatricule_ens = new JTextField();
        textFieldMatricule_ens.setForeground(Color.GRAY);
        textFieldMatricule_ens.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldMatricule_ens.setColumns(10);
        textFieldMatricule_ens.setBounds(519, 91, 137, 34);
        contentPane.add(textFieldMatricule_ens);

        JLabel labnomens = new JLabel("Nom_ens:");
        labnomens.setForeground(Color.PINK);
        labnomens.setFont(new Font("Georgia", Font.BOLD, 15));
        labnomens.setBounds(397, 138, 137, 37);
        contentPane.add(labnomens);

        textFieldNom_ens = new JTextField();
        textFieldNom_ens.setForeground(Color.GRAY);
        textFieldNom_ens.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNom_ens.setColumns(10);
        textFieldNom_ens.setBounds(519, 137, 137, 38);
        contentPane.add(textFieldNom_ens);

        JLabel lblPrenomens_1 = new JLabel("Prenom_ens :");
        lblPrenomens_1.setForeground(Color.PINK);
        lblPrenomens_1.setFont(new Font("Georgia", Font.BOLD, 15));
        lblPrenomens_1.setBounds(397, 186, 137, 37);
        contentPane.add(lblPrenomens_1);

        textFieldPrenom_ens = new JTextField();
        textFieldPrenom_ens.setForeground(Color.GRAY);
        textFieldPrenom_ens.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldPrenom_ens.setColumns(10);
        textFieldPrenom_ens.setBounds(519, 185, 137, 37);
        contentPane.add(textFieldPrenom_ens);

        JLabel lblCodeunite = new JLabel("Code_unite :");
        lblCodeunite.setForeground(new Color(0, 0, 255));
        lblCodeunite.setFont(new Font("Georgia", Font.BOLD, 15));
        lblCodeunite.setBounds(801, 138, 137, 37);
        contentPane.add(lblCodeunite);

        textFieldCodeUnit = new JTextField();
        textFieldCodeUnit.setForeground(Color.GRAY);
        textFieldCodeUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldCodeUnit.setColumns(10);
        textFieldCodeUnit.setBounds(921, 137, 137, 37);
        contentPane.add(textFieldCodeUnit);

        JLabel lblLibelle = new JLabel("Libelle :");
        lblLibelle.setForeground(Color.BLUE);
        lblLibelle.setFont(new Font("Georgia", Font.BOLD, 15));
        lblLibelle.setBounds(801, 186, 137, 37);
        contentPane.add(lblLibelle);

        textFieldLibelle = new JTextField();
        textFieldLibelle.setForeground(Color.GRAY);
        textFieldLibelle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldLibelle.setColumns(10);
        textFieldLibelle.setBounds(921, 185, 137, 37);
        contentPane.add(textFieldLibelle);

        JLabel lblNbrheures = new JLabel("Nbr_d'heures :");
        lblNbrheures.setForeground(Color.BLUE);
        lblNbrheures.setFont(new Font("Georgia", Font.BOLD, 15));
        lblNbrheures.setBounds(801, 234, 137, 37);
        contentPane.add(lblNbrheures);

        textFieldHeures = new JTextField();
        textFieldHeures.setForeground(Color.GRAY);
        textFieldHeures.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldHeures.setColumns(10);
        textFieldHeures.setBounds(921, 235, 137, 37);
        contentPane.add(textFieldHeures);

        JLabel lblMatriculeens_1 = new JLabel("Matricule_ens :");
        lblMatriculeens_1.setForeground(Color.BLUE);
        lblMatriculeens_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMatriculeens_1.setBounds(801, 282, 147, 37);
        contentPane.add(lblMatriculeens_1);

        textFieldMatEns = new JTextField();
        textFieldMatEns.setForeground(Color.GRAY);
        textFieldMatEns.setFont(new Font("Tahoma", Font.PLAIN, 17));
        textFieldMatEns.setColumns(10);
        textFieldMatEns.setBounds(921, 282, 137, 37);
        contentPane.add(textFieldMatEns);

        JButton btnEnregistrerUnite = new JButton("Apply");
        btnEnregistrerUnite.setForeground(new Color(0, 0, 255));
        btnEnregistrerUnite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String st = "INSERT INTO Unite VALUES ('"+textFieldCodeUnit.getText()
                        +"','"+textFieldLibelle.getText()+"','"+textFieldHeures.getText()+"','"
                        +textFieldMatEns.getText()+"')";
                try {
                    stmt = connection.prepareStatement(st);
                    setRs(stmt.executeQuery(st));
                    JOptionPane.showMessageDialog (null, "Insertion completed");
                } catch (SQLException exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog (null, "Insertion Failed");
                }
            }
        });
        btnEnregistrerUnite.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEnregistrerUnite.setBounds(921, 330, 137, 34);
        contentPane.add(btnEnregistrerUnite);


        JButton btnEnregitrerEnseignantUnite = new JButton("Apply");
        btnEnregitrerEnseignantUnite.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEnregitrerEnseignantUnite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String st = "INSERT INTO EtudiantUnite VALUES ("+textFieldMatEtu.getText()
                        +",'"+textFieldCodeUnite4.getText()+"',"+textFieldNoteCC.getText()
                        +","+textFieldNoteTP.getText()+","+textFieldNoteExam.getText()+")";
                try {
                    stmt = connection.prepareStatement(st);
                    rs=stmt.executeQuery(st);
                    JOptionPane.showMessageDialog (null, "Insertion completed");
                } catch (SQLException exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog (null, "Insertion Failed");
                }

            }
        });
        btnEnregitrerEnseignantUnite.setForeground(Color.BLUE);
        btnEnregitrerEnseignantUnite.setBounds(121, 680, 137, 44);
        contentPane.add(btnEnregitrerEnseignantUnite);

        JLabel lblmatriculeetu1 = new JLabel("Matricule_etu :");
        lblmatriculeetu1.setForeground(Color.BLUE);
        lblmatriculeetu1.setFont(new Font("Georgia", Font.BOLD, 14));
        lblmatriculeetu1.setBounds(5, 465, 137, 25);
        contentPane.add(lblmatriculeetu1);

        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setBounds(10, 560, 45, -4);
        contentPane.add(lblNewLabel_3);

        JLabel lblNotecc = new JLabel("Note_cc :");
        lblNotecc.setForeground(Color.BLUE);
        lblNotecc.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNotecc.setBounds(5, 544, 99, 25);
        contentPane.add(lblNotecc);

        JLabel lblNotetp = new JLabel("Note_tp:");
        lblNotetp.setForeground(Color.BLUE);
        lblNotetp.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNotetp.setBounds(5, 596, 83, 25);
        contentPane.add(lblNotetp);

        JLabel lblNoteexamen = new JLabel("Note-examen :");
        lblNoteexamen.setForeground(Color.BLUE);
        lblNoteexamen.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNoteexamen.setBounds(5, 643, 137, 21);
        contentPane.add(lblNoteexamen);

        JLabel lblcodeunite2 = new JLabel("Code_unite :");
        lblcodeunite2.setForeground(Color.BLUE);
        lblcodeunite2.setFont(new Font("Georgia", Font.BOLD, 14));
        lblcodeunite2.setBounds(5, 501, 114, 30);
        contentPane.add(lblcodeunite2);

        textFieldMatEtu = new JTextField();
        textFieldMatEtu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldMatEtu.setBounds(121, 460, 137, 34);
        contentPane.add(textFieldMatEtu);
        textFieldMatEtu.setColumns(10);

        textFieldCodeUnite4 = new JTextField();
        textFieldCodeUnite4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldCodeUnite4.setBounds(121, 501, 137, 36);
        contentPane.add(textFieldCodeUnite4);
        textFieldCodeUnite4.setColumns(10);

        textFieldNoteCC = new JTextField();
        textFieldNoteCC.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNoteCC.setBounds(121, 544, 137, 37);
        contentPane.add(textFieldNoteCC);
        textFieldNoteCC.setColumns(10);

        textFieldNoteTP = new JTextField();
        textFieldNoteTP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNoteTP.setBounds(121, 591, 137, 34);
        contentPane.add(textFieldNoteTP);
        textFieldNoteTP.setColumns(10);

        textFieldNoteExam = new JTextField();
        textFieldNoteExam.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNoteExam.setText("");
        textFieldNoteExam.setBounds(121, 634, 137, 37);
        contentPane.add(textFieldNoteExam);
        textFieldNoteExam.setColumns(10);

        JLabel lblNewLabel = new JLabel("Age:");
        lblNewLabel.setForeground(Color.PINK);
        lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        lblNewLabel.setBounds(397, 234, 56, 32);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(519, 235, 137, 37);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Id_section:");
        lblNewLabel_4.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNewLabel_4.setForeground(Color.ORANGE);
        lblNewLabel_4.setBounds(512, 469, 114, 28);
        contentPane.add(lblNewLabel_4);

        textField_1 = new JTextField();
        textField_1.setBounds(619, 465, 114, 39);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Nom_section:");
        lblNewLabel_5.setForeground(Color.ORANGE);
        lblNewLabel_5.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNewLabel_5.setBounds(512, 523, 137, 14);
        contentPane.add(lblNewLabel_5);

        textField_2 = new JTextField();
        textField_2.setBounds(619, 513, 114, 36);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Niveau:");
        lblNewLabel_6.setForeground(Color.ORANGE);
        lblNewLabel_6.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNewLabel_6.setBounds(512, 560, 99, 37);
        contentPane.add(lblNewLabel_6);

        textField_3 = new JTextField();
        textField_3.setBounds(619, 561, 114, 37);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("Sp\u00E9cialit\u00E9:");
        lblNewLabel_7.setForeground(Color.ORANGE);
        lblNewLabel_7.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNewLabel_7.setBounds(512, 608, 83, 25);
        contentPane.add(lblNewLabel_7);

        textField_4 = new JTextField();
        textField_4.setBounds(619, 609, 114, 37);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        JButton Sect = new JButton("Apply");
        Sect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        Sect.setFont(new Font("Tahoma", Font.BOLD, 14));
        Sect.setForeground(Color.ORANGE);
        Sect.setBounds(619, 657, 114, 44);
        Sect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String st = "INSERT INTO Section VALUES ("+textField_1.getText()+",'"+textField_2.getText()+"','"+textField_3.getText()+"','"+textField_4.getText()+"')";
                try {
                    stmt = connection.prepareStatement(st);
                    rs=stmt.executeQuery(st);
                    JOptionPane.showMessageDialog (null, "Insertion completed");
                } catch (SQLException exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog (null, "Insertion Failed");
                }
            }
        });
        contentPane.add(Sect);

        JLabel lblInsererUnTuple = new JLabel("Inserer un tuple dans la table Etudiant:  ");
        lblInsererUnTuple.setForeground(new Color(51, 255, 204));
        lblInsererUnTuple.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInsererUnTuple.setBounds(5, 63, 457, 34);
        contentPane.add(lblInsererUnTuple);

        JLabel lblInsererUnTuple_2 = new JLabel("Inserer un tuple dans la table EtudiantUnite :");
        lblInsererUnTuple_2.setForeground(new Color(51, 255, 204));
        lblInsererUnTuple_2.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInsererUnTuple_2.setBounds(5, 424, 457, 34);
        contentPane.add(lblInsererUnTuple_2);

        JLabel lblInsererUnTuple_2_1 = new JLabel("Inserer un tuple dans la table Section :");
        lblInsererUnTuple_2_1.setForeground(new Color(51, 255, 204));
        lblInsererUnTuple_2_1.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInsererUnTuple_2_1.setBounds(512, 424, 357, 34);
        contentPane.add(lblInsererUnTuple_2_1);

        JLabel lblInsererUnTuple_2_1_1 = new JLabel("Inserer un tuple dans la table Enseignant : ");
        lblInsererUnTuple_2_1_1.setForeground(new Color(51, 255, 204));
        lblInsererUnTuple_2_1_1.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInsererUnTuple_2_1_1.setBounds(397, 63, 457, 34);
        contentPane.add(lblInsererUnTuple_2_1_1);

        JLabel lblInsererUnTuple_2_1_1_1 = new JLabel("Inserer un tuple dans");
        lblInsererUnTuple_2_1_1_1.setForeground(new Color(51, 255, 204));
        lblInsererUnTuple_2_1_1_1.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInsererUnTuple_2_1_1_1.setBounds(801, 63, 457, 34);
        contentPane.add(lblInsererUnTuple_2_1_1_1);

        JLabel lblWindowInsertionIn = new JLabel("Window Insertion In table ...User BDDAdmin !");
        lblWindowInsertionIn.setForeground(SystemColor.activeCaption);
        lblWindowInsertionIn.setFont(new Font("Engravers MT", Font.BOLD | Font.ITALIC, 20));
        lblWindowInsertionIn.setBounds(131, 11, 723, 52);
        contentPane.add(lblWindowInsertionIn);

        JLabel lblInsererUnTuple_2_1_1_1_1 = new JLabel("la table Unite : ");
        lblInsererUnTuple_2_1_1_1_1.setForeground(new Color(51, 255, 204));
        lblInsererUnTuple_2_1_1_1_1.setFont(new Font("Source Sans Pro Black", Font.BOLD, 19));
        lblInsererUnTuple_2_1_1_1_1.setBounds(801, 89, 457, 34);
        contentPane.add(lblInsererUnTuple_2_1_1_1_1);

    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
