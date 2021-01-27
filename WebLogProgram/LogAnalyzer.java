
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
         records.clear();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             System.out.println("Line to parse: " + line);
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);    
         }
     }
     //Finding Unique IP Addresses    
     public int countUniqueIPs(){
       ArrayList<String> uniqueIps = new ArrayList<String>();
       for(LogEntry le : records){
          String iprAddr = le.getIpAddress();
          if(!uniqueIps.contains(iprAddr)){
              uniqueIps.add(iprAddr);
            }    
       }    
       return uniqueIps.size();
     }    
     
     public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            if (statusCode > num){
                System.out.println(statusCode);
            }
        }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)//"MMM DD" 
     {
        ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
        for (LogEntry le : records){
            Date d = le.getAccessTime();
            String dstr = d.toString();
            if (dstr.contains(someday)){
                String ipAddr = le.getIpAddress();
                if (!uniqueIPsOnDay.contains(ipAddr)){
                    uniqueIPsOnDay.add(ipAddr);
                }
            }
        }
        System.out.println(uniqueIPsOnDay.size());
        return uniqueIPsOnDay;
      }
      
     public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high){
                String ipAddr = le.getIpAddress();
                if (!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
     }       
     //Counting Website Visits   
     public HashMap countVisitsPerIP(){
       HashMap<String, Integer> counts = new HashMap<String, Integer>();
       for(LogEntry le : records){
          String iprAddr = le.getIpAddress();
          if(!counts.containsKey(iprAddr)){
              counts.put(iprAddr,1);
            }    
          else {
              counts.put(iprAddr,counts.get(iprAddr) + 1);
            }    
       }    
       return counts;
     }  
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int most = 0;
         for(String s: counts.keySet()){
             int count = counts.get(s);
             if(count > most){
                 most = count;
             }    
         }   
         return most;
     }   
        
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map) {
         ArrayList<String> iPsMostVisits = new ArrayList<String>();
         int maxVisits = mostNumberVisitsByIP(map);
         for (Integer v:map.values()) {
             for (String ip: map.keySet()) {
                 int visits = map.get(ip);
                 if(visits == maxVisits) {
                     iPsMostVisits.add(ip);
                    }
                }
            }
            return iPsMostVisits;   
       }
       
     public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
        for(LogEntry le : records){
            String key = le.getAccessTime().toString();
            key = key.substring(4,10);
            if(!hm.containsKey(key)){
                ArrayList<String> str = new ArrayList<String>();
                str.add(le.getIpAddress());
                hm.put(key,str);
            }    
            else {
                ArrayList<String> arr = hm.get(key);
                arr.add(le.getIpAddress());
                hm.put(key,arr);
            }    
        } 
        return hm;
     }  
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipMap) {
	String dateMostVisited = "";
	int mostIPsOnDay = 0;
	for (String key : ipMap.keySet()) {
		if (ipMap.get(key).size() > mostIPsOnDay) {
			mostIPsOnDay = ipMap.get(key).size();
			dateMostVisited = key;
		}
	}
	return dateMostVisited;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dates, String day){
         
         //Make an ArrayList with the ip addresses that occur on your day parameter in the HashMap
         ArrayList<String> visitsOnDay = dates.get(day);
         
         //Make a NEW HashMap to hold the unique IP addresses and the number of times they occur
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         
         //Now fill this HashMap using the same algorithm we've been using for the previous few assignments
         for(String ip : visitsOnDay){
             if(!ipCounts.containsKey(ip)){
                 ipCounts.put(ip, 1);
             }
             else{
                 ipCounts.put(ip, ipCounts.get(ip) + 1);
             }
         }
         
         //Now, the answer is as simple as calling iPsMostVisits (which returns an ArrayList<String>) on the HashMap you just made!
         return iPsMostVisits(ipCounts);
     }

}
