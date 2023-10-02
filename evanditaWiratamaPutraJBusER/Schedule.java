package evanditaWiratamaPutraJBusER;
import java.sql.Timestamp;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.*;

public class Schedule
{
    
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability = new LinkedHashMap<>();
    
    public Schedule (Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability (int numberOfSeats)
    {
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++)
        {
            String sn = seatNumber < 10 ? "0"+seatNumber : ""+seatNumber;
            seatAvailability.put("ER" + sn, true);
        }
    }
    
    public boolean isSeatAvailable (String seat)
    {
        /*
        System.out.println("Masuk sini dengan seat: " + seat);
        for (Map.Entry<String, Boolean> a: seatAvailability.entrySet())
        {

            String key = a.getKey();
            Boolean value = a.getValue();
            System.out.print(key + ":" + value);

        }
        */
        if (seatAvailability.containsKey(seat) && seatAvailability.get(seat))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void bookSeat (String seat)
    {
        seatAvailability.put(seat, false);
    }
    
    public void printSchedule () 
        throws ParseException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        
        // Print tanggal keberangkatan
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        
        // Print daftar kursi dan ketersediaan kursinya
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        
        // Create a list of seats and sort them numerically
        int maxSeatsPerRow = 4; // Assuming there are 4 seats per row
        int currentSeat = 1;
        
        for (String seat : this.seatAvailability.keySet())
        {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat ++;
        }
        System.out.println("\n");
    }
}
