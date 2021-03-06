package simpleWordCount;

import java.io.File;
import java.io.IOException;  
import java.util.*;  
 
import org.apache.hadoop.fs.Path;  
import org.apache.hadoop.conf.*;  
import org.apache.hadoop.io.*;  
import org.apache.hadoop.mapred.*;  
import org.apache.hadoop.util.*;  
 
public class WordCount {  
 
   public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {  
     
	 private final static IntWritable one = new IntWritable(1);  
     private Text word = new Text();  
 
     public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {  
       String line = value.toString();  
       System.out.println("=========="+line);
       StringTokenizer tokenizer = new StringTokenizer(line);  
       while (tokenizer.hasMoreTokens()) {  
    	 String token = tokenizer.nextToken();
    	 System.out.println("tok--"+token);
         word.set(token);  
         if(word.equals(new Text("the"))||word.equals(new Text("The"))){
         output.collect(word, one);  
    	}
       }  
     }  
   }  
 
   public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {  
     public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {  
       int sum = 0;  
       while (values.hasNext()) {  
    	   sum += values.next().get();  
       }  
       output.collect(key, new IntWritable(sum)); 
     }  
   }  
 
   public static void main(String[] args) throws Exception {  
     JobConf conf = new JobConf(WordCount.class);  
     conf.setJobName("wordcount");  
 
     conf.setOutputKeyClass(Text.class);  
     conf.setOutputValueClass(IntWritable.class);  
 
     conf.setMapperClass(Map.class);  
     conf.setCombinerClass(Reduce.class);  
     conf.setReducerClass(Reduce.class);  
 
     conf.setInputFormat(TextInputFormat.class);  
     conf.setOutputFormat(TextOutputFormat.class);  
 
     final File f = new File(WordCount.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    // String inFiles = "hdfs://localhost:9000/nyc_trip_data/";
   //  String outFiles = f.getAbsolutePath().replace("/build/classes", "") + "/src/outFiles/NycTaxiPerMonth";
     
     FileInputFormat.setInputPaths(conf, new Path("hdfs://localhost:54310/user/Devashri/inputRestaurantBoston"));  
     FileOutputFormat.setOutputPath(conf, new Path("hdfs://localhost:54310/user/Devashri/output"));  
 
     JobClient.runJob(conf);  
   }  
}  