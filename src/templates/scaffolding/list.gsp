<% import grails.persistence.Event %>
<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="list.${domainClass.propertyName}"  /></title>

	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.${domainClass.propertyName}" /></li>

						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.${domainClass.propertyName}" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
        </div>
    <div class="row">
			<div class="col-sm-8 col-md-8">
				
				<div class="page-header">
					<h1><g:message code="list.${domainClass.propertyName}" /></h1>
				</div>

				<g:if test="\${flash.message}">
				<bootstrap:alert class="alert-info">\${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>
						<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
							allowedNames = DomainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
							props = DomainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
							Collections.sort(props, comparator.constructors[0].newInstance([DomainClass] as Object[]))
							props.eachWithIndex { p, i ->
								if (i < 6) {
									if (p.isAssociation()) { %>
							<th class="header"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></th>
						<%      } else { %>
							<util:remoteSortableColumn update="listContent" action="list" property="${p.name}" title="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}" />
						<%  }   }   } %>

						</tr>
					</thead>
					<tbody>
					<g:each in="\${${propertyName}List}" var="${propertyName}">
						<tr>
                            <td class="link">
                                <g:link action="show" id="\${${propertyName}.id}" class="btn btn-small">Voir &raquo;</g:link>
                            </td>
						<%  props.eachWithIndex { p, i ->
						        if (i < 6) {
									if (p.type == Boolean || p.type == boolean) { %>
							<td><g:formatBoolean boolean="\${${propertyName}.${p.name}}" /></td>
						<%          } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
							<td><g:formatDate date="\${${propertyName}.${p.name}}" /></td>
						<%          } else { %>
							<td>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</td>
						<%  }   }   } %>

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="\${${propertyName}Total}" params="\${filterParams}" />

				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.${className}" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    </body>
</html>
