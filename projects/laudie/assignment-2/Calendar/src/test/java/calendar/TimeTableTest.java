package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.GregorianCalendar;
import java.util.LinkedList;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	TimeTable timeTable;
	LinkedList<Appt> appts;

	GregorianCalendar firstDay;
	GregorianCalendar lastDay;

	@Before
	public void setUp()
	{
		timeTable = new TimeTable();
		firstDay = new GregorianCalendar(2018, 2, 1, 0,0);
		lastDay = new GregorianCalendar(2019, 4, 30, 0,0);
	}

	 @Test
	  public void testGetApptRangeWeek()  throws Throwable
	 {
		 appts = new LinkedList<Appt>();
		 int[] recurringDays = new int[1];
		 recurringDays[0] = 1;
		 appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		 appts.get(0).setRecurrence(recurringDays, Appt.RECUR_BY_WEEKLY, 1, 6);
		 LinkedList<CalDay> calDays = timeTable.getApptRange(appts, firstDay, lastDay);
		 //assertion statement broken
	 }


	@Test
	public void testGetApptRangeMonth()
	{
		appts = new LinkedList<Appt>();
		int[] recurringDays = new int[1];
		recurringDays[0] = 1;
		appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		appts.get(0).setRecurrence(recurringDays, Appt.RECUR_BY_MONTHLY, 1, 6);
		//assertion statement broken
	}


	@Test
	public void testGetApptRangeYear()
	{
		appts = new LinkedList<Appt>();
		int[] recurringDays = new int[1];
		recurringDays[0] = 1;
		appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		appts.get(0).setRecurrence(recurringDays, Appt.RECUR_BY_YEARLY, 1, 6);
		//assertion statement broken
	}


	 @Test(expected = DateOutOfRangeException.class)
	 public void testGetApptRangeException() throws DateOutOfRangeException
	 {
		 LinkedList<CalDay> calDays = timeTable.getApptRange(appts, lastDay, firstDay);
	 }


	 @Test
	  public void testDeleteAppt()  throws Throwable
	 {
		 Appt deleteable;
		 appts = new LinkedList<Appt>();
		 int[] recurringDays = new int[1];
		 recurringDays[0] = 1;
		 appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		 appts.get(0).setRecurrence(recurringDays, 1, 1, 6);
         deleteable = new Appt(2,30,7,1,2018, "delete", "desc");
         appts.add(deleteable);
		 LinkedList<Appt> newAppts = timeTable.deleteAppt(appts, deleteable);
		 //assertion statement broken in last assignment
	 }


	 @Test
	public void testPermute()
	 {
		 Appt swap;
		 appts = new LinkedList<Appt>();
		 int[] recurringDays = new int[1];
		 recurringDays[0] = 1;
		 appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		 appts.get(0).setRecurrence(recurringDays, 1, 1, 6);
		 swap = new Appt(2,30,7,1,2018, "delete", "desc");
		 appts.add(swap);
		 int[] pv = new int[2];
		 pv[0] = 0;
		 pv[1] = 1;
		 LinkedList<Appt> apptsSort = timeTable.permute(appts, pv);
		 assertEquals(appts.get(0), apptsSort.get(1));
	 }
//add more unit tests as you needed
}
