package evanditaWiratamaPutraJBusER;
import java.util.Calendar;
import java.util.Map;
import java.util.LinkedHashMap;

public class Schedule
{
    
    public Calendar departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule (Calendar departureSchedule, int numberofSeats)
    {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberofSeats);
    }
    
    private void initializeSeatAvailability (int numberofSeats)
    {
        seatAvailability = new LinkedHashMap<>();
        for (int i = 1;i <= numberofSeats;i++)
        {
            seatAvailability.put("ER" + i, true);
        }
    }

}
