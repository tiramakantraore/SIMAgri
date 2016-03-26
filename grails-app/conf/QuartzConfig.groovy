
quartz {
    autoStartup = false
    jdbcStore = false
    waitForJobsToCompleteOnShutdown = true
    exposeSchedulerInRepository = true

    props {
        scheduler.skipUpdateCheck = false
    }
}

environments {
    test {
        quartz {
            autoStartup = true
        }
    }
}
