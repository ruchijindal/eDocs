<%--
    Document   : allotteeDetail
    Created on : 3 Sep, 2011, 10:57:58 AM
    Author     : smp
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <!--                       CSS                       -->

    <!-- Reset Stylesheet -->
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/reset.css" type="text/css" media="screen" />

    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/style.css" type="text/css" media="screen" />

    <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/invalid.css" type="text/css" media="screen" />

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/css/simpletree.css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Merge Files</title>
        <script  type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.validate_1.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.maskedinput-1.2.2.js"></script>
        <script type="text/javascript">
            function getlink()
            {


                var newlink=document.getElementById('file');
                newlink=newlink.value;
                alert(newlink);
            }


        </script>
    </head>
    <body>


        <div id="body-wrapper" style="width:400px;height:250px; margin-top: -50px;margin-left :-30px; "> <!-- Wrapper for the radial gradient background -->


            <div id="main-content"> <!-- Main Content Section with everything -->


                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>Merge PDF</h3><a href="#bottom"><img  alt="bottom" src="<%=request.getContextPath()%>/resources/images/icons/top.png"/></a>
                    </div>

                    <div class="content-box-content">


                        <noscript> <!-- Show a notification if the user has disabled javascript -->
                            <div class="notification error1 png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>

						Javascript is disabled or is not supported by your browser. Please upgrade your browser or Enable Javascript in your browser.
                                </div>
                            </div>

                        </noscript>

                        <%
                                    String filename = request.getParameter("filename");
                                    System.out.println("********** " + filename);
                        %>
                        <form method="post"  enctype="multipart/form-data" action="<%= request.getContextPath()%>/MergePDF?filename=<%=filename%>">

                            <table id="results">
                                <thead>  <tr><th> File</th></tr></thead>
                                <tbody>
                                    <tr>
                                        <td>  <input type="file" name="uploadfile" size="20" id="file"><br/><br/>
                                            <input class="button" type="submit" value="Upload File" onclick="getlink();"/></td>

                                        <td>
                                </tbody>

                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
