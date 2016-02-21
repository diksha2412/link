import com.enums.Seriousness
import com.enums.Visibility
import com.link.sharing.core.DocumentResource
import com.link.sharing.core.LinkResource
import com.link.sharing.core.Resource
import com.link.sharing.core.Subscription
import com.link.sharing.core.Topic
import com.link.sharing.core.User
import org.grails.datastore.mapping.query.Query.In

class BootStrap {
    //def grailsApplication
    def init = { servletContext ->
        //println grailsApplication.config.grails.testValue

        log.error ("hello bootstrap")

       // createUser()

//         User.all.each { User user->
//             log.info("creating topics")
//            createTopics(user)
//        }

//        Topic.all.each { Topic topic ->
//            println("creating resources")
//            createResources(topic)
//        }

//        User.all.each { User user ->
//            user.topics.each { Topic topic ->
//                    println("subscribing topics")
//                    subscribeTopics(user, topic)
//                }
//            }

    }

//   void createUser(){
//       if (!User.count()) {
//           new User(firstName: "diksha", lastName: "ahuja", userName: "diksha2412", password: "test123",
//                   email: "diksha.ahuja@tothenew.com", admin: true).save(failOnError: true)
//
//           new User(firstName: "pulkit", lastName: "ahuja", userName: "pulkit", password: "testabc",
//                   email: "ahujapulkit@gmail.com", admin: false).save(failOnError: true)
//
//       }
//   }


  /*  void createTopics(User user){
        if (user.topics.size()==0){
            5.times {
                Topic topic=new Topic(name: "topic${it}_${user.firstName}", createdBy: user,
                        visibility: Visibility.PUBLIC)
                if(topic.save()){
                    log.info("topic saved successfully")
                } else {
                    log.error("error in saving topic")
                }
                user.addToTopics(topic)
            }
        }
        if(user.save()){
            log.info("user topics saved successfully")
        } else {
            log.error("error in saving user"+user.errors.allErrors)
        }
    } */

     /*void createResources(Topic topic) {
        if (topic.resources.size() == 0) {

            2.times {
                Resource linkResource = new LinkResource(url: 'http://grails.github.io/grails-doc/latest/guide/theWebLayer.html',
                        description: topic.name, createdBy: topic.createdBy, topic: topic)

                Resource documentResource = new DocumentResource(filePath: "some/file/path", description: topic.name,
                        createdBy: topic.createdBy, topic: topic)

                if (linkResource.save() && documentResource.save()) {
                    log.info("resources saved successfully")
                } else {
                    log.error("error in saving the resources")
                }
                topic.addToResources(linkResource)
                topic.addToResources(documentResource)
            }

            if (topic.save()) {
                log.info("topic saved successfully")
            } else {
                log.error("error in saving topic")
            }
        }
    } */

    void subscribeTopics(User user, Topic topic){
        if (topic.createdBy!=user){
            Subscription subscription=new Subscription(topic: topic, user:user, seriousness: Seriousness.CASUAL)
            if(subscription.save()) {
                log.info("new subscription created")
            }else {
                log.error("error in creating subscription")
            }
            topic.subscriptions.add(subscription)
            topic.save()
            }
    }


    def destroy = {
    }
}
