/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import javax.persistence.EntityManager;

/**
 *
 * @author andrestntx
 */
public class ProductDAO extends BaseDAO<Product>{     

   public ProductDAO(EntityManager em) {
        super(em, Product.class);
    }
}
