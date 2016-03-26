package simagri

class SendsmstestController {
    def smsDecoderService
	def sendSMSService
    def index() {
        def message=params?.message?:""
        def from_phone=params?.from_addr?:""

        def resultat=smsDecoderService.decodeIt(message,from_phone)
		def messageToVusion
		if (resultat)
        messageToVusion = resultat
        else
		messageToVusion = "response:the response"
		//render messageToVusion
	//	sendSMSService.execute(messageToVusion,from_phone)

    }
}
