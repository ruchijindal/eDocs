/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.smp.entity.Login;
import com.smp.session.LoginFacade;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author smp
 */
@WebServlet(name = "logPdf", urlPatterns = {"/logPdf"})
public class logPdf extends HttpServlet {

    @EJB
    LoginFacade loginFacade;
    Login login = new Login();
    String date = "";
    String message = "";
    int count = 0, count1 = 0;

    class Eventr extends PdfPageEventHelper {

        public PdfPTable footer;
        protected PdfTemplate total;
        protected BaseFont helv;

        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(100, 100);
            total.setBoundingBox(new Rectangle(-20, -20, 100, 100));
            try {
                helv = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);

            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        }

        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            cb.saveState();
            String text = "Page " + writer.getPageNumber() + " of ";
            float textBase = document.bottom() - 20;
            float textSize = helv.getWidthPoint(text, 12);
            cb.beginText();
            cb.setFontAndSize(helv, 12);


            float adjust = helv.getWidthPoint("0", 12);
            cb.setTextMatrix(
                    document.right() - textSize - adjust, textBase);
            cb.showText(text);
            cb.endText();
            cb.addTemplate(total, document.right() - adjust, textBase);
//            }
            cb.restoreState();
        }

        public void onCloseDocument(PdfWriter writer, Document document) {
            total.beginText();
            total.setFontAndSize(helv, 12);
            total.setTextMatrix(0, 0);
            total.showText(String.valueOf(writer.getPageNumber() - 1));
            total.endText();
        }
    }

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
        ServletOutputStream out = response.getOutputStream();
        HttpSession session = request.getSession();
        Font smallFont = new Font(Font.TIMES_ROMAN, 9);



        try {


            String id = request.getParameter("id");
            login = loginFacade.find(id);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4, 0, 0, 60, 30);

            PdfWriter docWriter;
            docWriter = PdfWriter.getInstance(document, baos);
            Eventr er = new Eventr();
            docWriter.setPageEvent(er);
            document.open();
            Rectangle pageSize = new Rectangle(864, 1080);
            float[] widths1 = {1f, .8f,};
            PdfPTable table = new PdfPTable(widths1);
            PdfPCell cell = new PdfPCell(new Paragraph("User Activity Details "));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            Date d = new Date();
            DateFormat format = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
            String date1 = format.format(d);

            Paragraph paragraph1 = new Paragraph(" ");
            paragraph1 = new Paragraph("User Activity Details Exported For \n" + login.getUsername() + "\nat " + date1);
            cell = new PdfPCell(paragraph1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);


            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            addEmptyLine(paragraph1, 4);

            Chunk chunk = new Chunk("Date & Time");
            //chunk.setUnderline(.2f, -2f);
            cell = new PdfPCell(new Paragraph(chunk));
            cell.setBorder(Rectangle.BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            chunk = new Chunk("Activity");
            // chunk.setUnderline(.8f, -2f);
            cell = new PdfPCell(new Paragraph(chunk));
            cell.setBorder(Rectangle.BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            String log_path = (String) session.getAttribute("logPath");
            System.out.println("log path is ->> " + log_path);
            log_path += id.toString().trim() + ".xml";
            File f = new File(session.getAttribute("logPath").toString() + id.toString().trim() + ".xml");

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document docxml = docBuilder.parse(f);
            docxml.getDocumentElement().normalize();
            NodeList logList = docxml.getElementsByTagName("log");
            Node logNode = logList.item(0);
            if (logNode.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element logElement = (org.w3c.dom.Element) logNode;
                NodeList recordList = logElement.getElementsByTagName("record");
                if (recordList.getLength() > 0) {
                    for (int j = recordList.getLength() - 1; j >= 0; j--) {                        
                        count++;
                        Node recordNode = recordList.item(j);
                        if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
                            org.w3c.dom.Element recordElement = (org.w3c.dom.Element) recordNode;
                            date = recordElement.getAttribute("date");                           
                            cell = new PdfPCell(new Paragraph(date.substring(8, 10) + "/" + date.substring(4, 7) + "/" + date.substring(24, 28) + ", " + date.substring(11, 20), smallFont));
                            cell.setBorder(Rectangle.NO_BORDER);
                            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                            if (count % 2 == 0) {
                                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            }
                            table.addCell(cell);
                            NodeList messageList = recordElement.getElementsByTagName("message");                            
                            for (int k = 0; k < messageList.getLength(); k++) {                              
                                count1++;
                                Node messageNode = messageList.item(k);
                                if (messageNode.getNodeType() == Node.ELEMENT_NODE) {
                                    org.w3c.dom.Element messageElement = (org.w3c.dom.Element) messageNode;
                                    message = messageElement.getTextContent();                                    
                                    cell = new PdfPCell(new Paragraph(message.trim(), smallFont));
                                    cell.setBorder(Rectangle.NO_BORDER);
                                    cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                                    if (count1 % 2 == 0) {
                                        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                                    }
                                    table.addCell(cell);
                                }

                            }
                        }

                    }
                }



                //outerTable.addCell(table);
                document.add(table);

                document.close();
                docWriter.close();
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                response.setContentType("application/pdf");
                baos.writeTo(out);
                out.flush();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
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

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int j = 0; j < number; j++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
