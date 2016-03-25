/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

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
import static java.lang.Integer.parseInt;

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
        return new CategoryVO(name);
    }
    
    protected CategoryVO getCategoryVO(HttpServletRequest request){
        CategoryVO categoryVO = this.getNewCategoryVO(request);
        categoryVO.setId(new Long(request.getParameter("id")));
        return categoryVO;
    }
    
    protected void doGetPageError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("erros/404.jsp");
        rd.forward(request, response);
    }
    
    protected void existOrFail(HttpServletRequest request, HttpServletResponse response, CategoryVO categoryVO) 
            throws ServletException, IOException {
        
        if(categoryVO == null) {
            this.doGetPageError(request, response, "La categoría que está buscando no existe");
        }
    }
    
    protected RequestDispatcher doGetCategories(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade) 
            throws ServletException, IOException {
        
        Collection<CategoryVO> categoriesVO = facade.allCategories();
        request.setAttribute("categories", categoriesVO);
        return request.getRequestDispatcher("views/admin/categories/lists_categories.jsp");
    }
    
    protected RequestDispatcher doGetCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade, Long id)
        throws ServletException, IOException {
        
        CategoryVO categoryVO = facade.getCategory(id);
        this.existOrFail(request, response, categoryVO);
        request.setAttribute("category", categoryVO);
        return request.getRequestDispatcher("views/admin/categories/update_category.jsp");
    }
    
    protected RequestDispatcher doPostCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade){
        CategoryVO categoryVO = facade.newCategory(this.getCategoryVO(request));
        request.setAttribute("category", categoryVO);
        return request.getRequestDispatcher("views/admin/categories/view_category.jsp");
    }
    
    protected RequestDispatcher doDeleteCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade, Long id){
        String message = "La categoría no se eliminó";
        if(facade.deleteCategory(id)){                            
            message = "Categoría eliminada correctamente";
        }
        request.setAttribute("message", message);
        
        return request.getRequestDispatcher("page_message.jsp");
    }
    
    protected RequestDispatcher doPutCategory(HttpServletRequest request, HttpServletResponse response, CategoryFacade facade) 
            throws ServletException, IOException {
        
        CategoryVO categoryVO = this.getCategoryVO(request);
        categoryVO = facade.updateCategory(categoryVO);
        this.existOrFail(request, response, categoryVO);
        request.setAttribute("category", categoryVO);
        
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
        RequestDispatcher rd = null;
                
        if(id == null){
            rd = doGetCategories(request, response, facade);
        }
        else {
            rd = doGetCategory(request, response, facade, new Long(id));
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
        CategoryFacade facade = new CategoryFacade();
        
        int option = parseInt(request.getParameter("option"));
        switch(option) {
            case 1:   
                rd = doPostCategory(request, response, facade);
            break;                
            case 2:
                rd = doPutCategory(request, response, facade);  
            break;                      
            case 3:
                rd = doDeleteCategory(request, response, facade, new Long(request.getParameter("id")));
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
