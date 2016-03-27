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
        <title>Productos de Categoría ${productVOW.getCategoryVO().getName()}</title>
    </head>
    <body>
        <div class="col-xs-offset-1 col-xs-10">
            <h1 class="title-page" style="margin: 30px 0px;">Categoría ${productVOW.getCategoryVO().getName()}</h1>             
            <div class="row">
                <c:forEach var="product" items="${productVOW.getProductsVO()}">
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
    </body>
</html>
