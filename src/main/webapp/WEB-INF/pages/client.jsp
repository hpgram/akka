<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Talky Talky Application</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/sunny/jquery-ui-1.10.3.custom.css">
    <link rel="stylesheet" href="css/custom.css">
    <script src="js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>
<!--[if lt IE 7]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a
        href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<div id="login" style="display:none; cursor: default">
    <p class="feedback">
        User: <input type="text" id="username">
    </p>
    <input type="button" id="login-button" value="Login" />
</div>

<div id="loggedout" style="display:none; cursor: default">
    <p class="feedback">
        Logged out.
    </p>
    <input type="button" id="retry" value="Retry" />
</div>

<div class="main-container">
    <!-- Add your site or application content here -->
    <div class="container clearfix">
        <div>
            <p class="feedback">
                <span>Logged in as: </span><span id="loggedin-username"></span>
                <br>
                <span>Private Messages To:</span> <span id="select-result">None</span>.
            </p>
        </div>
        <div id="chatarea-div" class="chat-friends">
            <textarea id="chatarea" disabled></textarea>
        </div>
        <div id="friends-div" class="chat-friends">
            <ol id="friends">
            </ol>
        </div>
        <div>
            <textarea id="user-console"></textarea>
            <input id="submit" type="submit" value="Submit"/>
        </div>
    </div>
</div>

<!-- Scripts -->
<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.9.1.js"><\/script>')</script>
<script type="text/javascript" src="js/vendor/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="js/plugins.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
</body>
</html>
