import java.time.LocalDateTime;

public class Transaction implements ITransaction{
  private String payer;
  private int points;
  private LocalDateTime date;

  public Transaction(String payer, int points, LocalDateTime date) {
    this.payer = payer;
    this.points = points;
    this.date = date;
  }

  /**
   * Returns the name of the Payer
   *
   * @return name of payer
   */
  @Override
  public String getPayer() {
    return payer;
  }

  /**
   * Returns number of points each payer has
   *
   * @return number of points each payer has
   */
  @Override
  public int getPoints() {
    return points;
  }

  /**
   * Returns the date of the transaction
   *
   * @return date of transaction
   */
  @Override
  public LocalDateTime getDate() {
    return date;
  }

  /**
   * Sets the value of points to the value given in paramater
   * @param points
   */
  @Override
  public void setPoints(int points) {
    this.points = points;
  }

  /**
   * Sets the value of the date to the value given in parameter
   * @param date
   */
  @Override
  public void setDate(LocalDateTime date) {
    this.date = date;
  }

}
