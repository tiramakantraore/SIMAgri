import simagri.Sondage
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sondageshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sondage/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'sondage.label', default: 'Sondage'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("show.sondage")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',16,['code':("title.sondage")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',20,['code':("list.sondage")],-1)
printHtmlPart(7)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('message','g',26,['code':("create.sondage")],-1)
printHtmlPart(7)
})
invokeTag('link','g',27,['class':("create"),'action':("create")],2)
printHtmlPart(10)
invokeTag('message','g',36,['code':("show.sondage")],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',40,['class':("alert-info")],3)
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (sondageInstance?.titre)) {
printHtmlPart(15)
invokeTag('message','g',46,['code':("sondage.titre.label"),'default':("Titre")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',48,['bean':(sondageInstance),'field':("titre")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (sondageInstance?.description)) {
printHtmlPart(15)
invokeTag('message','g',54,['code':("sondage.description.label"),'default':("Description")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',56,['bean':(sondageInstance),'field':("description")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (sondageInstance?.dateDebut)) {
printHtmlPart(15)
invokeTag('message','g',62,['code':("sondage.dateDebut.label"),'default':("Date Debut")],-1)
printHtmlPart(16)
invokeTag('formatDate','g',64,['date':(sondageInstance?.dateDebut)],-1)
printHtmlPart(19)
}
printHtmlPart(18)
if(true && (sondageInstance?.duree)) {
printHtmlPart(15)
invokeTag('message','g',69,['code':("sondage.duree.label"),'default':("Duree")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',71,['bean':(sondageInstance),'field':("duree")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (sondageInstance?.dateFin)) {
printHtmlPart(15)
invokeTag('message','g',77,['code':("sondage.dateFin.label"),'default':("Date Fin")],-1)
printHtmlPart(16)
invokeTag('formatDate','g',79,['date':(sondageInstance?.dateFin)],-1)
printHtmlPart(19)
}
printHtmlPart(18)
if(true && (sondageInstance?.actif)) {
printHtmlPart(15)
invokeTag('message','g',84,['code':("sondage.actif.label"),'default':("Actif")],-1)
printHtmlPart(16)
invokeTag('formatBoolean','g',86,['boolean':(sondageInstance?.actif)],-1)
printHtmlPart(19)
}
printHtmlPart(18)
if(true && (sondageInstance?.details)) {
printHtmlPart(15)
invokeTag('message','g',91,['code':("sondage.details.label"),'default':("Details")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',93,['bean':(sondageInstance),'field':("details")],-1)
printHtmlPart(17)
}
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
createTagBody(3, {->
printHtmlPart(22)
invokeTag('message','g',104,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(23)
})
invokeTag('link','g',105,['class':("btn"),'action':("edit"),'id':(sondageInstance?.id)],3)
printHtmlPart(24)
})
invokeTag('form','g',108,[:],2)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',113,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416069509764L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
