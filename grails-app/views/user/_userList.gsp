<div class="table-responsive" id="listUpdate">
    <table class="table table-condensed table-hover">
        <thead>
        <tr>
            <g:sortableColumn property="id" title="Id"/>
            <g:sortableColumn property="userName" title="Username"/>
            <g:sortableColumn property="email" title="Email"/>
            <g:sortableColumn property="firstName" title="First name"/>
            <g:sortableColumn property="lastName" title="Last name"/>
            <g:sortableColumn property="active" title="Active"/>
            <th>Manage</th>
        </tr>
        </thead>

        <tbody>

        <g:each in="${usersList}" var="user">
            <g:if test="${user.active}">
                <g:set var="bootstrapClass" value="alert alert-success"/>
            </g:if>
            <g:else>
                <g:set var="bootstrapClass" value="alert alert-danger"/>
            </g:else>

            <tr class="${bootstrapClass}">
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>

                <td>
                    <g:if test="${user.active}">
                        Yes
                    </g:if>

                    <g:else>
                        No
                    </g:else>
                </td>

                <td>
                    <g:link controller="user" action="toggleActive" params="[id: user.id]">
                        <g:if test="${user.active}">
                            Deactivate
                        </g:if>
                        <g:else>
                            Activate
                        </g:else>
                    </g:link>

                </td>
            </tr>
        </g:each>

        </tbody>
    </table>
</div>




