<%-- 
    Document   : templat
    Created on : 12 Nov, 2011, 1:04:52 PM
    Author     : smp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Example of FlexPaper</title>
        <script type="text/javascript" src="../../flashviewer/flexpaper/js/swfobject/swfobject.js"></script>
        <script type="text/javascript">
            window.onload = function()
            {
            <%
                        System.out.println(request.getLocalPort() + "Context path is " + request.getLocalAddr());
            %>
                    var s= '<%=session.getAttribute("pdfname")%>';
                    var port='<%= request.getLocalPort()%>';
                    var url='<%= request.getLocalAddr()%>';
                    var compURL="http://"+url+":"+port+"/"+s;
                    alert(compURL);
                    // window.open('http://'+url+':'+port+'/'+s);
  
                  flashvars={
                      Name: "hello"
                  }
                    

                    var params = {
                        SwfFile : compURL,
                        Scale : 0.9,
                        menu: true,
                        allowFullScreen: true

                    };


                    swfobject.embedSWF("../../flashviewer/flexpaper/FlexPaperViewer.swf","flashcontent","100%","100%","9.0.0","../../flashviewer/flexpaper/js/swfobject/expressInstall.swf", params,flashvars);
                    parent.frames[0].document.body.style.overflow='hidden';
                }
        </script>
        <style type="text/css">

            /* hide from ie on mac \*/
            html {
                height: 100%;
                overflow: hidden;
            }

            #flashcontent {
                height: 100%;

            }
            /* end hide */

            body {
                height: 100%;
                margin: 0;
                padding: 0;
                background-color: #000000;
            }

        </style>

    </head>
    <body>
        <!--  <form>
          &nbsp;

          </form>-->

        <div id="flashcontent">
        </div>


    </body>
</html>

