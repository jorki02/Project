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
    <link href="${contextPath}/css/trainTranslation.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {

            restGet();
            restGetListOfWrongWords();
            setWord();

        });

        var service = '/ru/train/translation';

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

        var restGetListOfWrongWords = function () {
            $.ajax({
                type: 'GET',
                url: service + "/wrong",
                dataType: 'json',
                async: false,
                success: function (result) {
                    loadWrongWords(result);
                },
                error: function (jqXHR, textStatus, errorThrown) {

                }
            });
        };

        var size;
        var sizeWrongWords;
        var listOfWords = [];
        var listOfWrongWords = [];

        var loadWrongWords = function (result) {
            sizeWrongWords = result.length;

            for (var i = 0; i != sizeWrongWords; i++) {
                var word = new Object();
                word.english = result[i].english;
                word.russian = result[i].russian;
                word.example = result[i].example;
                word.meaning = result[i].meaning;
                word.id = result[i].id;
                listOfWrongWords[i] = word;
            }
        }

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

            var arr = randomInteger(0, sizeWrongWords - 1, 4);
            if (i < size) {
                var word = "<h1>" + listOfWords[i].english + "</h1>";
                var answers = [];
                answers[0] = "<button id='right-answer' type='button' class='btn btn-default' onclick='getRepeat(listOfWords[i].id); fillAnswer(); stunAnswers()'>" + listOfWords[i].russian + "</button>";
                answers[1] = "<button id='answer1' type='button' class='btn btn-default' onclick='getRepeat(listOfWords[i].id); fillAnswer(); fillThis(this); stunAnswers();'>" + listOfWrongWords[arr[0]].russian + "</button>";
                answers[2] = "<button id='answer2' type='button' class='btn btn-default' onclick='getRepeat(listOfWords[i].id); fillAnswer(); fillThis(this); stunAnswers();'>" + listOfWrongWords[arr[1]].russian + "</button>";
                answers[3] = "<button id='answer3' type='button' class='btn btn-default' onclick='getRepeat(listOfWords[i].id); fillAnswer(); fillThis(this); stunAnswers();'>" + listOfWrongWords[arr[2]].russian + "</button>";
                answers[4] = "<button id='answer4' type='button' class='btn btn-default' onclick='getRepeat(listOfWords[i].id); fillAnswer(); fillThis(this); stunAnswers();'>" + listOfWrongWords[arr[3]].russian + "</button>";
                answers.sort(compareRandom);
                var response = "";
                for(var k = 0; k < answers.length; k++){
                    response += answers[k];
                }
                $('#word').html(word);
                $('#answers').html(response);
            } else {
                var table = "<h1>Конец</h1>";
                $('#table-quiz').html(table);
            }
        }

        var compareRandom = function(a, b) {
            return Math.random() - 0.5;
        }

        var randomInteger = function (min, max, num) {
            var i, arr = [], res = [];
            for (i = min; i <= max; i++) arr.push(i);
            for (i = 0; i < num; i++) res.push(arr.splice(Math.floor(Math.random() * (arr.length)), 1)[0])
            return res;
        }

        var stunAnswers = function(){
            var answer = document.getElementById('right-answer');
            var elem1 = document.getElementById('answer1');
            var elem2 = document.getElementById('answer2');
            var elem3 = document.getElementById('answer3');
            var elem4 = document.getElementById('answer4');
            answer.disabled = true;
            elem1.disabled = true;
            elem2.disabled = true;
            elem3.disabled = true;
            elem4.disabled = true;
        }

        var fillAnswer = function (){
            var elem = document.getElementById('right-answer');
            elem.className = "btn btn-success";
        }

        var fillThis = function(button){
            button.className = "btn btn-danger";
        }

        var getRepeat = function (id) {
            $.ajax({
                type: 'GET',
                url: service + "/repeat/" + id,
                dataType: 'json',
                async: false,
                success: function (result) {
                    setDateRepeat(result);
                },
                error: function (jqXHR, textStatus, errorThrown) {

                }
            });
        };

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
        };

        var setRepeat = function (id, count) {
            $.ajax({
                type: 'PUT',
                url: service + "/updateRepeat/" + id,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(count),
                cache: false,
                complete: function (result) {
                    i++;
                    setWord();
                    $('#dates').html("");
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
                <div id="table-quiz">
                    <div id="word" class="col-md-2-offset col-md-4">
                    </div>
                    <div id="answers" class="col-md-4-offset col-md-4 btn-group-vertical">
                    </div>
                </div>
            </div>
            <div id="dates" class="dates">
            </div>
        </div>
    </div>

</div>

</body>
</html>

