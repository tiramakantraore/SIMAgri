import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_prix_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/prix/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'prix.label', default: 'Prix'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("show.prix")],-1)
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
invokeTag('message','g',16,['code':("title.prix")],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('message','g',20,['code':("list.prix")],-1)
printHtmlPart(6)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
invokeTag('message','g',26,['code':("create.prix")],-1)
printHtmlPart(6)
})
invokeTag('link','g',27,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',37,['code':("show.prix")],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',41,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(12)
invokeTag('display','f',44,['bean':("prixInstance"),'property':("produit")],-1)
printHtmlPart(12)
invokeTag('display','f',45,['bean':("prixInstance"),'property':("marche")],-1)
printHtmlPart(12)
invokeTag('display','f',46,['bean':("prixInstance"),'property':("date")],-1)
printHtmlPart(12)
invokeTag('display','f',47,['bean':("prixInstance"),'property':("montant")],-1)
printHtmlPart(12)
invokeTag('display','f',48,['bean':("prixInstance"),'property':("mesure")],-1)
printHtmlPart(12)
invokeTag('display','f',49,['bean':("prixInstance"),'property':("categorieTarifaire")],-1)
printHtmlPart(12)
invokeTag('display','f',50,['bean':("prixInstance"),'property':("statut")],-1)
printHtmlPart(12)
invokeTag('display','f',51,['bean':("prixInstance"),'property':("mesure")],-1)
printHtmlPart(12)
invokeTag('display','f',52,['bean':("prixInstance"),'property':("commentaire")],-1)
printHtmlPart(12)
invokeTag('display','f',53,['bean':("prixInstance"),'property':("commentaireAdministrateur")],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',56,['name':("id"),'value':(prixInstance?.id)],-1)
printHtmlPart(14)
invokeTag('hiddenField','g',58,['name':("update"),'value':(update)],-1)
printHtmlPart(15)
invokeTag('render','g',59,['template':("/partials/btnShow"),'model':([update:$update,instance:prixInstance])],-1)
printHtmlPart(16)
})
invokeTag('form','g',61,[:],2)
printHtmlPart(17)
})
invokeTag('captureBody','sitemesh',66,[:],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418942595268L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
