package Railway;

import Common.Ticket;
import Common.TicketTableComponent;

public class MyTicketPage extends GeneralPage {

	private TicketTableComponent ticketTable =
            new TicketTableComponent();

    public void cancelTicket(Ticket ticket) {
        ticketTable.cancelTicket(ticket);
    }

    public boolean isTicketPresent(Ticket ticket) {
        return ticketTable.isTicketPresent(ticket);
    }
}