import simagri.NoteMarche
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_noteMarche_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/noteMarche/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',7,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('display','f',11,['bean':("noteMarcheInstance"),'property':("titre")],-1)
printHtmlPart(5)
if(true && (noteMarcheInstance.photo)) {
printHtmlPart(6)
invokeTag('imageWithText','bill',17,['texte':("Photo"),'imageURL':(createLink(controller: 'noteMarche', action: 'showImg',params:[id:noteMarcheInstance?.id])),'imageHeight':("100")],-1)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('display','f',21,['bean':("noteMarcheInstance"),'property':("contenu")],-1)
printHtmlPart(6)
invokeTag('display','f',22,['bean':("noteMarcheInstance"),'property':("auteur")],-1)
printHtmlPart(6)
invokeTag('display','f',23,['bean':("noteMarcheInstance"),'property':("date")],-1)
printHtmlPart(6)
invokeTag('display','f',24,['bean':("noteMarcheInstance"),'property':("dateExpiration")],-1)
printHtmlPart(6)
invokeTag('display','f',25,['bean':("noteMarcheInstance"),'property':("actif")],-1)
printHtmlPart(6)
invokeTag('display','f',26,['bean':("noteMarcheInstance"),'property':("longitude")],-1)
printHtmlPart(6)
invokeTag('display','f',27,['bean':("noteMarcheInstance"),'property':("latitude")],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',31,['name':("id"),'value':(noteMarcheInstance?.id)],-1)
printHtmlPart(9)
invokeTag('render','g',32,['template':("/partials/btnShow")],-1)
printHtmlPart(10)
})
invokeTag('form','g',34,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458353401545L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
