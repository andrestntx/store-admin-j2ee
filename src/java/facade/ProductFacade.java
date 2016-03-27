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
import service.ProductService;
import service.ServiceFactory;
import vo.CategoryVO;
import vo.ProductVO;
import vow.CategoryVOW;
import vow.ProductVOW;
import vow.SearchVOW;

/**
 *
 * @author andrestntx
 */
public class ProductFacade extends BaseFacade {
    
    public CategoryVOW getProductsOfCategory(Long categoryId){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        
        CategoryService categoryService = ServiceFactory.getCategoryService();
        ProductService productService = ServiceFactory.getProductService();
        List<ProductVO> products = new ArrayList<>();
        CategoryVOW productVOW = null;
        
        try {
            trans.begin();
            CategoryVO category = categoryService.getCategory(categoryId, em);
            if(category != null){
                products = productService.getProductsOfCategory(category, em);
                productVOW = new CategoryVOW(category, products);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }

        return productVOW;
    }
    
    public ProductVO newProduct(ProductVO productVO, Long categoryId){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        
        CategoryService categoryService = ServiceFactory.getCategoryService();
        ProductService productService = ServiceFactory.getProductService();
        
        try {
            trans.begin();
            CategoryVO categoryVO = categoryService.getCategory(categoryId, em);
            productVO = productService.newProduct(productVO, categoryVO, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return productVO;
    }
    
    public ProductVO updateProduct(ProductVO productVO, Long categoryId){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        
        CategoryService categoryService = ServiceFactory.getCategoryService();
        ProductService productService = ServiceFactory.getProductService();
        
        try {
            trans.begin();
            CategoryVO categoryVO = categoryService.getCategory(categoryId, em);
            productVO = productService.updateProduct(productVO, categoryVO, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        } 

        return productVO;
    }

    public ProductVO getProduct(Long id) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        ProductService service = ServiceFactory.getProductService();
        ProductVO productVO = null;
        
        try {
            trans.begin();
            productVO = service.getProduct(id, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return productVO;
    }
    
    public ProductVOW getExistsProductForm(Long productId) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        
        ProductService service = ServiceFactory.getProductService();
        CategoryService serviceCategory = ServiceFactory.getCategoryService();
        
        ProductVOW productVOW = null;
        
        try {
            trans.begin();
            ProductVO productVO  = service.getProduct(productId, em);
            List<CategoryVO> categoriesVO = serviceCategory.allCategories(em);
            productVOW = new ProductVOW(productVO, categoriesVO);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return productVOW;
    }
    
    public ProductVOW getProductForm(Long categoryId) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        
        ProductService service = ServiceFactory.getProductService();
        CategoryService serviceCategory = ServiceFactory.getCategoryService();
        
        ProductVOW productVOW = null;
        
        try {
            trans.begin();
            List<CategoryVO> categoriesVO = serviceCategory.allCategories(em);
            CategoryVO categoryVO = serviceCategory.getCategory(categoryId, em);
            productVOW = new ProductVOW(categoryVO, categoriesVO);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return productVOW;
    }

    public boolean deleteProduct(Long id) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        ProductService service = ServiceFactory.getProductService();
        boolean flag = true;
        
        try {
            trans.begin();
            service.deleteProduct(id, em);
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

    public SearchVOW searchProducts(String search) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        
        ProductService productService = ServiceFactory.getProductService();
        List<ProductVO> products = new ArrayList<>();
        SearchVOW searchVOW = null;
        
        try {
            trans.begin();
            products = productService.searchProducts(search, em);
            searchVOW = new SearchVOW(search, products);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }

        return searchVOW;
    }
}
