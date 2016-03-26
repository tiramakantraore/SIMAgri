grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.7
grails.project.source.level = 1.7

//Remove the JDBC jar before the war is bundled
//grails.war.resources = { stagingDir ->
//    delete(file:"${stagingDir}/META-INF/context.xml")
//}
//grails.project.dependency.resolver = "maven"  or ivy
grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
	
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
      
        grailsCentral()
        mavenCentral()
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
      
	  

        runtime 'org.imgscalr:imgscalr-lib:4.2'
        compile "org.xhtmlrenderer:core-renderer:R8"
        runtime 'mysql:mysql-connector-java:5.1.29'
        build 'org.apache.httpcomponents:httpcore:4.2'
        build 'org.apache.httpcomponents:httpclient:4.2'
        compile 'org.grails.plugins:logback:0.3.1'
//        runtime 'org.apache.httpcomponents:httpcore:4.2'
//        runtime 'org.apache.httpcomponents:httpclient:4.2'
        // runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
      //  test "org.grails:grails-datastore-test-support:1.0-grails-2.4"
    }


    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.55"

        // plugins for the compile step
        compile ":scaffolding:2.1.2"
        compile ":cache:1.1.8"
        runtime ":jquery:1.9.1"
        compile ":asset-pipeline:1.9.9"
       // compile ":export:1.6"
   //     compile ":less-asset-pipeline:1.10.0"

        //       compile ":asset-pipeline:1.9.4"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate4:4.3.5.5"
        runtime ":database-migration:1.4.0"
        runtime ":resources:1.2.8"

        compile ":quartz:1.0.0"
        compile ":aws-sdk:1.9.9.1"
            compile ":less-asset-pipeline:1.9.0"
        // Uncomment these to enable additional asset-pipeline capabilities
        compile ":sass-asset-pipeline:1.7.0"
        //compile ":handlebars-asset-pipeline:1.3.0.3"
        compile (":bootstrap-file-upload:2.1.2" ){
            excludes "jquery","jqueryui"
        }
        compile ":google-analytics:2.3.3"
        compile ":phonenumbers:0.9"
        compile ":joda-time:1.5"
        compile ":spring-security-core:2.0-RC4"
        compile ":spring-security-ui:1.0-RC2"
        compile ":spring-security-facebook:0.15.2-CORE2"
        compile ":fields:1.1"
        //  compile ":filterpane:2.2.1"
        compile ":webxml:1.4.1"
        compile ":tropo-webapi-grails:0.2.1"
        compile ":mail:1.0.7"
        compile ":asynchronous-mail:1.2"
        compile ":wslite:0.7.2.0"
        compile ":admin-interface:0.6.5"
        compile ":easygrid:1.6.3"

      //  compile ":jquery-ui:1.10.3"
      compile ":google-visualization:0.7"
      compile ":ckeditor:4.4.1.0"
        compile ":excel-export:0.2.1"
        compile ":executor:0.3"
        compile ":standalone:1.3"
        compile ":sendgrid:1.3"
        compile ":hd-image-utils:1.0"
        compile ":mongo-file:1.4.0"  
       compile ":dynamic-db-config-property:0.4.0.9"
    }

}
