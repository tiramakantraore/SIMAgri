import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_home_mySlider_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/_mySlider.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (pageAccueilInstance)) {
printHtmlPart(1)
if(true && (images)) {
printHtmlPart(2)
loop:{
int i = 0
for( image in (images) ) {
printHtmlPart(3)
if(true && (image?.canRender())) {
printHtmlPart(4)
expressionOut.print(i==0?' active':'')
printHtmlPart(5)
expressionOut.print(createLink(controller: 'monImage', action: 'showImg',params:[id:image?.id]))
printHtmlPart(6)
expressionOut.print(image.description)
printHtmlPart(7)
}
printHtmlPart(8)
i++
}
}
printHtmlPart(9)
}
printHtmlPart(10)
}
printHtmlPart(11)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pagePrix?.id]))
printHtmlPart(12)
if(true && (pageAccueilInstance?.pagePrix?.photo)) {
printHtmlPart(13)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.pagePrix?.id]))
printHtmlPart(14)
}
printHtmlPart(15)
expressionOut.print(pageAccueilInstance?.pagePrix?.nom)
printHtmlPart(16)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pagePrix?.id]))
printHtmlPart(17)
expressionOut.print(pageAccueilInstance?.pagePrix?.contenu?.ellipsify())
printHtmlPart(18)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageOffre?.id]))
printHtmlPart(12)
if(true && (pageAccueilInstance?.pageOffre?.photo)) {
printHtmlPart(13)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.pageOffre?.id]))
printHtmlPart(14)
}
printHtmlPart(19)
expressionOut.print(pageAccueilInstance?.pageOffre?.nom)
printHtmlPart(20)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageOffre?.id]))
printHtmlPart(21)
expressionOut.print(pageAccueilInstance?.pageOffre?.contenu?.ellipsify())
printHtmlPart(22)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageStock?.id]))
printHtmlPart(12)
if(true && (pageAccueilInstance?.pageStock?.photo)) {
printHtmlPart(13)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.pageStock?.id]))
printHtmlPart(14)
}
printHtmlPart(19)
expressionOut.print(pageAccueilInstance?.pageStock?.nom)
printHtmlPart(23)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageStock?.id]))
printHtmlPart(21)
expressionOut.print(pageAccueilInstance?.pageStock?.contenu?.ellipsify())
printHtmlPart(22)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageReseau?.id]))
printHtmlPart(24)
if(true && (pageAccueilInstance?.pageReseau?.photo)) {
printHtmlPart(13)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.pageReseau?.id]))
printHtmlPart(14)
}
printHtmlPart(19)
expressionOut.print(pageAccueilInstance?.pageReseau?.nom)
printHtmlPart(25)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'showPublic',params:[id:pageAccueilInstance?.pageReseau?.id]))
printHtmlPart(26)
expressionOut.print(pageAccueilInstance?.pageReseau?.contenu?.ellipsify())
printHtmlPart(27)
expressionOut.print(g.message(code:"actualites.text", default:"Actualités"))
printHtmlPart(28)
expressionOut.print(g.message(code:"documentation.text", default:"Documentation"))
printHtmlPart(29)
loop:{
int i = 0
for( info in (topNInfos) ) {
printHtmlPart(30)
expressionOut.print(info?.date)
printHtmlPart(31)
invokeTag('set','g',137,['var':("infoURL"),'value':(info?.url?:g.createLink(controller:"info", action:"showPublic", id:info.id ))],-1)
printHtmlPart(32)
expressionOut.print(infoURL)
printHtmlPart(33)
expressionOut.print(info?.titre)
printHtmlPart(34)
i++
}
}
printHtmlPart(35)
invokeTag('render','g',144,['template':("documents"),'model':(['topNDocuments':topNDocuments])],-1)
printHtmlPart(36)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1446287320596L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
