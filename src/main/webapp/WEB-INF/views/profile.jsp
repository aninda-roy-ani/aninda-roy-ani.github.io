<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.test.app.model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        .resized-image {
            width: 300px;
            height: 300px;
        }
        .custom-input {
            height: 150px;
        }
        .custom-link {
            text-transform: uppercase;
            font-family: "Berlin Sans FB";
            font-size: x-large;
            text-emphasis: darkblue;
            text-decoration-color: darkblue;
        }
    </style>

</head>
<body>

<div class="container">

    <div class="card mb-3 text-white text-center bg-dark">
        <div class="card-header">
            User Profile
        </div>
        <div class="row no-gutters">
            <div class="col-md-4">
                <img src="${pageContext.request.contextPath}${acc.getImage()}" alt="image" class="resized-image img-thumbnail">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h3 class="card-title">${acc.getName().toUpperCase()}</h3>
                    <p class="card-text"><b>EMAIL:</b> ${acc.getEmail()}</p>
                    <p class="card-text"><b>PHONE:</b> ${acc.getPhone()}</p>
                    <p class="card-text"><b>CURRENTLY IN:</b> ${acc.getAddress()}</p>
                    <p class="card-text"><b>BIRTHDAY:</b> ${acc.getBirthday()}</p>
                </div>
            </div>
        </div>
        <div class="card-footer text-muted">
            A man of Madness!
        </div>
    </div>
    <div class="card text-warning bg-danger mb-3">
        <div class="card-header">
            <b>NOTES</b>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                <c:choose>
                    <c:when test="${AuthorNotes.getNotes().size() > 0}">

                    <c:forEach var="noteT" items="${AuthorNotes.getNotes()}">
                        <div class="card text-dark bg-warning text-center">
                            <div class="card-header">
                                <h3><b>${noteT.getTitle().toUpperCase()}</b></h3>
                            </div>
                            <div class="card-body">
                                <blockquote class="blockquote mb-0">
                                    <p>${noteT.getDescription()}</p>
                                    <footer class="blockquote-footer"><cite>${noteT.getDate()}</cite></footer>
                                </blockquote>
                            </div>
                            <div class="card-footer">
                                <p class="card-text" style="transform: rotate(0);">
                                    <b>${noteT.getScore()} scored! ${noteT.getThoughts().size()} thoughts! <a href="#grader" class="text-dark stretched-link"
                                                                  data-toggle="collapse" role="button" aria-expanded="false" aria-controls="grader">
                                        <span class="custom-link"><i>Share yours here!</i></span></a>
                                    </b>
                                </p>
                                <div class="collapse multi-collapse" id="grader">
                                    <div class="row">
                                        <form action="${pageContext.request.contextPath}/updateNote" id="scoresForm" method="POST">
                                        <input type="hidden" name="email" id="email" value="${acc.getEmail()}">
                                        <input type="hidden" name="password" id="password" value="${acc.getPassword()}">
                                        <input type="hidden" name="note_no1" id="note_no1" value="${noteT.getNoteid()}">
                                        <div class="col-6">
                                            <div class="card bg-secondary text-warning text-center">
                                                <div class="card-header">
                                                    <b>PUT SOME SCORES OVER YOUR BUDDY'S NOTE!</b>
                                                </div>
                                                <div class="card-body">
                                                    <input type="hidden" name="noteDescription" id="noteDescription" value="${noteT.getDescription()}">
                                                    <p>PRESENTATION (Out of 100)<input type="number" class="form-control" min="1" max="100" name="ScorePresentation" id="ScorePresentation"></p>
                                                    <p>STRUCTURE (Out of 100)<input type="number" class="form-control" min="1" max="100" name="ScoreStructure" id="ScoreStructure"></p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-6">
                                            <div class="card bg-secondary text-warning text-center">
                                                <div class="card-header">
                                                    <b>LEAVE A THOUGHT!</b>
                                                </div>
                                                <div class="card-body">
                                                    <p><textarea class="form-control custom-input mt-1" maxlength="500" rows="5" cols="100" name="thoughtText" id="thoughtText"></textarea></p>

                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn btn-dark form-control" type="submit">SUBMIT</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </c:when>
                    <c:otherwise><i>NO NOTES YET!</i></c:otherwise>
                </c:choose>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
</script>

</body>
</html>
