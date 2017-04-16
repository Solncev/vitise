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

    <div class="line1">
        <div class="col-xs-12 col-sm-5 col-sm-push-7 col-md-4 col-md-push-5 col-md-offset-1 col-lg-3 col-lg-push-5 col-lg-offset-2">
            <div class="col-xs-12 buttons">
                <input type="submit" value="Создать беседу" class="create-conversation" id="create-conversation">
                <input type="submit" value="Отменить создание" class="create-conversation" id="cancel-creation" hidden>
            </div>
        </div>
        <div class="col-xs-12 col-sm-7 col-sm-pull-5 col-md-5 col-md-pull-4 col-lg-5 col-lg-pull-3"
             id="messages-search">
            <div class="col-xs-12">
                <div class="col-xs-12 input-wrapper">
                    <a href="#"><i class="fa fa-lg fa-search" aria-hidden="true"></i></a>
                    <input type="text" placeholder="Поиск по сообщениям" class="search-input" id="conversation-search">
                    <input type="text" placeholder="Начните вводить имя или фамилию" class="search-input"
                           id="friend-search" hidden>
                </div>
            </div>
        </div>

    </div>

    <div class="line1">
        <div class="col-xs-12 col-sm-5 col-sm-push-7 col-md-4 col-md-push-5 col-md-offset-1 col-lg-3 col-lg-push-5 col-lg-offset-2"
             id="dialogs-block">
            <div class="col-xs-12">
                <div class="col-xs-12 messages-filter">
                    <h4>Все сообщения</h4>
                    <h4>Непрочитанные</h4>
                    <h4>От студентов</h4>
                    <h4>От преподавателей</h4>
                    <h4>От сотрудников деканата</h4>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-7 col-sm-pull-5 col-md-5 col-md-pull-4 col-lg-5 col-lg-pull-3" id="messages-block">
            <div class="col-xs-12">
                <div class="col-xs-12 messages-block">


                    <#list dialogs as dialog>
                        <a href="/messages/<#if dialog.conversation>conversation<#else>dialog</#if>/${dialog.id}">
                            <div class="message">
                                <div class="round-photo">
                                    <img src="/img/mal.png" alt="">
                                </div>
                                <div class="message-info"
                                    <#if dialog.lastMessage?? && dialog.readingLogDate < dialog.lastMessage.date && dialog.lastMessage.user.id != currentUser.id>
                                     style="background-color: #f2faff;"
                                    </#if>>
                                    <h4>${dialog.name}</h4> <#if dialog.lastMessage??>
                                    <span>${dialog.lastMessage.date}</span></#if>

                                    <#if dialog.newMessagesCount != 0>
                                        <p>Новых сообщений: ${dialog.newMessagesCount}</p>
                                    </#if>

                                    <#if dialog.lastMessage??>
                                        <p><#if dialog.conversation || dialog.lastMessage.user.id==currentUser.id>
                                            <b>${dialog.lastMessage.user.fullName}
                                                : </b></#if>${dialog.lastMessage.message}</p>
                                    </#if>
                                </div>
                            </div>
                        </a>
                    </#list>

                </div>
            </div>
        </div>


        <div class="col-xs-12 col-sm-7 col-sm-pull-5 col-md-5 col-md-pull-4 col-lg-5 col-lg-pull-3" id="friends-block"
             hidden>
            <div class="col-xs-12">
                <form action="/messages/conversation/create" method="post">
                    <div class="col-xs-12 messages-block friends-block">


                        <#list colleagues as colleague>
                            <div class="message friend">
                                <div class="round-photo">
                                    <img src="/img/mal.png" alt="">
                                </div>
                                <div class="message-info">
                                    <h4>${colleague.fullName}</h4>
                                    <input type="checkbox" name="members" class="f-check" id="f-check-123"
                                           value="${colleague.id}">
                                    <label for="f-check-123"></label>
                                </div>
                            </div>
                        </#list>

                    </div>
                    <div class="col-xs-12 messages-block">
                        <div class="creation">
                            <input type="text" name="name" placeholder="Введите название беседы" class="create-input">
                            <input type="submit" value="Создать" class="create-btn">
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

</div>


<script>
    $('#create-conversation').click(function () {
        $('#create-conversation').hide();
        $('#messages-block').hide();
        $('#conversation-search').hide();
        $('#cancel-creation').show();
        $('#friends-block').show('slow');
        $('#friend-search').show('slow');
    })

    $('#cancel-creation').click(function () {
        $('#create-conversation').show();
        $('#messages-block').show('slow');
        $('#conversation-search').show('slow');
        $('#cancel-creation').hide();
        $('#friends-block').hide();
        $('#friend-search').hide();
    })

    $('.friend').click(function () {
        $(this).find('input[type=checkbox]').prop("checked", !$(this).find('input[type=checkbox]').prop("checked"));
    });
</script>
</#macro>