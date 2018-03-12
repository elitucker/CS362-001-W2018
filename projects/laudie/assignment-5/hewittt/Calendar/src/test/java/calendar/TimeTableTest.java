package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	private TimeTable timeTable;
	private LinkedList<Appt> appts;

	private GregorianCalendar firstDay;
	private GregorianCalendar lastDay;

	@Before
	public void setUp()
	{
		timeTable = new TimeTable();
		firstDay = new GregorianCalendar(2018, 2, 1, 0,0);
		lastDay = new GregorianCalendar(2019, 4, 30, 0,0);
	}

	@Test
	public void testGetApptRangeWeekNullRecur()  throws Throwable
	{
		appts = new LinkedList<Appt>();
		appts.add(new Appt(0, 30, 5, 2, 2018, "recur", "desc"));
		appts.get(0).setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
		LinkedList<CalDay> calDays = timeTable.getApptRange(appts, firstDay, lastDay);
		assertEquals(calDays.get(4).getAppts(), appts);
	}


	@Test
	public void testGetApptRangeWeek()  throws Throwable
	{
		appts = new LinkedList<Appt>();
		int[] recurringDays = new int[1];
		recurringDays[0] = 1;
		appts.add(new Appt(0, 30, 5, 2, 2018, "recur", "desc"));
		appts.get(0).setRecurrence(recurringDays, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
		LinkedList<CalDay> calDays = timeTable.getApptRange(appts, firstDay, lastDay);
		assertEquals(calDays.get(4).getAppts(), appts);
		assertTrue(calDays.get(4).getAppts().get(0).isRecurring());
	}


	@Test
	public void testGetApptRangeNoRecur() throws Throwable
	{
		appts = new LinkedList<Appt>();
		appts.add(new Appt(10,30,5,2,2018, "recur", "desc"));
		LinkedList<CalDay> calDays = timeTable.getApptRange(appts, firstDay, lastDay);
		assertEquals(calDays.get(4).getAppts(), appts);
	}


	@Test
	public void testGetApptRangeAfterLastDay() throws Throwable
	{
		appts = new LinkedList<Appt>();
		GregorianCalendar tempDay = new GregorianCalendar(2018, 2, 4, 0,0);
		appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		LinkedList<CalDay> calDays = timeTable.getApptRange(appts, firstDay, tempDay);
		assertFalse(calDays.contains(appts.get(0)));
	}


	@Test
	public void testGetApptRangeMonth() throws Throwable
	{
		appts = new LinkedList<Appt>();
		int[] recurringDays = new int[1];
		recurringDays[0] = 1;
		appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		appts.get(0).setRecurrence(recurringDays, Appt.RECUR_BY_MONTHLY, 1, Appt.RECUR_NUMBER_FOREVER);
		LinkedList<CalDay> calDays = timeTable.getApptRange(appts, firstDay, lastDay);
		assertEquals(calDays.get(4).getAppts(), appts);
		assertTrue(calDays.get(4).getAppts().get(0).isRecurring());
	}


	@Test
	public void testGetApptRangeYear() throws Throwable
	{
		appts = new LinkedList<Appt>();
		int[] recurringDays = new int[1];
		recurringDays[0] = 1;
		appts.add(new Appt(10,30,5,2,2018, "recur", "desc"));
		appts.get(0).setRecurrence(recurringDays, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
		LinkedList<CalDay> calDays = timeTable.getApptRange(appts, firstDay, lastDay);
		assertEquals(calDays.get(4).getAppts(), appts);
		assertTrue(calDays.get(4).getAppts().get(0).isRecurring());
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
		assertEquals(1, newAppts.size());
	}


	@Test
	public void testDeleteNullAppt() throws Throwable
	{
		Appt deleteable = null;
		appts = null;
		LinkedList<Appt> newAppts = timeTable.deleteAppt(appts, deleteable);
		assertNull(newAppts);
	}


	@Test
	public void testDeleteInvalidAppt() throws Throwable
	{
		Appt deleteable = new Appt(2,70,7,1,2018, "delete", "desc");
		appts = new LinkedList<Appt>();
		appts.add(deleteable);
		LinkedList<Appt> newAppts = timeTable.deleteAppt(appts, deleteable);
		assertNull(newAppts);
	}


	@Test
	public void testPermute() throws Throwable
	{
		appts = new LinkedList<Appt>();
		int[] recurringDays = new int[1];
		recurringDays[0] = 1;
		appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		appts.get(0).setRecurrence(recurringDays, 1, 1, 6);
		appts.add(new Appt(2,30,7,1,2018, "delete", "desc"));
		appts.add(new Appt(3,30,7,1,2018, "delete", "desc"));
		int[] pv = new int[3];
		pv[0] = 1;
		pv[1] = 0;
		pv[2] = 2;
		LinkedList<Appt> apptsSort = timeTable.permute(appts, pv);
		assertEquals(appts.get(0), apptsSort.get(1));
	}


	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPermute() throws IllegalArgumentException
	{
		appts = new LinkedList<Appt>();
		appts.add(new Appt(10,30,5,1,2018, "recur", "desc"));
		int[] pv = new int[2];
		pv[0] = 1;
		pv[1] = 0;
		timeTable.permute(appts, pv);
	}
//add more unit tests as you needed
}
