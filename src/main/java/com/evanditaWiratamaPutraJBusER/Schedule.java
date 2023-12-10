package com.evanditaWiratamaPutraJBusER;
import java.sql.Timestamp;
import java.util.*;
import java.text.*;

/**
 * Kelas {@code Schedule} merepresentasikan jadwal keberangkatan suatu transportasi,
 * termasuk informasi seperti waktu keberangkatan dan ketersediaan kursi.
 * @author Evandita
 */
public class Schedule
{

    /**
     * Timestamp yang menunjukkan waktu keberangkatan.
     */
    public Timestamp departureSchedule;
    /**
     * Map yang menyimpan informasi ketersediaan kursi dengan format "ERxx" (nomor kursi).
     * Jika nilai true, kursi tersedia; jika false, kursi sudah dipesan.
     */
    public Map<String, Boolean> seatAvailability = new LinkedHashMap<>();

    /**
     * Membangun instance {@code Schedule} dengan parameter yang telah ditentukan.
     *
     * @param departureSchedule Timestamp yang menunjukkan waktu keberangkatan.
     * @param numberOfSeats     Jumlah kursi yang tersedia.
     */
    public Schedule (Timestamp departureSchedule, int numberOfSeats)
    {
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    /**
     * Menginisialisasi ketersediaan kursi berdasarkan jumlah kursi yang tersedia.
     *
     * @param numberOfSeats Jumlah kursi yang tersedia.
     */
    private void initializeSeatAvailability (int numberOfSeats)
    {
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++)
        {
            String sn = seatNumber < 10 ? "0"+seatNumber : ""+seatNumber;
            seatAvailability.put("ER" + sn, true);
        }
    }

    /**
     * Memeriksa apakah kursi tertentu tersedia.
     *
     * @param seat Nomor kursi yang akan diperiksa.
     * @return {@code true} jika kursi tersedia, {@code false} jika sudah dipesan atau tidak valid.
     */
    public boolean isSeatAvailable (String seat)
    {

        if (seatAvailability.containsKey(seat) && seatAvailability.get(seat) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Memeriksa apakah setiap kursi dalam daftar tertentu tersedia.
     *
     * @param seat Daftar nomor kursi yang akan diperiksa.
     * @return {@code true} jika semua kursi tersedia, {@code false} jika ada yang sudah dipesan atau tidak valid.
     */
    public boolean isSeatAvailable (List<String> seat)
    {
        boolean ret = false;
        for (String element: seat){
            if (seatAvailability.containsKey(element) && seatAvailability.get(element) == true)
            {
                ret = true;
            }
            else
            {
                return false;
            }
        }
        return ret;
    }

    /**
     * Memeriksa apakah setiap kursi dalam daftar tertentu tidak tersedia.
     *
     * @param seat Daftar nomor kursi yang akan diperiksa.
     * @return {@code true} jika setidaknya satu kursi tidak tersedia, {@code false} jika semua kursi tersedia atau tidak valid.
     */
    public boolean isSeatNotAvailable (List<String> seat)
    {
        boolean ret = false;
        for (String element: seat){
            if (seatAvailability.containsKey(element) && seatAvailability.get(element) == false)
            {
                ret = true;
            }
            else
            {
                return false;
            }
        }
        return ret;
    }

    /**
     * Memesan kursi tertentu.
     *
     * @param seat Nomor kursi yang akan dipesan.
     */
    public void bookSeat (String seat)
    {
        seatAvailability.put(seat, false);
    }

    /**
     * Memesan setiap kursi dalam daftar tertentu.
     *
     * @param seat Daftar nomor kursi yang akan dipesan.
     */
    public void bookSeat (List<String> seat)
    {
        for (String element: seat) {
            seatAvailability.put(element, false);
        }
    }

    /**
     * Membatalkan pemesanan setiap kursi dalam daftar tertentu.
     *
     * @param seat Daftar nomor kursi yang akan dibatalkan pemesanannya.
     */
    public void unBookSeat (List<String> seat)
    {
        for (String element: seat) {
            seatAvailability.put(element, true);
        }
    }

    /**
     * Mencetak jadwal keberangkatan dan ketersediaan kursi ke konsol.
     *
     * @throws ParseException Jika terdapat kesalahan saat melakukan parsing waktu.
     */
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

    /**
     * Menghasilkan representasi string dari objek {@code Schedule},
     * termasuk informasi waktu keberangkatan dan jumlah kursi yang terisi.
     *
     * @return Representasi string dari objek {@code Schedule}.
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        return "Schedule\t: " + formattedDepartureSchedule + "\nOccupied\t: " + Algorithm.count(seatAvailability.values().iterator(), false) + "/" + seatAvailability.size();
    }
}