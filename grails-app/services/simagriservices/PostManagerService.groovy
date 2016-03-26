package simagriservices

import grails.transaction.Transactional
import simagri.Post

@Transactional
class PostManagerService {
    def myUtilityService
    Boolean addPost(params) {
        def author=myUtilityService.getCurrent()
        def category=params.category
        def title=params.title
        def post=params.post
        def createdAt=params.createdAt
       return new Post(category:category,title:title,post:post,author:author,createdAt:createdAt).save(failOnError: true)
    }
}
