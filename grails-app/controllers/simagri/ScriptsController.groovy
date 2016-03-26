package simagri

class ScriptsController {

    def common() {
        StringBuffer buffer=new StringBuffer()
        def thequiz=Quizz.findByActif(true)
        def jquizMe=  thequiz?.toJquery()
        buffer.append(jquizMe)
        render  buffer.toString()
    }
}
