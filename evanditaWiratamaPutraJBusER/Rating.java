package evanditaWiratamaPutraJBusER;

public class Rating
{
    private long count;
    private long total;

    public Rating()
    {
        this.total = 0;
        this.count = 0;
    }
    
    public void insert (int rating)
    {
        total += rating;
        count += 1;
    }
    
    public double getAverage ()
    {
        if (count == 0)
        {
            return 0;
        }
        return ((double)total)/count;
    }
    
    public long getCount ()
    {
        return count;
    }
    
    public long getTotal ()
    {
        return total;
    }
    
    public String toString ()
    {
        return "\nClass: Rating\n" + "count: " + count + "\ntotal: " + total;
    }

}
