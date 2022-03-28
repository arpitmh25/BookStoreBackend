package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.exception.CartException;
import com.bridgelabz.bookstoreapp.exception.UserRegistrationException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Slf4j generates logger instance
@Service
@Slf4j
public class CartService implements ICartService {
    //Autowired repository class to inject its dependency
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;


    //Created service method which serves controller api to add items
    @Override
    public Cart insertItems(CartDTO cartdto) {
        Optional<Book> book = bookRepository.findById(cartdto.getBookID());
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findById(cartdto.getUserID());
        if(book.isPresent() && userRegistration.isPresent()) {
            if(cartdto.getQuantity() < book.get().getQuantity()) {
                Cart newCart = new Cart(cartdto.getQuantity(),book.get(),userRegistration.get());
                cartRepository.save(newCart);
                log.info("Cart record inserted successfully");
                book.get().setQuantity(book.get().getQuantity() - cartdto.getQuantity());
                bookRepository.save(book.get());
                return newCart;
            }else {
                throw new CartException("Requested quantity is not available");
            }
        }
        else {
            throw new UserRegistrationException("Book or User doesn't exists");
        }
    }
    //Created service method which serves controller api to get all items
    @Override
    public List<Cart> getAllCartItems() {
        List<Cart> cart = cartRepository.findAll();
        return cart;
    }

    //Created service method which serves controller api to get card items by id
    @Override
    public Cart getCartRecordById(Integer cartID) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        if (cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        } else {
            log.info("Cart record retrieved successfully for id " + cartID);
            return cart.get();
        }
    }

    //Created service method which serves controller api to update cart items by id
    @Override
    public Cart updateCartItems(Integer cartID, CartDTO cartdto) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        Optional<Book> book = bookRepository.findById(cartdto.getBookID());
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findById(cartdto.getUserID());
        if (cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        } else {
            if(book.isPresent() && userRegistration.isPresent()) {
                if(cartdto.getQuantity() < book.get().getQuantity()) {
                    Cart newCart = new Cart(cartID,cartdto.getQuantity(),book.get(),userRegistration.get());
                    cartRepository.save(newCart);
                    log.info("Cart record updated successfully for id "+cartID);
                    book.get().setQuantity(book.get().getQuantity() -(cartdto.getQuantity() -cart.get().getQuantity()));
                    bookRepository.save(book.get());
                    return newCart;
                }
                else {
                    throw new CartException("Requested quantity is not available");
                }
            }
            else {
                throw new UserRegistrationException("Book or User doesn't exists");
            }
        }
    }

    //Created service method which serves controller api to update delete by id
    @Override
    public Cart deleteCartItems(Integer cartID) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        Optional<Book>  book = bookRepository.findById(cart.get().getBook().getBookID());
        if(cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        }
        else {
            book.get().setQuantity(book.get().getQuantity() + cart.get().getQuantity());
            bookRepository.save(book.get());
            cartRepository.deleteById(cartID);
            log.info("Cart record deleted successfully for id "+cartID);
            return cart.get();
        }
    }



    //Created service method which serves controller api to update quantity items by id
    @Override
    public Cart updateQuantityItems(Integer cartID, Integer quantity) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        Optional<Book>  book = bookRepository.findById(cart.get().getBook().getBookID());
        if(cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        }
        else {
            if(quantity < book.get().getQuantity()) {
                cart.get().setQuantity(quantity);
                cartRepository.save(cart.get());
                log.info("Quantity in cart record updated successfully");
                book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
                bookRepository.save(book.get());
                return cart.get();
            }
            else {
                throw new CartException("Requested quantity is not available");
            }
        }
    }

    @Override
    public List<Cart> deleteAllFromCart() {

        cartRepository.deleteAll();
        return cartRepository.findAll();
    }

    @Override
    public Cart decreaseQuantity(Integer cartID) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        Optional<Book>  book = bookRepository.findById(cart.get().getBook().getBookID());
        if(cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        }
        else {
            if(cart.get().getQuantity() < book.get().getQuantity()) {
                cart.get().setQuantity(cart.get().getQuantity()-1);
                cartRepository.save(cart.get());
                log.info("Quantity in cart record updated successfully");
                book.get().setQuantity(book.get().getQuantity() - ((cart.get().getQuantity()-1) - cart.get().getQuantity()));
                bookRepository.save(book.get());
                return cart.get();
            }
            else {
                throw new CartException("Requested quantity is not available");
            }
        }
    }

    @Override
    public Cart increaseQuantity(Integer cartID) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        Optional<Book>  book = bookRepository.findById(cart.get().getBook().getBookID());
        if(cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        }
        else {
            if(cart.get().getQuantity() < book.get().getQuantity()) {
                cart.get().setQuantity(cart.get().getQuantity()+1);
                cartRepository.save(cart.get());
                log.info("Quantity in cart record updated successfully");
                book.get().setQuantity(book.get().getQuantity() - ((cart.get().getQuantity()+1) - cart.get().getQuantity()));
                bookRepository.save(book.get());
                return cart.get();
            }
            else {
                throw new CartException("Requested quantity is not available");
            }
        }
    }
    }
