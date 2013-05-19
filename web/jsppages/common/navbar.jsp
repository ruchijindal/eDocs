<%response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-store, no-cache");
            response.setDateHeader("Expires", 0);
%>

<script type="text/javascript">
    function rate()
    {
        xmlHttp=GetXmlHttpObject()
        if (xmlHttp==null)
        {
            alert ("Browser does not support HTTP Request")
            return;
        }


        var url= "<%=request.getContextPath()%>/rates" ;
        //xmlHttp.onreadystatechange=statechanged;
        xmlHttp.open("GET",url,true);

        xmlHttp.send(null);




    }
</script>
<%!     String userrole;
String accessRight;
    int dashflag;
    int searchflag;
    int adminflag;
    int repoflag;
    int crflag;
    int vuflag;
    int rcrdflag;
    int mstflag;
    int chlflag;
    int billflag;
    int billgen;
    int ledgen;
    int defgen;
    int errgen;
    int dmssettingsflag;
    int setrates;
    int setinsertrate;
    int setcessrate;
    int setdiscountrate;

%>

<%
            searchflag = 0;
            adminflag = 0;
            repoflag = 0;
            crflag = 0;
            vuflag = 0;
            rcrdflag = 0;
            mstflag = 0;
            chlflag = 0;
            billflag = 0;
            billgen = 0;
            ledgen = 0;
            defgen = 0;
            dmssettingsflag = 0;
            setrates = 0;
            setinsertrate = 0;
            setcessrate = 0;
            setdiscountrate = 0;
try{
            userrole = (String) session.getAttribute("userrole");
            if(null==userrole || userrole.equals("null")||userrole.equals(null))
                {
           
                response.sendRedirect("/index.jsp");
                }
            }catch(Exception e)
                    {
                e.printStackTrace();
                response.sendRedirect("/index.jsp");
                }
            Object oo=session.getAttribute("accessRight");
            accessRight=(String)session.getAttribute("accessRight");
            System.out.println("access Right for the current user is "+accessRight);
            String s = new String(request.getRequestURL());
            System.out.println("URL------------" + s);
            String s1 = s.substring(s.lastIndexOf("/") + 1);
            s = s.substring(0, s.lastIndexOf("/"));
            String searchstr = s.substring(s.lastIndexOf("/") + 1);
            System.out.println(searchstr);

            if (searchstr.equals("search")) {
                searchflag = 1;
            } else if (searchstr.equals("admin")) {
                adminflag = 1;
                if (s1.equals("create_user.jsp")) {
                    crflag = 1;
                } else if (s1.equals("view_user.jsp")) {
                    vuflag = 1;
                }
            } /*else if(searchstr.equals("browserepository"))
            repoflag=1;*/ else if (searchstr.equals("chl_mst")) {
                rcrdflag = 1;
                if (s1.equals("new_chl.jsp")) {
                    chlflag = 1;
                } else if (s1.equals("new_mst.jsp")) {
                    mstflag = 1;
                }
            } else if (searchstr.equals("billing")) {
                billflag = 1;
                if (s1.equals("billgen.jsp")) {
                    billgen = 1;
                } else if (s1.equals("ledgergen.jsp")) {
                    ledgen = 1;
                } else if (s1.equals("default_err.jsp")) {
                    defgen = 1;
                }

            } else if (searchstr.equals("dmssettings")) {
                dmssettingsflag = 1;
                if (s1.equals("setRates.jsp")) {
                    setrates = 1;
                } else if (s1.equals("setInterestRate.jsp")) {
                    setinsertrate = 1;
                } else if (s1.equals("setCessRate.jsp")) {
                    setcessrate = 1;
                } else if (s1.equals("setDiscountRate.jsp")) {
                    setdiscountrate = 1;
                }
            }

%>


