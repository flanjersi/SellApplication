<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Amuzon</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Custom styles for this template -->
    <link href="/css/shop-item.css" rel="stylesheet" />

    <link href="/css/animate.css" rel="stylesheet" />
    <link href="/css/font-awesome.min.css" rel="stylesheet" />

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Amuzon</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a class="nav-link" href="#">Home
                    <span class="sr-only">(current)</span>
                </a></li>
                <li class="nav-item"><button class="btn btn-danger" onclick="quit()">Quit</button></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">
            <h1 class="my-4">Amuzon</h1>
            <div class="list-group">
                <a href="/" class="list-group-item">Home</a>
            </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div id="productinfo" class="card mt-4">
                <img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt=""/>
                <div class="card-body">
                    <h3 class="card-title">${product.title}</h3>
                    <h4>${product.price}&euro;</h4>
                    <p class="card-text">${product.description}</p>
                    <span class="text-warning">
                        <c:forEach begin="1" end="${rating}">
                            &#9733;
                        </c:forEach>
                        <c:forEach begin="${rating + 1}" end="5">
                            &#9734;
                        </c:forEach>
                    </span>
                    <span><fmt:formatNumber value="${rating}" maxFractionDigits="1"/></span>
                    <div>
                        <small class="card-text text-muted">${product.date}, ${product.category}</small>
                    </div>
                </div>
            </div>
            <!-- /.card -->

            <div class="card card-outline-secondary my-4">
                <div class="card-header">Product Reviews</div>
                <div class="card-body" id="comments">
                    <c:forEach items="${comments}" var="comment" varStatus="status">
                        <small class="text-warning">
                            <c:forEach var="num" begin="1" end="${comment.rating}">
                                &#9733;
                            </c:forEach>
                            <c:forEach var="num" begin="${comment.rating + 1}" end="5">
                                &#9734;
                            </c:forEach>
                        </small>
                        <span>${comment.rating}</span>
                        <p>${comment.comment}</p>
                        <small class="text-muted">
                            Posted by Anonymous on ${comment.date}
                        </small>

                        <div class="row">
                            <div class="col-sm-3">
                                <form action="${pageContext.request.contextPath}/product/removecomment" method="POST">
                                    <input type="hidden" style="display: none" value="${comment.id}" name="commentId" />
                                    <input type="hidden" style="display: none" value="${product.id}" name="productId" />
                                    <button name="buttonRemove" type="submit" class="btn btn-danger btn-xs">
                                        <i class="fa fa-trash fa-lg" aria-hidden="true"></i>
                                    </button>
                                </form>
                            </div>
                            <div class="col-sm-3 offset-col-sm-3">
                                <form action="${pageContext.request.contextPath}/product/incrementUsefulVote" method="POST">
                                    <input type="hidden" style="display: none" value="${product.id}" name="productId" />
                                    <input type="hidden" style="display: none" value="${comment.id}" name="commentId" />

                                    <button name="buttonAddVotePositive" type="submit" class="btn">
                                        <i class="fa fa-thumbs-up"></i> ${comment.usefulVote}
                                    </button>
                                </form>
                            </div>
                            <div class="col-sm-3 offset-col-sm-3">
                                <form action="${pageContext.request.contextPath}/product/incrementNotUsefulVote" method="POST">
                                    <input type="hidden" style="display: none" value="${comment.id}" name="commentId" />
                                    <input type="hidden" style="display: none" value="${product.id}" name="productId" />

                                    <button name="buttonAddVoteNotPositive" type="submit" class="btn">
                                        <i class="fa fa-thumbs-down"></i> ${comment.notUsefulVote}
                                    </button>
                                </form>
                            </div>
                        </div>

                        <hr>
                    </c:forEach>

                    <button id="btnLeaveReview" type="button" class="mt-2 btn btn-success"
                            data-toggle="modal" data-target="#leaveReviewModal">
                        Leave Review
                    </button>

                </div>
                <div class="card-footer">

                </div>
            </div>
            <!-- /.card -->

            <div id="leaveReviewModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Leave a Review</h5>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <form id="addreviewform" action="${pageContext.request.contextPath}/product/addcomment" method="POST">
                                <input type="hidden" style="display: none" value="${product.id}" name="productId" />
                                <p>
                                    Rating :
                                    <input type="radio" id="rating-1" name="rating" value="1"/>
                                    <input type="radio" id="rating-2" name="rating" value="2"/>
                                    <input type="radio" id="rating-3" name="rating" value="3"/>
                                    <input type="radio" id="rating-4" name="rating" value="4"/>
                                    <input type="radio" id="rating-5" name="rating" value="5"/>
                                    <input type="hidden" id="rating-0" name="rating" value="0"/>
                                </p>
                                <p>
                                    <label for="comment">Write a comment:</label>
                                    <input id="comment" class="form-control" type="text" name="comment" />
                                </p>
                            </form>
                        </div>

                        <div class="modal-footer">
                            <!--  bouton avec un javascript pour submit le formulaire à distance -->
                            <button type="button" class="btn btn-primary"
                                    data-dismiss="modal"
                                    onclick="document.getElementById('addreviewform').submit(); return false;">Add</button>
                        </div>
                    </div>

                </div>
            </div>

        </div>
        <!-- /.col-lg-9 -->

    </div>

</div>
<!-- /.container -->


<!-- Bootstrap core JavaScript -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>

</body>

</html>
