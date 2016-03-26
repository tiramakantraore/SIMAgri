import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_questionChoixMultiple_option_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/questionChoixMultiple/_option.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(i)
printHtmlPart(1)
if(true && (hidden)) {
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('render','g',7,['template':("/questionChoixMultiple/optionsQCM"),'model':(['questionChoixMultipleInstance':questionChoixMultipleInstance,'i':i])],-1)
printHtmlPart(4)
invokeTag('hiddenField','g',11,['name':("options[${i}]?.id"),'value':(option?.id)],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',12,['name':("options[${i}]?.deleted"),'value':("false")],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',13,['name':("options[${i}]?.new"),'value':(option?.id == null?'true':'false')],-1)
printHtmlPart(7)
expressionOut.print(i)
printHtmlPart(8)
expressionOut.print(option?.id)
printHtmlPart(9)
invokeTag('textField','g',18,['name':("options[${i}]?.answerOption"),'maxlength':("150"),'required':("true"),'value':(option?.answerOption),'autocomplete':("off")],-1)
printHtmlPart(10)
invokeTag('checkBox','g',20,['name':("options[${i}]?.correctOption"),'maxlength':("150"),'value':(option?.correctOption),'autocomplete':("off")],-1)
printHtmlPart(11)
invokeTag('image','asset',24,['src':("skin/icon_delete.png"),'style':("vertical-align:middle;width:60%;height:60%"),'alt':("Suppr.")],-1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1411978689634L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
