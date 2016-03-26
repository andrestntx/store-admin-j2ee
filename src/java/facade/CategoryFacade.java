/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import service.CategoryService;
import service.ServiceFactory;
import vo.CategoryVO;

/**
 *
 * @author andrestntx
 */
public class CategoryFacade extends BaseFacade {
    
    public List<CategoryVO> allCategories(){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        CategoryService service = ServiceFactory.getCategoryService();
        List<CategoryVO> categories = new ArrayList<>();
        
        try {
            trans.begin();
            categories = service.allCategories(em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        } 

        return categories;
    }
    
    public CategoryVO newCategory(CategoryVO categoryVO){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        CategoryService service = ServiceFactory.getCategoryService();
        
        try {
            trans.begin();
            categoryVO = service.newCategory(categoryVO, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return categoryVO;
    }
    
    public CategoryVO updateCategory(CategoryVO categoryVO){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        CategoryService service = ServiceFactory.getCategoryService();
        
        try {
            trans.begin();
            categoryVO = service.updateCategory(categoryVO, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return categoryVO;
    }

    public CategoryVO getCategory(Long id) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        CategoryService service = ServiceFactory.getCategoryService();
        CategoryVO categoryVO = null;
        
        try {
            trans.begin();
            categoryVO = service.getCategory(id, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return categoryVO;
    }

    public boolean deleteCategory(Long id) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        CategoryService service = ServiceFactory.getCategoryService();
        boolean flag = true;
        
        try {
            trans.begin();
            service.deleteCategory(id, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
            flag = false;
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return flag;
    }
}
