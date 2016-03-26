package simagri

import com.dbconfig.ComparedProperty
import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.dao.DataIntegrityViolationException

class ConfigPropertyController {
	
	def grailsApplication
    def exportService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	def list() {
        renderList()
	}

    def renderList() {

        def fileProperties = grailsApplication.flatConfig
        def comparedProperties = fileProperties.collect {configKey, value ->
            def dbProperty = ConfigProperty.findByConfigKey(configKey)
            def isInDb = dbProperty? true : false
            def dbId = dbProperty? dbProperty.id : null
            def currentPro = dbProperty? dbProperty.value?.toString() :  grailsApplication.flatConfig[configKey]?.toString()

            new ComparedProperty(dbId, configKey.toString(), value.toString(), dbProperty?.value?.toString(), currentPro, isInDb)
        }

        ConfigProperty.list().each {

            if(!grailsApplication.flatConfig[it.configKey] && !"".equals(grailsApplication.flatConfig[it.configKey])){
                grailsApplication.flatConfig[it.configKey] = ""
                def comparedProperty = new ComparedProperty(it.id, it.configKey.toString(), null, it.value?.toString(), it.value?.toString(), true)
                if(!comparedProperties.contains(comparedProperty)){
                    comparedProperties.add(comparedProperty)
                }
            }
        }

        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename"+"Configs".uniquify(".${params.extension}"))

            List fields = ["configKey","fileProperty","dbProperty","currentProperty"]
            Map labels = ["configKey":"Clé paramètre","fileProperty":"Valeur fichier", "dbProperty": "Valeur base de données"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }
            Map formatters = [code:humanCase]
            Map parameters = [title: "LISTE DES PARAMETRES", "column.widths": [0.1,0.3, 0.25, 0.35],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, comparedProperties, fields, labels,formatters,parameters)

        }


        render template:'list',model:[comparedProperties: comparedProperties, totalNum: comparedProperties.size()]
    }
    def create() {
        render(template: "create", model: [configPropertyInstance: new ConfigProperty(params)])
    }

    def save() {
        def configPropertyInstance = new ConfigProperty(params)
        if (!configPropertyInstance.save(flush: true)) {
            render(template: "create", model: [configPropertyInstance: configPropertyInstance])
        }
		
		def properties = grailsApplication.flatConfig
		session.setAttribute("DBCfgProperties", properties)

        flash.message = message(code: 'default.created.message', args: [message(code: 'configProperty.label', default: 'ConfigProperty'), configPropertyInstance.id])
        render(template: "show", model: [configPropertyInstance: new ConfigProperty(params)])
    }

    def show(Long id) {
        def configPropertyInstance = ConfigProperty.get(id)
        if (!configPropertyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configProperty.label', default: 'ConfigProperty'), id])
            renderList()
        }
        render(template: "show", model: [configPropertyInstance: new ConfigProperty(params)])
    }

    def edit(Long id) {
        def configPropertyInstance = ConfigProperty.get(id)
        if (!configPropertyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configProperty.label', default: 'ConfigProperty'), id])
            redirect(action: "list")
        }

        render template:'edit',model:[configPropertyInstance: configPropertyInstance]
    }

    def update(Long id, Long version) {
        def configPropertyInstance = ConfigProperty.get(id)
        if (!configPropertyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configProperty.label', default: 'ConfigProperty'), id])
            renderList()
        }

        if (version != null) {
            if (configPropertyInstance.version > version) {
                configPropertyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'configProperty.label', default: 'ConfigProperty')] as Object[],
                          "Another user has updated this ConfigProperty while you were editing")
                render(template: "edit", model: [configPropertyInstance: configPropertyInstance])
            }
        }

        configPropertyInstance.properties = params

        if (!configPropertyInstance.save(flush: true)) {
            render(template: "edit", model: [configPropertyInstance: configPropertyInstance])
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'configProperty.label', default: 'ConfigProperty'), configPropertyInstance.id])
        render(template: "show", model: [configPropertyInstance: configPropertyInstance])
    }

    def delete(Long id) {
        def configPropertyInstance = ConfigProperty.get(id)
        if (!configPropertyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configProperty.label', default: 'ConfigProperty'), id])
            renderList()
        }

        try {
            configPropertyInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'configProperty.label', default: 'ConfigProperty'), id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'configProperty.label', default: 'ConfigProperty'), id])
            render(template: "show", model: [configPropertyInstance: configPropertyInstance])
        }
    }
	
	def addToFrequentlyUsedList() {
		
		def configProperty = new ConfigProperty(params.configKey?.toString(), params.value?.toString(), "")
		configProperty.save(flush:true)
		
		render configProperty.id
	}
	
	def compare() {
		def fileProperties = grailsApplication.flatConfig
		def dbProperties = ConfigProperty.list()
		
		def comparedProperties = dbProperties.collect {
			def dbProperty = it.value
			def fileProperty = fileProperties[it.configKey]
			
			new ComparedProperty(it.configKey?.toString(), dbProperty?.toString(), fileProperty?.toString(), null)
		}
		
		[comparedProperties: comparedProperties]
	}
}

class ComparedProperty {
    def dbId
    String configKey
    String fileProperty
    String dbProperty
    String currentProperty
    boolean isInDb

    public ComparedProperty (def dbId, String configKey, String fileProperty, String dbProperty, String currentProperty, boolean isInDb) {
        this.dbId = dbId
        this.configKey = configKey
        this.fileProperty = fileProperty
        this.dbProperty = dbProperty
        this.currentProperty = currentProperty
        this.isInDb = isInDb
    }
}
