<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<div>
    <p>Email: ${user.email}</p>
    <p>Name: ${user.name}</p>
    <p>Surname: ${user.surname}</p>
    <form action="/profile/change" method="post">
        <input name="password" type="password" placeholder="старый пароль">
        <input name="newPassword" type="password" placeholder="новый пароль" minlength="6" maxlength="20">
        <br>
        <div class="col-sm-10">
            <input type="file" name="photo"/>
        </div>
        <br>
    <#if isWorker>
        <#if user.description??>
            <label>О себе</label>
            <input name="description" value=${user.description}>
            <br>
        </#if>
        <ul>
            <#list directions?keys as key>
                <li><input type="checkbox" name="${key}" value="${key}" ${directions[key]}/>${key}</li>
            </#list>
        </ul>
    </#if>
        <br>
        <input class="btn btn-info btn-outline" type="submit" value="Сохранить">
    </form>

</div>