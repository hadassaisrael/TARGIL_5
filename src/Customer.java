

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Customer {
    private long id;
    private String name;
    private int tier;

    //constructor that gets a string with the orderInfo then splits the
    //text into an array and places the appropriate values into the appropriate fields
    public Customer(String customerInfo)
    {
        List<String> c= Stream.of(customerInfo.split(" ")).collect(Collectors.toList());
        id= Long.parseLong(c.get(1));
        name= c.get(3);
        tier=Integer.parseInt(c.get(5));
    }

    public Customer(long Cid,String Cname, int Ctier)
    {
        setId(Cid);
        setName(Cname);
        setTier(Ctier);
    }

    public String toString()
    {
        return "customer: "+ getId() + " name: "+ getName() +" tier: "+ getTier()+"\n";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}