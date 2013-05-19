/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.manager;

import com.smp.entity.Master;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author smp-06
 */
@Stateless
public class SearchManager {

    @PersistenceContext(unitName = "SMPDocManPU")
    EntityManager em;
    DateFormat formatter;
    Date blPerFr;
    Date blPerTo;
    Date dueDt;
    Date payDate;
    BigInteger id;
    BigInteger billAmt;
    BigInteger Surcharge;
    BigInteger Arrear;
    BigInteger Credit;
    BigInteger Noc;
    BigInteger Rmc;
    BigInteger chlSecu;
    BigInteger tFee;
    BigInteger paidAmt;
    Date EndAllotmentDate;
    Date StartAllotmentDate;
    List<Master> mastersList = new ArrayList<Master>();
    List<Master> mastersListByDate = new ArrayList<Master>();
    Date startRegistryDate;
    Date endRegistryDate;
    Date startPossessionDate;
    Date endPossessionDate;

//    public ArrayList<String> getSectors(String division, String xmlPath) {
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        DocumentBuilder db;
//        ArrayList<String> sectorList = new ArrayList<String>();
//        try {
//            db = dbf.newDocumentBuilder();
//            Document doc = db.parse(xmlPath);
//            NodeList jalList = doc.getElementsByTagName("jal" + division.trim());
//
//            NodeList jalChildList = jalList.item(0).getChildNodes();
//            for (int i = 1; i < jalChildList.getLength(); i = i + 2) {
//                sectorList.add(jalChildList.item(i).getTextContent());
//
//            }
//
//        } catch (Exception ex) {
//            Logger.getLogger(SearchManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return sectorList;
//
//
//    }
//    public String getBlocks(String division, String sector) {
//        String block = "";
//        int flag = 0;
//        List<String> blockList = em.createNamedQuery("Master" + division + ".findBySectorDistinct").setParameter("sector", sector).getResultList();
//        for (int i = 0; i < blockList.size(); i++) {
//            if (block.equals("")) {
//                if (blockList.get(i) == null) {
//                    block = "N/A";
//                    flag = 1;
//                } else {
//                    block = blockList.get(i);
//                }
//            } else {
//                if (blockList.get(i) == null) {
//                    block = block + ":" + "N/A";
//                    flag = 1;
//                } else {
//                    block = block + ":" + blockList.get(i);
//                }
//            }
//        }
//        if (flag == 0) {
//            block = block + ":" + "N/A";
//        }
//        return block;
//    }
//    public Master getConsumerDetails(String division, String consumerNo) {
//        Master master = null;
//        List masterList = em.createNamedQuery("Master" + division + ".findByConsNo").setParameter("consNo", consumerNo).getResultList();
//        if (!masterList.isEmpty()) {
//            master = (Master) masterList.get(0);
//        }
//        return master;
//    }
//    public Sewerdetails getSewerDetails(String division, String consumerNo) {
//        Sewerdetails sewerdetails = null;
//        List sewerdetailsList = em.createNamedQuery("Sewerdetails" + division + ".findByConsNo").setParameter("consNo", consumerNo).getResultList();
//        if (!sewerdetailsList.isEmpty()) {
//            sewerdetails = (Sewerdetails) sewerdetailsList.get(0);
//        }
//        return sewerdetails;
//    }
//    public List getChallanDetails(String division, String consumerNo) {
//        List challan1List = new ArrayList();
//        challan1List = em.createNamedQuery("Challan" + division + ".findByConsNo").setParameter("consNo", consumerNo).getResultList();
//        return challan1List;
//    }
//     public List getBills(String division, String consumerNo) {
//        List billrepositoryList = new ArrayList();
//        billrepositoryList = em.createNamedQuery("Billrepository" + division + ".findByConsNo").setParameter("consNo", consumerNo).getResultList();
//        return billrepositoryList;
//    }
//    public void updateConsumerDetail(String division, String consumerNo, String consumerName1, String connectionType, String connectionCategory, String flatType, String flatNo, String blockNo, String sector, double plotSize, double pipeSize, String regNo, Date connectionDate, String estimationNo, double estimationAmount, double security, Date estimationDate, double estimation1Amount, double noDueAmount, Date noDueDate, String transferName, String trf, Date calculationDate, Date possesionDate, Date completionDate, double id,String editedBY,Date editedDate) {
//        try {
//
//            Master master = (Master) em.createNamedQuery("Master" + division + ".findById").setParameter("id", id).getResultList().get(0);
//            master.setConsNo(consumerNo);
//            master.setConsNm1(consumerName1);
//            master.setConTp(connectionType);
//            master.setConsCtg(connectionCategory);
//            master.setFlatType(flatType);
//            master.setFlatNo(flatNo);
//            master.setBlkNo(blockNo);
//            master.setSector(sector);
//            master.setPlotSize(BigDecimal.valueOf(plotSize));
//            master.setPipeSize(BigDecimal.valueOf(pipeSize));
//            master.setRegNo(regNo);
//            master.setConnDt(connectionDate);
//            master.setEstiNo(estimationNo);
//            master.setEstiAmt(BigDecimal.valueOf(estimationAmount));
//            master.setNodueAmt(BigDecimal.valueOf(noDueAmount));
//            master.setEstiDt(noDueDate);
//            master.setTransNm(transferName);
//            master.setTrf(trf);
//            master.setCalDate(calculationDate);
//            master.setPosDate(possesionDate);
//            master.setCompDate(completionDate);
//            master.setEditedBy(editedBY);
//            master.setEditedDate(editedDate);
//            if (division.equals("1")) {
//                master1Facade.edit((Master1) master);
//            } else if (division.equals("2")) {
//                master2Facade.edit((Master2) master);
//            } else if (division.equals("3")) {
//                master3Facade.edit((Master3) master);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteConsumerDetail(String division, double id) {
//        if (division.equals("1")) {
//            Master1 master1 = master1Facade.find(BigDecimal.valueOf(id));
//            master1Facade.remove(master1);
//        } else if (division.equals("2")) {
//            Master2 master2 = master2Facade.find(BigDecimal.valueOf(id));
//            master2Facade.remove(master2);
//        } else if (division.equals("3")) {
//            Master3 master3 = master3Facade.find(BigDecimal.valueOf(id));
//            master3Facade.remove(master3);
//        }
//    }
//public void createSewerDetail(String division, double id, String consumerNO, String sRegNo, Date sConnDate, String sEstimationNo, int sEstimationAmount, int sSecurity, int sEstimationPaidAmt, Date sEstimationPaidDate, Date sNoDueDate, int sNoDueAmt,String createdBy,Date createdDate)
//    {
//    Sewerdetails sewerdetails= new Sewerdetails1();
//    if(division.equals("1"))
//    {
//        sewerdetails=new Sewerdetails1();
//    }else if(division.equals("2"))
//    {
//        sewerdetails=new Sewerdetails2();
//    }else if(division.equals("3")){
//        sewerdetails=new Sewerdetails3();
//    }
//    sewerdetails.setConsNo(consumerNO);
//    sewerdetails.setId(id);
//        sewerdetails.setSRegNo(sRegNo);
//        sewerdetails.setSConDt(sConnDate);
//        sewerdetails.setSEstNo(sEstimationNo);
//        sewerdetails.setSEstAmt(sEstimationAmount);
//        sewerdetails.setSSecu(sSecurity);
//        sewerdetails.setSEstPamt(sEstimationPaidAmt);
//        sewerdetails.setSEstPdate(sEstimationPaidDate);
//        sewerdetails.setSNodueDt(sNoDueDate);
//        sewerdetails.setSNodueAmt(sNoDueAmt);
//        sewerdetails.setCreatedBy(createdBy);
//        sewerdetails.setCreatedDate(createdDate);
//        if (division.equals("1")) {
//            sewerdetails1Facade.create((Sewerdetails1) sewerdetails);
//        } else if (division.equals("2")) {
//            sewerdetails2Facade.create((Sewerdetails2) sewerdetails);
//        } else if (division.equals("3")) {
//            sewerdetails3Facade.create((Sewerdetails3) sewerdetails);
//        }
//}
//    public void updateSewerDetail(String division, double id, String consumerNO, String sRegNo, Date sConnDate, String sEstimationNo, int sEstimationAmount, int sSecurity, int sEstimationPaidAmt, Date sEstimationPaidDate, Date sNoDueDate, int sNoDueAmt,String editedBy,Date editedDate) {
//        Sewerdetails sewerdetails = (Sewerdetails) em.createNamedQuery("Sewerdetails" + division + ".findById").setParameter("id", id).getResultList().get(0);
//        sewerdetails.setConsNo(consumerNO);
//        sewerdetails.setSRegNo(sRegNo);
//        sewerdetails.setSConDt(sConnDate);
//        sewerdetails.setSEstNo(sEstimationNo);
//        sewerdetails.setSEstAmt(sEstimationAmount);
//        sewerdetails.setSSecu(sSecurity);
//        sewerdetails.setSEstPamt(sEstimationPaidAmt);
//        sewerdetails.setSEstPdate(sEstimationPaidDate);
//        sewerdetails.setSNodueDt(sNoDueDate);
//        sewerdetails.setSNodueAmt(sNoDueAmt);
//        sewerdetails.setEditedBy(editedBy);
//        sewerdetails.setEditedDate(editedDate);
//        if (division.equals("1")) {
//            sewerdetails1Facade.edit((Sewerdetails1) sewerdetails);
//        } else if (division.equals("2")) {
//            sewerdetails2Facade.edit((Sewerdetails2) sewerdetails);
//        } else if (division.equals("3")) {
//            sewerdetails3Facade.edit((Sewerdetails3) sewerdetails);
//        }
//    }
    public List<Master> sectorSearch(String division, String sector) {
        //  System.out.println("block in manager...");
        List<Master> masterList = new ArrayList<Master>();
        masterList = em.createNamedQuery("Master" + division + ".findBySector").setParameter("sector", sector).getResultList();
        return (masterList);
    }

