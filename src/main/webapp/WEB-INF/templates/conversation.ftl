<#--conversation page-->
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<title>беседа</title>
<p><a href="/profile">вернуться на свою страницу</a></p>
<p><a href="/messages">вернуться к списку диалогов</a></p>

<h3>${conversation.name}</h3>
<hr/>

<#if conversation.currentMember.activeStatusName.name() != "DELETED">

<form action='/messages/conversation/${conversation.id}/change_status' method="post">
    <input type="hidden" name="user_id" value="${currentUser.id}">
    <#if conversation.currentMember.activeStatusName.name() == "ACTIVE">
        <input class="btn btn-info btn-outline" type="submit" value="Выйти из беседы">
    <#else>
        <input class="btn btn-info btn-outline" type="submit" value="Вернуться в беседу">
    </#if>
</form>
</#if>
<br>
<h3>Участники</h3>
<ul>
<#list conversation.members as member>
    <li><a href="/user/${member.id}"> ${member.name} ${member.surname}</a>
        <#if currentUser.id == conversation.creator.id && member.id != currentUser.id>
            <form action="/messages/conversation/${conversation.id}/delete" method="post">
                <input type="hidden" name="user_id" value="${member.id}">
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
    <p><select name="user_id">
        <#list available_users as user>
                <option value="${user.fullName}</option>
        </#list>
    </select></p>
    <input class=" btn btn-info btn-outline" type="submit" value="Добавить">
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

<#if conversation.currentMember.activeStatusName.name() == "ACTIVE">
    <@sf.form action='/messages/conversation/${conversation.id}/send' method="post" modelAttribute="message_form"
    enctype="multipart/form-data">
    <fieldset>
        <div class="field">
            <@sf.label path="text">Введите сообщение: </@sf.label>
            <@sf.input path="text" cssClass="form-control"/>
        </div>
        <input type="file" name="file" id="attach-btn">
        <div class="form-group">
            <input class="btn btn-info btn-outline" type="submit" value="Отправить">
        </div>
    </fieldset>
    </@sf.form>
<#elseif conversation.currentMember.activeStatusName.name() == "LEFT_OUT">
<p style="color: red">Вы покинули беседу</p>
<#else>
<p style="color: red">Вы удалены из беседы</p>
</#if>


<#list conversation.messages as message>
<div>
    <p <#if conversation.currentMember.messagesReadingLog.date < message.date && message.user.id != currentUser.id>
            style="color: red"
    </#if>><b>${message.user.name} ${message.user.surname} :</b> ${message.text} <i>${message.date}</i></p>
</div>
</#list>