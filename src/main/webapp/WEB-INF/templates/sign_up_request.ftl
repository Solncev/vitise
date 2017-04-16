<#include "base2.ftl">
<#macro title>Заявки на регистрацию</#macro>
<#macro link>
    <link rel="stylesheet" href="/css/colleagues.css">
    <link rel="stylesheet" href="/css/dialogs.css">
</#macro>
<#macro name>${currentUser.name}</#macro>
<#macro my_events>/user/${currentUser.id}/events</#macro>

<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

    <div class="main-block">
        <div class="line0">
            <div class="col-xs-12 col-sm-5 col-sm-push-7 col-md-4 col-md-push-5 col-md-offset-1 col-lg-3 col-lg-push-5 col-lg-offset-2"
                 id="friends-search">
                <div class="col-xs-12">
                </div>
            </div>
            <div class="col-xs-12 col-sm-7 col-sm-pull-5 col-md-5 col-md-pull-4 col-lg-5">
                <div class="col-xs-12">
                    <div class="col-xs-12 input-wrapper">
                        <a href="#"><i class="fa fa-lg fa-search" aria-hidden="true"></i></a>
                        <input type="text" placeholder="Начните вводить имя или фамилию" class="search-input" id="friend-search">
                    </div>
                </div>
            </div>
        </div>

        <div class="line0">
            <div class="col-xs-12 col-sm-3 col-sm-push-9 col-md-3 col-md-push-8 col-lg-2 col-lg-push-9 line0">
                <div class="col-xs-12">
                    <div class="col-md-12 news-nav">
                        <h4 id="friends-btn">Студенты</h4>
                        <h4 id="income-btn">НПР</h4>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 col-sm-9 col-sm-pull-3 col-md-7 col-md-offset-1 col-md-pull-3 col-lg-8 col-lg-offset-1 col-lg-pull-2 post-news">
                <div class="col-md-12"  id="friends">
                    <#list students as u>
                    <div class="col-md-12 post-block">
                        <div class="round-photo">
                            <img src="/img/camera.png" alt="">
                        </div>
                        <div class="post-info">
                            <h3 class="post-sign" style="margin-right: 20px">${u.fullName}:</h3>
                            <h3 class="post-sign" style="color: #122b40">Группа: ${u.group.name}</h3>

                        </div>
                        <form action="/sign_up_request/${u.id}/delete" method="post">
                            <input type="submit" value="Отклонить" id="news-submit-btn">
                        </form>
                        <form action="/sign_up_request/${u.id}/approve" method="post">
                            <input type="submit" value="Одобрить" id="news-submit-btn">
                        </form>
                    </div>
                    <#else>
                        No requests
                    </#list>
                </div>


                <div class="col-md-12"  id="income">
                    <#list NPRs as u>
                        <div class="col-md-12 post-block">
                            <div class="round-photo">
                                <img src="/img/camera.png" alt="">
                            </div>
                            <div class="post-info">
                                <h3 class="post-sign" style="margin-right: 20px">${u.fullName}:</h3>
                                <h3 class="post-sign" style="color: #122b40">${u.statusName.nameOnRus}</h3>

                            </div>
                            <form action="/sign_up_request/${u.id}/delete" method="post">
                                <input type="submit" value="Отклонить" id="news-submit-btn">
                            </form>
                            <form action="/sign_up_request/${u.id}/approve" method="post">
                                <input type="submit" value="Одобрить" id="news-submit-btn">
                            </form>
                        </div>
                    <#else>
                        No requests
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
            $('#friends-btn').css('border','1px solid #1b7181');
            $('#income-btn').css('border','none');
            $('#outcome-btn').css('border','none');
            $('#friends').show();
            $('#income').hide();
            $('#outcome').hide();
        });

        $('#income-btn').click(function () {
            $('#income-btn').css('border','1px solid #1b7181');
            $('#friends-btn').css('border','none');
            $('#outcome-btn').css('border','none');
            $('#income').show();
            $('#friends').hide();
            $('#outcome').hide();
        });

        $('#outcome-btn').click(function () {
            $('#outcome-btn').css('border','1px solid #1b7181');
            $('#income-btn').css('border','none');
            $('#friends-btn').css('border','none');
            $('#outcome').show();
            $('#income').hide();
            $('#friends').hide();
        });
    </script>

</#macro>


