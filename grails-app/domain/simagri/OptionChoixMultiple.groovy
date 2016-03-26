package simagri

class OptionChoixMultiple implements Serializable{
    String answerOption
    Boolean correctOption=false
    Integer numOrdre=0
    Boolean deleted
    static transients = [ 'deleted']
    static constraints = {
        answerOption(nullable:false,blank:false,maxSize:150)
        correctOption(nullable:false)
        numOrdre(blank:false, min:0)
    }
    String toString(){
        def reponseCorrecte=correctOption?"Bonne réponse":""
        return "(${numOrdre}) ${answerOption} ${reponseCorrecte}"
    }
    def toJSON(){
        def rep=[:]
        rep.option=answerOption
        rep.correct=correctOption
        return rep
    }
    static belongsTo=[question:QuestionChoixMultiple]
    static mapping = {
        sort "numOrdre"
    }
}
