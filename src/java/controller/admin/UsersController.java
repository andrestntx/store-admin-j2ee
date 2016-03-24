package controller.admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import facade.FacadeFactory;
import facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.UserVO;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Felipe Iz
 */
@WebServlet(name = "/AdminUserController", urlPatterns = {"/admin-users"})
public class UsersController extends HttpServlet {

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
            out.println("<title>Servlet userServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet studentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected UserVO getNewUserVO(HttpServletRequest request){
        String name = request.getParameter("name");
        String username = request.getParameter("username");                        
        String email = request.getParameter("email");
        String password = request.getParameter("password");                        

        return new UserVO(name, username, email, password); 
    }
    
    protected UserVO getUserVO(HttpServletRequest request){
        UserVO userVO = this.getNewUserVO(request);
        Long id = new Long(request.getParameter("id"));                      

        return userVO;
    }
    
    protected void doGetPageError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("erros/404.jsp");
        rd.forward(request, response);
    }
    
    protected void existOrFail(HttpServletRequest request, HttpServletResponse response, UserVO userVO) 
            throws ServletException, IOException {
        
        if(userVO == null) {
            this.doGetPageError(request, response, "El usuario que está buscando no existe");
        }
    }
    
    protected RequestDispatcher doGetUsers(HttpServletRequest request, HttpServletResponse response, UserFacade facade) 
            throws ServletException, IOException {
        
        Collection<UserVO> usersVO = facade.allUsers();
        request.setAttribute("users", usersVO);
        return request.getRequestDispatcher("views/admin/users/lists_users.jsp");
    }
    
    protected RequestDispatcher doGetUser(HttpServletRequest request, HttpServletResponse response, UserFacade facade, Long id)
        throws ServletException, IOException {

        UserVO userVO = facade.getUser(id);
        this.existOrFail(request, response, userVO);
        request.setAttribute("user", userVO);
        return request.getRequestDispatcher("views/admin/users/update_user.jsp");
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
        
        UserFacade facade = FacadeFactory.getUserFacade();
        String id = request.getParameter("user");
        RequestDispatcher rd = null;
                
        if(id.isEmpty()){
            rd = doGetUsers(request, response, facade);
        }
        else {
            rd = doGetUser(request, response, facade, new Long(id));
        } 
        
        rd.forward(request, response);
    }
    
    protected RequestDispatcher doPostUser(HttpServletRequest request, HttpServletResponse response, UserFacade facade){
        UserVO userVO = facade.newUser(this.getNewUserVO(request));
        request.setAttribute("user", userVO);
        return request.getRequestDispatcher("users/view_user.jsp");
    }
    
    protected RequestDispatcher doDeleteUser(HttpServletRequest request, HttpServletResponse response, UserFacade facade, Long id){
        String message = "El usuario no se eliminó";
        if(facade.deleteUser(id)) {                            
            message = "Usuario eliminado correctamente";
        }
        request.setAttribute("message", message);
        return request.getRequestDispatcher("page_message.jsp");
    }
    
    protected RequestDispatcher doPutUser(HttpServletRequest request, HttpServletResponse response, UserFacade facade) 
            throws ServletException, IOException {
        
        UserVO userVO = this.getUserVO(request);
        userVO = facade.updateUser(userVO);
        this.existOrFail(request, response, userVO);
        
        request.setAttribute("user", userVO);
        return request.getRequestDispatcher("users/view_user.jsp");
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
        UserFacade facade = FacadeFactory.getUserFacade();
        
        int option = parseInt(request.getParameter("option"));
        switch(option) {
            case 1:   
                rd = doPostUser(request, response, facade);
            break;                
            case 2:
                rd = doPutUser(request, response, facade);  
            break;                      
            case 3:
                rd = doDeleteUser(request, response, facade, new Long(request.getParameter("id")));
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
