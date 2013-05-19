/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smp.manager;

import com.smp.gda.FileGeneric;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 *
 * @author smp
 */
@Stateless
public class FileManager {

    List<FileGeneric> fileList = new ArrayList<FileGeneric>();
    DecimalFormat df = new DecimalFormat("0.0");
    public static List<String> newfileList = new ArrayList<String>();
    static int spc_count = -1;

    public boolean rmdir(final File folder) {
        // check if folder file is a real folder
        boolean flag = false;
        if (folder.isDirectory()) {
            File[] list = folder.listFiles();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    File tmpF = list[i];
                    if (tmpF.isDirectory()) {
                        rmdir(tmpF);
                    }
                    tmpF.delete();
                }
            }
            if (!folder.delete()) {
                System.out.println("can't delete folder : " + folder);
                flag = false;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    public List<FileGeneric> getFileList(String dirpath) {
        File f1 = new File(dirpath);
        File[] strFilesDirs = f1.listFiles();
        System.out.println("length of dir=====>" + strFilesDirs.length);
        FileGeneric fIleGeneric = new FileGeneric();
        fileList.clear();

        for (int i = 0; i < strFilesDirs.length; i++) {
            fIleGeneric = new FileGeneric();
            fIleGeneric.setParent(dirpath);
            if (strFilesDirs[i].isDirectory()) {
                fIleGeneric.setIsDir(1);

            } else {
                fIleGeneric.setIsDir(0);

            }
            if (strFilesDirs[i].isFile()) {
                fIleGeneric.setIsFile(1);

            } else {
                fIleGeneric.setIsFile(0);

            }


            String fileName = strFilesDirs[i].getName();
            fIleGeneric.setFileFullName(fileName);
            if (fileName.lastIndexOf(".") > 0) {
                fIleGeneric.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
            } else {
                fIleGeneric.setFileName(fileName);
            }
            double fileSize = strFilesDirs[i].length();
            fIleGeneric.setSize((long) fileSize);
            System.out.println("filesize=>" + fileSize);
            if ((fileSize / 1024) < 1024) {

                fIleGeneric.setFileSize(df.format(fileSize / (1024)) + "KB");
            }
            if ((fileSize / 1024) >= 1024) {

                fIleGeneric.setFileSize(df.format(fileSize / (1024 * 1024)) + "MB");
            }


            if (fileName.lastIndexOf(".") > 0) {
                fIleGeneric.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
            } else {
                fIleGeneric.setFileType("Unknown");
            }

            long datetime = strFilesDirs[i].lastModified();
            Date d = new Date(datetime);
            fIleGeneric.setLastUpdate(d);
            fileList.add(fIleGeneric);


        }
        return fileList;

    }

    public String getFolderSize(List<FileGeneric> fileList) {

        long totalsize = 0;
        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).getIsFile() == 1) {
                totalsize += fileList.get(i).getSize();
            }

        }
        System.out.println("totalsize====>" + totalsize);
        String fileSize = convertFileSize(totalsize);
        return fileSize;


    }

    static String convertFileSize(long size) {
        int divisor = 1;
        String unit = "bytes";
        if (size >= (1024 * 1024 * 1024)) {
            divisor = 1024 * 1024 * 1024;
            unit = "GB";
        } else if (size >= 1024 * 1024) {
            divisor = 1024 * 1024;
            unit = "MB";
        } else if (size >= 1024) {
            divisor = 1024;
            unit = "KB";
        }
        if (divisor == 1) {
            return size / divisor + " " + unit;
        }
        String aftercomma = "" + 100 * (size % divisor) / divisor;
        if (aftercomma.length() == 1) {
            aftercomma = "0" + aftercomma;
        }
        return size / divisor + "." + aftercomma + " " + unit;
    }

    public int totalFile(List<FileGeneric> fileList) {
        int totalfile = 0;
        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i).getIsFile() == 1) {
                totalfile = totalfile + 1;
            }

        }
        return totalfile;

    }

    public void addDir(File dirObj, String currentCode, ZipOutputStream zip) throws IOException {
        if (dirObj.isDirectory()) {
            File[] files = dirObj.listFiles();
            byte[] tmpBuf = new byte[1024 * 8];
            String path;

            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    addDir(files[i], currentCode, zip);
                    continue;
                }
                FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
                System.out.println(" Adding: " + files[i].getAbsolutePath());
                path = files[i].getAbsolutePath().substring(files[i].getAbsolutePath().indexOf(currentCode));
                System.out.println(" Adding: " + path);
                zip.putNextEntry(new ZipEntry(path));
                int len;
                int bytesBuffered = 0;
                while ((len = in.read(tmpBuf)) > 0) {
                    zip.write(tmpBuf, 0, len);
                    bytesBuffered += len;
                    if (bytesBuffered > 1024 * 1024) { //flush after 1mb
                        bytesBuffered = 0;
                        zip.flush();

                    }
                }
                zip.closeEntry();
                zip.flush();
                in.close();
            }
        } else {


            System.out.println("inside else file+++ " + dirObj);
            boolean exists = dirObj.exists();
            if (exists) {

                long fileSize = dirObj.length();
                int buffersize = (int) fileSize;
                byte[] buf = new byte[buffersize];

                int len;
                FileInputStream fin = new FileInputStream(dirObj);
                zip.putNextEntry(new ZipEntry(currentCode));
                int bytesread = 0, bytesBuffered = 0;
                while ((bytesread = fin.read(buf)) > -1) {
                    zip.write(buf, 0, bytesread);
                    bytesBuffered += bytesread;
                    if (bytesBuffered > 1024 * 1024) { //flush after 1mb
                        bytesBuffered = 0;
                        zip.flush();

                    }
                }
                zip.closeEntry();
                zip.flush();
                fin.close();
            }

        }
    }
}
