package logic;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import persistence.FakeInvoiceMapper;
import persistence.FakeUserMapper;
import persistence.mappers.InvoiceMapperInterface;

public class InvoiceTest {

    InvoiceMapperInterface invoiceMapper = null;

    @Before
    public void setup() {

        FakeInvoiceMapper fakeMapper = new FakeInvoiceMapper();
        Invoice.emptyInvoiceList();
        String[][] invoices = {
            {"100001", "29-10-2019", "10", "1", "5"},};
        for (String[] invoice : invoices) {
            HashMap<String, String> map = new HashMap();
            map.put("id_invoice", invoice[0]);
            map.put("date", invoice[1]);
            map.put("id_topping", invoice[2]);
            map.put("id_bottom", invoice[3]);
            map.put("amount", invoice[4]);
            fakeMapper.addCustomerInfo(map);
        }
        invoiceMapper = fakeMapper;
        Invoice.setupInvoiceMapper(invoiceMapper);
    }
    
    @Ignore
    @Test
    public void createCustomerInvoicesFromDB() {
        //arrange
        String email = "";

        //act
        ArrayList<Invoice> result = Invoice.createCustomerInvoicesFromDB(email);

        //assert
        int expectedSize = invoiceMapper.getInvoicesForCustomer(email).size();
        int expectedID_Invoice = 100001;
        String expectedDate = "29-10-2019";
        int expectedID_Topping = 10;
        int expectedID_bottom = 1;
        int expectedAmount = 5;

        assertEquals(expectedSize, result.size());
        assertEquals(expectedID_Invoice, result.get(0).getInvoiceID(), 0);
        assertTrue(expectedDate.equals(result.get(0).getDate()));

        /* assertEquals(expectedID_Topping, result.getLineItems(), 0);
        assertEquals(expectedID_bottom, result.getLineItems(), 0);
        assertEquals(expectedAmount, result.getLineItems(), 0); */
    }
}
