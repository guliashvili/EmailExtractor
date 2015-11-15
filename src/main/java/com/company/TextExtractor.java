package com.company;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by gguliash on 11/14/15.
 */
public class TextExtractor {

    private static final Tika tika = new Tika();
    public static String getText(File file) {
        try {

            String ret = tika.parseToString(file).toLowerCase();
            //System.out.println(ret);
            return ret;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        //return null;
    }
}
