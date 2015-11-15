package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gguliash on 11/14/15.
 */
public class EmailFinder {

    private static List<String> getEmailFromSmallText(String part){
        int mid = part.indexOf('@');
        int mid1 = part.indexOf('.',mid);
        if(mid == -1 || mid1 == -1) return null;
        int s = mid-1,e = -1;

        while(s >= 0 && (Character.isAlphabetic(part.charAt(s)) || Character.isDigit(part.charAt(s)))) s--;
        s++;

        for(String domain : EmailChecker.domains){

            if(part.startsWith(domain,mid1+1)){
                e = mid1 + domain.length() + 1;
                break;
            }
        }
        if(e == -1 ) return null;

        List<String> ret = new ArrayList<>();
        String candidate = part.substring(s,e + 1);
        ret.add(candidate);
        ret = EmailChecker.validate(ret);

        return ret;
    }
    private static List<String> getEmailDeepAnalysis(String text){
        List<String> ret = new ArrayList<>();

        int index = 0;
        while (true){
            int lastIndex = index;
            index = text.indexOf('@',index) + 1;
            if(index == 0) break;

            String smallPart = text.substring(Math.max(index-20,lastIndex),Math.min(text.length(),index + 20));
            List<String> emails = getEmailFromSmallText(smallPart);
            if(emails != null) ret.addAll(emails);

        }
        if(ret.size() > 0){
            //System.out.println(ret.get(0));
        }else {
            //System.out.println(text);
        }
        return ret;
    }

    public static List<String> getEmail(String text){
        String[] tokens = text.split("\\s+");
        List<String> ls = Arrays.asList(tokens);

        List<String> filtered = EmailChecker.validate(ls);
        if(filtered == null || filtered.size() == 0)
            filtered = getEmailDeepAnalysis(text);

        return filtered;
    }
}
