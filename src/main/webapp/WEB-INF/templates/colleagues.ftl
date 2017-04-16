<#include "base2.ftl">
<#macro title>Коллеги</#macro>
<#macro name>${currentUser.name}</#macro>
<#macro my_events>/user/${currentUser.id}/events</#macro>
<#macro link>
<link rel="stylesheet" href="/css/colleagues.css"></#macro>
<#macro content>
<div class="main-block">

    <div class="line0">
        <div class="col-xs-12 col-sm-3 col-sm-push-9 col-md-3 col-md-push-8 col-lg-2 col-lg-push-9 line0">
            <div class="col-xs-12">
                <div class="col-md-12 news-nav">
                    <h4 id="friends-btn">Мои друзья</h4>
                    <h4 id="income-btn">Входящие заявки</h4>
                    <h4 id="outcome-btn">Исходящие заявки</h4>
                </div>
            </div>
        </div>


        <div class="col-xs-12 col-sm-9 col-sm-pull-3 col-md-7 col-md-offset-1 col-md-pull-3 col-lg-8 col-lg-offset-1 col-lg-pull-2 post-news">
            <div class="col-md-12" id="friends">
                <#list colleagues as colleague>
                    <div class="col-md-12 post-block">
                    <#--<div class="round-photo">-->
                    <#--<img src="/img/camera.png" alt="">-->
                    <#--</div>-->
                        <div class="post-info">
                            <h3 class="post-sign">
                                <#if colleague.receiver.id == currentUser.id>
                                    <a href="/user/${colleague.sender.id}">${colleague.sender.fullName}</a>
                                <#else>
                                    <a href="/user/${colleague.receiver.id}">${colleague.receiver.fullName}</a>
                                </#if>
                            </h3>
                        </div>
                        <form action="/colleagues/${colleague.id}/delete" method="post">
                            <input type="submit" id="news-submit-btn" value="Удалить">
                        </form>
                    </div>
                <#else>
                    <h3>У вас нет друзей</h3>
                </#list>
            </div>

            <div class="col-md-12" id="income">
                <#list requests as request>
                    <div class="col-md-12 post-block">
                    <#--<div class="round-photo">-->
                    <#--<img src="/img/camera.png" alt="">-->
                    <#--</div>-->
                        <div class="post-info">
                            <h3 class="post-sign"><a href="/user/${request.sender.id}">
                            ${request.sender.fullName}</a></h3>
                        </div>
                        <form action="/colleagues/${request.id}/approve" method="post">
                            <input type="submit" id="news-submit-btn" value="Добавить">
                        </form>
                        <form action="/colleagues/${request.id}/delete" method="post">
                            <input type="submit" id="news-submit-btn" value="Отклонить">
                        </form>
                    </div>
                <#else>
                    <h3>У вас нет входящих заявок</h3>
                </#list>
            </div>

            <div class="col-md-12" id="outcome">
                <#list myrequests as request>
                    <div class="col-md-12 post-block">
                    <#--<div class="round-photo">-->
                    <#--<img src="/img/camera.png" alt="">-->
                    <#--</div>-->
                        <div class="post-info">
                            <h3 class="post-sign"><a href="/user/${request.receiver.id}">
                            ${request.receiver.fullName}</a></h3>
                        </div>
                        <form action="/colleagues/${request.id}/delete" method="post">
                            <input type="submit" id="news-submit-btn" value="Отменить">
                        </form>
                    </div>
                <#else>
                    <h3>У вас нет исходящих заявок</h3>
                </#list>
            </div>
        </div>
    </div>
</div>
<script>

    $(document).ready(function () {
        $('#friends-btn').click();
    });

    $('#friends-btn').click(function () {
        $('#friends-btn').css('border', '1px solid #1b7181');
        $('#income-btn').css('border', 'none');
        $('#outcome-btn').css('border', 'none');
        $('#friends').show();
        $('#income').hide();
        $('#outcome').hide();
    });

    $('#income-btn').click(function () {
        $('#income-btn').css('border', '1px solid #1b7181');
        $('#friends-btn').css('border', 'none');
        $('#outcome-btn').css('border', 'none');
        $('#income').show();
        $('#friends').hide();
        $('#outcome').hide();
    });

    $('#outcome-btn').click(function () {
        $('#outcome-btn').css('border', '1px solid #1b7181');
        $('#income-btn').css('border', 'none');
        $('#friends-btn').css('border', 'none');
        $('#outcome').show();
        $('#income').hide();
        $('#friends').hide();
    });
</script>
</#macro>