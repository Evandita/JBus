package evanditaWiratamaPutraJBusER;
import java.sql.Timestamp;
import java.util.*;
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
        if (seatAvailability.containsKey(seat) && seatAvailability.get(seat))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isSeatAvailable (List<String> seat)
    {
        boolean ret = false;
        for (String element: seat){
            if (seatAvailability.containsKey(element) && seatAvailability.get(element))
            {
                ret = true;
            }
            else
            {
                ret = false;
                break;
            }
        }
    return ret;
    }
    
    public void bookSeat (String seat)
    {
        seatAvailability.put(seat, false);
    }

    public void bookSeat (List<String> seat)
    {
        for (String element: seat) {
            seatAvailability.put(element, false);
        }
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

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        return "Schedule\t: " + formattedDepartureSchedule + "\nOccupied\t: " + Algorithm.count(seatAvailability.values().iterator(), false) + "/" + seatAvailability.size();
    }
}
