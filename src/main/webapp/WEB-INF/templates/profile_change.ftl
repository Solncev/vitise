<#include "base2.ftl">
<#macro title>Настройки</#macro>
<#macro name>${currentUser.name}</#macro>
<#macro my_events>/user/${currentUser.id}/events</#macro>
<#macro link>
<link rel="stylesheet" href="/css/profile_change.css"></#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="main-block">
    <div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 login-block">
        <h2 class="title">Редактирование информации</h2>
        <form action="/profile/change" method="post" enctype="multipart/form-data" name="edit-info">
        <#--<input name="password" type="password" placeholder="старый пароль">
        <input name="newPassword" type="password" placeholder="новый пароль" minlength="6" maxlength="20">
        <br>
        <img src="${user.photoName}">
        <div class="col-sm-10">
        <input type="file" name="file"/>
        </div>
        <br>
        <#if isWorker>
        <label>О себе</label>
        <input name="description"
        <#if user.description?has_content> value=${user.description} </#if> </>
        <br>
        <ul>
        <#list directions?keys as key>
        <li><input type="checkbox" name="${key}" value="${key}" ${directions[key]}/>${key}</li>
        </#list>
        </ul>
        </#if>
        <br>-->
            <div class="col-md-6 col-lg-6">
                <p class="input-label">Имя</p>
                <div class="input-wrapper">
                    <input name="name" type="text" class="login-input" value="${currentUser.name}" id="name-input1">
                </div>
                <p class="signup-error-text" id="name-error1">&nbsp;</p>
                <p class="input-label">Фамилия</p>
                <div class="input-wrapper">
                    <input name="surname" type="text" class="login-input" value="${currentUser.surname}"
                           id="name-input2">
                </div>
                <p class="signup-error-text" id="name-error2">&nbsp;</p>
                <p class="input-label">Отчество</p>
                <div class="input-wrapper">
                    <input name="thirdName" type="text" class="login-input" value="${currentUser.thirdName}"
                           id="name-input3">
                </div>
                <p class="signup-error-text" id="name-error3">&nbsp;</p>


                <div class="about">
                    <p class="input-label">О себе</p>
                    <div class="input-wrapper-textarea">
                        <textarea name="description" class="login-input" placeholder="Расскажите о себе" maxlength="220"><#if currentUser.description??>${currentUser.description}</#if></textarea>
                    </div>
                </div>
            </div>


            <div class="col-md-6 col-lg-6">
                <p class="input-label">E-mail</p>
                <div class="input-wrapper">
                    <input name="email" type="email" class="login-input" placeholder="example@mail.ru" id="email-input"
                           value="${currentUser.email}">
                </div>
                <p class="signup-error-text" id="email-error">&nbsp;</p>

                <p class="input-label">Телефон</p>
                <div class="input-wrapper">
                    <input name="telephoneNumber" type="tel" class="login-input" id="phone"
                           placeholder="+7(999) 999-999"
                           <#if currentUser.telephoneNumber??>value="${currentUser.telephoneNumber}"</#if>>
                </div>
                <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>

                <div class="pass-change">
                    <h4 class="title">Смена пароля</h4><br>
                    <p class="input-label">Старый пароль</p>
                    <div class="input-wrapper">
                        <input type="password" name="password" class="pass-input">
                    </div>
                    <p class="input-label">Новый пароль</p>
                    <div class="input-wrapper">
                        <input type="password" name="newPassword" id="pass" class="pass-input" id="pass1">
                    </div>

                    <p class="input-label">Повторите пароль</p>
                    <div class="input-wrapper">
                        <input type="password" name="newPasswordConfirmation" id="pass2" class="pass-input" id="pass2">
                    </div>
                    <p class="signup-error-text" id="pass2-error">&nbsp;</p>
                </div>
            </div>
            <img src="${currentUser.photoName}">
            <div class="col-sm-10">
                <input type="file" name="file"/>
            </div>
            <#if isWorker>
                <#list directions?keys as key>
                    <li><input type="checkbox" name="${key}" value="${key}" ${directions[key]}/>${key}</li>
                </#list>
                </ul>
            </#if>
            <p>&nbsp;</p>
            <div class="col-md-12 col-lg-8 col-lg-offset-2" id="signup-btn-wrapper">
                <button type="submit" id="signup-btn" class="signup-btn">Сохранить</button>
            </div>


        <#--<div class="col-md-12 col-lg-8 col-lg-offset-2"><input type="submit" value="Сохранить"-->
        <#--class="signup-btn"></div>-->
        </form>
    </div>
