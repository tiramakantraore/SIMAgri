package simagritaglibs

import de.andreasschmitt.export.taglib.util.RenderUtils
import groovy.xml.MarkupBuilder

class VisualizeTagLib {

        static namespace = "visualize"

        def formats = { attrs ->
            StringWriter writer = new StringWriter()
            def builder = new groovy.xml.MarkupBuilder(writer)

            if(!attrs?.'class'){
                attrs.'class' = "export"
            }


            List formats = ['histo', 'camembert']
            if(attrs?.formats){
                formats = new ArrayList(attrs.formats)
                attrs.remove("formats")
            }

            Map parameters = [:]
            if(attrs?.params){
                parameters = new HashMap(attrs.params)
                attrs.remove("params")
            }

            Map extensions = [excel: "xls"]

            builder."div"(attrs){
                formats.each { format ->
                    span("class": "menuButton"){
                        String message = ""

//                        try {
//                            message = g.message(code: 'default.' + format)
//                            if(message == 'default.' + format){
//                                message = format.toUpperCase()
//                            }
//                        }
//                        catch(Exception e) {
//                            message = format.toUpperCase()
//                        }

                        // Extension defaults to format
                        String extension = format
                        if(extensions.containsKey(format)){
                            extension = extensions[format]
                        }

                        Map params = [format: format, extension: extension] + parameters

                        a('class': format, href: "/", message)
                    }
                }
            }

            writer.flush()
            out << writer.toString()
        }



}
