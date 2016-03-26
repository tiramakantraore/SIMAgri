import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_priceHolder_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/priceHolder/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',6,['code':("show.priceHolder")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('display','f',17,['bean':("priceHolderInstance"),'property':("produit")],-1)
printHtmlPart(4)
invokeTag('display','f',18,['bean':("priceHolderInstance"),'property':("marche")],-1)
printHtmlPart(4)
invokeTag('display','f',19,['bean':("priceHolderInstance"),'property':("date")],-1)
printHtmlPart(4)
invokeTag('display','f',20,['bean':("priceHolderInstance"),'property':("prixGros")],-1)
printHtmlPart(4)
invokeTag('display','f',21,['bean':("priceHolderInstance"),'property':("prixDetail")],-1)
printHtmlPart(4)
invokeTag('display','f',22,['bean':("priceHolderInstance"),'property':("mesureGros")],-1)
printHtmlPart(4)
invokeTag('display','f',23,['bean':("priceHolderInstance"),'property':("mesureDetail")],-1)
printHtmlPart(4)
invokeTag('display','f',24,['bean':("priceHolderInstance"),'property':("commentaire")],-1)
printHtmlPart(4)
invokeTag('display','f',25,['bean':("priceHolderInstance"),'property':("isValidated")],-1)
printHtmlPart(4)
invokeTag('display','f',26,['bean':("priceHolderInstance"),'property':("isRejected")],-1)
printHtmlPart(4)
invokeTag('display','f',27,['bean':("priceHolderInstance"),'property':("auteur")],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',28,['name':("id"),'value':(priceHolderInstance?.id)],-1)
printHtmlPart(4)
invokeTag('hiddenField','g',29,['name':("update"),'value':(update)],-1)
printHtmlPart(4)
invokeTag('render','g',30,['template':("/partials/btnShow"),'model':([update:$update,instance:priceHolderInstance])],-1)
printHtmlPart(6)
})
invokeTag('form','g',33,[:],1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418942595337L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
