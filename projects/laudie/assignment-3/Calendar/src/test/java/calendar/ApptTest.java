package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	@Test
	public void testCreateAppt()  throws Throwable  {
		int startHour=0;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
							 startMinute ,
							 startDay ,
							 startMonth ,
							 startYear ,
							 title,
							 description);
		appt.setStartHour(startHour);
		appt.setStartMinute(startMinute);
		appt.setStartDay(startDay);
		appt.setStartMonth(startMonth);
		appt.setStartYear(startYear);
		appt.setTitle(title);
		appt.setDescription(description);
		appt.setRecurrence(null, 1,1,1);
		// assertions
		assertTrue(appt.getValid());
		assertEquals(0, appt.getStartHour());
		assertEquals(30, appt.getStartMinute());
		assertEquals(15, appt.getStartDay());
		assertEquals(01, appt.getStartMonth());
		assertEquals(2018, appt.getStartYear());
		assertEquals("Birthday Party", appt.getTitle());
		assertEquals("This is my birthday party.", appt.getDescription());
		assertEquals("\t1/15/2018 at 12:30am ,Birthday Party, This is my birthday party.\n", appt.toString());
	}

	@Test
	public void testCompareAppt()  throws Throwable  {
		int startHour=21;
		int startMinute=30;
		int startDay=15;
		int startMonth=02;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
							 startMinute ,
							 startDay ,
							 startMonth ,
							 startYear ,
							 title,
							 description);
		Appt appt2 = new Appt(startHour-1,
							  startMinute-1 ,
							  startDay-1 ,
							  startMonth-1 ,
							  startYear-1 ,
							  title,
							  description);
		appt.toString();
		int comp = appt.compareTo(appt2);
		assertEquals(5, comp);
	}


	@Test
	public void testToStringNull()
	{
		int startHour=25;
		int startMinute=20;
		int startDay=54;
		int startMonth=02;
		int startYear=2018;
		String title="Birthday Party";
		String description="This is my birthday party.";
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
							 startMinute ,
							 startDay ,
							 startMonth ,
							 startYear ,
							 title,
							 description);
		assertNull(appt.toString());
	}


	@Test
	public void testRecurrence()
	{
		int startHour=0;
		int startMinute=30;
		int startDay=15;
		int startMonth=01;
		int startYear=2018;
		String title=null;
		String description=null;
		//Construct a new Appointment object with the initial data
		Appt appt = new Appt(startHour,
							 startMinute ,
							 startDay ,
							 startMonth ,
							 startYear ,
							 title,
							 description);
		appt.setStartHour(startHour);
		appt.setStartMinute(startMinute);
		appt.setStartDay(startDay);
		appt.setStartMonth(startMonth);
		appt.setStartYear(startYear);
		appt.setTitle(title);
		appt.setDescription(description);
		int[] recurringDays = new int[1];
		recurringDays[0] = 1;
		appt.setRecurrence(recurringDays, 1, 1, 1);
		assertEquals(recurringDays, appt.getRecurDays());
		assertEquals(1, appt.getRecurBy());
		assertEquals(1, appt.getRecurIncrement());
		assertEquals(1, appt.getRecurNumber());
	}
//add more unit tests as you needed
	
}
