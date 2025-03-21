/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

    public CatalogManager() {
        products = new ArrayList<>();
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
