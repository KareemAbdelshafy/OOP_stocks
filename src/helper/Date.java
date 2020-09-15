package helper;


import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A class that represent the time and date that will be used in buying and selling.
 */
public class Date {

  private Calendar calendar;
  private String time;


  /**
   * Constructor that take date as string like 2018-01-16.
   */
  public Date(String date) {
    calendar = new GregorianCalendar();
    String[] s = date.split("-");
    calendar.set(Integer.valueOf(s[0]), Integer.valueOf(s[1]) - 1, Integer.valueOf(s[2]));
    time = "close";
  }

  /**
   * This constructor takes year month and day as integers.
   * @param year - year as an integer.
   * @param month - month as an integer.
   * @param day - day as an integer.
   */
  public Date(int year, int month, int day) {
    calendar.set(year, month - 1, day);
    time = "close";

  }


  /**
   * Return true if this day and time is a working day and time.
   *
   * @return true  if this day and time is a working day and time.
   */
  public boolean isWorkDay() {
    //Sun = 1, Mon = 2, Sat = 7
    int day = calendar.get(Calendar.DAY_OF_WEEK);

    return !(day == 1 || day == 7);

  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("");
    s.append(calendar.get(Calendar.YEAR)).append("-").append(calendar.get(Calendar.MONTH) + 1);
    s.append("-").append(calendar.get(Calendar.DAY_OF_MONTH));
    return s.toString();

  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return toString().equals(((Date) obj).toString());
  }

  /*
   * This method returns Calender  object for the date.
   * @return - Calender object for the current date.
   */
  public Calendar getCalendar() {
    return calendar;
  }

  /*
   * This method returns a string which is time.
   * @return - time in a String format.
   */
  public String getTime() {
    return time;
  }

  /**
   * This methods returns long value of time in milliseconds.
   * @return - Time in milliseconds in long.
   */
  public long timeInMilliSeconds() {
    return calendar.getTime().getTime();
  }

  /**
   * This is a setter method for time.
   * @param time - Takes a string to set time.
   */
  public void setTime(String time) {
    this.time = time;
  }
}
