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
import vow.ProductVOW;

/**
 *
 * @author andrestntx
 */
public class ProductFacade extends BaseFacade {
    
    public ProductVOW getProductsOfCategory(Long categoryId){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        
        CategoryService categoryService = ServiceFactory.getCategoryService();
        ProductService productService = ServiceFactory.getProductService();
        List<ProductVO> products = new ArrayList<>();
        ProductVOW productVOW = null;
        
        try {
            trans.begin();
            CategoryVO category = categoryService.getCategory(categoryId, em);
            if(category != null){
                System.out.println("entro aca");
                products = productService.getProductsOfCategory(categoryId, em);
                productVOW = new ProductVOW(category, products);
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

    public List<ProductVO> searchProducts(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
