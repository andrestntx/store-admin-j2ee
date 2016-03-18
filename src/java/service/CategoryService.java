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
 * @author Felipe Iz
 */
public class CategoryService {
    
    protected List<CategoryVO> toVO(List<Category> entities) {
        List<CategoryVO> vos = new ArrayList<>();
        for (Category entity : entities) {
            vos.add(entity.toVO());
        }
        return vos;
    }
    
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
        
        try {
            trans.begin();
            categories = categoryDAO.getAll();
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }
                        
        return this.toVO(categories);
    }
}
