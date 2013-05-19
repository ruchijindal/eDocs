/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.controller;

//import com.smp.entity.Billrepository;
import com.smp.entity.Master;
//import com.smp.entity.Sewerdetails;
//import com.smp.jal.BillUtility;
import com.smp.gda.ConvertToDate;
import com.smp.manager.SearchManager;
import com.smp.session.MasterFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.smp.entity.Scheme;
import com.smp.gda.FileGeneric;
import com.smp.gda.PdfHandler;
import com.smp.manager.FileManager;
import com.smp.session.SchemeFacade;
import java.text.DecimalFormat;
import java.io.PrintWriter;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author smp-06
 */
@WebServlet(name = "SearchController", urlPatterns = {"/RepositoryOverview", "/checkPropertyId", "/CurrentUser", "/GetCategory", "/addallottee", "/SetValues", "/SelectRecord", "/searchRecord", "/SearchController", "/AllotteeDetails", "/checkPropId", "/checkPropNum", "/AddScheme", "/ConsumerDetails", "/UpdateAllotteeDetails", "/deleteAllottee", "/UpdateSewerDetails", "/SelectSector", "/ViewPdf", "/updateChallan", "/deleteChallan", "/AddAllottee", "/DownloadPDF", "/UploadPDF", "/MergePDF", "/deleteFile", "/replacePDF", "/expandDir", "/backDir", "/showDetail", "/DownloadDir", "/createDir", "/moveFile", "/reNameFile", "/copyFile", "/findfile", "/DownloadAllfiles", "/Pdfviewer"})
public class SearchController extends HttpServlet {

