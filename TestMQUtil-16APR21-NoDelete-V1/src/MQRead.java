

import com.ibm.mq.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class MQRead
{
	FileReader reader = null;
	Properties p = new Properties();
   private MQQueueManager _queueManager = null;
   private Hashtable params = null;
   public int port = 1438;
   public String hostname   = "";//"172.18.209.136";
   public String channel    = "";//"SYSTEM.DEF.SVRCONN";
   public String qManager    = "";//"IHG1.2200QMGR_AC_";
   public String inputQName = "";//"cfdsdsradvisory";//"cfdlrp";//"cfdsdsradvisory";
//   public String inputQName_CfdSsimErrorQueue = "";//"cfdssimerror";

 public MQRead()
 {
   super();
 }
 
 
 // Through Arguments
 /*
 private boolean allParamsPresent()
 {
   boolean b = params.containsKey("-h") &&
     params.containsKey("-p") &&
     params.containsKey("-c") &&
     params.containsKey("-m") &&
     params.containsKey("-q");
   if (b)
   {
     try
     {
      port = Integer.parseInt((String) params.get("-p"));
     }
     catch (NumberFormatException e)
     {
      b = false;
     }
            // Set up MQ environment
     hostname = (String) params.get("-h");
     channel  = (String) params.get("-c");
     qManager = (String) params.get("-m");
     inputQName = (String) params.get("-q");

   }
   return b;
 }
 
*/ 
 private void init(String[] args) throws IllegalArgumentException
 {
	 
/*
   params = new Hashtable(5);
   if (args.length > 0 && (args.length % 2) == 0)
   {
     for (int i = 0; i < args.length; i+=2)
     {
      params.put(args[i], args[i+1]);
     }
   }
   else
   {
     throw new IllegalArgumentException();
   }
   if (allParamsPresent())
   {
       // Set up MQ environment
     MQEnvironment.hostname = hostname;
     MQEnvironment.channel  = channel;
     MQEnvironment.port     = port;

   }
   else
   {
     throw new IllegalArgumentException();
   }
   
   */
	 String inputCmd = args[0];
	 
	 try
	 {
		 if(inputCmd.equalsIgnoreCase("DL"))
		 {
			 reader = new FileReader("dl.properties");
			 p.load(reader);
			 System.out.println(p.getProperty("hostname")+"\t"+p.getProperty("qm")+"\t"+p.getProperty("port")+"\t"+p.getProperty("channel")+"\t"+p.getProperty("queue1"));
			 MQEnvironment.hostname = p.getProperty("hostname");
		     MQEnvironment.channel  = p.getProperty("channel");
		     MQEnvironment.port     = Integer.parseInt(p.getProperty("port"));
		     qManager = p.getProperty("qm");
		     inputQName = p.getProperty("queue1");
		 }
		 else if(inputCmd.equalsIgnoreCase("AC"))
		 {
			 reader = new FileReader("ac.properties");
			 p.load(reader);
			 System.out.println(p.getProperty("hostname")+"\t"+p.getProperty("qm")+"\t"+p.getProperty("port")+"\t"+p.getProperty("channel")+"\t"+p.getProperty("queue1"));
			 MQEnvironment.hostname = p.getProperty("hostname");
		     MQEnvironment.channel  = p.getProperty("channel");
		     MQEnvironment.port     = Integer.parseInt(p.getProperty("port"));
		     qManager = p.getProperty("qm");
		     inputQName = p.getProperty("queue1");
		 }
		 else if(inputCmd.equalsIgnoreCase("MH"))
		 {
			 reader = new FileReader("mh.properties");
			 p.load(reader);
			 System.out.println(p.getProperty("hostname")+"\t"+p.getProperty("qm")+"\t"+p.getProperty("port")+"\t"+p.getProperty("channel")+"\t"+p.getProperty("queue1"));
			 MQEnvironment.hostname = p.getProperty("hostname");
		     MQEnvironment.channel  = p.getProperty("channel");
		     MQEnvironment.port     = Integer.parseInt(p.getProperty("port"));
		     qManager = p.getProperty("qm");
		     inputQName = p.getProperty("queue1");
		 }
		 else if(inputCmd.equalsIgnoreCase("SK"))
		 {
			 reader = new FileReader("sk.properties");
			 p.load(reader);
			 System.out.println(p.getProperty("hostname")+"\t"+p.getProperty("qm")+"\t"+p.getProperty("port")+"\t"+p.getProperty("channel")+"\t"+p.getProperty("queue1"));
			 MQEnvironment.hostname = p.getProperty("hostname");
		     MQEnvironment.channel  = p.getProperty("channel");
		     MQEnvironment.port     = Integer.parseInt(p.getProperty("port"));
		     qManager = p.getProperty("qm");
		     inputQName = p.getProperty("queue1");
		 }
		 else if(inputCmd.equalsIgnoreCase("NZ"))
		 {
			 reader = new FileReader("nz.properties");
			 p.load(reader);
			 System.out.println(p.getProperty("hostname")+"\t"+p.getProperty("qm")+"\t"+p.getProperty("port")+"\t"+p.getProperty("channel")+"\t"+p.getProperty("queue1"));
			 MQEnvironment.hostname = p.getProperty("hostname");
		     MQEnvironment.channel  = p.getProperty("channel");
		     MQEnvironment.port     = Integer.parseInt(p.getProperty("port"));
		     qManager = p.getProperty("qm");
		     inputQName = p.getProperty("queue1");
		 }
	 }
	 catch(Exception e)
	 {
		 
	 }
   
	 //Commented on 16th APR 2020
	/* MQEnvironment.hostname = hostname;
     MQEnvironment.channel  = channel;
     MQEnvironment.port     = port;*/

 }
 
 /*
 private void initCfdSsimErrorQueue(String[] args) throws IllegalArgumentException
 {
	 

   
	 MQEnvironment.hostname = hostname;
     MQEnvironment.channel  = channel;
     MQEnvironment.port     = port;

 }
 
 */
 
 public static void main(String[] args)
 {

   MQRead readQ = new MQRead();

   try
   {
     /*readQ.init(args);
     readQ.selectQMgr();
     readQ.read();*/
     
     readQ.init(args);
     readQ.selectQMgr();
     readQ.readCfdSsimErrorQueue();
//     readQ.writeToExcel();
   }
   catch (IllegalArgumentException e)
   {
     System.out.println("Usage: java MQRead <-h host> <-p port> <-c channel> <-m QueueManagerName> <-q QueueName>");
     System.exit(1);
   }
   catch (MQException e)
   {
     System.out.println(e);
     System.exit(1);
   }
 }
 
 private void readCfdSsimErrorQueue() throws MQException
 {
   int openOptions = MQC.MQOO_INQUIRE + MQC.MQOO_FAIL_IF_QUIESCING +  MQC.MQOO_BROWSE;//MQC.MQOO_INQUIRE + MQC.MQOO_FAIL_IF_QUIESCING + MQC.MQOO_INPUT_SHARED;

   MQQueue queue = _queueManager.accessQueue( inputQName,
                                   openOptions,
                                   null,           // default q manager
                                   null,           // no dynamic q name
                                   null );         // no alternate user id


   System.out.println("MQRead v1.0 connected.\n");

   int depth = queue.getCurrentDepth();
   System.out.println("Current depth: " + depth + "\n");
   if (depth == 0)
   {
     return;
   }

   MQGetMessageOptions getOptions = new MQGetMessageOptions();
   getOptions.options = MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_CONVERT + MQC.MQGMO_BROWSE_NEXT;//MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_CONVERT;
   
   int count1 = 1;
   int count2 = 0;
   Map<String, Object[]> queueData = new TreeMap<String, Object[]>(); 
//   queueData.put(String.valueOf(count1), new Object[]{ "ID", "Text", "Queue Name" }); 
   queueData.put(String.valueOf(count1), new Object[]{"", "Queue Name", "Host Airline", "Timestamp", "Reason Text", "Message Text"}); 

   while(true)
   {
     MQMessage message = new MQMessage();
     try
     {
      queue.get(message, getOptions);
      byte[] b = new byte[message.getMessageLength()];
      message.readFully(b);
      String msgTxt = new String(b);
      
      String text = StringUtils.substringBetween(msgTxt, "<ErrorText>", "</ErrorText>");
      String queueName = StringUtils.substringBetween(msgTxt, "<QueueName>", "</QueueName>");
      String timestampTemp = StringUtils.substringBetween(msgTxt, "<Timestamp>", "</Timestamp>");
      String hostAirline = StringUtils.substringBetween(msgTxt, "<HostAirline>", "</HostAirline>");
      
      String messageTxt = StringUtils.substringAfter(msgTxt, "</usr>");
      
      long yourmilliseconds = Long.parseLong(timestampTemp, 10); //System.currentTimeMillis();
      SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
      Date resultdate = new Date(yourmilliseconds);
      
      String timestamp = sdf.format(resultdate);
//      System.out.println(new String(b)+"\n######Next Messege#####\n");
      
      System.out.println("\n"+msgTxt+"\n");
//      System.out.println("\n"+text+"\n");
      
//      count = count + 1;
//      ++count;
      
//      queueData.put(String.valueOf(++count1), new Object[] {String.valueOf(++count2), text, queueName});
      queueData.put(String.valueOf(++count1), new Object[] {null, queueName, hostAirline, timestamp, text, messageTxt});

      
      
      message.clearMessage();
     }
     catch (IOException e)
     {
      System.out.println("IOException during GET: " + e.getMessage());
      break;
     }
     catch (MQException e)
     {
      if (e.completionCode == 2 && e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) {
        if (depth > 0)
        {
         System.out.println("All messages read.");
        }
      }
      else
      {
        System.out.println("GET Exception: " + e);
      }
      break;
     }
   }
   
   //Sorting
   /*
   Map<String, Object[]>  sortedMap = new TreeMap<String, Object[]>();
   
   queueData.entrySet()
       .stream()
       .sorted(Map.Entry.comparingByKey())
       .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
   
   */
   MQRead readQ1 = new MQRead();
   readQ1.writeToExcelCfdSsimErrorQueue(queueData);
//   readQ1.writeToExcel(sortedMap);


   queue.close();
   _queueManager.disconnect();
   
 }
 
 
 
 
 /*
 private void read() throws MQException
 {
   int openOptions = MQC.MQOO_INQUIRE + MQC.MQOO_FAIL_IF_QUIESCING +  MQC.MQOO_BROWSE;//MQC.MQOO_INQUIRE + MQC.MQOO_FAIL_IF_QUIESCING + MQC.MQOO_INPUT_SHARED;

   MQQueue queue = _queueManager.accessQueue( inputQName,
                                   openOptions,
                                   null,           // default q manager
                                   null,           // no dynamic q name
                                   null );         // no alternate user id


   System.out.println("MQRead v1.0 connected.\n");

   int depth = queue.getCurrentDepth();
   System.out.println("Current depth: " + depth + "\n");
   if (depth == 0)
   {
     return;
   }

   MQGetMessageOptions getOptions = new MQGetMessageOptions();
   getOptions.options = MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_CONVERT + MQC.MQGMO_BROWSE_NEXT;//MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_CONVERT;
   
   int count1 = 1;
   int count2 = 0;
   Map<String, Object[]> queueData = new TreeMap<String, Object[]>(); 
//   queueData.put(String.valueOf(count1), new Object[]{ "ID", "Text", "Queue Name" }); 
   queueData.put(String.valueOf(count1), new Object[]{"", "Queue Name", "Host Airline", "Timestamp", "Message Text"}); 

   while(true)
   {
     MQMessage message = new MQMessage();
     try
     {
      queue.get(message, getOptions);
      byte[] b = new byte[message.getMessageLength()];
      message.readFully(b);
      String msgTxt = new String(b);
      
      String text = StringUtils.substringBetween(msgTxt, "<ErrorText>", "</ErrorText>");
      String queueName = StringUtils.substringBetween(msgTxt, "<QueueName>", "</QueueName>");
      String timestampTemp = StringUtils.substringBetween(msgTxt, "<Timestamp>", "</Timestamp>");
      String hostAirline = StringUtils.substringBetween(msgTxt, "<HostAirline>", "</HostAirline>");
      
      long yourmilliseconds = Long.parseLong(timestampTemp, 10); //System.currentTimeMillis();
      SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
      Date resultdate = new Date(yourmilliseconds);
      
      String timestamp = sdf.format(resultdate);
//      System.out.println(new String(b)+"\n######Next Messege#####\n");
      
      System.out.println("\n"+msgTxt+"\n");
//      System.out.println("\n"+text+"\n");
      
//      count = count + 1;
//      ++count;
      
//      queueData.put(String.valueOf(++count1), new Object[] {String.valueOf(++count2), text, queueName});
      queueData.put(String.valueOf(++count1), new Object[] {null, queueName, hostAirline, timestamp, text});

      
      
      message.clearMessage();
     }
     catch (IOException e)
     {
      System.out.println("IOException during GET: " + e.getMessage());
      break;
     }
     catch (MQException e)
     {
      if (e.completionCode == 2 && e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) {
        if (depth > 0)
        {
         System.out.println("All messages read.");
        }
      }
      else
      {
        System.out.println("GET Exception: " + e);
      }
      break;
     }
   }
   
   //Sorting//
   
  // Map<String, Object[]>  sortedMap = new TreeMap<String, Object[]>();
   
  // queueData.entrySet()
       .stream()
       .sorted(Map.Entry.comparingByKey())
       .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
   
   
   MQRead readQ1 = new MQRead();
   readQ1.writeToExcel(queueData);
//   readQ1.writeToExcel(sortedMap);


   queue.close();
   _queueManager.disconnect();
   
 }
 */
 
 private void selectQMgr() throws MQException
 {
   _queueManager = new MQQueueManager(qManager);
 }
 
 public void writeToExcelCfdSsimErrorQueue(Map<String, Object[]> data)
 {	 
	  // Blank workbook 
     XSSFWorkbook workbook = new XSSFWorkbook(); 
	 
	 // Create a blank sheet 
     XSSFSheet sheet = workbook.createSheet("Details"); 

     // Iterate over data and write to sheet 
     Set<String> keyset = data.keySet(); 
     int rownum = 0; 
     for (String key : keyset) { 
         // this creates a new row in the sheet 
         Row row = sheet.createRow(rownum++); 
         Object[] objArr = data.get(key); 
         int cellnum = 0; 
         for (Object obj : objArr) { 
             // this line creates a cell in the next column of that row 
             Cell cell = row.createCell(cellnum++); 
             if (obj instanceof String) 
                 cell.setCellValue((String)obj); 
             else if (obj instanceof Integer) 
                 cell.setCellValue((Integer)obj); 
         } 
     } 
     try { 
         
         FileOutputStream out = new FileOutputStream(new File("QueueData_CfdSsimErrorQueue.xlsx")); 

         workbook.write(out); 
         out.close(); 
         System.out.println("QueueData_CfdSsimErrorQueue.xlsx written successfully on disk."); 
     } 
     catch (Exception e) { 
         e.printStackTrace(); 
     } 
 }
 
 
 /*
 public void writeToExcel(Map<String, Object[]> data)
 {	 
	  // Blank workbook 
     XSSFWorkbook workbook = new XSSFWorkbook(); 
	 
	 // Create a blank sheet 
     XSSFSheet sheet = workbook.createSheet("Details"); 

     // Iterate over data and write to sheet 
     Set<String> keyset = data.keySet(); 
     int rownum = 0; 
     for (String key : keyset) { 
         // this creates a new row in the sheet 
         Row row = sheet.createRow(rownum++); 
         Object[] objArr = data.get(key); 
         int cellnum = 0; 
         for (Object obj : objArr) { 
             // this line creates a cell in the next column of that row 
             Cell cell = row.createCell(cellnum++); 
             if (obj instanceof String) 
                 cell.setCellValue((String)obj); 
             else if (obj instanceof Integer) 
                 cell.setCellValue((Integer)obj); 
         } 
     } 
     try { 
         
         FileOutputStream out = new FileOutputStream(new File("QueueData.xlsx")); 

         workbook.write(out); 
         out.close(); 
         System.out.println("QueueData.xlsx written successfully on disk."); 
     } 
     catch (Exception e) { 
         e.printStackTrace(); 
     } 
 }*/
 
}
