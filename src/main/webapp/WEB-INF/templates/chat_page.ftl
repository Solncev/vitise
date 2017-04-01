<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<title>сообщения</title>
<p><a href="/profile">вернуться на свою страницу</a></p>
<br>
<@sf.form role="form" action='${interlocutor}/send' method="post" modelAttribute="messageForm">
<fieldset>
    <div class="field">
        <@sf.label path="message"></@sf.label>
        <@sf.input path="message" cssClass="form-control"/>
    </div>
    <div class="form-group">
        <input class="btn btn-info btn-outline" type="submit" value="отправить">
    </div>
</fieldset>
</@sf.form>
<#list messages as m>
<div>
    <p>${m.sender.name} ${m.sender.surname} в ${m.date}: ${m.message}</p>
</div>
</#list>