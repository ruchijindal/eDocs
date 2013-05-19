<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- 
    Document   : useActivity
    Created on : 12 Sep, 2011, 5:13:01 PM
    Author     : smp
--%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.Node"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Handler"%>
<%@page import="java.util.logging.FileHandler"%>


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
        <title> User Activity</title>
        <link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />

        <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
        <link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="resources/css/simpletree.css" type="text/css" media="screen" />
        <script type="text/javascript">
            function handleSelect(elm)
            {
                window.location = elm.value;
            }
        </script>
        <jsp:include page="../common/header.jsp"/>
    </head>
    <body>
        <%
                    String id = request.getParameter("id");
        %>
        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
            <jsp:include page="../common/navbar.jsp"/>
            <div id="main-content"> <!-- Main Content Section with everything -->
                <a name="bottom"></a>


                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3> User Activity Details</h3>  <a href="<%= request.getContextPath()%>/logPdf?id=<%=id%>" class="button rightButton">Export Log</a>
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
                            <%!                                int pagesize = 10;
                                int rowstart = 1;
                                int rowend = 10;
                                int totalrecords;
                                int temp1;
                                int temp2;
                                String id;
                            %>
                            <%
                                        id = request.getParameter("id");
                                        request.setAttribute("pagesize", pagesize);
                            %>
                           

                            <table  >
                                <thead>
                                    <tr>
                                        <th>Date & Time</th>
                                        <th>Activity</th>


                                    </tr>
                                </thead>

                                <tbody>

                                    <%
                                                try {

                                                    String rowst = request.getParameter("s");
                                                    if (rowst == null || rowst.equals("null")) {
                                                        rowstart = 1;
                                                    } else {
                                                        rowstart = Integer.parseInt(rowst);

                                                    }
                                                    String rowen = request.getParameter("e");
                                                    if (rowen == null || rowen.equals("null")) {
                                                        rowend = 1;
                                                    } else {
                                                        rowend = Integer.parseInt(rowen);


                                                    }
                                                    if (rowstart <= 0 || rowend == 1) {
                                                        rowstart = 1;
                                                    }
                                                    if (rowend <= 0 || rowstart == 1) {
                                                        rowend = 1;
                                                    }


                                                    String date = "";
                                                    String message = "";

                                                    DateFormat dateFormat = DateFormat.getDateTimeInstance();



                                                    String log_path = (String) session.getAttribute("logPath");
                                                    System.out.println("log path is ->> " + log_path);
                                                    log_path += id.toString().trim() + ".xml";
                                                    File f = new File(session.getAttribute("logPath").toString() + id.toString().trim() + ".xml");
                                                    System.out.println("total ");
                                                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                                                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                                                    org.w3c.dom.Document docxml = docBuilder.parse(f);
                                                    docxml.getDocumentElement().normalize();
                                                    NodeList logList = docxml.getElementsByTagName("log");
                                                    Node logNode = logList.item(0);
                                                    if (logNode.getNodeType() == Node.ELEMENT_NODE) {
                                                        Element logElement = (Element) logNode;
                                                        NodeList recordList = logElement.getElementsByTagName("record");
                                                        if (recordList.getLength() > 0) {
                                                            totalrecords = recordList.getLength();
                                                            System.out.println("total records are " + totalrecords);
                                                            temp1 = rowstart;
                                                            temp2 = rowend + pagesize;
                                                            rowstart = totalrecords - rowstart;
                                                            rowend = totalrecords - rowend - pagesize;
                                                            System.out.println("row start " + rowstart + " rowend " + rowend);
                                                            if (rowend < 0) {
                                                                rowend = 0;
                                                            }
                                                            for (int j = rowstart; j >= rowend; j--) {

                                                                Node recordNode = recordList.item(j);
                                                                if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
                                                                    Element recordElement = (Element) recordNode;
                                                                    date = recordElement.getAttribute("date");%>
                                    <tr>
                                        <th style="font-weight: normal" ><%= date%> </th>
                                        <%
                                                                                                            NodeList messageList = recordElement.getElementsByTagName("message");
                                                                                                            for (int k = 0; k < messageList.getLength(); k++) {
                                                                                                                Node messageNode = messageList.item(k);
                                                                                                                if (messageNode.getNodeType() == Node.ELEMENT_NODE) {
                                                                                                                    Element messageElement = (Element) messageNode;
                                                                                                                    message = messageElement.getTextContent();%>
                                        <th style="font-weight: normal" ><%= message%></th><%
                                                                            }

                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }


                                        %>
                                </tbody>
                                <tr><td><div class="align-left">

                                            <select name="page" onchange="javascript:handleSelect(this)">
                                                <option value="">Select Page No.</option>
                                                <c:forEach var="pStart" varStatus="status" begin="1" end="<%= totalrecords%>" step="<%= pagesize%>">
                                                    <c:url value="/jsppages/user/userActivity.jsp?id=${param.id}&s=${pStart+1}&e=${pStart+param.pagesize}" var="urlCurr"/>
                                                    <option value=" ${urlCurr}" >Page No ${status.count} </option>
                                                </c:forEach>
                                            </select>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <%
                                                        int pagecount = totalrecords / pagesize;

                                                        if (totalrecords % pagesize > 0) {
                                                                pagecount += 1;
                                                            }
                                                            if (temp1 > 1) {%>
                                            <c:url value="/jsppages/user/userActivity.jsp?id=${param.id}&s=${param.s-pagesize}&e=${param.e-pagesize}" var="urlPrev"/>
                                            <a href="${urlPrev}">&laquo; Prev</a>
                                            <% }

                                                        int ii = 0;
                                                        int p = pagecount - rowstart / pagesize;

                                                        if (p == 0) {
                                                            p = 1;
                                                        }
                                                        System.out.println("p is " + p);
                                                        for (; p <= pagecount; p++) {

                                                            System.out.println("1 value " + ((p - 1) * pagesize + 1) + " temp1 " + temp1);
                                                            System.out.println("row start "+rowstart+ " rowend "+rowend);
                                                            if( (((p - 1) * pagesize + 2) == temp1)||(p==1) ){
                                                                System.out.println("if value " + ((p - 1) * pagesize + 1) + " temp1 " + temp1);
                                            %>
                                            <a href="<%= request.getContextPath()%>/jsppages/user/userActivity.jsp?id=${param.id}&s=<%= ((p - 1) * pagesize + 2)%>&e=<%= (p - 1) * pagesize + 1%>" /> <b style="color: black" ><%= p%></b>
                                            <%
                                                                                                    } else {
                                                                  System.out.println("2 value " + ((p - 1) * pagesize + 1) + " temp1 " + temp1);
                    %>

                                            <a href="<%= request.getContextPath()%>/jsppages/user/userActivity.jsp?id=${param.id}&s=<%= ((p - 1) * pagesize + 2)%>&e=<%= (p - 1) * pagesize + 1%>" /> <%= p%>
                                            <% }
                                                            if (ii++ >= 4) {
                                                                break;
                                                            }
                                                        }
                                                        if (temp2 < pagecount * pagesize) {
                                            %>
                                            <c:url value="/jsppages/user/userActivity.jsp?id=${param.id}&s=${param.s+pagesize}&e=${param.e+pagesize}" var="urlNext"/>
                                            <a href="${urlNext}">Next &raquo;</a>
                                            <%}%>


                                        </div></td><td><div class="pagination"> Displaying Records:&nbsp; <%= temp1%>&nbsp;-&nbsp;<%= temp2%></div></td>





                                </tr>


                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
