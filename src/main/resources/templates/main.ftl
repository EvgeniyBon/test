<#import "parts/common.ftl" as c >
<@c.page>
    <body>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Sign Out"/>
    </form>
    <span><a href="/user">User list</a> </span>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Enter message">
            <input type="text" name="tag" placeholder="Enter tag">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add message</button>
        </form>
    </div>
    <div>Messages</div>
    <form method="get" action="/main">
        <label>
            <input type="text" name="filter" value="${filter!}">
        </label>
        <button type="submit">Find</button>
    </form>
    <#list messages as mes>
        <div>
            <b>${mes.getId()}</b>
            <span>${mes.getText()}</span>
            <i>${mes.getTag()}</i>
            <b>${mes.getAuthorName()}</b>
            <div>
            <#if mes.fileName??>
            <img src="/img/${mes.fileName}">
            </#if>
            </div>
        </div>
    <#else>
        No messages
    </#list>
    </body>
</@c.page>
