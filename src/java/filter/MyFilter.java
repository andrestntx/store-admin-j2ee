/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.UserVO;

/**
 *
 * @author Felipe Iz
 */
@WebFilter(filterName = "MyFilter", urlPatterns = {"/*"})
public class MyFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private final FilterConfig filterConfig = null;

    public MyFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        
        HttpSession session = req.getSession();
        HttpSession session2 = req.getSession(false);
        if (session2 == null && !(uri.endsWith(".html") )) {
            res.sendRedirect("/storeAdmins/views/loguin.jsp");
        }
        if (uri.endsWith(".html") || uri.endsWith("/Login") || uri.endsWith(".css") || uri.endsWith(".js")
                || uri.endsWith(".png")
                || uri.endsWith(".jpg")
                || uri.endsWith(".gif")) {
            chain.doFilter(request, response);
        } else {
            if(uri.contains("admin")){
                UserVO user = (UserVO) session.getAttribute("usuario");
                if (user != null) {
                    System.out.println(user.getName());
                    chain.doFilter(request, response);
                }else{
                    res.sendRedirect("/storeAdmins/views/loguin.jsp");
                }  
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * Return a String representation of this object.
     * @return 
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("MyFilter()");
        }
        StringBuilder sb = new StringBuilder("MyFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
