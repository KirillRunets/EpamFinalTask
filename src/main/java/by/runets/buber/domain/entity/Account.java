package by.runets.buber.domain.entity;

import java.math.BigDecimal;

public class Account extends Entity {
    private BigDecimal accountAmount;

    public Account() {
    }

    public Account(int id) {
        super(id);
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }
    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Account account = (Account) o;

        return accountAmount != null ? accountAmount.equals(account.accountAmount) : account.accountAmount == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accountAmount != null ? accountAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountAmount=" + accountAmount +
                '}';
    }
}
