package regionWiseMA;

import java.io.File;
import java.io.IOException;  
import java.util.*;  
 
import org.apache.hadoop.fs.Path;  
import org.apache.hadoop.conf.*;  
import org.apache.hadoop.io.*;  
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.jobcontrol.Job;
import org.apache.hadoop.util.*;

import model.Consumption;  
 
/**
 * Finding daily consumption of an appliance
 * @author Devashri
 *
 */
public class RegionWise {  
 
   public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, DoubleWritable> {  
     
	 private Text myKey = new Text();  
 
     public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {  
       String line = value.toString();  
      
    //   String date = getDate(line);
       String month = getMonth(line);
       List<String> cols = Arrays.asList(line.split(","));
       Consumption consumption = new Consumption(cols);
       //myKey.set(date);
       try{
    	   myKey.set(month);
    	  // myKey.set(date);
           Double valueConsumedByAppliance = Double.parseDouble(consumption.getHeating());
           DoubleWritable val = new DoubleWritable(valueConsumedByAppliance);
          
           
           output.collect(myKey, val); 
       }catch(Exception e){
    	   System.out.println("Header");
       }
      
      }  
     
     public String getDate(String line){
    	 return line.substring(0,6);
     }
     
     public String getMonth(String line){
    	 return line.substring(0,3);
     }
   }  
 
   public static class Reduce extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable> {  
     public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {  
       double sum = 0;  
       int count =0;
       while (values.hasNext()) { 
    	   count++;
    	   sum += values.next().get();  
       }  
      double average = sum/count;
       output.collect(key, new DoubleWritable(average)); 
     }  
   }  
 
   public static void main(String[] args) throws Exception {  
	   
	  
     JobConf conf = new JobConf(RegionWise.class);  
     conf.setJobName("regionWiseAnalysis");    
     conf.setOutputKeyClass(Text.class);  
     conf.setOutputValueClass(DoubleWritable.class);  
 
     conf.setMapperClass(Map.class);  
     conf.setCombinerClass(Reduce.class);  
     conf.setReducerClass(Reduce.class);  
 
     conf.setInputFormat(TextInputFormat.class);  
     conf.setOutputFormat(TextOutputFormat.class);  
 
     final File f = new File(RegionWise.class.getProtectionDomain().getCodeSource().getLocation().getPath());
     
     FileInputFormat.setInputPaths(conf, new Path("hdfs://localhost:54310/user/Devashri/inputAllLargeHotelsMA"));  
     FileOutputFormat.setOutputPath(conf, new Path("hdfs://localhost:54310/user/Devashri/outputLargeHotelMA/avg_LgHotel_MA_heating"));  
 
     JobClient.runJob(conf);  
   }  
}  