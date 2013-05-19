<%--
    Document   : fusionDemo
    Created on : 9 Sep, 2011, 4:12:16 PM
    Author     : smp
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Repository Overview</title>
        <jsp:include page="../common/header.jsp"/>
        <script  type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.validate_1.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resources/jquery/jquery.maskedinput-1.2.2.js"></script>
        <script  type="text/javascript" src="charts/FusionCharts/FusionCharts.js"></script>
        <script  type="text/javascript" src="charts/FusionCharts/FusionChartsExportComponent.js"></script>
    </head>
    <body>
        <div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
            <jsp:include page="../common/navbar.jsp"/>
            <div id="main-content"> <!-- Main Content Section with everything -->
                <div class="content-box"><!-- Start Content Box -->
                    <div class="content-box-header">
                        <h3>Repository Overview</h3>
<!--                         <a href="#" class="button rightchart" onclick="javascript:startExport();">Export Chart</a>-->
                      
                        <div class="clear"></div>
                    </div> <!-- End .content-box-header -->
                    <div class="content-box-content">


                         <div id="chart">
                            <div id="fcexpDiv" ></div>
                        </div>



                        <script type="text/javascript">

                            function startExport(){
                                myExportComponent.BeginExport();
                            }

                            function updateChart(factoryIndex){                               
                                //DataURL for the chart
                                var strURL = "repositoryFileType.jsp?folderDiv="+factoryIndex;
                               // alert("url===="+strURL);
                                //URLEncode it - NECESSARY.
                               // strURL = escape(strURL);
                               // alert("strURL1===== "+strURL);
                                //Get reference to chart object using Dom ID "RepositoryDetailed"
                                var chartObj = getChartFromId("RepositoryDetailed");
                              //  alert("chartObj===== "+chartObj.toString());
                                //Send request for XML
                                chartObj.setDataURL(strURL);
                            }

                            function getTimeForURL(){
                                var dt = new Date();
                                var strOutput = "";
                                strOutput = dt.getHours() + "_" + dt.getMinutes() + "_" + dt.getSeconds() + "_" + dt.getMilliseconds();
                                return strOutput;
                            }

                        </script>

                        <%! int c, c1;

                            public int filesInsideDirectory(File file) {
                                c = 0;
                                if (file.isDirectory()) {
                                    File[] insideFile = file.listFiles();
                                    for (int i = 0; i < insideFile.length; i++) {
                                        if (insideFile[i].isDirectory()) {
                                            c = filesInsideDirectory1(insideFile[i]);

                                        } else if (insideFile[i].isFile()) {
                                            c++;
                                        }
                                    }
                                }
                                return c;

                            }

                            public int filesInsideDirectory1(File file) {

                                if (file.isDirectory()) {
                                    File[] insideFile = file.listFiles();
                                    for (int i = 0; i < insideFile.length; i++) {
                                        if (insideFile[i].isDirectory()) {
                                            filesInsideDirectory1(insideFile[i]);

                                        } else if (insideFile[i].isFile()) {
                                            c++;
                                            System.out.println("c inside inner directories==" + c);
                                        }
                                    }


                                }
                                return c;

                            }
                        %>




                        <% String strXML1 = "";
                                    int c1 = 0;
                                    String animateChart = null;
                                    animateChart = request.getParameter("animate");
                                    //Set default value of 1
                                    if (null == animateChart || animateChart.equals("")) {
                                        animateChart = "1";
                                    }
                                    //String dirpath1 = session.getAttribute("repositoryPath").toString();
                                    //String dirpath1 = "C:\\Repository\\";
                                  //  String dirpath1 = "/home/smp/Repository/";
                                    String dirpath1 =(String) session.getAttribute("repositoryPath");
                                   // String dir = "C:\\Repository\\788-708";
                                    File f2 = new File(dirpath1);
                                    File[] strFilesDirs1 = f2.listFiles();
                                    System.out.println("strFilesDirs1===" + strFilesDirs1.length);
                                    strXML1 = "<chart caption='Schemes and Number of Files in each Scheme'  pieSliceDepth='10' showBorder='0' formatNumberScale='0' Unitsanimation='" + animateChart + "' decimalPrecision='3' formatNumberScale='2'   exportEnabled='1' exportAtClient='1' exportHandler='fcBatchExporter' showFCMenuItem='0' showToolTipShadow='1' showAboutMenuItem='0'>";

                                    for (int i = 0; i < strFilesDirs1.length; i++) {
                                        if (strFilesDirs1[i].isDirectory()) {

                                            System.out.println("directory name" + strFilesDirs1[i]);
                                            int lastIndex = strFilesDirs1[i].toString().lastIndexOf(File.separator);
                                            System.out.println(File.separator+"lastIndex" + lastIndex);
                                            c1 = filesInsideDirectory(strFilesDirs1[i]);
                                            System.out.println("no of files inside" + strFilesDirs1[i] + "are===" + c1);
                                            System.out.println("folder=======" + strFilesDirs1[i]);
                                           session.setAttribute("repo"+String.valueOf(i), strFilesDirs1[i].getAbsolutePath());
                                            strXML1 += "<set label='" + strFilesDirs1[i].toString().substring(lastIndex + 1) + "' value='" + c1 + "' link='javaScript:updateChart(&quot;" + i + "&quot;)'/>";

                                        } //else if (strFilesDirs1[i].isFile()) {
                                        //  System.out.println("files name" + strFilesDirs1[i]);
                                        // }


                                    }

                                    strXML1 += "</chart>";

                        %>
                        <br class="clear"/> <center> <jsp:include page="charts/Includes/FusionChartsRenderer.jsp" flush="true">
                                <jsp:param name="chartSWF" value="charts/FusionCharts/Pie3D.swf" />
                                <jsp:param name="strURL" value="" />
                                <jsp:param name="strXML" value="<%= strXML1%>" />
                                <jsp:param name="chartId" value="RepositoryOverview" />
                                <jsp:param name="chartWidth" value="940" />
                                <jsp:param name="chartHeight" value="500" />
                                <jsp:param name="debugMode" value="false" />
                                <jsp:param name="registerWithJS" value="true" />
                            </jsp:include></center>




