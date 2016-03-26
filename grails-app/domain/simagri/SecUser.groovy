package simagri

class SecUser implements Serializable {

   transient springSecurityService
   String password
   boolean enabled = true
   boolean accountExpired
   boolean accountLocked
   boolean passwordExpired

   static transients = ['springSecurityService']

   static constraints = {
       password blank: true
   }

   static mapping = {
       table 'utilisateur'
       password column: '`password`'

   }

   Set<SecRole> getAuthorities() {
       SecUserSecRole.findAllBySecUser(this).collect { it.secRole }
   }

//   def beforeInsert() {
////       encodePassword()
//   }
//
//   def beforeUpdate() {
//       if (isDirty('password')) {
//           println "password ${password} is dirty"
//           encodePassword()
//       }
//   }

   public void encodePassword() {
       def passwordClair=password
       password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
     //  println "password Clair ${passwordClair} crypté ${password}"

   }

}
