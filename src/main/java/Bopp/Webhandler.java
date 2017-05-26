package Bopp;


import Bopp.Data.Field;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Copyright (C) 2017 FF6EB4
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/**
 *
 * @author FF6EB4
 */
public class Webhandler {
    public static Field<ArrayList<String>> fallback = 
            new Field<>("WEBHANDLER","MEMES",new ArrayList<>());
    
    public static Field<Long> lastTime = 
            new Field<>("WEBHANDLER","TIME",(long)0);
    
    public static ArrayList<String> getDankMemes() {
        if((System.currentTimeMillis() - lastTime.data) < 1000*60*20){
            return fallback.data;
        } else {
            System.out.println("ATTEMPTING MEME OVERLOAD...");
            lastTime.data = System.currentTimeMillis();
            lastTime.write();
        }
        String content = null;
        URLConnection connection = null;
        try {
          connection =  new URL("https://www.reddit.com/r/dankmemes/new").openConnection();
          Scanner scanner = new Scanner(connection.getInputStream());
          scanner.useDelimiter("\\Z");
          content = scanner.next();
        }catch ( Exception ex ) {
            System.out.println("FAILURE TO LOAD DANK MEMES!");
            return fallback.data;
        }
        ArrayList<String> ret = (ArrayList<String>)extractUrls(content);
        
        
        System.out.println("FINDING DANKEST MEMES");
        for(int i = ret.size() - 1; i >= 0; --i){
            String s = ret.get(i);
            //System.out.println(s);
            if(s.contains("&quot") || !(s.contains("imgur")||s.contains("i.redd.it"))){
                ret.remove(s);
            }
        }
        
        fallback.data = ret;
        fallback.write();
        
        return ret;
    }
    
    	
/**
 * Returns a list with all links contained in the input
 */
private static List<String> extractUrls(String text)
{
    List<String> containedUrls = new ArrayList<String>();
    String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
    Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
    Matcher urlMatcher = pattern.matcher(text);

    while (urlMatcher.find())
    {
        containedUrls.add(text.substring(urlMatcher.start(0),
                urlMatcher.end(0)));
    }

    return containedUrls;
}
}
