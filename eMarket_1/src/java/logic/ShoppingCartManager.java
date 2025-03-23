/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.io.Serializable;
import java.util.ArrayList;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import model.Product;
import model.ShoppingCartItem;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 *
 * @author laurentsun
 */

@Named
@SessionScoped
public class ShoppingCartManager implements Serializable {
    
    private ArrayList<ShoppingCartItem> shoppingCart;
    private Integer prodToAdd;

    @Inject
    private CatalogManager catalogManager;

    public ShoppingCartManager() {
        this.shoppingCart = new ArrayList<>();
    }

    @PostConstruct
    public void initCart() {
        System.out.println("Initialisation du panier...");
        this.shoppingCart = new ArrayList<>();
    }

    public ArrayList<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<ShoppingCartItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Integer getProdToAdd() {
        return prodToAdd;
    }

    public void setProdToAdd(Integer prodToAdd) {
        this.prodToAdd = prodToAdd;
    }

    

    public String addToCart() {
        // Trouver le produit via son ID
        Product product = catalogManager.getProductById(prodToAdd);
        if (product == null) {
            return "tocatalog"; // Gérer l'erreur si besoin
        }

        // Vérifier si le produit est déjà dans le panier
        ShoppingCartItem existingItem = shoppingCart.stream()
            .filter(item -> item.getProduct().getId().equals(prodToAdd))
            .findFirst()
            .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            ShoppingCartItem newItem = new ShoppingCartItem();
            newItem.setId(prodToAdd);
            newItem.setProduct(product);
            newItem.setQuantity(1);
            shoppingCart.add(newItem);
        }

        FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(
            FacesMessage.SEVERITY_INFO,
            "Succès",
            "Le produit a été ajouté au panier"
        )
    );

    return "tocatalog";
    }

    public void removeFromCart(Integer id) {
        shoppingCart.removeIf(item -> item.getId().equals(id));
    }
    
    
    
    
}
