package by.runets.buber.domain.entity;

public class Account extends Entity {
    private double accountAmount;

    public Account(int id) {
        super(id);
    }

    public double getAccountAmount() {
        return accountAmount;
    }
    public void setAccountAmount(double accountAmount) {
        this.accountAmount = accountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Account account = (Account) o;

        return Double.compare(account.accountAmount, accountAmount) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(accountAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountAmount=" + accountAmount +
                '}';
    }
}
