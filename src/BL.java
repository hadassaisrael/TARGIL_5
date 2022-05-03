import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static java.util.Collections.max;
import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;

public class BL implements IBL {
    @Override
    public Product getProductById(long productId) {
        // stream finds specific element where product id is equal to the given product id
        Product product= DataSource.allProducts.stream()
                .filter(p->p.getProductId()== productId).findAny().orElse(null);

        return product ;
    }

    @Override
    public Order getOrderById(long orderId) {
        // stream finds specific element where order id is equal to the given order id
        Order order= DataSource.allOrders.stream()
                .filter(o->o.getOrderId()== orderId).findAny().orElse(null);

        return order ;
    }

    @Override
    public Customer getCustomerById(long customerId) {
        // stream finds specific element where customer id is equal to the given customer id
        Customer customer = DataSource.allCustomers.stream()
                .filter(c->c.getId()== customerId).findAny().orElse(null);

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
        //Mapping the order list by customer and how much he ordered
        Map<Long , Long> counters = DataSource.allOrders.stream()
                .collect(groupingBy(o ->o.getCustomrId(),counting()));
        //List of customer IDs that have ordered more than 10 orders
        List<Long> frequentCustomer= counters.entrySet().stream()
                .sorted(comparingByValue(reverseOrder()))
                .filter(c-> c.getValue()>10)
                .map(Map.Entry::getKey)
                .collect(toList());

        //The function will return the list of popular customers.
        // A popular customer is a customer who is in tier 3, and has more than 10 orders.
        // The list will be sorted in ascending order by customer ID number.
        return DataSource.allCustomers.stream()
                .filter(c->c.getTier()== 3 && frequentCustomer.contains(c.getId()))
                .sorted((p1,p2)->((Long)p1.getId()).compareTo(p2.getId()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Order> getCustomerOrders(long customerId) {
        //The function will return the list of customer orders with customerId ID.
        //  and will be sorted in ascending order by order identification number.
        return DataSource.allOrders.stream()
                .filter(o->o.getCustomrId()==customerId)
                .sorted((p1,p2)->((Long)p1.getOrderId()).compareTo(p2.getOrderId()))
                .collect(Collectors.toList());
    }

    @Override
    public long numberOfProductInOrder(long orderId) {
        //The function will return the number of products ordered in the order number orderId
        return DataSource.allOrderProducts.stream()
                .filter(op->op.getOrderId()==orderId)
                .count();
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {

        //The function will return the list of popular products.
        // A popular product is a product that has appeared in at least orderedtimes orders.
        return DataSource.allOrderProducts.stream()
                .collect(groupingBy(p->p.getProductId(),counting()))
                .entrySet().stream()
                .filter(c-> c.getValue()>=orderedtimes)
                .map(c-> getProductById(c.getKey()))
                .sorted((p1,p2)->((Long)p1.getProductId()).compareTo(p2.getProductId()))
                .collect(toList());


    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
       //made a list of all id products
        List <Long> products= DataSource.allOrderProducts.stream()
                .filter(p-> p.getOrderId()==orderId)
                .map(p->p.getProductId())
                .collect(Collectors.toList());

        //The function will return the product list to a specific order with orderId
        //and sorted in ascending order by Product ID number.
        return DataSource.allProducts.stream()
                .filter(p-> products.contains(p.getProductId()))
                .sorted((p1,p2)->((Long)p1.getProductId()).compareTo(p2.getProductId()))
                .collect(toList());
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
         //made a list of all id customers that ordered productId
        List <Long> orders= DataSource.allOrderProducts.stream()
                .filter(p-> p.getProductId()==productId)
                .map(p->p.getOrderId())
                .collect(Collectors.toList());

        List <Order> orderList= DataSource.allOrders.stream()
                .filter(p->orders.contains(p.getOrderId()))
                .collect(toList());

        List <Long> customers= orderList.stream()
                .map(p-> p.getCustomrId())
                .collect(toList());

        //The function will return a list of all customers that ordered this specific product
        return DataSource.allCustomers.stream()
                .filter(c-> customers.contains(c.getId()))
                .sorted((p1,p2)->((Long)p1.getId()).compareTo(p2.getId()))
                .collect(toList());
    }


    @Override
    public Product getMaxOrderedProduct() {
        //The function will return the product that has been ordered the most times.
        Map <Long,Long> orderedProduct = DataSource.allOrderProducts.stream()
                .collect(groupingBy(OrderProduct::getProductId, counting()));

        long max= orderedProduct .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
        List <Product> maxProduct= DataSource.allProducts.stream()
                .filter(p -> p.getProductId() == max)
                .collect(toList());
        return maxProduct.get(0);

    }


    @Override
    public double sumOfOrder(long orderID) {
        //The function returns the total price of an order with the orderID order number.
        List <Double> productsInOrder =  DataSource.allOrderProducts.stream()
                .filter(po-> po.getOrderId()==orderID)
                .map(p-> p.getQuantity()* getProductById(p.getProductId()).getPrice())
                .collect(toList());
        return productsInOrder.stream().mapToDouble(i -> i).sum();
    }

    @Override
    public List<Order> getExpensiveOrders(double price) {

        //map the list of orders by order id and total order amount
        Map<Long , Double> countAllOrders = DataSource.allOrders.stream()
                .collect(toMap(o-> o.getOrderId(),o->sumOfOrder(o.getOrderId())));

        //The function returns the number of orders whose sum satisfies the condition
        // that is greater than or equal to price and sorted in ascending order by order ID number.
        return countAllOrders.entrySet().stream()
                .filter(c-> c.getValue()>=price)
                .map(c->getOrderById(c.getKey()))
                .sorted((p1,p2)->((Long)p1.getOrderId()).compareTo(p2.getOrderId()))
                .collect(toList());
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {


        Stream <Customer> customerStream = DataSource.allCustomers.stream()
                .filter(c -> c.getTier() == 3);
       //Mapping the list of orders by customer and how much he ordered
        Map <Long,Long> customersOrders = DataSource.allOrders.stream()
                .collect(groupingBy(Order::getCustomrId,counting()));

        //the highest number of single customer orders that exist
        long max=customersOrders.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();

        // list of customers with the max number of orders
       List <Long> customerId  =customersOrders.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

       //changed the ids of the customers to list of customers
        List <Customer> maxCustomer = DataSource.allCustomers.stream()
                .filter(c-> customerId.contains(c.getId()))
                        .collect(Collectors.toList());

        return maxCustomer;


    }

}
