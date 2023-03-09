package dao;

import entities.Produit;
;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProduitDaoImpl implements IProduitDao {
    private EntityManager entityManager= JpaUtil.getEntityManager("TP5_JEE");
    @Override
    public Produit save(Produit p) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(p);
        tx.commit();
        return p;
    }
    @Override
    public List<Produit> produitsParMC(String mc) {
        List<Produit> prods =


        entityManager.createQuery("select p from Produit p where p.nomProduit like :mc").setParameter("mc", "%"+mc+"%").getResultList();

        return prods;
    }
    @Override
    public Produit getProduit(Long id) {
        return entityManager.find(Produit.class, id);
    }
    @Override
    public Produit updateProduit(Produit p) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(p);
        tx.commit();
        return p;
    }
    @Override
    public void deleteProduit(Long id) {
        Produit produit = entityManager.find(Produit.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(produit);
        entityManager.getTransaction().commit();
    }
}