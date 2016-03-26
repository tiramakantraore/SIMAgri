import grails.util.Holders
import grails.plugin.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layoutsspringSecurityUI_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/springSecurityUI.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=utf-8")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',11,['default':("Security Management Console")],-1)
})
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir:'images',file:'favicon.ico'))
printHtmlPart(4)
invokeTag('javascript','asset',14,['src':("application.js")],-1)
printHtmlPart(5)
invokeTag('stylesheet','asset',15,['src':("application")],-1)
printHtmlPart(5)
invokeTag('stylesheet','asset',16,['src':("custom")],-1)
printHtmlPart(0)
invokeTag('resources','s2ui',17,['module':("spring-security-ui")],-1)
printHtmlPart(6)
expressionOut.print(fam.icon(name: 'lock'))
printHtmlPart(7)
expressionOut.print(fam.icon(name: 'group'))
printHtmlPart(8)
expressionOut.print(fam.icon(name: 'user'))
printHtmlPart(9)
expressionOut.print(fam.icon(name: 'exclamation'))
printHtmlPart(10)
expressionOut.print(fam.icon(name: 'information'))
printHtmlPart(11)
invokeTag('layoutHead','g',26,[:],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',26,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(0)
invokeTag('render','g',27,['template':("/layouts/mainHeader")],-1)
printHtmlPart(0)
invokeTag('render','g',27,['template':("/layouts/menu")],-1)
printHtmlPart(12)
invokeTag('message','g',28,['code':("spring.security.ui.menu.users")],-1)
printHtmlPart(13)
createTagBody(2, {->
invokeTag('message','g',29,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',29,['controller':("user"),'action':("search")],2)
printHtmlPart(14)
createTagBody(2, {->
invokeTag('message','g',30,['code':("spring.security.ui.create")],-1)
})
invokeTag('link','g',30,['controller':("user"),'action':("create")],2)
printHtmlPart(15)
invokeTag('message','g',31,['code':("spring.security.ui.menu.roles")],-1)
printHtmlPart(13)
createTagBody(2, {->
invokeTag('message','g',32,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',32,['controller':("role"),'action':("search")],2)
printHtmlPart(14)
createTagBody(2, {->
invokeTag('message','g',34,['code':("spring.security.ui.create")],-1)
})
invokeTag('link','g',34,['controller':("role"),'action':("create")],2)
printHtmlPart(16)
if(true && (SpringSecurityUtils.securityConfig.securityConfigType?.toString() == 'Requestmap')) {
printHtmlPart(17)
invokeTag('message','g',41,['code':("spring.security.ui.menu.requestmaps")],-1)
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',49,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',49,['controller':("requestmap"),'action':("search")],3)
printHtmlPart(14)
createTagBody(3, {->
invokeTag('message','g',53,['code':("spring.security.ui.create")],-1)
})
invokeTag('link','g',54,['controller':("requestmap"),'action':("create")],3)
printHtmlPart(16)
}
printHtmlPart(18)
if(true && (SpringSecurityUtils.securityConfig.rememberMe.persistent)) {
printHtmlPart(17)
invokeTag('message','g',61,['code':("spring.security.ui.menu.persistentLogins")],-1)
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',71,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',71,['controller':("persistentLogin"),'action':("search")],3)
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('message','g',78,['code':("spring.security.ui.menu.registrationCode")],-1)
printHtmlPart(13)
createTagBody(2, {->
invokeTag('message','g',81,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',81,['controller':("registrationCode"),'action':("search")],2)
printHtmlPart(16)
if(true && (Holders.pluginManager.hasGrailsPlugin('springSecurityAcl'))) {
printHtmlPart(17)
invokeTag('message','g',82,['code':("spring.security.ui.menu.acl")],-1)
printHtmlPart(13)
invokeTag('message','g',85,['code':("spring.security.ui.menu.aclClass")],-1)
printHtmlPart(19)
createTagBody(3, {->
invokeTag('message','g',87,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',87,['controller':("aclClass"),'action':("search")],3)
printHtmlPart(20)
createTagBody(3, {->
invokeTag('message','g',88,['code':("spring.security.ui.create")],-1)
})
invokeTag('link','g',88,['controller':("aclClass"),'action':("create")],3)
printHtmlPart(21)
invokeTag('message','g',91,['code':("spring.security.ui.menu.aclSid")],-1)
printHtmlPart(19)
createTagBody(3, {->
invokeTag('message','g',92,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',92,['controller':("aclSid"),'action':("search")],3)
printHtmlPart(20)
createTagBody(3, {->
invokeTag('message','g',94,['code':("spring.security.ui.create")],-1)
})
invokeTag('link','g',94,['controller':("aclSid"),'action':("create")],3)
printHtmlPart(21)
invokeTag('message','g',95,['code':("spring.security.ui.menu.aclObjectIdentity")],-1)
printHtmlPart(19)
createTagBody(3, {->
invokeTag('message','g',99,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',99,['controller':("aclObjectIdentity"),'action':("search")],3)
printHtmlPart(20)
createTagBody(3, {->
invokeTag('message','g',101,['code':("spring.security.ui.create")],-1)
})
invokeTag('link','g',102,['controller':("aclObjectIdentity"),'action':("create")],3)
printHtmlPart(21)
invokeTag('message','g',102,['code':("spring.security.ui.menu.aclEntry")],-1)
printHtmlPart(19)
createTagBody(3, {->
invokeTag('message','g',106,['code':("spring.security.ui.search")],-1)
})
invokeTag('link','g',106,['controller':("aclEntry"),'action':("search")],3)
printHtmlPart(20)
createTagBody(3, {->
invokeTag('message','g',108,['code':("spring.security.ui.create")],-1)
})
invokeTag('link','g',108,['controller':("aclEntry"),'action':("create")],3)
printHtmlPart(22)
}
printHtmlPart(17)
invokeTag('message','g',112,['code':("spring.security.ui.menu.appinfo")],-1)
printHtmlPart(23)
createTagBody(2, {->
invokeTag('message','g',114,['code':("spring.security.ui.menu.appinfo.config")],-1)
})
invokeTag('link','g',114,['action':("config"),'controller':("securityInfo")],2)
printHtmlPart(24)
createTagBody(2, {->
invokeTag('message','g',116,['code':("spring.security.ui.menu.appinfo.mappings")],-1)
})
invokeTag('link','g',116,['action':("mappings"),'controller':("securityInfo")],2)
printHtmlPart(24)
createTagBody(2, {->
invokeTag('message','g',118,['code':("spring.security.ui.menu.appinfo.auth")],-1)
})
invokeTag('link','g',118,['action':("currentAuth"),'controller':("securityInfo")],2)
printHtmlPart(24)
createTagBody(2, {->
invokeTag('message','g',122,['code':("spring.security.ui.menu.appinfo.usercache")],-1)
})
invokeTag('link','g',122,['action':("usercache"),'controller':("securityInfo")],2)
printHtmlPart(24)
createTagBody(2, {->
invokeTag('message','g',123,['code':("spring.security.ui.menu.appinfo.filters")],-1)
})
invokeTag('link','g',123,['action':("filterChain"),'controller':("securityInfo")],2)
printHtmlPart(24)
createTagBody(2, {->
invokeTag('message','g',126,['code':("spring.security.ui.menu.appinfo.logout")],-1)
})
invokeTag('link','g',126,['action':("logoutHandler"),'controller':("securityInfo")],2)
printHtmlPart(24)
createTagBody(2, {->
invokeTag('message','g',128,['code':("spring.security.ui.menu.appinfo.voters")],-1)
})
invokeTag('link','g',128,['action':("voters"),'controller':("securityInfo")],2)
printHtmlPart(24)
createTagBody(2, {->
invokeTag('message','g',129,['code':("spring.security.ui.menu.appinfo.providers")],-1)
})
invokeTag('link','g',129,['action':("providers"),'controller':("securityInfo")],2)
printHtmlPart(25)
createTagBody(2, {->
printHtmlPart(26)
invokeTag('username','sec',135,[:],-1)
printHtmlPart(27)
createClosureForHtmlPart(28, 3)
invokeTag('link','g',135,['controller':("logout")],3)
printHtmlPart(29)
})
invokeTag('ifLoggedIn','sec',135,[:],2)
printHtmlPart(18)
createClosureForHtmlPart(30, 2)
invokeTag('ifNotLoggedIn','sec',141,[:],2)
printHtmlPart(31)
createTagBody(2, {->
printHtmlPart(32)
expressionOut.print(request.contextPath)
printHtmlPart(33)
invokeTag('switchedUserOriginalUsername','sec',143,[:],-1)
printHtmlPart(34)
})
invokeTag('ifSwitched','sec',143,[:],2)
printHtmlPart(35)
invokeTag('layoutResources','s2ui',145,['module':("spring-security-ui")],-1)
printHtmlPart(18)
invokeTag('layoutBody','g',145,[:],-1)
printHtmlPart(36)
invokeTag('render','g',145,['template':("/includes/ajaxLogin"),'plugin':("spring-security-ui")],-1)
printHtmlPart(2)
invokeTag('showFlash','s2ui',146,[:],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',146,[:],1)
printHtmlPart(37)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1415327426676L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
