<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,java.security.*;" %>
<%response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <title>Create User</title>
        <link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />

        <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
        <link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="resources/css/simpletree.css" type="text/css" media="screen" />

        <jsp:include page="../common/header.jsp"/>
        <script  type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.validate_1.js"></script>
    </HEAD>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#form").validate();
        });

        function clearUserId(){
            if(document.getElementById("userid").value=="")
            {
                document.getElementById("luserid").style.visibility="hidden";
            }
        }

        window.onload = function()
        {
            document.getElementById("userid").focus();
        }

        function checkUserID(){

            userid= document.getElementById("userid").value

            if(document.getElementById("userid").value!="")
            {
                xmlHttp=GetXmlHttpObject()
                if (xmlHttp==null)
                {
                    alert ("Browser does not support HTTP Request")
                    return
                }
                var url="<%=request.getContextPath()%>/checkuser";
                url=url+"?&userid="+userid;
                xmlHttp.onreadystatechange=stateChanged
                xmlHttp.open("GET",url,true)
                xmlHttp.send(null)
            }
            else if(document.getElementById("userid").value=="")
            {
                document.getElementById("luserid").style.visibility="hidden";
            }

        }


        function stateChanged()
        {
            if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
            {
                var showdata = xmlHttp.responseText;
                var showdata = parseInt(xmlHttp.responseText,10);

                if(showdata==1)
                {
                    document.getElementById("luserid").style.visibility="visible";
                    document.getElementById("userid").focus();
                    showdata=0;

                }
                else
                {
                    document.getElementById("luserid").style.visibility="hidden";
                }

            }
        }

        function GetXmlHttpObject()
        {
            var xmlHttp=null;
            try
            {
                // Firefox, Opera 8.0+, Safari
                xmlHttp=new XMLHttpRequest();
            }
            catch (e)
            {
                //Internet Explorer
                try
                {
                    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }
                catch (e)
                {
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
            return xmlHttp;
        }

    </script>

    <BODY>



        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
            <jsp:include page="../common/navbar.jsp"/>
            <div id="main-content"> <!-- Main Content Section with everything -->
                <a name="bottom"></a>


                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>    Create New User</h3>
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
                            <c:set var="t1" value="<%= request.getAttribute("t1")%>"/>
                            <c:choose>
                                <c:when test="${t1=='1'}">
                                    <%session.setAttribute("t1", 0);%>

                                    <div class="notification success png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
					Data Inserted Successfuly.
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${t1=='2'}">
                                    <% session.setAttribute("t1", 0);%>
                                    <div class="notification error1 png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
					 Error ! tryagain.
                                        </div>
                                    </div>

                                </c:when>
                            </c:choose>
                            <c:set var="rowStart" value="1"></c:set>
                            <c:set var="rowEnd" value="${userrolelistsize}"></c:set>
                            <form id="form" method="post" action="<%= request.getContextPath()%>/CreateUser">

                                <p> <label class="form-label">User Name</label>
                                    <input type="text" id="name" name="name" class="required"/></p>
                                <p><label class="form-label">User Id</label>
                                    <input type="text"  id="userid" name="userid"  class="required" onblur="checkUserID()" onkeyup="clearUserID()"/>
                                    <label id="luserid" class="error" style="visibility:hidden" >User id already exists.</label></p>

                                <p> <label class="form-label">Password</label>
                                    <input type="password" id="password" name="password" minlength="6"  maxlength="15" class="required"/> </p>

                                <p> <label class="form-label">Confirm Password</label>
                                    <input type="password" id="conpassword" name="conpassword" minlength="6"  maxlength="15" class="required" equalTo="#password" /></p>





                                <p> <label class="form-label">User Role:</label>
                                    <select name="userrole" id="userrole" >
                                        <option value="Select Role">Select Role</option>
                                       
                                        <c:forEach begin="${rowStart-1}" step="1" end="${rowEnd}" var="master" varStatus="status" items="${userrolelist}">

                                            <option value="${master.id}">${master.userrole}</option>

                                        </c:forEach>
                                       
                                    </select>
                                </p>


                                <input class="button" type="submit" name="submit" value="submit"  >

                                <input type="reset" class="button" value="Reset" name="reset">
                            </form>
                        </div>
                    </div>
                </div>
                <jsp:include page="../common/footer.jsp"/>
            </div>
        </div>
    </BODY>
</HTML>