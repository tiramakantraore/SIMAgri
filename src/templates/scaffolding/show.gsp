<% import grails.persistence.Event %>
<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="show.${domainClass.propertyName}"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.${domainClass.propertyName}" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.${domainClass.propertyName}" />
							</g:link>
						</li>
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
					<h1><g:message code="show.${domainClass.propertyName}"/></h1>
				</div>

				<g:if test="\${flash.message}">
				<bootstrap:alert class="alert-info">\${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
					allowedNames = DomainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
					props = DomainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) }
					Collections.sort(props, comparator.constructors[0].newInstance([DomainClass] as Object[]))
					props.each { p -> %>
					<g:if test="\${${propertyName}?.${p.name}}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						<%  if (p.isEnum()) { %>
							<g:fieldValue bean="\${${propertyName}}" field="${p.name}"/>  </dt>

						<%  } else if (p.manyToOne || p.oneToOne) { %>
							<g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link> </dt>
						<%  } else if (p.type == Boolean || p.type == boolean) { %>
							<g:formatBoolean boolean="\${${propertyName}?.${p.name}}" />  </dt>
						<%  } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
							<g:formatDate date="\${${propertyName}?.${p.name}}" />  </dt>
						<%  } else if(!p.type.isArray()) { %>
							<g:fieldValue bean="\${${propertyName}}" field="${p.name}"/>

                        <%  } else if (p.oneToMany || p.manyToMany) { %>
                        <g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
                            <dd><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></dd>
                        </g:each>
						<%  } %>
                        </dt>
                        </div>
					</g:if>
				<%  } %>
				</dl>

				<g:form >
					<g:hiddenField name="id" value="\${${propertyName}?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="\${${propertyName}?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>

					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
