import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_geonameshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/geoname/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'geoname.label', default: 'Geoname'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("show.geoname"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',12,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/home'))
printHtmlPart(6)
invokeTag('message','g',16,['code':("default.home.label")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',18,['code':("list.geoname"),'args':([entityName])],-1)
})
invokeTag('link','g',18,['class':("list"),'action':("list")],2)
printHtmlPart(8)
createTagBody(2, {->
invokeTag('message','g',19,['code':("create.geoname"),'args':([entityName])],-1)
})
invokeTag('link','g',19,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',24,['code':("show.geoname"),'args':([entityName])],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (geonameInstance?.geonameId)) {
printHtmlPart(14)
invokeTag('message','g',33,['code':("geoname.geonameId.label"),'default':("Geoname Id")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',36,['bean':(geonameInstance),'field':("geonameId")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.name)) {
printHtmlPart(18)
invokeTag('message','g',44,['code':("geoname.name.label"),'default':("Name")],-1)
printHtmlPart(19)
invokeTag('fieldValue','g',47,['bean':(geonameInstance),'field':("name")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.asciiname)) {
printHtmlPart(20)
invokeTag('message','g',55,['code':("geoname.asciiname.label"),'default':("Asciiname")],-1)
printHtmlPart(21)
invokeTag('fieldValue','g',58,['bean':(geonameInstance),'field':("asciiname")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.latitude)) {
printHtmlPart(22)
invokeTag('message','g',66,['code':("geoname.latitude.label"),'default':("Latitude")],-1)
printHtmlPart(23)
invokeTag('fieldValue','g',69,['bean':(geonameInstance),'field':("latitude")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.longitude)) {
printHtmlPart(24)
invokeTag('message','g',77,['code':("geoname.longitude.label"),'default':("Longitude")],-1)
printHtmlPart(25)
invokeTag('fieldValue','g',80,['bean':(geonameInstance),'field':("longitude")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.featureClass)) {
printHtmlPart(26)
invokeTag('message','g',88,['code':("geoname.featureClass.label"),'default':("Feature Class")],-1)
printHtmlPart(27)
invokeTag('fieldValue','g',91,['bean':(geonameInstance),'field':("featureClass")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.featureCode)) {
printHtmlPart(28)
invokeTag('message','g',99,['code':("geoname.featureCode.label"),'default':("Feature Code")],-1)
printHtmlPart(29)
invokeTag('fieldValue','g',102,['bean':(geonameInstance),'field':("featureCode")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.countryCode)) {
printHtmlPart(30)
invokeTag('message','g',110,['code':("geoname.countryCode.label"),'default':("Country Code")],-1)
printHtmlPart(31)
invokeTag('fieldValue','g',113,['bean':(geonameInstance),'field':("countryCode")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.cc2)) {
printHtmlPart(32)
invokeTag('message','g',120,['code':("geoname.cc2.label"),'default':("Cc2")],-1)
printHtmlPart(33)
invokeTag('fieldValue','g',123,['bean':(geonameInstance),'field':("cc2")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.population)) {
printHtmlPart(34)
invokeTag('message','g',131,['code':("geoname.population.label"),'default':("Population")],-1)
printHtmlPart(35)
invokeTag('fieldValue','g',134,['bean':(geonameInstance),'field':("population")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.elevation)) {
printHtmlPart(36)
invokeTag('message','g',142,['code':("geoname.elevation.label"),'default':("Elevation")],-1)
printHtmlPart(37)
invokeTag('fieldValue','g',145,['bean':(geonameInstance),'field':("elevation")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.dem)) {
printHtmlPart(38)
invokeTag('message','g',152,['code':("geoname.dem.label"),'default':("Dem")],-1)
printHtmlPart(39)
invokeTag('fieldValue','g',155,['bean':(geonameInstance),'field':("dem")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.timeZone)) {
printHtmlPart(40)
invokeTag('message','g',163,['code':("geoname.timeZone.label"),'default':("Time Zone")],-1)
printHtmlPart(41)
invokeTag('fieldValue','g',166,['bean':(geonameInstance),'field':("timeZone")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (geonameInstance?.modificationDate)) {
printHtmlPart(42)
invokeTag('message','g',174,['code':("geoname.modificationDate.label"),'default':("Modification Date")],-1)
printHtmlPart(43)
invokeTag('formatDate','g',177,['date':(geonameInstance?.modificationDate)],-1)
printHtmlPart(16)
}
printHtmlPart(44)
createTagBody(2, {->
printHtmlPart(45)
invokeTag('hiddenField','g',185,['name':("id"),'value':(geonameInstance?.id)],-1)
printHtmlPart(46)
createTagBody(3, {->
invokeTag('message','g',187,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',187,['class':("edit"),'action':("edit"),'id':(geonameInstance?.id)],3)
printHtmlPart(46)
invokeTag('actionSubmit','g',190,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(47)
})
invokeTag('form','g',192,['action':("profile")],2)
printHtmlPart(48)
})
invokeTag('captureBody','sitemesh',194,[:],1)
printHtmlPart(49)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1413693385333L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
