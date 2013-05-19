<%-- 
    Document   : search
    Created on : 29 Aug, 2011, 4:38:22 PM
    Author     : smp
--%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=IE6">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Search</title>
        <jsp:include page="../common/header.jsp"/>
        <script  type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.validate_1.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.maskedinput-1.2.2.js"></script>
        <link href="<%= request.getContextPath()%>/resources/css/calendar.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/scripts/calendarControl.js" language="javascript"></script>


        <script type="text/javascript" >
            $(document).ready(function(){
                $("#rDate").mask("99/99/99");
                $("#pDate").mask("99/99/99");
                $("#aDate").mask("99/99/99");
                $("#rDate1").mask("99/99/99");
                $("#pDate1").mask("99/99/99");
                $("#aDate1").mask("99/99/99");

                $("#form2").validate();          

            });


            function checklength()
            {
                var data=document.form22.sCode.value.length+1;
                if(data<=3)
                {
                    document.form22.sCode.focus();
                }
                else{
                    document.form22.cCode.focus();
                }
            }

            function checkNullFields1()
            {
            
                           
                var aDate=document.getElementById("aDate").value;
                var aDate1=document.getElementById("aDate1").value;
                var rDate=document.getElementById("rDate").value;
                var rDate1=document.getElementById("rDate1").value;
                var pDate=document.getElementById("pDate").value;
                var pDate1=document.getElementById("pDate1").value;
                if ((aDate == "" ) && (aDate1 == "") && (rDate == "" ) && (rDate1 == "" ) && (pDate =="" ) && (pDate1 == "" ))
                {
                    document.getElementById("divsion1").removeAttribute("style");
                    document.getElementById("divsion2").style.display='none';
                    return false;
                }else if (((aDate =="") || (aDate1 == "")) && ((rDate == "") || (rDate1 == "")) && ((pDate == "") || (pDate1 == ""))) {
                    document.getElementById("divsion1").style.display='none';
                    document.getElementById("divsion2").removeAttribute("style");                  
                    return false;
                }else{
                    return true;
                }
            
          
            }

            function checkNullFields()
            {
                var sCode=document.getElementById("sCode").value;
                var cCode=document.getElementById("cCode").value;
                var schemeName= document.getElementById("schemeName").value;
                var pCode=document.getElementById("pCode").value;
                var propertyNo=document.getElementById("propertyNo").value;
                var propertyId=document.getElementById("propertyId").value;
                var alloteeName=document.getElementById("alloteeName").value;

                if ((sCode == "" ) && (cCode == "")&& (schemeName == "" ) && (pCode == "" ) && (propertyNo == "") && (propertyId == "" )  && (alloteeName == "" ))
                {
                    document.getElementById("div1").removeAttribute("style");
                    document.getElementById("div2").style.display='none';                  
                    return false;
                }
                else if ((sCode =="" || cCode == "")&& (schemeName == "" ) && (pCode == "" ) && (propertyNo == "") && (propertyId == "" )  && (alloteeName == "" )) {
                    document.getElementById("div2").removeAttribute("style");
                    document.getElementById("div1").style.display='none';                    
                    return false;
                }
                else{
                    return true;
                }
            }

            function populateCategory()
            {
                
                xmlHttp=GetXmlHttpObject()
                if (xmlHttp==null)
                {
                    alert ("Browser does not support HTTP Request")
                    return;
                }

                var schemeCode=document.getElementById("sCode").value;                
                var url="<%= request.getContextPath()%>/GetCategory?schemeCode="+schemeCode;
                xmlHttp.onreadystatechange=stateChanged
                xmlHttp.open("GET",url,true)
                xmlHttp.send(null)
            }
            function stateChanged()
            {
               
                if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                {
                    var catList=xmlHttp.responseText;
                    var catArray=catList.split(":");
                    document.getElementById("cCode").length=0;
                    document.getElementById("cCode").options[0]=new Option("Select","");
                    for(i=0;i<catArray.length;i++)
                    {
                        document.getElementById("cCode").options[i+1]=new Option(catArray[i].trim(),catArray[i].trim());
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
    </head>
    <body>
        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
            <jsp:include page="../common/navbar.jsp"/>
            <div id="main-content"> <!-- Main Content Section with everything -->
                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>Search</h3>
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
                        <c:set var="flag" value="<%=request.getAttribute("flag")%>"/>
                        <c:if test="${flag=='1'}">                           
                            <%request.setAttribute("flag", "0");%>
                            <div class="notification error1 png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>
			          No Record Found
                                </div>
                            </div>
                        </c:if>


                        <c:set var="delete" value="<%=request.getAttribute("delete")%>"/>
                        <c:if test="${delete=='1'}">
                            <%request.setAttribute("delete", "0");%>
                            <div class="notification success png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>
			          Allottee successfully deleted!
                                </div>
                            </div>
                        </c:if>

                        <div id="div1" class="notification error1 png_bg" style="display: none">
                            <a href="#" class="close"><img src="<%=request.getContextPath()%>/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                            <div>
			          Please Select Alteast One value.
                            </div>
                        </div>



                        <div id="div2" class="notification error1 png_bg" style="display: none">
                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                            <div>
			          Please Select Correct Scheme Code.
                            </div>
                        </div>


                        <div class="tab-content default-tab" >
                            <c:set var="schemeCode" value="<%=request.getAttribute("schemeCode")%>"/>
                            <c:set var="schemeCodeLength" value="<%=request.getAttribute("schemeCodeLength")%>"/>                            
                            <c:set var="schemeName" value="<%=request.getAttribute("schemeName")%>"/>
                            <c:set var="schemeNameLength" value="<%=request.getAttribute("schemeNameLength")%>"/>
                            <c:set var="catCode" value="<%=session.getAttribute("catCodeList")%>"/>
                            <c:set var="catCodeLength" value="<%=session.getAttribute("catCodeListLength")%>"/>


                            <table style="color: #000;">


                                <form  name="form1" id="form1" method="post" action="<%= request.getContextPath()%>/SelectRecord" onsubmit=" return checkNullFields()" >

                                    <tr>
                                        <td colspan="4"> <h4>Search By Property</h4></td>
                                    </tr>

                                    <tr>
                                        <td><label class="form-label">Scheme Code</label></td>
                                        <td>
                                            <select id="sCode" name="sCode" style="width: 70px;" onchange="populateCategory()"  >
                                                <option value="">Select</option>
                                                <c:forEach var="schemeCodeValue" begin="0" end="${schemeCodeLength-1}" step="1" items="${schemeCode}">
                                                    <option value="${schemeCodeValue}">${schemeCodeValue}</option>
                                                </c:forEach>
                                            </select> -                                           

                                            <select id="cCode" name="cCode" style="width: 70px;">
                                                <option value="">Select</option>
                                            </select>


                                        </td>

                                        <td><label class="form-label">Scheme Name</label></td>
                                        <td>
                                            <select id="schemeName" name="schemeName">
                                                <option value="">Select</option>
                                                <c:forEach var="schemeNameValue" begin="0" end="${schemeNameLength-1}" step="1" items="${schemeName}">
                                                    <option value="${schemeNameValue}">${schemeNameValue}</option>
                                                </c:forEach>
                                            </select>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <label class="form-label">Property Code</label></td>
                                        <td>
                                            <input  type="text" name="pCode" id="pCode" />
                                        </td>
                                        <td>

                                            <label class="form-label">Property No.</label></td>
                                        <td>
                                            <input  type="text" name="propertyNo" id="propertyNo" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="form-label">Property ID</label></td>
                                        <td>
                                            <input  type="text" name="propertyId"  id="propertyId" minlength="4" maxlength="9"   />
                                        </td>
                                        <td>

                                            <label class="form-label">Allotee Name</label></td>
                                        <td>
                                            <input  type="text" name="alloteeName" id="alloteeName"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <p>
                                                <input class="button" type="submit" value="Search" />
                                                <input type="reset" class="button" value="Reset" name="reset">
                                            </p>
                                        </td>
                                    </tr>
                                </form>



                            </table>
                        </div>

                    </div>


                </div>

                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>Search By Date</h3>
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
                        <c:set var="tempFlag" value="<%=request.getAttribute("tempFlag")%>"/>
                        <c:if test="${tempFlag=='1'}">
                            <%request.setAttribute("tempFlag", "0");%>
                            <div class="notification error1 png_bg">
                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                <div>
			          No Record Found
                                </div>
                            </div>
                        </c:if>

                        <div id="divsion1" class="notification error1 png_bg" style="display: none">
                            <a href="#" class="close"><img src="<%=request.getContextPath()%>/resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                            <div>
			          Please Select Alteast One value.
                            </div>
                        </div>

                        <div id="divsion2" class="notification error1 png_bg" style="display: none">
                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                            <div>
			          Please Enter Correct Date Range.
                            </div>
                        </div>


                        <div class="tab-content default-tab" >
                            <table style="color: #000;">
                                <form  name="form2" id="form2" method="post" action="<%= request.getContextPath()%>/searchRecord" onsubmit="return checkNullFields1()" >

                                    <tr> <td style="width: 160px;"><label class="form-label">Allotment Date</label></td>
                                        <td><input  type="text" name="aDate" id="aDate" style="width: 72px;"  onfocus="showCalendarControl(this)"/> - <input  type="text" name="aDate1" id="aDate1" style="width: 72px;" onfocus="showCalendarControl(this)" /></td>
                                    </tr>
                                    <tr>
                                        <td><label class="form-label">Registry Date</label></td>
                                        <td><input  type="text" name="rDate" id="rDate" style="width: 72px;" onfocus="showCalendarControl(this)" /> - <input  type="text" name="rDate1" id="rDate1" style="width: 72px;" onfocus="showCalendarControl(this)" /></td>
                                    </tr>
                                    <tr><td><label class="form-label">Possession Date</label></td>
                                        <td><input  type="text" name="pDate" id="pDate" style="width: 72px;"  onfocus="showCalendarControl(this)"/> - <input  type="text" name="pDate1" id="pDate1" style="width: 72px;"  onfocus="showCalendarControl(this)"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <p>
                                                <input class="button" type="submit" value="Search" />
                                                <input type="reset" class="button" value="Reset" name="reset">
                                            </p>
                                        </td>
                                    </tr>

                                </form>
                            </table>
                        </div>
                    </div>
                </div>
                <jsp:include page="../common/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
