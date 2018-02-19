package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	@Test
	public void testCreateCalDay() throws Throwable {
		CalDay invalCalDay = new CalDay();
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(2018, Calendar.JANUARY, 28);
		CalDay calDay = new CalDay(cal);
		assertFalse(invalCalDay.isValid());
		assertEquals(2018, calDay.getYear());
		assertEquals(0, calDay.getMonth());
		assertEquals(28, calDay.getDay());
	}


	@Test
	public void testIsValid() throws Throwable {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(2018, Calendar.JANUARY+1, 28);
		CalDay calDay = new CalDay(cal);
		assertTrue(calDay.isValid());
	}


	@Test
	public void testAddAppt()
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(2018, Calendar.JANUARY+1, 28);
		CalDay calDay = new CalDay(cal);
		Appt appt = new Appt(2, 0, 28, 1, 2018, "appt", "appt desc");
		calDay.addAppt(appt);
		assertEquals(1, calDay.getSizeAppts());
		Appt appt2 = new Appt(1, 0, 28, 1, 2018, "appt", "appt desc");
		calDay.addAppt(appt2);
		assertEquals(2, calDay.getSizeAppts());
	}


	@Test
	public void testSetAppt()
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(2018, Calendar.JANUARY+1, 28);
		CalDay calDay = new CalDay(cal);
	}


	@Test
	public void testToString()
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(2018, Calendar.JANUARY+1, 28);
		CalDay calDay = new CalDay(cal);
		assertEquals("\t --- 1/28/2018 --- \n --- -------- Appointments ------------ --- \n\n", calDay.toString());
	}


	@Test
	public void testMultipleToString()
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(2018, Calendar.JANUARY+1, 28);
		CalDay calDay = new CalDay(cal);
		Appt appt = new Appt(2, 0, 28, 1, 2018, "appt", "appt desc");
		calDay.addAppt(appt);
		assertEquals(1, calDay.getSizeAppts());
		Appt appt2 = new Appt(1, 0, 28, 1, 2018, "appt", "appt desc");
		calDay.addAppt(appt2);
		assertEquals("\t --- 1/28/2018 --- \n --- -------- Appointments ------------ --- \n" +
					 "\t1/28/2018 at 1:0am ,appt, appt desc\n" +
					 " \t1/28/2018 at 2:0am ,appt, appt desc\n \n", calDay.toString());
	}


	@Test
	public void testIteratorPass()
	{GregorianCalendar cal = new GregorianCalendar();
		cal.set(2018, Calendar.JANUARY+1, 28);
		CalDay calDay = new CalDay(cal);
		assertNotNull(calDay.iterator());
	}


	@Test
	public void testIteratorFail()
	{
		CalDay calDay = new CalDay();
		assertNull(calDay.iterator());
	}
//add more unit tests as you needed
}
