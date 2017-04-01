<#list users as u>
<p>Email: ${u.email}</p>
<p>Name: ${u.name}</p>
<p>Surname: ${u.surname}</p>
<p>ThirdName: ${u.thirdName}</p>
<form action="/sign_up_request/${u.id}/approve" method="post">
    <input type="submit" value="Подтвердить регистрацию">
</form>
<form action="/sign_up_request/${u.id}/delete" method="post">
    <input type="submit" value="Удалить пользователя">
</form>
<#else>
No requests
</#list>