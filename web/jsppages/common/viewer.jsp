<%-- 
    Document   : viewer
    Created on : 2 Nov, 2011, 12:54:42 PM
    Author     : smp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Document Viewer</title>
        <script
src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"
type="text/javascript"></script>

<script src="<%= request.getContextPath()%>/js/jquery.easing.js" type="text/javascript"></script>
<script src="<%= request.getContextPath()%>/js/jqueryFileTree.js" type="text/javascript"></script>
 <jsp:include page="../common/header.jsp"/>
<link href="<%= request.getContextPath()%>/resources/css/calendar.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="<%= request.getContextPath()%>/flashviewer/flexpaper/js/swfobject/swfobject.js"></script>
 <script type="text/javascript">
    window.onload = function()
  {
      
 var params = {
      SwfFile : "/SMPDocMan/swf/730-5027.swf",
	  Scale : .47
    }
swfobject.embedSWF("/SMPDocMan/flashviewer/flexpaper/FlexPaperViewer.swf","flashcontent","100%","100%","9.0.0","/SMPDocMan/flashviewer/flexpaper/js/swfobject/expressInstall.swf",param);
window.frames[0].document.body.style.overflow='hidden';



  </script>

    </head>
    <body>
         <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
            <jsp:include page="../common/navbar.jsp"/>
             <div id="main-content"> <!-- Main Content Section with everything -->
                <div class="content-box "><!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>PDF Preview</h3>
                        <div class="clear"></div>
                    </div> <!-- End .content-box-header -->
        
        <%

         String s=(String)session.getAttribute("pdfname");

    String source=application.getRealPath("/");
    String[] split=source.split("SMPDocMan");
    source=split[0]+"SMPDocMan/web/swf/";
   //  System.out.println("I viewer file name is eqauaald ddddddddddd "+request.getContextPath()+"web/swf/");


                   
        %>
      
        
        <iframe id="content_frame" name="content_frame" width="100%" height="600" src="../../flashviewer/flexpaper/templat.jsp"  >

    
        </iframe>
        </div>
         <jsp:include page="../common/footer.jsp"/>
         </div>
             </div>

    </body>
</html>
