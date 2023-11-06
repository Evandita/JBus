package evanditaWiratamaPutraJBusER;
import java.text.*;
import java.util.*;
import java.sql.Timestamp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class JBus
{
    public static void main (String[] args)
            throws ParseException, InterruptedException {
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
        //PT 4
        /*
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

         */
        //PRE CS Modul 5
        /*
        Integer[] numbers = {10,20,30,40,50};
        int valueToCheck = 30;

        boolean result = Algorithm.exists(numbers, valueToCheck);
        if (result) {
            System.out.println(valueToCheck + " terdapat dalam array.");
        }
        else {
            System.out.println(valueToCheck + " tidak terdapat dalam array.");
        }
        System.out.println("Hello from IntelliJ!");
        Bus bus1 = createBus();
        Bus bus2 = createBus();
        Bus bus3 = createBus();
        Bus bus4 = createBus();
        Bus bus5 = createBus();
        System.out.println(bus1.toString());
        System.out.println(bus2.toString());
        System.out.println(bus3.toString());
        System.out.println(bus4.toString());
        System.out.println(bus5.toString());
        */

        //CS MODUL 5
        /*
        Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number "+Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);
        */

        // PT Modul 5
        // Tes Pagination
        /*
        Bus b = createBus();
        List<Timestamp> listOfSchedules = new ArrayList<>();
        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));

        listOfSchedules.forEach(b::addSchedule);
        System.out.println("Page 1");
        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");
        System.out.println("Page 2");
        Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");

        // Tes Booking
        String msgSuccess = "Booking Success!";
        String msgFailed = "Booking Failed";
        // valid date, invalid seat = Booking Failed
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: ER17 ER18");
        System.out.println(Payment.makeBooking(t1, List.of("ER17", "ER18"), b)? msgSuccess : msgFailed);
        // valid date, invalid seat = Booking Failed
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("Make booking at July 18, 2023 15:00:00 Seat ER26");
        System.out.println(Payment.makeBooking(t2, "ER26", b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: ER07 ER08");
        System.out.println(Payment.makeBooking(t2, List.of("ER07", "ER08"), b)? msgSuccess : msgFailed);
        // valid date, valid seat = Booking Success
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: ER01 ER02");
        System.out.println(Payment.makeBooking(t3, List.of("ER01", "ER02"), b)? msgSuccess : msgFailed);
        // valid date, book the same seat = Booking Failed
        System.out.println("Make booking at July 20, 2023 12:00:00 Seat ER01");
        System.out.println(Payment.makeBooking(t3, "ER01", b)? msgSuccess : msgFailed);
        // check if the data changed
        System.out.println("\nUpdated Schedule");
        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);
        */

        /*
        //TP Modul 6

        String filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\data\\station.json";
        Gson gson = new Gson();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filepath));
            List<Station> stationjson = gson.fromJson(buffer, new TypeToken<List<Station>>() {}.getType());
            stationjson.forEach(e -> System.out.println(e.toString()));
            System.out.println();
            buffer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

         */

        /*
        //CS Modul 6
        try{
            String filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\data\\buses_CS.json";
            JsonTable<Bus> busList = new JsonTable<> (Bus.class, filepath);
            List<Bus> filteredBus = filterByDepartureAndArrival(busList, City.JAKARTA, City.SURABAYA,0,3);
            //Bus filteredBusId = filterBusId(busList, 155);
            //System.out.println(filteredBusId.toString());
            filteredBus.forEach(bus->System.out.println(bus.toString()));
            /*
            //Tes Duplikat
            Bus b =  createBus();
            b.addSchedule(Timestamp.valueOf("2023-7-18 15:00:00"));
            b.addSchedule(Timestamp.valueOf("2023-7-18 15:00:00"));

        }
        catch (Throwable t){
            t.printStackTrace();
        }
        */

        //PT Modul 6
        Bus bus = createBus();
        for (Schedule schedule1 : bus.schedules) {
            schedule1.printSchedule();
        }
        for(int i =0; i < 10; i++){
            BookingThread thread = new BookingThread("Thread " + i,bus,
                    Timestamp.valueOf("2023-07-27 19:00:00"));
        }
        Thread.sleep(1000);
        for (Schedule schedule : bus.schedules) {
            schedule.printSchedule();
        }

        try {

            String filepath = "C:\\Users\\Evandita\\Documents\\Tugas Kuliah\\Semester 3\\OOP 02\\Praktikum\\Pre CS\\Modul 1\\JBus\\data\\accountDatabase.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filepath);

            Account newAcc = new Account("Dio", "dio@gmail.com", "NgikNgok");
            tableAccount.add(newAcc);

            JsonTable.writeJson(tableAccount, filepath);
            tableAccount.forEach(System.out::println);
        }

        catch (Throwable t) {
            t.printStackTrace();
        }

    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize)
    {
        try {

            Predicate<Bus> s = (val) -> val.departure.city.equals(departure);
            List<Bus> filteredBus = Algorithm.collect(buses, s);
            return Algorithm.paginate(filteredBus,page, pageSize, t->true);
        }
        catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max)
    {
        try {

            Predicate<Bus> s = (val) -> val.price.price >= min && val.price.price <= max;
            List<Bus> filteredBus = Algorithm.collect(buses, s);
            return filteredBus;
        }
        catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses,City departure, City arrival, int page, int pageSize)
    {
        try {

            Predicate<Bus> s = (val) -> val.departure.city.equals(departure) && val.arrival.city.equals(arrival);
            List<Bus> filteredBus = Algorithm.collect(buses, s);
            return Algorithm.paginate(filteredBus,page, pageSize, t->true);
        }
        catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    public static Bus filterBusId(List<Bus> buses,int id)
    {
        try {

            Predicate<Bus> s = (val) -> val.id == id;
            Bus filteredBus = Algorithm.find(buses, s);
            return filteredBus;
        }
        catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }


    private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }
    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }
    public static void testFind(Integer[] t) {
        Integer valueToFind = 69;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found!" + result2);
        } else {
            System.out.println("Not Found");
        }
    }
    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val) -> val <= 22;
        Predicate<Integer> above = (val) -> val > 43;

        List<Integer> integerBelow = Algorithm.collect(t, below);
        List<Integer> integerAbove = Algorithm.collect(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);
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
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25,
                BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK,
                "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
                Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }


}