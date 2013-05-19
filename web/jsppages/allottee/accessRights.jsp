<%-- 
    Document   : accessRights
    Created on : 20 Sep, 2011, 1:27:41 PM
    Author     : smp
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <jsp:include page="../common/header.jsp"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Restriction </title>
        <script type="text/javascript">
            function editMode(id)
            {
                var pid = document.getElementById("pedit"+id);
              
                var pread=document.getElementById("pread"+id);
                var editbutton=document.getElementById("editbutton"+id);
                var savebutton=document.getElementById("savebutton"+id);
                var cancelbutton=document.getElementById("cancelbutton"+id);
                pid.removeAttribute("style");
                pread.setAttribute("style", "display:None");
                savebutton.removeAttribute("style");
                cancelbutton.removeAttribute("style");
                editbutton.setAttribute("style", "display:None");
                
            }
            function readMode(id)
            {
                var pid = document.getElementById("pedit"+id);

                var pread=document.getElementById("pread"+id);
                var editbutton=document.getElementById("editbutton"+id);
                var savebutton=document.getElementById("savebutton"+id);
                var cancelbutton=document.getElementById("cancelbutton"+id);
                pread.removeAttribute("style");
                pid.setAttribute("style", "display:None");
                editbutton.removeAttribute("style");
                savebutton.setAttribute("style", "display:None");
                cancelbutton.setAttribute("style", "display:None");

            }
        </script>
    </head>
    <body>
        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->

            <jsp:include page="../common/navbar.jsp"/>
            <div id="main-content"> <!-- Main Content Section with everything -->
                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">




                        <h3>Access Rights</h3><br/>


                        <h5></h5>

                        <div class="clear"></div>

                    </div> <!-- End .content-box-header -->
                    <div class="content-box-content">
                        <form method="post" action="<%=request.getContextPath()%>/AddRole">
                            <table>
                                <tr>
                                    <td>User Role:</td>
                                    <td colspan="2"> <input type="text" id="Role" name="Role" value="" /></td>
                                </tr>
                                <tr>
                                    <td>Access Rights:</td>
                                    <td colspan="2">
                                        <select id="rights" name="rights" >
                                            <option value="s">Select</option>
                                            <option value="r">Read Only</option>
                                            <option value="rw">Read & Write</option>
                                            <option value="mn">Recovery</option>
                                        </select></td>
                                </tr>
                            </table>
                            <br/>

                            <input type="submit" class="button" name="submit" value="Submit"/>
                            <input type="reset" class="button" name="cancel" value="Cancel"/>
                        </form>
                    </div>
                </div>
                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">




                        <h3>List of User Roles </h3><br/>


                        <h5></h5>

                        <div class="clear"></div>

                    </div> <!-- End .content-box-header -->
                    <div class="content-box-content">
                        <c:set var="rowStart" value="1"/>
                        
                        <c:set var="rowEnd" value="${rolesize}"/>
                        <form method="post" action="<%=request.getContextPath()%>/ChangeRights">
                            <table  >
                                <thead>
                                    <tr>
                                        <th>User Roles</th>
                                        <th>Access Rights</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach  begin="${rowStart-1}" step="1" end="${rowEnd-1}" var="roles" varStatus="status" items="${roleList}">
                                    <tr>
                                        <th>
                                            ${roles.userrole}
                                            <input type="hidden" name="id" id="id" value="${roles.id}"  >
                                        </th>
                                        <td>
                                   
                                            <div id="pread${roles.id}">
                                               
                                                <c:choose>
                                                    <c:when test="${roles.rights=='r'}" >
                                                       
                                                        <label>Read Only</label>
                                                    </c:when>
                                                    <c:when test="${roles.rights=='rw'}" >
                                                        
                                                        <label>Read & Write</label>
                                                    </c:when>
                                                    <c:when test="${roles.rights=='mn'}" >

                                                        <label>Recovery</label>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                            <div id="pedit${roles.id}" style="display: none"  >
                                                <input type="Radio" name="r${roles.id}"  value="read"  />Read Only
                                                <input type="Radio" name="r${roles.id}"  value="write"   />Read & Write
                                            </div>
                                        </td>
                                    <c:if test="${roles.rights!='mn'}" >
                                        <td>
                                            <input type="Button" class="button" name="Edit" value="Edit" id="editbutton${roles.id}"  onclick="editMode(${roles.id});" />
                                            <input type="submit" class="button" name="Save" value="Save" id="savebutton${roles.id}" style="display: none"  />
                                            <input type="Button" class="button" name="cancel" value="cancel" id="cancelbutton${roles.id}" style="display: none" onclick="readMode(${roles.id});" />
                                        </td>
                                        </c:if>
                                    <c:if test="${roles.rights=='mn'}" >
                                        <td></td></c:if>
                                    </tr>

                                </c:forEach>

                                </tbody>
                            </table>

                        </form>

                    </div>
                </div>



            </div>
            <jsp:include page="../common/footer.jsp"/>
        </div>

    </body>
</html>
