
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        // complete method
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog2_log");
        int uniqueIps = la.countUniqueIPs();
        System.out.println("There are " + uniqueIps + " Ips");
    }
    
    public void testPrintAllHigherThanNum() {
        // complete method
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
  
    }
    
    public void testUIPVOD(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 27"));
    }
    
    public void testCUIPIR(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPsInRange(400,499));
    }
    
    public void testcountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog1_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testmostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog2_log");
        int max = la.mostNumberVisitsByIP(la.countVisitsPerIP());
        System.out.println("max visits: "+max);
    }
    
    public void testiPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog2_log");
        System.out.println(la.iPsMostVisits(la.countVisitsPerIP()));
    }
    
    public void testiPsForDays() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog1_log");
        System.out.println(la.iPsForDays());
    }
    
    public void testdayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog2_log");
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
    }
    
    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog2_log");
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(),"Sep 30"));
    }
}
