<#--home page-->
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<title>моя страница</title>
<div>
    <p>Email: ${user.email}</p>
    <p><a href="/messages">мои диалоги</a></p>
    <p><a href="/profile">личный кабинет</a></p>
</div>

<@sf.form role="form" action='/news' method="post" modelAttribute="news">
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

<#list newses as n>
<div class="active">
    <p>${n.author.name} в ${n.date}: </p>
    <#if n.topic>
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