package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }
    
    public void setRunningTime(Duration runningTime) {
    	this.runningTime = runningTime;
    }

    public double getBaseTicketPrice() {
        return ticketPrice;
    }
    
    public void setBaseTicketPrice(double ticketPrice) {
    	this.ticketPrice = ticketPrice;
    }
    
    public int getSpecialCode() {
    	return specialCode;
    }
    
    public void setSpecialCode(int specialCode) {
    	this.specialCode = specialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}