<%-- 
    Document   : addAllottee
    Created on : Aug 30, 2011, 2:44:46 PM
    Author     : smp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Allottee</title>
        <jsp:include page="../common/header.jsp"/>
        <script  type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.validate_1.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.maskedinput-1.2.2.js"></script>
        <script type="text/javascript" >
            $(document).ready(function(){
                $("#rDate").mask("99/99/99");
                $("#pDate").mask("99/99/99");
                $("#aDate").mask("99/99/99");
                $("#form2").validate();
                $("#schemeform").validate();

            });
            function checklength()
            {
                var data=document.form22.sCode.value.length+1;
                if(data<3)
                {
                    document.form22.sCode.focus();
                }
                else{
                    document.form22.catCode.focus();
                }
              
            }
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;

                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;

                return true;

            }
            function chkIndex()
            {            
                var dd = document.getElementById("sname").selectedIndex;
                if(dd == 0)
                {                   
                    document.getElementById("erorlabel").style.display="inline";
                    return false;
                }
                else
                {
                    document.getElementById("erorlabel").style.display="none";
                    return true;
                }


            }
            function clearPropertyId(){
                if(document.getElementById("pId").value=="")
                {
                    document.getElementById("lpId").style.visibility="hidden";
                }
            }

            window.onload = function()
            {
                document.getElementById("pId").focus();
            }

            function checkpropertyId(){

                propId= document.getElementById("pId").value
                 scode=document.getElementById("sCode").value
                    catcode=document.getElementById("catCode").value
                   

                if(document.getElementById("pId").value!="")
                {
                    xmlHttp=GetXmlHttpObject()
                    if (xmlHttp==null)
                    {
                        alert ("Browser does not support HTTP Request")
                        return
                    }
                    var url="<%=request.getContextPath()%>/checkPropId";
                    url=url+"?propId="+propId+"&catCode="+catcode+"&sCode="+scode;
                    xmlHttp.onreadystatechange=stateChanged
                    xmlHttp.open("GET",url,true)
                    xmlHttp.send(null)
                }
                else if(document.getElementById("pId").value=="")
                {
                    document.getElementById("lpId").style.visibility="hidden";
                }

            }
            function clearPropertyNum(){
                if(document.getElementById("pNumber").value=="")
                {
                    document.getElementById("lpNumber").style.visibility="hidden";
                }
            }

            window.onload = function()
            {
                document.getElementById("pNumber").focus();
            }

            function checkpropertyNum(){

                propNum= document.getElementById("pNumber").value;
               
                if(document.getElementById("pNumber").value!="")
                {
                    xmlHttp=GetXmlHttpObject()
                    if (xmlHttp==null)
                    {
                        alert ("Browser does not support HTTP Request")
                        return
                    }
                    var url="<%=request.getContextPath()%>/checkPropNum";
                    url=url+"?&propNum="+propNum;
                    xmlHttp.onreadystatechange=stateChanged1
                    xmlHttp.open("GET",url,true)
                    xmlHttp.send(null)
                }
                else if(document.getElementById("pNumber").value=="")
                {
                    document.getElementById("lpNumber").style.visibility="hidden";
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
                        document.getElementById("lpId").style.visibility="visible";
                        document.getElementById("pId").focus();
                        showdata=0;

                    }
                    else
                    {
                        document.getElementById("lpId").style.visibility="hidden";
                    }

                }
            }
            function stateChanged1()
            {
                if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                {
                    var showdata = xmlHttp.responseText;
                    var showdata = parseInt(xmlHttp.responseText,10);

                    if(showdata==1)
                    {
                        document.getElementById("lpNumber").style.visibility="visible";
                        document.getElementById("pNumber").focus();
                        showdata=0;

                    }
                    else
                    {
                        document.getElementById("lpNumber").style.visibility="hidden";
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
                        <h3>Add Allottee</h3>
                        <div class="clear"></div>
                    </div> <!-- End .content-box-header -->
                    <div class="content-box-content">


                        <div class="tab-content default-tab" >
                            <div id="erorlabel" style="display:none;">
                                <div class="notification error1 png_bg">
                                    <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                    <div>
					Select Scheme Name
                                    </div>
                                </div>
                            </div>

                            <c:set var="t" value="<%= request.getAttribute("t")%>"/>                           
                            <c:choose>
                                <c:when test="${t=='2'}">
                                    <% request.setAttribute("t", 0);%>
                                    <c:set var="t" value="<%=0%>"/>

                                    <div class="notification success png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
					Data Inserted Successfuly.
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${t=='1'}">
                                    <% request.setAttribute("t", 0);%>
                                    <c:set var="t" value="<%=0%>"/>
                                    <div class="notification error1 png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
					 Error ! tryagain.
                                        </div>
                                    </div>

                                </c:when>
                            </c:choose>
                            <form name="form22" id="form2" method="post" action="<%= request.getContextPath()%>/AddAllottee" onsubmit="return chkIndex();">
                                <table id="results">

                                    <tr>
                                        <td>
                                            <p>
                                                <label class="form-label">Scheme Name</label>
                                                <select id="sname" name="sname" onchange="chkIndex();">
                                                    <option value="Select">Select</option>
                                                    <c:forEach  var="schemeValue"  items="${schemeList}">
                                                        <option value="${schemeValue.name}">${schemeValue.name}</option>
                                                    </c:forEach>
                                                </select>                                             

                                            <p>
                                                <label class="form-label">Scheme Code</label>
                                                <input  type="text" name="sCode" id="sCode" style="width: 68px;" maxlength="3" onkeypress="checklength()" onkeydown="return onlyNumbers()" /> -
                                                <input  type="text" name="catCode" id="catCode" style="width: 68px;" maxlength="3" onkeypress="return onlyNumbers()" />
                                            </p>
                                            <p>
                                                <label class="form-label">Property ID</label>
                                                <input  type="text" name="pId" id="pId" onkeypress="return onlyNumbers()" 
                                                        maxlength="4" class="required number" onblur="checkpropertyId()" onkeyup="clearPropertyId()"/>
                                                <label id="lpId" class="error" style="visibility:hidden" >Property id already exists.</label>
                                            </p>
                                            <p>
                                                <label class="form-label">Property Number</label>
                                                <input  type="text" name="pNumber" id="pNumber" onkeypress="return onlyNumbers()"
                                                        class="required" onblur="checkpropertyNum()" onkeyup="clearPropertyNum()"/>
                                                <label id="lpNumber" class="error" style="visibility:hidden" >Property Number already exists.</label>


                                            </p>
                                            <p>
                                                <label class="form-label">Property Code</label>
                                                <input  type="text" name="pCode" id="pCode" onkeypress="return onlyNumbers()" />
                                            </p>
                                            <p>
                                                <label class="form-label">Property Type</label>
                                                <input  type="text" name="pType" id="pType" />
                                            </p>

                                            <p>
                                                <label class="form-label">Final Cost</label>
                                                <input  type="text" name="fCost" id="fCost" onkeypress="return onlyNumbers()" />
                                            </p>                                          

                                            <p>
                                                <input class="button" type="submit" value="Submit" />
                                                <input type="reset" class="button" value="Reset" name="reset">
                                            </p>
                                        </td>
                                        <td>
                                            <p>
                                                <label class="form-label">Allottee Name</label>
                                                <input  type="text" name="aName" id="aName" />
                                            </p>
                                            <p>
                                                <label class="form-label">Father Name</label>
                                                <input  type="text" name="fName" id="fName" />
                                            </p>
                                            <p>
                                                <label class="form-label">Address</label>
                                                <input  type="text" name="Address" id="Address" />
                                            </p>                              

                                            <p>
                                                <label class="form-label">Allottee Date</label>
                                                <input  type="text" name="aDate" id="aDate" />
                                            </p>

                                            <p>
                                                <label class="form-label">Registry Date</label>
                                                <input  type="text" name="rDate" id="rDate" />
                                            </p>

                                            <p>
                                                <label class="form-label">Possession Date</label>
                                                <input  type="text" name="pDate" id="pDate" />
                                            </p>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>

                    </div>
                </div>

                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>Add Scheme</h3>
                        <div class="clear"></div>
                    </div> <!-- End .content-box-header -->
                    <div class="content-box-content">


                        <div class="tab-content default-tab" >                       
                            <form name="schemeform" id="schemeform" method="post" action="<%= request.getContextPath()%>/AddScheme">
                                <table id="results">                                   
                                    <tr>
                                        <td>
                                            <label class="form-label">Scheme Name</label>
                                            <input  type="text" name="schemeName" id="schemeName" class="required" />
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <input class="button" type="submit" value="Submit" />
                                            <input type="reset" class="button" value="Reset" name="reset">

                                        </td>

                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
                <jsp:include page="../common/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
