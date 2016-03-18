/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import entity.Category;

/**
 *
 * @author andrestntx
 */
public class CategoryVO extends BaseVO {
    private int id;
    private String name;

    public CategoryVO(String name) {
        this.name = name;
    }

    public CategoryVO() {
    }
    
    public Category toEntity(){
        Category entity = new Category();        
        entity.setName(this.getName());
                
        return entity;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
