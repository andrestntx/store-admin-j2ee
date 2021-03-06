<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : lists_users
    Created on : 18/03/2016, 10:06:29 AM
    Author     : Felipe Iz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <jsp:include page="/views/includes/meta.jsp" ></jsp:include>

    <title>Ver Usuarios</title>

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
                    <h1 class="page-header">Listado de Usuarios
                        <a href="/storeAdmins/admin-users?option=create" class="btn btn-primary">
                            <i class="fa fa-plus"></i> Nuevo
                        </a>
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                
                <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Nombre de usuario</th>                        
                        <th>Email</th> 
                        <th>Opción</th> 
                    </tr>
                </thead>
                <c:forEach var="user" items="${users}"> 
                    <tbody>
                        <tr>
                            <td>${user.getName()}</td>
                            <td>${user.getUsername()}</td>                            
                            <td>${user.getEmail()}</td>  
                            <td>
                                <a class="btn btn-info" href="/storeAdmins/admin-users?user=${user.getId()}">Ver</a>
                                <a class="btn btn-warning" href="/storeAdmins/admin-users?user=${user.getId()}&option=edit">Editar</a>
                            </td>
                        </tr>
                    </tbody>
                 </c:forEach>
            </table>
                                          
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <jsp:include page="/views/includes/script.jsp" ></jsp:include>

</body>

</html>


