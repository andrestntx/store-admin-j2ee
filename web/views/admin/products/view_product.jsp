<%-- 
    Document   : view_user
    Created on : 18/03/2016, 09:00:10 AM
    Author     : Felipe Iz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <jsp:include page="/views/includes/meta.jsp" ></jsp:include>

    <title>Producto</title>

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
                    <h1 class="page-header">Producto
                        <a class="btn btn-success" href="/storeAdmins/admin-products">Todos</a>
                        <a class="btn btn-warning" href="/storeAdmins/admin-products?product=${requestScope.product.id}&option=edit">
                            <i class="fa fa-pencil"></i> Editar
                        </a>
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row"> 
                <div class="row">                
                   <c:if test="${message != null}" var="exsits_message" scope="request">
                    <p class="alert alert-info">${requestScope.message}</p>
                </c:if>
                
                <dl class="dl-horizontal">
                    <dt>Nombre:</dt> <dd>${requestScope.product.name}</dd>
                    <dt>Precio:</dt> <dd>${requestScope.product.price}</dd>
                    <dt>Descripción</dt> <dd>${requestScope.product.description}</dd>
                </dl>
                
                                             
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <jsp:include page="/views/includes/script.jsp" ></jsp:include>

</body>

</html>