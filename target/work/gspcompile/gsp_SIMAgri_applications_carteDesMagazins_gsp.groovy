import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_carteDesMagazins_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_carteDesMagazins.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',4,['code':("carte_des_magazins")],-1)
printHtmlPart(1)
invokeTag('message','g',8,['code':("tousLesMagazins.text"),'default':("Tous les magazins")],-1)
printHtmlPart(2)
invokeTag('message','g',11,['code':("aucunMagazin.text"),'default':("Aucun magazin")],-1)
printHtmlPart(3)
invokeTag('checkBoxList','bill',16,['referenceCollection':(mesMagazins),'containerClass':("${ctnerTemplateMag} limitHeight"),'instanceName':("magazins"),'onClickCheck':("magazinClick();")],-1)
printHtmlPart(4)
invokeTag('render','g',1,['template':("/applications/cartes_geo_mag_js")],-1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1448237231935L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
