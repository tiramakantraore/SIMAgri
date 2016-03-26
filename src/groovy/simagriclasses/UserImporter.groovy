package simagriclasses

import grails.util.Environment
import simagri.Activite
import simagri.Entreprise
import simagri.Operateur
import simagri.Utilisateur
import org.codehaus.groovy.grails.web.context.ServletContextHolder as SCH
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes as GA


class UserImporter {
    def grailsApplication
    def userService
    def sendSMSService
    String fileName
    Utilisateur aUser
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    def toLong(def value){
        def valueLong=[]
        value.each{

            valueLong<<it.toLong()
        }
        return valueLong
    }
    UserImporter(String afileName)
    {
        this.fileName=afileName
        def ctx = SCH.servletContext.getAttribute(GA.APPLICATION_CONTEXT)
        sendSMSService = ctx.sendSMSService
        grailsApplication= ctx.grailsApplication
        userService=ctx.userService

    }

    void  executeImport(def params, Boolean ecraserDoublons){

       UserXLSImporter userImporter=new UserXLSImporter(fileName)
        def users=userImporter.getUsers()
    //    println "users ${users}"
        users.each{userMap->
            if ((Environment.current == Environment.DEVELOPMENT)) {
                println "utilisateur ${userMap}"
            }
            def dateOfBirth=new Date()
            if (userMap.datenaissance instanceof String) {
                if (userMap.datenaissance)
                dateOfBirth = new Date(userMap.datenaissance?:"")

            }

            if ((userMap.gender =='M') || (userMap.gender =='Masculin') || (userMap.gender =='male')) {
                userMap.gender = 'male'
            }
            else
            {
                userMap.gender = 'female'
            }

            def username="${userMap.lastName} ${userMap.firstName}"
            def entreprise
            def activite
            if (userMap.entreprise){
				entreprise = Entreprise.findByNom(userMap.entreprise)?:new Entreprise(nom:userMap.entreprise).save(flush: true)
            }else
				entreprise = Entreprise.findByNom('default')?:new Entreprise(nom:'default').save(flush: true)

            if (userMap.activite){
                Integer maxActivityCode=15
                if (userMap.activite?.toString()?.length()<15)
                    maxActivityCode=userMap.activite?.toString()?.length()
                def codeActivite=userMap.activite?.substring(0,maxActivityCode)
                activite = Activite.findByCode(codeActivite)?:new Activite(libelle:userMap.activite,code:codeActivite).save(flush: true)
            }else
                activite = Activite.findByCode('default')?:new Activite(libelle:'default',code:'default').save(flush: true)

            String thePhone=userMap.mobilePhone?.toString()?.getRightPhone()

            if ((username!=null) && thePhone) {


				if (!Utilisateur.exists(thePhone)){
                      def aUser=new Utilisateur()
                       def theData=[username:username,enabled:true,accountExpired:false,
                               accountLocked:false,passwordExpired: false,firstName:userMap.firstName,lastName:userMap.lastName,gender:userMap.gender,
                               dateOfBirth:dateOfBirth,login:"${userMap.lastName} ${userMap.firstName}",
                               mobilePhone:thePhone,entreprise:entreprise,country:userMap.country?:"bfa",email:userMap.email,town:userMap.town,ReseauxIds:params.ReseauxIds,activite:activite]
                    aUser?.properties=theData
                   if (userService?.createAndSave(theData,aUser,ecraserDoublons))  {
                       sendSMSService?.execute("Bienvenue sur la plateforme ${grailsApplication.config.nomPlateforme}, login =${theData?.login}, mobilePhone =${theData?.mobilePhone}  Mot de passe=${grailsApplication?.config?.simAgriDefaultPassword?:"simagri"}",aUser?.mobilePhone?.toString())

                        Operateur.list().each{oper->
                            if (oper.isPrefixInList(aUser?.getPrefix()?.toString()?:"")) {
                                aUser?.operateur=oper
                            }
                        }
                   }
				}else {

//                    if (ecraserDoublons) {
                         def theData=[username:username,firstName:userMap.firstName,gender:userMap.gender,lastName:userMap.lastName,activite:activite,
                                dateOfBirth:dateOfBirth,login:"${userMap.lastName} ${userMap.firstName}",town:userMap.town,country:userMap.country,entreprise:entreprise,
                                ReseauxIds:params.ReseauxIds,mobilePhone:thePhone,email:userMap.email]

                        userService?.bulkUpdate(theData,thePhone,ecraserDoublons);

//                    }

                }

            }
        }
    }


}
