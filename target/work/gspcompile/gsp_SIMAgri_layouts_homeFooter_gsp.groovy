import simagri.TypePartenaire
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_homeFooter_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_homeFooter.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('image','asset',7,['src':("logo_ICCO_90x35px.png"),'height':("35px")],-1)
})
invokeTag('link','g',7,['controller':("pageUtilisateur"),'action':("showByPartnerType"),'params':([typePartenaire: 'ICCO']),'title':("Partenaire ICCO"),'class':("partnersLogo")],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('image','asset',9,['src':("logo_IICD_90x35px.png"),'height':("35px")],-1)
})
invokeTag('link','g',9,['controller':("pageUtilisateur"),'action':("showByPartnerType"),'params':([typePartenaire:'IICD']),'title':("Partenaire IICD"),'class':("partnersLogo")],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('image','asset',11,['src':("logo_TTC_90x35px.png"),'height':("35px")],-1)
})
invokeTag('link','g',11,['controller':("pageUtilisateur"),'action':("showByPartnerType"),'params':([typePartenaire:'TTC']),'title':("Partenaire TEXT TO CHANGE"),'class':("partnersLogo")],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('image','asset',13,['src':("logo_TFK_45x35px.png"),'height':("35px")],-1)
})
invokeTag('link','g',13,['controller':("pageUtilisateur"),'action':("showByPartnerType"),'params':([typePartenaire:'TFK']),'title':("Partenaire INTER-PROFESSION TABLE FILIERE KARITE"),'class':("partnersLogo")],1)
printHtmlPart(2)
invokeTag('set','g',14,['var':("typePartenaire"),'value':("$session.typePartenaire")],-1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('image','asset',17,['src':("$session.footerLogo"),'height':("35px")],-1)
})
invokeTag('link','g',17,['controller':("pageUtilisateur"),'action':("showByPartnerType"),'params':([typePartenaire:typePartenaire]),'title':("Partenaire ${session.organisation?.toUpperCase()}"),'class':("partnersLogo")],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('image','asset',19,['src':("logo_Bamig_45x35px.png"),'height':("35px")],-1)
})
invokeTag('link','g',19,['controller':("pageUtilisateur"),'action':("showByPartnerType"),'params':([typePartenaire:'BAMIG']),'title':("Partenaire BAMIG"),'class':("partnersLogo")],1)
printHtmlPart(5)
expressionOut.print(developer?:'Joël Hyacinthe TRAORE')
printHtmlPart(6)
expressionOut.print(session.organisation?:'Aprossa Afrique Verte')
printHtmlPart(7)
expressionOut.print(createLink(uri: '/partenaires'))
printHtmlPart(8)
expressionOut.print(session.organisation?:'Aprossa Afrique Verte')
printHtmlPart(9)
expressionOut.print(g.message(code:"telephone.text", default:"Téléphone"))
printHtmlPart(10)
expressionOut.print(session.TelOrganisation?:'+22650341139')
printHtmlPart(11)
if(true && (session.FaxOrganisation)) {
printHtmlPart(12)
expressionOut.print(session.FaxOrganisation)
printHtmlPart(11)
}
printHtmlPart(13)
expressionOut.print(session.MobileOrganisation?:'+22670723552')
printHtmlPart(14)
expressionOut.print(session.EmailOrganisation)
printHtmlPart(15)
expressionOut.print(session.EmailOrganisation)
printHtmlPart(16)
if(true && (session.EmailOrganisation2)) {
printHtmlPart(17)
expressionOut.print(session.EmailOrganisation2)
printHtmlPart(15)
expressionOut.print(session.EmailOrganisation2)
printHtmlPart(16)
}
printHtmlPart(18)
invokeTag('image','asset',62,['src':("cereal_footer.png")],-1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1445487226551L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
