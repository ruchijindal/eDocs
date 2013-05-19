/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.manager;

import com.smp.entity.Login;
import com.smp.session.LoginFacade;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smp-06
 */
@Stateless
public class LoginManager {

    @PersistenceContext(unitName = "SMPDocManPU")
    EntityManager em;
    @EJB
    LoginFacade loginFacade;
    Login login = new Login();
    List<Login> loginList = new ArrayList<Login>();
    MessageDigest algorithm = null;

    public int validateUser(String userName, String password) {
        int flag = 0;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");

            loginList = loginFacade.findAll();
            byte[] defaultBytes = password.getBytes();
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte[] messageDigest = algorithm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
           password = hexString.toString();

            for (int i = 0; i < loginList.size(); i++) {
                if (loginList.get(i).getUserid().equals(userName) && loginList.get(i).getPassword().equals(password)) {
                    flag = 1;
                    login=loginList.get(i);
                    break;
                } else {
                    flag = 0;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public List<Login> find(String userName, String password) {
        try {
          algorithm = MessageDigest.getInstance("SHA-256");

              loginList = loginFacade.findAll();
               byte[] defaultBytes = password.getBytes();
              algorithm.reset();
               algorithm.update(defaultBytes);
             byte[] messageDigest = algorithm.digest();
              StringBuffer hexString = new StringBuffer();
              for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
               if (hex.length() == 1) {
                   hexString.append('0');
               }
               hexString.append(hex);
              }
             password = hexString.toString();
            loginList.clear();
            loginList = em.createNamedQuery("Login.findByUseridAndPassword").setParameter("userid", userName).setParameter("password", password).getResultList();

            if (loginList.isEmpty()) {
                System.out.println("yahoooooooooooooooooooooooooooooooooooo");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loginList;
    }

    public int changePassword(String userId, String oldPassword, String newPassword, String confirmPassword) throws NoSuchAlgorithmException {
  algorithm = MessageDigest.getInstance("SHA-256");
        login = (Login) em.createNamedQuery("Login.findByUserid").setParameter("userid", userId).getResultList().get(0);
        byte[] defaultBytes = oldPassword.getBytes();
        algorithm.reset();
        algorithm.update(defaultBytes);
        byte[] messageDigest = algorithm.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        oldPassword = hexString.toString();

        System.out.println("login name and pass word in  login manager is " + login.getPassword() + "  " + login.getUserid());
        if (!oldPassword.equals(login.getPassword())) {
            return 0;
        } else if (newPassword.equals("")) {
            return 4;
        } else if (!newPassword.equals(confirmPassword)) {
            return 1;
        } else {
            algorithm = MessageDigest.getInstance("SHA-256");
            byte[] defaultBytes1 = newPassword.getBytes();
            algorithm.reset();
            algorithm.update(defaultBytes1);
            byte[] messageDigest1 = algorithm.digest();
            StringBuffer hexString1 = new StringBuffer();
            for (int i = 0; i < messageDigest1.length; i++) {
                String hex1 = Integer.toHexString(0xFF & messageDigest1[i]);
                if (hex1.length() == 1) {
                    hexString1.append('0');
                }
                hexString1.append(hex1);
            }
            newPassword = hexString1.toString();
            login.setPassword(newPassword);
            loginFacade.edit(login);

            return 2;

        }


    }
}
