package com.enums

enum TraitType {
    SKILLS('Skills'),
    INTERACTIONS('Interactions'),
    INTERESTS('Interests'),
    FOOD('Food'),
    MUSIC('Music'),
    ART('Art'),
    DRINK('Drink'),
    KEYWORD('Keyword')


    String value

    TraitType(String value) {
        this.value = value
    }
}

