package simagritaglibs

class TextWithImageTagLib {
    static namespace = "bill"
    def  imageWithText = { attrs, body ->
        def texte=attrs.remove('texte')
        def imageUrl=attrs.remove('imageURL')?:""
        def imagePosition=(attrs.remove('imagePosition')=="NA")?"":attrs.remove('imagePosition')?:"Droite"
        def imageWidth=attrs.remove('imageWidth')
        def imageHeight=attrs.remove('imageHeight')


        //     if(!referenceCollection){throw new Exception(message(code:"mytagLib.referenceCollection.missingAttribute", args:'format') as Throwable)}
//        if(!texte){throw new Exception(message(code:"TextWithImageTagLib.texte.missingAttribute", args:'format') as Throwable)}
        if(imagePosition && !imageUrl){throw new Exception(message(code:"TextWithImageTagLib.imageUrl.missingAttribute", args:'format') as Throwable)}
        out <<render(template: "/base/textWithImage",
                model: [texte: texte?:"",imageWidth:imageWidth?:0,imageHeight:imageHeight?:0,imagePosition:imagePosition,
                        imageUrl:imageUrl,wrappingClass:(imagePosition=="Droite")?"textwraptoRight":((imagePosition=="Haut")?"textwraptoTop":((imagePosition=="Bas")?"textwraptoBottom":"textwraptoLeft"))])


    }

}
