package ATM;

import java.util.TreeSet;

public class Accounts {

    private Users user;
    private TreeSet<Users> accounts = new TreeSet<Users>();

    public void addAccount(Users user) {
        accounts.add(user);
    }

    public Users checkID(String ID, int pin) {
        for (Users user:accounts)
        {
            if (user.getId().equals(ID) && user.getPin()==pin)
                return user;
        }
        return null;
    }

    public Users getUser() {
        return user;
    }
}