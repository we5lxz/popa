package n2;

class BankAccount {
    final String accountNumber;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("228");
        //account.accountNumber = 10; java: cannot assign a value to final variable accountNumber
        //потому что финал нельзя переопределить
        System.out.println("номер счета " + account.getAccountNumber());
    }
}