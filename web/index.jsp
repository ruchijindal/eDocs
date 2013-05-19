

<%!    int validationFlag = 0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,java.security.*;" %>
<%response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<HTML>
    <HEAD>
        <TITLE>Login Page </TITLE>

        <!-- Reset Stylesheet -->
        <link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />

        <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
        <link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />

        <script type="text/javascript">


            window.onload = function()
            {
                document.getElementById("userName").focus();
            }


        </script>

    </HEAD>
    <BODY id="login">

        <div id="login-wrapper" class="png_bg">
            <div id="login-top">
                <img  alt="DMS-JAL"  src="resources/images/logo.png"/>
            </div> <!-- End #logn-top -->

            <div id="login-content">
                <%
                            try {
                                validationFlag = ((Integer) request.getAttribute("validationFlag")).intValue();
                            } catch (Exception ex) {
                                System.out.println("Exception:" + ex);
                            }


                %>
                <form id="form1" method = "post" action="Login" >


                    <%
                                if (validationFlag == 1) {
                                    validationFlag = (Integer) 0;
                                    request.setAttribute("validationFlag", validationFlag);
                    %>
                    <div class="notification information png_bg">
                        <div>
							Enter valid userid and password.
                        </div>
                    </div
                    <%
                                    } else {
                    %>

                    <div class="notification information png_bg">
                        <div>
							Enter userid and password.
                        </div>
                    </div
                    <%                           }
                    %>


                    <p>
                        <label>User ID</label>
                        <input class="text-input" type = "text" name = "userName" id="userName">
                    </p>
                    <div class="clear"></div>
                    <p>
                        <label> Password</label>
                        <input class="text-input" type="password" name = "password" id="password" >
                    </p>
                    <div class="clear"></div>
                    <p><input  class="button" type = "submit"  value = "Sign In" ></p>

                </form>
            </div>
        </div>



    </BODY>
</HTML>