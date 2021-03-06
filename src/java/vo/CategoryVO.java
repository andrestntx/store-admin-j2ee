/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import entity.Category;
import java.util.List;

/**
 *
 * @author andrestntx
 */
public class CategoryVO extends BaseVO {
    private Long id;
    private String name;
    private String description;
    private List<ProductVO> productsVO;

    public CategoryVO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryVO() {
    }
    
    public Category toEntity(){
        Category entity = new Category();        
        entity.setName(this.getName());
        entity.setId(this.getId());
        entity.setDescription(this.getDescription());
               
        return entity;
    }
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public List<ProductVO> getProductsVO() {
        return productsVO;
    }

    public void setProductsVO(List<ProductVO> productsVO) {
        this.productsVO = productsVO;
    }
    
    

}
