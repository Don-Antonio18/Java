public class BankTest {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(
            "123456", "Antonio Kerr", 1000.0
            );

        BankAccount account2 = new BankAccount(
            "789012","Jane Doe", 500.0
            );

        account1.deposit(200.0);
        account1.withdraw(150.0);
        account1.printAccountDetails();

        account2.deposit(300.0);
        account2.withdraw(100.0);
        account2.printAccountDetails();
    }
}
