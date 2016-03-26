package simagri


class Quizz implements Serializable{
    String titre
    String description
    Boolean actif=false
    static constraints = {
        titre nullable:false, maxSize:250, unique:true
        description nullable:true, maxSize:255
        questions nullable:true
        actif nullable:true
    }
    static hasMany = [questions:QuestionChoixMultiple]

    String toString() {
        titre
    }
    def toJSON(){

        return QuestionChoixMultiple.toJSON(this)

    }

}
