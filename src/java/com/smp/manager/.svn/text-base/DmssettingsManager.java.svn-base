/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.manager;


import com.smp.entity.Login;
import com.smp.entity.Userrole;
import com.smp.session.LoginFacade;
import com.smp.session.UserroleFacade;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smp
 */
@Stateless
public class DmssettingsManager {

    
    @EJB
    LoginFacade loginFacade;
    @EJB
    UserroleFacade userroleFacade;
    Userrole Userrole;
    @PersistenceContext(unitName = "SMPDocManPU")
    EntityManager em;
 //   Rates rates = new Rates();
    Login login = new Login();
    HttpSession session;
    DateFormat formatter;
    Date date_from;
    Date date_to;
    Date datefrom;
    Date dateto;
    long min_plot_size;
    long max_plot_size;
    long pipe_size;
    double rates2;
    long id1;
    BigDecimal id2;
    MessageDigest algorithm = null;
    String encyptpass = "";
    java.util.Date date = new java.util.Date();
    long createDate;
    long lastLoginDate;
    BigDecimal id;
   // List<Rates> ratesList = new ArrayList<Rates>();
    Login loginTab1 = new Login();
 
    public void createUser(String userid, String password, String username, String userroleid, String createdby) {
        try {
            createDate = date.getTime();
            lastLoginDate = date.getTime();
            Userrole=new Userrole();

            try {
                algorithm = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException nsae) {
                System.out.println("Cannot find digest algorithm" + nsae);
                System.exit(1);
            }

            byte[] defaultBytes = password.getBytes();
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();

            for (int j = 0; j < messageDigest.length; j++) {
                String hex = Integer.toHexString(0xFF & messageDigest[j]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            encyptpass = hexString.toString();

            login = new Login();
            login.setUserid(userid);
            login.setPassword(encyptpass);
           
            login.setFkUserrole(Integer.parseInt(userroleid));
            Userrole=userroleFacade.find(Integer.parseInt(userroleid));
             login.setUserrole(Userrole.getUserrole());

            login.setDate(new java.sql.Date(createDate));
           
            login.setUsername(username);
            login.setCreatedby(createdby);

            loginFacade.create(login);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public int checkUser(String userid) {
        Login login = new Login();
        login = loginFacade.find(userid);
        if (login != null) {
            return 1;
        } else {
            return 0;
        }

    }

    public Login getUserDetail(String userid) {
        login = new Login();
        login = loginFacade.find(userid);
        return login;
    }

    public void editUser(String userid, String username, String userrole) {
        try {

           
            login = new Login();
            loginTab1 = loginFacade.find(userid);
            String password = loginTab1.getPassword();
            System.out.println("password==== " + loginTab1);
            System.out.println("hello==== ");
            Date crdate = loginTab1.getDate();
            System.out.println("crdate==== " + loginTab1.getDate());
            String createdBy = loginTab1.getCreatedby();
            System.out.println("createdby==== " + loginTab1.getCreatedby());
            Date lastLodinDt = loginTab1.getDate();
            System.out.println("lastLogindate==== " + loginTab1.getDate());
            login.setUserid(userid);
            login.setPassword(password);
            login.setUserrole(userrole);
            login.setDate(crdate);
           
            login.setUsername(username);
           
            login.setCreatedby(createdBy);
           
            loginFacade.edit(login);
        } catch (Exception e) {
            e.printStackTrace();
            
            System.out.println(e);
        }


    }

    public void deleteUser(String userid) {
        login = loginFacade.find(userid);
        loginFacade.remove(login);
    }

}
