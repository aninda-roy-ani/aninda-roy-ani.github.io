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
        .resized-image-buddy {
            height: 200px;
        }
        .custom-input {
            height: 150px;
        }
        .custom-link {
            text-transform: uppercase;
            font-family: "Berlin Sans FB";
        }
        .smaller-text{
            font-size: xx-small;
        }
    </style>

</head>
<body>

<div class="container">

    <div class="card mb-3 text-white text-center bg-secondary">
        <div class="card-header">
            User Profile
        </div>
        <div class="row no-gutters bg-dark">
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
            <div class="row">
                <div class="col">
                    <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#findBuddies">
                        Find Some Buddies
                    </button>

                    <div class="modal" id="findBuddies" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="findBudTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="findBudTitle">SUGGESTION FOR BUDDIES</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <c:choose>
                                        <c:when test="${lob.size() > 0}">
                                            <div class="row row-cols-1 row-cols-md-3">
                                            <c:forEach var="budd" items="${lob}">
                                                <div class="col-md-6 mb-1">
                                                    <div class="card">
                                                        <img src="${pageContext.request.contextPath}${budd.getImage()}" class="card-img-top resized-image-buddy" alt="...">
                                                        <div class="card-body smaller-text">
                                                            <form action="${pageContext.request.contextPath}/newBudd" method="POST">
                                                            <h5 class="card-title" style="text-transform: uppercase;">${budd.getName()}</h5>
                                                            <p class="card-text">EMAIL: ${budd.getEmail()}</p>
                                                            <p class="card-text">LIVING IN: ${budd.getAddress()}</p>
                                                            <div class="btn-group" role="group" aria-label="Basic example">
                                                                <button type="submit" class="btn btn-secondary">More</button>
                                                                <input type="hidden" name="option" id="option" value="0">
                                                                <button type="submit" class="btn btn-secondary">Request</button>
                                                            </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <p>NO BUDDY TO SUGGEST!</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">More</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#Buddies">
                        List of Added Buddies
                    </button>

                    <div class="modal" id="Buddies" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="BudTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="BudTitle">BUDDIES</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <c:choose>
                                        <c:when test="${lob.size() > 0}">
                                            <div class="row row-cols-1 row-cols-md-3">
                                                <c:forEach var="budd" items="${lob}">
                                                    <div class="col-md-6 mb-1">
                                                        <div class="card">
                                                            <img src="${pageContext.request.contextPath}${budd.getImage()}" class="card-img-top resized-image-buddy" alt="...">
                                                            <div class="card-body smaller-text">
                                                                <form action="${pageContext.request.contextPath}/listBudd" method="POST">
                                                                    <h5 class="card-title" style="text-transform: uppercase;">${budd.getName()}</h5>
                                                                    <p class="card-text">EMAIL: ${budd.getEmail()}</p>
                                                                    <p class="card-text">LIVING IN: ${budd.getAddress()}</p>
                                                                    <div class="btn-group" role="group" aria-label="Basic example">
                                                                        <button type="submit" class="btn btn-secondary">More</button>
                                                                        <input type="hidden" name="option1" id="option1" value="0">
                                                                        <button type="submit" class="btn btn-secondary">Remove</button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <p>NO BUDDY TO SUGGEST!</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">More</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#findBuddiesReq">
                        Find Buddy Request
                    </button>

                    <div class="modal" id="findBuddiesReq" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="findBudReqTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="findBudReqTitle">REQUESTED FOR ADD-BUDDY</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <c:choose>
                                        <c:when test="${lob.size() > 0}">
                                            <div class="row row-cols-1 row-cols-md-3">
                                                <c:forEach var="budd" items="${lob}">
                                                    <div class="col-md-6 mb-1">
                                                        <div class="card">
                                                            <img src="${pageContext.request.contextPath}${budd.getImage()}" class="card-img-top resized-image-buddy" alt="...">
                                                            <div class="card-body smaller-text">
                                                                <form action="${pageContext.request.contextPath}/buddReq" method="POST">
                                                                    <h5 class="card-title" style="text-transform: uppercase;">${budd.getName()}</h5>
                                                                    <p class="card-text">EMAIL: ${budd.getEmail()}</p>
                                                                    <p class="card-text">LIVING IN: ${budd.getAddress()}</p>
                                                                    <div class="btn-group" role="group" aria-label="Basic example">
                                                                        <button type="submit" class="btn btn-secondary">More</button>
                                                                        <input type="hidden" name="option2" id="option2" value="0">
                                                                        <button type="submit" class="btn btn-secondary">Accept</button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <p>NO BUDDY TO SUGGEST!</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">More</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
                                    <b><a href="#grader" class="text-dark stretched-link"
                                                                  data-toggle="collapse" role="button" aria-expanded="false" aria-controls="grader">
                                        <span class="custom-link"><i>${noteT.getScore()} scored! ${noteT.getThoughts().size()} thoughts received! Share yours here!</i></span></a>
                                    </b>
                                </p>
                                <div class="collapse multi-collapse" id="grader">
                                    <div class="row">
                                        <div class="col">
                                            <form action="${pageContext.request.contextPath}/updateNote" id="scoresForm" method="POST">
                                            <input type="hidden" name="email" id="email" value="${acc.getEmail()}">
                                            <input type="hidden" name="password" id="password" value="${acc.getPassword()}">
                                            <input type="hidden" name="note_no1" id="note_no1" value="${noteT.getNoteid()}">
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
                                                <div class="card bg-secondary text-warning text-center">
                                                    <div class="card-header">
                                                        <b>LEAVE A THOUGHT!</b>
                                                    </div>
                                                    <div class="card-body">
                                                        <p><textarea class="form-control custom-input mt-1" maxlength="500" rows="5" cols="100" name="thoughtText" id="thoughtText"></textarea></p>

                                                    </div>
                                                </div>
                                            <button class="btn btn-dark form-control" type="submit">SUBMIT</button>
                                            </form>
                                        </div>
                                        <div class="col">
                                            <div class="card bg-info text-dark text-center">
                                                <div class="card-header">
                                                    <b>THOUGHTS</b>
                                                </div>
                                                <div class="card-body">
                                                    <c:choose>
                                                        <c:when test="${noteT.getThoughts().size() > 0}">
                                                            <c:forEach var="thought1" items="${noteT.getThoughts()}">
                                                            <c:if test="${thought1.getInterpretation().length() > 0}">
                                                            <div class="card bg-dark text-warning">
                                                                <div class="card-header">
                                                                    <b>${thought1.getInterpreter()}</b>
                                                                </div>
                                                                <div class="card-body smaller-text">
                                                                    <p>${thought1.getInterpretation()}</p>
                                                                </div>
                                                            </div>
                                                            </c:if>
                                                            </c:forEach>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
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