    public List<Master> blockSearch(String division, String sector, String block) {
        //    System.out.println("block in manager..."+block);
        List<Master> masterList = new ArrayList<Master>();
        Query query = em.createNamedQuery("Master" + division + ".findBySectorAndBlkNo");
        query.setParameter("sector", sector);
        query.setParameter("blkNo", block);
        masterList = query.getResultList();
        return (masterList);
    }

    public List<Master> search() {
        List<Master> masterList = new ArrayList<Master>();


        return (masterList);
    }

    public List<Master> searchByAll(String sCode, String cCode, String schemeName, String pCode, String propertyNo, String propertyId, String alloteeName) {
        try {
            Master master = new Master();
            if ((sCode == null) || sCode.equals("")) {
                sCode = null;
            }
            if ((cCode == null) || cCode.equals("")) {
                cCode = null;
            }

            if ((schemeName == null) || schemeName.equals("")) {
                schemeName = null;
            }else{
                schemeName=schemeName.trim();
            }

            if ((pCode == null) || pCode.equals("")) {
                pCode = null;
            }else{
                pCode=pCode.trim();
            }

            if ((propertyNo == null) || propertyNo.equals("")) {
                propertyNo = null;
            }else{
                propertyNo=propertyNo.trim();
            }

            if ((propertyId == null) || propertyId.equals("")) {
                propertyId = null;
            }else{
                propertyId=propertyId.trim();
            }

            if ((alloteeName == null) || alloteeName.equals("")) {
                alloteeName = null;
            }else
            {
                alloteeName=alloteeName.trim();
            }

            mastersList.clear();
            mastersList = em.createNamedQuery("Master.findBySelectedItems").setParameter("schemeCode", sCode).setParameter("catCode", cCode).setParameter("schemeName", schemeName).setParameter("propCode", pCode).setParameter("propNumber", propertyNo).setParameter("propId", propertyId).setParameter("alloteeName", alloteeName).getResultList();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return mastersList;

    }

    public List<Master> searchByDate(String aDate, String aDate1, String rDate, String rDate1, String pDate, String pDate1) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            if ((aDate == null) || aDate.equals("") || aDate.equals("__/__/__")) {
                StartAllotmentDate = null;
            } else {
                StartAllotmentDate = (Date) dateFormat.parse(aDate);
            }

            if ((aDate1 == null) || aDate1.equals("") || aDate1.equals("__/__/__")) {
                EndAllotmentDate = null;
            } else {
                EndAllotmentDate = (Date) dateFormat.parse(aDate1);
            }
            if (rDate.equals("") || (rDate == null)) {
                startRegistryDate = null;
            } else {
                startRegistryDate = (Date) dateFormat.parse(rDate);
            }
            if (rDate1.equals("") || (rDate1 == null)) {
                endRegistryDate = null;
            } else {
                endRegistryDate = (Date) dateFormat.parse(rDate1);
            }
            if (pDate.equals("") || (pDate == null)) {
                startPossessionDate = null;
            } else {
                startPossessionDate = (Date) dateFormat.parse(pDate);
            }
            if (pDate1.equals("") || (pDate1 == null)) {
                endPossessionDate = null;
            } else {
                endPossessionDate = (Date) dateFormat.parse(pDate1);
            }

            mastersListByDate.clear();
            mastersListByDate = em.createNamedQuery("Master.findBySelectedDate").setParameter("allotteeDate", StartAllotmentDate).setParameter("allotteeDate1", EndAllotmentDate).setParameter("registryDate", startRegistryDate).setParameter("registryDate1", endRegistryDate).setParameter("possessionDate", startPossessionDate).setParameter("possessionDate1", endPossessionDate).getResultList();


        } catch (Exception e) {
            e.printStackTrace();

        }
        return mastersListByDate;
    }
