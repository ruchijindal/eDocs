/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.controller;

import com.smp.entity.Login;
import com.smp.entity.Userrole;

import com.smp.manager.DmssettingsManager;

import com.smp.session.LoginFacade;
import com.smp.session.UserroleFacade;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smp
 */
@WebServlet(name = "DmssettingsController", urlPatterns = {"/RolesRights","/AddRole", "/ChangeRights", "/SetUserDetails", "/deleteUser", "/DmssettingsController", "/CreateUser", "/checkuser", "/viewUser", "/viewUserList", "/userList", "/editUser", "/searchType", "/searchCategory", "/searchDateFrom", "/pagination", "/SetDiscountRate", "/closeNewRate", "/discountRate", "/editDiscountRate", "/deleteDiscountRate"})
public class DmssettingsController extends HttpServlet {

    @EJB
    DmssettingsManager dmssettingsManager;
    @EJB
    LoginFacade loginFacade;
    @EJB
    UserroleFacade userroleFacade;
    Userrole userRole = new Userrole();
    HttpSession session;
    String connectionType;
    String connectionCategory;
    String flatType;
    String sector;
    String dateFrom;
    String dateTo;
    String minPlotSize;
    String maxPlotSize;
    String pipeSize;
    String rate;
    String connectionType_1;
    String connectionCategory_1;
    String sector_1;
    String flatType_1;
    String dateFrom_1;
    String dateTo_1;
    String plotSize_1;
    String pipeSize_1;
    String id;
    String user_id;
    String userid;
    String password;
    String cpassword;
    String firstname;
    String lastname;
    String userrole;
    String division;
    String createdby;
    int flag = 0;
    int t = 0, t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0, t7 = 0;
    private String plotSize;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session = request.getSession();
            if (session.getAttribute("userrole") == null) {
                response.sendRedirect("/index.jsp");
            }
            String path = request.getServletPath();
//     
            if (path.equals("/CreateUser")) {
                System.out.println("inside Create  user.....");
                createdby = (String) session.getAttribute("userid");
                userid = request.getParameter("userid");
                password = request.getParameter("password");
                firstname = request.getParameter("name");
                //lastname = request.getParameter("lastname");
               String  userroleid = request.getParameter("userrole");
                
                division = request.getParameter("division");
                System.out.println("user role id "+userroleid);
                dmssettingsManager.createUser(userid, password, firstname, userroleid, createdby);
                t1 = 1;
                request.setAttribute("t1", t1);
                session.setAttribute("t", 0);
                session.setAttribute("t2", 0);
                response.sendRedirect(request.getContextPath() + "/viewUser");
            } else if (path.equals("/SetUserDetails")) {
                List<Userrole> userroles=userroleFacade.findAll();
                for(Userrole u:userroles)
                {
                    System.out.println("role "+u.getUserrole()+"   t "+u.getRights());
                }
                session.setAttribute("userrolelist", userroles);
                session.setAttribute("userrolelistsize", userroles.size());
                 System.out.println("in create user paghgfhfhggggggggggggggfe");
                request.getServletContext().getRequestDispatcher("/jsppages/user/create_user.jsp").forward(request, response);


            } else if (path.equals("/checkuser")) {

                userid = request.getParameter("userid");
                flag = dmssettingsManager.checkUser(userid);
                out.println(flag);

            } else if (path.equals("/viewUser")) {
                System.out.println("inside view user.....");
                List<Login> userList = loginFacade.findAll();
                request.setAttribute("userList", userList);
                request.setAttribute("userLists", userList.size());
                session.setAttribute("t", 0);
                session.setAttribute("t1", 0);
                session.setAttribute("t2", 0);
                request.getServletContext().getRequestDispatcher("/jsppages/user/view_user.jsp").forward(request, response);

            } else if (path.equals("/viewUserList")) {
                List<Login> userList = loginFacade.findAll();
                request.setAttribute("userList", userList);
                request.setAttribute("userLists", userList.size());

            } else if (path.equals("/userList")) {
                userid = request.getParameter("id");
                session.setAttribute("userList", dmssettingsManager.getUserDetail(userid));
                request.getServletContext().getRequestDispatcher("/jsppages/admin/edit.jsp").forward(request, response);

            } else if (path.equals("/editUser")) {

                createdby = (String) session.getAttribute("userid");
                userid = request.getParameter("userId");
                firstname = request.getParameter("username");
                // lastname = request.getParameter("lastName");
                userrole = request.getParameter("userRole");
                //division = request.getParameter("division");

                dmssettingsManager.editUser(userid, firstname, userrole);
                t = 1;
                session.setAttribute("t", t);
                request.getServletContext().getRequestDispatcher("/viewUserList").forward(request, response);

            } else if (path.equals("/deleteUser")) {
                userid = request.getParameter("id");
                dmssettingsManager.deleteUser(userid);
                t2 = 1;
                session.setAttribute("t2", t2);
                request.getServletContext().getRequestDispatcher("/viewUserList").forward(request, response);
            } else if (path.equals("/pagination")) {
                String start = request.getParameter("s");
                String end = request.getParameter("e");
                session.setAttribute("s", Integer.valueOf(start));
                session.setAttribute("e", Integer.valueOf(end));
            } else if (path.equals("/RolesRights")) {
                  List<Userrole> userRoleList = userroleFacade.findAll();
                System.out.println("Role list size  " + userRoleList.size());
                session.setAttribute("rolesize", userRoleList.size());
                session.setAttribute("roleList", userRoleList);
                for (int l = 0; l < userRoleList.size(); l++) {
                    System.out.println(l + "   " + userRoleList.get(l).getUserrole());
                }
                request.getRequestDispatcher("/jsppages/allottee/accessRights.jsp").forward(request, response);

            } else if (path.equals("/ChangeRights")) {
                String[] ids=request.getParameterValues("id");

                String[] rights= new String[ids.length];
                for(int i=0;i<ids.length;i++)
                {
                    rights[i]=request.getParameter("r"+ids[i]);
                    if(rights[i]!=null &&!rights[i].equals("null"))
                    {
                         System.out.println(ids[i]+"  a read is " + rights[i]);
                userRole=new Userrole();
                userRole=userroleFacade.find(Integer.parseInt(ids[i]));

                 if(rights[i].equalsIgnoreCase("write"))
                {
                    userRole.setRights("rw");
                }else if(rights[i].equalsIgnoreCase("read"))
                {
                    userRole.setRights("r");
                }else{
                    userRole.setRights("r");
                }
                 userroleFacade.edit(userRole);
                    }
                }
               
               // if(!rights.equals("null"))
             //   {
               
               // }
 //else{
      // userRole.setRights("R");
 //}

               
                request.getRequestDispatcher("/RolesRights").forward(request, response);

            } else if (path.equals("/AddRole")) {
                String roleName = request.getParameter("Role");
                String rights = request.getParameter("rights");
                System.out.println("rights are " + roleName + "  " + rights);
                userRole.setRights(rights);
                userRole.setUserrole(roleName);
                userroleFacade.create(userRole);
                List<Userrole> userRoleList = userroleFacade.findAll();
                System.out.println("Role list size  " + userRoleList.size());
                session.setAttribute("rolesize", userRoleList.size());
                session.setAttribute("roleList", userRoleList);

                request.getServletContext().getRequestDispatcher("/jsppages/allottee/accessRights.jsp").forward(request, response);
            } //else if (path.equals("/SetDiscountRate")) {
//                System.out.println("inside set discount rates");
//                dateFrom = request.getParameter("dateFrom");
//                dateTo = request.getParameter("dateTo");
//                rate = request.getParameter("rate");
//                dmssettingsManager.setDiscountRate(dateFrom, dateTo, rate);
//                t1 = 1;
//                request.setAttribute("t1", t1);
//                request.getServletContext().getRequestDispatcher("/discountRate").forward(request, response);
//
//            } else if (path.equals("/editDiscountRate")) {
//                System.out.println("inside edit discount rates");
//                id = request.getParameter("id");
//                dateFrom = request.getParameter("date_From");
//                dateTo = request.getParameter("date_To");
//                rate = request.getParameter("Rate");
//                dmssettingsManager.editDiscountRate(id, dateFrom, dateTo, rate);
//                 t6 = 1;
//                request.setAttribute("t6", t6);
//                request.getServletContext().getRequestDispatcher("/discountRate").forward(request, response);
//            } else if (path.equals("/deleteDiscountRate")) {
//                id = request.getParameter("id");
//                dmssettingsManager.deleteDiscountRate(id);
//                 t7 = 1;
//                request.setAttribute("t7", t7);
//                request.getServletContext().getRequestDispatcher("/discountRate").forward(request, response);
//            }
        } catch (Exception e) {
            t = 2;
            session.setAttribute("t", t);
            t1 = 2;
            request.setAttribute("t1", t1);
            t2 = 2;
            session.setAttribute("t2", t2);

            t3 = 2;
            session.setAttribute("t3", t3);
            t4 = 2;
            session.setAttribute("t4", t4);
            t5 = 2;
            session.setAttribute("t5", t5);
            t6 = 2;
            request.setAttribute("t6", t6);
            t7 = 2;
            request.setAttribute("t7", t7);
            e.printStackTrace();
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
