package com.ttnd.linksharing

import com.ttnd.linksharing.co.ResourceSearchCO
import grails.transaction.Transactional

@Transactional
class ResourceService {

    List<Resource> search(ResourceSearchCO resourceSearchCO) {
        Resource.userResourceSearch(resourceSearchCO) as List<Resource>
    }
}
