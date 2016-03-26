package simagri

class QuestionChoixMultiple implements Serializable{
    String question
    Boolean actif=true
    String commentaireBonneReponse
    String commentaireMauvaiseReponse
    Quizz quiz
    static constraints = {
        question(nullable:false,blank:false,maxSize:1000)
        options(nullable:true)
        actif(nullable:false)
        commentaireBonneReponse(nullable:true,maxSize:1000,widget:'textarea')
        commentaireMauvaiseReponse(nullable:true,maxSize:1000,widget:'textarea')
        quiz nullable:false
    }
    static hasMany=[options:OptionChoixMultiple]
    static belongsTo=[quiz:Quizz]

    String toString(){
        question
    }

    static def  toJSON(Quizz theQuiz){
        def info=[:]
        info.name="<p><span>${theQuiz.titre}</span></p>"
        info.main="<p>${theQuiz.description?:""}</p>"
        info.results=""
        info.level1="Le meilleur score"
        info.level2="Presque le meilleur score"
        info.level3="Score moyen"
        info.level4="presque le plus faible score"
        info.level5="Le plus faible score"
        def listeQuestions=createCriteria().list() {
            eq('quiz',theQuiz)

        }

        def listOfJqueryQues=[]
        listeQuestions.each{ques->

            def jqueryQues=[:]
            jqueryQues.q=ques.question
            def optionsCorrectes=OptionChoixMultiple.createCriteria().list() {
                eq('question',ques)

            }

            def listReponses=[]
            optionsCorrectes.each{option->
                listReponses<<option.toJSON()
            }
            jqueryQues.a=listReponses
            jqueryQues.correct="<p><span>Bonne réponse!</span> ${ques.commentaireBonneReponse?:""}</p>"
            jqueryQues.incorrect="<p><span>Oh non!</span> ${ques.commentaireMauvaiseReponse?:""}</p>"

            listOfJqueryQues.add(jqueryQues)

        }
      def jsonData=  [info:info,questions: listOfJqueryQues]
      return jsonData
    }

}
