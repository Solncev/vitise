<#include "base2.ftl">
<#macro title>Регистрация</#macro>
<#macro name>${user.name}</#macro>
<#macro my_events>/user/${user.id}/events</#macro>
<#macro link>
<link rel="stylesheet" href="/css/login.css"></#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="main-block">
    <div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 login-block">
        <h2 class="title">Регистрация сотрудника деканата</h2>
        <@sf.form action="/admin/add_deanery" name="sign_up" method="post" modelAttribute="userForm">
            <fieldset>
                <div class="col-md-6 col-lg-6">
                    <p class="input-label">Имя</p>
                    <div class="input-wrapper">
                        <@sf.input path="name" type="text" class="login-input" id="name-input1"/>
                    </div>
                    <p class="signup-error-text" id="name-error1">&nbsp;</p>

                    <p class="input-label">Фамилия</p>
                    <div class="input-wrapper">
                        <@sf.input path="surname" type="text" class="login-input" id="name-input2"/>
                    </div>
                    <p class="signup-error-text" id="name-error2">&nbsp;</p>

                    <p class="input-label">Отчество</p>
                    <div class="input-wrapper">
                        <@sf.input path="thirdName" type="text" class="login-input" id="name-input3"/>
                    </div>
                    <p class="signup-error-text" id="name-error3">&nbsp;</p>

                    <p class="input-label">Телефон</p>
                    <div class="input-wrapper">
                        <@sf.input path="telephoneNumber" type="tel" class="login-input"
                        id="phone" placeholder="+7(999) 999-999"/>
                    </div>
                    <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>
                </div>


                <div class="col-md-6 col-lg-6">
                    <p class="input-label">E-mail</p>
                    <div class="input-wrapper">
                        <@sf.input path="email" type="email" class="login-input" id="email-input"
                        placeholder="example@mail.ru"/>
                    </div>
                    <p class="signup-error-text" id="email-error">&nbsp;</p>

                    <p class="input-label">Пароль</p>
                    <div class="input-wrapper">
                        <@sf.input path="password" type="password" id="pass1" class="pass-input" pattern=".{6,50}"
                        title="Минимальная длина 6 символов"/>
                    </div>
                    <p class="signup-error-text" id="pass1-error">&nbsp;</p>
                    <p class="input-label">Повторите пароль</p>
                    <div class="input-wrapper">
                        <input type="password" name="" id="pass2" class="pass-input">
                    </div>
                    <p class="signup-error-text" id="pass2-error">&nbsp;</p>
                </div>
                <div class="col-md-12 col-lg-8 col-lg-offset-2" id="signup-btn-wrapper">
                    <button type="submit" id="signup-btn" class="signup-btn">Добавить сотрудника</button>
                </div>
            </fieldset>
        </@sf.form>
    </div>
</div>
    <#include "sign_up_valid.ftl">
</#macro>