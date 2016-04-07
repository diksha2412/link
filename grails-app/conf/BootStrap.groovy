import com.enums.Seriousness
import com.enums.Visibility
import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.Role
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.UserRole

class BootStrap {
    def utilService
    //def grailsApplication
    def init = { servletContext ->
        //println grailsApplication.config.grails.testValue

        //String path="${grailsApplication.config.grails.linksharing.folderPath}"

        if (!User.count()) {
            createUser()
        }

        if (!Topic.count()) {
            User.all.each { User user ->
                println("creating topics")
                createTopics(user)
            }
        }

        //Subscribing user to other topics
        User.all.each { User user ->
            Topic.findAllByCreatedByNotEqual(user).each { Topic topic ->
                subscribeTopics(user, topic)
            }
        }

        if (!Resource.count()) {
            Topic.all.each { Topic topic ->
                log.info("creating document resources")
                2.times { createResourceAndNotifyAll("some/file/path${it}", topic.name, topic, topic.createdBy, true) }
                log.info("creating link resources")
                2.times { createResourceAndNotifyAll("http://www.google.com", topic.name, topic, topic.createdBy) }
            }
        }

        /*if (!ResourceRating.count()) {
            println("creating resource rating")
            ReadingItem.findAllByIsRead(false).each {
                createResourceRating(it.resource, it.resource.createdBy, 3)
            }
        }*/

        if (!ResourceRating.count()) {
            println("creating resource rating")
            User.all.each { User user ->
                user.readingItems.each { ReadingItem readingItem ->
                    if (!readingItem.isRead) {
                        createResourceRating(readingItem.resource, user, 3)
                    }
                }
            }
        }
    }

    List<Role> createRoles(){
        Role adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
        Role userRole = Role.findOrSaveWhere(authority: 'ROLE_USER')

        List<Role> roles=[]
        roles.add(adminRole)
        roles.add(userRole)

        return roles
    }


    void createUser() {
        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>creating users")
        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>creating user1")
        String pass = utilService.fetchEncodedPassword("test123")
        User u1 = new User(firstName: "diksha", lastName: "ahuja", username: "diksha.ahuja@tothenew.com", password: pass,
                confirmPassword: pass, email: "diksha.ahuja@tothenew.com")
        println "${u1.validate()}"
        println ">>>>>>>>>errors are:"
        println "${u1.errors}"
        if (u1.validate()){
            u1.save(flush: true, failOnError: true)
        }
       UserRole.create(u1, createRoles()[0], true)
//       UserRole.create(u1, createRoles()[1], true)

        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>creating user1")
        String pass1 = utilService.fetchEncodedPassword("testabc")
        User u2 = new User(firstName: "pulkit", lastName: "ahuja", username: "ahujad81@gmail.com", password: pass1,
                confirmPassword: pass1, email: "ahujad81@gmail.com")
        println "${u1.validate()}"
        if (u2.validate()){
            u2.save(flush: true, failOnError: true)
        }
        UserRole.create(u2, createRoles()[1], true)
    }

    void createTopics(User user) {
        if (true) {
            5.times {
                Topic topic = new Topic(name: "topic${it}_${user.firstName}", createdBy: user,
                        visibility: Visibility.PUBLIC)
                if (topic.save(failOnError: true)) {
                    log.debug("topic saved successfully")
                } else {
                    log.error("error in saving topic")
                }
                user.addToTopics(topic)
            }
            if (user.save(failOnError: true)) {
                log.info("user topics saved successfully")
            } else {
                log.error("error in saving user" + user.errors.allErrors)
            }
        }
    }

    void createResourceAndNotifyAll(String location, String description, Topic topic, User user, Boolean isDocumentResource = false) {
        Resource resource
        if (isDocumentResource) {
            resource = createDocumentResource(location, topic, user, description)
        } else {
            resource = createLinkResource(location, topic, user, description)
        }
        topic.addToResources(resource)
        updateReadingItems(resource, topic)
    }

    LinkResource createLinkResource(String link, Topic topic, User user, String description) {
        new LinkResource(url: link, description: description, createdBy: user, topic: topic).save()
    }

    DocumentResource createDocumentResource(String path, Topic topic, User user, String description) {
        new DocumentResource(filePath: path, topic: topic, description: description, createdBy: user).save()
    }

    void updateReadingItems(Resource resource, Topic topic) {
        List<Subscription> subscriptions = Subscription.findAllByTopic(topic)
        subscriptions.find { Subscription subscription ->
            subscription.user != resource.createdBy
        }?.user?.each { User user ->
            user.addToReadingItems(new ReadingItem(resource: resource, user: user)).save(failOnError: true)
        }
    }

    void subscribeTopics(User user, Topic topic) {
        Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.CASUAL)
        if (subscription.save(failOnError: true)) {
            log.info("new subscription created")
        } else {
            log.error("error in creating subscription")
        }
        topic.addToSubscriptions(subscription)
        topic.save()
    }

    void createResourceRating(Resource resource, User user, Integer score) {
        resource.addToResourceratings(new ResourceRating(resource: resource,
                user: user, score: score)).save()
    }
}
