

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

public class OrderProduct {
    private long orderId;
    private long productId;
    private int quantity;

    //constructor that gets a string with the orderInfo then splits the
    //text into an array and places the appropriate values into the appropriate fields
    public OrderProduct(String orderInfo)
    {
        List<String> op= Stream.of(orderInfo.split(" ")).collect(Collectors.toList());
        orderId = Long.parseLong(op.get(2));
        productId = Long.parseLong(op.get(5));
        quantity = Integer.parseInt(op.get(7));
    }

    public OrderProduct(long oId, long pId, int quantity)
    {
        setOrderId(oId);
        setProductId(pId);
        setQuantity(quantity);
    }

    public String toString()
    {
        return "ord id: "+ getOrderId() + " prod id: "+ getProductId() +" quantity: "+ getQuantity()+"\n";
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
