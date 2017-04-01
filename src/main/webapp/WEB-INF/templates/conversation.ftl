<#--conversation page-->
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<title>беседа</title>
<p><a href="/profile">вернуться на свою страницу</a></p>
<p><a href="/messages">вернуться к списку диалогов</a></p>

<h3>${conversation.name}</h3>
<hr/>

<h3>Участники</h3>
<ul>
<#list conversation.members as member>
    <li><a href="/user/${member.id}"> ${member.name} ${member.surname}</a>
    <#if currentUser.id == conversation.creator.id && member.id != currentUser.id>
        <form action="/messages/conversation/${conversation.id}/delete" method="post">
            <input type="hidden" name="member_id" value="${member.id}">
            <input class="btn btn-info btn-outline" type="submit" value="Удалить">
        </form>
    </#if>
    </li>
</#list>
</ul>
<hr/>

<#if currentUser.id == conversation.creator.id>
<form action="/messages/conversation/${conversation.id}/add" method="post">
    <p>Добавить участника:</p>
    <p><select name="member_id">
        <#list available_users as user>
            <option value="${user.id}">${user.surname} ${user.name} ${user.thirdName}</option>
        </#list>
    </select></p>
    <input class="btn btn-info btn-outline" type="submit" value="Добавить">
</form>
<hr/>
</#if>

<#if currentUser.id == conversation.creator.id>
<form action="/messages/conversation/${conversation.id}/change" method="post">
    <p>Изменить название:</p>
    <input type="text" name="name" value="${conversation.name}">
    <input class="btn btn-info btn-outline" type="submit" value="Изменить название">
</form>
<hr/>
</#if>

<@sf.form action='${conversation.id}/send' method="post" modelAttribute="message_form">
    <fieldset>
        <div class="field">
            <@sf.label path="text">Введите сообщение: </@sf.label>
            <@sf.input path="text" cssClass="form-control"/>
        </div>
        <div class="form-group">
            <input class="btn btn-info btn-outline" type="submit" value="Отправить">
        </div>
    </fieldset>
</@sf.form>
<hr/>

<#list conversation.messages as message>
<div>
    <p><b>${message.user.name} ${message.user.surname} :</b> ${message.text} <i>${message.date}</i></p>
</div>
</#list>