package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
	
    @Test
    void totalFeeForCustomerWhen25PercentDiscountHighest() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(37.5, reservation.getTotalFee());
    }
    
    @Test
    void throwExceptionWhenSequenceNotFound() {
    	Customer john = new Customer("John Doe");
    	Theater theater = new Theater(LocalDateProvider.singleton());
    	IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
					theater.reserve(john, 12, 4);
				});
    	assertEquals("not able to find any showing for given sequence 12", thrown.getMessage());
    } 

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}
