package stateWiseAnalysisMA;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import model.ElexConsumption;
import regionWiseMA.RegionWise;
import stateWiseAnalysisMA.AreaAndBuildingWiseAvgConsumption.Map;
import stateWiseAnalysisMA.AreaAndBuildingWiseAvgConsumption.Reduce;

public class SummerWinterAnalysis {
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, DoubleWritable> {  
	     
		 private Text myKey = new Text();  
	 
	     public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {  
	       String line = value.toString();  
	      
	       List<String> cols = Arrays.asList(line.split(","));
	       
	       try{
	    	   String stateCode = getState(cols.get(0));
	    	   String buildingCode = getBuildingType(cols.get(0));
	    	   String regionCode = getRegion(cols.get(0));
	    	   
	    	   ElexConsumption consumption = new ElexConsumption(cols,stateCode,buildingCode,regionCode);
	    	   String month = getMonth(consumption.getTimeStamp());
	    	   String createdKey = month+buildingCode;
	    	   myKey.set(createdKey);
	    	  // myKey.set(month);
	    	   Double elexByFans=Double.parseDouble(consumption.getFans());
	    	   Double elexByFacility=Double.parseDouble(consumption.getFacility());
	    	   Double elexByCooling=Double.parseDouble(consumption.getCooling());
	    	   Double elexByHeating=Double.parseDouble(consumption.getHeating());
	    	   Double elexByIntLights=Double.parseDouble(consumption.getInteriorLight());
	    	   Double elexByIntEquipment=Double.parseDouble(consumption.getInteriorEquipment());
	    	   Double totalConsumption = elexByFans+elexByFacility+elexByCooling+elexByHeating+elexByIntLights+elexByIntEquipment;
	           Double valueConsumedByAppliance = elexByIntEquipment;
	           DoubleWritable val = new DoubleWritable(valueConsumedByAppliance);
	           
	           
	           if(createdKey.substring(2,4).equals("SO")){
	        	   output.collect(myKey, val); 
	           }
	          
	       }catch(Exception e){
	    	   System.out.println("Header");
	       }
	      
	      }
	     
	    public String getState(String code){
	 		
			 return code.substring(0,6);
		}
	    
	    public String getMonth(String timeStamp){
	    	return timeStamp.substring(0,2);
	    }
		
		public String getRegion(String code){
			 return code.substring(7,9);
		}
		
		public String getBuildingType(String code){
			 return code.substring(10,12);
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
	     
	     FileInputFormat.setInputPaths(conf, new Path("hdfs://localhost:54310/user/Devashri/CSV"));  
	     FileOutputFormat.setOutputPath(conf, new Path("hdfs://localhost:54310/user/Devashri/SummerWinterAnalysis/SOTotalConsIntEquipments"));  
	 
	     JobClient.runJob(conf);  
	   }
}
