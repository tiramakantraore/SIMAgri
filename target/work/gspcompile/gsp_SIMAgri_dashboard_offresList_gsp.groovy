import simagri.Offre
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_dashboard_offresList_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dashboard/_offresList.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
loop:{
int i = 0
for( offre in (offreList) ) {
printHtmlPart(1)
i++
}
}
printHtmlPart(1)
if(true && (offreList?.size()==0)) {
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(offre?.titre)
printHtmlPart(5)
})
invokeTag('link','g',23,['controller':("offre"),'action':("showPub"),'id':(offre?.id),'target':("_blank"),'rel':("ligneOffre"),'data-original-title':(offre.toString())],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1415914492576L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
