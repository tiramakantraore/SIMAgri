import simagri.PageUtilisateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageUtilisateurlistePartenaires_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageUtilisateur/listePartenaires.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("pageLayout")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("list.partenaires")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',17,['code':("list.partenaires")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',20,['class':("alert-info")],3)
printHtmlPart(8)
}
printHtmlPart(9)
loop:{
int i = 0
for( entryInstance in (pageUtilisateurInstanceList) ) {
printHtmlPart(10)
expressionOut.print(entryInstance?.nom)
printHtmlPart(11)
if(true && ("$entryInstance?.contenu?.trim()")) {
printHtmlPart(12)
invokeTag('imageWithText','bill',36,['texte':(entryInstance?.contenu?.prettify()),'imageURL':(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:entryInstance?.id])),'imagePosition':(entryInstance?.alignement?.name())],-1)
printHtmlPart(13)
}
printHtmlPart(14)
expressionOut.print(entryInstance?.adresse)
printHtmlPart(15)
expressionOut.print(entryInstance?.email)
printHtmlPart(15)
expressionOut.print(entryInstance?.mobile)
printHtmlPart(15)
expressionOut.print(entryInstance?.telephone)
printHtmlPart(16)
i++
}
}
printHtmlPart(17)
})
invokeTag('captureBody','sitemesh',52,[:],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418679690089L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
