<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : home
    Created on : Mar 18, 2016, 10:22:49 AM
    Author     : andrestntx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/views/includes/css.jsp" ></jsp:include>
        <title>Categorías</title>
    </head>
    <body>
        <div class="col-sm-offset-1 col-sm-10 col-md-offset-2 col-md-8">
            <h1 class="title-page">Categorías</h1>             
            <div class="row">
                <c:forEach var="category" items="${categoires}"> 
                    <div class="col-lg-12 col-md-2">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="col-xs-1">
                                            <i class="fa fa-folder-open fa-2x"></i>
                                        </div> 
                                        <div class="col-xs-8">
                                            <div><h4>${category.name}</h4></div>
                                        </div> 
                                        <a title="${category.description}" href="/storeAdmins/categories?category=${category.getId()}">
                                        <div class="col-xs-3 panel-footer">
                                            <span class="pull-left">Ver Productos</span>
                                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                            <div class="clearfix"></div>
                                        </div>
                                        </a>  
                                    </div>                                
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>                 
        </div>
    </body>
</html>
