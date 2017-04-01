<div>
    <i>Эта форма будет в модальном окне. Здесь будеть ajax для поиска по коллегам и их добавления в беседу.</i>
    <form action="/messages/conversation/create" method="post">
        <input type="text" name="name" placeholder="Введите название беседы..">
        <p>Участники беседы:</p>
        <p><select multiple name="members[]">
            <#list colleagues as colleague>
            <option value="${colleague.id}">${colleague.surname} ${colleague.name} ${colleague.thirdName}</option>
            </#list>
        </select></p>
        <input class="btn btn-info btn-outline" type="submit" value="Создать беседу">
    </form>
</div>
<hr/>
<h3>Список диалогов:</h3>
<#list interlocutors as i>
<div>
    <p><a href="/messages/user/${i.id}">Перейти в диалог с ${i.name} ${i.surname}</a></p>
</div>
</#list>
<h3>Список бесед:</h3>
<#list conversations as conversation>
<div>
    <p><a href="/messages/conversation/${conversation.id}">${conversation.name}</a></p>
</div>
</#list>