    @PersistenceContext(unitName = "SMPDocManPU")
    EntityManager em;
    @EJB
    SearchManager searchManager;
    @EJB
    MasterFacade masterFacade;
    @EJB
    FileManager fileManager;
    @EJB
    SchemeFacade schemeFacade;
    Master master = new Master();
    ConvertToDate convertToDate = new ConvertToDate();
    String schemeName;
    String pId;
    String pCode;
    String pNumber;
    String aName;
    String fName;
    String Address;
    String sCode;
    String fCost;
    String pType;
    String aDate;
    String rDate;
    String pDate;
    String catCode;
    int flag = 0;
    ServletOutputStream sos = null;
    int t, t1, t2;
    List<FileGeneric> fileList = new ArrayList<FileGeneric>();
    DecimalFormat df = new DecimalFormat("0.0");
    ArrayList<Master> list = new ArrayList<Master>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        HttpSession session = request.getSession();
        String alertMsg = null;
        if (session.getAttribute("userrole") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        try {
            String path = request.getServletPath();

            if (path.equals("/SearchController")) {

                List<Master> schemeCode = em.createNamedQuery("Master.findByTotalSchemeCode").getResultList();
                List<Master> schemeName = em.createNamedQuery("Master.findByTotalSchemeName").getResultList();

                System.out.println("catCode" + catCode);
                System.out.println("schemeName" + schemeName);
                if (schemeCode.contains(null)) {
                    schemeCode.remove(null);
                }

                if (schemeName.contains(null)) {
                    schemeName.remove(null);
                }
                System.out.println("schemeName after remove null" + schemeName);

                request.setAttribute("schemeCode", schemeCode);
                request.setAttribute("schemeCodeLength", schemeCode.size());
                request.setAttribute("schemeName", schemeName);
                request.setAttribute("schemeNameLength", schemeName.size());
                request.getServletContext().getRequestDispatcher("/jsppages/search/search.jsp").forward(request, response);
            }
            if (path.equals("/GetCategory")) {
                out = response.getWriter();
                try {
                    String scheme_Code;
                    List<String> catCodeList = new ArrayList<String>();
                    catCodeList.clear();
                    String schemeCode = request.getParameter("schemeCode");
                    if (schemeCode.equals("")) {
                        scheme_Code = "";
                    } else {
                        scheme_Code = schemeCode;
                    }
                    catCodeList = em.createNamedQuery("Master.findByDisctinctSchemeCode").setParameter("schemeCode", scheme_Code).getResultList();

                    String catCode = "";
                    for (int i = 0; i < catCodeList.size(); i++) {
                        if (catCodeList.size() - 1 == i) {
                            catCode = catCode + catCodeList.get(i);
                        } else {
                            catCode = catCode + catCodeList.get(i) + ":";
                        }


                    }
                    out.println(catCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (path.equals("/RepositoryOverview")) {
                response.sendRedirect(request.getContextPath() + "/jsppages/statistics/repositoryOverview.jsp");
            } else if (path.equals("/RepositoryOverview")) {
                response.sendRedirect(request.getContextPath() + "/jsppages/statistics/udStatus.jsp");
            } else if (path.equals("/Pdfviewer")) {

                String file_name = request.getParameter("filename");
                String parent_dir = request.getParameter("parentPath");
                try {
                    String dirpath = request.getParameter("parentPath") + "/" + file_name;
                    File f = new File(dirpath);
                    if (f.exists()) {

                        String dest = request.getServletContext().getRealPath("/") + "swf/" + file_name.substring(0, file_name.indexOf(".")) + ".swf";
                        String[] split = dest.split("edocs");
                        //String[] split1=file_name
                        dest = split[0] + "SMPDocMan/web/swf/" + file_name.substring(0, file_name.lastIndexOf(".")) + ".swf";
                        // dest=split[0]+"SMPDocMan/web/swf/"+"doc.swf";

                        dest = "/home/smp/glassfish-3.0.1/glassfish/domains/domain1/docroot/" + file_name.substring(0, file_name.indexOf(".")) + ".swf";
                        // dest="/home/smp/dep/"+file_name.substring(0, file_name.indexOf("."))+".swf";
                        System.out.println(dirpath + "  in controler------------  " + dest + " Servletcontext " + request.getServletContext().getContextPath());
                        PdfHandler pdfHandler = new PdfHandler(dirpath, dest, session);
                        int k = 0;
                        File ff = new File(dest);
                        while (pdfHandler.t.isAlive() || !ff.exists()) {
                            System.out.println("thread " + (k++));
                            Thread.currentThread().sleep(1000);
                        }
                        //pdfHandler.pdfToswf(dirpath,dest);
                        //  session.setAttribute("pdfname", file_name.substring(0, file_name.indexOf("."))+".swf");
                        session.setAttribute("pdfname", file_name.substring(0, file_name.indexOf(".")) + ".swf");
                        //request.getServletContext().getRequestDispatcher("/jsppages/common/viewer.jsp").forward(request, response);
                        response.sendRedirect(request.getContextPath() + "/jsppages/common/viewer.jsp");
                    }
                } catch (Exception ee) {
                    System.out.println("Exception in search Controler" + ee);
                }

            } else if (path.equals("/addallottee")) {
                String t = request.getParameter("t");
                if (t == null) {
                    t = "0";
                }
                System.out.println("inside ddalootee.......");
                List<Scheme> schemeList = new ArrayList<Scheme>();
                schemeList = schemeFacade.findAll();
                // System.out.println("inside ddalootee.......((" + schemeList.size());
                session.setAttribute("schemeLength", schemeList.size());
                session.setAttribute("schemeList", schemeList);
                //System.out.println("in add alottee path");
                request.setAttribute("t", t);
                request.getServletContext().getRequestDispatcher("/jsppages/allottee/addAllottee.jsp").forward(request, response);

            } else if (path.equals("/CurrentUser")) {
                System.out.println("in current user controler");
                //   request.getServletContext().getRequestDispatcher("/jsppages/user/userInfo.jsp").forward(request, response);
                request.getServletContext().getRequestDispatcher("/jsppages/user/userInfo.jsp").forward(request, response);
                //response.sendRedirect("/SMPDocMan/jsppages/user/userInfo.jsp");
            } else if (path.equals("/AllotteeDetails")) {

                //  System.out.println("In Allottee Details ok ...................");
                String propcode = request.getParameter("propCode");
                String propid = request.getParameter("propId");
                Master record = new Master();
                //    System.out.println("prop id " + propid + " propcode " + propcode);
                Query query = em.createNamedQuery("Master.findByPropCodeAndPropId");
                query.setParameter("propCode", Integer.parseInt(propcode));
                query.setParameter("propId", Integer.parseInt(propid));
                record = (Master) query.getSingleResult();
                session.setAttribute("AllotteeDetail", record);
                request.setAttribute("t", 0);
                request.setAttribute("t1", 0);
                request.setAttribute("t2", 0);
                request.setAttribute("td", 0);
                String repPath = session.getAttribute("repositoryPath").toString();
                System.out.println(File.separator + "  rep Path is " + repPath);
                String dirpath = repPath + record.getSchemeCode() + "-" + record.getCatCode() + File.separator + record.getCatCode() + "-" + propid.trim();
                File checkfile = new File(dirpath);
                if (!checkfile.exists()) {
                    session.removeAttribute("dircreation");
                    session.setAttribute("dircreation", 2);
                } else {
                    session.removeAttribute("dircreation");
                    session.setAttribute("dircreation", 1);
                    session.setAttribute("rootPath", dirpath);
                    fileList = fileManager.getFileList(dirpath);

                    String pathName = dirpath.substring(dirpath.indexOf(record.getSchemeCode() + "-" + record.getCatCode()));
                    String totalSize = fileManager.getFolderSize(fileList);
                    int totalFile = fileManager.totalFile(fileList);
                    session.setAttribute("totalSize", totalSize);
                    session.setAttribute("totalFile", totalFile);
                    session.setAttribute("fileList", fileList);
                    session.setAttribute("dirLength", fileList.size());
                    session.setAttribute("pathName", pathName);
                    //  Calendar calendar=Calendar.getInstance();
                }
                request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);
            } else if (path.equals("/SetValues")) {
                //System.out.println("in /setvalues record");
                String value = request.getParameter("value");
                String item = request.getParameter("sym");
                // System.out.println("value-->"+value);
                // System.out.println("sym-->"+item);
                if (item.equals("s")) {
                    session.setAttribute("sector", value);
                } else if (item.equals("b")) {
                    session.setAttribute("block", value);
                    //System.out.println("block{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{----->"+ value);

                } else if (item.equals("con_t")) {

                    session.setAttribute("con_tp", value);
                    // System.out.println("coonection type{{{{{{{{{{{{{{{{{{{----->"+ value);

                } else if (item.equals("con_n_f")) {

                    session.setAttribute("cons_no_fr", value);
                    // System.out.println("consumer no from ----->"+ value);

                } else if (item.equals("con_n_t")) {

                    session.setAttribute("cons_no_to", value);
                    //System.out.println("consumer no from----->"+ value);
                } else if (item.equals("bill_to")) {

                    session.setAttribute("billdate", value);

                } else if (item.equals("due")) {

                    session.setAttribute("duedate1", value);
                    //System.out.println("consumer no from----->"+ value);
                } else {
                }

            } else if (path.equals("/SelectRecord")) {
                List<Master> records = new ArrayList<Master>();
                List<Master> masterRecords = new ArrayList<Master>();

                String sCode = request.getParameter("sCode");
                System.out.println("sCode-----------------> " + sCode);
                String cCode = request.getParameter("cCode");
                System.out.println("cCode-----------------> " + cCode);
                String schemeName = request.getParameter("schemeName");
                System.out.println("schemeName-----------------> " + schemeName);
                String pCode = request.getParameter("pCode");
                System.out.println("pCode-----------------> " + pCode);
                String propertyNo = request.getParameter("propertyNo");
                System.out.println("propertyNo-----------------> " + propertyNo);
                String propertyId = request.getParameter("propertyId");
                System.out.println("propertyId-----------------> " + propertyId);
                String alloteeName = request.getParameter("alloteeName");
                System.out.println("alloteeName-----------------> " + alloteeName);

                masterRecords = searchManager.searchByAll(sCode, cCode, schemeName, pCode, propertyNo, propertyId, alloteeName);
                if (masterRecords.isEmpty()) {
                    request.setAttribute("flag", 1);
                    request.getServletContext().getRequestDispatcher("/SearchController").forward(request, response);
                } else {
                    request.setAttribute("masterRecords", masterRecords);
                    request.setAttribute("masterRecordsSize", masterRecords.size());
                    request.getServletContext().getRequestDispatcher("/jsppages/search/search_result.jsp").forward(request, response);
                }


            } else if (path.equals("/searchRecord")) {
                System.out.println("inside searchRecord");
                List<Master> masterRecords = new ArrayList<Master>();
                String aDate = request.getParameter("aDate");
                String aDate1 = request.getParameter("aDate1");
                String rDate = request.getParameter("rDate");
                String rDate1 = request.getParameter("rDate1");
                String pDate = request.getParameter("pDate");
                String pDate1 = request.getParameter("pDate1");
                masterRecords = searchManager.searchByDate(aDate, aDate1, rDate, rDate1, pDate, pDate1);

                System.out.println("masterRecords1========>> " + masterRecords);
                if (masterRecords.isEmpty()) {
                    request.setAttribute("tempFlag", 1);
                    request.getServletContext().getRequestDispatcher("/SearchController").forward(request, response);
                } else {
                    request.setAttribute("masterRecords", masterRecords);
                    request.setAttribute("masterRecords1Size", masterRecords.size());
                    request.getServletContext().getRequestDispatcher("/jsppages/search/search_result.jsp").forward(request, response);
                }

            } else if (path.equals("/AddAllottee")) {
                schemeName = request.getParameter("sname");
                pId = request.getParameter("pId");
                pCode = request.getParameter("pCode");
                catCode = request.getParameter("catCode");
                pNumber = request.getParameter("pNumber");
                aName = request.getParameter("aName");
                fName = request.getParameter("fName");
                Address = request.getParameter("Address");
                sCode = request.getParameter("sCode");
                fCost = request.getParameter("fCost");
                pType = request.getParameter("pType");
                aDate = request.getParameter("aDate");
                rDate = request.getParameter("rDate");
                pDate = request.getParameter("pDate");

                String createdBy = (String) session.getAttribute("userid");
                Date createdDate = new Date();
                System.out.println("In AddAllottee Controller");
                Date adate = new Date();
                Date pdate = new Date();
                Date rdate = new Date();
                System.out.println("aDate " + aDate + " " + rDate + "  " + pDate);
                try {
                    if (!(aDate.equals("") || aDate.equals("__/__/__"))) {
                        adate = convertToDate.convertStringToDate(aDate);
                    }
                    if (!(pDate.equals("") || aDate.equals("__/__/__"))) {
                        pdate = convertToDate.convertStringToDate(pDate);
                    }
                    if (!(rDate.equals("") || aDate.equals("__/__/__"))) {
                        rdate = convertToDate.convertStringToDate(rDate);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    master.setSchemeName(schemeName);
                    master.setPropId(Integer.parseInt(pId));
                    master.setPropCode(Integer.parseInt(pCode));
                    master.setPropNumber(Integer.parseInt(pNumber));
                    master.setSchemeCode(sCode.trim());
                    master.setCatCode(catCode.trim());
                    master.setAlloteeName(aName);
                    master.setFatherName(fName);
                    master.setAddress(Address);
                    master.setPropertyType(pType);
                    //  master.setSectionName("");
                    //   master.setEstimationCost(null); //manual
                    master.setFianlCost(Integer.parseInt(fCost));
                    master.setAllotteeDate(adate);
                    master.setPossessionDate(pdate);
                    master.setRegistryDate(rdate);
                    masterFacade.create(master);
                    try {
                        String repositorydir = (String) session.getAttribute("repositoryPath");
                        File file = new File(repositorydir + "" + sCode + "-" + catCode);
                        if (!file.exists()) {
                            boolean b = file.mkdir();
                            if (b == true) {

                                file = new File(repositorydir + "" + sCode + "-" + catCode + file.separator + catCode + "-" + pId);
                                boolean subflag = file.mkdir();
                                if (subflag == true) {
                                    session.removeAttribute("dircreation");
                                    session.setAttribute("dircreation", 1);
                                }

                            } else {
                                session.removeAttribute("dircreation");
                                session.setAttribute("dircreation", 2);
                            }
                        } else {
                            file = new File(repositorydir + "" + sCode + "-" + catCode + file.separator + catCode + "-" + pId);
                            if (!file.exists()) {
                                boolean subflag = file.mkdir();
                                if (subflag == true) {
                                    session.removeAttribute("dircreation");
                                    session.setAttribute("dircreation", 1);
                                } else {
                                    session.removeAttribute("dircreation");
                                    session.setAttribute("dircreation", 2);
                                }
                            }
                        }

                    } catch (Exception ee) {
                        session.removeAttribute("dircreation");
                        session.setAttribute("dircreation", 2);
                    }
                    session.setAttribute("AllotteeDetail", master);
                    String repPath = session.getAttribute("repositoryPath").toString();
                    System.out.println(File.separator + "  rep Path is " + repPath);
                    String dirpath = repPath + master.getSchemeCode() + "-" + master.getCatCode() + File.separator + master.getCatCode() + "-" + pId.trim();
                    File checkfile = new File(dirpath);
                    if (!checkfile.exists()) {
                        session.removeAttribute("dircreation");
                        session.setAttribute("dircreation", 2);
                    } else {
                        session.removeAttribute("dircreation");
                        session.setAttribute("dircreation", 1);
                        session.setAttribute("rootPath", dirpath);
                        fileList = fileManager.getFileList(dirpath);

                        String pathName = dirpath.substring(dirpath.indexOf(master.getSchemeCode() + "-" + master.getCatCode()));
                        String totalSize = fileManager.getFolderSize(fileList);
                        int totalFile = fileManager.totalFile(fileList);
                        session.setAttribute("totalSize", totalSize);
                        session.setAttribute("totalFile", totalFile);
                        session.setAttribute("fileList", fileList);
                        session.setAttribute("dirLength", fileList.size());
                        session.setAttribute("pathName", pathName);
                        //  Calendar calendar=Calendar.getInstance();
                    }
                    master = new Master();
                    String pId;
                    schemeName = "";
                    pCode = "";
                    pNumber = "";
                    aName = "";
                    fName = "";
                    Address = "";
                    sCode = "";
                    fCost = "";
                    pType = "";
                    aDate = "";
                    rDate = "";
                    pDate = "";
                    catCode = "";
                    t1 = 1;
                    request.removeAttribute("t1");
                    request.setAttribute("t1", t1);

                    // response.sendRedirect(request.getContextPath() + "/addallottee?t=2");
                    request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);
                } catch (Exception e) {
                    t = 1;
                    request.setAttribute("t", t);
                    request.getServletContext().getRequestDispatcher("/jsppages/allottee/addAllottee.jsp").forward(request, response);
                }
            } else if (path.equals("/checkPropertyId")) {
                System.out.println("inside checkPropertyId");

                if ((!request.getParameter("propId").equals("")) && (!request.getParameter("catCode").equals("")) && (!request.getParameter("schemeCode").equals(""))) {
                    int propId = Integer.parseInt(request.getParameter("propId"));
                    String schemeCode = request.getParameter("schemeCode");
                    String catCode = request.getParameter("catCode");
                    System.out.println("inside checkPropertyId" + catCode + propId + schemeCode);


                    List<Master> masterList = em.createNamedQuery("Master.findBySchemeCodeAndCatCodeAndPropId").setParameter("schemeCode", schemeCode).setParameter("catCode", catCode).setParameter("propId", propId).getResultList();
                    if (!masterList.isEmpty()) {
                        flag = 1;
                        out = response.getWriter();
                        out.println(flag);
                    } else {
                        flag = 0;
                        out = response.getWriter();
                        out.println(flag);
                    }
                }

            } else if (path.equals("/checkPropId")) {

                if ((!request.getParameter("propId").equals("")) && (!request.getParameter("catCode").equals("")) && (!request.getParameter("sCode").equals(""))) {

                    int propId = Integer.parseInt(request.getParameter("propId"));
                    String scode = request.getParameter("sCode");
                    String ccode = request.getParameter("catCode");
                    System.out.println("propId " + propId + " scode " + scode + " ccode " + ccode);
                    Query query = em.createNamedQuery("Master.findBySchemeCodeAndCatCodeAndPropId");
                    query.setParameter("propId", propId);
                    query.setParameter("schemeCode", scode);
                    query.setParameter("catCode", ccode);
                    List<Master> masterList = query.getResultList();
                    if (!masterList.isEmpty()) {
                        flag = 1;
                        out = response.getWriter();
                        out.println(flag);
                    } else {
                        flag = 0;
                        out = response.getWriter();
                        out.println(flag);
                    }
                }

            } else if (path.equals("/checkPropNum")) {

                if (request.getParameter("propNum") != null) {
                    int propNum = Integer.parseInt(request.getParameter("propNum"));
                    List<Master> masterList = em.createNamedQuery("Master.findByPropNumber").setParameter("propNumber", propNum).getResultList();
                    if (!masterList.isEmpty()) {
                        flag = 1;
                        out = response.getWriter();
                        out.println(flag);
                    } else {
                        flag = 0;
                        out = response.getWriter();
                        out.println(flag);
                    }
                }

            } else if (path.equals("/AddScheme")) {
                String schemeName = request.getParameter("schemeName");

                try {
                    if ((schemeName != null) || (!schemeName.equals(""))) {
                        Scheme scheme = new Scheme();
                        scheme.setName(schemeName);

                        schemeFacade.create(scheme);
                        scheme = new Scheme();
                        t = 2;
                        request.setAttribute("t", t);
                        request.getServletContext().getRequestDispatcher("/addallottee").forward(request, response);
                        //   request.getServletContext().getRequestDispatcher("/jsppages/allottee/addAllottee.jsp").forward(request, response);


                    }
                } catch (Exception e) {
                    System.out.println("Exception in add Scheme" + e);
                    t = 1;
                    request.setAttribute("t", t);
                    request.getServletContext().getRequestDispatcher("/jsppages/allottee/addAllottee.jsp").forward(request, response);

                }


            } else if (path.equals("/UpdateAllotteeDetails")) {
                rDate = "";
                pDate = "";
                rDate = "";
                pId = request.getParameter("propId");
                pCode = request.getParameter("propCode");
                catCode = request.getParameter("catCode");
                pNumber = request.getParameter("propNumber");
                aName = request.getParameter("allotteeName");
                fName = request.getParameter("fatherName");
                Address = request.getParameter("address");
                sCode = request.getParameter("schemeCode");
                fCost = request.getParameter("finalCost");
                pType = request.getParameter("propType");
                aDate = request.getParameter("allotDate");
                rDate = request.getParameter("regDate");
                pDate = request.getParameter("possessionDate");
                String id = request.getParameter("id");
                Date adate = null;
                Date pdate = null;
                Date rdate = null;
                System.out.println("Allottee Date in Update Allotee Details is " + aDate + " " + rDate + " " + pDate);
                try {
                    if (!aDate.equals("")) {
                        adate = convertToDate.convertStringToDate(aDate);
                    } else {
                        adate = null;
                    }
                    if (!rDate.equals("")) {
                        rdate = convertToDate.convertStringToDate(rDate);
                    } else {
                        rdate = null;
                    }
                    if (!pDate.equals("")) {
                        pdate = convertToDate.convertStringToDate(pDate);
                    } else {
                        pdate = null;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                master = new Master();
                master = masterFacade.find(Integer.parseInt(id));
                master.setPropId(Integer.parseInt(pId));
                master.setPropCode(Integer.parseInt(pCode));
                master.setPropNumber(Integer.parseInt(pNumber));
                master.setSchemeCode(sCode.trim());
                master.setCatCode(catCode.trim());
                master.setAlloteeName(aName);
                master.setFatherName(fName);
                master.setAddress(Address);
                master.setPropertyType(pType);
                //  master.setSectionName("");
                //  master.setEstimationCost(null); //manual
                master.setFianlCost(Integer.parseInt(fCost));
                master.setAllotteeDate(adate);
                master.setPossessionDate(pdate);
                master.setRegistryDate(rdate);
                masterFacade.edit(master);
                request.setAttribute("t", 1);
                session.setAttribute("AllotteeDetail", master);
                String repPath = session.getAttribute("repositoryPath").toString();
                System.out.println("rep Path is " + repPath);
                String dirpath = repPath + sCode.trim() + "-" + catCode.trim() + File.separator + catCode.trim() + "-" + pId.trim();
                String roothPath = (String) session.getAttribute("rootPath");
                File oldFile = new File(roothPath);
                File newFile = new File(dirpath);
                boolean success = oldFile.renameTo(newFile);
                System.out.println("sucess==>" + success);
                session.setAttribute("rootPath", dirpath);
                fileList.clear();
                fileList = fileManager.getFileList(dirpath);

                String pathName = dirpath.substring(dirpath.indexOf(sCode.trim() + "-" + catCode.trim()));
                String totalSize = fileManager.getFolderSize(fileList);
                int totalFile = fileManager.totalFile(fileList);
                session.setAttribute("totalSize", totalSize);
                session.setAttribute("totalFile", totalFile);
                session.setAttribute("fileList", fileList);
                session.setAttribute("dirLength", fileList.size());
                session.setAttribute("pathName", pathName);


                request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);

            } else if (path.equals("/deleteAllottee")) {
                master = new Master();
                String allotteeId = request.getParameter("id");
                master = masterFacade.find(Integer.parseInt(allotteeId));
                if (master == null) {
                    t2 = 2;
                } else {
                    masterFacade.remove(master);
                    t2 = 1;
                }
                request.setAttribute("delete", t2);
                request.getServletContext().getRequestDispatcher("/SearchController").forward(request, response);

            } //            else if (path.equals("/ViewPdf")) {
            //                String propertyId = request.getParameter("propId");
            //                String catCode = request.getParameter("catCode");
            //                String schemeCode = request.getParameter("schemeCode");
            //                System.out.println("property id is " + propertyId + " cat Code " + catCode + " scheme code " + schemeCode);
            //                try {
            //                    path = "/home/smp/GDA/" + schemeCode.trim() + "-" + catCode.trim() + "/" + catCode.trim() + "-" + propertyId.trim() + ".pdf";
            //                    System.out.println(path);
            //                    File file = new File(path);
            //                    int size = (int) file.length();
            //                    if (size > Integer.MAX_VALUE) {
            //                    }
            //                    FileInputStream fis = null;
            //                    BufferedInputStream bis = null;
            //                    if (file.exists()) {
            //                        fis = new FileInputStream(path);
            //                        bis = new BufferedInputStream(fis);
            //                        response.setContentLength(size);
            //                        response.setHeader("Content-Disposition", " filename=" + schemeCode.trim() + "-" + catCode.trim());
            //                        response.setContentType("application/pdf");
            //                        sos = response.getOutputStream();
            //                        int ch;
            //                        while ((ch = bis.read()) != -1) {
            //                            sos.write((byte) ch);
            //                        }
            //                        sos.flush();
            //                    } else {
            //                        t = 3;
            //                        request.setAttribute("t", t);
            //                        request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);
            //
            //                    }
            //                } catch (Exception e) {
            //                    e.printStackTrace();
            //                }
            //            }
            else if (path.equals("/DownloadPDF")) {


                String filename = request.getParameter("filename");
                System.out.println("filename=>*******" + filename);


                try {
                    String dirpath = request.getParameter("parentPath") + "/" + filename;
                    File f = new File(dirpath);
                    if (f.exists()) {
                        int length = 0;
                        ServletOutputStream op = response.getOutputStream();
                        ServletContext context = getServletConfig().getServletContext();
                        String mimetype = context.getMimeType(filename);

                        //
                        //  Set the response and go!
                        //
                        //

                        xmlEntry(session, request, f.getName(), "downloaded");
                        response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
                        response.setContentLength((int) f.length());
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

                        //
                        //  Stream to the requester.
                        //
                        byte[] bbuf = new byte[8 * 1024];
                        DataInputStream in = new DataInputStream(new FileInputStream(f));

                        while ((in != null) && ((length = in.read(bbuf)) != -1)) {
                            op.write(bbuf, 0, length);
                        }

                        in.close();
                        op.flush();
                        op.close();
                    } else {
                        t = 2;
                        session.setAttribute("td", t);
                        session.setAttribute("alertMsg", "File does not exist!");
                        request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "File does not exist!");
                    request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);

                }
            } else if (path.equals("/replacePDF")) {

                String filename = request.getParameter("filename");
                System.out.println("replace  file................" + filename);
                String dirpath = null;
                String rootNode = (String) session.getAttribute("rootPath");

                if (request.getParameter("parentPath") == null || request.getParameter("parentPath").equals("")) {
                    dirpath = rootNode;

                } else {
                    dirpath = request.getParameter("parentPath");
                }


                System.out.println("dirpath in replacedir..==>" + dirpath);
                try {

                    File f1 = new File(dirpath);
                    File[] strFilesDirs = f1.listFiles();
                    int flag = 0;
                    for (int i = 0; i < strFilesDirs.length; i++) {
                        if (strFilesDirs[i].getName().equals(filename)) {
                            flag = 1;
                            break;
                        }
                    }
                    System.out.println("flag===>" + flag);
                    out = response.getWriter();
                    out.println(flag);


                } catch (Exception e) {

                    e.printStackTrace();
                }



            } else if (path.equals("/UploadPDF")) {


                String rootNode = (String) session.getAttribute("rootPath");
                String dirpath = null;

                if (request.getParameter("currentPath") == null || request.getParameter("currentPath").equals("")) {
                    dirpath = rootNode;
                } else {
                    dirpath = request.getParameter("currentPath");
                }

                String heading;
                String description;
                String filename = null;
                System.out.println("upload file");
                try {
                    ByteArrayOutputStream buffer = null;
                    ServletInputStream in = request.getInputStream();
                    System.out.println("in==>" + in);

                    byte[] line = new byte[128];
                    System.out.println("line=====> " + line);

                    int i = in.readLine(line, 0, 128);
                    System.out.println("i=====> " + i);
                    int boundaryLength = i - 2;
                    System.out.println("boundaryLength=====> " + boundaryLength);
                    String boundary = new String(line, 0, boundaryLength);

                    if (i > 0) {
                        while (i != -1) {
                            String newLine = new String(line, 0, i);
                            if (newLine.startsWith("Content-Disposition: form-data; name=\"uploadfile\"")) {

                                String s = new String(line, 0, i - 2);
                                System.out.println("s==========> " + s);

                                //We don't require file name.
                                int pos = s.indexOf("filename=\"");
                                if (pos != -1) {
                                    String filepath = s.substring(pos + 10, s.length() - 1);
                                    // Windows browsers include the full path on the client
                                    // But Linux/Unix and Mac browsers only send the filename
                                    // test if this is from a Windows browser

                                    pos = filepath.lastIndexOf("\"");
                                    if (pos != -1) {
                                        filename = filepath.substring(pos + 1);
                                    } else {
                                        filename = filepath;
                                    }
                                }
                                //this is the file content
                                i = in.readLine(line, 0, 128);
                                i = in.readLine(line, 0, 128);
                                // blank line
                                i = in.readLine(line, 0, 128);
                                buffer = new ByteArrayOutputStream();
                                newLine = new String(line, 0, i);
                                while (i != -1 && !newLine.startsWith(boundary)) {
                                    buffer.write(line, 0, i);
                                    i = in.readLine(line, 0, 128);
                                    newLine = new String(line, 0, i);
                                }

                            }
                            i = in.readLine(line, 0, 128);
                        }

                        String outputfile = dirpath + "/" + filename;
                        FileOutputStream fos = new FileOutputStream(outputfile);
                        fos.write(buffer.toByteArray());
                        fos.close();
                        t = 1;
                        session.setAttribute("td", t);

                        xmlEntry(session, request, filename, "Uploaded");
                        session.setAttribute("alertMsg", "File Successfully Uploaded !");
                        response.sendRedirect(request.getContextPath() + "/showDetail?currentDir=" + dirpath);

                    } else {
                        t = 2;
                        session.setAttribute("td", t);
                        session.setAttribute("alertMsg", " File Cannot be  Upload !");
                        response.sendRedirect(request.getContextPath() + "/showDetail?currentDir=" + dirpath);

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", " File Cannot be  Upload !");
                    response.sendRedirect(request.getContextPath() + "/showDetail?currentDir=" + dirpath);

                }



            } else if (path.equals("/MergePDF")) {

                String outputfile = null;
                System.out.println("inside mergePDF)))))))))))))");
                String filename = null;

                String rootNode = (String) session.getAttribute("rootPath");
                String dirpath = null;
                if (request.getParameter("parentPath") == null || request.getParameter("parentPath").equals("")) {
                    dirpath = rootNode;
                } else {
                    dirpath = request.getParameter("parentPath");
                }
                try {
                    String sfilename = request.getParameter("filename");
                    System.out.println("filename=====>" + sfilename);

                    ByteArrayOutputStream buffer = null;
                    ServletInputStream in = request.getInputStream();
                    System.out.println("in==>" + in);

                    byte[] line = new byte[128];
                    System.out.println("line=====> " + line);

                    int i = in.readLine(line, 0, 128);
                    System.out.println("i=====> " + i);
                    int boundaryLength = i - 2;
                    System.out.println("boundaryLength=====> " + boundaryLength);
                    String boundary = new String(line, 0, boundaryLength);


                    while (i != -1) {
                        String newLine = new String(line, 0, i);
                        if (newLine.startsWith("Content-Disposition: form-data; name=\"uploadfile\"")) {

                            String s = new String(line, 0, i - 2);
                            System.out.println("s==========> " + s);

                            //We don't require file name.
                            int pos = s.indexOf("filename=\"");
                            if (pos != -1) {
                                String filepath = s.substring(pos + 10, s.length() - 1);
                                // Windows browsers include the full path on the client
                                // But Linux/Unix and Mac browsers only send the filename
                                // test if this is from a Windows browser

                                pos = filepath.lastIndexOf("\"");
                                if (pos != -1) {
                                    filename = filepath.substring(pos + 1);
                                } else {
                                    filename = filepath;
                                }
                            }
                            //this is the file content
                            i = in.readLine(line, 0, 128);
                            i = in.readLine(line, 0, 128);
                            // blank line
                            i = in.readLine(line, 0, 128);
                            buffer = new ByteArrayOutputStream();
                            newLine = new String(line, 0, i);
                            while (i != -1 && !newLine.startsWith(boundary)) {
                                buffer.write(line, 0, i);
                                i = in.readLine(line, 0, 128);
                                newLine = new String(line, 0, i);
                            }

                        }
                        i = in.readLine(line, 0, 128);
                    }

                    outputfile = dirpath + "/" + "newpdf.pdf";
                    FileOutputStream fos = new FileOutputStream(outputfile);
                    fos.write(buffer.toByteArray());
                    fos.close();

                    String[] files = {dirpath + "/" + sfilename, outputfile};
                    Document PDFCombineUsingJava = new Document();
                    PdfCopy copy = new PdfCopy(PDFCombineUsingJava, new FileOutputStream(dirpath + "/" + "new.pdf"));
                    PDFCombineUsingJava.open();
                    PdfReader ReadInputPDF;
                    int number_of_pages;
                    for (i = 0; i < files.length; i++) {
                        ReadInputPDF = new PdfReader(files[i]);
                        number_of_pages = ReadInputPDF.getNumberOfPages();
                        for (int page = 0; page < number_of_pages;) {
                            copy.addPage(copy.getImportedPage(ReadInputPDF, ++page));
                        }
                    }
                    PDFCombineUsingJava.close();
                    File deletefile = new File(outputfile);
                    boolean flag1 = deletefile.delete();
                    System.out.println("flag1======>" + flag1);
                    File f1 = new File(dirpath + "/" + sfilename);
                    boolean success = f1.delete();
                    if (success) {
                        File f = new File(dirpath + "/" + "new.pdf");
                        f.renameTo(new File(dirpath + "/" + sfilename));

                        xmlEntry(session, request, sfilename, "Merged");
                    }
                    t = 1;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", " File Successfully Merged!");

                    response.sendRedirect(request.getContextPath() + "/showDetail?currentDir=" + dirpath);
                    // request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);

                } catch (Exception i) {
                    File deletefile = new File(outputfile);
                    deletefile.delete();
                    deletefile = new File(dirpath + "/" + "new.pdf");
                    deletefile.delete();
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "  There is some error.");
                    System.out.println(i);
                    response.sendRedirect(request.getContextPath() + "/showDetail?currentDir=" + dirpath);
                }


            } else if (path.equals("/deleteFile")) {
                String eString = "";
                try {
                    System.out.println("inside delete.......*********");
                    String sfilename = request.getParameter("selectedFiles");
                    String[] filenamelist = sfilename.split(":");
                    System.out.println("filename=====>" + sfilename);
                    String rootNode = (String) session.getAttribute("rootPath");
                    String dirpath = null;
                    List<String> errorListFile = new ArrayList<String>();
                    boolean flag = false;
                    if (session.getAttribute("parentNode") == null) {
                        dirpath = rootNode;
                    } else {
                        dirpath = (String) session.getAttribute("parentNode");
                    }
                    File deletefile;
                    System.out.println("dirpath=====>" + dirpath);
                    for (int i = 0; i < filenamelist.length; i++) {
                        deletefile = new File(dirpath + "/" + filenamelist[i]);
                        if (deletefile.isDirectory()) {
                            flag = fileManager.rmdir(deletefile);
                        } else {
                            flag = deletefile.delete();

                        }
                        if (flag == true) {

                            xmlEntry(session, request, deletefile.getName(), "deleted");
                        }
                        if (flag = false) {
                            errorListFile.add(filenamelist[i]);
                        }
                    }

                    if (errorListFile.size() > 0) {

                        for (int i = 0; i < errorListFile.size(); i++) {
                            eString += errorListFile.get(i) + " ";
                        }
                        t = 2;
                        session.setAttribute("td", t);
                        session.setAttribute("alertMsg", "Deletion Denied." + eString);
                    } else {

                        t = 1;
                        session.setAttribute("td", t);
                        session.setAttribute("alertMsg", "Successfully Deleted.");
                    }

                } catch (Exception e) {
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "Deletion Denied." + eString);
                    System.out.println(e);
                }


            } else if (path.equals("/expandDir")) {
                Master record = new Master();

                record = (Master) session.getAttribute("AllotteeDetail");
                String pathName = null;

                System.out.println("+++++++++++++expandDir");
                String parentNode = request.getParameter("parentNode");
                String dirName = request.getParameter("dirName");
                System.out.println("parentNode==>" + parentNode);
                System.out.println("dirName==>" + dirName);
                String dirpath;
                if (dirName.equals("root")) {
                    dirpath = parentNode;
                } else {

                    dirpath = parentNode + "/" + dirName;
                }
                String rootNode = (String) session.getAttribute("rootPath");
                if (rootNode.equals(dirpath)) {
                    session.removeAttribute("parentNode");
                } else {
                    session.setAttribute("parentNode", dirpath);
                }
                System.out.println("dirpath==> " + dirpath);
                pathName = dirpath.substring(dirpath.indexOf(record.getSchemeCode() + "-" + record.getCatCode()));
                session.setAttribute("pathName", pathName);
                fileList = fileManager.getFileList(dirpath);
                System.out.println("+++++++++++++size" + fileList.size());
                session.setAttribute("fileList", fileList);
                String totalSize = fileManager.getFolderSize(fileList);
                int totalFile = fileManager.totalFile(fileList);
                session.setAttribute("totalSize", totalSize);
                session.setAttribute("totalFile", totalFile);
                session.setAttribute("fileList", fileList);
                session.setAttribute("dirLength", fileList.size());
                //  Calendar calendar=Calendar.getInstance();

                request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);
            } else if (path.equals("/backDir")) {

                Master record = new Master();

                record = (Master) session.getAttribute("AllotteeDetail");
                String pathName = null;

                String parentNode = request.getParameter("parentNode");
                String dirpath = parentNode.substring(0, parentNode.lastIndexOf("/"));
                System.out.println("hello++++" + dirpath);

                String rootNode = (String) session.getAttribute("rootPath");
                if (rootNode.equals(dirpath)) {
                    session.removeAttribute("parentNode");
                } else {
                    session.setAttribute("parentNode", dirpath);
                }
                pathName = dirpath.substring(dirpath.indexOf(record.getSchemeCode() + "-" + record.getCatCode()));
                session.setAttribute("pathName", pathName);
                fileList = fileManager.getFileList(dirpath);
                session.setAttribute("fileList", fileList);
                String totalSize = fileManager.getFolderSize(fileList);
                int totalFile = fileManager.totalFile(fileList);
                session.setAttribute("totalSize", totalSize);
                session.setAttribute("totalFile", totalFile);
                session.setAttribute("fileList", fileList);
                session.setAttribute("dirLength", fileList.size());
                //  Calendar calendar=Calendar.getInstance();

                request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);
            } else if (path.equals("/showDetail")) {
                Master record = new Master();

                record = (Master) session.getAttribute("AllotteeDetail");
                String pathName = null;
                System.out.println("inside showDetailr..............");
                String rootNode = (String) session.getAttribute("rootPath");
                String dirpath = null;
                if (request.getParameter("currentDir") == null || request.getParameter("currentDir").equals("")) {
                    System.out.println("inside ifff");
                    dirpath = rootNode;

                } else {
                    dirpath = request.getParameter("currentDir");
                    System.out.println("inside else.........");
                }

                request.setAttribute("t", 0);
                request.setAttribute("t1", 0);
                request.setAttribute("t2", 0);

                fileList = fileManager.getFileList(dirpath);
                String totalSize = fileManager.getFolderSize(fileList);
                int totalFile = fileManager.totalFile(fileList);
                pathName = dirpath.substring(dirpath.indexOf(record.getSchemeCode() + "-" + record.getCatCode()));
                session.setAttribute("pathName", pathName);
                session.setAttribute("totalSize", totalSize);
                session.setAttribute("totalFile", totalFile);
                session.setAttribute("fileList", fileList);
                session.setAttribute("dirLength", fileList.size());
                request.getServletContext().getRequestDispatcher("/jsppages/allottee/allotteeDetail.jsp").forward(request, response);

            } else if (path.equals("/DownloadDir")) {
                try {

                    String dirPath = request.getParameter("parentPath") + "/" + request.getParameter("filename");
                    String filename = request.getParameter("filename");
                    OutputStream out1 = response.getOutputStream();
                    ZipOutputStream zip = new ZipOutputStream(out1);
                    final String VERSION_NR = "1.2";
                    final int COMPRESSION_LEVEL = 1;
                    zip.setComment("Created by jsp File Browser v. " + VERSION_NR);
                    zip.setLevel(COMPRESSION_LEVEL);

                    response.setContentType("application/octet-stream");
                    response.addHeader("Content-Disposition",
                            "attachment; filename=\"" + filename + ".zip\"");
                    //adding multiple files to zip

                    fileManager.addDir(new File(dirPath), filename, zip);

                    zip.flush();
                    zip.close();
                    out1.close();
                } catch (ZipException ex) {
                    System.out.println("zip exception");
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "There is some error.");
                } catch (Exception ex) {
                    System.out.println("exception");
                    ex.printStackTrace();
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "There is some error.");
                }


            } else if (path.equals("/createDir")) {
                System.out.println("create dir0000000");

                String pNode;
                boolean flag = false;
                int buttonId = Integer.parseInt(request.getParameter("flag"));
                if (session.getAttribute("parentNode") == null) {
                    pNode = "null";
                } else {
                    pNode = (String) session.getAttribute("parentNode");
                }
                String rNode = (String) session.getAttribute("rootPath");
                String dirName = request.getParameter("filename");
                String dirPath = null;
                if (pNode.equals("null")) {
                    dirPath = rNode;
                } else {
                    dirPath = pNode;
                }
                try {
                    flag = false;

                    File f = new File(dirPath + "/" + dirName);
                    if (buttonId == 1) {
                        flag = f.mkdir();

                    } else if (buttonId == 2) {
                        flag = f.createNewFile();

                    }
                    if (flag == true) {
                        System.out.println("inside if............");
                        t = 1;
                        if (buttonId == 1) {
                            xmlEntry(session, request, f.getName(), " Directory created");
                        } else if (buttonId == 2) {
                            xmlEntry(session, request, f.getName(), " file created");
                        }
                        session.setAttribute("td", t);
                        session.setAttribute("alertMsg", "Successfully Created!");

                    } else {
                        t = 2;
                        session.setAttribute("td", t);
                        session.setAttribute("alertMsg", "The name " + dirName + " is already used in current working directory.");

                    }
                    //request.getServletContext().getRequestDispatcher("/showDetail?currentDir=" + dirPath).forward(request, response);
                    // response.sendRedirect(request.getContextPath() + "/showDetail?currentDir=" + dirPath);

                } catch (Exception ex) {
                    System.out.println("exception");
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "The name " + dirName + " is already used in current working directory.");
                    //request.getServletContext().getRequestDispatcher("/showDetail?currentDir=" + dirPath).forward(request, response);
                    // response.sendRedirect(request.getContextPath() + "/showDetail?currentDir=" + dirPath);
                }


            } else if (path.equals("/moveFile")) {

                String parentNode = null;

                String rootNode = (String) session.getAttribute("rootPath");
                if (session.getAttribute("parentNode") == null) {
                    parentNode = rootNode;

                } else {
                    parentNode = (String) session.getAttribute("parentNode");
                }
                System.out.println("inside movefile==>");
                String destination = request.getParameter("destination");
                destination = destination.trim();
                destination = parentNode + "/" + destination;
                String source = request.getParameter("source");
                System.out.println("source=>" + source);
                String[] filenamelist = source.split(":");
                System.out.println("size===>" + filenamelist.length);
                File file;
                File dir;
                File chkDest;
                List<String> existFile = new ArrayList<String>();
                String existFileString = "";
                boolean success = false;
                t = 0;
                if (new File(destination).isDirectory()) {

                    try {
                        System.out.println("++++++++ size+ " + filenamelist.length);
                        for (int i = 0; i < filenamelist.length; i++) {
                            if (filenamelist[i].equals("") || filenamelist[i].equals("null")) {
                                break;
                            }


                            chkDest = new File(destination + "/" + filenamelist[i]);
                            if (chkDest.exists()) {
                                existFile.add(filenamelist[i]);

                            } else {
                                source = parentNode + "/" + filenamelist[i];
                                System.out.println("source==>" + source);

                                // File (or directory) to be moved
                                file = new File(source);

                                // Destination directory
                                dir = new File(destination);

                                // Move file to new directory
                                success = file.renameTo(new File(dir, file.getName()));
                                if (success) {
                                    t = 1;

                                } else {
                                    t = 2;

                                }
                            }
                        }
                        if (existFile.size() > 0) {
                            t = 3;
                            for (int i = 0; i < existFile.size(); i++) {
                                existFileString += existFile.get(i) + " ";
                            }
                        }

                        if (t == 1) {
                            session.setAttribute("td", 1);
                            session.setAttribute("alertMsg", "Successfully Moved!");
                        } else if (t == 2) {
                            session.setAttribute("td", 2);
                            session.setAttribute("alertMsg", "Moved Denied!");
                        } else if (t == 3) {
                            session.setAttribute("td", 2);
                            session.setAttribute("alertMsg", "Destination Directory already contain this file!" + existFileString);

                        } else {
                            session.setAttribute("td", 2);
                            session.setAttribute("alertMsg", "No files Selected! " + existFileString);

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        t = 2;
                        session.setAttribute("td", t);
                        session.setAttribute("alertMsg", "Moved Denied! ");

                    }
                } else {
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "Directory name is not valid !");


                }
            } else if (path.equals("/copyFile")) {

                String parentNode = null;

                String rootNode = (String) session.getAttribute("rootPath");
                if (session.getAttribute("parentNode") == null) {
                    parentNode = rootNode;

                } else {
                    parentNode = (String) session.getAttribute("parentNode");
                }
                System.out.println("inside copyfile==>");
                String destination = request.getParameter("destination");
                destination = destination.trim();
                destination = parentNode + "/" + destination;
                String source = request.getParameter("source");
                System.out.println("destination=>" + destination);

                String[] filenamelist = source.split(":");
                System.out.println("size===>" + filenamelist.length);
                File file;
                File dir;
                File chkDest;
                List<String> existFile = new ArrayList<String>();
                String existFileString = "";
                boolean success = false;
                t = 0;
                if (new File(destination).isDirectory()) {

                    try {
                        for (int i = 0; i < filenamelist.length; i++) {
                            if (filenamelist[i].equals("") || filenamelist[i].equals("null")) {
                                break;
                            }


                            chkDest = new File(destination + "/" + filenamelist[i]);
                            if (chkDest.exists()) {
                                existFile.add(filenamelist[i]);

                            } else {
                                source = parentNode + "/" + filenamelist[i];
                                System.out.println("source==>" + source);

                                // File (or directory) to be moved
                                file = new File(source);

                                // Destination directory
                                dir = new File(destination);

                                // Move file to new directory

                                FileReader infile = new FileReader(source);
                                FileWriter outfile = new FileWriter(destination + "/" + filenamelist[i]);
                                int c;

                                while ((c = infile.read()) != -1) {
                                    outfile.write(c);
                                }

                                infile.close();
                                outfile.close();
                                t = 1;

                            }
                        }
                        if (existFile.size() > 0) {
                            t = 2;
                            for (int i = 0; i < existFile.size(); i++) {
                                existFileString += existFile.get(i) + " ";
                            }
                        }

                        if (t == 1) {
                            session.setAttribute("td", 1);
                            session.setAttribute("alertMsg", "Successfully Copied!");
                        } else if (t == 2) {
                            session.setAttribute("td", 2);
                            session.setAttribute("alertMsg", "Destination Directory already contain this file!" + existFileString);

                        } else {
                            session.setAttribute("td", 2);
                            session.setAttribute("alertMsg", "No files Selected!" + existFileString);

                        }


                    } catch (Exception e) {
                        System.out.println("Exception in copy file " + e);
                        t = 2;
                        session.setAttribute("td", t);
                        session.setAttribute("alertMsg", "Copy Denied!");

                    }
                } else {
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "Directory name is not valid !");
                }
            } else if (path.equals("/reNameFile")) {

                String parentNode = null;

                String rootNode = (String) session.getAttribute("rootPath");
                if (session.getAttribute("parentNode") == null) {
                    parentNode = rootNode;

                } else {
                    parentNode = (String) session.getAttribute("parentNode");
                }
                System.out.println("inside renamefile==>");
                String newName = request.getParameter("newName");
                newName = newName.trim();
                String oldName = request.getParameter("oldName");

                File oldFile;
                File newFile;
                if (oldName.indexOf(".") > 0) {
                    String filetype = oldName.substring(oldName.lastIndexOf("."));
                    System.out.println("filetype====>" + filetype);
                    newName = newName + filetype;
                }
                boolean success = false;
                t = 0;

                try {
                    oldFile = new File(parentNode + "/" + oldName);
                    newFile = new File(parentNode + "/" + newName);
                    System.out.println(" oldFile++" + oldFile);
                    System.out.println(" newFile++" + newFile);

                    if (newFile.exists()) {
                        session.setAttribute("td", 2);
                        session.setAttribute("alertMsg", "This File already Exist!");

                    } else {


                        success = oldFile.renameTo(newFile);
                        if (success) {
                            session.setAttribute("td", 1);
                            session.setAttribute("alertMsg", "Successfully Renamed!");
                            // File was not successfully renamed
                        } else {
                            session.setAttribute("td", 2);
                            session.setAttribute("alertMsg", "Select exactly one file or folder. Rename failed ");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "Cannot Renamed!");

                }
            } else if (path.equals("/DownloadAllfiles")) {
                System.out.println("download file===> ");
                String parentNode = null;

                String rootNode = (String) session.getAttribute("rootPath");
                if (session.getAttribute("parentNode") == null) {
                    parentNode = rootNode;

                } else {
                    parentNode = (String) session.getAttribute("parentNode");
                }
                String source = request.getParameter("downloadfile");
                System.out.println("download file source=====>" + source);

                String[] filenamelist = source.split(":");
                System.out.println("size of selected file===> " + filenamelist.length);
                try {




                    OutputStream out1 = response.getOutputStream();
                    ZipOutputStream zip = new ZipOutputStream(out1);
                    final String VERSION_NR = "1.2";
                    final int COMPRESSION_LEVEL = 1;
                    zip.setComment("Created by jsp File Browser v. " + VERSION_NR);
                    zip.setLevel(COMPRESSION_LEVEL);

                    response.setContentType("application/octet-stream");
                    response.addHeader("Content-Disposition",
                            "attachment; filename=\"eDocs.zip\"");
                    //adding multiple files to zip
                    for (int i = 0; i < filenamelist.length; i++) {
                        System.out.println("inside for i==>" + i + filenamelist[i]);
                        fileManager.addDir(new File(parentNode + "/" + filenamelist[i]), filenamelist[i], zip);

                    }

                    zip.flush();
                    zip.close();
                    out1.close();
                } catch (ZipException ex) {
                    System.out.println("zip exception");
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "There is some error.");
                } catch (Exception ex) {
                    System.out.println("exception");
                    ex.printStackTrace();
                    t = 2;
                    session.setAttribute("td", t);
                    session.setAttribute("alertMsg", "There is some error.");
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
            t1 = 2;
            request.setAttribute("t1", t1);
            t = 2;
            request.setAttribute("t", t);
            t2 = 2;
            request.setAttribute("t2", t2);
            request.setAttribute("td", 2);
            session.setAttribute("alertMsg", "There is some error!");
        } finally {
            //  out.close();
        }


    }

    public void xmlEntry(HttpSession session, HttpServletRequest request, String filename, String action) {
        String temp1 = "";
        File logFile = new File(session.getAttribute("logPath").toString().trim() + session.getAttribute("userid").toString().trim() + ".xml");
        if (logFile.exists()) {

            try {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                org.w3c.dom.Document doc = docBuilder.parse(logFile);
                doc.getDocumentElement().normalize();
                NodeList logList = doc.getElementsByTagName("log");
                int logListSize = logList.getLength();
                System.out.println("log List size is " + logListSize);
                Node logNode = logList.item(0);

                if (logNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element logElement = (Element) logNode;
                    Node newNode = doc.createElement("record");
                    Element newNodeElement = (Element) newNode;

                    newNodeElement.setAttribute("date", new Date().toString());

                    Node childNode = doc.createElement("message");
                    Element childElement = (Element) childNode;
                    childElement.setAttribute("status", "u");
                    temp1 += filename + " " + action + "   From " + request.getRemoteAddr();
                    childNode.setTextContent(temp1);
                    newNode.appendChild(childNode);
                    logNode.appendChild(newNode);
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    doc.normalize();
                    StreamResult result = new StreamResult(logFile);
                    DOMSource source = new DOMSource(doc);
                    transformer.transform(source, result);
                }
            } catch (Exception e) {
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
