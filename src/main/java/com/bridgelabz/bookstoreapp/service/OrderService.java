package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.OrderDetails;
import com.bridgelabz.bookstoreapp.exception.OrderException;
import com.bridgelabz.bookstoreapp.exception.UserRegistrationException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.model.Orders;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Slf4j generates logger instance
@Slf4j
@Service
public class OrderService implements IOrderService {
    //Autowired repository class to inject its dependency
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    //Autowired ICartService interface so we can inject its dependency here
    @Autowired
    ICartService cartService;

    //Created service method which serves controller api to add items
    @Override
    public Orders insertOrderItem(OrderDetails orderDetails) {
        List<Cart> cartItems = cartService.getAllCartItems();
        Cart firstElement = cartItems.get(0);
        Optional<Book> book = bookRepository.findById(firstElement.getBook().getBookID());
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findById(orderDetails.getUserID());
        if (book.isPresent() && userRegistration.isPresent()) {
            if (firstElement.getQuantity() < book.get().getQuantity()) {
                Orders newOrder = new Orders(book.get().getPrice(), firstElement.getQuantity(), orderDetails.getAddress(), book.get(), userRegistration.get(), false);
                orderRepository.save(newOrder);
                log.info("Order record inserted successfully");
                cartService.deleteAllFromCart();
                return newOrder;
            } else {
                throw new OrderException("Requested quantity is not available");
            }
        } else {
            throw new OrderException("Book or User doesn't exists");
        }

    }

    //Created service method which serves controller api to get all orders
    @Override
    public List<Orders> getAllOrderItems() {
        List<Orders> orders = orderRepository.findAll();
        log.info(" Order Items get successfully");
        return orders;
    }

    //Created service method which serves controller api to get order by id
    @Override
    public Orders getOrderItems(Integer orderID) {
        Optional<Orders> orders = orderRepository.findById(orderID);
        if (orders.isEmpty()) {
            throw new OrderException("Order Item doesn't exists");
        } else {
            log.info("Order item get successfully for id " + orderID);
            return orders.get();
        }
    }

    //Created service method which serves controller api to update order by id
    @Override
    public Orders updateOrderItems(Integer orderID, OrderDTO orderdto) {
        Optional<Orders> orders = orderRepository.findById(orderID);
        Optional<Book> book = bookRepository.findById(orderdto.getBookID());
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findById(orderdto.getUserID());
        if (orders.isEmpty()) {
            throw new OrderException("Order Record doesn't exists");
        } else {
            if (book.isPresent() && userRegistration.isPresent()) {
                if (orderdto.getQuantity() < book.get().getQuantity()) {
                    Orders newOrder = new Orders(orderID, book.get().getPrice(), orderdto.getQuantity(), orderdto.getAddress(), book.get(), userRegistration.get(), orderdto.isCancell());
                    orderRepository.save(newOrder);
                    log.info("Order record updated successfully for id " + orderID);
                    book.get().setQuantity(book.get().getQuantity() - (orderdto.getQuantity() - orders.get().getQuantity()));
                    bookRepository.save(book.get());
                    return newOrder;
                } else {
                    throw new OrderException("Requested quantity is not available");
                }
            } else {
                throw new UserRegistrationException("Book or User doesn't exists");

            }
        }
    }

    //Created service method which serves controller api to delete order by id
    @Override
    public Orders deleteOrderItem(Integer orderID) {
        Optional<Orders> orders = orderRepository.findById(orderID);
        Optional<Book> book = bookRepository.findById(orders.get().getBook().getBookID());
        if (orders.isEmpty()) {
            throw new OrderException("Order Record doesn't exists");
        } else {
            book.get().setQuantity(book.get().getQuantity() + orders.get().getQuantity());
            bookRepository.save(book.get());
            orderRepository.deleteById(orderID);
            log.info("Order record deleted successfully for id " + orderID);
            return orders.get();
        }
    }
}
