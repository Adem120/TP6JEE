package dao;

import entities.Produit;

import java.util.List;
public class TestDao {
    public static void main(String[] args) {
        ProduitDaoImpl pdao= new ProduitDaoImpl();
        Produit prod=pdao.save( new Produit("tévé",3500));
        System.out.println(prod);
        List<Produit> prods =pdao.produitsParMC("tévé");
        for (Produit p : prods)
            System.out.println(p);

    }
}