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
        <title>Editar Usuario</title>
    </head>
    <body>
        <div class="col-sm-offset-1 col-sm-10 col-md-offset-2 col-md-8">
            <h1 class="title-page">Datos de Usuario</h1>
            <form method="POST" action="/storeAdmins/admin/users" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Nombre</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" name="name" value="${requestScope.user.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Nombre de usuario</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" name="username" value="${requestScope.user.username}"/>
                    </div>
                </div>                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="email" name="email" value="${requestScope.user.email}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Contraseña</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="password" name="password" value=""/>
                    </div>
                </div>                
                <input type="hidden" name="id" value="${requestScope.user.id}">
                <input type="hidden" name="option" value="3">
                <input type="submit" class="btn btn-info col-sm-offset-2" value="Guardar"/>
            </form>
        </div> 
    </body>
</html>
