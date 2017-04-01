<#--event create page-->
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<title>events | create</title>

<@sf.form action="/events/create" method="post" modelAttribute="event">
<fieldset>
    <div class="field">
        <@sf.label path="name">name</@sf.label>
        <@sf.input path="name" type="text" cssClass="form-control"/>
    </div>
    <div class="field">
        <@sf.label path="text">text</@sf.label>
        <@sf.textarea cols="60" rows="10" path="text"/>
    </div>

    <div class="field">
        <@sf.label path="eventDate">date</@sf.label>
        <@sf.input path="eventDate" type="date" cssClass="form-control"/>
    </div>

    <div class="form-group">
        <input class="btn btn-info btn-outline" type="submit" value="создать">
    </div>
</fieldset>
</@sf.form>