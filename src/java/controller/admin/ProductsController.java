/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import facade.FacadeFactory;
import facade.ProductFacade;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ProductVO;
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
        productVO.setId(new Long(request.getParameter("id")));
        return productVO;
    }
    
    protected void doGetPageError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("erros/404.jsp");
        rd.forward(request, response);
    }
    
    protected void existOrFail(HttpServletRequest request, HttpServletResponse response, ProductVO productVO) 
            throws ServletException, IOException {
        
        if(productVO == null) {
            this.doGetPageError(request, response, "El producto que está buscando no existe");
        }
    }
    
    protected RequestDispatcher doGetProducts(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long categoryId) 
            throws ServletException, IOException {
        
        ProductVOW productVOW = facade.getProductsOfCategory(categoryId);
        if(productVOW == null){
            this.doGetPageError(request, response, "La categoría no existe");
        }
        
        request.setAttribute("productVOW", productVOW);
        return request.getRequestDispatcher("views/admin/products/lists_products.jsp");
    }
    
    protected RequestDispatcher doGetProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long id)
        throws ServletException, IOException {
        
        ProductVO productVO = facade.getProduct(id);
        this.existOrFail(request, response, productVO);
        request.setAttribute("product", productVO);
        return request.getRequestDispatcher("views/admin/products/update_product.jsp");
    }
    
    protected RequestDispatcher doPostProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade){
        ProductVO productVO = facade.newProduct(this.getNewProductVO(request), new Long(request.getParameter("category_id")));
        request.setAttribute("product", productVO);
        return request.getRequestDispatcher("views/admin/products/view_product.jsp");
    }
    
    protected RequestDispatcher doDeleteProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long id){
        String message = "El producto no se eliminó";
        if(facade.deleteProduct(id)){                            
            message = "Producto eliminado correctamente";
        }
        request.setAttribute("message", message);
        
        return request.getRequestDispatcher("page_message.jsp");
    }
    
    protected RequestDispatcher doPutProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade) 
            throws ServletException, IOException {
        
        ProductVO productVO = this.getProductVO(request);
        productVO = facade.updateProduct(productVO, new Long(request.getParameter("category_id")));
        this.existOrFail(request, response, productVO);
        request.setAttribute("product", productVO);
        
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
        String categoryId = request.getParameter("category");
        String productId = request.getParameter("product");
        
        RequestDispatcher rd = null;
                
        if(categoryId != null){
            rd = doGetProducts(request, response, facade, new Long(categoryId));
        }
        else if(productId == null){
            rd = doGetProduct(request, response, facade, new Long(productId));
        } 
        else {
            this.doGetPageError(request, response, "La categoría no existe");
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
                
        RequestDispatcher rd = null;
        ProductFacade facade = FacadeFactory.getProductFacade();
        
        int option = parseInt(request.getParameter("option"));
        switch(option) {
            case 1:   
                rd = doPostProduct(request, response, facade);
            break;                
            case 2:
                rd = doPutProduct(request, response, facade);  
            break;                      
            case 3:
                rd = doDeleteProduct(request, response, facade, new Long(request.getParameter("id")));
            break;                                 
        }  
        
        rd.forward(request, response); 
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
