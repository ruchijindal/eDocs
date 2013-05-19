<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%-- 
    Document   : search_result
    Created on : 2 Sep, 2011, 2:59:10 PM
    Author     : smp
--%>


<%response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setDateHeader("Expires", 0);
%>

<%@page  language="java" import="java.util.*,java.sql.*,javax.servlet.*,javax.xml.parsers.DocumentBuilderFactory,javax.xml.parsers.DocumentBuilder,org.w3c.dom.*"  %>



<script type="text/javascript">
    function handleSelect(elm)
    {
        window.location = elm.value;
    }
</script>


<script type="text/javascript">
    var sortedOn = 0;

    function SortTable(sortOn) {

        var table = document.getElementById('results');
        var tbody = table.getElementsByTagName('tbody')[0];
        var rows = tbody.getElementsByTagName('tr');

        var rowArray = new Array();
        for (var i=0, length=rows.length; i<length; i++) {
            rowArray[i] = new Object;
            rowArray[i].oldIndex = i;
            rowArray[i].value = rows[i].getElementsByTagName('td')[sortOn].firstChild.nodeValue;
        }

        if (sortOn == sortedOn) { rowArray.reverse() }
        else {
            sortedOn = sortOn;
            /*
Decide which function to use from the three:RowCompareNumbers,
RowCompareDollars or RowCompare (default).
For first column, I needed numeric comparison.
             */
            if (sortedOn == 0) {
                rowArray.sort(RowCompare);
            }
            else {
                rowArray.sort(RowCompare);
            }
        }

        var newTbody = document.createElement('tbody');
        for (var i=0, length=rowArray.length ; i<length; i++) {
            newTbody.appendChild(rows[rowArray[i].oldIndex].cloneNode(true));
        }

        table.replaceChild(newTbody, tbody);
    }

    function RowCompare(a, b) {

        var aVal = a.value;
        var bVal = b.value;
        return (aVal == bVal ? 0 : (aVal > bVal ? 1 : -1));
    }
    // Compare number
    function RowCompareNumbers(a, b) {

        var aVal = parseInt( a.value);
        var bVal = parseInt(b.value);
        return (aVal - bVal);
    }
    // compare currency
    function RowCompareDollars(a, b) {

        var aVal = parseFloat(a.value.substr(1));
        var bVal = parseFloat(b.value.substr(1));
        return (aVal - bVal);
    }
</script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
    </head>
    <jsp:include page="../common/header.jsp"/>


    <body>

        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->


            <jsp:include page="../common/navbar.jsp"/>

            <div id="main-content"> <!-- Main Content Section with everything -->



                <div class="content-box"><!-- Start Content Box -->

                    <div class="content-box-header">




                        <h3>Search Results:-&nbsp;&nbsp;</h3><br/>


                        <h5></h5>

                        <div class="clear"></div>

                    </div> <!-- End .content-box-header -->

                    <div class="content-box-content">


                        <noscript> <!-- Show a notification if the user has disabled javascript -->
                            <div class="notification error1 png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>

						Javascript is disabled or is not supported by your browser. Please upgrade your browser or Enable Javascript in your browser.
                                </div>
                            </div>

                        </noscript>
                        <c:set var="t" value="<%= request.getAttribute("t")%>"/>
                        <c:choose>
                            <c:when test="${t=='1'}">
                                <% request.setAttribute("t", 0);%>
                                <div class="notification error1 png_bg">
                                    <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                    <div>
			          No Record Found
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                 <c:set var="masterRecordsSize" value="<%= request.getAttribute("masterRecordsSize")%>"/>
                                <div class="notification information png_bg">
                                    <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                    <div>
				 Records found: ${masterRecordsSize} &nbsp;&nbsp;&nbsp;&nbsp;
                                    </div>
                                </div>

                                <div>
                                    <c:set value="10" var="pageSize"/>

                                    <c:choose>
                                        <c:when test="${empty param.s}">
                                            <c:set var="rowStart" value="1"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="rowStart" value="${param.s}"/>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${empty param.e}">
                                            <c:set var="rowEnd" value="10"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="rowEnd" value="${param.e}"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:set var="masterRecords" value="<%= request.getAttribute("masterRecords") %>"/>
                                    <table id="results">

                                        <thead>

                                            <tr>
                                                <th> Scheme Code</th>
                                                <th> Property ID</th>
                                                <th> Property Code</th>
                                                <th> Property Number</th>
                                                <th>Allottee Name</th>
                                                <th> Father Name</th>
                                                <th> Scheme Name</th>
                                                <th>Allottee Date</th>
                                                <th>Final Cost</th>


                                                <th>Actions</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach begin="${rowStart-1}" step="1" end="${rowEnd-1}" var="master" varStatus="status" items="${masterRecords}">

                                            <tr>
                                                <td><a href="<%= request.getContextPath()%>/AllotteeDetails?propId=${master.propId}&propCode=${master.propCode}">${master.schemeCode}-${master.catCode}</a></td>



                                                <td>${master.propId} </td>
                                                <td> ${master.propCode}</td>
                                                <td>${master.propNumber} </td>
                                                <td>${master.alloteeName} </td>
                                                <td>${master.fatherName} </td>
                                                <td>${master.schemeName}</td>
                                                <td><fmt:formatDate pattern="dd/MMM/yyyy" value="${master.allotteeDate}" var="allotDate"/>${allotDate}</td>
                                                <td>${master.fianlCost} </td>

                                                <td><a href="<%= request.getContextPath()%>/AllotteeDetails?propId=${master.propId}&propCode=${master.propCode}">VIEW</a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>



                                    </table>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>

                <jsp:include page="../common/footer.jsp"/>
            </div>
        </div>


    </body>
</html>
