package simagriservices

import grails.util.Environment
import java.text.SimpleDateFormat

class AnalyticsService {
    // Automatically injected by Grails.
    def grailsApplication

    // Default parser to read date formats from Google Analytics XML.
    def dateParser = new SimpleDateFormat("EEEE, MMMM ddd, yyyy", Locale.US)

    def visitors() {
        // Directory with XML reports defined in Config_old.groovyoovy.
        def dir = new File(grailsApplication.config.input.dir)

        // List all files, reverse sort them on date,
        // and the top one is the newest file.
        def xmlFile = dir?.listFiles()?.sort{ file -> file?.lastModified() }.reverse()[0]

        // Read XML data.
        def report = new XmlSlurper().parse(xmlFile)

        // Fill list with number of visitor per day.
        // Data is found in XML:
        // AnalyticsReport / Report / Graph / Serie / Point / Value|Label
        def visitors = []
        report.Report.Graph[0].Serie[0].Point.each {
            visitors << [visits: new Integer(it.Value.text()),
                    date: dateParser.parse(it.Label.text())]
        }
        return visitors
    }
}