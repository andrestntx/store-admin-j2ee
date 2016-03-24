/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author andrestntx
 */
public class ServiceFactory {
    
    public static UserService getUserService(){
        return UserService.getService();
    }

    public static CategoryService getCategoryService() {
        return CategoryService.getService();
    }
    
    public static ProductService getProductService() {
        return ProductService.getService();
    }
}
