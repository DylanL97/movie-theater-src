package com.jpmc.theater;

import java.time.LocalDateTime;
import java.util.Objects;

public class Showing {
    private Movie movie;
    private int sequence;
    private LocalDateTime startTime;
    
    private static final int MOVIE_CODE_SPECIAL = 1;

    public Showing(Movie movie, int sequence, LocalDateTime startTime) {
        this.movie = movie;
        this.sequence = sequence;
        this.startTime = startTime;
    }

    public Movie getMovie() {
        return movie;
    }
    
    public void setMovie(Movie movie) {
    	this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
    	this.startTime = startTime;
    }

    public int getSequence() {
        return sequence;
    }
    
    public void setSequence(int sequence) {
    	this.sequence = sequence;
    }
    

    public double calculateTicketPrice() {
        return applyDiscount(movie.getBaseTicketPrice());
    }

    private double applyDiscount(double baseTicketPrice) {
        double discount = 0;
        int startHour = startTime.getHour();
        
        if (MOVIE_CODE_SPECIAL == movie.getSpecialCode()) {
            discount = Math.max(discount, baseTicketPrice * 0.2);  // 20% discount for special movie
        }
        
        if (sequence == 1) {
            discount = Math.max(discount, 3); // $3 discount for 1st show
        } else if (sequence == 2) {
            discount = Math.max(discount, 2); // $2 discount for 2nd show
        } else if (sequence == 7) {
        	discount = Math.max(discount, 1); // $1 discount for 7th show
        }

        if (startHour >= 11 && startHour <= 16) {
        	discount = Math.max(discount, baseTicketPrice * 0.25); // 25% discount for movie starting between 11AM - 4PM
        }

        // biggest discount wins
        return baseTicketPrice - discount;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showing showing = (Showing) o;
        return  Objects.equals(movie, showing.movie)
                && Objects.equals(sequence, showing.sequence)
                && Objects.equals(startTime, showing.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, sequence, startTime);
    }
    
}
