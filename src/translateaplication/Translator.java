/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translateaplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that make a HTTP request to get the translate of a word
 * @author Samuel
 */
public class Translator {
    
    static HashMap<String, String> languages;
    
    public Translator() {
        languages = new HashMap<>();
        
        // Setting the languages and their codes
        languages.put("English", "en");
        languages.put("German", "de");
        languages.put("French", "fr");
        languages.put("Spanish", "es");
    }
   
    /**
     * This function translate a word
     * @param from Source Language
     * @param to   Target Language
     * @param text Texto to translate
     * @return
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException 
     */
    public String translate(String from, String to, String text) throws MalformedURLException, ProtocolException, IOException{
        
        if(!languages.containsKey(from) || !languages.containsKey(to)){
            return "Language not available";
        }
        
        // URL of translate web page
        String url_string = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" +
                languages.get(from) + "&tl=" + languages.get(to) + "&dt=t&q=" + text.replace(" ", "\\");

        /*
            Creating conexion and setting it's properties and method
        */
        URL url = new URL(url_string);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        
        /*
            Settings conexion's parameters
        */
        con.setRequestProperty("sl", from);
        con.setRequestProperty("tl", to);
        con.setRequestProperty("q", text);
        
        /*
            Reading response
        */
        StringBuilder response = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        br.close();
        String[] result = response.toString().split(",");
        return result[0].substring(4, result[0].length()-1);
    }
    
    public String showLanguages(){
        System.out.println("AVAILABLE LANGUAGUES: ");
        System.out.println("----------------------");
        String result = "";

        for (Map.Entry<String, String> entry : languages.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();

            result += "> " + key + "\n";
        }
        return result;
    }
}
