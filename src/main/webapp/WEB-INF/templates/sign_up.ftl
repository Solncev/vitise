<#include "base.ftl">
<#macro title>Регистрация</#macro>
<#macro content>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="main-block">
    <div class="col-md-10 col-md-offset-1 col-sm-12 login-block">
        <h1 class="title">Регистрация</h1>
        <@sf.form action="/sign_up/${role}" method="post" modelAttribute="userForm">
            <fieldset>
                <div class="col-md-6 col-lg-4 col-lg-offset-1">
                    <p class="input-label">Имя</p>
                    <div class="input-wrapper">
                        <@sf.input path="name" type="text" class="login-input" placeholder="Введите имя"/>
                    </div>
                    <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>

                    <p class="input-label">Фамилия</p>
                    <div class="input-wrapper">
                        <@sf.input path="surname" type="text" class="login-input" placeholder="Введите фамилию"/>
                    </div>
                    <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>

                    <p class="input-label">Отчество</p>
                    <div class="input-wrapper">
                        <@sf.input path="thirdName" type="text" class="login-input" placeholder="Введите отчество"/>
                    </div>
                    <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>

                    <p class="input-label">Телефон</p>
                    <div class="input-wrapper">
                        <@sf.input path="telephoneNumber" type="tel" class="login-input" placeholder="Введите номер"/>
                    </div>
                    <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>
                </div>


                <div class="col-md-6 col-lg-4 col-lg-offset-2">
                    <p class="input-label">E-mail</p>
                    <div class="input-wrapper">
                        <@sf.input path="email" type="email" class="login-input" placeholder="Введите e-mail"/>
                    </div>
                    <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>

                    <p class="input-label">Пароль</p>
                    <div class="input-wrapper">
                        <@sf.input path="password" type="password" class="pass-input" placeholder="Введите пароль"/>
                    </div>
                    <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>

                    <p class="input-label">Повторите пароль</p>
                    <div class="input-wrapper">
                        <input type="password" class="pass-input" placeholder="Повторите пароль">
                    </div>
                    <p class="signup-error-text" style="visibility: hidden;">Ошибка в введенных данных</p>

                    <#if role == "worker">
                        <p class="input-label">Выберите должность</p>
                        <div class="input-wrapper">
                            <select name="status">
                                <#list statuses as s>
                                    <option value="${s}">${s}</option>
                                </#list>
                            </select>
                        </div>
                    <#else>
                        <p class="input-label">Выберите курс</p>
                        <div class="input-wrapper">
                            <select name="course" id="courseSelect">
                                <option value="1">1 бакалавриат</option>
                                <option value="2">2 бакалавриат</option>
                                <option value="3">3 бакалавриат</option>
                                <option value="4">4 бакалавриат</option>
                                <option value="5">1 магистратура</option>
                                <option value="6">2 магистратура</option>
                            </select>
                        </div>
                        <p class="input-label">Выберите группу</p>
                        <div class="input-wrapper">
                            <select name="group" id="groupSelect">
                            </select>
                        </div>
                    </#if>
                </div>
                <div class="col-md-12 col-lg-8 col-lg-offset-2">
                    <input type="submit" value="Зарегистрироваться" class="signup-btn">
                </div>
            </fieldset>
        </@sf.form>
    </div>
</div>
<script type="application/javascript">
    var checkEmailIsEmpty = function () {
        $.ajax({
            'url': '/email_check',
            'data': {
                'email': $("#email").val()
            },
            'type': 'get',
            'success': function (obj) {
                $("#email_info").html(obj);
            }
        })
    };
    $("#email").change(checkEmailIsEmpty);

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

</script>
</#macro>