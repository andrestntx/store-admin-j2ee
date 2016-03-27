/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.FacadeFactory;
import facade.ProductFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ProductVO;
import vow.SearchVOW;

/**
 *
 * @author andrestntx
 */
@WebServlet(name = "ProductsController", urlPatterns = {"/products"})
public class ProductsController extends HttpServlet {    
    
    
    protected RequestDispatcher doGetProductById(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long productId)
        throws ServletException, IOException {
                
        ProductVO product = facade.getProduct(productId);
        if(product == null){
            return Handler.doGetPageError(request, response, "El producto no existe");
        }
        
        request.setAttribute("product", product);
                RequestDispatcher rd = request.getRequestDispatcher("views/guest/product.jsp");
        
        return rd;
    }
    
    protected RequestDispatcher doGetSearchProduct(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, String search)
        throws ServletException, IOException {
                
        SearchVOW searchVOW = facade.searchProducts(search);
        
        request.setAttribute("searchVOW", searchVOW);
        RequestDispatcher rd = request.getRequestDispatcher("views/guest/search.jsp");
        
        return rd;
    }

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
            out.println("<title>Servlet CategoryProductsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryProductsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        
        String productId = request.getParameter("product");
        String search = request.getParameter("search");
        
        if(Handler.isLong(productId)) {
            RequestDispatcher rd = this.doGetProductById(request, response, FacadeFactory.getProductFacade(), new Long(productId));
            rd.forward(request, response);
        }
        else if(search != null) {
            RequestDispatcher rd = this.doGetSearchProduct(request, response, FacadeFactory.getProductFacade(), search);
            rd.forward(request, response);
        }
        else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("/storeAdmins/categories");
        } 
        
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
        processRequest(request, response);
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
