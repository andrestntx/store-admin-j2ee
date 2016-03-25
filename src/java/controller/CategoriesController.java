/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.CategoryFacade;
import facade.FacadeFactory;
import facade.ProductFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.ProductService;
import vo.CategoryVO;
import vo.ProductVO;
import vow.ProductVOW;

/**
 *
 * @author andrestntx
 */
@WebServlet(name = "CategoriesController", urlPatterns = {"/categories"})
public class CategoriesController extends HttpServlet {
    
    protected void doGetPageError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("erros/404.jsp");
        rd.forward(request, response);
    }
    
    protected RequestDispatcher doGetCategories(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade) 
        throws ServletException, IOException {
        
        List<CategoryVO> categories = facade.allCategories();
        request.setAttribute("categoires", categories);
        RequestDispatcher rd = request.getRequestDispatcher("views/guest/categories.jsp");
        return rd; 
    }
    
    protected RequestDispatcher doGetCategoryProducts(HttpServletRequest request, HttpServletResponse response, ProductFacade facade, Long categoryId)
        throws ServletException, IOException {
                
        ProductVOW productVOW = facade.getProductsOfCategory(categoryId);
        if(productVOW == null){
            this.doGetPageError(request, response, "La categoría no existe");
        }
        
        request.setAttribute("productVOW", productVOW);
                RequestDispatcher rd = request.getRequestDispatcher("views/guest/products.jsp");
        
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
            out.println("<title>Servlet CategoriesController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriesController at " + request.getContextPath() + "</h1>");
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
        
        String categoryId = request.getParameter("category");
        RequestDispatcher rd = null;
        CategoryFacade categoryFacade = FacadeFactory.getCategoryFacade();
        
        if(categoryId == null) {
            rd = this.doGetCategories(request, response, categoryFacade);
        }
        else {
            rd = this.doGetCategoryProducts(request, response, FacadeFactory.getProductFacade(), new Long(categoryId));
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
