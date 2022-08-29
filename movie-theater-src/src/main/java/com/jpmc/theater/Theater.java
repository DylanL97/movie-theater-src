package com.jpmc.theater;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONObject;
import com.google.gson.*;

public class Theater {

    LocalDateProvider provider;
    private HashMap<Integer, Showing> schedule;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        
        // Store schedule as hash map of sequence to Showing for O(1) access to Showings by sequence when reserving
        // Also eliminates the need to enter schedule inputs in correct sequence order
        schedule = new HashMap<>(
        		Map.of(
            1, new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            2, new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))), 
            3, new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))), 
            4, new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))), 
            5, new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))), 
            6, new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))), 
            7, new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))), 
            8, new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))), 
            9, new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        ));
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        showing = schedule.get(sequence);
        if (showing == null) {
            throw new IllegalArgumentException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    @SuppressWarnings("unchecked")
	public void printSchedule() {
    	
        System.out.println("===================================================");
        
        // Store schedule as mapping of movie titles to list of showings to display data in an
        // intuitive, organized fashion
        JSONObject scheduleJson = new JSONObject();
        HashMap<String, ArrayList<JSONObject>> showings = new HashMap<>();
        
        for (Showing s : schedule.values()) {
        	String currTitle = s.getMovie().getTitle();
        	
        	JSONObject showingJson = new JSONObject();
        	LocalDateTime start = s.getStartTime();
        	String month = start.getMonth().toString();
        	int day = start.getDayOfMonth();
        	int year = start.getYear();
        	String date = month + " " + day + ", " + year;
        	Double fee = s.calculateTicketPrice();
        	String startTime = s.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        	showingJson.put("Start Time", startTime);
        	showingJson.put("Date", date);
        	showingJson.put("Sequence", s.getSequence());
        	showingJson.put("Runtime", humanReadableFormat(s.getMovie().getRunningTime()));
        	// ensure price is always displayed to 2 decimal places
        	showingJson.put("Ticket Price", "$" + DECIMAL_FORMAT.format((Double) fee));
        	
        	showings.computeIfAbsent(currTitle, t -> new ArrayList<>()).add(showingJson);
        }
        for (String title : showings.keySet()) {
        	scheduleJson.put(title, showings.get(title));
        }
        
        // format Json schedule for pretty printing
        String jsonString = scheduleJson.toString();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement json = gson.fromJson(jsonString,JsonElement.class);
        String printedSchedule = gson.toJson(json);
        System.out.println(printedSchedule);
        System.out.println("===================================================");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}
