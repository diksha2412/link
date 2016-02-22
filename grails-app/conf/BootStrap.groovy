import com.enums.Seriousness
import com.enums.Visibility
import com.link.sharing.core.DocumentResource
import com.link.sharing.core.LinkResource
import com.link.sharing.core.ReadingItem
import com.link.sharing.core.Resource
import com.link.sharing.core.ResourceRating
import com.link.sharing.core.Subscription
import com.link.sharing.core.Topic
import com.link.sharing.core.User

class BootStrap {
    //def grailsApplication
    def init = { servletContext ->
        //println grailsApplication.config.grails.testValue

        log.error("hello bootstrap")
        if (!User.count()) {
            createUser()
        }

        if (!Topic.count()) {
            User.all.each { User user ->
                log.info("creating topics")
                createTopics(user)
            }
        }

        if (!Subscription.count()) {
            User.all.each { User user ->
                Topic.findAllByCreatedByNotEqual(user).each { Topic topic ->
                    subscribeTopics(user, topic)
                }
            }
        }

        if (!Resource.count) {
            Topic.all.each { Topic topic ->
                log.info("creating document resources")
                2.times { createResourceAndNotifyAll("some/file/path${it}", topic.name, topic, topic.createdBy, true) }
                log.info("creating link resources")
                2.times { createResourceAndNotifyAll("http://www.google.com", topic.name, topic, topic.createdBy) }
            }
        }

        if (!ResourceRating.count) {
            ReadingItem.findAllByIsRead(false).each {
                createResourceRating(it.resource, it.resource.createdBy, 3)
            }
        }


    }

    void createUser() {
        new User(firstName: "diksha", lastName: "ahuja", userName: "diksha2412", password: "test123",
                email: "diksha.ahuja@tothenew.com", admin: true).save(failOnError: true)

        new User(firstName: "pulkit", lastName: "ahuja", userName: "pulkit", password: "testabc",
                email: "ahujapulkit@gmail.com", admin: false).save(failOnError: true)

    }


    void createTopics(User user) {
        if (user.topics.size() == 0) {
            5.times {
                Topic topic = new Topic(name: "topic${it}_${user.firstName}", createdBy: user,
                        visibility: Visibility.PUBLIC)
                if (topic.save()) {
                    log.info("topic saved successfully")
                } else {
                    log.error("error in saving topic")
                }
                user.addToTopics(topic)
            }
        }
        if (user.save()) {
            log.info("user topics saved successfully")
        } else {
            log.error("error in saving user" + user.errors.allErrors)
        }
    }

    void createResourceAndNotifyAll(String location, String description, Topic topic, User user, Boolean isDocumentResource = false) {
        Resource resource
        if (isDocumentResource) {
            resource = createDocumentResource(location, topic, user, description)
        } else {
            resource = createLinkResource(location, topic, user, description)
        }
        updateReadingItems(resource, topic)

    }

    LinkResource createLinkResource(String link, Topic topic, User user, String description) {
        new LinkResource(url: link, description: description, createdBy: user, topic: topic).save()
    }

    DocumentResource createDocumentResource(String path, Topic topic, User user, String description) {
        new DocumentResource(filePath: path, topic: topic, description: description, createdBy: user).save()
    }

    void updateReadingItems(Resource resource, Topic topic) {
        println("updating reading times")
        List<Subscription> subscriptions = Subscription.findAllByTopic(topic)
        subscriptions.find { Subscription subscription ->
            subscription.user != resource.createdBy
        }.user.each { User user ->
            user.addToReadingItems(new ReadingItem(resource: resource, user: user)).save(failOnError: true)
        }
    }

    void subscribeTopics(User user, Topic topic) {
        println "${topic.createdBy != user}"
        if (topic.createdBy != user) {
            Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.CASUAL)
            if (subscription.save(failOnError: true)) {
                log.info("new subscription created")
            } else {
                log.error("error in creating subscription")
            }
            topic.subscriptions.add(subscription)
            topic.save()
        }
    }

    void createResourceRating(Resource resource, User user, Integer score) {
            resource.addToResourceratings(new ResourceRating(resource: resource,
                    user: user, score: score)).save()
    }
}
