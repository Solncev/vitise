<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/jquery.maskedinput.js"></script>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top header">
    <div class="container-fluid">
        <div class="navbar-header">
            <img src="/img/logo1.png" alt="" class="logo">
        </div>
    </div>
</nav>

<@content/>

<div class="footer">
    <h4 class="footer-text">(c) 2017 Казанский (Приволжский) Федеральный Университет</h4>
    <div class="social">
        <a class="img-link" href="#"><img src="/img/insta-icon.png" alt=""></a>
        <a class="img-link" href="#"><img src="/img/vk-icon.png" alt=""></a>
        <a class="img-link" href="#"><img src="/img/twitter-icon.png" alt=""></a>
    </div>
</div>
</body>
</html>
