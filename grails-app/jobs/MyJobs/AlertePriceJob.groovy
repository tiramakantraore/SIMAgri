package MyJobs


class AlertePriceJob {
    def pushPricesReseauService
    def pushOffresReseauService
    static triggers = {
      //simple name:'simpleTrigger', startDelay:1000, repeatInterval: 50001
        // cronExpression: "s m h D M W Y"
      cron name:'cronTrigger',  cronExpression: "0 0 7 * * ?", group:'triggers'
   //     cron name:'cronTrigger', startDelay:10000, cronExpression: '0 0 0/4 1/1 * ?'

    }
    def group = "myJobs"

    def execute() {

        pushPricesReseauService?.fire()

        println "Job executed successfully "
    }

}
