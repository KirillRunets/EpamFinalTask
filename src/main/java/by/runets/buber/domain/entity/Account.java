package by.runets.buber.domain.entity;

public class Account extends Entity {
    private int accountId;
    private double accountAmount;

    public Account() {
    }

    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

        if (accountId != account.accountId) return false;
        return Double.compare(account.accountAmount, accountAmount) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + accountId;
        temp = Double.doubleToLongBits(accountAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountAmount=" + accountAmount +
                '}';
    }
}
