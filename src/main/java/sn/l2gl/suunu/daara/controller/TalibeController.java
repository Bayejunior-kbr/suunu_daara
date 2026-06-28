package sn.l2gl.suunu.daara.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sn.l2gl.suunu.daara.exception.SuppressionImpossibleException;
import sn.l2gl.suunu.daara.exception.TalibeDejaExistantException;
import sn.l2gl.suunu.daara.exception.TalibeIntrouvableException;
import sn.l2gl.suunu.daara.model.dao.TalibeDao;
import sn.l2gl.suunu.daara.model.models.Talibes;
import sn.l2gl.suunu.daara.util.CsvExporter;
import sn.l2gl.suunu.daara.view.TalibeView;

import javax.swing.*;
import java.util.ArrayList;

import java.io.File;
import java.util.List;

import java.io.IOException;
import javax.swing.table.DefaultTableModel;



@Getter
@Setter
@AllArgsConstructor
public class TalibeController {

    private final TalibeDao dao= new TalibeDao(); // import  talibesDao()

    private final TalibeView vue; //import talibesViews

    private Talibes enCours; //vas me permettre de teste entre modif ou inserer


    private void rafraichir(){
        vue.resetForm();
        vue.afficherTalibes(dao.listerTous());
    }


    public void enregistre() {
        try {
            Talibes t = vue.getTalibeForm();
            if (enCours == null) {
                dao.inserer(t);
            } else {
                t.setMatricule(enCours.getMatricule());
                dao.modifier(t);
            }
            enCours = null; // IMPORTANT
            rafraichir();
            vue.afficherMessage("Enregistré avec succès");
        } catch (TalibeDejaExistantException e) {
            vue.afficherErreur(e.getMessage());
        }
    }


    public void supprimer(){
        try{
            String matricule = vue.getMatricule();
            dao.supprimer(matricule);
            rafraichir();
            vue.afficherMessage("Supprimes avec succes");

        }catch(SuppressionImpossibleException e){
            vue.afficherErreur(e.getMessage());
        }
    }

    public void recherche() {
        try {
            String matricule = vue.getMatricule();

            Talibes t = dao.trouver(matricule)
                    .orElseThrow(() -> new TalibeIntrouvableException("Introuvable"));

            vue.remplirForm(t);

        } catch (TalibeIntrouvableException e) {
            vue.afficherErreur(e.getMessage());
        }
    }

    public void exporter() {
        try {
            List<Talibes> liste = dao.listerTous();
            List<String[]> data = new ArrayList<>();
            for (Talibes t : liste) {
                data.add(new String[]{
                        t.getMatricule()," |",
                        t.getNom()," |",
                        t.getPrenom()," |",
                        String.valueOf(t.getDateNaissance())," |",
                        t.getNomTuteur()," |",
                        t.getTelephoneTuteur()," |" +
                        ""
                        // t.getTelephoneTuteur(), (t.getClasse() != null ? t.getClasse().getLibelle() : "")
                });
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Enregistrer le fichier CSV");
            int baye = fileChooser.showSaveDialog(null);
            if (baye == JFileChooser.APPROVE_OPTION) {
                File fichier = fileChooser.getSelectedFile();
                if (!fichier.getName().toLowerCase().endsWith(".csv")) {
                    fichier = new File(fichier.getAbsolutePath() + ".csv");
                }

                CsvExporter.exporter(
                        //new File("talibes.csv"),
                        fichier,
                        new String[]{"Matricule","| ", "Nom"," |", "Prenom"," |", "Date Naissance"," ", "Nom Tuteur"," |", "Telephone Tuteur"," |"},
                        data
                );
                vue.afficherMessage("Export réussi");
            }
        } catch (IOException e) {
            vue.afficherErreur("Erreur export CSV");
        }
    }

    public TalibeController(TalibeView vue){
        this.vue = vue;
        vue.getBtnRecherche().addActionListener(e->recherche());
        vue.getBtnEnregistrer().addActionListener(e->enregistre());
        vue.getBtnSupprimer().addActionListener(e->supprimer());
        vue.getBoutonExporter().addActionListener(e->exporter());
        rafraichir(); // IMPORTANT
    }
}
