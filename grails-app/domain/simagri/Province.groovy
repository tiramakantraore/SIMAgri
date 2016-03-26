package simagri


class Province implements Serializable{
	String nom
	Region region

	static constraints = {
        region(nullable:false)
        nom(blank:false)

	}
    static searchable = {
        communes component:true
        region parent: true, reference: true

    }
	static hasMany=[communes:Commune]

	String toString()
	{
		"${nom}"
	}
    static belongsTo = Region
    static mapping = {
        sort "nom"
        cache true
    }
  
}
