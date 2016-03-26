package simagri

class S3Asset implements Serializable {
    static transients = ["tmpfile", 'fullBucketName', 'options']
    transient def myAWSService
    static mapping = {
        key column: 'aws_key'
        status column: 'aws_status', index: 'idx_asset_status'
        optionList lazy: false
        sort id:"desc"
    }

    public static final String DEFAULT_LOCAL_ASSETS = 's3-plugin-temp'
    public static final String STATUS_BLANK = "blank"
    public static final String STATUS_TO_BE_REMOVED = "to-be-removed"
    public static final String STATUS_REMOVED = "removed"
    public static final String STATUS_NEW = "new"
    public static final String STATUS_INPROGRESS = "inprogress"
    public static final String STATUS_HOSTED = "hosted"
    public static final String STATUS_MISSING_FILE = "missing-local-file"
    public static final String STATUS_ERROR = 'error'
    public static final String DEFAULT_BUCKET_NAME = "simagri-files"

    public static final String SEPARATOR = "."

    static hasMany = [optionList: S3AssetOption]

    //persisted properites
    String localPath
    String localUrl

    /*
   The bucket to store this resource in. It will be prefixed with your S3 access key
   when uploaded to to S3 to ensure it is unique.
    */
    String key
    String bucket
    String title
    String description
    String mimeType
    String protocol = "http://"
    String originalFilename
    String status = STATUS_BLANK
    Long length
    Long lastModified
    Double percentTransferred
    Long bytesPerSecond
    Long bytesTransfered
    Map options
    String hostName // the host name of the server that created the asset

    //transient props
    File tmpfile

    static constraints = {
        localPath(nullable: true)
        title(nullable: true)
        percentTransferred(nullable: true)
        hostName(nullable: true)
        bytesPerSecond(nullable: true)
        bytesTransfered(nullable: true)
        description(nullable: true)
        length(nullable: true)
        lastModified(nullable: true)
        originalFilename(nullable: true)
        status nullable:true
        key nullable:true
    }

    S3Asset() {
    }

    S3Asset(File file) {
        newFile(file);
    }

    S3Asset(File file, Map options) {
        newFile(file, options);
    }

    def newFile(File file) {
        newFile(file, [:]);
    }

    //method to setup s3 asset for upload with custom options for upload

    def newFile(File file, Map options) {
        this.tmpfile = file;
        this.length = file.length();
        this.lastModified = file.lastModified();
        this.localPath = this.tmpfile.canonicalPath;
        this.localUrl =this.tmpfile.name
        this.optionList = parseOptions(options);
        this.status=STATUS_HOSTED

    }

    private List parseOptions(Map inMap) {
        def out = []
        inMap.each {k, v ->
            out << new S3AssetOption(name: k.toString(), value: v?.toString(), asset: this)
        }
        out
    }

    // Done for backwards compatibility

    public Map getOptions() {
        if (options == null) {
            options = [:]
            optionList.each {
                options[it.name] = it.value
            }
        }
        options
    }

    //read only S3 url
    transient url = {
        return url()
    }

    String url() {
        return  myAWSService.url(this.key)
    }
    def getFullBucketName(){
      return  myAWSService.getFullBucketName()
    }

    /**
     Returns the full bucket name which is <access key>.<bucket>
     */


    String toString() {
        return "Document ${title}"
    }



}
