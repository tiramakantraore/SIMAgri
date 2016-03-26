package simagri

class UploadExcellFileController {
    def userImportService
    def marketImportService
    def productImportService
    def indexMarches() {
    }
    def indexProduits() {
    }
    def indexUsers() {

        [ecraserDoublons:true]
    }
    def uploadProduits(){
        try {

            def f = request.getFile('myFile')
            def webRootDir = servletContext.getRealPath("/")
            if (f?.empty) {
                flash.message = 'Vous devez sélectionner un fichier'
                render(view: 'index')
            }

            def okcontents = ['excel/xls', 'excel-x/xlsx','application/vnd.ms-excel']
            if (! okcontents.contains(f.getContentType())) {
                flash.message = "Le type du fichier doit être compris dans: ${okcontents}"
                render(view: 'index')
            }

            def fileName="produit_".uniquify(".xls")
            def dir=new File(webRootDir+"excell")//some path...
            if(!dir.exists()){
                dir.mkdirs()
            }
            def produitDir = new File(webRootDir+"excell",fileName)

            f.transferTo(produitDir)
           productImportService.importFile(produitDir.absolutePath)
            redirect(controller:"home", action:"accueil")

        }catch (Exception e){
             flash.message = message(code: 'echec.importation')+" cause : ${e}"
			 redirect(controller:"home", action:"accueil")
        }
    }
    def uploadMarches(){
        try {

            def f = request.getFile('myFile')
            def webRootDir = servletContext.getRealPath("/")
            if (f?.empty) {
                flash.message = 'Vous devez sélectionner un fichier'
                render(view: 'index')
            }

            def okcontents = ['excel/xls', 'excel-x/xlsx','application/vnd.ms-excel']
            if (! okcontents.contains(f.getContentType())) {
                flash.message = "Le type du fichier doit être compris dans: ${okcontents}"
                render(view: 'index')
            }

            def fileName="marche_".uniquify(".xls")
            def dir=new File(webRootDir+"excell")//some path...
            if(!dir.exists()){
                dir.mkdirs()
            }
            def marcheDir = new File(webRootDir+"excell",fileName)

            f.transferTo(marcheDir)
            marketImportService.importFile(marcheDir.absolutePath)
            redirect(controller:"home", action:"accueil")
        }catch (Exception e){
             flash.message = message(code: 'echec.importation')+" cause : ${e}"
			 redirect(controller:"home", action:"accueil")
			 }
    }
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    def uploadUsers() {


            def ecraserDoublons=params.ecraserDoublons?true:false

            def listeParam=params?.ReseauxIds?.tokenize(',')?.collect{it as Long}
            def idReseaux=toList(listeParam?:[])

            def reseauxIdList =[]
            if (idReseaux) {
                    reseauxIdList=Reseau.createCriteria().list(){
                        inList("id",idReseaux)
                    }
            }
       if (reseauxIdList?.size()>0) {
       try {
        def f = request.getFile('myFile')
        def webRootDir = servletContext.getRealPath("/")
        if (f?.empty) {
            flash.message = 'Vous devez sélectionner un fichier'
            render(view: 'index')
        }

        def okcontents = ['excel/xls', 'excel-x/xlsx','application/vnd.ms-excel']
        if (! okcontents.contains(f.getContentType())) {
            flash.message = "Le type du fichier doit être compris dans: ${okcontents}"
            render(view: 'index')
        }

        def fileName="user_".uniquify(".xls")
        def dir=new File(webRootDir+"excell")//some path...
            	      if(!dir.exists()){
               	         dir.mkdirs()
                         println "Le dossier n''existe pas "
               	      }
        def userDir = new File(webRootDir+"excell",fileName)

        f.transferTo(userDir)
       userImportService.importFile(userDir.absolutePath,params,ecraserDoublons)
        redirect(controller:"home", action:"accueil")

        }catch (Exception e){
             flash.message = message(code: 'echec.importation')+" cause : ${e}"
             redirect(controller:"uploadExcellFile", action:"indexUsers")

        }
       }else {
           flash.message=message(code: 'default.reseauNonSelectionne.message')
           redirect(controller:"uploadExcellFile", action:"indexUsers")
       }
    }
}
