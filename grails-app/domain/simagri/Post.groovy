package simagri

class Post implements Serializable{
    static searchable = true              // <-- Make Posts searchable
    static hasMany = [comments: Comment]
    String category, title, post
    Utilisateur author
    Date createdAt
    static constraints = {
    }
}
