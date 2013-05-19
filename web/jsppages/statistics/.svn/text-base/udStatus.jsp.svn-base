<%--
    Document   : udStatus
    Created on : 19 Sep, 2011, 11:18:25 AM
    Author     : smp
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="java.io.File"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="java.util.Date"%>
<%@page import="org.w3c.dom.Node"%>
<%@page import="javax.xml.transform.dom.DOMSource"%>
<%@page import="javax.xml.transform.stream.StreamResult"%>
<%@page import="javax.xml.transform.TransformerFactory"%>
<%@page import="javax.xml.transform.Transformer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload/Download Status</title>
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
                        <h3>Upload And Download Overview</h3>
<!--                        <a href="#" class="button rightchart" onclick="javascript:startExport();">Export Chart</a>-->

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
                                //URLEncode it - NECESSARY.
                                strURL = escape(strURL);
                                alert(strURL);
                                //Get reference to chart object using Dom ID "ConsumerDetailed"
                                var chartObj = getChartFromId("ConsumerDetailed");
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



                        <%
                                    int downFile = 0;
                                    int upFile = 0;
                                    Date date2;
                                    Date date1 = null;
                                    String days = "";
                                    String strXML1 = "";
                                    String current_day = "";
                                    int current_index = 0;
                                    Map<Integer, Integer> uploadMap = new TreeMap<Integer, Integer>();
                                    Map<Integer, Integer> downloadMap = new TreeMap<Integer, Integer>();
                                    ArrayList week_day = new ArrayList();
                                    week_day.add("Mon");
                                    week_day.add("Tue");
                                    week_day.add("Wed");
                                    week_day.add("Thu");
                                    week_day.add("Fri");
                                    week_day.add("Sat");
                                    week_day.add("Sun");

                                    uploadMap.put(0, 0);
                                    uploadMap.put(1, 0);
                                    uploadMap.put(2, 0);
                                    uploadMap.put(3, 0);
                                    uploadMap.put(4, 0);
                                    uploadMap.put(5, 0);
                                    uploadMap.put(6, 0);

                                    downloadMap.put(0, 0);
                                    downloadMap.put(1, 0);
                                    downloadMap.put(2, 0);
                                    downloadMap.put(3, 0);
                                    downloadMap.put(4, 0);
                                    downloadMap.put(5, 0);
                                    downloadMap.put(6, 0);

                                    File logFile = new File(session.getAttribute("logPath").toString().trim() + session.getAttribute("userid").toString().trim() + ".xml");
                                    if (logFile.exists()) {
                                        System.out.println("Log File Exists");
                                        try {

                                            String animateChart = null;
                                            animateChart = request.getParameter("animate");
                                            //Set default value of 1
                                            if (null == animateChart || animateChart.equals("")) {
                                                animateChart = "1";
                                            }

                                            strXML1 = "<chart caption='Upload & Download Files Overview' subcaption='for last 7 days' lineThickness='1' showValues='0' formatNumberScale='0' Unitsanimation='" + animateChart + "'anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridColor='C3300' shadowAlpha='40'  numvdivlines='5'chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' exportEnabled='1' exportAtClient='1' exportHandler='fcBatchExporter' showFCMenuItem='0' showToolTipShadow='1' showAboutMenuItem='0'>";

                                            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                                            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                                            Document doc = docBuilder.parse(logFile);
                                            doc.getDocumentElement().normalize();
                                            NodeList logList = doc.getElementsByTagName("log");
                                            int logListSize = logList.getLength();
                                            System.out.println("log List size is " + logListSize);
                                            Node logNode = logList.item(0);

                                            if (logNode.getNodeType() == Node.ELEMENT_NODE) {
                                                Element logElement = (Element) logNode;
                                                NodeList recordList = logElement.getElementsByTagName("record");
                                                System.out.println("record List size is " + recordList.getLength());
                                                if (recordList.getLength() > 0) {
                                                    for (int day = 0; day < 7; day++) {
                                                        long daydiff = 0;
                                                        downFile = 0;
                                                        upFile = 0;

                                                        for (int i = 0; i < recordList.getLength(); i++) {
                                                            date1 = new Date();
                                                            daydiff = 0;
                                                            Node recordNode = recordList.item(i);
                                                            if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
                                                                Element recordElement = (Element) recordNode;

                                                                if (recordElement.hasAttribute("date")) {

                                                                    String date = recordElement.getAttribute("date").trim();

                                                                    SimpleDateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                                                                    date2 = df.parse(date);
                                                                    long d1 = date1.getTime();
                                                                    long d2 = date2.getTime();
                                                                    long d = d1 - d2;
                                                                    daydiff = d / (24 * 60 * 60 * 1000);
                                                                    if (daydiff == day) {
                                                                        System.out.println(date1.toString() + "date2 is " + date2.toString() + "   " + date1.compareTo(date2));


                                                                    } else {
                                                                        continue;
                                                                    }
                                                                    days = date2.toString().substring(0, 3);
                                                                    System.out.println("days==== " + days);
                                                                }
                                                                NodeList messageList = recordElement.getElementsByTagName("message");
                                                                System.out.println("message List size is " + messageList.getLength());


                                                                if (messageList.getLength() > 0) {
                                                                    for (int j = 0; j < messageList.getLength(); j++) {
                                                                        Node messageNode = messageList.item(j);
                                                                        if (messageNode.getNodeType() == Node.ELEMENT_NODE) {
                                                                            Element messageElement = (Element) messageNode;
                                                                            String status = "";
                                                                            if (messageElement.hasAttribute("status")) {
                                                                                status = messageElement.getAttribute("status").trim();
                                                                                System.out.println(messageElement.getTextContent() + " Status is " + status);
                                                                            } else {
                                                                                continue;
                                                                            }
                                                                            if (status.equalsIgnoreCase("d")) {
                                                                                int weekday1 = downloadMap.get(week_day.indexOf(days));
                                                                                System.out.println("inside download=== " + weekday1);
                                                                                downloadMap.put(week_day.indexOf(days), ++weekday1);
                                                                                downFile++;
                                                                            } else if (status.equalsIgnoreCase("u")) {
                                                                                int weekday = uploadMap.get(week_day.indexOf(days));
                                                                                System.out.println("weekday==== " + weekday);
                                                                                uploadMap.put(week_day.indexOf(days), ++weekday);
                                                                                upFile++;
                                                                            } else {
                                                                                int temp = Integer.parseInt(status);
                                                                                if (temp > 0) {
                                                                                    downFile = downFile + temp;
                                                                                }
                                                                            }

                                                                        }

                                                                    }

                                                                }

                                                            }

                                                        }


                                                    }
                                                }


                                                current_day = date1.toString().substring(0, 3);
                                                current_index = week_day.indexOf(current_day);
                                                current_index = current_index + 1;
                                                System.out.println("current_index============= " + current_index);
                                                System.out.println("uploadmap==== " + uploadMap);
                                                System.out.println("downloadmap==== " + downloadMap);
                                                Set keys = uploadMap.keySet();
                                                Set keys1 = downloadMap.keySet();
                                                strXML1 += "<categories>";
                                                int temp = 0;
                                                int i = current_index;
                                                while (temp < 8) {


                                                    temp++;
                                                    if (i < 7) {
                                                        System.out.println("days========= " + week_day.get(i));
                                                        strXML1 += "<category label='" + week_day.get(i) + "'/>";
                                                        i++;
                                                    } else {
                                                        i = 0;

                                                    }
                                                }
                                                strXML1 += "</categories>";

                                                strXML1 += "<dataset seriesName='Upload' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
                                                temp = 0;
                                                i = current_index;
                                                while (temp < 8) {
                                                    temp++;
                                                    if (i < 7) {
                                                        System.out.println("value-= " + uploadMap.get(i));
                                                        strXML1 += "<set value='" + uploadMap.get(i) + "'/>";
                                                        i++;
                                                    } else {
                                                        i = 0;

                                                    }
                                                }

                                                strXML1 += "</dataset>";

                                                strXML1 += "<dataset seriesName='Download' color='F1683C' anchorBorderColor='F1683C' anchorBgColor='F1683C'>";
                                                temp = 0;
                                                i = current_index;
                                                while (temp < 8) {
                                                    temp++;
                                                    if (i < 7) {
                                                        System.out.println("value-= " + uploadMap.get(i));
                                                        strXML1 += "<set value='" + downloadMap.get(i) + "'/>";
                                                        i++;
                                                    } else {
                                                        i = 0;

                                                    }
                                                }

                                                strXML1 += "</dataset>";

                                                strXML1 += "<styles>";
                                                strXML1 += "<definition>";
                                                strXML1 += "<style name='CaptionFont' type='font' size='12'/>";
                                                strXML1 += "</definition>";
                                                strXML1 += "<application>";
                                                strXML1 += "<apply toObject='CAPTION' styles='CaptionFont'/>";
                                                strXML1 += "<apply toObject='SUBCAPTION' styles='CaptionFont'/>";
                                                strXML1 += "</application>";
                                                strXML1 += "</styles>";
                                                strXML1 += "</chart>";

                                                System.out.println(strXML1);

                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                        %>
                        <table width="98%" border="0" cellspacing="0" cellpadding="3" align="center">

                            <br class="clear"/> <center> <jsp:include page="charts/Includes/FusionChartsRenderer.jsp" flush="true">
                                    <jsp:param name="chartSWF" value="charts/FusionCharts/MSLine.swf" />
                                    <jsp:param name="strURL" value="" />
                                    <jsp:param name="strXML" value="<%= strXML1%>" />
                                    <jsp:param name="chartId" value="UDstatus" />
                                    <jsp:param name="chartWidth" value="900" />
                                    <jsp:param name="chartHeight" value="550" />
                                    <jsp:param name="debugMode" value="false" />
                                    <jsp:param name="registerWithJS" value="true" />
                                </jsp:include></center>


                     <!--       <script type="text/javascript">
                                //Initialize Batch Exporter with DOM Id as fcBatchExporter
                                var myExportComponent = new FusionChartsExportObject("fcBatchExporter", "charts/FusionCharts/FCExporter.swf");

                                //Add the charts to queue. The charts are referred to by their DOM Id.
                                myExportComponent.sourceCharts = ['UDstatus'];

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
                                myExportComponent.componentAttributes.defaultExportFileName = 'Upload_Download';
                                //Width and height
                                myExportComponent.componentAttributes.width = '300';
                                myExportComponent.componentAttributes.height = '60';
                                //Title of button
                                myExportComponent.componentAttributes.btnsavetitle = 'Save chart';
                                myExportComponent.componentAttributes.btndisabledtitle = 'click on button';

                                //Render the exporter SWF in our DIV fcexpDiv                                
                                myExportComponent.Render("fcexpDiv");
                            </script>

-->


                        </table>

                    </div>
                </div>
                <jsp:include page="../common/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
