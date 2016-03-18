/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import entity.Product;

/**
 *
 * @author andrestntx
 */
public class ProductVO extends BaseVO {
    private int id;
    private int created_by;
    private double price;
    private String name;
    private String description;

    public ProductVO(int created_by, double price, String name, String description) {
        this.created_by = created_by;
        this.price = price;
        this.name = name;
        this.description = description;
    }
    
    public ProductVO(){
    
    }
    
    public Product toEntity(){
        Product entity = new Product();        
        entity.setName(this.getName());
        entity.setCreated_by(this.getCreated_by());
        entity.setDescription(this.getDescription());
        entity.setPrice(this.getPrice());
                
        return entity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
