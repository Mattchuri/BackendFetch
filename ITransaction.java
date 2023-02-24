import java.time.LocalDateTime;

public interface ITransaction {
  /**
   * Returns the name of the Payer
   * @return name of payer
   */
  public String getPayer();

  /**
   * Returns number of points each payer has
   * @return number of points each payer has
   */
  public int getPoints();

  /**
   * Returns the date of the transaction
   * @return date of transaction
   */
  public LocalDateTime getDate();

  /**
   * Sets the value of points to the value given in parameter
   * @param points
   */
  public void setPoints(int points);

  /**
   * Sets the value of the date to the value given in parameter
   * @param date
   */
  public void setDate(LocalDateTime date);


}
