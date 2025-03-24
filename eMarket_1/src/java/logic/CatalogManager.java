/*
/eMarket_1/src/java/logic/CatalogManager.java
 */
package logic;

import java.io.Serializable;
import java.util.ArrayList;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.ejb.EJB;
import model.Product;
import facade.ProductFacade;

@Named
@ApplicationScoped
public class CatalogManager implements Serializable {
    private ArrayList<Product> products;
    private String newProductName;
    private Double newProductPrice;

    @EJB
    private ProductFacade productFacade;
    
    public CatalogManager() {
        products = new ArrayList<>();
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
        Product entity = new Product(products.size()+1, this.newProductName, this.newProductPrice);
        products.add(entity) ; 
        productFacade.create(entity);
        return "tocatalog" ;
    }

    @PostConstruct
    public void initCatalog() {
        System.out.println("Initialisation du catalogue...");
        // Création des produits
        products.addAll(productFacade.findAll());
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