</div>
<#--<#include "sign_up_valid.ftl">-->
<script>


    //    var checkEmailIsEmpty = function () {
    //        $.ajax({
    //            'url': '/email_check',
    //            'data': {
    //                'email': $("#email-input").val()
    //            },
    //            'type': 'get',
    //            'success': function (obj) {
    //                $("#email-error").html(obj);
    //            }
    //        })
    //    };
    //    $("#email-input").change(checkEmailIsEmpty);


    jQuery(function ($) {
        $("#phone").mask("+7(999) 999-9999");
    });


    // to-do change to false later
    var a = new Array(7).fill(true);

    function validateEmail(email) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    a[0] = true;
    //    function emailValidation() {
    //        $("#email-error").text('\xa0');
    //        var email = $("#email-input").val();
    //        if (validateEmail(email)) {
    //            a[0] = true;
    //        } else {
    //            $("#email-error").text("такого адреса не существует");
    //            a[0] = false;
    //        }
    //        return false;
    //    }
    //    $("#email-input").change("click", emailValidation);


    function validateName(name) {
        var re = /^[a-zA-Zа-яА-Я'][a-zA-Zа-яА-Я-' ]+[a-zA-Zа-яА-Я']?$/u;
        return re.test(name);
    }

    function nameValidation1() {
        $("#name-error1").text('\xa0');
        var name = $("#name-input1").val();
        if (validateName(name)) {
            a[1] = true;
        }
        else {
            a[1] = false;
            $("#name-error1").text("Имя введено неправильно");
        }
        return false;
    }
    $("#name-input1").change("click", nameValidation1);

    function nameValidation2() {
        $("#name-error2").text('\xa0');
        var name = $("#name-input2").val();
        if (validateName(name)) {
            a[2] = true;
        }
        else {
            $("#name-error2").text("Фамилия введена неправильно");
            a[2] = false;
        }
        return false;
    }
    $("#name-input2").change("click", nameValidation2);

    function nameValidation3() {
        $("#name-error3").text('\xa0');
        var name = $("#name-input3").val();
        if (validateName(name)) {
            a[3] = true;
        }
        else {
            $("#name-error3").text("Отчество введено неправильно");
            a[3] = false;
        }
        return false;
    }
    $("#name-input3").change("click", nameValidation3);

    $('#phone').change('click', function () {
        if ($('#phone').val().length == 16) {
            a[4] = true;
        }
        else {
            a[4] = false;
        }
    });


    //    I will implement thos later
    a[5] = true;
    a[6] = true;
    //    $('#pass1').change('click', function () {
    //        if ($('#pass1').val().length < 6) {
    //            $('#pass1-error').text("Слишком короткий пароль");
    //            a[5] = false;
    //        }
    //        else {
    //            $('#pass1-error').text('\xa0');
    //            a[5] = true;
    //        }
    //    });
    //
    //    $('#pass2').change('click', function () {
    //        if ($('#pass1').val() != $('#pass2').val()) {
    //            $('#pass2-error').text("Пароли не совпадают");
    //            a[6] = false;
    //        }
    //        else {
    //            $('#pass2-error').text('\xa0');
    //            a[6] = true;
    //        }
    //    });

    function isTrue(element, index, array) {
        return element == true;
    }


    $('#signup-btn').click(function () {
        $('form[name=edit-info]').submit(function (e) {
            if (!a[1] || !a[2] || !a[3]) {
                return false;
            }
        })
    });

</script>
</#macro>