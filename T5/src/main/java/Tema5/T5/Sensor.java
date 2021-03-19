package Tema5.T5;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.Duration;

public class Sensor {

	private String fileName;
	private ArrayList<MonitoredData> l = new ArrayList<MonitoredData>();
	private ArrayList<Integer> distinctDays =new ArrayList<Integer>();
	private ArrayList<String> distinctActivities =new ArrayList<String>();
	private int id=1;
	
	public Sensor(String n)
	{
		fileName=n;
	}
	
	public void listActivities()
	{
		

	
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			PrintWriter writer = new PrintWriter("Task_1.txt", "UTF-8");
			
			
			
		stream.forEachOrdered(s ->{
								l.add(new MonitoredData(s,id++)) ;
								writer.println(s);
								});
		
		writer.close();
		System.out.println("Task 1 ended");
		
		}
		catch (IOException e) {
			e.printStackTrace();
	}
		
	}
	
	public void distinctDays() throws FileNotFoundException, UnsupportedEncodingException
	{	
		long count=0;
		PrintWriter writer = new PrintWriter("Task_2.txt", "UTF-8");
		
		for(MonitoredData md:l)
		{
			distinctDays.add(md.getDay1());
			distinctDays.add(md.getDay2());
			
		}
		
		
		  count = distinctDays.stream().distinct().count(); 
		  
		  writer.println("The number of distinct days is: "+count);
		  writer.close();
		  System.out.println("Task 2 ended");
	}
	
	public void distinctActivities() throws FileNotFoundException, UnsupportedEncodingException
	{	
		
		
		for(MonitoredData md:l)
		{
			distinctActivities.add(md.getActivity());
			
		}
		
		 Map<String, Integer> counts = distinctActivities.parallelStream().
		            collect(Collectors.toConcurrentMap(w -> w, w -> 1, Integer::sum));
		 
		 PrintWriter writer = new PrintWriter("Task_3.txt", "UTF-8");
		 writer.println("Distinct activities: ");
		 writer.println(counts);
		 writer.close();
		 System.out.println("Task 3 ended");
		
	}
	
	public void task4() throws FileNotFoundException, UnsupportedEncodingException
	{	
		Map<Integer,Map<String,Long>> a = l.parallelStream().collect(Collectors.groupingBy(
				t -> {	
						return t.getDay1();
				},
			Collectors.groupingBy(MonitoredData::getActivity,Collectors.counting())));
		
		StringBuilder res = new StringBuilder();
		res.append("Each day activity:\n");
		id=1;
		a.forEach((key,value)->res.append("day"+id++ + " activities: "+value+"\n"));
		
		 PrintWriter writer = new PrintWriter("Task_4.txt", "UTF-8");
		 writer.println(res.toString());
		 writer.close();
		 System.out.println("Task 4 ended");
	}
	
	public void task5() throws FileNotFoundException, UnsupportedEncodingException
	{
		
		
		Map<Object, Long> m = 
						l.parallelStream().collect(
						Collectors.groupingBy(t ->t.getActivity(),
						Collectors.reducing(0L,t -> t.getDuration(),
								(x,y) -> x+y)));
		
		StringBuilder res = new StringBuilder();
		res.append("Duration:\n");
	
		m.forEach((key,value)->res.append("activity: "+ key +" duration: "+value+"\n"));
		
		 PrintWriter writer = new PrintWriter("Task_5.txt", "UTF-8");
		 writer.println(res.toString());
		 writer.close();
		 System.out.println("Task 5 ended");
		
	}
	
	public void task6() throws FileNotFoundException, UnsupportedEncodingException
	{
		Map<String,Long> activities5 = l.parallelStream()
				.filter(t->t.getDuration() <= 300)
				.collect(Collectors.groupingBy(t -> t.getActivity(),
						 Collectors.counting()));
		
		java.util.List<String> res = l.parallelStream()
				.map(t -> t.getActivity())
											 .filter(s -> activities5.containsKey(s))
											 .filter(s -> activities5.get(s) >= 0.9 * getActivityApperance(s))
											 .distinct()
											 .collect(Collectors.toList());
		
		 PrintWriter writer = new PrintWriter("Task_6.txt", "UTF-8");
		 writer.println(res);
		 writer.close();
		 System.out.println("Task 6 ended");
											 
											 
	}

	private long getActivityApperance(String s) {
		long x =0;
		for(MonitoredData md:l)
		{
			if(md.getActivity().contentEquals(s))
				x++;
		}
		return x;
		
	}
	
}
