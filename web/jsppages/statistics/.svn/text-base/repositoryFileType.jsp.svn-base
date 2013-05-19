<%--
    Document   : repositoryFileType
    Created on : 14 Sep, 2011, 4:04:57 PM
    Author     : smp
--%>

<%@page import="java.lang.String"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%!    Map<String, Integer> map = new HashMap<String, Integer>();

    public void filesInsideDirectory(File file) {
        map.clear();
        if (file.isDirectory()) {
            File[] insideFile = file.listFiles();
            for (int i = 0; i < insideFile.length; i++) {
                if (insideFile[i].isDirectory()) {
                    filesInsideDirectory1(insideFile[i]);

                } else if (insideFile[i].isFile()) {
                    String extensn = (insideFile[i].toString().toLowerCase().substring(insideFile[i].toString().lastIndexOf(".")));
                    if (map.containsKey(extensn)) {
                        int temp = map.get(extensn);
                        map.put(extensn, temp + 1);
                    } else {
                        map.put(extensn, 1);
                    }

                }
            }
        }
    }

    public void filesInsideDirectory1(File file) {

        if (file.isDirectory()) {
            File[] insideFile = file.listFiles();
            for (int i = 0; i < insideFile.length; i++) {
                if (insideFile[i].isDirectory()) {
                    filesInsideDirectory1(insideFile[i]);

                } else if (insideFile[i].isFile()) {
                    String extensn = (insideFile[i].toString().toLowerCase().substring(insideFile[i].toString().lastIndexOf(".")));
                    if (map.containsKey(extensn)) {
                        int temp = map.get(extensn);
                        map.put(extensn, temp + 1);
                    } else {
                        map.put(extensn, 1);
                    }
                }
            }
        }
    }
%>

<%






            System.out.println(map);
//System.out.println("yahooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
            String strXML = "";
            int c1 = 0;
            String animateChart = null;
            animateChart = request.getParameter("animate");
            //Set default value of 1
            if (null == animateChart || animateChart.equals("")) {
                animateChart = "1";
            }
            String FolderDivision = request.getParameter("folderDiv");
          //  System.out.println("Folder division "+FolderDivision);
            String repo=(String) session.getAttribute("repo"+FolderDivision.trim());
          //  System.out.println("repo is "+repo);
            FolderDivision=repo;

            String directory = FolderDivision.toString().substring(FolderDivision.toString().lastIndexOf("/") + 1);
            System.out.println("director="+directory);
            File file = new File(FolderDivision);
            strXML = "<chart caption='Number of files in each Scheme " + directory + "'showSum='1'   xAxisName='File Types' yAxisName='No. of Files' showBorder='0' bgcolor='#F0F0F0' formatNumberScale='2'  Unitsanimation='" + animateChart + "' decimalPrecision='0' formatNumberScale='0'  rotatevalues='0' placevaluesinside='1' labelDisplay='Rotate' slantLabels='1' exportEnabled='1' exportAtClient='1' exportHandler='fcBatchExporter'    showToolTipShadow='1'  showAboutMenuItem='0'>";
            filesInsideDirectory(file);
            Set keys = map.keySet();
            for (Iterator i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                strXML += "<set label='" + key + "' value='" + map.get(key) + "'/>";
            }
            strXML += "</chart>";


%>
<%=strXML%>


