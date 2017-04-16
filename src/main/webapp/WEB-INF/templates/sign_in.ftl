<#include "base1.ftl">
<#macro title>Вход</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<div class="main-block">

    <div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 login-block">
        <h1 class="title">Вход в систему</h1>

        <div class="modal-form" id="modal-form">
            <a href="/sign_up/stud"><p>Студент</p></a>
            <a href="/sign_up/worker"><p>Сотрудник</p></a>
        </div>

        <#if error??>
            <div class="error">
                <p>Ошибка в введенных данных</p>
            </div>
        </#if>


        <@sf.form action='/login/process' method="post" modelAttribute="authForm">
            <fieldset>
                <p class="input-label">E-mail</p>
                <div class="input-wrapper">
                    <@sf.input path="email" type="text" class="login-input" placeholder="Введите e-mail"/>
                    <@sf.errors path="email" cssClass="help-block"/>
                </div>

                <p>&nbsp; </p>

                <p class="input-label">Пароль</p>
                <div class="input-wrapper">
                    <@sf.input path="password" type="password" class="pass-input" placeholder="Введите пароль"/>
                    <@sf.errors path="password" cssClass="help-block"/>
                </div>

                <div class="col-md-12 line">
                    <input class="checkbox" type="checkbox" name="rememberMe" id="checkbox" hidden>
                    <label for="checkbox" class="check-label"></label>
                    <span class="remember-sign">Запомнить меня</span>
                    <a href="#"><span class="forgot-sign">Забыли пароль?</span></a>
                </div>
                <div class="col-md-12 line">
                    <input type="submit" value="Войти" class="login-btn" id="login-btn">
                </div>
            </fieldset>
        </@sf.form>
        <div class="col-md-12">
            <h3 class="links"><span id="signup-btn">Регистрация</span></h3>
        </div>
    <#--<div class="col-md-12">-->
    <#--<p class="error-text">Пользователя с таким e-mail не существует</p>-->
    <#--</div>-->


    </div>
</div>
<script>
    $("#signup-btn").click(function () {
        $("#modal-form").show();
    })

    var mouse_is_inside = false;
    $(document).ready(function () {
        $('#modal-form').hover(function () {
            mouse_is_inside = true;
        }, function () {
            mouse_is_inside = false;
        });

        $("body").mouseup(function () {
            if (!mouse_is_inside) $('#modal-form').hide();
        });
    });
</script>
</#macro>



