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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import vo.CategoryVO;

/**
 *
 * @author andrestntx
 */
public class CategoryService {
    public void persist(CategoryVO vo, EntityManager entityManager) {
        Category entity = new Category();
        entity.setId(vo.getId());
        entity.setName(vo.getName());
        
        CategoryDAO categoryDAO = new CategoryDAO(entityManager);
    }
    
    public List<CategoryVO> getAllCategories(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("storeAdminsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        CategoryDAO categoryDAO = DAOFactory.getCategoryDAO(em);
        List<Category> categories = null;
        List<CategoryDAO> categoriesVO = new ArrayList<CategoryDAO>();
        
        try {
            trans.begin();
            categories = categoryDAO.all();
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }
        
        for (Category category : categories) {
            categoriesVO.add(category.toVO());
        }
        
        return categoriesVO;
    }
}
