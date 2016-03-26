package simagri

class Counter {

  Date dateCreated
  String name
  String user
  Utilisateur connected
  static constraints = {
      connected nullable: true
  }
}
