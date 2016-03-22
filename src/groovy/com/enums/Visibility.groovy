package com.enums

enum Visibility {
    PUBLIC, PRIVATE


    static Visibility convert(String string) {
        if (string.equalsIgnoreCase("private")) {
            com.enums.Visibility.PRIVATE
        } else {
            com.enums.Visibility.PUBLIC
        }
    }
}