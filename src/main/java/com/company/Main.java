package com.company;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        try{
            new File("unresolved").delete();
            new File("unresolved").mkdir();
        }catch (Exception e){

        }

        EmailExtractor emailExtractor = new EmailExtractor("Cvs");
        List<String> emails = emailExtractor.getEmails();

        File f = new File("out.txt");
        if(!f.exists()) {
            try{
                f.createNewFile();
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }

        try(FileOutputStream fos = new FileOutputStream(f)){
            try (OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8")){
                try (BufferedWriter bw = new BufferedWriter(osw)) {
                    for (String s : emails) bw.append(s + "\n");
                }
            }

        }catch (Exception e){

        }


        for(String email : emails){
            //System.out.println(email);
        }
        System.out.println(emails.size());


    }
}
