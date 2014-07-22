Frequent-Itemset-Mining-Using-MapReduce
=======================================

The original data would be a text ﬁle, each line is a transaction containing multiple items
separated by commas (item1, item2, item3,¢¢¢, itemn). Let’s use the following dummy data as
an example. Each line is a basket or a transaction. Each number is an item id.
1,2,5
2,3
2,4,5
1,2
1,5

The map function:
My map function would take this original ﬁle and generate an intermediate output. The
input key would be LongWritable: offset of each line from the beginning of the ﬁle. The input
value would be Text: the content of each line. The output key would be Text: the itemsets.
The output value is IntWritable: 1. In the example case, it will generate the following outputs

1,2,5--> (1,1), (2,1), (5,1), ((1,2),1), ((1,5),1), ((2,5),1), ((1,2,5),1)

2,3 --> (2,1), (3,1), ((2,3),1)

2,4,5 --> (2,1), (4,1), (5,1), ((2,4),1), ((2,5),1), ((4,5),1), ((2,4,5),1)

1,2 --> (1,1), (2,1), ((1,2),1)

1,5 --> (1,1), (5,1), ((1,5),1)

The reduce function:
My reduce function would aggregate all values for each key. The output key would be Text:
itemsets. The output value is IntWritable: the number of occurrence of each corresponding
key. In the example, case, it will generate the following outputs: 

(1,3),(2,4),(3,1),(4,1),(5,3),((1,2),2),((1,5),2),((2,3),1),((2,4),1),((2,5),2),((4,5),1)
((1,2,5),1), ((2,4,5),1)

The partition function:
My partition class extends the existing Partition class in Hadoop. 
