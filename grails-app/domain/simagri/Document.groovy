package simagri

class Document {
    String body
    String footer1
    String footer2
    String footer3
    String footer4
    byte[] bodyImg
    byte[] footerImg1
    byte[] footerImg2
    byte[] footerImg3
    byte[] footerImg4
    String bodyImgfilename
    String footerImg1filename
    String footerImg2filename
    String footerImg3filename
    String footerImg4filename
    Date uploadDate = new Date()
    static constraints = {
        footerImg1filename(blank:true,nullable:true)
        footerImg2filename(blank:true,nullable:true)
        footerImg3filename(blank:true,nullable:true)
        footerImg4filename(blank:true,nullable:true)
        bodyImgfilename(blank:true,nullable:true)
        body nullable:true, maxSize: 2500
        bodyImg(blank: true, nullable:true, maxSize:1073741824)
        footerImg1(blank: true, nullable:true, maxSize:1073741824)
        footerImg2(blank: true, nullable:true, maxSize:1073741824)
        footerImg3(blank: true, nullable:true, maxSize:1073741824)
        footerImg4(blank: true, nullable:true, maxSize:1073741824)
        footer1 nullable:true, maxSize: 1000
        footer2 nullable:true, maxSize: 1000
        footer3 nullable:true, maxSize: 1000
        footer4 nullable:true, maxSize: 1000


    }
}