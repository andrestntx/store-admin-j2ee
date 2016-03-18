/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDAO;
import entity.Category;
import javax.persistence.EntityManager;
import vo.CategoryVO;

/**
 *
 * @author Felipe Iz
 */
public class CategoryService {
    public void persist(CategoryVO vo, EntityManager entityManager) {
        Category entity = new Category();
        entity.setId(vo.getId());
        entity.setName(vo.getName());
        
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
    }
}
