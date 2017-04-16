<#include "base1.ftl">
<#macro title>Регистрация</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="main-block">
    <div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 login-block">
        <h2 class="title">Регистрация</h2>
        <@sf.form action="/sign_up/${role}" name="sign_up" method="post" modelAttribute="userForm">
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

                    <#if role == "worker">
                        <div class="selects">
                            <div class="left-side">
                                <p class="input-label">Должность</p>
                                <div class="input-wrapper">
                                    <select class="login-input" name="status">
                                        <#list statuses as s>
                                            <option value="${s}">${s.getNameOnRus()}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                        </div>
                    <#else>
                        <div class="selects">
                            <div class="left-side">
                                <p class="input-label">Курс</p>
                                <div class="input-wrapper">
                                    <select name="course" id="courseSelect" class="login-input">
                                        <option value="1">1 бакалавриат</option>
                                        <option value="2">2 бакалавриат</option>
                                        <option value="3">3 бакалавриат</option>
                                        <option value="4">4 бакалавриат</option>
                                        <option value="5">1 магистратура</option>
                                        <option value="6">2 магистратура</option>
                                    </select>
                                </div>
                            </div>
                            <div class="right-side">
                                <p class="input-label">Группа</p>
                                <div class="input-wrapper">
                                    <select class="login-input" name="group" id="groupSelect">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </#if>
                </div>
                <div class="col-md-12 col-lg-8 col-lg-offset-2" id="signup-btn-wrapper">
                    <button type="submit" id="signup-btn" class="signup-btn">Зарегистрироваться</button>
                </div>
            </fieldset>
        </@sf.form>
    </div>
</div>

    <#--<#include "sign_up_valid.ftl">-->

<script>
    var sendCourse = function () {
        $.ajax({
            'url': '/get_groups',
            'data': {
                'course_number': $("#courseSelect").val()
            },
            'type': 'get',
            'success': function (json) {
                $("#groupSelect option").each(function () {
                    $(this).remove();
                });
                $.each(json, function (i, value) {
                    $('#groupSelect').append($('<option>').text(value).attr('value', i));
                });
            }
        })
    };
    $("#courseSelect").on('change', sendCourse);
    $(document).ready(sendCourse);


    var checkEmailIsEmpty = function () {
        $.ajax({
            'url': '/email_check',
            'data': {
                'email': $("#email-input").val()
            },
            'type': 'get',
            'success': function (obj) {
                $("#email-error").html(obj);
            }
        })
    };
    $("#email-input").change(checkEmailIsEmpty);


    jQuery(function ($) {
        $("#phone").mask("+7(999) 999-9999");
    });

    var a = new Array(7).fill(false);

    function validateEmail(email) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    function emailValidation() {
        $("#email-error").text('\xa0');
        var email = $("#email-input").val();
        if (validateEmail(email)) {
            a[0] = true;
        } else {
            $("#email-error").text("такого адреса не существует");
            a[0] = false;
        }
        return false;
    }
    $("#email-input").change("click", emailValidation);


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

    $('#pass1').change('click', function () {
        if ($('#pass1').val().length < 6) {
            $('#pass1-error').text("Слишком короткий пароль");
            a[5] = false;
        }
        else {
            $('#pass1-error').text('\xa0');
            a[5] = true;
        }
    });

    $('#pass2').change('click', function () {
        if ($('#pass1').val() != $('#pass2').val()) {
            $('#pass2-error').text("Пароли не совпадают");
            a[6] = false;
        }
        else {
            $('#pass2-error').text('\xa0');
            a[6] = true;
        }
    });

    function isTrue(element, index, array) {
        return element == true;
    }




    $('#signup-btn').click(function () {
        $('form[name=sign_up]').submit(function (e) {
            if (!a.every(isTrue)) {
                return false;
            }
        })
    });



</script>
</#macro>