//    public void insertChallan(String division, String cons_no, String flat_no, String blk, String sec, String bl_per_fr, String bl_per_to, String due_dt, String bill_amt, String surcharge, String paid_amt, String pay_date, String arrear, String credit, String recp_no, String dis_cd, String noc, String rmc, String chlsecu, String t_fee, String css, String bnk_cd, String br_nm,String createdBy,Date createdDate) {
//        try {
//            Query maxId = em.createNamedQuery("Challan" + division + ".findBymaxid");
//            Object maximumId = maxId.getResultList().get(0);
//            id = (BigInteger) maximumId;
//            Challan challan = null;
//             if (division.equals("1")) {
//                challan=new Challan1();
//            } else if (division.equals("2")) {
//                challan=new Challan2();
//            } else if (division.equals("3")) {
//                challan=new Challan3();
//            }
//            if ((id != null)) {
//                challan.setId(BigInteger.valueOf(id.intValue() + 1));
//
//            } else {
//                challan.setId(BigInteger.valueOf(1));
//
//            }
//            challan.setConsNo(cons_no);
//            challan.setFlatNo(flat_no);
//            challan.setSec(sec);
//            challan.setBlk(blk);
//            formatter = new SimpleDateFormat("dd/MM/yy");
//            blPerFr = (Date) formatter.parse(bl_per_fr);
//            challan.setBlPerFr(blPerFr);
//            formatter = new SimpleDateFormat("dd/MM/yy");
//            blPerTo = (Date) formatter.parse(bl_per_to);
//            challan.setBlPerTo(blPerTo);
//            formatter = new SimpleDateFormat("dd/MM/yy");
//            if(due_dt!=null && !due_dt.equals(""))
//            dueDt = (Date) formatter.parse(due_dt);
//            else
//                dueDt=null;
//            challan.setDueDt(dueDt);
//            if(bill_amt!=null && !bill_amt.equals(""))
//            challan.setBillAmt(BigInteger.valueOf(Long.parseLong(bill_amt)));
//             if(surcharge!=null && !surcharge.equals(""))
//            challan.setSurcharge(BigInteger.valueOf(Long.parseLong(surcharge)));
//             if(paid_amt!=null && !paid_amt.equals(""))
//            challan.setPaidAmt(BigInteger.valueOf(Long.parseLong(paid_amt)));
//            formatter = new SimpleDateFormat("dd/MM/yy");
//             if(pay_date!=null && !pay_date.equals(""))
//            payDate = (Date) formatter.parse(pay_date);
//            challan.setPayDate(payDate);
//             if(arrear!=null && !arrear.equals(""))
//            challan.setArrear(BigInteger.valueOf(Long.parseLong(arrear)));
//             if(credit!=null && !credit.equals(""))
//            challan.setCredit(BigInteger.valueOf(Long.parseLong(credit)));
//            challan.setRecpNo(recp_no);
//            challan.setDisCd(dis_cd);
//             if(noc!=null && !noc.equals(""))
//            challan.setNoc(BigInteger.valueOf(Long.parseLong(noc)));
//             if(rmc!=null && !rmc.equals(""))
//            challan.setRmc(BigInteger.valueOf(Long.parseLong(rmc)));
//             if(chlsecu!=null && !chlsecu.equals(""))
//            challan.setSecu(BigInteger.valueOf(Long.parseLong(chlsecu)));
//             if(t_fee!=null && !t_fee.equals(""))
//            challan.setTFee(BigInteger.valueOf(Long.parseLong(t_fee)));
//            challan.setCreatedBy(createdBy);
//            challan.setCreatedDate(createdDate);
//            challan.setStatus("UA");
//            //challan.setCss(css);
//            challan.setBnkCd(bnk_cd);
//            challan.setBrNm(br_nm);
//            if (division.equals("1")) {
//                challan1Facade.create((Challan1) challan);
//            } else if (division.equals("2")) {
//                challan2Facade.create((Challan2) challan);
//            } else if (division.equals("3")) {
//                challan3Facade.create((Challan3) challan);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void editChallan(String division, String row_id, String bl_per_fr, String bl_per_to, String due_dt, String bill_amt, String surcharge, String paid_amt, String pay_date, String arrear, String credit, String recp_no, String dis_cd, String noc, String rmc, String chlsecu, String t_fee, String css, String bnk_cd, String br_nm,String editedBy,Date editedDate) {
//        try {
//
//            Challan challan = null;
//            challan = (Challan) em.createNamedQuery("Challan" + division + ".findById").setParameter("id", Long.parseLong(row_id)).getResultList().get(0);
//             if (bl_per_fr.equals("")) {
//                blPerFr = null;
//            } else {
//                formatter = new SimpleDateFormat("dd/MM/yy");
//                blPerFr = (Date) formatter.parse(bl_per_fr);
//            }
//            challan.setBlPerFr(blPerFr);
//            if (bl_per_to.equals("")) {
//                blPerTo = null;
//            } else {
//                formatter = new SimpleDateFormat("dd/MM/yy");
//                blPerTo = (Date) formatter.parse(bl_per_to);
//            }
//            challan.setBlPerTo(blPerTo);
//            System.out.println("due_dt========= "+due_dt);
//            if (due_dt.equals("")) {
//                dueDt = null;
//            } else {
//                formatter = new SimpleDateFormat("dd/MM/yy");
//                dueDt = (Date) formatter.parse(due_dt);
//            }
//            challan.setDueDt(dueDt);
//
//            if (bill_amt.equals("")) {
//                billAmt = null;
//            } else {
//                billAmt = BigInteger.valueOf(Long.parseLong(bill_amt));
//            }
//            challan.setBillAmt(billAmt);
//
//            if (surcharge.equals("")) {
//                Surcharge = null;
//            } else {
//                Surcharge = BigInteger.valueOf(Long.parseLong(surcharge));
//            }
//            challan.setSurcharge(Surcharge);
//            if(paid_amt.equals(""))
//            {
//                paidAmt=null;
//            }else{
//                paidAmt=BigInteger.valueOf(Long.parseLong(paid_amt));
//            }
//            challan.setPaidAmt(paidAmt);
//
//            formatter = new SimpleDateFormat("dd/MM/yy");
//            if (pay_date.equals("")) {
//                payDate = null;
//            } else {
//                formatter = new SimpleDateFormat("dd/MM/yy");
//                payDate = (Date) formatter.parse(pay_date);
//            }
//            challan.setPayDate(payDate);
//            if (arrear.equals("")) {
//                Arrear = null;
//            } else {
//                Arrear = BigInteger.valueOf(Long.parseLong(arrear));
//            }
//            challan.setArrear(Arrear);
//
//           if (credit.equals("")) {
//                Credit = null;
//            } else {
//                Credit = BigInteger.valueOf(Long.parseLong(credit));
//            }
//            challan.setCredit(Credit);
//
//             if (recp_no.equals("")) {
//                recp_no = null;
//            }
//            challan.setRecpNo(recp_no);
//
//            if (dis_cd.equals("")) {
//                dis_cd = null;
//            }
//            challan.setDisCd(dis_cd);
//
//            if (noc.equals("")) {
//                Noc = null;
//            } else {
//                Noc = BigInteger.valueOf(Long.parseLong(noc));
//            }
//            challan.setNoc(Noc);
//
//            if (rmc.equals("")) {
//                Rmc = null;
//            } else {
//                Rmc = BigInteger.valueOf(Long.parseLong(rmc));
//            }
//            challan.setRmc(Rmc);
//
//            if (chlsecu.equals("")) {
//                chlSecu = null;
//            } else {
//                chlSecu = BigInteger.valueOf(Long.parseLong(chlsecu));
//            }
//            challan.setSecu(chlSecu);
//
//            if (t_fee.equals("")) {
//                tFee = null;
//            } else {
//                tFee = BigInteger.valueOf(Long.parseLong(t_fee));
//            }
//            challan.setTFee(tFee);
//
////            if (css.equals("")) {
////                css = null;
////            }
////            challan.setCss(css);
//             if (bnk_cd.equals("")) {
//                bnk_cd = null;
//            }
//            challan.setBnkCd(bnk_cd);
//
//            if (br_nm.equals("")) {
//                br_nm = null;
//            }
//            challan.setBrNm(br_nm);
//            challan.setEditedBy(editedBy);
//            challan.setEditedDate(editedDate);
//            if (division.equals("1")) {
//                challan1Facade.edit((Challan1) challan);
//            } else if (division.equals("2")) {
//                challan2Facade.edit((Challan2) challan);
//            } else if (division.equals("3")) {
//                challan3Facade.edit((Challan3) challan);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteChallan(String division, String row_id) {
//        try {
//            if (division.equals("1")) {
//                Challan1 challan1 = new Challan1();
//                challan1 = challan1Facade.find(BigInteger.valueOf(Long.parseLong(row_id)));
//                challan1Facade.remove(challan1);
//            } else if (division.equals("2")) {
//                Challan2 challan2 = new Challan2();
//                challan2 = challan2Facade.find(BigInteger.valueOf(Long.parseLong(row_id)));
//                challan2Facade.remove(challan2);
//            } else if (division.equals("3")) {
//                Challan3 challan3 = new Challan3();
//                challan3 = challan3Facade.find(BigInteger.valueOf(Long.parseLong(row_id)));
//                challan3Facade.remove(challan3);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
