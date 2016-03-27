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
        <div class="col-sm-offset-1 col-sm-10">
            <h1 class="title-page" style="margin: 30px 0 20px 0px;">Categorías</h1>     
            <div class="row" style="margin-bottom: 20px;">
                <form action="/storeAdmins/products">
                    <div class="form-group col-sm-8">
                        <input type="text" name="search" value="" placeholder="Buscar productos..." class="form-control"/>
                    </div>
                    <div class="form-group col-sm-4">
                        <input type="submit" value="Buscar Producto.." class="btn btn-info"/>
                    </div>
                </form>
            </div>
            <div class="row">
                <c:forEach var="category" items="${categoires}">        
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12 text-center">
                                        <i class="fa fa-folder-open fa-5x"></i>
                                        <div class="h4">${category.name}</div>
                                    </div>                                
                                </div>
                            </div>
                            <a href="/storeAdmins/categories?category=${category.getId()}" alt="${category.description}">
                                <div class="panel-footer">
                                    <span class="pull-left">Productos</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>  
                                
            </c:forEach>                 
        </div>
    </body>
</html>
