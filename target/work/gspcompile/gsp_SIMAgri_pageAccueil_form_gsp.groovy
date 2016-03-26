import simagri.PageUtilisateur
import  simagri.MonImage
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageAccueil_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageAccueil/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('textField','g',22,['name':(property),'value':(it.value),'maxlength':("25"),'required':(""),'class':("form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',23,['bean':("pageAccueilInstance"),'property':("nom")],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('checkBox','g',25,['name':(property),'value':(it.value),'class':("check")],-1)
printHtmlPart(2)
})
invokeTag('field','f',26,['bean':("pageAccueilInstance"),'property':("estPrincipal")],1)
printHtmlPart(3)
invokeTag('checkBoxList','bill',39,['referenceCollection':(MonImage.list(sort: 'nom', order: 'asc')),'containerClass':(ctnerTemplateImage),'instanceName':("mesImages")],-1)
printHtmlPart(4)
invokeTag('radioBoxList','bill',43,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("pagePrix"),'defaultValue':(pageAccueilInstance?.pagePrix?.nom)],-1)
printHtmlPart(5)
invokeTag('radioBoxList','bill',47,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("pageOffre"),'defaultValue':(pageAccueilInstance?.pageOffre?.nom)],-1)
printHtmlPart(6)
invokeTag('radioBoxList','bill',51,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("pageStock"),'defaultValue':(pageAccueilInstance?.pageStock?.nom)],-1)
printHtmlPart(7)
invokeTag('radioBoxList','bill',55,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("pageReseau"),'defaultValue':(pageAccueilInstance?.pageReseau?.nom)],-1)
printHtmlPart(8)
invokeTag('radioBoxList','bill',59,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("banniere"),'defaultValue':(pageAccueilInstance?.banniere?.nom)],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1417037599933L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
