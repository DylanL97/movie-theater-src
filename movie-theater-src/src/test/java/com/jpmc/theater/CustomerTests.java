package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

public class CustomerTests {

	@Test
	void testGetters() {
		Customer c = createMockCustomer();
		assertEquals("John Doe", c.getName());
		assertNotNull(c.getID());
	}
	
	@Test
	void testSetters() {
		Customer c = createMockCustomer();
		c.setName("Jane Doe");
		assertEquals("Jane Doe", c.getName());
	}
	
	@Test
	void testEquality() {
		Customer c1 = createMockCustomer();
		Customer c2 = createMockCustomer();
		// unique UUID will still differ even if names are the same
		assertNotEquals(c1, c2);
		
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(2022, Month.AUGUST, 30, 19, 30, 40)
        );
        Reservation r = new Reservation(c1, showing, 4);
        assertEquals(c1, r.getCustomer());
	}
	
	@Test
	void testToString() {
		Customer c = createMockCustomer();
		assertEquals("name: John Doe", c.toString());
	}
    
    private Customer createMockCustomer() {
    	return new Customer("John Doe");
    }
}
