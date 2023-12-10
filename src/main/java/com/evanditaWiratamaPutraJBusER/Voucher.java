package com.evanditaWiratamaPutraJBusER;
import com.evanditaWiratamaPutraJBusER.dbjson.Serializable;
/**
 * Kelas {@code Voucher} merepresentasikan voucher yang dapat diterapkan pada suatu harga,
 * memberikan diskon atau rabat berdasarkan kondisi tertentu.
 *
 * <p>Instances dari kelas ini dibuat dengan nama unik, kode, jenis, jumlah minimum,
 * dan nilai diskon/rabat. Setelah voucher diterapkan, voucher ditandai sebagai sudah digunakan.
 *
 * <p>Kelas {@code Voucher} mengimplementasikan antarmuka {@code Serializable} untuk menandakan
 * bahwa instansinya dapat diserialisasi.
 * @author Evandita
 */
public class Voucher extends Serializable
{
    /**
     * Nama dari voucher.
     */
    public String name;
    /**
     * Flag yang menandakan apakah voucher sudah digunakan atau belum.
     */
    private boolean used;
    /**
     * Jumlah minimum yang diperlukan agar voucher dapat diterapkan.
     */
    public double minimum;
    /**
     * Nilai diskon atau rabat yang diberikan oleh voucher.
     */
    public double cut;
    /**
     * Kode unik yang terkait dengan voucher.
     */
    public int code;
    /**
     * Jenis dari voucher, baik {@code Type.DISCOUNT} atau {@code Type.REBATE}.
     */
    public Type type;


    /**
     * Membangun instance {@code Voucher} baru dengan parameter yang telah ditentukan.
     *
     * @param name     Nama dari voucher.
     * @param code     Kode unik yang terkait dengan voucher.
     * @param type     Jenis dari voucher, baik {@code Type.DISCOUNT} atau {@code Type.REBATE}.
     * @param minimum  Jumlah minimum yang diperlukan agar voucher dapat diterapkan.
     * @param cut      Nilai diskon atau rabat yang diberikan oleh voucher.
     */
    public Voucher (String name, int code, Type type, double minimum, double cut)
    {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }

    /**
     * Memeriksa apakah voucher sudah digunakan atau belum.
     *
     * @return {@code true} jika voucher sudah digunakan, {@code false} jika belum.
     */
    public boolean isUsed ()
    {
        return used;
    }

    /**
     * Memeriksa apakah voucher dapat diterapkan pada harga yang diberikan.
     *
     * @param price Harga yang diperiksa sesuai dengan kondisi voucher.
     * @return {@code true} jika voucher dapat diterapkan, {@code false} jika tidak.
     */
    public boolean canApply (Price price)
    {
        if (price.price > minimum && used == false)
        {
            return true;
        }
        return false;
    }

    /**
     * Menerapkan voucher pada harga yang diberikan dan mengembalikan jumlah yang didiskon atau dirabat.
     *
     * @param price Harga yang menjadi target penerapan voucher.
     * @return Jumlah yang didiskon atau dirabat.
     */
    public double apply (Price price)
    {
        used = true;
        if (cut < 0)
        {
            cut = 0;
        }
        
        if (type == Type.DISCOUNT)
        {
            if (cut > 100) 
            {
                cut = 100;
            }
            return (double)(100 - cut)/100 * price.price;
        }
        else if (type == Type.REBATE)
        {
            if (cut > price.price)
            {
                return 0;
            }
            return price.price - cut;
        }
        else return 0;
    }
    
}
