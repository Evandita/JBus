package evanditaWiratamaPutraJBusER;
import java.text.*;
import java.text.ParseException;
import java.util.*;
import java.sql.Timestamp;

public class JBus
{
    public static void main (String[] args)
    throws ParseException
    {
        /*        
        System.out.println(getBusId());
        System.out.println(getBusName());
        if (isDiscount()){
            System.out.println("Berhasil");
        }
        System.out.println(getDiscountPercentage(1000,900));
        System.out.println(getDiscountPercentage(1000,0));
        System.out.println(getDiscountPercentage(0,0));
        
       
        System.out.println(getDiscountedPrice(1000,10));
        System.out.println(getDiscountedPrice(1000,100));
        System.out.println(getDiscountedPrice(1000,120));
        System.out.println(getDiscountedPrice(0,00));
        
       
       System.out.println(getOriginalPrice(900,10));
       System.out.println(getOriginalPrice(1000,0));
       System.out.println(getOriginalPrice(0,100));
       System.out.println(getOriginalPrice(0,120));
        
       
       System.out.println(getAdminFee(1000));
       System.out.println(getAdminFee(500));
       System.out.println(getAdminFee(0));
       
       
       System.out.println(getTotalPrice(10000,2));
       System.out.println(getTotalPrice(5000,1));
       System.out.println(getTotalPrice(0,2));
       */
       
       //CS 2
       /*
       Bus testBus = createBus();
       System.out.println(testBus.name);
       System.out.println(testBus.facility);
       System.out.println(testBus.price.price);
       System.out.println(testBus.capacity);
       */
      
       //CS 3
       /*
       Payment testPayment = new Payment(1, 1, 1, "A", 1, "A", "A");
       Invoice testInvoice = new Invoice(2, 2, 2, "B");
       Station testStation = new Station(3, "C", City.DEPOK);
       System.out.println(testPayment.print());
       System.out.println(testInvoice.print());
       System.out.println(testStation.print());
       */
      
       //PT 3
       /*
       Review testReview = new Review(1, "23 August 2023", "Bad Quality");
       Price testPrice = new Price(100000, 20000);
       Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "JL. Margonda Raya");
       Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
       Bus testBus = new Bus(1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
       Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
       Rating testRating = new Rating();
       System.out.println(testReview);
       System.out.println(testBus);
       System.out.println(testAccount);
       System.out.println(testPrice);
       System.out.println(testRating);
       */
       /*
       Price[] unfilteredArray = new Price[5];
       for (int i = 0;i < unfilteredArray.length;i++)
       {
           int j = 5000;
           unfilteredArray[i] = new Price((i+1)*j);
       }
       
       System.out.println("Price List");
       
       for (Price price : unfilteredArray)
       {
           System.out.println(price.price);
       }
       
       System.out.println("Below 12000.0");
       System.out.println(Validate.filter(unfilteredArray, 12000, true));
       System.out.println("Above 10000.0");
       System.out.println(Validate.filter(unfilteredArray, 10000, false));

       
       Bus testBus = createBus();
       
       //Payment
       Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
       System.out.println(testPayment.getDepartureInfo());
       System.out.println(testPayment.getTime());
       
       // Tes Schedule
       Calendar schedule1 = Calendar.getInstance();
       testBus.addSchedule(schedule1);
       Calendar schedule2 = Calendar.getInstance();
       schedule2.add(Calendar.DAY_OF_MONTH, 3);
       testBus.addSchedule(schedule2);
       
       for (Schedule s: testBus.schedules)
       {
           testBus.printSchedule(s);
       }
       
       */
      
    Bus b = createBus();
    Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
    Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
    b.addSchedule(schedule1);
    b.addSchedule(schedule2);
    b.schedules.forEach(schedule ->
    {
        try
        {
             schedule.printSchedule();
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }
    });

    // Invalid date
    Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
    System.out.println("Make booking at July 19, 2023 15:00:00 Seat ER12");
    System.out.println(Payment.makeBooking(t1, "ER12", b));
    // Valid date, invalid seat
    Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat ER20");
    System.out.println(Payment.makeBooking(t2, "ER20", b));
    // Valid date, valid seat
    System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat ER07");
    System.out.println(Payment.makeBooking(t2, "ER07", b));
    Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat ER01");
    
    System.out.println(Payment.makeBooking(t3, "ER01", b));
    System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat ER01 again");
    System.out.println(Payment.makeBooking(t3, "ER01", b));
    // Check if the data changed
    System.out.println("\nUpdated Schedule\n");
    b.schedules.forEach(schedule ->
    {
        try
        {
             schedule.printSchedule();
        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }
    });

    }
    
    public static int getBusId ()
    {
        return 0;
    }
    
    public static String getBusName ()
    {
        return "Bus";
    }
    
    public static boolean isDiscount ()
    {
        return true;
    }
    
    public static float getDiscountPercentage (int beforeDiscount, int afterDiscount)
    {
        if (afterDiscount >= beforeDiscount){
            return 0;
        }
        else{
            return (float)((beforeDiscount - afterDiscount))/beforeDiscount * 100;
        }
    }
    
    public static int getDiscountedPrice (int price, float discountPercentage)
    {
        if (discountPercentage > 100){
            discountPercentage = 100;
        }      
        return (int)((100-discountPercentage)/100 * price);
    }
    
    public static int getOriginalPrice (int discountedPrice, float discountPercentage)
    {
        if (discountPercentage > 100){
            discountPercentage = 100;
        }
        return (int)(discountedPrice/(100-discountPercentage) * 100);
    }
    
    public static float getAdminFeePercentage ()
    {
        return (float)(0.05);
    }
    
    public static int getAdminFee (int price)
    {
        return (int)(getAdminFeePercentage() * price);
    }
    
    public static int getTotalPrice (int price,int numberOfSeat)
    {
        return (int)(price * numberOfSeat + getAdminFee(price *numberOfSeat));
    }
    
        public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
    
}
