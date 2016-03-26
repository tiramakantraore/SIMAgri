package simagri

class SMSLogger implements Serializable{

    Boolean isIn
    String message
	String direction
    Utilisateur user
    Reseau reseau
    String fromPhone
    String toPhone
    String operateur
	Integer dureeEnS
    String semaine   //provide the exact column name of the date field
    String mois
    String trimestre
    String annee
    Date date
    static transients = ['theUserMobilePhone','theUser','theOperator']
    static constraints = {
        message nullable:false, blank:false, maxSize:2000
        fromPhone nullable:true, maxSize:25
        toPhone nullable:true , maxSize:25
		dureeEnS nullable:true
        operateur nullable:true, maxSize:45
		direction nullable:true, maxSize:45
        reseau(nullable:true)
    }
    String getFromPhonePrefix() {
        return fromPhone?.toString()?.getPhonePrefix()

    }
    String getToPhonePrefix() {
        return toPhone?.toString()?.getPhonePrefix()

    }
    String getTheUserMobilePhone() {
        return user?.mobilePhone?:(isIn?fromPhone:toPhone)
    }
    String getTheUser() {
        return user?.toString()?:(isIn?fromPhone:toPhone)
    }
    String getOperatorPrefix() {
        if (isIn)  {
            return getFromPhonePrefix()
        }else {
            return getToPhonePrefix()
        }


    }
    String getTheOperator() {

        return operateur?:(user?.operateur?:Operateur.getTheOperator(theUserMobilePhone?.toString()?.getPhonePrefix()))

    }

    static mapping={
        sort date:"desc"
        semaine formula :'WEEK(date)'    //provide the exact column name of the date field
        mois formula :'MONTH(date)'
        trimestre formula :'QUARTER(date)'
        annee formula :'YEAR(date)'

    }
    static belongsTo = [user:Utilisateur]
}
