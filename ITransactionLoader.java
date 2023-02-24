import java.io.FileNotFoundException;
import java.util.List;

/**
 * Instances of this interface can be used to load transaction data from a CSV file.
 */

public interface ITransactionLoader {

    /**
     * This method loads the list of transactions from a CSV file.
     * @param CSVFile name of the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    List<ITransaction> loadTransactions(String CSVFile) throws FileNotFoundException;

}
