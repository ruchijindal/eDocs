<%-- 
    Document   : userInfo
    Created on : 7 Sep, 2011, 2:37:21 PM
    Author     : smp
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,java.security.*;" %>
<%response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> User Statistics</title>
        <link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />

        <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
        <link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="resources/css/simpletree.css" type="text/css" media="screen" />

        <jsp:include page="../common/header.jsp"/>
    </head>
    <body>
        <c:set var="accessRight" value="<%= session.getAttribute("accessRight")%>" />
        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
        <jsp:include page="../common/navbar.jsp"/>
        <div id="main-content"> <!-- Main Content Section with everything -->
            <a name="bottom"></a>


            <div class="content-box"><!-- Start Content Box -->
                <div class="content-box-header">
                    <h3>  User Statistics</h3>
                    <div class="clear"></div>

                </div>


                <div class="content-box-content">
                    <div class="tab-content default-tab" id="tab2">
                        <noscript> <!-- Show a notification if the user has disabled javascript -->
                            <div class="notification error1 png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>

						Javascript is disabled or is not supported by your browser. Please upgrade your browser or Enable Javascript in your browser.
                                </div>
                            </div>

                        </noscript>
                        <c:set var="rowStart" value="1"></c:set>
                        <c:set var="rowEnd" value="${currentUsersize}"></c:set>

                        <form id="form" method="post" action="#">
                            <table  >
                                <thead>
                                <tr>
                                    <th>User Name</th>
                                    <th>Active Locations</th>
                                    <th>Recent Activity</th>

                                </tr>
                                </thead>
                                <c:choose>
                                <c:when test="${accessRight=='mn'||accessRight=='rw'}">
                                <tbody>
                                    <c:forEach begin="${rowStart-1}" step="1" end="${rowEnd}" var="master" varStatus="status" items="${userlist}">
                                <tr>
                                    <td>  ${master.username}</td>
                                    <td> ${master.modified} </td>
                                    <td> <a href="<%= request.getContextPath()  %>/jsppages/user/userActivity.jsp?id=${master.userid}" class="button">View Activity</a></td>
                                </tr>
                                    </c:forEach>
                                </tbody>
                                </c:when>
                                <c:otherwise>
                                     <tbody>
                                <tr>
                                    <td>  ${userName}</td>
                                    <td> ${modified} </td>
                                    <td> <a href="<%= request.getContextPath()  %>/jsppages/user/userActivity.jsp?id=${userid}" class="button">View Activity</a></td>
                                </tr>

                                </tbody>
                                </c:otherwise>
                                </c:choose>



                            </table>
                        </form>
                    </div>                                   
                </div>
            </div>
        </div>
        </div>
    </body>
</html>
