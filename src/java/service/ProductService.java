/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DAOFactory;
import dao.ProductDAO;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import vo.ProductVO;

/**
 *
 * @author Felipe Iz
 */
public class ProductService extends BaseService {
    
    protected List<ProductVO> toVO(List<Product> entities) {
        List<ProductVO> vos = new ArrayList<>();
        for (Product entity : entities) {
            vos.add(entity.toVO());
        }
        return vos;
    }
    
    public void persist(ProductVO vo, EntityManager entityManager) {
        Product entity = new Product();
        entity.setId(vo.getId());
        entity.setDescription(vo.getDescription());
        entity.setName(vo.getName());
        entity.setPrice(vo.getPrice());
        
        ProductDAO productDAO = new ProductDAO(entityManager);
    }

    public List<ProductVO> getAllProducts() {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        List<Product> products = null;
        
        try {
            trans.begin();
            products = productDAO.getAll();
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }
                        
        return this.toVO(products);
    }

    public List<ProductVO> getProductsByCategory(String id) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        List<Product> products = null;
        
        try {
            trans.begin();
            products = productDAO.getByAttribute("category_id", id);
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }
                        
        return this.toVO(products);
    }

    public ProductVO getProduct(Long productId) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        Product product = null;
        
        try {
            trans.begin();
            product = productDAO.find(new Long(productId));
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }
                        
        return product.toVO();
    }

    public List<ProductVO> searchProducts(String search) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        List<Product> products = null;
        
        try {
            trans.begin();
            products = productDAO.getLikeByAttribute("name", search);
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }
                        
        return this.toVO(products);
    }
}
