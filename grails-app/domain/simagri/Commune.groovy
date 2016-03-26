package simagri


class Commune implements Serializable{
	String nom
	Province province	

	static constraints = {
        nom(blank:false,unique:true)
		province(nullable:false)

	}
    static searchable = {
        province parent: true, reference: true
    }
	String toString()
	{
		"${nom}"
	}
    static mapping = {
        sort "nom"
    }
    static belongsTo = Province
}
