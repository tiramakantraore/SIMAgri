
import com.dbconfig.ConfigProperty
import org.springframework.context.i18n.LocaleContextHolder

class SecurityFilters {
    def myUtilityService
    def messageSource
    def grailsApplication
    def filters = {
        all(controller: '*', action: '*') {
            before = {
                if (!session.country) {
                   return false
                }else
                        return true

                }


            }
            after = { model ->
              request?.isPjax =false
            }
        }
    }
}