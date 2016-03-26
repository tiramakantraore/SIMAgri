package simagri

class SmsToPhone implements Serializable{
    String toPhoneNumber
    String yourTextMessage
    static constraints = {
        toPhoneNumber(blank: false,maxSize:25)
        yourTextMessage(blank: false,maxSize:255,widget:'textarea')
    }
}
