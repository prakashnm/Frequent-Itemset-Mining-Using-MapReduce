package com.hadoop.frequentitemset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FrequentItemSetMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
	
	public Set<List<String>> powerSet = new HashSet<List<String>>();
	public Context globalContext;
	
	private void buildPowerSet(List<String> list, int count) throws IOException, InterruptedException
	{
	    powerSet.add(list);
	    
	    if(count>0)	    
	    	globalContext.write(new Text(list.toString()),new IntWritable(1));

	    for(int i=0; i<list.size(); i++)
	    {
	        List<String> temp = new ArrayList<String>(list);
	        temp.remove(i);
	        buildPowerSet(temp, temp.size());
	    }
	}
	
	public void map(LongWritable inputKey, Text inputVal, Context context) throws IOException, InterruptedException
	{
		globalContext = context;
		String[] splits = inputVal.toString().split(" ");
		List<String> splitList = Arrays.asList(splits);
		
		buildPowerSet(splitList,splitList.size());
		
	}

}

