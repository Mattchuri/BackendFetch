build:
	javac ITransaction.java
	javac ITransactionLoader.java
	javac Transaction.java
	javac TransactionLoader.java
	javac Main.java

run:
	java Main 5000 transactions.csv
