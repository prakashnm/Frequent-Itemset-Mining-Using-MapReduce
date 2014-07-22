package com.hadoop.frequentitemset;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class FrequentItemSetReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
	
	public void reduce(Text key,Iterable<IntWritable> listofvalues,Context context) throws IOException, InterruptedException
	{
		int sum=0;
		for(IntWritable val:listofvalues)
			sum=sum+val.get();
		
		context.write(key,new IntWritable(sum));
	}
	
}
