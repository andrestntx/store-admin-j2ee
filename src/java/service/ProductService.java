/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductDAO;
import entity.Product;
import javax.persistence.EntityManager;
import vo.ProductVO;

/**
 *
 * @author Felipe Iz
 */
public class ProductService {
    
    public void persist(ProductVO vo, EntityManager entityManager) {
        Product entity = new Product();
        entity.setId(vo.getId());
        entity.setDescription(vo.getDescription());
        entity.setName(vo.getName());
        entity.setPrice(vo.getPrice());
        
        ProductDAO productDAO = new ProductDAO(entityManager);
    }
}
