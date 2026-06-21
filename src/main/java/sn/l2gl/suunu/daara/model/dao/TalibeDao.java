package sn.l2gl.suunu.daara.model.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sn.l2gl.suunu.daara.*;
import sn.l2gl.suunu.daara.model.models.Talibes;
import sn.l2gl.suunu.daara.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class TalibeDao implements Dao<Talibes ,String> {

    /* public void afficherTalibes(){
         Session session= HibernateUtil.getSessionFactory().openSession();
         List<Talibes>talibes=session.createQuery("FROM Talibes",Talibes.class).list();
         for(Talibes c:talibes){
             System.out.println(
                     c.getMatricule()+" | "+
                             c.getPrenom()+" | "+c.getNom()+" | "+c.getDateNaissance()+" | "+c.getNomTuteur()+" | "+c.getTelephoneTuteur()+" | "+c.getClasse()
             );
         }
         session.close();

     }*/
    @Override
    public List<Talibes> listerTous(){ // vas recupere tous les talibes pour les afficher
        Session session =HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("FROM Talibes",Talibes.class).list();
    }

    @Override
    public Talibes inserer(Talibes entity){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            session.persist(entity); // ou save
            tx.commit();
            System.out.println("Tlibes insere avec succes");
            return entity;
        }finally {
            session.close();
        }
    }

    @Override
    public  Optional<Talibes> modifier(Talibes entity){
        Session session=HibernateUtil.getSessionFactory().openSession();

        Transaction tx=null;
        try {
            tx=session.beginTransaction();
            Talibes talibes = session.get(Talibes.class, entity.getMatricule());
            if (talibes != null) {
                talibes.setMatricule(entity.getMatricule());
                talibes.setPrenom(entity.getPrenom());
                talibes.setNom(entity.getNom());
                talibes.setDateNaissance(entity.getDateNaissance());
                talibes.setNomTuteur(entity.getNomTuteur());
                talibes.setTelephoneTuteur(entity.getTelephoneTuteur());
                talibes.setClasse(entity.getClasse());
                session.update(talibes);
            } else {
                return Optional.empty();
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
                tx.rollback();
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
