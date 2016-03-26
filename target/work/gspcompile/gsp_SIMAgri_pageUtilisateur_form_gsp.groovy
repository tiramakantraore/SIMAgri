import simagri.TypePartenaire
import  simagri.DestinationType
import  simagri.TypeAlignement
import  simagri.Reseau
import  simagri.PageUtilisateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageUtilisateur_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageUtilisateur/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('radioBoxList','bill',8,['referenceCollection':(DestinationType?.values()[0..DestinationType?.values()?.size()-1]),'defaultValue':(pageUtilisateurInstance?.destinationType?.name()),'instanceName':("destinationType"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'isEnum':("true"),'title':("")],-1)
printHtmlPart(2)
})
invokeTag('field','f',10,['bean':("pageUtilisateurInstance"),'property':("destinationType"),'required':("true")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('radioBoxList','bill',16,['referenceCollection':(TypeAlignement?.values()[0..TypeAlignement?.values()?.size()-1]),'defaultValue':(pageUtilisateurInstance?.alignement?.name()),'instanceName':("alignement"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'isEnum':("true"),'title':("")],-1)
printHtmlPart(1)
})
invokeTag('field','f',17,['bean':("pageUtilisateurInstance"),'property':("alignement"),'required':("true")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('radioBoxList','bill',23,['referenceCollection':(TypePartenaire?.values()[0..TypePartenaire?.values()?.size()-1]),'defaultValue':(pageUtilisateurInstance?.typePartenaire?.name()),'instanceName':("typePartenaire"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'isEnum':("true"),'title':("")],-1)
printHtmlPart(6)
})
invokeTag('field','f',24,['bean':("pageUtilisateurInstance"),'property':("typePartenaire"),'required':("true")],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('textField','g',33,['name':(property),'value':(it.value),'maxlength':("250"),'required':(""),'class':("form-control")],-1)
printHtmlPart(6)
})
invokeTag('field','f',34,['bean':("pageUtilisateurInstance"),'property':("nom"),'required':("true")],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(4)
expressionOut.print(pageUtilisateurInstance?.contenu)
printHtmlPart(1)
})
invokeTag('editor','ckeditor',50,['name':(property),'height':("300px"),'width':("100%")],2)
printHtmlPart(2)
})
invokeTag('field','f',51,['bean':("pageUtilisateurInstance"),'property':("contenu")],1)
printHtmlPart(10)
invokeTag('message','g',57,['code':("monImage.photo.label"),'default':("Photo")],-1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','g',69,['type':("email"),'name':(property),'value':(it.value),'class':("form-control")],-1)
printHtmlPart(12)
})
invokeTag('field','f',71,['bean':("pageUtilisateurInstance"),'property':("email"),'required':("true")],1)
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','g',81,['type':("tel"),'name':(property),'value':(it.value),'class':("form-control")],-1)
printHtmlPart(6)
})
invokeTag('field','f',81,['bean':("pageUtilisateurInstance"),'property':("mobile"),'required':("true")],1)
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','g',92,['type':("tel"),'name':(property),'value':(it.value),'class':("form-control")],-1)
printHtmlPart(6)
})
invokeTag('field','f',92,['bean':("pageUtilisateurInstance"),'property':("telephone"),'required':("true")],1)
printHtmlPart(14)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('textArea','g',103,['name':(property),'value':(it.value),'cols':("80"),'rows':("5"),'class':("form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',103,['bean':("pageUtilisateurInstance"),'property':("description"),'required':("true")],1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423435079954L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
