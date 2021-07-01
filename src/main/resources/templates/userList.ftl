<#import "parts/common.ftl" as c>
<@c.page>
    User List
    <table>
        <thead>
        <tr>
            <th>User</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">edit</a> <a href="/user/${user.id}/delete">delete</a> </td>
            </tr>
        </#list>
        <a href="/">Main page</a>
    </table>
</@c.page>