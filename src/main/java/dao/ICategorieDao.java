package dao;

import java.util.List;
import entities.Categorie;
import entities.Produit;

public interface ICategorieDao {
    public Categorie save(Categorie cat);
    public Categorie getCategorie(Long id);
    public Categorie updateCategorie(Categorie cat);
    public void deleteCategorie(Long id);
    public List<Categorie> getAllCategories();



    List<Produit> produitsParcat(Long mc);

}