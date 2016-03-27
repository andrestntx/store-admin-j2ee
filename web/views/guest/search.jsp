<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : home
    Created on : Mar 18, 2016, 10:22:49 AM
    Author     : Felipe iz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/views/includes/css.jsp" ></jsp:include>
        <title>Busqueda de Productos: ${searchVOW.search}</title>
    </head>
    <body>
        <div class="col-xs-offset-1 col-xs-10">
            
            <h1 class="title-page" style="margin-top: 30px;">Busqueda de Productos: ${searchVOW.search}
                <a href="/storeAdmins/categories" class="btn btn-primary">
                    <i class="fa fa-folder-open"></i> Volver a Categorías
                </a>
            </h1>   
            <div style="margin-bottom: 30px;">
                <form action="/storeAdmins/products">
                    <div class="form-group col-sm-8" style="padding-left: 0;">
                        <input type="text" name="search" value="${searchVOW.search}" class="form-control"/>
                    </div>
                    <div class="form-group col-sm-4">
                        <input type="submit" value="Buscar Producto.." class="btn btn-info"/>
                    </div>
                </form>
            </div>
            <div class="row">
                <c:forEach var="product" items="${searchVOW.products}">
                    <div class="col-sm-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <i class="fa fa-shopping-cart fa-2x"> - ${product.name}</i> 
                            </div>
                            <div class="panel-body">
                                <p>${product.description}</p>
                            </div>
                            <a  href="/storeAdmins/categories?category=${product.getId()}">
                            <div class="panel-footer">
                                <span class="pull-left">Ver</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                            </a>  
                        </div>
                    </div> 
                </c:forEach>                 
            </div>
        </div>    
    </body>
</html>
