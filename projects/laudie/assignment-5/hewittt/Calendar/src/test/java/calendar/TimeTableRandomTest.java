package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest
{

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS = 100;


	/**
	 * Return a randomly selected method to be tests !.
	 */
	public static String RandomSelectMethod(Random random)
	{
		String[] methodArray = new String[] {"setTitle", "setRecurrence"};// The list of the of methods to be tested in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)

		return methodArray[n]; // return the method name
	}


	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
	public static int RandomSelectRecur(Random random)
	{
		int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY, Appt.RECUR_BY_MONTHLY, Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
		return RecurArray[n]; // return the value of the  appointments to recur
	}


	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
	public static int RandomSelectRecurForEverNever(Random random)
	{
		int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER, Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

		int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
		return RecurArray[n]; // return appointments to recur forever or Never recur
	}


	@Test
	public void randomTest() throws Throwable
	{

		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


		System.out.println("Start testing...");

		try
		{
			for (int iteration = 0; elapsed < TestTimeout; iteration++)
			{
				TimeTable timeTable = new TimeTable();
				long randomseed = System.currentTimeMillis(); //10
				Random random = new Random(randomseed);
				//appts and appt both null possibilities

				int startHour = ValuesGenerator.getRandomIntBetween(random, -2, 26);
				int startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 62);
				int startDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
				int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startYear = ValuesGenerator.RandInt(random);
				String title = "Birthday Party";
				String description = "This is my birthday party.";
				//Construct a new Appointment object with the initial data
				Appt appt = new Appt(startHour,
									 startMinute,
									 startDay,
									 startMonth,
									 startYear,
									 title,
									 description);
				if (ValuesGenerator.getBoolean(0.05f, random))
					appt = null;

				LinkedList<Appt> appts = new LinkedList<Appt>();
				if (ValuesGenerator.getBoolean(0.1f, random))
					appts = null;
				else
				{
					for (int i = 0; i < 4; i++)
					{
						startHour = ValuesGenerator.getRandomIntBetween(random, -2, 26);
						startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 62);
						startDay = ValuesGenerator.getRandomIntBetween(random, -1, 32);
						startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
						startYear = ValuesGenerator.RandInt(random);
						appts.add(new Appt(startHour,
										   startMinute,
										   startDay,
										   startMonth,
										   startYear,
										   title,
										   description));
					}
					appts.add(appt);
				}

				for (int i = 0; i < NUM_TESTS; i++)
				{
					timeTable.deleteAppt(appts, appt);
				}

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10000) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		System.out.println("Done testing...");
	}


	@Test
	public void randomGetTest()
	{
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


		System.out.println("Start testing...");

		try
		{
			for (int iteration = 0; elapsed < TestTimeout; iteration++)
			{
				TimeTable timeTable = new TimeTable();
				long randomseed = System.currentTimeMillis(); //10
				Random random = new Random(randomseed);

				int startHour;
				int startMinute;
				int startDay;
				int startMonth;
				int startDay1 = ValuesGenerator.getRandomIntBetween(random, -1, 32);
				int startMonth1 = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startYear = 1;
				GregorianCalendar firstDay = new GregorianCalendar(startYear, startMonth1, startDay1);

				int startDay2 = ValuesGenerator.getRandomIntBetween(random, -1, 32);
				int startMonth2 = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				GregorianCalendar lastDay = new GregorianCalendar(startYear, startMonth2, startDay2);

				LinkedList<Appt> appts = new LinkedList<Appt>();
				if (ValuesGenerator.getBoolean(0.1f, random))
					appts = null;
				else
				{
					for (int i = 0; i < 4; i++)
					{
						startHour = ValuesGenerator.getRandomIntBetween(random, -2, 26);
						startMinute = ValuesGenerator.getRandomIntBetween(random, -1, 62);
						startDay = ValuesGenerator.getRandomIntBetween(random, startDay1, startDay2);
						startMonth = ValuesGenerator.getRandomIntBetween(random, startMonth1, startMonth2);
						appts.add(new Appt(startHour,
										   startMinute,
										   startDay,
										   startMonth,
										   startYear,
										   "Boring",
										   "Extra Boring"));
					}
				}

				for (int i = 0; i < NUM_TESTS; i++)
				{
					try
					{
						if (appts != null)
							timeTable.getApptRange(appts, firstDay, lastDay);
					}
					catch (DateOutOfRangeException e)
					{

					}
				}
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if ((iteration % 10000) == 0 && iteration != 0)
					System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

			}
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		System.out.println("Done testing...");
	}
}