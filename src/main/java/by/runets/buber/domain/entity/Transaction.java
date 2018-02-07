package by.runets.buber.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction extends Entity {
    private Account from;
    private Account to;
    private Date date;
    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(int id) {
        super(id);
    }

    public Transaction(Account from, Account to, Date date, BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.amount = amount;
    }

    public Account getFrom() {
        return from;
    }
    public void setFrom(Account from) {
        this.from = from;
    }
    public Account getTo() {
        return to;
    }
    public void setTo(Account to) {
        this.to = to;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Transaction that = (Transaction) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionDAO{" +
                "from=" + from +
                ", to=" + to +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
