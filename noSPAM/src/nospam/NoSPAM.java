/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nospam;

import IndonesianNLP.*;
import com.opencsv.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author theaolivia
 */
public class NoSPAM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // convert CSV to List
            CSVReader reader;
            reader = new CSVReader(new FileReader("dataset.csv"));
            List myEntries = reader.readAll();
            
//            for (int i=0; i < myEntries.size(); i++){
//                System.out.println(myEntries.get(i));                
//            }
            System.out.println("SSSSS");
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
               // nextLine[] is an array of values from the line
               System.out.println(nextLine[0]);
            }
        } catch (IOException ex) {
            Logger.getLogger(NoSPAM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
