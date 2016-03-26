package simagritaglibs

class RadioboxListTagLib {
  // static defaultEncodeAs = [taglib: 'html']
    static namespace = "bill"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def radioBoxList = { attrs, body ->
        def referenceCollection=attrs.remove('referenceCollection')
        def instanceName=attrs.remove('instanceName')?:"null"
        def onClickRadio=attrs.remove('onClickRadio')?:"null"
        def title=attrs.remove('title')?:""
        def isArray=attrs.remove('isArray')?:""
        def radioName=attrs.remove('radioName')?:""
        def defaultValue=attrs.remove('defaultValue')?:""
        def isEnum=attrs.remove('isEnum')?:false
        def limitWidth=attrs.remove('limitWidth')
        def emptyText=attrs.remove('emptyText')
       // if(!referenceCollection){throw new Exception(message(code:"mytagLib.referenceCollection.missingAttribute", args:'format') as Throwable)}
        if(!instanceName){throw new Exception(message(code:"mytagLib.instanceName.missingAttribute", args:'format') as Throwable)}
        if (referenceCollection) {
          out << render(template: "/base/radiolist",
                    model: [referenceCollection: referenceCollection?:[],isArray:isArray,radioName:radioName,limitWidth:limitWidth,
                            containerClass:attrs.containerClass?:"my1Columns",title:title,defaultValue:defaultValue,isEnum:isEnum,
                            liClass:attrs.liClass?:"unliststyle",labelClass:attrs.labelClass?:"labelClass", instanceName:instanceName,onClickRadio:onClickRadio])

        }else {
            out<< "<p class=\"note_pas_de_donnees\"> ${emptyText?:g.message(code:"pasDeDonnes","Il n y a pas de données à afficher veuillez vérifier vos paramètres")}</p>"
        }


    }

}
