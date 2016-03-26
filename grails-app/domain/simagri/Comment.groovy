package simagri

class Comment implements Serializable{
    static searchable = true              // <-- Make Comments searchable
    static belongsTo = [post: Post]
    String comment
    Post post
    Utilisateur user
    Date createdAt
    static constraints = {
    }
}
