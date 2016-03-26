<%@ page import="org.springframework.validation.FieldError" %>

		<div class="row">

			
			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${quizzInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${quizzInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" role="form" name="quizzForm" >
                        <div class="form-group">
                            <f:field bean="quizzInstance" property="titre" id="quizz_titre" input-class="form-control"/>

                            <g:hiddenField name='questions[${i}]?.id' value='${detail?.id}'/>
                            <g:hiddenField name='questions[${i}]?.deleted' value='false'/>
                            <g:hiddenField name='questions[${i}]?.new' value="${detail?.id == null?'true':'false'}"/>


                            <g:render template="/quizz/details" model="['sondageInstance':sondageInstance]" />

                        </div>
					</g:form>
                <div  class="btn-flat  btn-primary validerQuizz">

                    <g:message code="default.button.create.label" default="Create" />
                </div>
			</div>
            <div class="col-sm-3 col-md-3">

            </div>
		</div>
        %{--<g:render template='/quizz/detail' model="['quizz_detail':null,'i':'_clone','hidden':true]"/>--}%