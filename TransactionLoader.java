import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;

public class TransactionLoader implements ITransactionLoader{
    private String line;                                        //the current line
    private int count;                                          //used to count number of quotation marks. (even = full quote, odd means keep reading past commas in quotes)
    private int column;                                         //keeps track of which column the reader is at (I.E Payer column, Points column, or Date column)
    private String[] arrOfTransactions = new String[3];         //Array with the number of columns
    private ArrayList<ITransaction> list = new ArrayList<>();   //list that will store transaction information


    /**
     * This method loads the list of transactions from a CSV file.
     *
     * @param CSVFile name of the CSV file relative to the executable
     * @return a list of Transaction objects
     * @throws FileNotFoundException
     */
    @Override
    public List<ITransaction> loadTransactions(String CSVFile) throws FileNotFoundException {
        FileReader fr = new FileReader(CSVFile);
        BufferedReader br = new BufferedReader(fr);

        //variable used to build up string by reading each character in buffered reader. (To not mistake commas in quotes)
        String strBuilder = "";

        try {
            //reads header line so that index 0 is the first transaction
            String firstLineSpacer = br.readLine();
            //while there is a next line (AKA the number of transactions)
            while (((line = br.readLine()) != null)) {
                //for each character in the line
                for (int i = 0; i < line.length(); i++) {
                    //If character is a quotation, increment the quotation counter
                    if (line.charAt(i) == '"' ) {
                        count++;
                    }
                    //If we're at the end of a transaction (no comma at the end)
                    if ((i == line.length() - 1)) {
                        arrOfTransactions[column] = strBuilder + line.charAt(i);
                        strBuilder = "";
                        column++;
                        continue;
                    }
                    //If there are even number of quotations (a full quote), disregard the commas in the quotations and store word
                    if (((count % 2) == 0) && (line.charAt(i) == ',')) {
                        arrOfTransactions[column] = strBuilder;
                        strBuilder = "";
                        column++;
                    }
                    else {
                        strBuilder += line.charAt(i);
                    }
                }
                //Combine the payer, points, and date values as a single transaction object
                Transaction transaction = new Transaction(arrOfTransactions[0], Integer.parseInt(arrOfTransactions[1]),
                        LocalDateTime.parse(arrOfTransactions[2].
                                replace("\"", ""), DateTimeFormatter.ISO_OFFSET_DATE_TIME)); //uses DateTimeFormatter to parse dates easily

                //add transactions to a list
                list.add(transaction);

                //sort the list
                Collections.sort(list, new Comparator<ITransaction>() { //just call comparator to not mix excapsulation and interface
                    @Override
                    public int compare (ITransaction t1, ITransaction t2) {
                        return t1.getDate().compareTo(t2.getDate());
                    }});
                column = 0;
            }
            fr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
