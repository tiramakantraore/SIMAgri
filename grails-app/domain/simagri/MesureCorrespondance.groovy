package simagri

class MesureCorrespondance implements Serializable {
    Mesure mesureDepart
	Mesure mesureDestination
	BigDecimal equivalence
    static constraints = {
        mesureDepart(nullable:false)
        mesureDestination(nullable:false)
		equivalence(nullable:false, bindable: false)
    }
	String toString()
	{
		"${mesureDepart?.name} ${mesureDestination?.name} ${equivalence}"
	}
	static mapping = {
		cache true
	}
}
