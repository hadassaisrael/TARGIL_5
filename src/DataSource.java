

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class DataSource {

    public static List<Customer> allCustomers;
    public static List<Order> allOrders;
    public static List<Product> allProducts;
    public static List<OrderProduct> allOrderProducts;
    // Update this path according to your data files location
    public static String basePath = "";
    public static String customersPath = basePath +"customers.txt";
    public static String ordersPath = basePath +"orders.txt";
    public static String productsPath = basePath +"products.txt";
    public static String orderProductPath = basePath +"orderProduct.txt";

    static
    {
        try {
            allCustomers = readCustomersfromFile();
            allOrders = readOrdersfromFile();
            allProducts = readProductsfromFile();
            allOrderProducts = readOrderProductsfromFile();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public static List<Customer> readCustomersfromFile() throws IOException {
        return Files.lines(Paths.get(customersPath)).map(l->new Customer(l)).collect(Collectors.toList());
    }

    public static List<Order> readOrdersfromFile() throws IOException {
        return Files.lines(Paths.get(ordersPath)).map(l->new Order(l)).collect(Collectors.toList());
    }

    public static List<Product> readProductsfromFile() throws IOException {
        return Files.lines(Paths.get(productsPath)).map(l->new Product(l)).collect(Collectors.toList());
    }

    public static List<OrderProduct> readOrderProductsfromFile() throws IOException {
        return Files.lines(Paths.get(orderProductPath)).map(l->new OrderProduct(l)).collect(Collectors.toList());
    }
}


