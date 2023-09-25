package evanditaWiratamaPutraJBusER;


public class JBus
{
    public static void main (String[] args)
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
    /*
    public static Bus createBus ()
    {
        Price price = new Price(750000,5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25);
        return bus;
    }
    */
}
