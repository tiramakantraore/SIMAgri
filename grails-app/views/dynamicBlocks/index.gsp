<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="adminLayout">
	<title>'dynamic:block' tag examples</title>
	<g:javascript>
		function initDatePicker(num) {
			$('#birthday' + num).datepicker();
		}
	</g:javascript>
</head>

<body>

<g:form controller="dynamicBlocks" action="submitAction" style="margin: 100px;">
	<p>The 'dynamic:block' tag with callback function:</p>
    <div class="row">
    <div class="col-xs-2 col-xs-offset6">
        <span class="questionLabel">  firstName </span>

    </div>
    <div class="col-xs-2" >
        <span class="questionLabel">  lastName </span>
    </div>
    <div class="col-xs-2" >
        <span class="questionLabel">  birthday </span>
    </div>
    </div>
    <dynamic:block itemId="fullName" min="2" max="5" addBtnId="addFullName" removeBtnLabel="Delete" removeOffset="4"
						  onComplete="initDatePicker" limitReachedMsg="Limit is exceeded!" template="/partials/elem"/>
	<input id="addFullName" type="button" value="Add user"/>

	<br/><br/>

	<p>The 'dynamic:block' tag with checkboxes:</p>
	<dynamic:block itemId="skills" min="1" max="5" addBtnId="addSkills" removeBtnLabel="Suppr.">
        %{--<div class="row">--}%
       %{--<div class="col-xs-6 col-xs-offset4">--}%
		<g:textField name="skill" placeholder="Skill name"/>
       %{--</div>--}%
        %{--</div>--}%
        %{--<div class="row">--}%
      %{--<div class="col-xs-8 col-xs-offset4">--}%
        %{--<g:textField name="degre" placeholder="Skill degre"/>--}%
      %{--</div>--}%
        %{--</div>--}%
        %{--<div class="row">--}%
     %{--<div class="col-xs-8 col-xs-offset4">--}%
		%{--<g:checkBox name="isVerified"/>--}%
     %{--</div>--}%
        %{--</div>--}%
        %{--<div class="row">--}%
  %{--<div class="col-xs-8 col-xs-offset4">--}%
        %{--<g:textArea name="commentaire" placeholder="Commentaire"/>--}%
  %{--</div>--}%
        %{--</div>--}%
	</dynamic:block>
    <input id="addSkills" type="button" value="Add skills"/>



    <br/><br/>

	<p>The 'dynamic:block' tag with radio inputs:</p>
	<dynamic:block itemId="radioInputsTest" min="4">
		<span>
			<label>Option1</label>
			<g:radio value="1" name="radioGroup" checked="checked"/>
			<label>Option2</label>
			<g:radio value="2" name="radioGroup"/>
		</span>
	</dynamic:block>

	<br/><br/>

	<g:submitButton name="submit" value="Submit" style="display: block;"/>
</g:form>
<asset:deferredScripts/>
</body>
</html>