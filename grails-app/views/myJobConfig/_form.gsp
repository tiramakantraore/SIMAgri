<%@ page import="simagri.MyJobConfig" %>


<div class="fieldcontain ${hasErrors(bean: myJobConfigInstance, field: 'cron', 'error')} " rel="ligneInfo" data-original-title="${cronFormat}">
	<label for="cron">
		<g:message code="myJobConfig.cron.label" default="Cron" placeHolder="0 0/40 7,13,18 * * ?" />
		
	</label>
	<g:textField name="cron" value="${myJobConfigInstance?.cron}"/>
</div>

Heure : "${new Date()}"
<script type="text/javascript">
$(document).ready(function(){
$('[rel=ligneInfo]').tooltip({template: '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'});
 });

</script>



