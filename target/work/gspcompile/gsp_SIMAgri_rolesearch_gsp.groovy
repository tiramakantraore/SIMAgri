import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_rolesearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/role/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("springSecurityUI")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',5,['code':("spring.security.ui.role.search")],-1)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
invokeTag('message','g',22,['code':("role.authority.label"),'default':("Authority")],-1)
printHtmlPart(7)
invokeTag('textField','g',24,['name':("authority"),'class':("textField"),'size':("50"),'maxlength':("255"),'autocomplete':("off"),'value':(authority)],-1)
printHtmlPart(8)
invokeTag('submitButton','s2ui',29,['elementId':("search"),'form':("roleSearchForm"),'messageCode':("spring.security.ui.search")],-1)
printHtmlPart(9)
})
invokeTag('form','g',33,['action':("roleSearch"),'name':("roleSearchForm")],3)
printHtmlPart(10)
})
invokeTag('form','s2ui',35,['width':("100%"),'height':("200"),'elementId':("formContainer"),'titleCode':("spring.security.ui.role.search")],2)
printHtmlPart(10)
if(true && (searched)) {
printHtmlPart(5)

def queryParams = [authority: authority]

printHtmlPart(11)
invokeTag('remoteSortableColumn','util',49,['update':("listContent"),'action':("list"),'property':("authority"),'title':(message(code: 'role.authority.label', default: 'Authority')),'params':(queryParams)],-1)
printHtmlPart(12)
loop:{
int i = 0
for( role in (results) ) {
printHtmlPart(13)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(14)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: role, field: "authority"))
})
invokeTag('link','g',57,['action':("edit"),'id':(role.id)],4)
printHtmlPart(15)
i++
}
}
printHtmlPart(16)
invokeTag('remotePaginate','util',65,['update':("listContent"),'action':("list"),'total':(totalCount),'params':(queryParams)],-1)
printHtmlPart(17)
invokeTag('paginationSummary','s2ui',69,['total':(totalCount)],-1)
printHtmlPart(18)
}
printHtmlPart(19)
expressionOut.print(createLink(action: 'ajaxRoleSearch'))
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',86,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442325558373L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
