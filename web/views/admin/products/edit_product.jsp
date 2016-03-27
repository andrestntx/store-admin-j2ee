<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : edit_user
    Created on : 18/03/2016, 07:37:39 PM
    Author     : Felipe Iz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <jsp:include page="/views/includes/meta.jsp" ></jsp:include>

    <title>Editar Producto</title>

    <jsp:include page="/views/includes/css.jsp" ></jsp:include>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <jsp:include page="/views/includes/header.jsp" ></jsp:include>
            <jsp:include page="/views/includes/settings.jsp" ></jsp:include>
            <jsp:include page="/views/includes/sidebar.jsp" ></jsp:include>

        </nav>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Categoría ${product.categoryVO.name} - Editar Producto</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <form method="POST" action="/storeAdmins/admin-products" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Nombre</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="name" value="${requestScope.productVOW.productVO.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Precio</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="text" name="price" value="${requestScope.productVOW.productVO.price}"/>
                    </div>
                </div>                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Descripción</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="textarea" name="description" value="${requestScope.productVOW.productVO.description}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Categoría</label>
                    <div class="col-sm-8">
                        <select name="category_id" class="form-control">
                            <c:forEach var="cat" items="${requestScope.productVOW.categoriesVO}"> 
                                <option value="${cat.id}" 
                                    <c:if test="${cat.id == productVOW.productVO.categoryVO.id}">
                                        selected 
                                    </c:if>>
                                    ${cat.name}
                                </option>
                            </c:forEach>                    
                        </select>
                    </div>
                </div> 
                
                <input type="hidden" name="created_by" value="1">
                <input type="hidden" name="id" value="${requestScope.productVOW.productVO.id}">
                <input type="hidden" name="_method" value="PUT">
                <input type="submit" class="btn btn-info col-sm-offset-2" value="Guardar"/>
            </form>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <jsp:include page="/views/includes/script.jsp" ></jsp:include>

</body>

</html>