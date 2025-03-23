/*
/eMarket_1/src/java/logic/CatalogManager.java
 */
package logic;

import java.io.Serializable;
import java.util.ArrayList;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import model.Product;

@Named
@ApplicationScoped
public class CatalogManager implements Serializable {
    private ArrayList<Product> products;

    // Variables pour le formulaire de création de produit
    private Integer newProductId;
    private String newProductName;
    private Double newProductPrice;
    
    public CatalogManager() {
        products = new ArrayList<>();
    }

    // Getters et setters pour les variables de création de produit
    public Integer getNewProductId() {
        return newProductId;
    }
    public void setNewProductId(Integer newProductId) {
        this.newProductId = newProductId;
    }

    public String getNewProductName() {
        return newProductName;
    }
    public void setNewProductName(String newProductName) {
        this.newProductName = newProductName;
    }

    public Double getNewProductPrice() {
        return newProductPrice;
    }
    public void setNewProductPrice(Double newProductPrice) {
        this.newProductPrice = newProductPrice;
    }

    public String createProduct() {
        Product product = new Product();
        product.setId(newProductId);
        product.setName(newProductName);
        product.setPrice(newProductPrice);
        products.add(product);

        // Réinitialiser les champs
        newProductId = null;
        newProductName = null;
        newProductPrice = null;

        return "tocatalog"; // Déclenche la navigation vers catalog.xhtml
    }

    @PostConstruct
    public void initCatalog() {
        System.out.println("Initialisation du catalogue...");
        // Création des produits
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("Ordinateur portable");
        p1.setPrice(999.99);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("Smartphone");
        p2.setPrice(699.99);

        Product p3 = new Product();
        p3.setId(3);
        p3.setName("Tablette");
        p3.setPrice(499.99);

        // Ajout des produits au catalogue
        products.add(p1);
        products.add(p2);
        products.add(p3);
        System.out.println("Catalogue initialisé avec " + products.size() + " produits");
    }

    public ArrayList<Product> getProducts() {
        System.out.println("Nombre de produits : " + products.size());
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Product getProductById(Integer id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
