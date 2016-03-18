/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

/**
 *
 * @author andrestntx
 */
@WebServlet(name = "CategoriesController", urlPatterns = {"/categories"})
public class CategoriesController extends HttpServlet {

    protected void doGetCategories(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        CategoryService service = new CategoryService();
        List<CategoryVO> categories = service.getAllCategories();
        
        request.setAttribute("categoires", categories);
        RequestDispatcher rd = request.getRequestDispatcher("public/categories.jsp");
        rd.forward(request, response);
    }
    
    protected void doGetCategoryProducts(HttpServletRequest request, HttpServletResponse response, String categoryId)
        throws ServletException, IOException {
        
        List<ProductVO> products = new ArrayList<>();
        CategoryService categoryService = new CategoryService();
        CategoryVO category = categoryService.getCategory(categoryId);
        
        if(category != null) {
            ProductService productService = new ProductService();
            products = productService.getProductsByCategory(categoryId);
        }
        
        request.setAttribute("category", category);
        request.setAttribute("products", products);
        RequestDispatcher rd = request.getRequestDispatcher("public/products.jsp");
        rd.forward(request, response);
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
        
        if(categoryId.isEmpty()) {
            this.doGetCategories(request, response);
        }
        else {
            this.doGetCategoryProducts(request, response, categoryId);
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
