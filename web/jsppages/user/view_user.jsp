<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="chl" value="1"></c:set>
<html>
    <head>
        <title>View User</title>
        <link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />

        <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
        <link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="resources/css/simpletree.css" type="text/css" media="screen" />

        <jsp:include page="../common/header.jsp"/>
    </head>
    <script type="text/javascript">

        function makeEditableChl(chl)
        {

            var txt_inp = document.getElementsByTagName('input');
            var m=0 , j=1,k=1;
 
            for(i=0; i< txt_inp.length; i++)
            {

                if(txt_inp[i].type == "text"  )
                {
                    var x = txt_inp[i];


                    // get or set id


                    if(x.name=="username"||x.name=="userRole")
                    {

                        x.id = "Id"+j; // We are dynamically setting and ID
                        Id="Id"+j;
                        
                        if((j==((chl-1)*2)+k) && k<=2)
                        {

                            document.getElementById(Id).removeAttribute("readonly");
                            document.getElementById(Id).removeAttribute("style");
                            document.getElementById("uun"+chl).setAttribute("style", "display: none");
                            document.getElementById("uur"+chl).setAttribute("style", "display: none");
                            document.getElementById(Id).id="ID"+k;
                            k++;
                            document.getElementById("edit"+chl).style.display="none";
                            document.getElementById("save"+chl).style.display="inline-block";
                            document.getElementById("del"+chl).style.display="inline-block";
                            
                        }
                        j++;


                    }
                }

            }


        }

        function edit_User(chl)
        {
            xmlHttp=GetXmlHttpObject()

            if (xmlHttp==null)
            {
                alert ("Browser does not support HTTP Request")
                return;
            }

            var txt_inp = document.getElementsByTagName('input');
            var  j=1,k=1;

            for(i=0; i< txt_inp.length; i++)
            {

                if(txt_inp[i].type == "text"  )
                {
                    var x = txt_inp[i];
                    if(x.name=="id"||x.name=="username"||x.name=="userRole"||x.name=="crDate"||x.name=="createdBy")
                    {

                        x.id = "Id"+j; // We are dynamically setting and ID
                        Id="Id"+j;
                        if((j==((chl-1)*5)+k) && k<=5)
                        {
                            document.getElementById(Id).id="ID"+k;
                            k++;
                        }
                        j++;


                    }
                }
            }
           
            var userId=document.getElementById("ID1").value;
           
            var username=document.getElementById("ID2").value;
            var userRole=document.getElementById("ID3").value;

            var url= "<%=request.getContextPath()%>/editUser?userId=" +userId +"&username="+username  +"&userRole=" +userRole ;

            xmlHttp.onreadystatechange=statechanged;
            xmlHttp.open("POST",url,true);
            xmlHttp.send(null);
        }

        function deleteConfirm(id)
        {
            var msg;
            msg="Do you want to delete this record permanently?"
            var agree=confirm(msg);
            if(agree)
                deleteUser(id);
            else
                return false;

        }

        function deleteUser(id)
        {
            xmlHttp=GetXmlHttpObject()
            if (xmlHttp==null)
            {
                alert ("Browser does not support HTTP Request")
                return;
            }

            var url= "<%=request.getContextPath()%>/deleteUser?id="+id;
            xmlHttp.onreadystatechange=statechanged;
            xmlHttp.open("GET",url,true);
            xmlHttp.send(null);
        }

        function statechanged()
        {
            if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
            {
                window.location.reload();
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
        function labelonly()
        {
            var uid=document.getElementById("uid");
            var uun=document.getElementById("uun");
            var uur=document.getElementById("uur");
            var ucd=document.getElementById("ucd");
            var ucb=document.getElementById("ucb");


        }
    </script>
    <body onload="labelonly();" >
        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->


            <jsp:include page="../common/navbar.jsp"/>

            <div id="main-content"> <!-- Main Content Section with everything -->



                <div class="content-box"><!-- Start Content Box -->

                    <div class="content-box-header">

                        <h3>Users Details</h3>

                        <div class="clear"></div>

                    </div> <!-- End .content-box-header -->

                    <div class="content-box-content">
                         <c:set var="t1" value="<%= session.getAttribute("t1")%>"/>
                            <c:choose>
                                <c:when test="${t1=='1'}">
                                    <% session.setAttribute("t1", 0);%>
                                    <div class="notification success png_bg" >
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
					 Updated Successfully
                                        </div>
                                    </div>

                                </c:when>
                                <c:when test="${t1=='2'}">
                                    <% session.setAttribute("t1", 0);%>
                                    <div class="notification error1 png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
			          There was some error.
                                        </div>
                                    </div>
                                </c:when>

                            </c:choose>

                
                            <c:set var="t" value="<%= session.getAttribute("t")%>"/>
                            <c:choose>
                                <c:when test="${t=='1'}">
                                    <% session.setAttribute("t", 0);%>
                                    <div class="notification success png_bg" >
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
					 Updated Successfully
                                        </div>
                                    </div>

                                </c:when>
                                <c:when test="${t=='2'}">
                                    <% session.setAttribute("t", 0);%>
                                    <div class="notification error1 png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
			          There was some error.
                                        </div>
                                    </div>
                                </c:when>

                            </c:choose>



                            <c:set var="t2" value="<%= session.getAttribute("t2")%>"/>
                            <c:choose>
                                <c:when test="${t2=='1'}">
                                    <% session.setAttribute("t2", 0);%>
                                    <div class="notification success png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
					Data Deleted Successfuly.
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${t2=='2'}">
                                    <% session.setAttribute("t2", 0);%>
                                    <div class="notification error1 png_bg">
                                        <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
                                        <div>
			          There was some error.
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>

 <table id="table1"  >
                            <thead>
                                <tr>
                                    <th>User ID</th>
                                    <th>User Name </th>

                                    <th>User Role</th>

                                    <th>Creation Date</th>

                                    <th>CreatedBy</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            
                            <c:forEach var="userList" items="${userList}" >
                               
                                    <form id="form${chl}" method="post" action=""  >
                                        <tr >
                                            <td align="left" >

                                                    <label id="uid${chl}"  >
                                                        ${userList.userid}
                                                    </label>


                                                <input type="text" name="id" id="id" value="${userList.userid}" size="8px;" readonly style="display: none" />

                                            </td>
                                            <td align="left" >

                                                    <label id="uun${chl}" >
                                                        ${userList.username}
                                                    </label>
                                                

                                                <input type="text" name="username" id="username" value="${userList.username}" size="8px;" readonly style="display: none" />

                                            </td>

                                            <td align="left" >

                                                    <label  id="uur${chl}" style="text-align: center" >
                                                        ${userList.userrole}
                                                    </label>


                                                <input type="text" name="userRole" id="userRole" value="${userList.userrole}" size="8px;" readonly style="display: none" />

                                            </td>

                                            <td align="left" >

                                        <fmt:formatDate pattern="dd/MM/yy" value="${userList.date}" var="crDate"/>

                                            <label id="ucd${chl}" >
                                            ${crDate}
                                            </label>


                                        <input type="text" name="crDate" id="crDate" value="${crDate}" size="8px;" readonly style="display: none" />

                                        </td>

                                        <td align="left" >

                                                <label id="ucb${chl}" >
                                                ${userList.createdby}
                                                </label>
                                            

                                            <input type="text" name="createdBy" id="createdBy" value="${userList.createdby}" size="8px;" readonly style="display: none" />

                                        </td>
                                        <td id="edit${chl}"  style="display:inline-block">  <a href="#bottom" onclick="makeEditableChl(${chl})"><label class="button" >EDIT</label></a></td>
                                        <td id="save${chl}" style="display:none"><input  type="button" onclick="edit_User(${chl})"  class="savebutton" value="" size="8px;"/></td>
                                        <td id="del${chl}" style="display:none"><a href="" onclick="deleteConfirm('${userList.userid}')" ><label class="delbutton"></label></a></td>
                                        </tr>
                                        <c:set var="chl" value="${chl+1}"></c:set>

                                    </form>
                               
                            </c:forEach>

                        </table>
                    </div>
                </div>
                <jsp:include page="../common/footer.jsp"/>
            </div>
        </div>
    </body>
</html>