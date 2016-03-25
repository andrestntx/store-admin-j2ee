/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDAO;
import dao.DAOFactory;
import entity.Category;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import vo.CategoryVO;

/**
 *
 * @author Felipe Iz
 */
public class CategoryService {
    
    private static CategoryService service;
    
    private CategoryService() {}
    
    public static CategoryService getService() {
        if(service == null) {
            service = new CategoryService(); 
        }
        
        return service;
    }
    
    protected List<CategoryVO> toVO(List<Category> entities) {
        List<CategoryVO> vos = new ArrayList<>();
        for (Category entity : entities) {
            vos.add(entity.toVO());
        }
        return vos;
    }

    public CategoryVO getCategory(Long categoryId, EntityManager em) {
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO(em);
        Category category = categoryDAO.find(categoryId);
        
        if(category != null){
            return category.toVO();
        }
       
        return null;
    }

    public CategoryVO newCategory(CategoryVO categoryVO, EntityManager em){ 
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO(em);        
        Category category = categoryVO.toEntity();
        categoryDAO.save(category);
        return category.toVO();
    }
    
    public boolean newCategories(Collection<CategoryVO> categories, EntityManager em){ 
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO(em);
        for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
            CategoryVO category = (CategoryVO)iterator.next();
            categoryDAO.save(category.toEntity());
        }
        return true;
    }
    
    public CategoryVO updateCategory(CategoryVO categoryVO, EntityManager em){
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO(em);
        Category category = categoryDAO.update(categoryVO.toEntity());
        
        if(category != null){
            return category.toVO();
        }
       
        return null;
    }
    
    public boolean deleteCategory(Long id, EntityManager em){
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO(em);
        return categoryDAO.delete(id);
    }
    
    public List<CategoryVO> allCategories(EntityManager em) {
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO(em);
        return this.toVO(categoryDAO.getAll());
    }
}
