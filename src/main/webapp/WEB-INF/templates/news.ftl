<#include "base2.ftl">
<#macro title>Новости</#macro>
<#macro name>${currentUser.name}</#macro>
<#macro my_events>/user/${currentUser.id}/events</#macro>
<#macro link>
<link rel="stylesheet" href="/css/news-profile.css"></#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="main-block">
    <div class="line1">
        <div class="col-xs-12 col-sm-9 col-md-7 col-md-offset-1 col-lg-8 col-lg-offset-1 post-news">
            <div class="col-md-12">
                <div class="col-md-12 post-block">
                    <form role="form" action="/news" id="post-form" method="post" enctype="multipart/form-data">
                        <div class="round-photo">
                            <img src="/img/bg3.png" alt="">
                        </div>
                        <textarea id="text" name="text" cols="1" class="post-input"
                                  placeholder="Здесь вы можете написать свою новость"
                                  oninput="auto_grow(this)" required></textarea>
                        <div id="buttons" style="width: 100%;">
                            <div class="" style="width: 100%;">
                                <input class="checkbox" type="checkbox" name="" id="checkbox" hidden>
                                <label for="checkbox" class="check-label"></label>
                                <span class="remember-sign">Мероприятие</span>
                            </div>

                            <div class="datetime">
                                <input type="date" name="eventDate" id="date" required>
                            </div>

                            <div class="bottom-buttons">
                                <button type="submit" id="submit-btn" class="submit-btn">Опубликовать</button>
                                <label for="add-photo-btn" class="add-photo-label"><img src="/img/camera.png"
                                                                                        alt=""></label>
                                <input type="file" name="file" id="add-photo-btn">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="line0">
        <div class="col-xs-12 col-sm-3 col-sm-push-9 col-md-3 col-md-push-8 col-lg-2 col-lg-push-9">
            <div class="col-xs-12">
                <div class="col-md-12 news-nav">
                    <h4 id="common-filter">Новости <span hidden>common</span></h4>
                    <h4 id="events-filter">Мероприятия <span hidden>events</span></h4>
                    <h4 id="all-filter">Все <span hidden>all</span></h4>
                </div>
            </div>
        </div>


        <div class="col-xs-12 col-sm-9 col-sm-pull-3 col-md-7 col-md-offset-1 col-md-pull-3 col-lg-8 col-lg-offset-1 col-lg-pull-2 post-news">
            <div class="col-md-12">
                <#list common_news as n>
                    <div class="col-md-12 post-block">
                        <div class="round-photo">
                            <img src="/img/camera.png" alt="">
                        </div>
                        <div class="post-info">
                            <h3 class="post-sign">${n.author.fullName}
                            </h3> <span>${n.pubDate}</span>
                            <p class="post-sign">&nbsp;</p>
                        </div>
                        <div class="post-content">
                            <p>${n.getText()}</p>
                        </div>
                        <#if n.isEvent()>
                            <h4>Дата события: ${n.eventDate?date}</h4>
                            <p>Количество участников: ${n.subscriptionsCount}</p>
                            <form action="/events/subscribe" method="post">
                                <input name="event_id" type="hidden" value="${n.getId()}"/>
                                <#if n.subscribeStatus>
                                    <div id="news-buttons" style="width: 100%;">
                                        <input type="submit" value="Отписаться" id="news-submit-btn">
                                    </div>
                                <#else>
                                    <div id="news-buttons" style="width: 100%;">
                                        <input type="submit" value="Записаться" id="news-submit-btn">
                                    </div>
                                </#if>
                            </form>
                        </#if>
                    </div>
                </#list>
            </div>
        </div>


    </div>
</div>
<script>

    function getNews(value) {
        $.ajax({
            'url': '/get_news',
            'data': {
                'filter': value
            },
            'type': 'get',
            'success': function (json) {
                alert(json)
            }
        })
    }

    $('#all-filter').click(function () {
        getNews($('#all-filter > span').text());
    });

    $('#events-filter').click(function () {
        getNews($('#events-filter > span').text());
    });

    $('#common-filter').click(function () {
        getNews($('#common-filter > span').text());
    });
</script>
    <#include "js_create_news.ftl">
</#macro>