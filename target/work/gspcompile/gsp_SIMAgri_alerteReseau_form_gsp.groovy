import simagri.Utilisateur
import  simagri.Produit
import  simagri.Marche
import  simagri.CategorieProduit
import  simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_alerteReseau_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/alerteReseau/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('hiddenField','g',3,['name':("recevoirOffres"),'value':(alerteInstance?.recevoirOffres)],-1)
printHtmlPart(0)
invokeTag('hiddenField','g',5,['name':("recevoirPrix"),'value':(alerteInstance?.recevoirPrix)],-1)
printHtmlPart(0)
invokeTag('hiddenField','g',7,['name':("recevoirParEmail"),'value':(alerteInstance?.recevoirParEmail)],-1)
printHtmlPart(0)
invokeTag('hiddenField','g',9,['name':("recevoirParSMS"),'value':(alerteInstance?.recevoirParSMS)],-1)
printHtmlPart(1)
invokeTag('field','f',12,['bean':("alerteInstance"),'property':("nom"),'required':("true"),'input-class':("form-control")],-1)
printHtmlPart(2)
invokeTag('field','f',17,['bean':("alerteInstance"),'property':("typeAlerte"),'input-class':("form-control")],-1)
printHtmlPart(2)
invokeTag('field','f',22,['bean':("alerteInstance"),'property':("categories"),'input-class':("form-control")],-1)
printHtmlPart(3)
invokeTag('field','f',28,['bean':("alerteInstance"),'property':("produits"),'input-class':("form-control")],-1)
printHtmlPart(3)
invokeTag('field','f',34,['bean':("alerteInstance"),'property':("produits"),'input-class':("form-control")],-1)
printHtmlPart(3)
invokeTag('field','f',40,['bean':("alerteInstance"),'property':("markets"),'input-class':("form-control")],-1)
printHtmlPart(4)
invokeTag('field','f',47,['bean':("alerteInstance"),'property':("markets"),'input-class':("form-control")],-1)
printHtmlPart(5)
invokeTag('field','f',52,['bean':("alerteInstance"),'property':("suspendre"),'input-class':("check")],-1)
printHtmlPart(6)
invokeTag('field','f',62,['bean':("alerteInstance"),'property':("lundi"),'input-class':("check")],-1)
printHtmlPart(7)
invokeTag('field','f',65,['bean':("alerteInstance"),'property':("mardi"),'input-class':("check")],-1)
printHtmlPart(7)
invokeTag('field','f',68,['bean':("alerteInstance"),'property':("mercredi"),'input-class':("check")],-1)
printHtmlPart(8)
invokeTag('field','f',71,['bean':("alerteInstance"),'property':("jeudi"),'input-class':("check")],-1)
printHtmlPart(7)
invokeTag('field','f',77,['bean':("alerteInstance"),'property':("vendredi"),'input-class':("check")],-1)
printHtmlPart(7)
invokeTag('field','f',80,['bean':("alerteInstance"),'property':("samedi"),'input-class':("check")],-1)
printHtmlPart(9)
invokeTag('field','f',83,['bean':("alerteInstance"),'property':("dimanche"),'input-class':("check")],-1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1417463325724L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