<!--                        <script type="text/javascript">
                            //Initialize Batch Exporter with DOM Id as fcBatchExporter
                            var myExportComponent = new FusionChartsExportObject("fcBatchExporter", "charts/FusionCharts/FCExporter.swf");

                            //Add the charts to queue. The charts are referred to by their DOM Id.
                            myExportComponent.sourceCharts = ['RepositoryOverview','RepositoryDetailed'];

                            //------ Export Component Attributes ------//
                            //Set the mode as full mode
                            myExportComponent.componentAttributes.fullMode='1';
                            //Set saving mode as both. This allows users to download individual charts/ as well as download all charts as a single file.
                            myExportComponent.componentAttributes.saveMode='batch';

                            //Default export format
                            myExportComponent.componentAttributes.defaultExportFormat = 'pdf';
                            //Do not show allowed export format drop-down
                            myExportComponent.componentAttributes.showAllowedTypes = '1';

                            //Default file name.
                            myExportComponent.componentAttributes.defaultExportFileName = 'Repository Overview';
                            //Width and height
                            myExportComponent.componentAttributes.width = '300';
                            myExportComponent.componentAttributes.height = '60';
                            //Title of button
                            myExportComponent.componentAttributes.btnsavetitle = 'Save chart';
//                            myExportComponent.componentAttributes.btndisabledtitle = 'click on button';

                            //Render the exporter SWF in our DIV fcexpDiv
                            myExportComponent.Render("fcexpDiv");
                        </script>
-->

                        <%
                                    //Column 2D Chart with changed "No data to display" message
                                    //We initialize the chart with <chart></chart>
%>

                        <center> <jsp:include page="charts/Includes/FusionChartsRenderer.jsp" flush="true">
                                <jsp:param name="chartSWF" value="charts/FusionCharts/Column3D.swf?ChartNoDataText=Please select a Division from pie chart above to view detailed data." />
                                <jsp:param name="strURL" value="" />
                                <jsp:param name="strXML" value="<chart></chart>" />
                                <jsp:param name="chartId"  value="RepositoryDetailed" />
                                <jsp:param name="chartWidth" value="940" />
                                <jsp:param name="chartHeight" value="430" />
                                <jsp:param name="debugMode" value="false" />
                                <jsp:param name="registerWithJS" value="true" />
                            </jsp:include>

                        </center>
                        <br class="clear"/>
                    </div>
                </div>
                <jsp:include page="../common/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
