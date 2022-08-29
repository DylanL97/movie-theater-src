package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTests {

	@Test
	void testGetters() {
		Reservation r = createMockReservation();
		Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(2022, Month.AUGUST, 30, 19, 30, 40)
        );
		assertEquals("John Doe", r.getCustomer().getName());
		assertEquals(showing, r.getShowing());
		assertEquals(4, r.getAudienceCount());
	}
	
	@Test
	void testSetters() {
		Reservation r = createMockReservation();
		r.setCustomer(new Customer("Jane Doe"));
		Showing showing = new Showing(
                new Movie("The Batman", Duration.ofMinutes(95), 9, 0),
                1,
                LocalDateTime.now()
        );
		r.setShowing(showing);
		r.setAudienceCount(5);
		assertEquals("Jane Doe", r.getCustomer().getName());
		assertEquals(showing, r.getShowing());
		assertEquals(5, r.getAudienceCount());
		
	}
	
    @Test
    void totalFeeWhenSequence1BiggestDiscount() {
        assertEquals(38, createMockReservation().getTotalFee());
    }
    
    private Reservation createMockReservation() {
    	Customer customer = new Customer("John Doe");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(2022, Month.AUGUST, 30, 19, 30, 40)
        );
        return new Reservation(customer, showing, 4);
    }
}
