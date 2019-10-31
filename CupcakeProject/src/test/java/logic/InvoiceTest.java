package logic;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import persistence.FakeCupcakeMapper;
import persistence.FakeInvoiceMapper;
import persistence.FakeUserMapper;
import persistence.mappers.CupcakeMapperInterface;
import persistence.mappers.InvoiceMapperInterface;

public class InvoiceTest {

    InvoiceMapperInterface invoiceMapper;

    @Before
    public void setup() {
        Invoice.emptyInvoiceList();

        //Make Mock Mapper for Cupcake Toppings and Bottoms
        FakeCupcakeMapper fakeCupcakeMapper = new FakeCupcakeMapper();
        HashMap<String, String> mapCupcake = new HashMap();

        String[][] bottoms = {
            {"1", "Chocolate", "5"},
            {"2", "Vanilla", "5"},
            {"3", "Nutmeg", "5"},
            {"4", "Pistacio", "6"},
            {"5", "Almond", "7"}
        };

        for (String[] bottom : bottoms) {
            mapCupcake = new HashMap();
            mapCupcake.put("id", bottom[0]);
            mapCupcake.put("bottom", bottom[1]);
            mapCupcake.put("price", bottom[2]);
            fakeCupcakeMapper.addBottomInfo(mapCupcake);
        }

        String[][] toppings = {
            {"1", "Chocolate", "5"},
            {"2", "Blueberry", "5"},
            {"3", "Rasberry", "5"},
            {"4", "Crispy", "6"},
            {"5", "Strawberry", "6"},
            {"6", "Rum/Raisin", "7"},
            {"7", "Orange", "8"},
            {"8", "Lemon", "8"},
            {"9", "Blue cheese", "9"}
        };

        for (String[] topping : toppings) {
            mapCupcake = new HashMap();
            mapCupcake.put("id", topping[0]);
            mapCupcake.put("topping", topping[1]);
            mapCupcake.put("price", topping[2]);
            fakeCupcakeMapper.addToppingInfo(mapCupcake);
        }

        CupcakeMapperInterface cupcakeMapper = fakeCupcakeMapper;
        CupcakeTopping.setupMapper(cupcakeMapper);
        CupcakeBottom.setupMapper(cupcakeMapper);

        //Make Mock Mapper for Users
        FakeUserMapper fakeUserMapper = new FakeUserMapper();
        User.getUserList().clear();
        String[][] users = {
            {"cahit", "and51Ae", "cph@gmail.com", "50", "c"},
            {"marcus", "Tester1", "testing@email.dk", "100", "c"}
        };
        for (String[] user : users) {
            HashMap<String, String> mapUser = new HashMap();
            mapUser.put("username", user[0]);
            mapUser.put("login", user[1]);
            mapUser.put("email", user[2]);
            mapUser.put("balance", user[3]);
            mapUser.put("role", user[4]);
            fakeUserMapper.addUserInfo(mapUser);
        }
        FakeUserMapper userMapper = fakeUserMapper;
        User.setupMapper(userMapper);

        //Make Mock Mapper for Invoices
        FakeInvoiceMapper fakeInvoiceMapper = new FakeInvoiceMapper();
        String[][] invoices = {
            {"100001", "cph@gmail.com", "29-10-2019", "5", "1", "5"},
            {"100002", "testing@email.dk", "30-10-2019", "3", "2", "1"},
            {"100002", "testing@email.dk", "30-10-2019", "1", "4", "3"}
        };
        for (String[] invoice : invoices) {
            HashMap<String, String> mapInvoice = new HashMap();
            mapInvoice.put("id_invoice", invoice[0]);
            mapInvoice.put("email", invoice[1]);
            mapInvoice.put("date", invoice[2]);
            mapInvoice.put("id_topping", invoice[3]);
            mapInvoice.put("id_bottom", invoice[4]);
            mapInvoice.put("amount", invoice[5]);
            fakeInvoiceMapper.addInvoiceToFakeMapper(mapInvoice);
        }
        invoiceMapper = fakeInvoiceMapper;
        Invoice.setupInvoiceMapper(invoiceMapper);
    }

    @Test
    public void testCreateCustomerInvoicesFromDB() {
        //arrange
        String email = "cph@gmail.com";

        //act
        ArrayList<Invoice> result = Invoice.createCustomerInvoicesFromDB(email);

        //assert
        // int expectedSize = invoiceMapper.getInvoicesForCustomer(email).size(); //THIS DOES NOT WORK WITH MULTIPLE LINEITEMS IN ONE INVOICE
        int expectedSize = 1;
        int expectedID_Invoice = 100001;
        String expectedDate = "29-10-2019";
        int expectedID_Topping = 5;
        int expectedID_bottom = 1;
        int expectedAmount = 5;
        User expectedUserForInvoice = User.getUserFromUserList(email);

        assertEquals(expectedSize, result.size());
        assertEquals(expectedID_Invoice, result.get(0).getInvoiceID(), 0);
        assertTrue(expectedDate.equals(result.get(0).getDate()));
        assertEquals(expectedID_Topping, result.get(0).getLineItems().get(0).getCupcakeTopping().getCupcakeToppingID(), 0);
        assertEquals(expectedID_bottom, result.get(0).getLineItems().get(0).getCupcakeBottom().getCupcakeBottomID(), 0);
        assertEquals(expectedAmount, result.get(0).getLineItems().get(0).getAmount(), 0);
        assertEquals(expectedUserForInvoice, result.get(0).getUser());
    }
    
    @Test
    public void testCreateAdminInvoicesFromDB() {
        //act
        ArrayList<Invoice> result = Invoice.getInvoices();  //right now, this calls the method createAdminInvoicesFromDB() and gets the list

        //assert
        int expectedSize = 2;
        assertEquals(expectedSize, result.size());
    }
    
    @Test
    public void testConvertShoppingCartToNewInvoiceFromUser() {
        //arrange
        Customer customer = new Customer("Michael", "Tester2", "Testing2@Email.dk", "c", 100);
        customer.setShoppingCart(new ShoppingCart());
        customer.getShoppingCart().addLineItemsToShoppingCart(1, 2, 3);
        
        //act
        Invoice result = Invoice.convertShoppingCartToNewInvoiceFromUser(customer);

        //assert
        int expectedSize = 2;
        double expectedNewBalance = 70;
        assertEquals(expectedNewBalance, customer.getBalance(), 0);
    }

    /*
    Make tests for:
    -convertShoppingCartToNewInvoiceFromUser
    -createCustomerInvoicesFromDB need: negative test, test on multiple lineitems in one invoice
    -createAdminInvoicesFromDB
    -getTotalPrice
    
     */
}
