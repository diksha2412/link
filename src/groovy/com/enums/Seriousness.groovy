package com.enums

/**
 * Created by diksha on 11/2/16.
 */

enum Seriousness {
    SERIOUS, VERY_SERIOUS, CASUAL


    static com.enums.Seriousness convert(String serious) {
        if (serious.equalsIgnoreCase("serious")) {
            com.enums.Seriousness.SERIOUS
        } else if (serious.equalsIgnoreCase("veryserious") || serious.equalsIgnoreCase("very_serious")) {
            com.enums.Seriousness.VERY_SERIOUS
        } else {
            com.enums.Seriousness.CASUAL
        }
    }
}