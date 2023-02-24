import java.io.FileNotFoundException;
import java.util.*;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    //reads first argument in commandline
    int pointsToSpend = Integer.parseInt(args[0]);
    //reads second argument in commandline
    String CSVFile = args[1];


    TransactionLoader readCSV = new TransactionLoader();
    //Adds into a list all the transaction objects
    List<ITransaction> list = readCSV.loadTransactions(CSVFile);

    //creates new reference hashmap for easier printing
    HashMap<String, ITransaction> print = (spendPoints(pointsToSpend, list));

    for (String payer : print.keySet()) {
      String key = payer.toString();
      int value = print.get(payer).getPoints();
      System.out.println(key + " " + value);
    }
  }


  /**
   * This method helps to spend the points correctly with the criteria given.
   * @param pointsToSpend
   * @param transactions
   * @return payerPoints (a HashMap of all the elements)
   */
  public static HashMap<String, ITransaction> spendPoints(int pointsToSpend, List<ITransaction> transactions) {
    //assign reference to points needed to spend
    int points = pointsToSpend;

    for (ITransaction transaction : transactions) {
      //if we cannot spend points from a payer, just skip
      if (transaction.getPoints() == 0) {
        continue;
      }
      //if we either payer points are negative / we have more points than payer
      if (transaction.getPoints() < 0 || points >= transaction.getPoints()) {
        points = points - transaction.getPoints();
        transaction.setPoints(0);

        //if payer has more points, spend all our points
      } else {
        transaction.setPoints(transaction.getPoints() - points);
        points = 0;
      }
    }

    //A new hashmap to total up payers' points and remove duplicate payer names
    HashMap<String, ITransaction> payerPoints = new HashMap<>();

    for (ITransaction transaction : transactions) {
      if (!payerPoints.containsKey(transaction.getPayer())) {
        payerPoints.put(transaction.getPayer(), transaction);
      } else {

        //create new instance of a transaction to store added changes to the points for duplicate payers
        Transaction tranStore = new Transaction(transaction.getPayer(), transaction.getPoints(), transaction.getDate());

        //update points in hashmap
        tranStore.setPoints(payerPoints.get(transaction.getPayer()).getPoints() + transaction.getPoints());
        payerPoints.put(transaction.getPayer(), tranStore);
      }
    }
    return payerPoints;
  }
}
