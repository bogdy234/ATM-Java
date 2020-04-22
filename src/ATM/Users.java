package ATM;

public class Users implements Comparable{
    private String id;   //numar card de 16 cifre
    private int pin;    //pin de 4 cifre
    private double balance;

    public Users(String id, int pin, double balance) {
        this.id = id;
        this.pin = pin;
        this.balance=balance;
    }

    public void deposit(double amount){
        balance+=amount;
    }

    public void withdraw(double amount){
        balance-=amount;
    }

    public double getBalance(){
        return this.balance;
    }

    @Override
    public int compareTo(Object o){
        Users ur=(Users) o;
        if (balance>ur.balance) return 1;
        if (balance==ur.balance) return 0;
        return -1;
    }

    public String getId() {
        return id;
    }

    public int getPin() {
        return pin;
    }

}
