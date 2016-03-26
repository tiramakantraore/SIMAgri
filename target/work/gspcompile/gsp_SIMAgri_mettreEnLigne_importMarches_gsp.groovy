import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mettreEnLigne_importMarches_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mettreEnLigne/_importMarches.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',1,['code':("importer.marches")],-1)
printHtmlPart(1)
invokeTag('message','g',6,['code':("selectionnerFichierExcelMarche.label"),'default':("Veuillez sélectionner le fichier excel des marchés:")],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',9,['class':("alert-info")],2)
printHtmlPart(4)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(createLink(controller: 'home', action: 'downloadMarcheTemplate'))
printHtmlPart(6)
invokeTag('message','g',14,['code':("default.texteFormatMarche.label"),'default':("")],-1)
printHtmlPart(7)
invokeTag('render','g',18,['template':("/partials/genericBtn"),'model':([theactionName:'uploadMarches',
        btnClass:'btn-primary',btnName:'default.button.envoyer.label',inputField:'myMarketFile',successMessage:'La mise en ligne des marchés a réusi'])],-1)
printHtmlPart(8)
})
invokeTag('form','g',22,['action':("uploadMarches")],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428773253985L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
