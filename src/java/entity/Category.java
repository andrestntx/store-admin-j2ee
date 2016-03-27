/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import vo.CategoryVO;

/**
 *
 * @author andrestntx
 */
@Entity
public class Category extends BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    
    @OneToMany (cascade=CascadeType.ALL)
    private List<Product> products;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public CategoryVO toVO(){
        CategoryVO vo = new CategoryVO();
        vo.setId(this.getId());
        vo.setName(this.getName());
        vo.setDescription(this.getDescription());
        
        /*if(this.getProducts() != null){
            List<ProductVO> productsVO = new ArrayList<>();
            for (Product product : this.getProducts()) {
                productsVO.add(product.toVO());
            }
            vo.setProductsVO(productsVO);
        }*/
        
        return vo;
    }

}
