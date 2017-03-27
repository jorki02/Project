<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Static Top Navbar Example for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/common.css" rel="stylesheet">
    <link href="${contextPath}/css/welcome.css" rel="stylesheet">
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {

            countRemainingWords("tw");
            countRemainingWords("wt");
            countRemainingWords("cards");

        });

        var service = '/ru/train';

        var countRemainingWords = function (type) {
            $.ajax({
                type: 'GET',
                url: service + "/remaining/" + type,
                dataType: 'json',
                async: false,
                success: function (result) {
                    var table = "Оставшиеся слова: " + result;
                    if (result == 0) {
                        $("#button-train-" + type).addClass("disabledbutton");
                    }
                    $('#remaining-words-' + type).html(table);
                },
                error: function (jqXHR, textStatus, errorThrown) {

                }
            });
        };

    </script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<!-- Static navbar -->
<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-middle">
                <li class="active"><a href="#">Тренеровки слов</a></li>
                <li><a href="#about">Тренеровки грамматики</a></li>
                <li><a href="${contextPath}/ru/dictionary">Словарь</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <li class="active"><a class="btn btn-default navbar-btn"
                                      onclick="document.forms['logoutForm'].submit()">Выйти</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="row">
            <div class="col-lg-4">
                <div class="description">
                    <h2>Слово - Перевод</h2>
                    <p>Тренировка “Слово-перевод” улучшает навык перевода слов с английского на ваш родной язык, помогая
                        лучше понимать английские тексты и речь. </p>
                </div>
                <div id="remaining-words-wt" class="remaining-words text-muted"></div>
                <div id="button-train-wt"  class="button-train">
                    <a class="btn btn-primary" href="/ru/train/translationWord" role="button">Тренеровать</a>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="description">
                    <h2>Перевод - Слово</h2>
                    <p>Тренировка “Перевод-слово” развивает навык перевода с вашего родного языка на английский, что
                        позволяет лучше выражать свои мысли на английском языке.</p>
                </div>
                <div id="remaining-words-tw" class="remaining-words text-muted"></div>
                <div id="button-train-tw" class="button-train">
                    <a class="btn btn-primary" href="/ru/train/wordTranslation" role="button">Тренеровать</a>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="description">
                    <h2>Словарные карточки</h2>
                    <p>Словарные карточки помогают быстро проверить знания английских слов из личного словаря.</p>
                </div>
                <div id="remaining-words-cards" class="remaining-words text-muted"></div>
                <div id="button-train-cards" class="button-train">
                    <a class="btn btn-primary" href="/ru/train/cards"
                       role="button">Тренеровать</a>
                </div>
            </div>
        </div>
    </div>


</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
</body>
</html>