import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_dynamicBlocksindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dynamicBlocks/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(2)
createClosureForHtmlPart(4, 2)
invokeTag('javascript','g',10,[:],2)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('block','dynamic',30,['itemId':("fullName"),'min':("2"),'max':("5"),'addBtnId':("addFullName"),'removeBtnLabel':("Delete"),'removeOffset':("4"),'onComplete':("initDatePicker"),'limitReachedMsg':("Limit is exceeded!"),'template':("/partials/elem")],-1)
printHtmlPart(8)
createTagBody(3, {->
printHtmlPart(9)
invokeTag('textField','g',38,['name':("skill"),'placeholder':("Skill name")],-1)
printHtmlPart(10)
})
invokeTag('block','dynamic',42,['itemId':("skills"),'min':("1"),'max':("5"),'addBtnId':("addSkills"),'removeBtnLabel':("Suppr.")],3)
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(12)
invokeTag('radio','g',49,['value':("1"),'name':("radioGroup"),'checked':("checked")],-1)
printHtmlPart(13)
invokeTag('radio','g',51,['value':("2"),'name':("radioGroup")],-1)
printHtmlPart(14)
})
invokeTag('block','dynamic',52,['itemId':("radioInputsTest"),'min':("4")],3)
printHtmlPart(15)
invokeTag('submitButton','g',54,['name':("submit"),'value':("Submit"),'style':("display: block;")],-1)
printHtmlPart(5)
})
invokeTag('form','g',54,['controller':("dynamicBlocks"),'action':("submitAction"),'style':("margin: 100px;")],2)
printHtmlPart(5)
invokeTag('deferredScripts','asset',54,[:],-1)
printHtmlPart(5)
})
invokeTag('captureBody','sitemesh',55,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442325558621L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
