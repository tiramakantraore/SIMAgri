package simagri

class Opportunite implements Serializable{
    String name
	String description
	Date dateBegin
	Integer delaiInDays
    Date expirationDate
    Utilisateur publisher
    Utilisateur author
    static constraints = {
		name(blank:false,unique:true)
		description(blank:false,maxSize:255,widget:'textarea')
		dateBegin(nullable:true,widget:'datePicker')
		delaiInDays(blank:true)
        expirationDate(nullable:true,widget:'datePicker')
        publisher(blank:false,nullable:false)
    }
	String toString()
	{
		"${name}"
	}
}