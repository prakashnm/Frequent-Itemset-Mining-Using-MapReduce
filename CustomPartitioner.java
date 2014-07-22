package com.hadoop.frequentitemset;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitioner extends Partitioner<Text, IntWritable>{
	
	/**
	 * Remember partitioner will be called on map output key and output value..(which is known as intermediate key value pair
	 * 
	 * Also see in the mapper we are sending character of the word as output key and entire word as value
	 * 
	 */

	@Override
	public int getPartition(Text inputMapKey, IntWritable inputMapValue, int numOfReducer) {
		int partitionNum = 0;
		String inputString=inputMapKey.toString();
		String[] splits =  inputString.substring(1,inputString.length()-1).split(",");
		
		switch(Integer.parseInt(splits[0])%10) {
			case 0:
				partitionNum = 0;
				break;
			case 1:
				partitionNum = 1;
				break;
			case 2:
				partitionNum = 2;
				break;
			case 3:
				partitionNum = 3;
				break;
			case 4:
				partitionNum = 4;
				break;
			case 5:
				partitionNum = 5;
				break;
			case 6:
				partitionNum = 6;
				break;
			case 7:
				partitionNum = 7;
				break;
			case 8:
				partitionNum = 8;
				break;
			case 9:
				partitionNum = 9;
				break;
			default:  partitionNum = 1;
		}
		return partitionNum;
		
	}

}
