package Tema5.T5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, UnsupportedEncodingException
    {
    	Sensor s = new Sensor("Activities.txt");
    	s.listActivities();
    	s.distinctDays();
    	s.distinctActivities();
    	s.task4();
    	s.task5();
    	s.task6();

    	 
    }
}
