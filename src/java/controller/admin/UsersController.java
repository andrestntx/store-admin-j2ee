package controller.admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.Handler;
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
    
    protected RequestDispatcher existOrFail(HttpServletRequest request, HttpServletResponse response, UserVO userVO) 
            throws ServletException, IOException {
        
        if(userVO == null) {
            return Handler.doGetPageError(request, response, "El usuario que está buscando no existe");
        }
        
        return null;
    }
    
    protected RequestDispatcher doGetUsers(HttpServletRequest request, HttpServletResponse response, UserFacade facade) 
            throws ServletException, IOException {
        
        Collection<UserVO> usersVO = facade.allUsers();
        request.setAttribute("users", usersVO);
        return request.getRequestDispatcher("views/admin/users/lists_users.jsp");
    }
    
    protected RequestDispatcher doGetUser(HttpServletRequest request, HttpServletResponse response, UserFacade facade, Long id,String option)
        throws ServletException, IOException {

        UserVO userVO = facade.getUser(id);
        RequestDispatcher rd = this.existOrFail(request, response, userVO);
        if(rd != null) {
            return rd;
        }
        request.setAttribute("user", userVO);
        if(option != null){
            return request.getRequestDispatcher("views/admin/users/edit_user.jsp");
        }
        return request.getRequestDispatcher("views/admin/users/view_user.jsp");
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
        String option = request.getParameter("option");
        RequestDispatcher rd = null;
        
        if(option != null){
            if("create".equals(option)){
                rd = request.getRequestDispatcher("views/admin/users/new_user.jsp");
            }else{
                if("edit".equals(option)){
                    rd = doGetUser(request, response, facade, new Long(id),option);
                }
            }
        } else{
           if(id == null){
                rd = doGetUsers(request, response, facade);
            }
            else if(Handler.isLong(id)){
                rd = doGetUser(request, response, facade, new Long(id), null);
            } 
            else {
                rd = Handler.doGetPageError(request, response, "El usuario no existe"); 
            } 
        }               
        
        
        rd.forward(request, response);
    }
    
    protected RequestDispatcher doPostUser(HttpServletRequest request, HttpServletResponse response, UserFacade facade){
        UserVO userVO = facade.newUser(this.getNewUserVO(request));
        request.setAttribute("user", userVO);
        return request.getRequestDispatcher("views/admin/users/view_user.jsp");
    }
    
    protected RequestDispatcher doDeleteUser(HttpServletRequest request, HttpServletResponse response, UserFacade facade, Long id){
        String message = "El usuario no se eliminó";
        if(facade.deleteUser(id)) {                            
            message = "Usuario eliminado correctamente";
        }
        request.setAttribute("message", message);
        return request.getRequestDispatcher("views/admin/users/lists_users.jsp");
    }
    
    protected RequestDispatcher doPutUser(HttpServletRequest request, HttpServletResponse response, UserFacade facade) 
            throws ServletException, IOException {
        
        Long id = new Long(request.getParameter("id"));        
        UserVO userVO = facade.getUser(id);
        userVO.setName(request.getParameter("name"));
        userVO.setUsername(request.getParameter("username"));
        userVO.setEmail(request.getParameter("email"));
        if(!request.getParameter("password").isEmpty()){
          userVO.setPassword(request.getParameter("password"));  
        }        
        userVO = facade.updateUser(userVO);
        this.existOrFail(request, response, userVO);
        
        request.setAttribute("user", userVO);
        return request.getRequestDispatcher("views/admin/users/view_user.jsp");
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
        
        try {
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
                default:
                    rd = Handler.doGetPageError(request, response, "Opción invalida"); 
                break;                                 
            } 
        } catch (NumberFormatException e) {
            rd = Handler.doGetPageError(request, response, "El usuario que está buscando no existe"); 
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
