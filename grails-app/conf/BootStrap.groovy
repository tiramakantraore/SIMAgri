import grails.util.Environment
import org.codehaus.groovy.runtime.DateGroovyMethods
import org.joda.time.DateTime
import simagri.Event
import simagri.EventRecurType
import simagri.MyJobConfig
import simagri.Requestmap
import simagri.Reseau
import simagri.SecRole
import simagri.SecUserSecRole
import simagri.Utilisateur
import simagriclasses.Rounding
import java.text.Normalizer
import java.util.regex.Matcher
import java.util.regex.Pattern
import static org.joda.time.DateTimeConstants.MONDAY
import static org.joda.time.DateTimeConstants.WEDNESDAY
import static org.joda.time.DateTimeConstants.FRIDAY
import java.text.NumberFormat
import static java.util.TimeZone.setDefault
import com.dbconfig.ConfigProperty
class BootStrap {
    def messageSource
    def grailsApplication
    def quartzScheduler
    def springSecurityService

    def init = { servletContext ->
        for (dc in grailsApplication.domainClasses) {
            dc.clazz.metaClass.getGrailsApplication = { -> grailsApplication }
            dc.clazz.metaClass.static.getGrailsApplication = { -> grailsApplication }
        }
        setDefault(TimeZone.getTimeZone("UTC"))
        createJobConfig()

//        Environment.executeForCurrentEnvironment {
//            production {
//                // do something in production
//            }
//            development {
//                // do something only in development
//            }
//            pre_production {
//                // do something for your custom environment
//            }
//        }

        quartzScheduler.start()

        ConfigProperty.list()*.updateConfigMap()
        if (grailsApplication.config.grails.executeBootstrap) {
            configureSuperAdmin()
        }

        setToAdmin()

    }

    public configureSuperAdmin() {
        def enqueteurRole=SecRole.findByAuthority("ROLE_ENQUETEUR")?:new SecRole(authority:"ROLE_ENQUETEUR").save(flush:true)

       


        ]
        listOfRequestMap.each{ requestmap->
            Requestmap.findByUrlAndConfigAttribute(requestmap.url, requestmap.config) ?: new Requestmap(url: requestmap.url, configAttribute: requestmap.config).save(flush: true)

        }
        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            new Requestmap(url: url, configAttribute: 'permitAll').save()
        }
        def listeEnqueteurs=Utilisateur.findAllByRole("Enqueteur")
        listeEnqueteurs.each{enqueteur->
                SecUserSecRole.removeAll(enqueteur)
            SecUserSecRole.create(enqueteur, enqueteurRole)

        }

        def superAdminRole = SecRole.findByAuthority("ROLE_SUPER_ADMIN") ?: new SecRole(authority: "ROLE_SUPER_ADMIN").save(flush: true)
        def reseauPrincipal = Reseau.get(1);
     


    }

    private void createJobConfig(){
        def myJobConfigInstance
        if (MyJobConfig.count==0)
            myJobConfigInstance= new MyJobConfig(operationId:"pause",jobClass:"MyJobs.AlertePriceJob",groupJob:"myJobs",cron:"0 * * * 1/5 ?").save(flush:true)
        else
            myJobConfigInstance=MyJobConfig.first()

    }
    private void buildDevelopmentStuff(){
        // Creating dates for our test events
        def now = new DateTime()
        def tomorrow = now.plusDays(1)

// Creating a weekly event that occurs every MWF
        def event = new Event(title: 'Repeating MWF Event').with {
            startTime = now.toDate()
            endTime = now.plusHours(1).toDate()
            location = "Regular location"
            recurType = EventRecurType.WEEKLY
            [MONDAY, WEDNESDAY, FRIDAY]*.toInteger().each { addToRecurDaysOfWeek(it) }
            addToExcludeDays(now.withDayOfWeek(MONDAY).plusWeeks(1).toDate())
            isRecurring = true
            save(flush: true)
        }

// Non-repeating single event that replaces the one excluded next Monday
        def event2 = new Event(title: event.title).with {
            sourceEvent = event
            startTime = event.startTime
            endTime = event.endTime
            location = "New one-time location"
            isRecurring = false
            save()
        }

// Plain old non-repeating event
        def event3 = new Event(title: 'Just a normal event').with {
            startTime = tomorrow.toDate()
            endTime = tomorrow.plusMinutes(30).toDate()
            isRecurring = false
            save()
        }
    }
    private static void setToAdmin() {

         def list = (1..30).toList()
     assert list == [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
              18, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
      assert list.paginate( 5, 7 ) == [8, 9, 10, 11, 12]
      assert list.paginate( 5 )    == [1, 2, 3, 4, 5]
       assert list.paginate( 5, 0 ) == [1, 2, 3, 4, 5]
       assert list.paginate( 5, 1 ) == [2, 3, 4, 5, 6]

    }

    def destroy = {
    }
}
