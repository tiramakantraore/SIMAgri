package simagri


import grails.test.mixin.*
import spock.lang.*

@TestFor(MonImageController)
@Mock(MonImage)
class MonImageControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.monImageInstanceList
        model.monImageInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.monImageInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def monImage = new MonImage()
        monImage.validate()
        controller.save(monImage)

        then: "The create view is rendered again with the correct model"
        model.monImageInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        monImage = new MonImage(params)

        controller.save(monImage)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/monImage/show/1'
        controller.flash.message != null
        MonImage.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def monImage = new MonImage(params)
        controller.show(monImage)

        then: "A model is populated containing the domain instance"
        model.monImageInstance == monImage
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def monImage = new MonImage(params)
        controller.edit(monImage)

        then: "A model is populated containing the domain instance"
        model.monImageInstance == monImage
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/monImage/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def monImage = new MonImage()
        monImage.validate()
        controller.update(monImage)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.monImageInstance == monImage

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        monImage = new MonImage(params).save(flush: true)
        controller.update(monImage)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/monImage/show/$monImage.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/monImage/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def monImage = new MonImage(params).save(flush: true)

        then: "It exists"
        MonImage.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(monImage)

        then: "The instance is deleted"
        MonImage.count() == 0
        response.redirectedUrl == '/monImage/index'
        flash.message != null
    }
}
