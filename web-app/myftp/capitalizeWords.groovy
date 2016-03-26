
/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 27/08/13
 * Time: 01:02
 * To change this template use File | Settings | File Templates.
 */
List words=[]
this.args.each{arg->
    //noinspection GroovyAssignabilityCheck
    arg.split().each{
        words.add(it.toUpperCase())
    }
}

return words

