<#--
<title>Сообщения</title>
<p><a href="/profile">вернуться на свою страницу</a></p>
<br>
<form role="form" action='/messages/dialog/${relation.id}/send' method="post">
    <div class="field">
        <textarea name="message" cols="60" rows="10" placeholder="Введите свое сообщение"></textarea>
    </div>
    <div class="form-group">
        <input class="btn btn-info btn-outline" type="submit" value="Отправить">
    </div>
</form>
<#list relation.messages as message>
<div>
    <p <#if relation.readingLog.date < message.date && message.user.id != current_user.id>
            style="color: red"
    </#if>><b>${message.user.name} ${message.user.surname} :</b> ${message.message} <i>${message.date}</i></p>
</div>
</#list>
-->

<#include "base2.ftl">
<#macro title>Диалоги</#macro>
<#macro name>${currentUser.name}</#macro>
<#macro my_events>/user/${currentUser.id}/events</#macro>
<#macro link>
<link rel="stylesheet" href="/css/dialogs.css">
</#macro>

<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<div class="main-block">
    <div class="line2">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
            <div class="col-xs-12">
                <div class="col-xs-12 input-wrapper">
                    <a href="/messages"><i class="fa fa-lg fa-chevron-left" aria-hidden="true"></i> &nbsp; &nbsp;</a>
                    <h4 id="title"><#if currentUser.id=relation.first.id>${relation.second.fullName}<#else
                    >${relation.first.fullName}</#if></h4>
                    <input type="text" placeholder="Начните вводить имя или фамилию" class="search-input1"
                           id="message-search-field">

                    <a href="#" id="search-message-btn"><i class="fa fa-lg fa-search" aria-hidden="true"></i></a>
                    <a href="#" id="cancel-search-btn" hidden><i class="fa fa-lg fa-times" aria-hidden="true"></i></a>
                </div>
            </div>
        </div>
    </div>

    <div class="line2">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3"
             id="messages-block">
            <div class="col-xs-12">
                <div class="col-xs-12 messages-items-block">

                    <#list relation.messages as message>
                        <div class="message-item"
                            <#if relation.readingLog.date < message.date && message.user.id != currentUser.id>
                             style="background-color: #f2faff;"
                            </#if>>
                            <div class="round-photo">
                                <img src="/img/mal.png" alt="">
                            </div>
                            <div class="message-text">
                                <h4>${message.user.fullName}</h4> <span>${message.date}</span>
                                <h5>${message.message}</h5>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
            <div class="col-xs-12">
                <div class="col-xs-12 send-buttons">

                    <form action='/messages/dialog/${relation.id}/send' method="post" id="form"
                          enctype="multipart/form-data">
                        <textarea name="message" id="message" cols="1" class="post-input"
                                  placeholder="Введите текст сообщения"
                                  oninput="auto_grow(this)"></textarea>
                        <button type="submit" id="submit-btn">
                            <i class="fa fa-2x fa-paper-plane" aria-hidden="true"></i>
                        </button>
                        <input type="file" name="file" id="attach-btn">
                        <label for="attach-btn"><i class="fa fa-2x fa-paperclip" aria-hidden="true"></i></label>
                    </form>
                </div>
            </div>
        </div>

    </div>

</div>


<script>
    $('.friend').click(function () {
        $(this).find('input[type=checkbox]').prop("checked", !$(this).find('input[type=checkbox]').prop("checked"));
    });

    function auto_grow(element) {
        element.style.height = "auto";
        element.style.height = (element.scrollHeight) + "px";
    }

    $('.post-input').keypress(function () {
        $('#buttons').show();
    })

    $('#submit-btn').click(function () {
        if ($("#message").val() && $.trim($("#message").val()).length > 0) {
            $("#form").submit();
            $('#buttons').hide();
        } else {
            return false;
        }

    })


    $(function () {
        $('.messages-items-block').scrollTop(1E10);
    });

    $('#search-message-btn').click(function () {
        $(this).hide();
        $('#title').hide();
        $('#message-search-field').show('fast');
        $('#cancel-search-btn').show();
    });

    $('#cancel-search-btn').click(function () {
        $(this).hide();
        $('#title').show('fast');
        $('#message-search-field').hide();
        $('#search-message-btn').show();
    });

</script>
</#macro>