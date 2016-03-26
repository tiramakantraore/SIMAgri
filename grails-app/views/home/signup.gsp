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
    <meta name="layout" content="appsLayout">
    <style>
    /*#example {*/
    /*text-align: center;*/
    /*}*/
    #treeview {
        display: inline-block;
        vertical-align: top;
        min-width: 50%;
        max-width: 100%;
        background: white;
        overflow: scroll;
        position: relative;
        text-overflow: ellipsis;
        /*min-height: 300px;*/
        max-height: 500px;
        text-align: left;
        margin: 0 0.25em;
    }

    .groupe-section {
        /*display: inline-block;*/
        /*vertical-align: top;*/
        width: 100%;
        /*background-color:black;*/
        /*min-height: 250px;*/
        /*text-align: left;*/
        /*margin: 0 0.25em;*/
    }

    </style>
</head>
<body>
<div class="row">
            <div class="col-sm-12 col-md-12 ">
                <div class="titleAddAccount"><g:message code="createAnAccount.text" default="Create an account" /></div>
                <g:form action="signup" name="signupForm" >
                    <g:hiddenField name="parentText" />
                <g:hiddenField name="parentIdSaved" />
                    <g:if test="${flash.messageErreur}">
                        <bootstrap:alert class="alert-info">${flash.messageErreur}</bootstrap:alert>
                    </g:if>

                    <g:hasErrors bean="${utilisateurInstance}">
                        <bootstrap:alert class="alert-error">
                            <ul>
                                <g:eachError bean="${utilisateurInstance}" var="error">
                                    <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                </g:eachError>
                            </ul>
                        </bootstrap:alert>
                    </g:hasErrors>
</div>
</div>
        <div class="row">
         <div class="col-sm-1 col-md-1">
          </div>
                 <div class="col-sm-5 col-md-5">
                            <g:hiddenField name="groupsId" />
                                <g:message code="choisir.reseau"/>
                                <div  class="k-content" >
                                    <div id="treeview" class="groupe-section"></div>
                                    <div id="result" class="groupe-section"></div>

                                    %{--<div class="groupe-section">--}%
                                    %{--</div>--}%
                               </div>
                  </div>
                   <div class="col-sm-6 col-md-6  ">
                   <g:render template="signupform"/>
                    <br>
                    <div class="row">
                        <div class="col-xs-12">
                            <button class="btn-flat  btn-warning"><g:message code="signUp.text" default="Sign Up"/></button>
                            <input type="reset" class="btn-flat btn" value=<g:message code="reset.text" default="reset"/> />
                        </div>
                    </div>
                </g:form>

        </div>


        </div> <!-- end of col-sm-5 col-md-5 tag-->





<script type="text/javascript">

    var mobilePhone=$('#mobilePhone');
        var reseauTree=$('#treeview');
        var  idResaux=$('#groupsId');
    var email=$("[name='email']");
    var password=$("[name='passwordtyped']");
    // function that gathers IDs of checked nodes
    function checkedNodeIds(nodes, checkedNodes) {
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].checked) {
                checkedNodes.push(nodes[i].id);
            }

            if (nodes[i].hasChildren) {
                checkedNodeIds(nodes[i].children.view(), checkedNodes);
            }
        }

    }

    function fireOnTreeChecked() {
        var checkedNodes = [],
                treeView = reseauTree.data("kendoTreeView"),
                message;
        checkedNodeIds(treeView.dataSource.view(), checkedNodes);

        var optionarray = [];
        var i = 0;
        var j = 0;
        for (i = 0; i < checkedNodes.length; i++) {
            optionarray[j] = {
                'id': checkedNodes[i]
            };
            j++
        }
        if (checkedNodes.length > 0) {
            idResaux.val(checkedNodes.join(","));
        }
    }



    $(document).ready(function(){

        var formParams=$('form[name="signupForm"]');
        formParams[0].reset();
        $.getJSON("${createLink(controller:'reseau', action:'getSignUpTree')}", {},function (data) {
            var reseaux= reseauTree.kendoTreeView({
                loadOnDemand: true,
                dataSource: data,
                checkboxes: {
                    checkChildren: false
                }
                ,
                schema: {
                    model: {
                        id: 'id',
                        text: 'text'
                    }
                }
            });
            reseauTree.data("kendoTreeView").dataSource.bind("change", function (e) {
                fireOnTreeChecked();
            });
              reseauTree.data("kendoTreeView").expand(".k-item");
        });

//        reseauTree.data("kendoTreeView").dataSource.bind("change", function (e) {
//            var checkedNodes = [],
//                    treeView = reseauTree.data("kendoTreeView"),
//                    message;
//
//            checkedNodeIds(treeView.dataSource.view(), checkedNodes);
//
//
//            var optionarray = []; var i=0; j=0;
//            for(i=0; i < checkedNodes.length; i++ ) {
//                optionarray[j] = {
//                    'id' :checkedNodes[i]
//                };
//                j++
//            }
//
//        });
//        reseauTree.data("kendoTreeView").expand(".k-item");

        $.mask.definitions['~']='[+]';
        mobilePhone.mask("~99999999999");
        mobilePhone.change(function() {

            if (mobilePhone.val()>"") {

                $.getJSON("${createLink(controller:"home", action:'findUserByMobile')}", {mobilePhone:mobilePhone.val()},
                        function(data){
                           if (data.isValid){
                            if (!data.isEmpty) {
                                alert(data.userName+' utilise déjà le numéro '+mobilePhone.val());

                            }
                           }
                            else {
                               alert("le numéro "+mobilePhone.val()+" n' est pas un numéro de téléphone valide");
                           }

                        }
                );



            }
        });
        email.val('');
        password.val('');
    });


</script>

</body>
</html>