/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author andrestntx
 */
public class FacadeFactory {
    public static UserFacade getUserFacade(){
        return new UserFacade();
    }

    public static CategoryFacade getCategoryFacade() {
        return new CategoryFacade();
    }
    
    public static ProductFacade getProductFacade() {
        return new ProductFacade();
    }
}
