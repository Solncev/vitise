<#include "base2.ftl">
<#macro title>Профиль</#macro>
<#macro name>${currentUser.name}</#macro>
<#macro my_events></#macro>
<#macro link></#macro>
<#macro content>
<div class="main-block">
    <form action="/admin/users/filter">
        <input type="text" name="searchField"/>
        <select name="status">
            <option value="all">Все</option>
            <#list statusNames as statusName>
                <option value="${statusName.ordinal()}">${statusName.toString()}</option>
            <#else>
            </#list>
        </select>
        <select name="isActive">
            <option value="null">Все</option>
            <option value="true">Активные</option>
            <option value="false">Архивированные</option>
        </select>
        <input type="submit" value="Фильтр">
    </form>
    <#list users as user>
    ${user.surname}
    ${user.name}
        <form action="/admin/users/archive" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <input type="submit" value="<#if user.isActive()>Архивировать<#else>Разархивировать</#if>">
        </form>
    <#else>
        Empty List
    </#list>
</div>
</#macro>