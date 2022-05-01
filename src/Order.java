
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Long.parseLong;

public class Order {

    private long orderId;
    private Date orderDate;
    private Date deliveryDate;
    private OrderStatus status;

    private long customrId;

    //Builder with parameter of order details. We converted the dates from string to date.
    public Order(String orderInfo) {
        List<String> o= Stream.of(orderInfo.split(" ")).collect(Collectors.toList());
        orderId= Long.parseLong(o.get(1));
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Date OrderDate= null;
        try {
            OrderDate = formatter1.parse(o.get(4));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderDate= OrderDate;
        Date DeliveryDate= null;
        try {
            DeliveryDate = formatter1.parse(o.get(7));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        deliveryDate= DeliveryDate;
        status = OrderStatus.valueOf(o.get(9));
        customrId= Long.parseLong(o.get(12));
    }

    public Order(long Oid, Date OorderDate, Date OdeliveryDate, OrderStatus Ostatus, long OcustomrId)
    {
        setOrderId(Oid);
        setOrderDate(OorderDate);
        setDeliveryDate(OdeliveryDate);
        setStatus(Ostatus);
        setCustomrId(OcustomrId);
    }



    public String toString()
    {
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
        return "order: "+ getOrderId() + " order date: "+ ft.format(getOrderDate()) +" delivery date: "+ ft.format(getDeliveryDate()) + " status: "+ getStatus() + " customr id: "+ getCustomrId()+"\n";
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getCustomrId() {
        return customrId;
    }

    public void setCustomrId(long customrId) {
        this.customrId = customrId;
    }
}