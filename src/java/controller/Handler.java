/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrestntx
 */
public class Handler {
    
    protected static String viewNotFound = "views/errors/404.jsp";
    
    public static RequestDispatcher doGetPageError(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
        request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher(viewNotFound);
        return rd;
    }
    
    public static boolean isLong(String id){
        try {
            new Long(id);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    
}
