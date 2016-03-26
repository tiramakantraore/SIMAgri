class UrlMappings {

    static mappings = {
       
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }
  
        "403"(controller: "errors", action: "forbidden")
        "404"(controller: "home", action: "page_404")
        "500"(controller: "errors", action: "internalError")
  
    }
}
