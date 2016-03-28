package com.ttnd.linksharing

class CustomBean {
    String fname
    int age

    CustomBean(String fname){
        this.fname=fname
    }
    
    CustomBean(){
    }

    void setFname(String fname) {
        this.fname = fname
    }

    void setAge(int age) {
        this.age = age
    }

    String getFname() {
        return fname
    }

    int getAge() {
        return age
    }

}
