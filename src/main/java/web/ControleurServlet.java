package web;

import dao.CategorieDaoImpl;
import dao.ICategorieDao;
import dao.IProduitDao;
import dao.ProduitDaoImpl;
import entities.Categorie;
import entities.Produit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
    IProduitDao metier;
    ICategorieDao metierCat;
    CategorieModele model2= new CategorieModele();


    @Override
    public void init() throws ServletException {
        metier = new ProduitDaoImpl();
        metierCat = new CategorieDaoImpl();
        List<Categorie> cats = metierCat.getAllCategories();
        model2.setCategories(cats);
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        String path=request.getServletPath();
        if (path.equals("/index.do"))
        {
            request.getRequestDispatcher("produits.jsp").forward(request,response);
        }
        else if (path.equals("/index2.do"))
        {


            request.setAttribute("catModel", model2);

            request.getRequestDispatcher("produitcat.jsp").forward(request,response);
        }
        else if (path.equals("/chercher.do"))
        {
            String motCle=request.getParameter("motCle");
            ProduitModele model= new ProduitModele();
            model.setMotCle(motCle);
            List<Produit> prods = metier.produitsParMC(motCle);

            model.setProduits(prods);
            request.setAttribute("model", model);

            request.getRequestDispatcher("produits.jsp").forward(request,response);
        }  else if (path.equals("/cherchercat.do"))
        {


            String cat=request.getParameter("categorie");
            Long id=Long.parseLong(cat);
            ProduitModele model= new ProduitModele();
            request.setAttribute("catModel", model2);

            model.setMotCle(cat);
            List<Produit> prods = metierCat.produitsParcat(id);
            model.setProduits(prods);

            request.setAttribute("model", model);
            request.setAttribute("cat", cat);
            request.getRequestDispatcher("produitcat.jsp").forward(request,response);
        }
        else if (path.equals("/saisie.do") )

        {
            List<Categorie> cats = metierCat.getAllCategories();
            CategorieModele model= new CategorieModele();
            model.setCategories(cats);
            request.setAttribute("catModel", model);

            request.getRequestDispatcher("saisieProduit.jsp").forward(request,response);
        }
        else if (path.equals("/save.do") && request.getMethod().equals("POST"))

        {
            String nom=request.getParameter("nom");
            Long categorieId=Long.parseLong(request.getParameter("categorie"));
            double prix = Double.parseDouble(request.getParameter("prix"));
            Categorie cat = metierCat.getCategorie(categorieId);
            Produit p = metier.save(new Produit(nom,prix,cat));
            request.setAttribute("produit", p);
            response.sendRedirect("chercher.do?motCle="); }
        else if (path.equals("/supprimer.do"))
        {
            Long id= Long.parseLong(request.getParameter("id"));
            metier.deleteProduit(id);
            response.sendRedirect("chercher.do?motCle=");
        }
        else if (path.equals("/editer.do") )

        {
            Long id= Long.parseLong(request.getParameter("id"));
            Produit p = metier.getProduit(id);
            request.setAttribute("produit", p);
            List<Categorie> cats = metierCat.getAllCategories();
            CategorieModele model= new CategorieModele();
            model.setCategories(cats);
            request.setAttribute("catModel", model);
            request.getRequestDispatcher("editerProduit.jsp").forward(request,response);
        }
        else if (path.equals("/update.do") )

        { Long id = Long.parseLong(request.getParameter("id"));
            String nom=request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Long categorieId=Long.parseLong(request.getParameter("categorie"));
            Produit p = new Produit();
            p.setIdProduit(id);
            p.setNomProduit(nom);
            p.setPrix(prix);
            Categorie cat = metierCat.getCategorie(categorieId);
            p.setCategorie(cat);
            metier.updateProduit(p);
            response.sendRedirect("chercher.do?motCle=");
        }

    }
    @Override
    protected void doPost(HttpServletRequest request,

                          HttpServletResponse response) throws

            ServletException, IOException {
        doGet(request,response);
    }
}