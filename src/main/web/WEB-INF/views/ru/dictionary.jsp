<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/common.css" rel="stylesheet">
    <link href="${contextPath}/css/dashboard.css" rel="stylesheet">
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {

            restGet('all');

        });

        var service = '/ru/dictionary';

        var restGet = function (id) {
            $.ajax({
                type: 'GET',
                url: service + "/" + id,
                dataType: 'json',
                async: false,
                success: function (result) {
                    loadWords(result);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    loadEmptyTable();
                }
            });
        };

        var getTranslations = function () {
            $.ajax({
                type: 'GET',
                url: service + "/" + $('#word').val(),
                dataType: 'json',
                async: false,
                success: function (result) {
                    loadTranslations(result);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    loadEmptyTable();
                }
            });
        };

        var addWord = function (wordId, translationId) {
            var JSONObject = {
                'english_id': wordId,
                'translation_id': translationId,
            };
            $.ajax({
                type: 'POST',
                url: service + "/add",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(JSONObject),
                dataType: 'json',
                async: false,
                success: function (result) {
                    loadWords(result);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    loadEmptyTable();
                }
            });
        }

        var loadTranslations = function (result) {
            var list = "";

            if (Array.isArray(result)) {
                var size = result.length;
            }

            if (Array.isArray(result)) {
                for(var i = 0; i != size; i++){
                    list += "<li><a href='' onclick='addWord(" + result[i].english_id  + ","+ result[i].translation_id + "); return false;'>" +result[i].russian + "</a></li>";
                }
            } else{
                list += "<li><a href='' onclick='addWord(" + result.english_id  + ","+ result.translation_id + "); return false;'>" + result.russian + "</a></li>";
            }

            $('#translations').html(list);
        }

        var loadEmptyTable = function() {

        };

        var loadWords = function(result) {
            var table = "";
            if (Array.isArray(result)) {
                var size = result.length;
            }

            if (Array.isArray(result)) {
                for(var i = 0; i != size; i++){
                    table += "<tr><td>#</td><td>" +result[i].english + "</td><td>" +result[i].russian + "</td><td>" + result[i].meaning  + "</td><td>" + result[i].example + "</td></tr>"
                }
            } else{
                table += "<tr><td>#</td><td>" +result.english + "</td><td>" + result.russian + "</td><td>" + result.meaning + "</td><td>" + result.example + "</td></tr>"
            }

            $('#response').html(table);
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
                <li><a href="${contextPath}/ru/welcome">Тренеровки слов</a></li>
                <li><a href="#about">Тренеровки грамматики</a></li>
                <li class="active"><a href="#">Словарь</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <li class="active"><a class="btn btn-default navbar-btn" onclick="document.forms['logoutForm'].submit()">Выйти</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Overview</a></li>
                <li><a href="#">Reports</a></li>
                <li><a href="#">Analytics</a></li>
                <li><a href="#">Export</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">Nav item</a></li>
                <li><a href="">Nav item again</a></li>
                <li><a href="">One more nav</a></li>
                <li><a href="">Another nav item</a></li>
                <li><a href="">More navigation</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">Nav item again</a></li>
                <li><a href="">One more nav</a></li>
                <li><a href="">Another nav item</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <h2 class="sub-header">Словарь</h2>
            <div class="table-responsive">
                <form class="navbar-form navbar-left" role="search">
                    <div class="dropdown">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" id="word" autocomplete="off">
                    </div>
                    <ul class="dropdown-menu" id="translations">

                    </ul>
                    <button type="submit" class="btn btn-default dropdown-toggle" data-toggle="dropdown" onclick="getTranslations()">Добавить</button>
                    </div>
                </form>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>English</th>
                        <th>Русский</th>
                        <th>Meaning</th>
                        <th>Example</th>
                    </tr>
                    </thead>
                    <tbody id="response">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
