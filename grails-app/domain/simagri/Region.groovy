package simagri


class Region implements Serializable{
   	String nom
    static constraints = {

        nom(blank:false)
        provinces(nullable:true)

    }
    static searchable = {
        provinces component:true
        only= 'nom'
    }
	static hasMany=[provinces:Province]

	String toString()
	{
		"${nom}"
	}
    static mapping = {
        sort "nom"
        cache true
    }
    Set<Marche>  getMarches(){
        try {
            return Marche.findAllByRegion(this).collect{it} as Set
        }
        catch( e){
            log.debug(e)
            return []
        }
    }

}
