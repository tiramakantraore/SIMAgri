import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_counterindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/counter/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',3,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',3,[:],2)
printHtmlPart(1)
invokeTag('require','r',4,['modules':("counter")],-1)
printHtmlPart(1)
invokeTag('layoutResources','r',5,[:],-1)
printHtmlPart(3)
expressionOut.print(resource(dir: 'images', file: 'background.jpg'))
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(createLink(action: 'increment', controller: 'counter'))
printHtmlPart(6)
expressionOut.print(createLink(action: 'incrementRandom', controller: 'counter'))
printHtmlPart(7)
expressionOut.print(createLink(action: 'delete', controller: 'counter'))
printHtmlPart(8)
})
invokeTag('script','r',105,[:],2)
printHtmlPart(9)
})
invokeTag('captureHead','sitemesh',106,[:],1)
printHtmlPart(10)
createTagBody(1, {->
printHtmlPart(11)
expressionOut.print(totalCount)
printHtmlPart(12)
if(true && (counterList.size() == 0)) {
printHtmlPart(13)
}
else {
printHtmlPart(14)
for( counter in (counterList) ) {
printHtmlPart(15)
expressionOut.print(counter[0])
printHtmlPart(16)
expressionOut.print(counter[1])
printHtmlPart(17)
}
printHtmlPart(18)
invokeTag('counterMonthlyGraph','g',134,['countMonth':("6"),'name':(counterList[0][0]),'gridColor':("#aaa"),'graphColor':("#666"),'graphId':("counter"),'id':("graph"),'width':("980"),'onComplete':("graphReady();")],-1)
printHtmlPart(19)
}
printHtmlPart(20)
invokeTag('textField','g',142,['name':("manualIncrement")],-1)
printHtmlPart(21)
for( counter in (last100) ) {
printHtmlPart(22)
invokeTag('formatDate','g',159,['date':(counter.dateCreated),'type':("date"),'style':("LONG"),'timeStyle':("SHORT")],-1)
printHtmlPart(23)
invokeTag('formatDate','g',160,['date':(counter.dateCreated),'type':("time"),'style':("LONG"),'timeStyle':("SHORT")],-1)
printHtmlPart(24)
expressionOut.print(counter.name)
printHtmlPart(25)
}
printHtmlPart(26)
invokeTag('layoutResources','r',168,[:],-1)
printHtmlPart(9)
})
invokeTag('captureBody','sitemesh',169,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442327397892L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
