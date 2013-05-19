/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.gda;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author smp
 */
public class ConvertToDate {

    Calendar dt = Calendar.getInstance();
    DateFormat formatter;
    java.sql.Date sqldt = null;
    java.util.Date utildt = null;

    public java.sql.Date convertStringToDate(String strdt) {

        formatter = new SimpleDateFormat("dd/MM/yy");
        try {
            utildt = (java.util.Date) formatter.parse(strdt);
            System.out.println(" utildt---> " + utildt);
        } catch (ParseException ex) {
            System.out.println("Exception in ConvertToDate:" + ex);
        }
        sqldt = new java.sql.Date(utildt.getTime());
        System.out.println(" sqldt---> " + sqldt);
        utildt = null;
        return sqldt;
    }

    public Calendar convertStringToCLDate(String strdt) {
        formatter = new SimpleDateFormat("dd/MM/yy");
        try {
            utildt = (java.util.Date) formatter.parse(strdt);
        } catch (ParseException ex) {
            System.out.println("Exception in ConvertToDate:" + ex);
        }
        dt.setTime(utildt);
        return dt;
    }
}
