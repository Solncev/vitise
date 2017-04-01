<#--events page-->
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<title>user | events</title>

<div>
    <h1>User: ${user.surname} ${user.name} ${user.thirdName}</h1>
    <p>Email: ${user.email}</p>
</div>

<hr/>
<h1>Events:</h1>
<hr/>

<div>
<#list events as event >
    <h3>${event.name}</h3>
    <h4>Date: ${event.eventDate}</h4>
    <p>${event.text}</p>
    <form action="/events/subscribe" method="post" modelAttribute="event">
        <input name="event_id" type="hidden" value="${event.id}"/>
        <#if event.subscribeStatus>
            <input class="btn btn-info btn-outline" type="submit" value="I won't go!">
        <#else>
            <input class="btn btn-info btn-outline" type="submit" value="I will go!">
        </#if>
    </form>
    <p>Number of participants: ${event.subscriptionsCount}</p>
    <i><a href="#">${event.author.name} ${event.author.thirdName}</a>, ${event.pubDate}</i>
    <#if current_user.id = event.author.id>
        <form action="/events/remove" method="post">
            <input name="event_id" type="hidden" value="${event.id}"/>
            <input class="btn btn-info btn-outline" type="submit" value="Remove!">
        </form>
    </#if>
    <hr/>
</#list>
</div>