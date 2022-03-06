/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translateaplication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class TranslateAplication {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {
        // TODO code application logic here
        
        Translator translator = new Translator();
        boolean finish = false;
        System.out.println("WELCOME TO SAMUEL RODRIGUEZ'S TRANSLATOR APP");
        System.out.println("--------------------------------------------");
        
        while (!finish){
            System.out.println("\n1. Get available languages");
            System.out.println("2. Translate");
            System.out.println("3. Exit");
            Scanner sc = new Scanner(System.in);
            
            int opt = sc.nextInt();
            
            switch (opt){
                case 1: // Show available languages
                    System.out.println(translator.showLanguages());
                    break;
                case 2: // Translate a text
                    sc.nextLine();
                    System.out.print("Source Languague: ");
                    String from = sc.nextLine();
                    System.out.print("Target Languague: ");
                    String to = sc.nextLine();
                    System.out.print("Text to translate: ");
                    String text = sc.nextLine();
                    System.out.print(translator.translate(from, to, text) + "\n");
                    break;
                case 3: // Exit
                    finish = true;
                    break;
                    
                default:
                    System.out.println("Invalid Option");
            }
        }
        
        
    }
}
