package sn.l2gl.suunu.daara.view;


import sn.l2gl.suunu.daara.model.models.Talibes;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class TalibeView extends JPanel {
    //Parce que  View n’est pas une fenêtre, c’est un conteneur de composants.

    private JTextField textMatricule;
    private JTextField textPrenom;
    private JTextField textNom;
    private JTextField textDateNaissance;
    private JTextField textNomTuteur;
    private JTextField textTelephoneTuteur;

    private JTable tableTalibe;

   // private JComboBox<String> classes;

    private JButton btnEnregistrer;
    private JButton btnSupprimer;
    private JButton btnRecherche;
    private JButton btnExporter;


    public void resetForm() {
        textMatricule.setText("");
        textPrenom.setText("");
        textNom.setText("");
        textDateNaissance.setText("");
        textNomTuteur.setText("");
        textTelephoneTuteur.setText("");
       // classes.setSelectedIndex(0);
    }

    public void afficherMessage(String message){
        JOptionPane.showMessageDialog(this,message);
    }

    public void afficherErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public String getMatricule() {
        return textMatricule.getText();
    }

    public void afficherTalibes(List<Talibes> liste) {
        String[] colonnes = {"Matricule", "Prénom", "Nom", "Date Naissance", "Nom Tuteur", "Téléphone"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        for (Talibes t : liste) {
            model.addRow(new Object[]{
                    t.getMatricule(),
                    t.getPrenom(),
                    t.getNom(),
                    t.getDateNaissance(),
                    t.getNomTuteur(),
                    t.getTelephoneTuteur()
            });
        }
        tableTalibe.setModel(model);
    }

    public TalibeView(){
        setLayout((new BorderLayout()));
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        formPanel.add(new JLabel("Matricule :"));
        textMatricule=new JTextField();
        formPanel.add(textMatricule);

        formPanel.add(new JLabel("Prenom :"));
        textPrenom=new JTextField();
        formPanel.add(textPrenom);

        formPanel.add(new JLabel("Nom :"));
        textNom=new JTextField();
        formPanel.add(textNom);

        formPanel.add(new JLabel("Date Naissance :(yyyy-MM-dd)"));
        textDateNaissance=new JTextField();
        formPanel.add(textDateNaissance);

        formPanel.add(new JLabel("Nom Tuteur:"));
        textNomTuteur = new JTextField();
        formPanel.add(textNomTuteur);

        formPanel.add(new JLabel("Telephone Tuteur"));
        textTelephoneTuteur=new JTextField();
        formPanel.add(textTelephoneTuteur);

       /* add(new JLabel("Classe"));
        classes=new JComboBox<>(new String[]{"Classe A","Classe B"});
        add(classes);*/
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnEnregistrer= new JButton("Enregistrer");
        btnSupprimer=new JButton("Supprimer");
        btnRecherche=new JButton("cherche");
        btnExporter = new JButton("Exporter CSV");


        btnPanel.add(btnEnregistrer);
        btnPanel.add(btnRecherche);
        btnPanel.add(btnSupprimer);
        btnPanel.add(btnExporter);

        // ── Panneau tableau (bas) ──
        tableTalibe = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableTalibe);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        // ── Assemblage ──
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);


    }


    public JButton getBtnEnregistrer(){return btnEnregistrer;}
    public JButton getBtnSupprimer(){return btnSupprimer;}
    public JButton getBtnRecherche() {return btnRecherche;}
    public JButton getBoutonExporter() {
        return btnExporter;
    }


    public Talibes getTalibeForm() {
        Talibes t = new Talibes();

        t.setMatricule(textMatricule.getText());
        t.setPrenom(textPrenom.getText());
        t.setNom(textNom.getText());
        LocalDate date;
        try {
            date = LocalDate.parse(textDateNaissance.getText());
        } catch (Exception e) {
            throw new RuntimeException("Format date invalide (AAAA-MM-JJ)");
        }
        t.setDateNaissance(date);

        t.setNomTuteur(textNomTuteur.getText());
        t.setTelephoneTuteur(textTelephoneTuteur.getText());
       /* Classe c = (Classe) classes.getSelectedItem();
        t.setClasse(c);*/
        return t;
    }

    public void remplirForm(Talibes t) {

        textMatricule.setText(t.getMatricule());
        textPrenom.setText(t.getPrenom());
        textNom.setText(t.getNom());

        textDateNaissance.setText(String.valueOf(t.getDateNaissance()));

        textNomTuteur.setText(t.getNomTuteur());
        textTelephoneTuteur.setText(t.getTelephoneTuteur());

      /*  if (t.getClasse() != null) {
            classes.setSelectedItem(t.getClasse().getLibelle());
        }*/
    }



}
/*
package sn.l2gl.suunu.daara.view;

import sn.l2gl.suunu.daara.model.models.Talibes;
import sn.l2gl.suunu.daara.model.models.Classe;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.LocalDate;

public class TalibeView extends JPanel {

    private JTextField textMatricule;
    private JTextField textPrenom;
    private JTextField textNom;
    private JTextField textDateNaissance;
    private JTextField textNomTuteur;
    private JTextField textTelephoneTuteur;

    private JComboBox<String> classes;

    private JButton btnEnregistrer;
    private JButton btnSupprimer;
    private JButton btnRecherche;
    private JButton btnExporter;

    // ── Palette ──────────────────────────────────────────────
    private static final Color BG         = new Color(0x1C2B3A);
    private static final Color CARD_BG    = new Color(0x243447);
    private static final Color ACCENT     = new Color(0x1A6EFF);
    private static final Color ACCENT2    = new Color(0x0D4DB5);
    private static final Color TEXT_WHITE = Color.WHITE;
    private static final Color TEXT_GRAY  = new Color(0xA8B8CC);
    private static final Color FIELD_BG   = new Color(0x1A2738);
    private static final Color FIELD_BORDER = new Color(0x2E4460);
    private static final Color BTN_RED    = new Color(0xD93025);
    private static final Color BTN_GREEN  = new Color(0x1E8A4A);
    private static final Color BTN_GRAY   = new Color(0x3A4A5C);

    public TalibeView() {
        setLayout(new BorderLayout());
        setBackground(BG);

        // ── HEADER ─────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ACCENT2);
        header.setBorder(new EmptyBorder(14, 24, 14, 24));

        JLabel title = new JLabel("📚  Gestion Daara — Talibés");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(TEXT_WHITE);
        header.add(title, BorderLayout.WEST);

        JLabel subtitle = new JLabel("Saisie & gestion des élèves");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(new Color(0xBBCCDD));
        header.add(subtitle, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // ── FORM CARD ──────────────────────────────────────
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(CARD_BG);
        card.setBorder(new CompoundBorder(
                new EmptyBorder(20, 24, 10, 24),
                new CompoundBorder(
                        BorderFactory.createLineBorder(FIELD_BORDER, 1),
                        new EmptyBorder(20, 20, 20, 20)
                )
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Champs : [label, field, label, field] par ligne
        textMatricule      = styledField("Ex: TAL-2025-001");
        textPrenom         = styledField("Prénom du talibé");
        textNom            = styledField("Nom de famille");
        textDateNaissance  = styledField("AAAA-MM-JJ");
        textNomTuteur      = styledField("Nom complet du tuteur");
        textTelephoneTuteur= styledField("Ex: 77 000 00 00");
        classes            = styledCombo(new String[]{"Classe A", "Classe B"});

        // Ligne 0
        addRow(card, gbc, 0, "Matricule :", textMatricule, "Prénom :", textPrenom);
        // Ligne 1
        addRow(card, gbc, 1, "Nom :", textNom, "Date Naissance :", textDateNaissance);
        // Ligne 2
        addRow(card, gbc, 2, "Nom Tuteur:", textNomTuteur, "Telephone Tuteur", textTelephoneTuteur);
        // Ligne 3 — Classe
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        card.add(styledLabel("Classe"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        card.add(classes, gbc);
        gbc.gridwidth = 1;

        // ── BOUTONS ────────────────────────────────────────
        JPanel btnPanel = new JPanel(new GridLayout(1, 4, 12, 0));
        btnPanel.setBackground(BG);
        btnPanel.setBorder(new EmptyBorder(16, 24, 20, 24));

        btnEnregistrer = styledButton("✔  Enregistrer", ACCENT);
        btnRecherche   = styledButton("🔍  Cherche",    BTN_GRAY);
        btnSupprimer   = styledButton("✖  Supprimer",   BTN_RED);
        btnExporter    = styledButton("📤  Exporter CSV", BTN_GREEN);

        btnPanel.add(btnEnregistrer);
        btnPanel.add(btnRecherche);
        btnPanel.add(btnSupprimer);
        btnPanel.add(btnExporter);

        add(card, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    // ── Helpers UI ────────────────────────────────────────────

    private void addRow(JPanel p, GridBagConstraints g, int row,
                        String lbl1, JComponent f1,
                        String lbl2, JComponent f2) {
        g.gridy = row;
        g.weightx = 0;
        g.gridx = 0; p.add(styledLabel(lbl1), g);
        g.weightx = 1;
        g.gridx = 1; p.add(f1, g);
        g.weightx = 0;
        g.gridx = 2; p.add(styledLabel(lbl2), g);
        g.weightx = 1;
        g.gridx = 3; p.add(f2, g);
    }

    private JLabel styledLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(TEXT_GRAY);
        return l;
    }

    private JTextField styledField(String placeholder) {
        JTextField f = new JTextField(14) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !isFocusOwner()) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(new Color(0x556677));
                    g2.setFont(getFont().deriveFont(Font.ITALIC, 11f));
                    g2.drawString(placeholder, 8, getHeight() / 2 + 5);
                }
            }
        };
        f.setBackground(FIELD_BG);
        f.setForeground(TEXT_WHITE);
        f.setCaretColor(TEXT_WHITE);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(FIELD_BORDER, 1),
                new EmptyBorder(6, 10, 6, 10)
        ));
        return f;
    }

    private JComboBox<String> styledCombo(String[] items) {
        JComboBox<String> cb = new JComboBox<>(items);
        cb.setBackground(FIELD_BG);
        cb.setForeground(TEXT_WHITE);
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cb.setBorder(BorderFactory.createLineBorder(FIELD_BORDER, 1));
        return cb;
    }

    private JButton styledButton(String text, Color bg) {
        JButton b = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? bg.darker() :
                        getModel().isRollover() ? bg.brighter() : bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        b.setForeground(TEXT_WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(160, 38));
        return b;
    }

    // ── Méthodes inchangées ───────────────────────────────────

    public void resetForm() {
        textMatricule.setText("");
        textPrenom.setText("");
        textNom.setText("");
        textDateNaissance.setText("");
        textNomTuteur.setText("");
        textTelephoneTuteur.setText("");
        classes.setSelectedIndex(0);
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void afficherErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public String getMatricule() {
        return textMatricule.getText();
    }

    public JButton getBtnEnregistrer()  { return btnEnregistrer; }
    public JButton getBtnSupprimer()    { return btnSupprimer; }
    public JButton getBtnRecherche()    { return btnRecherche; }
    public JButton getBoutonExporter()  { return btnExporter; }

    public Talibes getTalibeForm() {
        Talibes t = new Talibes();
        t.setMatricule(textMatricule.getText());
        t.setPrenom(textPrenom.getText());
        t.setNom(textNom.getText());
        t.setDateNaissance(LocalDate.parse(textDateNaissance.getText()));
        t.setNomTuteur(textNomTuteur.getText());
        t.setTelephoneTuteur(textTelephoneTuteur.getText());
        Classe c = new Classe();
        c.setLibelle((String) classes.getSelectedItem());
        t.setClasse(c);
        return t;
    }

    public void remplirForm(Talibes t) {
        textMatricule.setText(t.getMatricule());
        textPrenom.setText(t.getPrenom());
        textNom.setText(t.getNom());
        textDateNaissance.setText(String.valueOf(t.getDateNaissance()));
        textNomTuteur.setText(t.getNomTuteur());
        textTelephoneTuteur.setText(t.getTelephoneTuteur());
        if (t.getClasse() != null) {
            classes.setSelectedItem(t.getClasse().getLibelle());
        }
    }
}

*/