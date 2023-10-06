package evanditaWiratamaPutraJBusER;


import java.util.HashMap;

public class Serializable implements Comparable<Serializable>
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable()
    {
        int count = mapCounter.getOrDefault(this.getClass(), 0);
        this.id = count;
        mapCounter.put(this.getClass(), count + 1);

    }

    public static <T> Integer getLastAssignedId (Class <T> Obj)
    {
        return mapCounter.get(Obj) ;
    }

    public static <T> Integer setLastAssignedId (Class <T> Obj, int id)
    {
        return mapCounter.replace(Obj, id);
    }

    public int compareTo (Serializable s)
    {
        if (id == s.id)
        {
            return 0;
        }
        else if (id > s.id)
        {
            return 1;
        }
        else {
            return -1;
        }
    }

    public boolean equals(Serializable s)
    {
        if (s.id == id){
            return true;
        }
        return false;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof Serializable && ((Serializable)obj).id == this.id )
        {
            return true;
        }
        else return false;
    }
}
