import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_carteDesMarches_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_carteDesMarches.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',3,['code':("carte_des_marches")],-1)
printHtmlPart(1)
invokeTag('message','g',7,['code':("tousLesMarches.text"),'default':("Tous les marchés")],-1)
printHtmlPart(2)
invokeTag('message','g',10,['code':("aucunMarche.text"),'default':("Aucun marché")],-1)
printHtmlPart(3)
invokeTag('checkBoxList','bill',15,['referenceCollection':(mesMarches),'containerClass':("${ctnerTemplate} limitHeight"),'instanceName':("markets"),'onClickCheck':("marcheClick();")],-1)
printHtmlPart(4)
invokeTag('render','g',34,['template':("/applications/cartes_geo_marches_js")],-1)
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1448237231887L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
