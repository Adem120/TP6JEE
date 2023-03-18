package dao;

import entities.Categorie;
import entities.Produit;


import java.util.Date;
import java.util.List;
public class TestDao {
    public static void main(String[] args) {
        ProduitDaoImpl pdao = new ProduitDaoImpl();
        CategorieDaoImpl cdao = new CategorieDaoImpl();

        ICategorieDao metierCat;
        List<Produit> prod;
        metierCat = new CategorieDaoImpl();

        prod = metierCat.produitsParcat(8L);
        System.out.println(prod);

       /* Date d = new Date();
        Categorie c = new Categorie("machine", d);
        cdao.save(c);

        // pdao.save(p);
        for (Categorie cat : cdao.getAllCategories()) {
            System.out.println(cat.toString());
        }
        List<Categorie> cats = metierCat.getAllCategories();
        List<Produit> prods = pdao.produitsParMC("laptop");
        for (Produit p1 : prods)
            System.out.println(p1);



    }*/
    }
}