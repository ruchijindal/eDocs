/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smp.gda;

import javax.servlet.http.HttpSession;

/**
 *
 * @author smp
 */
public class PdfHandler implements Runnable {
    String input;
    String output;
    HttpSession session=null;
   public  Thread t;

    public PdfHandler() {
        t=new Thread(this, "swfgenerator");
        t.start();
    }
    public PdfHandler(String i,String j, HttpSession session)
    {
        input=i;
        output=j;
        t=new Thread(this, "swfgenerator");
        this.session=session;
        t.start();
    }
public PdfHandler(String i,String j)
    {
        input=i;
        output=j;
        t=new Thread(this, "swfgenerator");
        this.session=null;
        t.start();
    }

    public void run()
    {
 try {
                        if(session!=null)
                        System.out.println("swf tool path="+session.getAttribute("swftool_path"));
                        System.out.println("Input "+input);
                        System.out.println("Output "+output);
                        Process process = Runtime.getRuntime().exec("pdf2swf" + " " + input + " -o " + output);
                       // process.waitFor();
                        System.out.println("yahoooooooooooooooooooooooooooo");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
    }
//  public void pdfToswf(String in,String out)
//    {
//         try {
//            input=in;
//            output=out;
//                        Process process = Runtime.getRuntime().exec("pdf2swf" + " " + input + " -o " + output);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//    }
}
