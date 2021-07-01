<#macro registration path>
<body>
Add new user
<form action="${path}" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/>
        <a  href="/login">Back to login page</a>
    </div>

</form>
</body>
</#macro>