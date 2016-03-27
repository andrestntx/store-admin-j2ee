/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import vo.ProductVO;

/**
 *
 * @author andrestntx
 */
@Entity
public class Product extends BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne (cascade=CascadeType.PERSIST)
    private Category category;
    
    private int created_by;
    
    private double price;
    private String name;
    private String description;
    
    public ProductVO toVO(){
        ProductVO vo = new ProductVO();
        vo.setId(this.getId());
        vo.setName(this.getName());
        vo.setPrice(this.getPrice());
        vo.setDescription(this.getDescription());
        vo.setCreated_by(this.getCreated_by());
        vo.setCategoryVO(this.getCategory().toVO());
        return vo;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    
    public Category getCategory() {
        return category;
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

    public void setCategory(Category category) {
        this.category = category;
    }
}
