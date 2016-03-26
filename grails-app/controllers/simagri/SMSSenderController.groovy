package simagri

class SMSSenderController {

    def index() {
        [smsToReseauxInstance: new SmsToReseaux(params)]
    }
}
