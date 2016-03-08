// Place your Spring DSL code here
beans = {
    myBean(com.ttnd.linksharing.CustomBean){ bean ->
        bean.scope='prototype'
        fname="diksha"
        age=23
    }

//    myBeanUsingConstructor(com.ttnd.linksharing.CustomBean,"diksha"){
//
//    }

//    myBean1(com.ttnd.linksharing.CustomBean,"surabhi"){
//
//    }
}
