import grails.util.Holders
import org.apache.log4j.*
// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = true
cache.headers.enabled = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text-plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      swf:'application/x-shockwave-flash',
                      pdf: 'application/pdf',
                      rtf: 'application/rtf',
                      excel: 'application/vnd.ms-excel',
                      ods: 'application/vnd.oasis.opendocument.spreadsheet',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
]


// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"

// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'
nomPlateforme='SIMAgri'

// GSP settings
//grails {
//    views {
//        gsp {
//            encoding = 'UTF-8'
//            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
//            codecs {
//                expression = 'html' // escapes values inside ${}
//                scriptlet = 'html' // escapes output from scriptlets in GSPs
//                taglib = 'none' // escapes output from taglibs
//                staticparts = 'none' // escapes output from static template parts
//            }
//        }
//        // escapes all not-encoded output at final stage of outputting
//        // filteringCodecForContentType.'text/html' = 'html'
//    }
//}



grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false
indicatif="226"
grails.defaultCountry="bfa"
grails.vusionURL="http://simagri.texttochange.org:2230"
grails.vusionPath="/sendsms/"
grails.vusionshortCode="3144"
javamelody.disabled = true
environments {
    development {
        grails.logging.jul.usebridge = true
        grails.plugin.fields.disableLookupCache = true
        grails.assets.minifyJs = false

      //  grails.serverURL = "http://localhost:8080/SIMAgri"
    }
    devmali {
        grails.logging.jul.usebridge = true
        grails.plugin.fields.disableLookupCache = true
        grails.assets.minifyJs = false
        indicatif="223"
        grails.defaultCountry="ml"
        //  grails.serverURL = "http://localhost:8080/SIMAgri"
    }
    production {
        grails.logging.jul.usebridge = false
        grails.assets.minifyJs = true
        // TODO: grails.serverURL = "http://www.changeme.com"
    }

}

//// log4j configuration
//log4j.main = {
//    // Example of changing the log pattern for the default console appender:
//    //
//    //appenders {
//    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
//    //}
//  //  debug 'org.hibernate.SQL'
////    trace 'org.hibernate.type'
//    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
//            'org.codehaus.groovy.grails.web.pages',          // GSP
//            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
//            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
//            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
//            'org.codehaus.groovy.grails.commons',            // core / classloading
//            'org.codehaus.groovy.grails.plugins',            // plugins
//            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
//            'org.springframework',
//            'org.hibernate',
//            'net.sf.ehcache.hibernate'
//   // Enable the Quartz plugin logging
//    debug 'grails.plugins.quartz'
//    rollingFile name: "myAppender", maxFileSize: 2048,
//            file: "/var/logs/simagri.log",
//            datePattern: "'.'yyyy-MM-dd",   //Rollover at midnight each day.
//            layout: logLayoutPattern
//
//}

myconfig {
    myvariable {
        workdir = 0
    }
}

log4j = {
    def pattern = new PatternLayout("[%p] [%c{3}] %m%n")

    appenders {
        appender new DailyRollingFileAppender(
                name:"file",
                file:"${Holders.config.myconfig.myvariable.workdir}/app.log",
                layout: pattern,
                datePattern: "'.'yyyy-MM-dd")

        rollingFile name:"stacktrace",
                file:"${Holders.config.myconfig.myvariable.workdir}/stacktrace.log",
                maxFileSize:'100KB'

        console name:"stdout",
                layout: pattern
    }

    root {
        environments {
            production {
                error "file"
            }
            development {
                error "file", "stdout"
            }
        }
    }

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
            'org.codehaus.groovy.grails.web.pages',          // GSP
            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
            'org.codehaus.groovy.grails.commons',            // core / classloading
            'org.codehaus.groovy.grails.plugins',            // plugins
            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'

    error 'grails.plugins.quartz'
    error 'org.hibernate.SQL'

}
grails.hibernate.cache.queries = false
grails.gorm.autoFlush = false
grails.gorm.failOnError = false
grails.slogan=""" SIMAgri : Système d'Information de Marché
                   Agricole. C'est une plateforme communautaire WEB to SMS.
                   Pour plus d'information visitez htpp://www.simagri.net """
