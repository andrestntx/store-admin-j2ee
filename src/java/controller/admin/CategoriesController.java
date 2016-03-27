/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.Handler;
import facade.CategoryFacade;
import facade.FacadeFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.CategoryVO;

/**
 *
 * @author andrestntx
 */
@WebServlet(name = "AdminCategoriessController", urlPatterns = {"/admin-categories"})
public class CategoriesController extends HttpServlet {

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
    
    protected CategoryVO getNewCategoryVO(HttpServletRequest request){
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        return new CategoryVO(name, description);
    }
    
    protected CategoryVO getCategoryVO(HttpServletRequest request, Long id){
        CategoryVO categoryVO = this.getNewCategoryVO(request);
        categoryVO.setId(id);
        return categoryVO;
    }
    
    protected RequestDispatcher existOrFail(HttpServletRequest request, HttpServletResponse response, CategoryVO categoryVO) 
            throws ServletException, IOException {
        
        if(categoryVO == null) {
            return Handler.doGetPageError(request, response, "La categoría que está buscando no existe");
        }
        
        return null;
    }
    
    protected RequestDispatcher doGetCategories(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade) 
            throws ServletException, IOException {
        
        Collection<CategoryVO> categoriesVO = facade.allCategories();
        request.setAttribute("categories", categoriesVO);
        return request.getRequestDispatcher("views/admin/categories/lists_categories.jsp");
    }
    
    protected RequestDispatcher doGetCreateCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade)
        throws ServletException, IOException {
        return request.getRequestDispatcher("views/admin/categories/new_category.jsp");
    }
    
    protected RequestDispatcher doGetCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade, Long id)
        throws ServletException, IOException {
        
        CategoryVO categoryVO = facade.getCategory(id);
        RequestDispatcher rd = this.existOrFail(request, response, categoryVO);        
        request.setAttribute("category", categoryVO);
        return rd;
    }
    
    protected RequestDispatcher doPostCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade){
        CategoryVO categoryVO = facade.newCategory(this.getNewCategoryVO(request));
        request.setAttribute("category", categoryVO);
        request.setAttribute("message", "Categoría creada correcatemente");
        return request.getRequestDispatcher("views/admin/categories/view_category.jsp");
    }
    
    protected RequestDispatcher doDeleteCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade, Long id){
        String message = "La categoría no se eliminó";
        if(facade.deleteCategory(id)){                            
            message = "Categoría eliminada correctamente";
        }
        request.setAttribute("message", message);
        
        return request.getRequestDispatcher("views/admin/categories/lists_categories.jsp");
    }
    
    protected RequestDispatcher doPutCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade, Long id) 
            throws ServletException, IOException {
        
        CategoryVO categoryVO = this.getCategoryVO(request, id);
        categoryVO = facade.updateCategory(categoryVO);
        RequestDispatcher rd = this.existOrFail(request, response, categoryVO);
        
        if(rd != null) {
            return rd;
        }
        
        request.setAttribute("category", categoryVO);
        request.setAttribute("message", "Categoría actualizada correcatemente");
        return request.getRequestDispatcher("views/admin/categories/view_category.jsp");
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
        
        CategoryFacade facade = FacadeFactory.getCategoryFacade();
        String id = request.getParameter("category");
        String option = request.getParameter("option");
        RequestDispatcher rd = null;
                
        if(id == null && option == null){
            rd = doGetCategories(request, response, facade);
        }
        else if(Handler.isLong(id) && option != null && "edit".equals(option)){
            rd = doGetCategory(request, response, facade, new Long(id));
            if(rd == null){
                rd = request.getRequestDispatcher("views/admin/categories/edit_category.jsp");
            }
        }
        else if(Handler.isLong(id)){
            rd = doGetCategory(request, response, facade, new Long(id));
            if(rd == null){
                rd = request.getRequestDispatcher("views/admin/categories/view_category.jsp");
            }
        }
        else if("create".equals(option)){
            rd = doGetCreateCategory(request, response, facade);
        } 
        else{
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
        
        if(null != method)switch (method) {
            case "PUT":
                this.doPut(request, response);
                break;
            case "DELETE":
                this.doDelete(request, response);
                break;
        }
        else {
            CategoryFacade facade = new CategoryFacade();
            RequestDispatcher rd = doPostCategory(request, response, facade); 
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
        CategoryFacade facade = new CategoryFacade();
        
        String id = request.getParameter("id");
        
        if(Handler.isLong(id)) {             
            rd = doPutCategory(request, response, facade, new Long(id));  
        }
        else {
            rd = Handler.doGetPageError(request, response, "La categoría no existe");
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
        CategoryFacade facade = new CategoryFacade();
        String method = request.getParameter("_method");
        String id = request.getParameter("id");
        
        if(Handler.isLong(id)) {
            rd = doDeleteCategory(request, response, facade, new Long(id));
        }
        else {
            rd = Handler.doGetPageError(request, response, "La categoría no existe");
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
