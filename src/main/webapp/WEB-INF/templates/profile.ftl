<#include "base2.ftl">
<#macro title>Профиль</#macro>
<#macro name>${currentUser.name}</#macro>
<#macro my_events>/user/${currentUser.id}/events</#macro>
<#macro link>
<link rel="stylesheet" href="/css/news-profile.css"></#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<div class="main-block">
    <div class="line1">
        <div class="col-md-10 col-md-offset-1 col-sm-12 col-xs-12">

            <div class="col-md-3 col-sm-4">
                <div class="col-md-12 photo-block">

                    <div class="photo-border">
                        <img class="profile-photo" src="/img/bg3.png" alt="">
                        <!--<div class="profile-photo"-->
                        <!--style="background-color: #8A8490; width: 100%; height: 	100%;"></div>-->
                    </div>
                    <input type="file" name="" id="add-profile-photo">
                    <label for="add-profile-photo" class="upload-btn">Загрузить фото</label>
                </div>
            </div>

            <div class="col-md-9 col-sm-8">
                <div class="col-md-12 info-block">
                    <h2 style="color: #1b6d85;">${currentUser.fullName}</h2>
                    <#if group??><p>Группа: &nbsp; <span class="about-text">${group.name}</span></p></#if>
                    <p>Телефон:<span class="about-text">${currentUser.telephoneNumber}</span></p>
                    <p>E-mail&nbsp;&nbsp; &nbsp; &nbsp;<span class="about-text">${currentUser.email}</ span></p>
                    <p>О себе:&nbsp; &nbsp;&nbsp;<span class="about-text">
                        <#if currentUser.description??>${currentUser.description}</#if>
                    </span></p>
                    <a href="/profile/change">
                        <button class="modify-btn">Редактировать информацию</button>
                    </a>
                </div>
            </div>

        </div>
    </div> <!-- line1 -->

    <div class="line1">
        <div class="col-md-10 col-md-offset-1 col-sm-12 col-xs-12">
            <div class="col-md-12">
                <div class="col-md-12 post-block">
                    <form role="form" action="/news" id="post-form" name="post-form" method="post"
                          enctype="multipart/form-data">
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

    <div class="line1">
        <#list newses as n>
            <div class="col-md-10 col-md-offset-1 col-sm-12 col-xs-12 post">
                <div class="col-md-12">
                    <div class="col-md-12 post-block">
                        <div class="round-photo">
                            <img src="/img/bg3.png" alt="">
                        </div>
                        <div class="post-info">
                            <h3 class="post-sign">${n.author.surname} ${n.author.name} ${n.author.thirdName}</h3>
                            <span>${n.pubDate}</span>
                            <#if group??><p class="post-sign">Студент группы  ${group.name}</p></#if>
                        </div>

                        <div class="post-content">
                            <p>${n.text}</p>
                            <form action="/news/remove" method="post">
                                <input name="news_id" type="hidden" style="display: none" value="${n.id}"/>
                                <button type="submit" class="delete-btn"  title="Удалить">
                                    <i class="fa fa-2x fa-times" aria-hidden="true"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="col-md-10 col-md-offset-1 col-sm-12 col-xs-12 post">
                <div class="col-md-12">
                    <div class="col-md-12 post-block">
                        <p>Нет новостей</p>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>
    <#include "js_create_news.ftl">
</#macro>