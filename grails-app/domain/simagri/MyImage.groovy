package simagri

/**
 * Created by Tiramakan on 28/02/14.
 */
abstract class MyImage {
    static int KO = 1024*25   //25 ko
    static int MAX_PICTURE_SIZE = 8 * KO
    Byte[]  file
    static constraints ={
        file(nullable:false, maxSize: MAX_PICTURE_SIZE)
    }
    static mapping = {
        file column:"image", sqlType: "blob"

    }
}