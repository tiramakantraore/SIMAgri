<%--
  Created by IntelliJ IDEA.
  User: Tiramakan
  Date: 17/07/13
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="org.springframework.validation.FieldError" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="indexLayout">
<style>
</style>
</head>
<body>
<div class="row">
    <div class="col-sm-3 col-md-3">
    </div>
    <div class="col-sm-5 col-md-5">
            <div class="loginBox">
                <div class="titleresetpwd"><g:message code="reinitialisationpwd.text" default="Reset password" /></div>
                <g:form controller="login" action="resetPassword" name="resetPassword"  method="post" accept-charset="UTF-8"  >
                    <g:hiddenField name="parentText" />
                <g:hiddenField name="parentIdSaved" />
                    <g:if test="${flash.messageErreur}">
                        <bootstrap:alert class="alert-info">${flash.messageErreur}</bootstrap:alert>
                    </g:if>

                <g:render template="resetPwdForm"/>
                    <br>
                     <button type="submit" class="btn-flat  btn-primary"  >

                        <g:message code="resetBtn.text" default="Reset"/>
                    </button>



                </g:form>

        </div> <!-- end of col-sm-5 col-md-5 tag-->
    </div> <!-- end of row tag-->
    <div class="col-sm-3 col-md-3">
    </div>

</div>




<script type="text/javascript">
    var mobilePhone=$('#mobilePhone');



    $(document).ready(function(){
        mobilePhone.change(function() {

            if (mobilePhone.val()>"") {

                $.getJSON("${createLink(controller:"home", action:'findUserByMobile')}", {mobilePhone:mobilePhone.val()},
                        function(data){
                           if (data.isValid){
                            if (data.isEmpty) {
                                alert("Le numéro "+mobilePhone.val()+" n'est pas enregistré ");

                            }
                           }

                        }
                );



            }
        });
    });


</script>

</body>
</html>