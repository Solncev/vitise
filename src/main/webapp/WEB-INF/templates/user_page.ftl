<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<#if user.id != userPage.id>
    <#if state == 0>
    <form action="/colleagues/${userPage.id}/add" method="post">
        <input type="submit" value="Add colleague">
    </form>
    <#elseif state == -2>
    <form action="/colleagues/${colleagues.id}/approve" method="post">
        <input type="submit" value="Approve">
    </form>
    <#else>
    <form action="/colleagues/${colleagues.id}/delete" method="post">
        <input type="submit" value="<#if state == 1>Delete colleague<#else>Delete request</#if>">
    </form>
    </#if>
</#if>
<div>
    <p>Email: ${userPage.email}</p>
    <p>Name: ${userPage.name}</p>
    <p>Surname: ${userPage.surname}</p>

    <p><a href="/user/${user.id}/events">Cобытия</a></p>
    <p><a href="/messages/user/${userPage.id}">Перейти в диалог</a></p>
<#if user.id == userPage.id>
    <p><a href="/profile/change">изменить личные данные</a></p>
    <@sf.form role="form" action="/news" method="post" modelAttribute="news">
        <fieldset>
            <div class="field">
                <@sf.label path="topic">тема</@sf.label>
                <@sf.input path="topic" cssClass="form-control"/>
            </div>
            <div class="field">
                <@sf.label path="text">текст</@sf.label>
                <br>
                <@sf.textarea cols="40" rows="10" path="text" placeholder="Enter your news..."></@sf.textarea>
            </div>
            <div class="form-group">
                <input class="btn btn-info btn-outline" type="submit" value="поделиться">
            </div>
        </fieldset>
    </@sf.form>
</#if>


<#list newses as n>
    <div class="active">
        <p>${n.author.name} в ${n.date}: </p>
        <#if n.topic??>
            <p>тема: ${n.topic}</p>
        </#if>
        <p>текст: ${n.text}</p>
        <#if user.id = n.author.id>
            <form action="/news/remove" method="post">
                <input name="news_id" type="hidden" value="${n.id}"/>
                <input class="btn btn-info btn-outline" type="submit" value="Удалить!">
            </form>
        </#if>
    </div>
</#list>
</div>