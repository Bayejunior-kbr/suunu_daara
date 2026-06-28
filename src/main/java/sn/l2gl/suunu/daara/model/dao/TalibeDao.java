package sn.l2gl.suunu.daara.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.suunu.daara.exception.TalibeDejaExistantException;
import sn.l2gl.suunu.daara.model.models.Talibes;
import sn.l2gl.suunu.daara.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class TalibeDao implements Dao<Talibes ,String> {

    @Override
    public List<Talibes> listerTous(){ // vas recupere tous les talibes pour les afficher
        Session session =HibernateUtil.getSessionFactory().openSession(); // ouverture de session
        return session.createQuery("FROM Talibes",Talibes.class).list(); //va enregistre dans une liste tout les element
    }

    @Override
    public Talibes inserer(Talibes entity){
        //verification de l'existance du mat
        List<Talibes> liste = listerTous();  //on recupere la liste des talibes
        for (Talibes t : liste) {
            if (t.getMatricule().equals(entity.getMatricule())) { // on compare les mat par equals pour savoir si elle existe deja ou pas
                throw new TalibeDejaExistantException("Matricule déjà existant !");
            }
        }

        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            session.persist(entity); // ou save pour enregistre
            tx.commit(); //push les modification
            System.out.println("Talibes insere avec succes");
            return entity;
        }finally {
            session.close(); // fermer la session
        }
    }

    @Override
    public  Optional<Talibes> modifier(Talibes entity){ //en modification en respectant le dao
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            Talibes talibes = session.get(Talibes.class, entity.getMatricule());
            if (talibes != null) {
                talibes.setPrenom(entity.getPrenom());
                talibes.setNom(entity.getNom());
                talibes.setDateNaissance(entity.getDateNaissance());
                talibes.setNomTuteur(entity.getNomTuteur());
                talibes.setTelephoneTuteur(entity.getTelephoneTuteur());
                //talibes.setClasse(entity.getClasse());
                session.update(talibes); //pour les modification

            } else {
                return Optional.empty(); //return la valeur ki existe ou vide
            }
            tx.commit();
            System.out.println("Modifer avec succes");
            return Optional.of(talibes);// Optional dit ke cette valeur peut exister ou pas et of vas dire ke je suis sur ke la valeur existe
        }finally {
            session.close();
        }
    }


    @Override
    public boolean supprimer(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        try {
            tx = session.beginTransaction();
            Talibes talibes = session.get(Talibes.class, id);
            if (talibes != null) {
                session.delete(talibes);
                tx.commit();
                System.out.println("Talibes suprimes avec succes");
                return true;
            } else {
                tx.rollback(); //contraire de commit
                System.out.println("Talibes nom troves");
                return false;
            }
        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();//afficher les dettaille complet d'une erreur
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public Optional<Talibes> trouver(String id){
        Session session=HibernateUtil.getSessionFactory().openSession();
        try {
            Talibes talibes = session.get(Talibes.class, id);
            return Optional.ofNullable(talibes);// si la valeur est null je le met dedans sionne je retourne vide
        }finally {
            session.close();

        }

    }


}
