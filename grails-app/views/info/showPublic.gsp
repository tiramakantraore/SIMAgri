
<%@ page import="simagri.Info" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="indexLayout">
		<g:set var="entityName" value="${message(code: 'info.label', default: 'Info')}" />
		<title>${infoInstance.titre}</title>
	</head>
	<body>
		<div class="row">

			
			<div class="col-sm-12 col-md-12">
                <g:render template="infostemplate"/>

                %{--<g:if test="${infoInstance.contenu}">--}%
                %{--<span class="infos-date">${infoInstance.date}</span>--}%
                    %{--<h4>${infoInstance.titre}</h4>--}%
                    %{--${infoInstance.contenu.decodeHTML()}--}%
                %{--<p>${infoInstance.contenu}</p>--}%
                    %{--<g:if test="${infoInstance?.url?.contains('youtube')}">--}%
                    %{--<span class="infos-date">${infoInstance.date}</span>--}%
                        %{--<h4>${infoInstance.titre}</h4>--}%
                        %{--<g:video videoKey="${infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""}" width="100%" height="350px"/>--}%

                        %{--<p><prettytime:display date="${infoInstance?.date}" /></p>--}%
                    %{--</g:if>--}%
                    %{--<g:elseif test="${infoInstance.url}">--}%
                        %{--<a class="infos-link" href="<g:fieldValue bean="${infoInstance}" field="url"/>">--}%
                            %{--découvrir le lien--}%
                        %{--</a>--}%
                    %{--</g:elseif>--}%

                    %{--<p><prettytime:display date="${infoInstance?.date}" /></p>--}%
                %{--</g:if>--}%
                %{--<g:elseif test="${infoInstance.url} && ${infoInstance?.url?.contains('youtube')}">--}%
                %{--<span class="infos-date">${infoInstance.date}</span>--}%
                    %{--<h4>${infoInstance.titre}</h4>--}%

                    %{--<g:video videoKey="${infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""}" width="100%" height="350px"/>--}%

                    %{--<p><prettytime:display date="${infoInstance?.date}" /></p>--}%
                %{--</g:elseif>--}%


			</div>

		</div>
	</body>
</html>
