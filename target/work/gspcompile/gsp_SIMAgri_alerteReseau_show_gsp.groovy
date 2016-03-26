import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_alerteReseau_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/alerteReseau/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'alerte.label', default: 'Alerte'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("show.alerte")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',16,['code':("title.alerteReseau")],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('message','g',21,['code':("list.alerte")],-1)
printHtmlPart(6)
})
invokeTag('link','g',22,['class':("list"),'action':("list")],2)
printHtmlPart(7)
invokeTag('message','g',32,['code':("show.alerte")],-1)
printHtmlPart(8)
if(true && (flash.message)) {
printHtmlPart(9)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',36,['class':("alert-info")],3)
printHtmlPart(9)
}
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(10)
invokeTag('display','f',39,['bean':("alerteInstance"),'property':("typeAlerte")],-1)
printHtmlPart(10)
invokeTag('display','f',40,['bean':("alerteInstance"),'property':("typeOffre")],-1)
printHtmlPart(10)
invokeTag('display','f',41,['bean':("alerteInstance"),'property':("valide")],-1)
printHtmlPart(10)
invokeTag('display','f',42,['bean':("alerteInstance"),'property':("suspendre")],-1)
printHtmlPart(10)
invokeTag('display','f',43,['bean':("alerteInstance"),'property':("reseaux")],-1)
printHtmlPart(10)
invokeTag('display','f',44,['bean':("alerteInstance"),'property':("destinataires")],-1)
printHtmlPart(10)
invokeTag('display','f',45,['bean':("alerteInstance"),'property':("produits")],-1)
printHtmlPart(10)
invokeTag('display','f',46,['bean':("alerteInstance"),'property':("markets")],-1)
printHtmlPart(10)
invokeTag('display','f',47,['bean':("alerteInstance"),'property':("lundi")],-1)
printHtmlPart(10)
invokeTag('display','f',48,['bean':("alerteInstance"),'property':("mardi")],-1)
printHtmlPart(10)
invokeTag('display','f',49,['bean':("alerteInstance"),'property':("mercredi")],-1)
printHtmlPart(10)
invokeTag('display','f',50,['bean':("alerteInstance"),'property':("jeudi")],-1)
printHtmlPart(10)
invokeTag('display','f',51,['bean':("alerteInstance"),'property':("vendredi")],-1)
printHtmlPart(10)
invokeTag('display','f',52,['bean':("alerteInstance"),'property':("samedi")],-1)
printHtmlPart(10)
invokeTag('display','f',53,['bean':("alerteInstance"),'property':("dimanche")],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',54,['name':("update"),'value':(update)],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',57,['name':("id"),'value':(alerteInstance?.id)],-1)
printHtmlPart(11)
invokeTag('render','g',58,['template':("/partials/btnShow"),'model':([update:$update,instance:alerteInstance])],-1)
printHtmlPart(13)
})
invokeTag('form','g',60,[:],2)
printHtmlPart(14)
})
invokeTag('captureBody','sitemesh',60,[:],1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418942595313L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
