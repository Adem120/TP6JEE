package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import entities.Categorie;
import entities.Produit;
import util.JpaUtil;
public class CategorieDaoImpl implements ICategorieDao {
int id;
    private EntityManager entityManager=JpaUtil.getEntityManager("TP6_JEE");
    @Override
    public Categorie save(Categorie cat ) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(cat);
        tx.commit();
        return cat;
    }
    @Override
    public Categorie getCategorie(Long id) {
        return entityManager.find(Categorie.class, id);
    }
    @Override
    public Categorie updateCategorie(Categorie cat) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(cat);
        tx.commit();
        return cat;
    }
    @Override
    public void deleteCategorie(Long id) {
        Categorie categorie = entityManager.find(Categorie.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(categorie);
        entityManager.getTransaction().commit();
    }
    @Override
    public List<Categorie> getAllCategories() {
        List<Categorie> cats =

                entityManager.createQuery("select c from Categorie c").getResultList();
        return cats;
    }
    @Override
    public List<Produit> produitsParcat(Long mc) {
        List<Produit> prods =


                entityManager.createQuery("select p from Produit p where p.categorie.idCat="+mc).getResultList();

        return prods;
    }
}