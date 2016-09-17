/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tesinanlp;

import IndonesianNLP.IndonesianNETagger;
import IndonesianNLP.IndonesianPOSTagger;
import IndonesianNLP.IndonesianPhraseChunker;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianStemmer;
import IndonesianSyntacticParser.CYKParser;
import IndonesianSemanticAnalyzer.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class TesInaNLP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       // IndonesianNETagger inner = new IndonesianNETagger();
       // inner.NETagFile("./data/tmp3.txt", "./data/tmpout.txt");

try {

            System.out.println("Demo NLP Tools utk bahasa Indonesia");
        IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
        System.out.println("*************************");
        System.out.println("Formalisasi Kata");
        
        String sentence = "kata2nya 4ku donk loecoe bangedh gt .";
        System.out.println(sentence);
        System.out.println(formalizer.formalizeSentence(sentence));
        
            System.out.println("*************************");
            System.out.println("Indonesian STEMMER");
        
            IndonesianStemmer stemmer = new IndonesianStemmer();

        String word = "memperbantukannya";
        System.out.println("Kata masukan:"+word);
        
        System.out.println("Kata dasar: " + stemmer.stem(word));

        for(int i = 0; i < stemmer.derivationalprefix.size(); i++){
            System.out.println("Derivational Prefix: " + stemmer.derivationalprefix.get(i));
        }
        
        System.out.println("Particle Suffix: " + stemmer.particlesuffix);
        System.out.println("Possessive Pronoun Suffix : " + stemmer.possessivepronounsuffix);

        System.out.println("Derivational Suffix : " + stemmer.derivationalsuffix);

    
    
        CYKParser parser = new CYKParser("./resource/syntacticparser/grammarCNF.txt");
            System.out.println("*************************");
            
            sentence = "Pak SBY bertemu dengan Bu Mega .";
            System.out.println("isi kalimat:"+sentence);
            ArrayList<String[]> posTag = IndonesianPOSTagger.doPOSTag(sentence);
            
            
            
            System.out.println("*************************");
            System.out.println("Hasil POS Tagger:");
            for(int i = 0; i < posTag.size(); i++){
                System.out.print(posTag.get(i)[0] + "/" + posTag.get(i)[1]+" ");
            }            
            
            System.out.println("\n*************************");
            IndonesianPhraseChunker chunker = new IndonesianPhraseChunker();
            chunker.setSentence(sentence);
            chunker.extractPhrase();
            System.out.println("Hasil Phrase Chunker:");
            chunker.printResult();
            
            parser.setSentence(sentence);

            parser.CYKParsing();
            parser.getTree();
            String stringTree = parser.printBestTreeToString();
            System.out.println("\n*************************");
            System.out.println("Hasil pohon sintaksis:");
            System.out.println(parser.printBestTreeToString());
            System.out.println("*************************");
            System.out.println("Hasil semantic analyzer:");
            SemanticElmt elmt = new SemanticElmt(sentence, stringTree, "./resource/semanticanalyzer/ruleFile.txt");
            System.out.println(elmt.PrintLexicalSemantic());
            //System.out.println("*************************");
            //IndonesianNETagger inner = new IndonesianNETagger();
            //inner.NETagFile("./data/sample.txt", "./data/tmpout.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SemanticElmt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SemanticElmt.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        
    }
    
}
