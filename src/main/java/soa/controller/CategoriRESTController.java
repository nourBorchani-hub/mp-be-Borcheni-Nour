package soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soa.entities.Categorie;
import soa.entities.Produit;
import soa.repository.CategorieRepository;
import soa.repository.ProduitRepository;
@RestController // pour déclarer un service web de type REST
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")  //    http://localhost:8080/produits
public class CategoriRESTController {
	 @Autowired // pour l'injection de dépendances
	    private CategorieRepository categoryRepos;

	    //  Message d'accueil
	    //  http://localhost:8080/produits/index  (GET)
	    @GetMapping(value ="/index" )
	    public String accueil() {
	        return "BienVenue au service Web REST 'cats'.....";
	    }

	    //  Afficher la liste des produits
	    //  http://localhost:8080/produits/ (GET)

	    @GetMapping(
	            // spécifier le path de la méthode
	            value= "/",
	            // spécifier le format de retour en XML
	            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
	    )
	    public  List<Categorie> getAllCategories() {
	        return categoryRepos.findAll();

	    }

	    //  Afficher un produit en spécifiant son 'id'
	    //  http://localhost:8080/produits/{id} (GET)
	    @GetMapping(
	            // spécifier le path de la méthode qui englobe un paramètre
	            value= "/{id}" ,
	            // spécifier le format de retour en XML
	            produces = { MediaType.APPLICATION_JSON_VALUE }
	    )
	    public Categorie getCategorie(@PathVariable Long id) {
	    	Categorie p =categoryRepos.findById(id).get();
	        return p;
	    }

	    // Supprimer un produit par 'id' avec la méthode 'GET'
	    //  http://localhost:8080/produits/delete/{id}  (GET)
	    @GetMapping(
	            // spécifier le path de la méthode
	            value = "/delete/{id}")
	    public void deleteCategorie(@PathVariable Long id)
	    {
	    	categoryRepos.deleteById(id);
	    }

	    //  ajouter un produit avec la méthode "POST"
	    //  http://localhost:8080/produits/   (POST)
	    @PostMapping(
	            // spécifier le path de la méthode
	            value = "/"  ,
	            //spécifier le format de retour
	            produces = { MediaType.APPLICATION_JSON_VALUE }
	    )
	    public Categorie saveCategorie(@RequestBody Categorie p)
	    {
	        return categoryRepos.save(p);
	    }

	    //  modifier un produit avec la méthode "PUT"
	    //  http://localhost:8080/produits/   (PUT)
	    @PutMapping(
	            // spécifier le path de la méthode
	            value = "/"  ,
	            //spécifier le format de retour
	            produces = { MediaType.APPLICATION_JSON_VALUE  }
	    )
	    public Categorie updateCategorie(@RequestBody Categorie p)
	    {
	        return categoryRepos.save(p);
	    }

	    // Supprimer un produit  avec la méthode 'DELETE'
	    //  http://localhost:8080/produits/   (DELETE)
	


}
