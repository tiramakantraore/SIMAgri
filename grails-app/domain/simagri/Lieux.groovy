package simagri


class Lieux implements Serializable{

	String nom
	BigDecimal longitude=0.0
	BigDecimal latitude=0.0
    static searchable = {
        commune parent: true, reference: true
    }
    Commune commune
	static constraints = {
        nom(blank:false)
        commune(nullable:true)
		longitude nullable:true, scale: 10, bindable: false
		latitude nullable:true, scale: 10,bindable: false

	}

	String toString()
	{
		"${nom} "
	}
    static mapping = {
        sort "nom"
        cache true
    }
}
