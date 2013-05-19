<%-- 
    Document   : allotteeDetail
    Created on : 3 Sep, 2011, 10:57:58 AM
    Author     : smp
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <jsp:include page="../common/header.jsp"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Allottee Detail</title>
        <script  type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.validate_1.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.maskedinput-1.2.2.js"></script>
        <script type="text/javascript">

           
            function filter (begriff){
                var suche = begriff.value.toLowerCase();
                var table = document.getElementById("filetable");
                var ele;
                for (var r = 1; r < table.rows.length; r++){
                    ele = table.rows[r].cells[1].innerHTML.replace(/<[^>]+>/g,"");
                    if (ele.toLowerCase().indexOf(suche)>=0 )
                        table.rows[r].style.display = '';
                    else table.rows[r].style.display = 'none';
                }
            }
            $(document).ready(function()
            {
                $("#possDate").mask("99/aaa/9999");
                $("#regDate").mask("99/aaa/9999");
                $("#allotDate").mask("99/aaa/9999");
                $("#aform").validate();


                //                for(k=0;k<document.forms.length;k++)
                //                {
                //                    document.forms[k].id="form"+k;
                //                    // alert(document.forms[k].id);
                //                }
                //                for(k=0;k<document.forms.length;k++)
                //                {
                //                    f=document.forms[k].id;
                //
                //                    $("#"+f+"").validate();
                //                }



            });

            function popitup(url) {
                newwindow=window.open(url,'name','height=1200,width=1200');
                if (window.focus) {newwindow.focus()}
                return false;
            }
            window.onload = function()
            {
                document.getElementById("bl_per_fr").focus();
            }

            function confirmDel()
            {
                var msg;
                msg="Do you want to delete this record permanently?"
                var agree=confirm(msg);
                if(agree)
                    return true;
                else
                    return false;

            }

            function confirmDelpdf()
            {
                var msg;
                msg="Do you want to delete this pdf file permanently?"
                var agree=confirm(msg);
                if(agree)
                    return true;
                else
                    return false;

            }

            function makeEditable()
            {
                var txt_inp = document.getElementsByTagName('input');
                var TxtInpId;
                var i, j=1;

                for(i=0; i< txt_inp.length; i++)
                {

                    if(txt_inp[i].type == "text")
                    {
                        var x = txt_inp[i];
                        TxtInpId = x.id;  // get or set id
                        if(TxtInpId == "" &&(x.name=="allotDate"||x.name=="allotteeName"||x.name=="propId"||x.name=="fatherName"||
                            x.name=="propCode"||x.name=="address"||x.name=="propType"||x.name=="propNumber"||x.name=="regDate"||
                            x.name=="finalCost"||x.name=="possessionDate"))
                        {
                          
                            x.id = TxtInpId = "CId"+j; // We are dynamically setting and ID
                            Id="CId"+j;
                            document.getElementById(Id).removeAttribute("readonly");
                            if(x.name=="allotDate"||x.name=="possessionDate"||x.name=="regDate")
                            {
                                $("#"+Id).mask("99/aaa/9999");
                            }
                            // x.id="";
                            j++;

                        }
                    }
                }

                ///////////////////////////////////////////////////////////

                var txt_select=document.getElementsByTagName('select');
                var TxtSelectId;
                var s,t=1;
                for(s=0; s< txt_select.length; s++)
                {
                    var z = txt_select[s]
                    TxtSelectId = z.id;  // get or set id

                    if(TxtSelectId == "")
                    {
                        z.id = TxtSelectId = "z"+t; // We are dynamically setting and ID
                        document.getElementById(TxtSelectId).removeAttribute("disabled");
                        t++;
                    }

                }

                document.getElementById("editAllottee").removeAttribute("style");
                document.getElementById("cancelAllottee").removeAttribute("style");
                /////////////////////////////////////////////////////////////


            }


            /******************************************************/
            function clearPropertyId(){
                if(document.getElementById("CId2").value=="")
                {
                    document.getElementById("lpId").style.visibility="hidden";
                  
                }
            }
            function checkpropertyId(){

                propId= document.getElementById("CId2").value;
              
                schemeCode= document.getElementById("schemeCode").value;
                catCode= document.getElementById("catCode").value;

                if(document.getElementById("CId2").value!="")
                {
                    xmlHttp=GetXmlHttpObject()
                    if (xmlHttp==null)
                    {
                        alert ("Browser does not support HTTP Request")
                        return
                    }
                    var url="<%=request.getContextPath()%>/checkPropertyId";
                    url=url+"?&propId="+propId+"&schemeCode="+schemeCode+"&catCode="+catCode;
                    xmlHttp.onreadystatechange=pstateChanged
                    xmlHttp.open("GET",url,true)
                    xmlHttp.send(null)
                }
                else if(document.getElementById("CId2").value=="")
                {
                    document.getElementById("lpId").style.visibility="hidden";                     
                }

            }
            function pstateChanged()
            {
                if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                {
                    var showdata = xmlHttp.responseText;
                    var showdata = parseInt(xmlHttp.responseText,10);

                    if(showdata==1)
                    {
                        document.getElementById("lpId").style.visibility="visible";                       
                        document.getElementById("CId2").focus();
                        document.getElementById("wrongPropId").value="0";
                        showdata=0;
                      
                    }
                    else
                    {
                        document.getElementById("lpId").style.visibility="hidden";
                        document.getElementById("wrongPropId").value="1";
                       
                    }

                }
            }
            function ckhId()
            {
                if(  document.getElementById("wrongPropId").value == "1")
                    return true;
                else
                    return false;

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

            function poponload(chl)
            {
                var dirSize=document.getElementById("dirSize").value;
                for( i=1;i<=dirSize;i++)
                {
                    document.getElementById("checkbox"+i).checked=false;
                }
              
                document.getElementById("checkbox"+chl).checked=true;
                document.getElementById("b1"+chl).style.display="none";
                document.getElementById("b2"+chl).style.display="none";
                document.getElementById("b3"+chl).style.display="none";
                  
                document.getElementById("b4"+chl).style.display="inline";
                document.getElementById("b5"+chl).style.display="inline";
                document.getElementById("file"+chl).style.display="inline";

            }
            function  cancelonload(chl)
            {
                document.getElementById("b1"+chl).style.display="inline";
                document.getElementById("b2"+chl).style.display="inline";
                document.getElementById("b3"+chl).style.display="inline";

                document.getElementById("b4"+chl).style.display="none";
                document.getElementById("b5"+chl).style.display="none";
                document.getElementById("file"+chl).style.display="none";

            }
            function deleteConfirm()
            {
                var msg;
                var  filename1;
                var fileString="";

                var dirSize=document.getElementById("dirSize").value;
                for( i=1;i<=dirSize;i++)
                {
                    if(document.getElementById("checkbox"+i).checked){
                        filename1=document.getElementById("hiddenname"+i).value;
                        fileString+= filename1+":";
                    }
                }
                if(fileString == "" || fileString == null)
                {
                    alert("No File Selected!");
                    return false;
                }
                else
                {
                    msg="Do you want to delete this file permanently?"
                    var agree=confirm(msg);
                    if(agree)
                        deleteFile(fileString);
                    else
                        return false;
                }

            }

            function deleteFile(fileString)
            {
          
                xmlHttp=GetXmlHttpObject()
                if (xmlHttp==null)
                {
                    alert ("Browser does not support HTTP Request")
                    return;
                }
                var url= "<%=request.getContextPath()%>/deleteFile?selectedFiles="+fileString;
                xmlHttp.onreadystatechange=statechanged;
                xmlHttp.open("GET",url,true);
                xmlHttp.send(null);
            }
            function statechanged()
            {
                if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                {
                    window.location.reload();
                    var dirSize=document.getElementById("dirSize").value;
                    for( i=1;i<=dirSize;i++)
                    {
                        document.getElementById("checkbox"+i).checked=false;
                    }
                }

            }
            
            function replaceFile(path)
            {
                var filename=document.getElementById("uploadfile").value;
               

                xmlHttp=GetXmlHttpObject()
                if (xmlHttp==null)
                {
                    alert ("Browser does not support HTTP Request")
                    return;
                }
                var url= "<%=request.getContextPath()%>/replacePDF?filename="+filename+"&parentPath="+path;
                xmlHttp.onreadystatechange=statechanged1;
                xmlHttp.open("POST",url,true);
                xmlHttp.send(null);
            }

            function statechanged1()
            {
                if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
                {
                    var flag = xmlHttp.responseText;
                   
                    if(flag == 1)
                    {
                        document.getElementById("flag").value="1";
                      
                    }
                      
                    else
                    {
                        document.getElementById("flag").value="0";
                      
                    }
                        

                }

            }
            

            function replaceConfirm()
            {
                var msg;
                msg="Do you want to replace the Existing file?"
                var filename=document.getElementById("uploadfile").value;
                if(filename == "" || filename == null)
                {return false;}
                else
                {
               
                    if( document.getElementById("flag").value == 1){
                        var agree=confirm(msg);
                        if(agree)
                            return true;
                        else
                            return false;
                    }
                    else
                        return true;
                }

            }
           
            
            function getlink(chl)
            {
                var filename=document.getElementById("file"+chl).value;
               
                if(filename == null || filename == "")
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            function expandDir(filename,parent)
            {
                
                xmlHttp=GetXmlHttpObject()
                if (xmlHttp==null)
                {
                    alert ("Browser does not support HTTP Request")
                    return;
                }
               
                var url= "<%=request.getContextPath()%>/expandDir?dirName="+filename+"&parentNode="+parent;
                xmlHttp.onreadystatechange=statechanged;
                xmlHttp.open("GET",url,true);
                xmlHttp.send(null);

            }
            function checkButtonClick(button)
            {
                var filename=document.getElementById("fname").value;

                if(filename == null || filename == "")
                {
                    alert("Enter Dir/File  Name");

                }
                else{
                    xmlHttp=GetXmlHttpObject()
                    if (xmlHttp==null)
                    {
                        alert ("Browser does not support HTTP Request")
                        return;
                    }
                    var url= "<%=request.getContextPath()%>/createDir?filename="+filename+"&flag="+button;
                    xmlHttp.onreadystatechange=statechanged;
                    xmlHttp.open("GET",url,true);
                    xmlHttp.send(null);
                }
            }
            function moveFile()
            {
                var fileString="";
                var dirSize=document.getElementById("dirSize").value;
                var filename=document.getElementById("fname").value;
                var k=0;
                var filename1;
                if(filename == null || filename == "")
                {
                    alert("Enter Destination Path");
                    return false;

                }
                else
                {
                
                 
                    for( i=1;i<=dirSize;i++)
                    {
                        if(document.getElementById("checkbox"+i).checked){
                            //alert("if");

                            filename1=document.getElementById("hiddenname"+i).value;
                            parentPath=document.getElementById("hiddenpath"+i).value;
                            fileString+= filename1+":";
                        }


                    }
                 
                
                    
                    xmlHttp=GetXmlHttpObject()
                    if (xmlHttp==null)
                    {
                        alert ("Browser does not support HTTP Request")
                        return;
                    }
                    var url= "<%=request.getContextPath()%>/moveFile?destination="+filename+"&source="+fileString;
                    xmlHttp.onreadystatechange=statechanged;
                    xmlHttp.open("POST",url,true);
                    xmlHttp.send(null);
                    return true;
                
                }

                
            }

            function renameFile()
            {
                var oldName="";
                var dirSize=document.getElementById("dirSize").value;
             
                var filename=document.getElementById("fname").value;
                var k=0;
                var filename1;
                if(filename == null || filename == "")
                {
                    alert("Enter New Filename");

                }
                else
                {
                    for( i=1;i<=dirSize;i++)
                    {
                        if(document.getElementById("checkbox"+i).checked){

                            filename1=document.getElementById("hiddenname"+i).value;
                            k++;
                        }

                    }
                    if(k > 1){
                        alert("Select only one file to rename");
                    }
                    else
                    {
                        oldName=filename1;
                    

                        xmlHttp=GetXmlHttpObject()
                        if (xmlHttp==null)
                        {
                            alert ("Browser does not support HTTP Request")
                            return;
                        }
                        var url= "<%=request.getContextPath()%>/reNameFile?newName="+filename+"&oldName="+oldName;
                        xmlHttp.onreadystatechange=statechanged;
                        xmlHttp.open("POST",url,true);
                        xmlHttp.send(null);

                    }
                }


            }

            function copyFile()
            {
                var fileString="";
                var dirSize=document.getElementById("dirSize").value;
                var filename=document.getElementById("fname").value;
                var k=0;
                var filename1;
                if(filename == null || filename == "")
                {
                    alert("Enter Destination Path");
                    return false;

                }
                else
                {


                    for( i=1;i<=dirSize;i++)
                    {
                        if(document.getElementById("checkbox"+i).checked){
                            //alert("if");

                            filename1=document.getElementById("hiddenname"+i).value;
                            parentPath=document.getElementById("hiddenpath"+i).value;
                            fileString+= filename1+":";
                        }


                    }



                    xmlHttp=GetXmlHttpObject()
                    if (xmlHttp==null)
                    {
                        alert ("Browser does not support HTTP Request")
                        return;
                    }
                    var url= "<%=request.getContextPath()%>/copyFile?destination="+filename+"&source="+fileString;
                    xmlHttp.onreadystatechange=statechanged;
                    xmlHttp.open("POST",url,true);
                    xmlHttp.send(null);
                    return true;

                }


            }


            function  chkCheckbox(id)
            {
                var dirSize=document.getElementById("dirSize").value;
                for( i=1;i<=dirSize;i++)
                {
                    document.getElementById("checkbox"+i).chpropId1ecked=false;
                }


                document.getElementById("checkbox"+id).checked=true;

            }
            function selectallCheckbox()
            {
                var dirSize=document.getElementById("dirSize").value;
                if( document.getElementById("selectall").checked == true)
                {
                    for( i=1;i<=dirSize;i++)
                    {
                        document.getElementById("checkbox"+i).checked=true;
                    }

                }
                else
                {
                    for( i=1;i<=dirSize;i++)
                    {
                        document.getElementById("checkbox"+i).checked=false;
                    }

                }
            }

            function downloadFileMethod()
            {
               
                var  filename1;
                var fileString="";

                var dirSize=document.getElementById("dirSize").value;
            
                for( i=1;i<=dirSize;i++)
                {
                    if(document.getElementById("checkbox"+i).checked){
                        filename1=document.getElementById("hiddenname"+i).value;
                        fileString+= filename1+":";
                    }
                }
                propId1
                if(fileString == "" || fileString == null)
                {
                    alert("No File Selected!");
                    return false;
                }
                else
                {
                    document.getElementById("downloadfile").value=fileString;
                    return true;
             
                }

            }
            /******************************************************/


        </script>
    </head>
    <body>
          <c:set var="accessRight" value="<%= session.getAttribute("accessRight")%>" />

        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->

            <jsp:include page="../common/navbar.jsp"/>
            <div id="main-content"> <!-- Main Content Section with everything -->

                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">

                        <h3>Allottee Detail</h3><a href="#bottom"><img  alt="bottom" src="<%=request.getContextPath()%>/resources/images/icons/top.png"/></a>
                        <c:if test="${accessRight=='mn'||accessRight=='rw'}">
                        <div class="content-box-tabs">
                            <a href="#" onclick="makeEditable()" ><label class="button">EDIT</label></a><!-- href must be unique and match the id of target div -->
                            <a href="<%= request.getContextPath()%>/deleteAllottee?id=${AllotteeDetail.id}"><label class="button" onclick="return confirmDel()">DELETE</label></a>
                        </div>
                        </c:if>
                        <div class="clear"></div>
                    </div>
                    <div class="content-box-content">

                        <div class="tab-content default-tab" id="tab1">
                            <form method="post"  id="aform" name="aform" action="<%= request.getContextPath()%>/UpdateAllotteeDetails" onsubmit=" return ckhId();">
                                <input type="hidden" name="rowid" value=""/>
                                <input type="hidden" id="wrongPropId" name="wrongPropId" value="1"/>
                                <noscript> <!-- Show a notification if the user has disabled javascript -->
                                    <div class="notification error1 png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
						Javascript is disabled or is not supported by your browser. Please upgrade your browser or Enable Javascript in your browser.
                                        </div>
                                    </div>

                                </noscript>
                                <%
                                            System.out.println("td is " + session.getAttribute("td"));
                                %>
                                <c:set var="dircr" value="<%= session.getAttribute("dircreation")%>"/>
                                <c:choose>
                                    <c:when test="${dircr=='2'}">
                                        <% session.setAttribute("dircreation", 0);%>
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
			           There was some error in Repository please create Directory Structure from File Manager..
                                            </div>
                                        </div>
                                    </c:when>
                                </c:choose>
                                <c:set var="t" value="<%= request.getAttribute("t")%>"/>
                                <c:choose>
                                    <c:when test="${t=='1'}">
                                        <% request.setAttribute("t", 0);%>
                                        <div class="notification success png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
			             Record updated.
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${t=='2'}">
                                        <% request.setAttribute("t", 0);%>
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
			           There was some error.
                                            </div>
                                        </div>
                                    </c:when>

                                    <c:when test="${t=='3'}">
                                        <% request.setAttribute("t", 0);%>
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
			           pdf file does not exist!
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${t=='4'}">
                                        <% request.setAttribute("t", 0);%>
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
                                                File is readonly can't be deleted.
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${t=='5'}">
                                        <% request.setAttribute("t", 0);%>
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
                                                Directory is not empty.
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${t=='6'}">
                                        <% request.setAttribute("t", 0);%>
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
                                                There was some error.
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${t=='7'}">
                                        <% request.setAttribute("t", 0);%>
                                        <div class="notification success png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
                                                File deleted successfully.
                                            </div>
                                        </div>
                                    </c:when>



                                </c:choose>


                                <c:set var="t1" value="<%= request.getAttribute("t1")%>"/>
                                <c:choose>
                                    <c:when test="${t1=='1'}">
                                        <%request.setAttribute("t1", 0);%>
                                        <div class="notification success png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
			           Record inserted Successfully.
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${t1=='2'}">
                                        <% request.setAttribute("t1", 0);%>
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
			           There was some error.
                                            </div>
                                        </div>
                                    </c:when>
                                </c:choose>


                                <c:choose>
                                    <c:when test="${t2=='1'}">
                                        <%request.setAttribute("t2", 0);%>
                                        <div class="notification success png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
			           Record deleted successfully.
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:when test="${t2=='2'}">
                                        <%request.setAttribute("t2", 0);%>
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>
			           There was some error.
                                            </div>
                                        </div>
                                    </c:when>
                                </c:choose>

                                <input type="hidden" name="id" value="${AllotteeDetail.id}" name="id"/>

                                <table  class="ed" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <th>Scheme Code</th>
                                        <td><input type="text" id="schemeCode" name="schemeCode" style="width: 70px;"  class="required" readonly value="${AllotteeDetail.schemeCode}"/>-
                                            <input type="text" id="catCode" name="catCode"  class="required" style="width: 70px;" readonly value="${AllotteeDetail.catCode}"   /></td>
                                        <th>Allottee Name</th>
                                        <td><input type="text" name="allotteeName"   readonly value="${AllotteeDetail.alloteeName}" /></td>
                                    </tr>
                                    <tr>
                                        <th>Property Id</th>
                                        <td><input type="text"  name="propId"  readonly value="${AllotteeDetail.propId}" 
                                                   maxlength="4" class="required number" onblur="checkpropertyId()" onkeyup="clearPropertyId()"/>
                                            <label id="lpId" class="error" style="visibility:hidden" >Property id already exists.</label>
                                        </td>
                                        <th>Father Name</th>
                                        <td><input type="text" name="fatherName" class="required" value="${AllotteeDetail.fatherName}" readonly /></td>
                                    </tr>
                                    <tr>
                                        <th>Property Code</th>
                                        <td><input type="text" name="propCode" class="required"  value="${AllotteeDetail.propCode}"  readonly />
                                        <th>Address</th>
                                        <td><input type="text" name="address"  class="required"  value="${AllotteeDetail.address}" readonly /></td>

                                    </tr>                            
                                    <tr>

                                        <th>Property Type</th>
                                        <td><input type="text" name="propType" value="${AllotteeDetail.propertyType}" readonly /></td>
                                        <th>Allottee Date</th>
                                        <td><fmt:formatDate pattern="dd/MMM/yyyy" value="${AllotteeDetail.allotteeDate}" var="allotDate"/><input type="text" name="allotDate"  value="${allotDate}" readonly /></td>

                                    </tr>
                                    <tr>

                                        <th>Property Number</th>
                                        <td><input type="text" name="propNumber"   class="number" value="${AllotteeDetail.propNumber}" readonly/></td>
                                        <th>Registry Date</th>
                                        <td><fmt:formatDate pattern="dd/MMM/yyyy" value="${AllotteeDetail.registryDate}" var="regDate"/><input type="text" name="regDate"   value="${regDate}" readonly /></td>


                                    </tr>
                                    <tr>

                                        <th>Final Cost</th>
                                        <td><input type="text" name="finalCost"   class="number" value="${AllotteeDetail.fianlCost}" readonly/></td>

                                        <th>Possession Date</th>
                                        <td><fmt:formatDate pattern="dd/MMM/yyyy" value="${AllotteeDetail.possessionDate}" var="possessionDate"/><input type="text" name="possessionDate"    value="${possessionDate}" readonly /></td>

                                    </tr>

                                </table>
                                <div align="center">
                                    <input id="editAllottee" type="submit" class="button" style="display: none"  value="SAVE" />
                                    <a href="<%= request.getContextPath()%>/AllotteeDetails?propId=${AllotteeDetail.propId}&propCode=${AllotteeDetail.propCode}"> <input id="cancelAllottee" type="button" class="button" style="display: none" value="CANCEL" /></a>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>


                <div class="content-box"><!-- Start Content Box -->

                    <div class="content-box-header">




                        <h3>Document Details </h3><a href="#bottom"><img  alt="bottom" src="<%=request.getContextPath()%>/resources/images/icons/top.png"/></a>

                        <c:set var="parentNode" value="<%= session.getAttribute("parentNode")%>"/>
                        <c:set var="totalSize" value="<%= session.getAttribute("totalSize")%>"/>
                        <c:set var="totalFile" value="<%= session.getAttribute("totalFile")%>"/>
                        <c:set var="pathName" value="<%= session.getAttribute("pathName")%>"/>




                        <div class="clear"></div>

                    </div> <!-- End .content-box-header -->
                    <c:choose>
                        <c:when test="${dircr=='2'}">
                            <c:set var="dircr" value="0" />
                            <br/>
                            <h6> No Directory / File available.</h6>
                            <br/>
                        </c:when>
                        <c:when test="${dircr=='1'}">


                            <div class="content-box-content">
                                <div class="tab-content default-tab" id="tab1">

                                    <noscript> <!-- Show a notification if the user has disabled javascript -->
                                        <div class="notification error1 png_bg">
                                            <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                            <div>

						Javascript is disabled or is not supported by your browser. Please upgrade your browser or Enable Javascript in your browser.
                                            </div>
                                        </div>

                                    </noscript>
                                    <c:set var="td" value="<%= session.getAttribute("td")%>"/>
                                    <c:set var="alertMsg" value="<%= session.getAttribute("alertMsg")%>"/>
                                    <%System.out.println("td=====> " + session.getAttribute("td") + "alerMsg=>" + session.getAttribute("alertMsg"));%>
                                    <c:choose>
                                        <c:when test="${td=='2'}">
                                            <% session.setAttribute("td", 0);%>
                                            <div class="notification error1 png_bg">
                                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                                <div>
                                                    ${alertMsg}
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${td=='1'}">
                                            <% session.setAttribute("td", 0);%>
                                            <div class="notification success png_bg">
                                                <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                                <div>
                                                    ${alertMsg}
                                                </div>
                                            </div>
                                        </c:when>

                                    </c:choose>

                                    <span class="pathname"> Filename filter : </span> <input name="filt" onKeypress="event.cancelBubble=true;" onkeyup="filter(this)" type="text">
                                    <table id="filetable" class="filelist" >

                                        <thead>

                                            <tr>
                                                <th></th>
                                                <th> Document</th>
                                                <th> Size</th>
                                                <th> Type</th>
                                                <th> Last Update</th>
                                                <th>Actions</th>
                                                <th></th><th></th>

                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:set var="chl" value="1"></c:set>
                                            <c:set var="rootPath" value="<%= session.getAttribute("rootPath")%>"/>
                                            <tr><td></td><td colspan="7"><a href="<%= request.getContextPath()%>/expandDir?dirName=root&parentNode=${rootPath}">[ Root ]</a> </td>
                                            </tr>

                                            <c:if test="${(parentNode ne null) && (parentNode ne rootPath)}">
                                                <tr><td></td><td colspan="7"><a href="<%= request.getContextPath()%>/backDir?parentNode=${parentNode}">[..]</a> </td>
                                                </tr>
                                            </c:if>
                                            <c:set var="dirLength" value="<%= session.getAttribute("dirLength")%>"/>
                                        <input type="hidden" id="dirSize" value="${dirLength}"/>
                                         

                                        <c:forEach  var="file"  items="${fileList}">

                                            <tr>
                                            <form method="post" id="row${chl}"  enctype="multipart/form-data" action="<%= request.getContextPath()%>/MergePDF?filename=${file.fileFullName}&parentPath=${file.parent}" onsubmit="return getlink('${chl}');">
                                                <td><input id="checkbox${chl}" type="checkbox"></td>
                                                <input type="hidden" id="hiddenname${chl}" value="${file.fileFullName}"/>
                                                <input type="hidden" id="hiddenpath${chl}" value="${file.parent}"/>
                                                <input type="hidden" id="hiddentype${chl}" value="${file.isFile}"/>
                                                <c:if test="${file.isFile eq 1 }">

                                                    <td><a onclick="chkCheckbox('${chl}')" href="<%= request.getContextPath()%>/DownloadPDF?filename=${file.fileFullName}&parentPath=${file.parent}">  ${file.fileName}  </a></td>
                                                    <td> ${file.fileSize} </td>
                                                    <td> ${file.fileType} </td>
                                                    <td> <fmt:formatDate type="date" value="${file.lastUpdate}" pattern="dd/MMM/yyyy" />
                                                    </td>

                                                    <td><a onclick="chkCheckbox('${chl}')"  id="b1${chl}" class="button" href="<%= request.getContextPath()%>/DownloadPDF?filename=${file.fileFullName}&parentPath=${file.parent}" style="display: inline">Download</a>

                                                    <c:if test="${fn:containsIgnoreCase(file.fileType,'pdf')==true}">
                                                         <a onclick="chkCheckbox('${chl}')" id="b2${chl}" class="button" href="<%= request.getContextPath()%>/Pdfviewer?filename=${file.fileFullName}&parentPath=${file.parent}" style="display: inline">View</a>
                                                       <c:if test="${accessRight=='mn'||accessRight=='rw'}">
                                                           <a  id="b3${chl}" class="button" href="#" onclick="poponload('${chl}');" style="display: inline">Merge</a>
                                                       </c:if>
                                                        </c:if>
                                                        <input id="file${chl}" type="file" name="uploadfile" size="5"  style="display: none">
                                                        <input id="b4${chl}" class="button" type="submit" value="Merge" style="display: none"   title="Browse the file to Merge"  />
                                                        <input id="b5${chl}" class="button" onclick="cancelonload('${chl}');" type="button" value="Cancel" style="display: none"  />

                                                    </td>

                                                </c:if>
                                                <c:if test="${file.isDir eq 1 }">
                                                    <td><a onclick="chkCheckbox('${chl}')" href="<%= request.getContextPath()%>/expandDir?dirName=${file.fileFullName}&parentNode=${file.parent}">[ ${file.fileName} ]</a> </td>
                                                    <td> ${file.fileSize} </td>
                                                    <td> DIR </td>
                                                    <td> <fmt:formatDate type="date" value="${file.lastUpdate}" pattern="dd/MMM/yyyy" />
                                                    </td>
                                                    <td> <a onclick="chkCheckbox('${chl}')" class="button" href="<%= request.getContextPath()%>/DownloadDir?filename=${file.fileFullName}&parentPath=${file.parent}">Download</a>
                                                    </td>

                                                </c:if>

                                            </form>
                                            </tr>

                                            <c:set var="chl" value="${chl+1}"></c:set>

                                        </c:forEach>

                                        </tbody>

                                    </table><br/>
                                    <input type="checkbox" id="selectall" onchange="selectallCheckbox();"/><label>Select All</label>
                                    <div align="center">
                                        <c:if test="${parentNode eq null}">
                                            <tr><td colspan="5"><b>${totalSize}</b> in <b>${totalFile} files </b> in <span class="pathname"> ${pathName}</span></td></tr>
                                        </c:if>
                                        <c:if test="${parentNode ne null}">
                                            <tr><td colspan="5"><b>${totalSize}</b> in <b>${totalFile} files</b> in <span class="pathname"> ${pathName}</span></td></tr>
                                        </c:if>
                                    </div>
                                    <br/>

                                    <form method="post" action="<%=request.getContextPath()%>/DownloadAllfiles"  onsubmit="return downloadFileMethod();" >
                                        <c:if test="${accessRight=='mn'||accessRight=='rw'}">

                                         <input class="button" type="button" value="Delete Selected Files" onclick="deleteConfirm();" title="Select the file for deletion" />
                                        </c:if>
                                        <input type="hidden" value="" id="downloadfile" name="downloadfile"/>
                                        <input class="button" type="submit" value="Download Selected Files as Zip" title="Select the file for deletion" />
                                    </form>

                                    <br/>

                                    <div class="formular">
                                        <input type="text" name="fname" id="fname" title="Enter the name">
                                        <input class="button" type="button" value="Create Dir" onclick="checkButtonClick('1')" title="Enter the directory name" />
                                        <input class="button" type="button" value="Create File" onclick="checkButtonClick('2')" title="Enter the File name" />
                                        <input class="button" type="button" value="Move Files" onclick=" moveFile()" title="Enter the Designation Directory name" />                                        
                                        <input class="button" type="button" value="Copy Files" onclick=" copyFile()" title="Enter the Designation Directory name" />
                                        <c:if test="${accessRight=='mn'||accessRight=='rw'}">
                                            <input class="button" type="button" value="Rename File" onclick=" renameFile()" title="Enter the new File  name" />
                                        </c:if>

                                    </div>
                                    <br/>

                                    <div class="formular">
                                        <input type="hidden" id="flag" name="flag" value="0"/>
                                        <form method="post"  enctype="multipart/form-data" action="<%= request.getContextPath()%>/UploadPDF?currentPath=${parentNode}"  onsubmit="return replaceConfirm();">
                                            <input type="file" name="uploadfile" size="10" id="uploadfile"  onchange="replaceFile('${parentNode}');">
                                            <input  class="button" type="submit" value="Upload File" title="Browse the file to Upload"/>
                                        </form>
                                    </div>
                                </div>

                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <%  String osType = System.getProperty("os.name");
                        System.out.println("os+++++++" + osType);%>

            <jsp:include page="../common/footer.jsp"/>
        </div>

    </body>
</html>
