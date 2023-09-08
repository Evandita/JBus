package evanditaWiratamaPutraJBusER;


public class JBus
{
    public void main(String[] args){
        /*
        System.out.println(getBusId());
        System.out.println(getBusName());
        if (isDiscount()){
            System.out.println("Berhasil");
        }
        System.out.println(getDiscountPercentage(1000,900));
        System.out.println(getDiscountPercentage(1000,0));
        System.out.println(getDiscountPercentage(0,0));
        */
       /*
        System.out.println(getDiscountedPrice(1000,10));
        System.out.println(getDiscountedPrice(1000,100));
        System.out.println(getDiscountedPrice(1000,120));
        System.out.println(getDiscountedPrice(0,00));
        */
       /*
       System.out.println(getOriginalPrice(900,10));
       System.out.println(getOriginalPrice(1000,0));
       System.out.println(getOriginalPrice(0,100));
       System.out.println(getOriginalPrice(0,120));
        */
       /*
       System.out.println(getAdminFee(1000));
       System.out.println(getAdminFee(500));
       System.out.println(getAdminFee(0));
       */
       /*
       System.out.println(getTotalPrice(10000,2));
       System.out.println(getTotalPrice(5000,1));
       System.out.println(getTotalPrice(0,2));
       */
       
    }
    
    public int getBusId(){
        return 0;
    }
    
    public String getBusName(){
        return "Bus";
    }
    
    public boolean isDiscount(){
        return true;
    }
    
    public float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        if (afterDiscount >= beforeDiscount){
            return 0;
        }
        else{
            return (float)((beforeDiscount - afterDiscount))/beforeDiscount * 100;
        }
    }
    
    public int getDiscountedPrice(int price, float discountPercentage){
        if (discountPercentage > 100){
            discountPercentage = 100;
        }      
        return (int)((100-discountPercentage)/100 * price);
    }
    
    public int getOriginalPrice(int discountedPrice, float discountPercentage){
        if (discountPercentage > 100){
            discountPercentage = 100;
        }
        return (int)(discountedPrice/(100-discountPercentage) * 100);
    }
    
    public float getAdminFeePercentage(){
        return (float)(0.05);
    }
    
    public int getAdminFee(int price){
        return (int)(getAdminFeePercentage() * price);
    }
    
    public int getTotalPrice(int price,int numberOfSeat){
        return (int)(price * numberOfSeat + getAdminFee(price *numberOfSeat));
    }
    
}
