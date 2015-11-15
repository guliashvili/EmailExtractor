package com.company;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;
/**
 * Created by gguliash on 11/13/15.
 */
public class EmailExtractor {



    int error = 0;
    int couldnot = 0;
    int differentExt = 0;



    private void recursive(List<File> files,File current){
        if(current.isFile()) files.add(current);
        else{
            for(File file : current.listFiles()){
                recursive(files,file);
            }
        }
    }

    private String folder;

    public EmailExtractor(String folder){
        this.folder = folder;
    }

    private List<File> getFiles(){
        List<File> lst = new ArrayList<>();
        recursive(lst,new File(folder));
        return lst;
    }





    private List<String> getEmails(List<File> files){
        List<String> ret = new ArrayList<>();
        for(File file : files) {
            String text = TextExtractor.getText(file);
            if(text == null) error++;
            else {

                List<String> get = EmailFinder.getEmail(text);
                if (get != null)
                    ret.addAll(get);
                if (get == null || get.size() == 0) {
                    couldnot++;
                    System.out.println(file.getPath());
                    System.out.println(text);
                }
            }
        }
        return ret;
    }

    private void makeListUnique(List<String> emails){
        emails.sort((a,b) -> a.compareTo(b));
        int k = -1;
        for(int i = 0; i < emails.size(); i++) {
            if(k == -1 || !emails.get(i).equals(emails.get(k))) k++;
            emails.set(k,emails.get(i));
        }
        while(k+1 < emails.size()) emails.remove(emails.size()-1);
    }

    public List<String> getEmails(){
        List<File> files = getFiles();
        List<String> emails = getEmails(files);
        makeListUnique(emails);

        System.out.println("couldnot = " + couldnot + " ; error = " + error + " ; differentExt = " + differentExt);

        return emails;
    }
}