grailsApplication.config.breadcrumbs.crumbs.max=5
grails.json.legacy.builder = false
grails.views.javascript.library="jquery"
breadcrumbs.home=grails.serverURL

myftp.dir = '/myftp/'
grails.plugin.springsecurity.requestMap.className = 'simagri.Requestmap'
grails.plugin.springsecurity.securityConfigType = SecurityConfigType.Requestmap
// Added by the Spring Security Core plugin:

grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
grails.plugin.springsecurity.successHandler.alwaysUseDefaultTargetUrl = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/'
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.fii.rejectPublicInvocations = false
//rememberMe {
//    cookieName = 'grails_remember_me'
//    alwaysRemember = false
//    tokenValiditySeconds = AbstractRememberMeServices.TWO_WEEKS_S // 1209600 -> 14 days
//    parameter = AbstractRememberMeServices.DEFAULT_PARAMETER // '_spring_security_remember_me'
//    key = 'grailsRocks'
//    persistent = true
//    persistentToken {
//        DomainClassName = 'simagri.TokenSecurite'
//        seriesLength = PersistentTokenBasedRememberMeServices.DEFAULT_SERIES_LENGTH // 16
//        tokenLength = PersistentTokenBasedRememberMeServices.DEFAULT_TOKEN_LENGTH // 16
//    }
//    useSecureCookie = true
//    createSessionOnSuccess = true
//}
//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//	'/':                              ['permitAll'],
//	'/index':                         ['permitAll'],
//	'/index.gsp':                     ['permitAll'],
//	'/assets/**':                     ['permitAll'],
//	'/**/js/**':                      ['permitAll'],
//	'/**/css/**':                     ['permitAll'],
//	'/**/images/**':                  ['permitAll'],
//	'/**/favicon.ico':                ['permitAll']
//]
//def base = "admin"
//failureHandler.defaultFailureUrl = '/login/authfail?login_error=1'
//failureHandler.ajaxAuthFailUrl = '/login/authfail?ajax=true'
//grails.plugin.admin.accessRoot = "/$base"
//grails.plugin.admin.allowDefaultConfig = true
//grails.plugin.admin.security.role = "ROLE_ADMIN"
//grails.plugin.admin.domains."Paramètres" = [
//        "simagri.Utilisateur",
//        "simagri.Reseau",
//        "simagri.Produit",
//        "simagri.CategorieProduit"
//]
//grails.plugin.admin.domain.Utilisateur = {
//    list includes: ['mobilePhone','operateur','login','reseaux','reseaux','mesProduits','mesMarches','mesMagazins']
//    create excludes: ['password','enabled','accountExpired','accountLocked','passwordExpired']
//    edit excludes: ['password','enabled','accountExpired','accountLocked','passwordExpired']
//}

//grails.assets.less.compile = 'less4j'
//grails.assets.excludes = ["**/*.less","**/*.css","**/*.png","**/*.jpg","**/*.gif","**/*.md","**/*.htm"
//                          ,"**/*.eot","**/*.svg","**/*.ttf","**/*.woff","**/*.ico","**/*.bmp","**/*.html"]

//grails.assets.plugin."twitter-bootstrap".excludes = ["**/*.less"]
//grails.assets.plugin."twitter-bootstrap".includes = ["bootstrap.less","variables.less"]
//
//grails.assets.plugin."famfamfam".excludes = ["**/*.png"]
grails.assets.plugin."twitter-bootstrap".excludes = ["bootstrap.css","**/*.less"]

