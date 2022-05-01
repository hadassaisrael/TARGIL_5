import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

public class Product
{
    private long ProductId;
    private String name;
    private ProductCategory category;
    private double price;

    //constructor that gets a string with the orderInfo then splits the
    // //text into an array and places the appropriate values into the appropriate fields
    public Product(String orderInfo)
    {
        List<String> p= Stream.of(orderInfo.split(" ")).collect(Collectors.toList());
        ProductId = Long.parseLong(p.get(1));
        name= p.get(2);
        category= ProductCategory.valueOf(p.get(4));
        price=Double.parseDouble(p.get(6));
    }

    public Product(long PId, String Pname, ProductCategory Pcategory, double Pprice)
    {
        setProductId(PId);
        setName(Pname);
        setCategory(Pcategory);
        setPrice(Pprice);

    }


    public String toString()
    {
        return "Product: "+ getProductId() + " "+ getName() +" category: "+ getCategory() + " price: "+ getPrice()+"\n";
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}