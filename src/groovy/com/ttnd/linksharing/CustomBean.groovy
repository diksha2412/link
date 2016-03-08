package com.ttnd.linksharing

/**
 * Created by diksha on 7/3/16.
 */
class CustomBean {
    String fname
    int age

    CustomBean(String fname){
        println "inside constructor"
        this.fname=fname
    }
    
    CustomBean(){
        println "inside default constructor"
    }

    void setFname(String fname) {
        println("setter of fname")
        this.fname = fname
    }

    void setAge(int age) {
        println("setter of age")
        this.age = age
    }

    String getFname() {
        println("getter of fname")
        return fname
    }

    int getAge() {
        println("getter of age")
        return age
    }

}