<ul id="nav" class="dropdown dropdown-horizontal">



    <li>
        <%
                    if (searchflag == 1) {
        %>
        <a href="<%= request.getContextPath()%>/SearchController" class=" current"> <!-- Add the class "current" to current menu item -->
					Search
        </a>

        <%
                                             } else {
        %>


        <a href="<%= request.getContextPath()%>/SearchController"> <!-- Add the class "current" to current menu item -->
					Search
        </a>
        <%
                    }
        %>
    </li>




    <%    //if (userrole.equals("admin") || userrole.equals("employee") || userrole.equals("general")) {
             if(accessRight.equals("rw")||accessRight.equals("r")||accessRight.equals("mn")){
    %>

    <li>
        <%
                                                  if (rcrdflag == 1) {
        %>
        <div class="current "> <!-- Add the class "current" to current menu item -->
            <span class="dir">New Records</span>
        </div>
        <%                                   } else {
        %>
        <span class="dir">New Records</span>
        <%                                          }
        %>
        <ul><li>
                <%

                %>
                <a href="<%= request.getContextPath()%>/addallottee">Add Allottee</a>
                 
                <%

                %>

                <%

                %>
            </li>
        </ul>
    </li>

    <li>
        <%
                                                  if (rcrdflag == 1) {
        %>

            <a href="<%= request.getContextPath()%>/FileManager" class="current"> File Manager</a>

        <%                                   } else {
        %>
        <a  href="<%= request.getContextPath()%>/FileManager">File Manager</a>
        <%                                          }
        %>
         
    </li>
    <%
                }
           //     if (userrole.equals("admin") || userrole.equals("employee") || userrole.equals("general")) {
              if(accessRight.equals("rw")||accessRight.equals("r")||accessRight.equals("mn")){
    %>



    <%            }
    %>


    <%
             //   if (userrole.equals("admin")) {
    if(accessRight.equals("mn")){
    %>
    <li>
        <%
                                                  if (adminflag == 1) {
        %>
        <div class="current "> <!-- Add the class "current" to current menu item -->
            <span class="dir">User Manager</span>
        </div>
        <%                                   } else {
        %>

        <span class="dir">User Manager</span>

        <%                                          }
        %>
        <ul><li>
                <%

                                                          if (crflag == 1) {
                %>
                <a  class="current" href="<%= request.getContextPath()%>/SetUserDetails">Create User</a>
                <%
                                                            } else {
                %>
                <a   href="<%= request.getContextPath()%>/SetUserDetails">Create User</a>

                <%
                                                          }
                %>
            </li>

            <li>
                <%
                                                          if (vuflag == 1) {
                %>
                <a class="current" href="<%= request.getContextPath()%>/viewUser" >View User</a>
                <%
                                                              } else {
                %>
                <a href="<%= request.getContextPath()%>/viewUser" >View User</a>
                <%
                                                          }
                %>
            </li>
        </ul>
    </li>
    <%
                }
               // if (userrole.equals("admin")) {
    if(accessRight.equals("mn")){
    %>
    <li>
        <%

                                                  if (dmssettingsflag == 1) {
        %>
        <div class="current "> <!-- Add the class "current" to current menu item -->
            <span class="dir"> Settings</span>
        </div>
        <%                                      } else {
        %>
        <span class="dir"> Settings</span>
        <%                                          }
        %>
        <ul><li><a href="<%=request.getContextPath() %>/RolesRights">Access Restrictions</a></li></ul>
    </li>
    <% }if(accessRight.equals("rw")||accessRight.equals("r")||accessRight.equals("mn")){ %>
    <li>
        <%

                                                  if (dmssettingsflag == 1) {
        %>
        <div class="current "> <!-- Add the class "current" to current menu item -->
            <span class="dir"> Statistics & Reports</span>
        </div>
        <%                                      } else {
        %>
        <span class="dir"> Statistics & Reports</span>
        <%                                          }
        %>
        <ul><li><a href=" <%= request.getContextPath()%>/RepositoryOverview">Repository Overview</a></li>
        <li><a href=" <%= request.getContextPath()%>/UploadDownloadStatus">Up/Down Status </a></li>
         <li><a href="<%= request.getContextPath()%>/CurrentUser" >User Statistics</a></li>
        </ul>

    </li>
    <%
                }
    %>



</ul> <!-- End #main-nav -->

