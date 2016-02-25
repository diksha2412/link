package com.enums

/**
 * Created by diksha on 11/2/16.
 */

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