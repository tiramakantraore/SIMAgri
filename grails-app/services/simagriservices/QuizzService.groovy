package simagriservices

import grails.converters.JSON
import groovy.json.JsonSlurper
import simagri.Choix
import simagri.OptionChoixMultiple
import simagri.QuestionChoixMultiple
import simagri.Quizz
import simagri.Sondage
import org.grails.databinding.SimpleMapDataBindingSource
import static groovy.json.JsonParserType.LAX as RELAX
class QuizzService {
    def grailsApplication
    def messageSource  //pour acceder aux traductions
    def myUtilityService

    def execute(params) {
        try {


            Map formData = JSON.parse(params.formData?.toString()) as Map

            def quizzInstance = Quizz.findByTitre(formData.titreQuizz)?:new Quizz()
            quizzInstance.titre=formData.titreQuizz
            quizzInstance.description=formData.description
            quizzInstance.actif=true



            def questions = formData.findAll { it.key.startsWith("questions_") }.sort { it.key }.collect {
                def keyId= it.key-"questions_"
                def number=keyId.find{/\d+/}
                [numquestion:number,name:it.key-"questions_$number.",value:it.value]
            }.groupBy {it.numquestion}

            def questions3=[],reponses=[],reponsesOk=[],questReponse=[]
            questions.eachWithIndex{question,i->

                 question.value.each {q->

                     if (q.name.startsWith("ok_"))
                     {
                         def keyId= q.name-"ok_"
                         def numberOk=keyId.find{/\d+/}
                         if (q.value)
                         reponsesOk+=[numero:numberOk,isOk:q.name-"ok_$numberOk.",value:q.value]
                     }else
                     if (q.name.startsWith("rep_"))
                     {
                        if (q.value){
                            def keyId= q.name-"rep_"
                            def numberRep=keyId.find{/\d+/}
                            reponses+=[numquestion:q.numquestion,numero:numberRep,reponse:q.name-"rep_$numberRep.",value:q.value]
                        }
                     }else
                      {
                           questions3+=[question:q.name,value:q.value]
                      }
               }

                def questionStruct=[question:"",commentaireBonneReponse:"",commentaireMauvaiseReponse:""]

                def listReponses=[]
                questions3.each {questGrp->
                    questionStruct[questGrp.question]=questGrp.value
                }
                reponses.each {rep->
                    def reponseStruct=[reponse:"",isOk:false,numquestion:0]
                    reponseStruct.reponse=rep.value
                    def isOk=reponsesOk.findAll{repOk->repOk.numero==rep.numero}
                    reponseStruct["isOk"]=isOk?true:false
                    reponseStruct.numquestion=rep.numquestion
                    listReponses<<reponseStruct
                }
                questReponse<<[numquestion:i,question:questionStruct,reponses:listReponses]

            }


            questReponse.each {quesrep->
//                println " Question N° ${quesrep.numquestion} "
//                println "${quesrep}"
//                println "   ----   "

                def qcm=new QuestionChoixMultiple([question:quesrep.question.question,
                actif:true,commentaireBonneReponse:quesrep.question.commentaireBonneReponse,
                commentaireMauvaiseReponse: quesrep.question.commentaireMauvaiseReponse])

                quesrep.reponses.eachWithIndex{item,i->

                    if (item.numquestion.toInteger()==quesrep.numquestion.toInteger()) {
                    qcm.addToOptions([answerOption:item.reponse,correctOption:item.isOk,numOrdre:i])
                    }
                }
                quizzInstance.addToQuestions(qcm)
              }
            quizzInstance.save(failOnError: true)
         }catch (Exception e) {
            log.error  " exception ${e}"
            return  false
        }
    }

}
