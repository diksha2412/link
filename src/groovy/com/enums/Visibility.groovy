package com.enums

enum Visibility {
    PUBLIC, PRIVATE


    static Visibility convert(String string) {
        if (string.equalsIgnoreCase("public")) {
            com.enums.Visibility.PUBLIC
        } else {
            com.enums.Visibility.PRIVATE
        }

    }

}