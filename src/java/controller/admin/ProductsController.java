/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.Handler;
import facade.CategoryFacade;
import facade.FacadeFactory;
import facade.ProductFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.CategoryVO;
import vo.ProductVO;
import vow.CategoryVOW;
import vow.ProductVOW;

/**
 *
 * @author andrestntx
 */
@WebServlet(name = "AdminProductsController", urlPatterns = {"/admin-products"})
public class ProductsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected ProductVO getNewProductVO(HttpServletRequest request){
        int created_by = Integer.parseInt(request.getParameter("created_by"));
        double price = Double.parseDouble(request.getParameter("price"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");                                               
        
        return new ProductVO(created_by, price, name, description);
    }
    
    protected ProductVO getProductVO(HttpServletRequest request){
        ProductVO productVO = this.getNewProductVO(request);
        return productVO;
    }
    
    protected RequestDispatcher doGetCreateProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long categoryId) throws ServletException, IOException
    {
        ProductVOW productVOW = facade.getProductForm(categoryId);
        if(productVOW == null || productVOW.getCategoryVO() == null) {
            return Handler.doGetPageError(request, response, "La categoría no existe");
        }
        
        request.setAttribute("productVOW", productVOW);
        return request.getRequestDispatcher("views/admin/products/new_product.jsp");
    }
    
    protected RequestDispatcher doGetProducts(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long categoryId) 
            throws ServletException, IOException {
        
        CategoryVOW categoryVOW = facade.getProductsOfCategory(categoryId);
        if(categoryVOW == null){
            return Handler.doGetPageError(request, response, "La categoría no existe");
        }
        
        request.setAttribute("categoryVOW", categoryVOW);
        return request.getRequestDispatcher("views/admin/products/lists_products.jsp");
    }
    
    protected RequestDispatcher doGetCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade, Long id)
        throws ServletException, IOException {
        
        CategoryVO categoryVO = facade.getCategory(id);
        if(categoryVO == null){
            return Handler.doGetPageError(request, response, "La categoría no existe");
        }
        request.setAttribute("category", categoryVO);
        return null;
    }
    
    protected RequestDispatcher doGetProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long productId)
        throws ServletException, IOException {
        
        ProductVOW productVOW = facade.getExistsProductForm(productId);
        if(productVOW == null){
            return Handler.doGetPageError(request, response, "La categoría o el producto no existe");
        }
        
        request.setAttribute("productVOW", productVOW);
        return null;
    }
    
    
    protected RequestDispatcher doPostProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long categoryId){
        ProductVO productVO = facade.newProduct(this.getNewProductVO(request), categoryId);
        request.setAttribute("product", productVO);
        request.setAttribute("message", "Producto creado correcatemente");
        return request.getRequestDispatcher("views/admin/products/view_product.jsp");
    }
    
    protected RequestDispatcher doDeleteProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long id){
        String message = "El producto no se eliminó";
        if(facade.deleteProduct(id)){                            
            message = "Producto eliminado correctamente";
        }
        request.setAttribute("message", message);
        
        return request.getRequestDispatcher("views/admin/products/lists_products.jsp");
    }
    
    protected RequestDispatcher doPutProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long categoryId, Long productId) 
            throws ServletException, IOException {
        
        ProductVO productVO = this.getProductVO(request);
        productVO.setId(productId);
        
        productVO = facade.updateProduct(productVO, categoryId);
        
        if(productVO == null){
            return Handler.doGetPageError(request, response, "La categoría o el producto no existe");
        }
        
        request.setAttribute("product", productVO);
        request.setAttribute("message", "Producto actualizado correcatemente");
        return request.getRequestDispatcher("views/admin/products/view_product.jsp");
    }

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProductFacade facade = FacadeFactory.getProductFacade();
        CategoryFacade categoryFacade = FacadeFactory.getCategoryFacade();
        
        String option = request.getParameter("option");
        String categoryId = request.getParameter("category");
        String productId = request.getParameter("product");
        
        RequestDispatcher rd = null;
        
        if(Handler.isLong(categoryId)) {
            if("create".equals(option)){
                rd = this.doGetCreateProduct(request, response, facade, new Long(categoryId));
            }
            else {
                rd = doGetProducts(request, response, facade, new Long(categoryId));
            }
        }
        else if("edit".equals(option) && Handler.isLong(productId)){
            rd = doGetProduct(request, response, facade, new Long(productId));
            if(rd == null) {
                rd = request.getRequestDispatcher("views/admin/products/edit_product.jsp");
            }
        } 
        else if(Handler.isLong(productId)){
            ProductVO product = facade.getProduct(new Long(productId));
            request.setAttribute("product", product);
            
            if(product == null) {
                rd = Handler.doGetPageError(request, response, "El producto no existe");
            }
            else {
                rd = request.getRequestDispatcher("views/admin/products/view_product.jsp");
            }
        }
        else {
            rd = Handler.doGetPageError(request, response, "La categoría no existe");
        }
        
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String method = request.getParameter("_method");
        String category_id = request.getParameter("category_id");
        
        if(null != method)switch (method) {
            case "PUT":
                this.doPut(request, response);
                break;
            case "DELETE":
                this.doDelete(request, response);
                break;
        }
        else {
            RequestDispatcher rd = null;
                
            if(Handler.isLong(category_id)) {   
                ProductFacade facade = new ProductFacade();
                rd = doPostProduct(request, response, facade, new Long(category_id)); 
            }
            else {
                rd = Handler.doGetPageError(request, response, "La categoría o el producto no existe");
            }

            rd.forward(request, response);
        }
    }
    
    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;
        ProductFacade facade = new ProductFacade();
        
        String id = request.getParameter("id");
        String category_id = request.getParameter("category_id");
        
        if(Handler.isLong(id) && Handler.isLong(category_id)) {             
            rd = doPutProduct(request, response, facade, new Long(category_id), new Long(id));  
        }
        else {
            rd = Handler.doGetPageError(request, response, "La categoría o el producto no existe");
        }
        
        rd.forward(request, response); 
    }
    
    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = null;
        ProductFacade facade = new ProductFacade();
        String method = request.getParameter("_method");
        String id = request.getParameter("id");
        
        /*if(Handler.isLong(id)) {
            rd = doDeleteCategory(request, response, facade, new Long(id));
        }
        else {
            rd = Handler.doGetPageError(request, response, "La categoría no existe");
        }
        
        rd.forward(request, response); */
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
