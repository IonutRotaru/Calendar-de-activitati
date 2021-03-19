package Tema5.T5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonitoredData {

	private String start;
	private String stop;
	private String activity;
	private int id;
	private LocalDateTime begin;
	private LocalDateTime end;
	private int day1,day2;
	private long duration;
	
	public MonitoredData(String s,int i)
	{	
		id=i;
		String[] words = s.split("		");
		start = words[0];
		stop = words[1];
		activity = words[2];
		
		
		
		String[] days = words[0].split("-");
		
		day1 = Integer.parseInt(days[1])*31 + Integer.parseInt(days[2].substring(0, 2));
		
		String[] days2 = words[1].split("-");
		day2 = Integer.parseInt(days2[1])*31 + Integer.parseInt(days2[2].substring(0, 2));
		
		genBegin(start);
		genEnd(stop);
		setDuration();
	}
	
	public void genBegin(String s)
	{	
		int y=Integer.parseInt(s.substring(0,4));
		int m=Integer.parseInt(s.substring(5,7));
		int d=Integer.parseInt(s.substring(8,10));
		int h=Integer.parseInt(s.substring(11,13));
		int min=Integer.parseInt(s.substring(14,16));
		int sec=Integer.parseInt(s.substring(17));
		
		begin =  LocalDateTime.of(y,m,d,h,min,sec);
		
	
		
	}
	public void genEnd(String s)
	{	
		int y=Integer.parseInt(s.substring(0,4));
		int m=Integer.parseInt(s.substring(5,7));
		int d=Integer.parseInt(s.substring(8,10));
		int h=Integer.parseInt(s.substring(11,13));
		int min=Integer.parseInt(s.substring(14,16));
		int sec=Integer.parseInt(s.substring(17));
		
		end =  LocalDateTime.of(y,m,d,h,min,sec);
		
	
		
	}
	
	public int getDay1()
	{
		return day1;
	}
	
	public int getDay2()
	{
		return day2;
	}
	public String getActivity()
	{
		return activity;
	}
	public String getStart()
	{
		return start;
	}
	
	public String getEnd()
	{
		return stop;
	}
	public int getId()
	{
		return id;
	}
	public LocalDateTime getBeginDate()
	{
		return begin;
	}
	
	public LocalDateTime getEndDate()
	{
		return end;
	}
	
	public void setDuration()
	{	
		
		duration= Duration.between(begin, end).getSeconds();
	}
	public long getDuration()
	{
		return duration;
	}
	public String toString()
	{
		String r = "";
		return r + start+"   "+ stop+"    "+activity + day1+" "+ day2;
	}
	
}
