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
public class ProductVOW {
    private ProductVO productVO;
    private CategoryVO categoryVO;
    private List<CategoryVO> categoriesVO;

    public ProductVOW(ProductVO productVO, CategoryVO categoryVO, List<CategoryVO> categoriesVO) {
        this.productVO = productVO;
        this.categoriesVO = categoriesVO;
        this.categoryVO = categoryVO;
    }

    public ProductVOW(CategoryVO categoryVO, List<CategoryVO> categoriesVO) {
        this.categoryVO = categoryVO;
        this.categoriesVO = categoriesVO;
    }

    public ProductVO getProductVO() {
        return productVO;
    }

    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }

    public List<CategoryVO> getCategoriesVO() {
        return categoriesVO;
    }

    public void setCategoriesVO(List<CategoryVO> categoriesVO) {
        this.categoriesVO = categoriesVO;
    }

    public CategoryVO getCategoryVO() {
        return categoryVO;
    }

    public void setCategoryVO(CategoryVO categoryVO) {
        this.categoryVO = categoryVO;
    }
    
}
