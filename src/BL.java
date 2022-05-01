import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

public class BL implements IBL {
    @Override
    public Product getProductById(long productId) {
        // stream finds specific element where product id is equal to the given product id
        Product product= DataSource.allProducts.stream().filter(p->p.getProductId()== productId).findAny().orElse(null);

        return product ;
    }

    @Override
    public Order getOrderById(long orderId) {
        // stream finds specific element where order id is equal to the given order id
        Order order= DataSource.allOrders.stream().filter(o->o.getOrderId()== orderId).findAny().orElse(null);

        return order ;
    }

    @Override
    public Customer getCustomerById(long customerId) {
        // stream finds specific element where customer id is equal to the given customer id
        Customer customer = DataSource.allCustomers.stream().filter(c->c.getId()== customerId).findAny().orElse(null);

        return customer ;
    }

    // returns list of products in the cat category and their price is equal to or less than the price.
    //The list will be sorted in ascending order by ID number.
    @Override
    public List<Product> getProducts(ProductCategory cat, double price) {

        return DataSource.allProducts.stream()
                .filter(p->p.getCategory()== cat && p.getPrice()<= price)
                .sorted((p1,p2)->((Long)p1.getProductId()).compareTo(p2.getProductId()))
                .collect(Collectors.toList());

    }

    //The function will return the list of popular customers. A popular customer is a customer who is in tier 3,
    // and has more than 10 orders.
    //The list will be sorted in ascending order by customer ID number.
    @Override
    public List<Customer> popularCustomers() {
        Map<Long , Long> counters = DataSource.allOrders.stream()
                .collect(Collectors.groupingBy(o ->o.getCustomrId(), Collectors.counting()));

        return DataSource.allCustomers.stream()
                .filter(c->c.getTier()== 3 && counters.containsValue(c.getId()))
                .sorted((p1,p2)->((Long)p1.getProductId()).compareTo(p2.getProductId()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Order> getCustomerOrders(long customerId) {
        //To do
        return null;
    }

    @Override
    public long numberOfProductInOrder(long orderId) {
        //To do
        return 0;
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {
        //To do
        return null;
    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
        //To do
        return null;
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        //To do
        return null;
    }

    @Override
    public Product getMaxOrderedProduct() {
        //To do
        return null;

    }
    @Override
    public double sumOfOrder(long orderID) {
        //To do
        return 0;
    }

    @Override
    public List<Order> getExpensiveOrders(double price) {
        //To do
        return null;
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {
        //To do
        return null;

    }

}
