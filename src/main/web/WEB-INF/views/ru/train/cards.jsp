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

    <title>Словарные карточки</title>

    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/common.css" rel="stylesheet">
    <link href="${contextPath}/css/train.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {

            restGet();
            setWord();

        });

        var service = '/ru/train';

        var restGet = function () {
            $.ajax({
                type: 'GET',
                url: service + "/",
                dataType: 'json',
                async: false,
                success: function (result) {
                    loadWords(result);
                },
                error: function (jqXHR, textStatus, errorThrown) {

                }
            });
        };

        var size;
        var listOfWords = [];

        var loadWords = function (result) {
            if (Array.isArray(result)) {
                size = result.length;
            } else {
                size = 1;
            }

            if (Array.isArray(result)) {
                for (var i = 0; i != size; i++) {
                    var word = new Object();
                    word.english = result[i].english;
                    word.russian = result[i].russian;
                    word.example = result[i].example;
                    word.meaning = result[i].meaning;
                    word.id = result[i].id;
                    listOfWords[i] = word;
                }
            } else {
                var word = new Object();
                word.english = result.english;
                word.russian = result.russian;
                word.example = result.example;
                word.meaning = result.meaning;
                word.id = result.id;
                listOfWords[0] = word;
            }

        }

        var i = 0;

        var setWord = function () {
            if (i < size) {
                var table = "<h1>" + listOfWords[i].english + "</h1>";
                var elem = document.getElementById('answer-button');
                elem.style.visibility = 'visible';
                $('#english').html(table);
            } else {
                var table = "<h1>Конец</h1>";
                $('#english').html(table);
            }
        }

        var getRepeat = function (id) {
            $.ajax({
                type: 'GET',
                url: service + "/repeat/" + id,
                dataType: 'json',
                async: false,
                success: function (result) {
                    setRussianWord();
                    setDateRepeat(result);
                },
                error: function (jqXHR, textStatus, errorThrown) {

                }
            });
        }

        var setRussianWord = function () {
            var table = "<h1>" + listOfWords[i].russian + "</h1>";
            var elem = document.getElementById('answer-button');
            elem.style.visibility = 'hidden';
            $('#russian').html(table);
        }

        var setDateRepeat = function (count) {
            var string = "";

            if (count == 0) {
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 0);'>Повтор через 10 минут</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 1);'>Повтор через день</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 2);'>Повтор через 3 дня</button>";
            }

            if (count == 1) {
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 0);'>Повтор через 10 минут</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 1);'>Повтор через день</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 2);'>Повтор через 3 дня</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 3);'>Повтор через 7 дней</button>";
            }

            if (count == 2) {
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 0);'>Повтор через 10 минут</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 2);'>Повтор через 3 дня</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 3);'>Повтор через 7 дней</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 4);'>Повтор через 14 дней</button>";
            }

            if (count == 3) {
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 0);'>Повтор через 10 минут</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 3);'>Повтор через 7 дней</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 4);'>Повтор через 14 дней</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 5);'>Повтор через 30 дней</button>";
            }

            if (count == 4) {
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 0);'>Повтор через 10 минут</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 4);'>Повтор через 14 дней</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 5);'>Повтор через 30 дней</button>";
            }

            if (count == 5) {
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 0);'>Повтор через 10 минут</button>";
                string += "<button type='button' class='btn btn-default' onclick='setRepeat(listOfWords[i].id, 5);'>Повтор через 30 дней</button>";
            }

            $('#dates').html(string);
        }

        var setRepeat = function (id, count) {
            $.ajax({
                type: 'PUT',
                url: service + "/updateRepeat/" + id,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(count),
                cache: false,
                complete: function (result) {
                    i++;
                    setEmptyRussian();
                    setWord();
                    $('#dates').html("");
                }
            });
        };

        var setEmptyRussian = function(){
            $('#russian').html("");
        }

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
                <li class="active"><a href="${contextPath}/ru/welcome">Тренеровки слов</a></li>
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

<div class="container container-for-train">

    <div class="jumbotron">
        <div class="row word-pane">
            <div class="col-lg-12">
                <div id="english">
                </div>
                <div id="russian">
                </div>
                <div id="answer-button">
                    <button type='button' class='btn btn-default' onclick='getRepeat(listOfWords[i].id)'>Ответ</button>
                </div>
                <div id="dates" class="dates">
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
