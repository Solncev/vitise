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
                    </div>
                    <#if state == 0>
                        <form action="/colleagues/${userPage.id}/add" method="post">
                            <input class="upload-btn" type="submit" value="Добавить в коллеги">
                        </form>
                    <#elseif state == -2>
                        <form action="/colleagues/${colleagues.id}/approve" method="post">
                            <input class="upload-btn" type="submit" value="Подтвердить">
                        </form>
                    <#else>
                        <form action="/colleagues/${colleagues.id}/delete" method="post">
                            <input class="upload-btn" type="submit"
                                   value="<#if state == 1>Удалить коллегу<#else>Отменить</#if>">
                        </form>
                    </#if>
                </div>
            </div>

            <div class="col-md-9 col-sm-8">
                <div class="col-md-12 info-block">
                    <h2 style="color: #1b6d85;">${userPage.fullName}</h2>
                    <#if group??><p>Группа: &nbsp; <span class="about-text">${group.name}</span></p></#if>
                    <p>Телефон:<span class="about-text">${userPage.telephoneNumber}</span></p>
                    <p>E-mail&nbsp;&nbsp; &nbsp; &nbsp;<span class="about-text">${userPage.email}</ span></p>
                    <p>О себе:&nbsp; &nbsp;&nbsp;<span class="about-text">
                        <#if userPage.description??>${userPage.description}</#if>
                    </span></p>
                    <form action="/messages/dialog/create/${userPage.id}" method="post">
                        <input class="modify-btn" type="submit" value="Написать сообщение"/>
                    </form>
                </div>
            </div>


        </div>
    </div> <!-- line1 -->


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
                            <span>${n.date}</span>
                            <#if group??><p class="post-sign">Студент группы  ${group.name}</p></#if>
                        </div>

                        <div class="post-content">
                            <p>${n.text}</p>
                            <#if currentUser.id = n.author.id>
                                <form action="/news/remove" method="post">
                                    <input name="news_id" type="hidden" value="${n.id}"/>
                                    <input class="btn" type="submit" value="Удалить!">
                                </form>
                            </#if>
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
</#macro>