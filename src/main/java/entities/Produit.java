package entities;


import javax.persistence.*;
import java.io.Serializable;

    import entities.Categorie;
@Entity
@Table(name = "produits")
public class Produit implements Serializable {
    @Id
    @Column(name="ID_PRODUIT")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idProduit;
    @Column (name="NOM_PRODUIT")
    private String nomProduit;
    private double prix;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
@ManyToOne
private Categorie categorie;
    public Produit() {
        super();
    }

    public Produit(String nomProduit, double prix,Categorie cat) {

        super();
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.setCategorie(cat);
    }
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Long getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }
    public String getNomProduit() {
        return nomProduit;
    }
    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    @Override
    public String toString() {
        return "Produit [idProduit=" + idProduit + ", nomProduit=" +

                nomProduit + ", prix=" + prix + "]";
    }
}