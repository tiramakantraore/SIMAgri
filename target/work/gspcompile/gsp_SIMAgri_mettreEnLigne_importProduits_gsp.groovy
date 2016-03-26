import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mettreEnLigne_importProduits_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mettreEnLigne/_importProduits.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',1,['code':("importer.produits")],-1)
printHtmlPart(1)
invokeTag('message','g',6,['code':("selectionnerFichierExcelProduit.label"),'default':("Veuillez sélectionner le fichier excel des produits:")],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',9,['class':("alert-warning")],2)
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(createLink(controller: 'home', action: 'downloadProduitTemplate'))
printHtmlPart(7)
invokeTag('message','g',14,['code':("default.texteFormatProduit.label"),'default':("")],-1)
printHtmlPart(8)
invokeTag('render','g',16,['template':("/partials/genericBtn"),'model':([theactionName:'uploadProduits',btnClass:'btn-primary',btnName:'default.button.envoyer.label',inputField:'myProductFile',successMessage:'La mise en ligne des produits a réussie'])],-1)
printHtmlPart(9)
})
invokeTag('form','g',18,[:],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428773254030L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
