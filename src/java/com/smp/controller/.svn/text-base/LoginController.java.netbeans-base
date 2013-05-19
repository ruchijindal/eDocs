/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.controller;

import com.smp.entity.Login;
import com.smp.entity.Userrole;
import com.smp.gda.UserData;
import com.smp.manager.LoginManager;
import com.smp.session.UserroleFacade;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author smp-06
 */
@WebServlet(name = "Login", urlPatterns = {"/Login", "/Logout", "/ChangePassword"})
public class LoginController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @PersistenceContext(unitName = "SMPDocManPU")
    EntityManager em;
    List<String> userList = new ArrayList<String>();
    List<UserData> userlist = new ArrayList<UserData>();
    UserData userData = new UserData();
    @EJB
    LoginManager loginManager;
    @EJB
    UserroleFacade userroleFacade;
    Userrole userrole=new Userrole();

    HttpSession session;
    int validationFlag = 0;
    FileHandler hand;
    String log_path;
    String repository_path;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String xmlFile = request.getServletContext().getRealPath("") + "/resources/XMLUtils/PathXML.xml";
        try {
            String path = request.getServletPath();

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document docxml = docBuilder.parse(xmlFile);
            docxml.getDocumentElement().normalize();
            NodeList p = docxml.getElementsByTagName("path");
            int total_path = p.getLength();

            // System.out.println("Root element of the doc is " + docxml.getDocumentElement().getNodeName());
            //     System.out.println("Total no of consumers : " + total_path);
            Node p1 = p.item(0);
            if (p1.getNodeType() == Node.ELEMENT_NODE) {

                org.w3c.dom.Element pathElement = (org.w3c.dom.Element) p1;
                NodeList path_list = pathElement.getElementsByTagName("repository_path");
                Node rep_path = path_list.item(0);
                Element rep_path_element = (Element) rep_path;
                repository_path = rep_path_element.getTextContent().trim();
                //    System.out.println("rep path value is " + rep_path_element.getTextContent());
                //     System.out.println("p1 is " + p1.getNodeName());
                NodeList log_path_list = pathElement.getElementsByTagName("log_path");
                Node log_path = log_path_list.item(0);
                Element log_path_element = (Element) log_path;
                this.log_path = log_path_element.getTextContent().trim();
                System.out.println("repository Path " + repository_path + "\n Log path " + this.log_path);
                 NodeList swfpathList = pathElement.getElementsByTagName("swftool_path");
                Node swfpath = swfpathList.item(0);
                Element swfpathElement = (Element) swfpath;
                session = request.getSession(true);
                session.setAttribute("swftool_path", swfpathElement.getTextContent());
            }
            if (path.equals("/Login")) {
                String name = request.getParameter("userName");
                String password = request.getParameter("password");
                int flag = 1;
                loginManager.validateUser(name, password);
                if (flag == 1) {
                    //  System.out.println("user validated.....................");
                    List<Login> loginList = new ArrayList<Login>();
                    loginList = loginManager.find(name, password);
                    session = request.getSession(true);
                //session.setMaxInactiveInterval(3);
                    session.setAttribute("logPath", log_path);
                    session.setAttribute("repositoryPath", repository_path);
                    session.setAttribute("userid", name);
                    session.setAttribute("userName", loginList.get(0).getUsername());

                    System.out.println("User ID is " + name);

                    boolean temp = true;
                    if (!userlist.isEmpty()) {
                        for (int i = 0; i < userlist.size(); i++) {
                            if (userlist.get(i).getUserid().equals(name)) {
                                temp = false;
                                userlist.get(i).setModified(userlist.get(i).getModified() + 1);
                                break;
                            }
                        }
                        if (temp) {
                            userData.setUsername(loginList.get(0).getUsername());
                            userData.setUserid(name);
                            userData.setModified(1);
                            userlist.add(userData);
                            temp = false;
                        }
                    } else {
                        userData.setUsername(loginList.get(0).getUsername());
                        userData.setUserid(name);
                        userData.setModified(1);
                        userlist.add(userData);
                    }

                    userData = new UserData();
                    userrole= (Userrole) em.createNamedQuery("Userrole.findById").setParameter("id", loginList.get(0).getFkUserrole()).getResultList().get(0);
                    //  System.out.println("system IP is _>>>>>. " + request.getRemoteAddr());
                    session.setAttribute("currentUsersize", userlist.size());
                    session.setAttribute("userlist", userlist);
                    session.setAttribute("accessRight", userrole.getRights());
                    session.setAttribute("userrole", loginList.get(0).getUserrole());
                    File f = new File(log_path + name + ".xml");
                    String xml;
                    if (!f.exists()) {
                        f.createNewFile();
                        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
                        xml += "\n<log>\n<record date=\"" + new Date().toString() + "\" >\n<message>\nLogin with " + name + " from " + request.getRemoteAddr() + " \n</message>  \n</record>\n </log>";
                        //   FileOutputStream fout=new FileOutputStream(f);
                        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                        bw.write(xml);
                        bw.close();
                    } else {
                        Document doc = docBuilder.parse(f);
                        doc.getDocumentElement().normalize();
                        NodeList logList = doc.getElementsByTagName("log");
                        int logListSize = logList.getLength();
                        System.out.println("log List size is " + logListSize);
                        Node logNode = logList.item(0);
                        System.out.println("log Node Name " + logNode.getNodeName());
                        if (logNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element logElement = (Element) logNode;
                            Node newNode = doc.createElement("record");
                            Element newNodeElement=(Element) newNode;
                            
                            newNodeElement.setAttribute("date", new Date().toString());
                            
                            Node childNode = doc.createElement("message");
                            String temp1 = "Login With " + name + " from " + request.getRemoteAddr();
                            childNode.setTextContent(temp1);
                            newNode.appendChild(childNode);
                            logNode.appendChild(newNode);
                            Transformer transformer = TransformerFactory.newInstance().newTransformer();
                         doc.normalize();
                            StreamResult result = new StreamResult(f);
                            DOMSource source = new DOMSource(doc);
                            transformer.transform(source, result);
                        }
                    }
                     System.out.println("vaid User.......................");
                    response.sendRedirect(request.getContextPath() + "/SearchController");
                } else {
                    validationFlag = 1;
                    System.out.println("Invaid User.......................");
                    request.setAttribute("validationFlag", validationFlag);
                    request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } else if (path.equals("/Logout")) {

                session = request.getSession(false);
                if (session.getAttribute("userrole").equals("null") ) {
                    response.sendRedirect("/index.jsp");
                }               
                String name = (String) session.getAttribute("userid");
                session.removeAttribute("userid");
                userData.setUserid(name);
                for (int i = 0; i < userlist.size(); i++) {
                    if (userlist.get(i).getUserid().equals(name.trim()) && userlist.get(i).getModified() == 1) {

                        userlist.remove(i);
                        break;

                    } else if (userlist.get(i).getUserid().equals(name.trim()) && userlist.get(i).getModified() > 1) {
                        userlist.get(i).setModified(userlist.get(i).getModified() - 1);
                        break;
                    }
                }
                
                userlist.remove(userData);
                session.setAttribute("userlist", userlist);

                System.out.println("UserList" + userList);
                session.setAttribute("currentUsersize", userlist.size());
               
                session.removeAttribute("userrole");
                
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath());
            } else if (path.equals("/ChangePassword")) {
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                String confirmPassword = request.getParameter("confirmPassword");
                String userId = (String) session.getAttribute("userid");
                System.out.println(oldPassword+" "+newPassword+"  "+confirmPassword+"  "+userId);
                int changePassFlag = 1;
                try {
                    changePassFlag = loginManager.changePassword(userId, oldPassword, newPassword, confirmPassword);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("changePassFlag", changePassFlag);
                request.getServletContext().getRequestDispatcher("/jsppages/common/ch_pass.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } finally {

            out.close();
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
