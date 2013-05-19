
<%response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setDateHeader("Expires", 0);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!    int changePassFlag = 3;

%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*,java.security.*;"%>

<html>
    <title>Change Password</title>
    <jsp:include page="../common/header.jsp"/>
    <script  type="text/javascript" src="../../jquery/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="../../jquery/jquery.validate.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#ch_pass').validate();

        });
    </script>



    <%


                String userrole = (String) session.getAttribute("userrole");

                request.getSession(false);

                try {
                    changePassFlag = ((Integer) request.getAttribute("changePassFlag")).intValue();
                } catch (Exception ex) {
                    System.out.println("Exception:" + ex);
                }



    %>
    <body>
        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->


            <jsp:include page="../common/navbar.jsp"/>


            <div id="main-content"> <!-- Main Content Section with everything -->


                <div class="content-box"><!-- Start Content Box -->

                    <div class="content-box-header">

                        <h3> Change Password </h3>

                        <div class="clear"></div>

                    </div> <!-- End .content-box-header -->

                    <div class="content-box-content">

                        <div class="tab-content default-tab" id="tab2"> <!-- This is the target div. id must match the href of this div's tab -->


                            <noscript> <!-- Show a notification if the user has disabled javascript -->
                                <div class="notification error1 png_bg">
                                    <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                    <div>

						Javascript is disabled or is not supported by your browser. Please upgrade your browser or Enable Javascript in your browser.
                                    </div>
                                </div>

                            </noscript>


                            <%                                         if (changePassFlag == 2) {
                                            changePassFlag = (Integer) 0;
                                            request.setAttribute("changePassFlag", changePassFlag);

                            %>
                            <div class="notification success png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>
				Password changed succesfully.
                                </div>
                            </div>

                            <%
                                                                     } else if (changePassFlag == 0) {
                                                                         changePassFlag = (Integer) 3;
                                                                         request.setAttribute("changePassFlag", changePassFlag);
                            %>

                            <div class="notification error1 png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>
			         Old Password does not match.
                                </div>
                            </div>


                            <%

                                                                     } else if (changePassFlag == 1) {
                                                                         changePassFlag = (Integer) 3;
                                                                         request.setAttribute("changePassFlag", changePassFlag);
                            %>

                            <div class="notification error1 png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>
			         New Password and Confirm Password does not match.
                                </div>
                            </div>


                            <%

                                                                     } else if (changePassFlag ==4) {
                                                                         changePassFlag = (Integer) 3;
                                                                         request.setAttribute("changePassFlag", changePassFlag);
                            %>

                            <div class="notification error1 png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>
			         Enter New Password
                                </div>
                            </div>


                            <%

                                                                     }
                            %>

                            <form id="ch_pass" method="post" action="<%= request.getContextPath()%>/ChangePassword">
                                <p>
                                    <label>Old Password:</label><br/>
                                    <input type="password" id="oldPassword" name="oldPassword" minlength="5" maxlength="15" class="required"/>
                                </p>

                                <p>
                                    <label>New Password:</label><br/>
                                    <input type="password" id="newPassword" name="newPassword" minlength="5" maxlength="15" class="required"/>
                                </p>

                                <p>
                                    <label>Confirm Password:</label><br/>
                                    <input  type="password" id="confirmPassword" name="confirmPassword" class="required" minlength="5" maxlength="15" equalTo="#newPassword"/>
                                </p>


                                <input type="submit" class="button" value="Submit" name="submit">
                                <a href="../DashBoard/dash_result.jsp"><input type="button" class="button" id="Cancel" value="CANCEL"/></a>

                            </form>


                        </div>

                    </div> <!-- End #main-content -->

                </div>
                <jsp:include page="../common/footer.jsp"/>
            </div>


        </div>
    </body>



</html>