//grails.assets.plugin."font-awesome-resources".excludes = ["**/*.less"]
//grails.assets.plugin."jquery-ui".excludes = ["**/*.less"]
nomCourtPlateforme="SIMAgri"
grails.plugins.twitterbootstrap.fixtaglib = true
grails.assets.bundle=true
//grails.plugins.twitterbootstrap.defaultBundle = 'bundle_bootstrap'  ,"myjscomponents/*.*","mycsscomponents/*.*"
grails.executeBootstrap=true
grails.plugin.awssdk.accessKey ="AKIAJGMHE4UZ7GT7UVSA"
grails.plugin.awssdk.secretKey ="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"
//grails.plugin.aws.credentials.bucket="amassamali" pour le mali
grails.plugin.aws.credentials.bucket="aprossabf"

//
//grails {
//    plugin {
//        aws {
//            credentials {
//                accessKey = "AKIAJGMHE4UZ7GT7UVSA"
//                secretKey = "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"
//            }
//            s3 {
//                bucket = "aprossabf"
//            }
//        }
//    }
//}

grails.gmail.username="simagriaprossa@gmail.com"
grails.gmail.password="simagri2013"
grails {
    plugin {
        awssdk {
            connectionTimeout = 30000
             socketTimeout = 30000
        }
    }
}
grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = grails.gmail.username
        password = grails.gmail.password
        props = ["mail.smtp.auth":"true",
                 "mail.smtp.socketFactory.port":"465",
                 "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                 "mail.smtp.socketFactory.fallback":"false"]
    }
}
grails.mail.default.from="simagri <simagriaprossa@gmail.com>"

grails.plugin.aws.localAssetPath='./s3-plugin-temp/'
grails.map.latitude=12.238333
grails.map.zoom=7
grails.map.longitude=-1.561593


//
//
//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//        '/**/fonts/**':                 ['permitAll']
//]
//ckeditor {
//    config = "/js/config.js"
//    skipAllowedItemsCheck = false
//    defaultFileBrowser = "ofm"
//    upload {
//        basedir = "/uploads/"
//        overwrite = true
//        link {
//            browser = true
//            upload = true
//            allowed = ['html', 'htm']
//            denied = [ 'php', 'php2', 'php3', 'php4', 'php5',
//                      'phtml', 'pwml', 'inc', 'asp', 'aspx', 'ascx', 'jsp',
//                      'cfm', 'cfc', 'pl', 'bat', 'exe', 'com', 'dll', 'vbs', 'js', 'reg',
//                      'cgi', 'htaccess', 'asis', 'sh', 'shtml', 'shtm', 'phtm']
//        }
//        image {
//            browser = true
//            upload = true
//            allowed = ['jpg', 'gif', 'jpeg', 'png']
//            denied = []
//        }
//        flash {
//            browser = false
//            upload = false
//            allowed = ['swf']
//            denied = []
//        }
//    }
//}



grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.DomainClassName = 'simagri.TokenSecurite'
//In Config.groovy
grails.plugins.remotepagination.max=10
//EnableBootstrap here when using twitter bootstrap, default is set to false.
grails.plugins.remotepagination.enableBootstrap=true

grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
grails.plugin.springsecurity.successHandler.alwaysUseDefaultTargetUrl = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/'
file.upload.directory= '/temp/'
DefaultCurrency="XOF"
devise="FCFA"

grails.maskTelephone="~99999999999"


//asynchronous.mail.default.attempt.interval=300000l       // Five minutes
//asynchronous.mail.default.max.attempts.count=1
//asynchronous.mail.send.repeat.interval=60000l           // One minute
//asynchronous.mail.expired.collector.repeat.interval=607000l
//asynchronous.mail.messages.at.once=100
//asynchronous.mail.send.immediately=true  // since 0.1.2
//asynchronous.mail.override=false    // since 0.2.0
//asynchronous.mail.clear.after.sent=false    // since 0.2.0
//asynchronous.mail.disable=fasle    // since 0.7
//asynchronous.mail.useFlushOnSave=true
//asynchronous.mail.persistence.provider='hibernate' // Possible values are 'hibernate', 'hibernate4', 'mongodb'
//asynchronous.mail.gparsPoolSize=1
//asynchronous.mail.newSessionOnImmediateSend=false

google.analytics.webPropertyID = ["UA-42756665-1","UA-58698859-1"]
google.analytics.enabled = true

