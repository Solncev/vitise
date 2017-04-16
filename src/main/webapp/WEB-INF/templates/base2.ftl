<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<@link/>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/jquery.maskedinput.js" type="text/javascript"></script>
</head>
<body>


<nav class="navbar navbar-default navbar-fixed-top header">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <!--<i class="fa fa-bars fa-2x" aria-hidden="true"></i>-->
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img src="/img/logo1.png" alt="" class="logo">
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" style="margin-left: 50px;">
                <li><a href="/news">Новости</a></li>


                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Деканат <span class="caret"></span></a>
                    <ul class="dropdown-menu green-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right padded-right">
                <li class="dropdown navbar-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><@name/>
                        <span id="notifications-number"></span>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/profile">Моя страница</a></li>
                        <li><a href="/colleagues">Коллеги<span id="requests-number"></span></a></li>
                        <li><a href="/messages">Сообщения<span id="messages-number"></span></a></li>
                        <li><a href="#">Успеваемость</a></li>
                        <li><a href="#">Документы</a></li>
                        <li><a href="<@my_events/>">События</a></li>
                        <li><a href="#">Посещаемость</a></li>
                        <li><a href="/profile/change">Настройки</a></li>
                        <li><a href="/support">Поддержка</a></li>
                        <li><a href="/logout">Выход</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<@content/>
<div class="footer">
    <h4 class="footer-text">(c) 2017 Казанский (Приволжский ) Федеральный Университет</h4>
    <div class="social">
        <a class="img-link" href="#"><img src="/img/insta-icon.png" alt=""></a>
        <a class="img-link" href="#"><img src="/img/vk-icon.png" alt=""></a>
        <a class="img-link" href="#"><img src="/img/twitter-icon.png" alt=""></a>
    </div>
</div>

<script>
    var messages = 0;
    var requests = 0;
    var notifications = messages + requests;

    var getNumberOfRequests = function () {
        $.ajax({
            'url': '/colleagues/get_number',
            'data': {
                'requests': 0
            },
            'type': 'get',
            'success': function (json) {
                requests = 0;
                if (json > 0) {
                    $("#requests-number").text(' (' + json + ')');
                    requests = json;
                    notifications = requests + messages;
                    $('#notifications-number').text(' (' + notifications + ')');
                }
                if (json == 0)
                    requests = 0;
            },
            'complete': function (json) {
                setTimeout(getNumberOfRequests, 50000);
            }
        })
    };

    var getNumberOfMessages = function () {
        $.ajax({
            'url': '/messages/get_number',
            'data': {
                'messages': 0
            },
            'type': 'get',
            'success': function (json) {
                messages = 0;
                if (json > 0) {
                    $("#messages-number").text(' (' + json + ')');
                    messages = json;
                    notifications = requests + messages;
                    $('#notifications-number').text(' (' + notifications + ')');
                    if (json == 0) {
                        messages = 0;
                    }
                }
            },
            'complete': function (json) {
                setTimeout(getNumberOfMessages, 50000);
            }
        })
    };

    $(document).ready(function () {
        getNumberOfRequests();
        getNumberOfMessages();
//        alert(notifications);
        if (notifications > 0)
            $('#notifications-number').text(' (' + notifications + ')');
    });
</script>

</body>
</html>