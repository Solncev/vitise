<h2>My request colleagues</h2>
<#list myrequests as request>
<h3>Request ${request.receiver.surname}  ${request.receiver.name}</h3>
<form action="/colleagues/${request.id}/delete" method="post">
    <input type="submit" value="Delete request">
</form>
<hr>
<#else>
<h3>Empty list</h3>
</#list>
<br>
<br>
<h2>Colleagues request</h2>
<#list requests as request>
<h3>Request ${request.sender.surname} ${request.sender.name}</h3>
<form action="/colleagues/${request.id}/approve" method="post">
    <input type="submit" value="Add colleague">
</form>
<form action="/colleagues/${request.id}/delete" method="post">
    <input type="submit" value="Delete colleague">
</form>
<hr>
<#else>
<h3>Empty list</h3>
</#list>
<br>
<br>
<h2>My Colleagues:</h2>
<#list colleagues as colleague>
    <#if colleague.receiver.id == user.id>
    <h3><a href="/user/${colleague.sender.id}">
    ${colleague.sender.surname} ${colleague.sender.name}</a></h3>
    <#else>
    <h3><a href="/user/${colleague.receiver.id}">
    ${colleague.receiver.surname} ${colleague.receiver.name}</a></h3>
    </#if>
<hr>
<#else>
<h3>Empty list</h3>
</#list>