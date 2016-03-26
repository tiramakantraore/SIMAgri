includeTargets << grailsScript("_GrailsInit")

target(_Event: "The description of the script goes here!") {
    // TODO: Implement script here
}
eventCreateWarStart = { warName, myDir ->
    println 'EVENT CALLED!'
    grails.war.resources = { stagingDir ->
        delete(file:"${stagingDir}/META-INF/context.xml")
    }

    File libDir = new File("${myDir}/META-INF/")
    if (grailsEnv != "development") {
        libDir.eachFileMatch( ~/^(context).*\.xml/) { File jarToRemove ->
            println 'CONTEXT XML: ' + jarToRemove
            jarToRemove.delete()
        }
    }
}
setDefaultTarget(_Event)
