package com.hadoop.frequentitemset;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.hadoop.frequentitemset.CustomPartitioner;
import com.hadoop.frequentitemset.FrequentItemSetDriver;
import com.hadoop.frequentitemset.FrequentItemSetMapper;
import com.hadoop.frequentitemset.FrequentItemSetReducer;

public class FrequentItemSetDriver extends Configured implements Tool{

	public static void main(String args[]) throws Exception
	{
		ToolRunner.run(new FrequentItemSetDriver(),args);
	}
	
	
	@Override
	public int run(String[] args) throws Exception {
		
		Job job = new Job(getConf(),"Frequent item Set Job");
		job.setJarByClass(FrequentItemSetDriver.class);
		job.setMapperClass(FrequentItemSetMapper.class);
		job.setReducerClass(FrequentItemSetReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setNumReduceTasks(10);

		job.setPartitionerClass(CustomPartitioner.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
		
		return 0;
	}

}
