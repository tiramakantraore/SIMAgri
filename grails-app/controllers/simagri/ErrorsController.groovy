package simagri

class ErrorsController {

    def index() { 
		render(view:'/error')
	}
    def internalError() {
//        def exception = request.exception
//        render(text: "Exception in ${exception?.className}",
//                contentType: "text/plain", encoding: "UTF-8")
    }
	def notFound() { }
    def notYetDevelopped(){

    }
}
