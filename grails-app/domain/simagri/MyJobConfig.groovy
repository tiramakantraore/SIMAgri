package simagri

class MyJobConfig {
    String operationId
    String statut
    String jobClass="MyJobs.AlertePriceJob"
    String groupJob="myJobs"
    String cron='0/6 * * * * ?'
    static constraints = {
        operationId(nullable: false,inList:["pause","resume"])
        cron(nullable:true)
        statut(nullable:true)
    }
}
