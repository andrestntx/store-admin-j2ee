/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vow;

import java.util.List;
import vo.ProductVO;

/**
 *
 * @author andrestntx
 */
public class SearchVOW {
    String search;
    List<ProductVO> products;

    public SearchVOW(String search, List<ProductVO> products) {
        this.search = search;
        this.products = products;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<ProductVO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductVO> products) {
        this.products = products;
    }
    
    
}
