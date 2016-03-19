package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserService;
import vo.UserVO;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Felipe Iz
 */
@WebServlet(name = "/UserController", urlPatterns = {"/UserController"})
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
        
        UserService service = new UserService();
        int funcion =parseInt(request.getParameter("funcion"));
        switch(funcion) {
            case 1:
                Long id = new Long(request.getParameter("id"));
                UserVO userVO = service.findUser(id);
                request.setAttribute("user", userVO);
                RequestDispatcher rd = request.getRequestDispatcher("users/update_user.jsp");
                rd.forward(request, response);
            break;
            case 2:
                Collection<UserVO> usersVO = service.allUsers();
                request.setAttribute("users", usersVO);
                rd = request.getRequestDispatcher("users/lists_users.jsp");
                rd.forward(request, response);
            break;
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
            UserService service = new UserService();
            int funcion =parseInt(request.getParameter("funcion"));
            switch(funcion) {
                   case 1:   
                        String name = request.getParameter("name");
                        String username = request.getParameter("username");                        
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");                        
                        
                        UserVO userVO = new UserVO(name, username, email, password);                        
                        
                        userVO = service.newUser(userVO);
                        request.setAttribute("user", userVO);
                        RequestDispatcher rd = request.getRequestDispatcher("users/view_user.jsp");
                        rd.forward(request, response);                        
                        break;
                   case 2:
                       Long id = new Long(request.getParameter("id"));
                       userVO = service.findUser(id);
                       if(userVO != null){                            
                            request.setAttribute("user", userVO);
                            rd = request.getRequestDispatcher("users/view_user.jsp");
                        }
                        else{
                           String message = "No existe el usuario";
                            request.setAttribute("message", message);
                            rd = request.getRequestDispatcher("page_message.jsp");
                        }                    
                       
                       rd.forward(request, response);
                       break;
                   case 3:
                        id = new Long(request.getParameter("id"));
                        if(service.deleteUser(id)){                            
                             String message = "Usuario eliminado correctamente";
                             request.setAttribute("message", message);                             
                         }
                         else{                            
                             String message = "El usuario no se eliminó";
                             request.setAttribute("message", message);
                         } 
                        
                        rd = request.getRequestDispatcher("page_message.jsp");
                        rd.forward(request, response);
                       break;                      
                       
                       case 6:
                           id = new Long(request.getParameter("id"));
                           userVO = service.findUser(id);
                           name = request.getParameter("name");
                           username = request.getParameter("username");                        
                           email = request.getParameter("email");
                           password = request.getParameter("password");
                           userVO.setEmail(email);
                           userVO.setName(name);
                           userVO.setUsername(username);
                           userVO.setPassword(password);
                           
                           service.updateUser(userVO);
                           request.setAttribute("user", userVO);
                           rd = request.getRequestDispatcher("users/view_user.jsp");
                           rd.forward(request, response);                            
                       break;
                                              
                }     
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
