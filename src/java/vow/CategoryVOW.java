/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vow;

import java.util.List;
import vo.CategoryVO;
import vo.ProductVO;

/**
 *
 * @author andrestntx
 */
public class CategoryVOW {
    private CategoryVO categoryVO;
    private List<ProductVO> productsVO;

    public CategoryVOW(CategoryVO categoryVO, List<ProductVO> productsVO) {
        this.categoryVO = categoryVO;
        this.productsVO = productsVO;
    }

    public CategoryVO getCategoryVO() {
        return categoryVO;
    }

    public void setCategoryVO(CategoryVO categoryVO) {
        this.categoryVO = categoryVO;
    }

    public List<ProductVO> getProductsVO() {
        return productsVO;
    }

    public void setProductsVO(List<ProductVO> productsVO) {
        this.productsVO = productsVO;
    }
    
    
}
