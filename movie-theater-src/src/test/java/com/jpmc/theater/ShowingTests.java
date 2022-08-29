package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

public class ShowingTests {
	
	@Test
	void testGetterMethods() {
		Showing showing = createMockShowing();
	    assertEquals("Spider-Man: No Way Home", showing.getMovie().getTitle());
	    assertEquals(1, showing.getSequence());
	    assertEquals(LocalDateTime.of(2022, Month.AUGUST, 29, 19, 30, 40), showing.getStartTime());
	}
	
	@Test
	void testSetterMethods() {
		Showing showing = createMockShowing();
		Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
		showing.setMovie(turningRed);
		LocalDateTime start = LocalDateTime.of(2022, Month.AUGUST, 30, 19, 30, 40);
		showing.setStartTime(start);
		showing.setSequence(6);
		
		assertEquals("Turning Red", showing.getMovie().getTitle());
	    assertEquals(6, showing.getSequence());
	    assertEquals(start, showing.getStartTime());
	}
	
	@Test 
	void calculateTicketPriceWhenSequence1BiggestDiscount() {
		Showing showing = createMockShowing();
		assertEquals(9.50, showing.calculateTicketPrice());
	}
	
	@Test 
	void calculateTicketPriceWhenSequence2BiggestDiscount() {
		Showing showing = createMockShowing();
		showing.setSequence(2);
		showing.getMovie().setSpecialCode(0);
		assertEquals(10.50, showing.calculateTicketPrice());
	}
	
	@Test 
	void calculateTicketPriceWhenSequence7BiggestDiscount() {
		Showing showing = createMockShowing();
		showing.setSequence(7);
		showing.getMovie().setSpecialCode(0);
		assertEquals(11.50, showing.calculateTicketPrice());
	}
	
	@Test 
	void calculateTicketPriceWhenStartTimeBiggestDiscount() {
		Showing showing = createMockShowing();
		showing.setSequence(7);
		showing.getMovie().setSpecialCode(1);
		showing.setStartTime(LocalDateTime.of(2022, Month.AUGUST, 29, 12, 30, 40));
		assertEquals(9.375, showing.calculateTicketPrice());
	}
	
	@Test 
	void calculateTicketPriceWhenSpecialCodeBiggestDiscount() {
		Showing showing = createMockShowing();
		showing.setSequence(2);
		assertEquals(10.00, showing.calculateTicketPrice());
	}
	
	@Test
	void testEquality() {
		Showing showing1 = createMockShowing();
		Showing showing2 = createMockShowing();
		assertEquals(showing1, showing2);
		
		showing2.setSequence(3);
		assertNotEquals(showing1, showing2);
	}
	
	private Showing createMockShowing() {
		String title = "Spider-Man: No Way Home";
		LocalDateTime start = LocalDateTime.of(2022, Month.AUGUST, 29, 19, 30, 40);
		Movie spiderMan = new Movie(title, Duration.ofMinutes(90),12.5, 1);
	    Showing showing = new Showing(spiderMan, 1, start);
	    return showing;
	}

}