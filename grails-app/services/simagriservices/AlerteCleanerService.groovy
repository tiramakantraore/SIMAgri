package simagriservices
import simagri.AlerteReseau
class AlerteCleanerService {

    def execute() {

        AlerteReseau.findAll().each{
            it.suspendre=true
            it.save()
        }


    }
}
