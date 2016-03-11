package com.ttnd.linksharing.VO

import com.enums.Visibility
import com.ttnd.linksharing.User


class TopicVO {
    Long id
    String name
    Visibility visibility
    Integer count
    User createdBy
}
