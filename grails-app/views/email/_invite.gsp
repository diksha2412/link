<%@ page contentType="text/html;charset=UTF-8" %>

%{--
<a href=" ${createLink base = "${hostURL}" controller="topic" action="join" params="[id: topicId]}">
    Click here to subscribe"></a></createLink>
--}%


<a href="${createLink( controller: 'topic', action: 'join', base: '${hostURL}', params: '[topicId: topicId]')}">
    Click here to subscribe
</a>