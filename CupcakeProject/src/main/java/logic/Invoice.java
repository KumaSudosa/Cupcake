/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import persistence.mappers.InvoiceMapperInterface;

/**
 *
 * @author Michael N. Korsgaard
 */
public class Invoice {

    private static ArrayList<Invoice> invoices;
    private User user;
    private ShoppingCart shoppingCart;
    private String date;
    private InvoiceMapperInterface invoiceMapper;

    public Invoice(User user, ShoppingCart shoppingCart, String date) {
        this.user = user;
        this.shoppingCart = shoppingCart;
        this.date = date;
    }

    public void setInvoiceMapper(InvoiceMapperInterface invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }

    public static ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public User getUser() {
        return user;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

}
