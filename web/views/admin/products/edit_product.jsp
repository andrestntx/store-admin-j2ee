<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : edit_user
    Created on : 18/03/2016, 07:37:39 PM
    Author     : Felipe Iz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/storeAdmins/public/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="/storeAdmins/public/assets/css/style.css">
        <title>Editar Producto</title>
    </head>
    <body>
        <div class="col-sm-offset-1 col-sm-10 col-md-offset-2 col-md-8">
            <h1 class="title-page">Datos del Producto</h1>
            <form method="POST" action="/storeAdmins/admin/products" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Nombre</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" name="name" value="${requestScope.product.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Precio</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" name="price" value="${requestScope.product.username}"/>
                    </div>
                </div>                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="textarea" name="description" value="${requestScope.product.email}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Categoría</label>
                    <div class="col-sm-10">
                        <select name="category_id">
                            <c:forEach var="category" items="${categories}"> 
                                <option <c:if test="${product.categoryId} == ${category.id}"> selected </c:if>  value="${category.id}">${category.name}</option>
                            </c:forEach>                    
                        </select>
                    </div>
                </div>              
                <input type="hidden" name="created_by" value="1">
                <input type="hidden" name="option" value="3">
                <input type="submit" class="btn btn-info col-sm-offset-2" value="Guardar"/>
            </form>
        </div> 
    </body>
</html>
