package simagriservices

import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.transfer.Upload
import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.beans.factory.InitializingBean
import simagri.S3Asset
import simagri.S3AssetOption

class MyAWSService implements InitializingBean{

    def grailsApplication
    def myUtilityService
    def amazonWebService
    def messageSource
    private static config
    private static hostName
    def mimeTypeMap = [
            "image/png": "png",
            "image/jpeg": "jpg",
            "image/gif": "gif",
            "image/tiff": "tiff",
            "application/pdf": "pdf",
            "video/mpeg": "mpeg",
            "video/mp4": "mp4",
            "video/quicktime": "mov",
            "video/x-ms-wmv": "wmv",
            "text/html": "html",
            "text/xml": "xml",
            "audio/mpeg": "mp3",
            "application/octet-stream": "flv",
            "application/vnd.ms-excel":"xls",
            "vnd.openxmlformats-officedocument.wordprocessingml.document":"docx",
            "application/doc":"doc",
            "application/rtf":"rtf"]
    void afterPropertiesSet() {
        config = grailsApplication.config
        def passwd = config.aws.secretKeyEncryptPassword ?: 'aws.secretKeyEncryptPassword'
        buildLocalAssetPath()
        try {
            hostName = InetAddress.getLocalHost().getHostName()
        } catch (UnknownHostException uhe) {
        }
    }
    def delete(S3Asset s3Asset){
        amazonWebService.s3.deleteObject(getFullBucketName() as String, s3Asset.key)
    }
    private buildLocalAssetPath() {
        def res = new File(getLocalAssetPathFromConfig())
        if (!res.exists()) {
            res.mkdir()
        }
    }

    def getFullBucketName(){
        return  grailsApplication.config.grails.plugin.aws.credentials.bucket
    }
    def buildObjectKey(name, path) {

        def objectKey = name
        if (path) {
            if (!path.endsWith("/"))
                path = "${path}/"
            objectKey = "${path}${name}"
        }
        return objectKey
    }
    String url(String name, String path = null) {
        def objectKey = buildObjectKey(name, path)
        return "http://${getFullBucketName()}.s3.amazonaws.com/${objectKey}"
    }
    File getNewTmpLocalFile(String mimeType) {
        Map map = mimeTypeMap
        def ext = null


        if (map.containsKey(mimeType)) {
            ext = "." + map[mimeType]
        }

        File.createTempFile("s3Asset", ext, getLocalAssetPath())
    }
    private getLocalAssetPath() {

        new File(getLocalAssetPathFromConfig())
    }

    private def getLocalAssetPathFromConfig() {
        def path=grailsApplication.config.grails.plugin.aws.localAssetPath ?: S3Asset.DEFAULT_LOCAL_ASSETS
        return path
    }

    public uploadDocument(file,params,options) {
        def s3Asset = new S3Asset(params)
        if(!s3Asset.hasErrors()) {
       //     def grailsWebRequest = WebUtils.retrieveGrailsWebRequest()
// request is the HttpServletRequest
//            def request = grailsWebRequest.getCurrentRequest()
//            def flash = grailsWebRequest.attributes.getFlashScope(request)
            s3Asset.mimeType = file.contentType;
            //create temporary file to hold submitted file
            File tmp = getNewTmpLocalFile(file.contentType)
            String flashMes
            def docName = file?.originalFilename

            file.transferTo(tmp)
            s3Asset.newFile(tmp)
            s3Asset.mimeType = file.contentType
            s3Asset.originalFilename = docName
            s3Asset.key= docName
            s3Asset.bucket = grailsApplication.config.grails.plugin.aws.credentials.bucket


            def optionList = []
            options.each {
                optionList << new S3AssetOption(name: it.key, value: it.value, asset: s3Asset)
            }
            // s3Asset.optionList << optionList
            s3Asset.save(flush:true)
        //    def buckets = amazonWebService.s3.listBuckets()
         //   buckets.each {bucket ->
           //     println "bucketName: ${bucket.name}, creationDate: ${bucket.creationDate}"
           // }
             try {


                PutObjectRequest putObjectRequest = new PutObjectRequest(s3Asset.bucket, "${file.originalFilename}", tmp).withCannedAcl(CannedAccessControlList.PublicRead)
                Upload upload = amazonWebService.transferManager.upload(putObjectRequest)

                while (!upload.done) {
                        flashMes ="""
                       Transfer: $upload.description
                          - State: $upload.state
                          - Progress: $upload.progress.bytesTransfered
                          - mime type :$s3Asset.mimeType
                        """
            //    println flashMes
                 //   flash.message=flashMes
                    // Do work while we wait for our upload to complete…
                    Thread.sleep(500)
                }

            //    amazonWebService.s3.putObject(new PutObjectRequest(s3Asset.bucket, "${file.originalFilename}", tmp).withCannedAcl(CannedAccessControlList.PublicRead))
         //   Upload upload = amazonWebService.transferManager.upload(new PutObjectRequest(s3Asset.bucket, "${file.originalFilename}", tmp).withCannedAcl(CannedAccessControlList.PublicRead))

//            if (Environment.current == Environment.DEVELOPMENT ){
//
//             while (!upload.done) {
//                 flashMes ="""
//                       Transfer: $upload.description
//                          - State: $upload.state
//                          - Progress: $upload.progress.bytesTransfered
//                          - mime type :$s3Asset.mimeType
//                        """
//                println flashMes
//                         Thread.sleep(100)
//            }

             //   log.info("s3Asset.bucket ${s3Asset.bucket}  file.originalFilename   ${file.originalFilename} ");
        } catch (Exception e) {
            log.debug("Unable to upload file, upload was aborted. ${e}");
                e.printStackTrace();
        }
//            }
        }

    }



}
