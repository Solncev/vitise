<#include "base1.ftl">
<#macro title>Поддержка</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<div class="main-block">
    <form method="post" action="/support">
        <input name="message" type="text">
        <input type="submit" value="Отправить">
    </form>

</div>

</#macro>