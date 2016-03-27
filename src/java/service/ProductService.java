/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DAOFactory;
import dao.ProductDAO;
import entity.Category;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.CategoryVO;
import vo.ProductVO;

/**
 *
 * @author Felipe Iz
 */
public class ProductService {
    
    private static ProductService service;
    
    private ProductService() {

    }
    
    public static ProductService getService() {
        if(service == null) {
            service = new ProductService(); 
        }
        
        return service;
    }
    
    protected List<ProductVO> toVO(List<Product> entities) {
        List<ProductVO> vos = new ArrayList<>();
        for (Product entity : entities) {
            vos.add(entity.toVO());
        }
        return vos;
    }
   
    public List<ProductVO> allProducts(EntityManager em) {
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        return this.toVO(productDAO.getAll());               
    }

    public List<ProductVO> getProductsOfCategory(CategoryVO categoryVO, EntityManager em) {
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        List<ProductVO> products = this.toVO(productDAO.getByAttribute("category", categoryVO.toEntity()));
        return products;
    }

    public ProductVO getProduct(Long productId, EntityManager em) {
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        Product product = productDAO.find(new Long(productId)); 
        
        if(product != null){
            return product.toVO();
        }
       
        return null;
    }

    public List<ProductVO> searchProducts(String search, EntityManager em) {
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        return this.toVO(productDAO.getLikeByAttribute("name", search));
    }

    public ProductVO updateProduct(ProductVO productVO, CategoryVO categoryVO, EntityManager em){
        ProductDAO productDAO = DAOFactory.getProductDAO(em);  
        Product product = productDAO.find(productVO.getId());

        if( product != null) {
            product.setCategory(categoryVO.toEntity());
            return productDAO.update(product).toVO();
        }
       
        return null;
    }

    public ProductVO newProduct(ProductVO productVO, CategoryVO categoryVO, EntityManager em){ 
        productVO.setCategoryVO(categoryVO);
        ProductDAO productDAO = DAOFactory.getProductDAO(em);        
        Product product = productVO.toEntity();
        productDAO.save(product);
        return product.toVO();
    }
    
    public boolean deleteProduct(Long id, EntityManager em){
        ProductDAO productDAO = DAOFactory.getProductDAO(em);
        return productDAO.delete(id);
    }
    




}
