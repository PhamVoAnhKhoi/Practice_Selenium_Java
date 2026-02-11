package Common;

import java.util.Objects;

public class Ticket {

    private String departStation;
    private String arriveStation;
    private String seatType;
    private String departDate;
    private String bookDate;
    private String expiredDate;
    private String amount;
    private String totalPrice;

    public Ticket(String departStation, String arriveStation,
                  String seatType, String departDate,
                  String bookDate, String expiredDate,
                  String amount, String totalPrice) {

        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.seatType = seatType;
        this.departDate = departDate;
        this.bookDate = bookDate;
        this.expiredDate = expiredDate;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public String getDepartStation() { return departStation; }
    public String getArriveStation() { return arriveStation; }
    public String getSeatType() { return seatType; }
    public String getDepartDate() { return departDate; }
    public String getBookDate() { return bookDate; }
    public String getExpiredDate() { return expiredDate; }
    public String getAmount() { return amount; }
    public String getTotalPrice() { return totalPrice; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(departStation, ticket.departStation) &&
                Objects.equals(arriveStation, ticket.arriveStation) &&
                Objects.equals(seatType, ticket.seatType) &&
                Objects.equals(departDate, ticket.departDate) &&
                Objects.equals(amount, ticket.amount);
                // Ignore dynamic fields like bookDate, expiredDate if needed
    }

    @Override
    public int hashCode() {
        return Objects.hash(departStation, arriveStation,
                seatType, departDate, amount);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "depart='" + departStation + '\'' +
                ", arrive='" + arriveStation + '\'' +
                ", seat='" + seatType + '\'' +
                ", departDate='" + departDate + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
