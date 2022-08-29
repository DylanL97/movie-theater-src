package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieTests {
    
	@Test
	void testGetterMethods() {
		Movie movie = createMockMovie();
	    assertEquals("Spider-Man: No Way Home", movie.getTitle());
	    assertEquals(Duration.ofMinutes(90), movie.getRunningTime());
	    assertEquals(12.5, movie.getBaseTicketPrice());
	    assertEquals(1, movie.getSpecialCode());
	}
	
	@Test
	void testSetterMethods() {
		Movie movie = createMockMovie();
		String title = "Turning Red";
		movie.setTitle(title);
		Duration runningTime = Duration.ofMinutes(100);
		movie.setRunningTime(runningTime);
		movie.setBaseTicketPrice(13);
		movie.setSpecialCode(0);
		
		assertEquals(title, movie.getTitle());
		assertEquals(runningTime, movie.getRunningTime());
		assertEquals(13, movie.getBaseTicketPrice());
		assertEquals(0, movie.getSpecialCode());
		
	}
	
	@Test
	void testEquality() {
		Movie movie1 = createMockMovie();
		Movie movie2 = createMockMovie();
		assertEquals(movie1, movie2);
		movie2.setTitle("The Batman");
		assertNotEquals(movie1, movie2);
	}
	
	Movie createMockMovie() {
		return new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
	}
}
