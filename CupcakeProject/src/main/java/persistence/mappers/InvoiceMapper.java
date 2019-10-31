package persistence.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Invoice;
import logic.LineItem;
import persistence.DB;

/**
 * @author Michael N. Korsgaard
 */
public class InvoiceMapper implements InvoiceMapperInterface {

    @Override
    public int getNewHighestInvoiceNumber() {
        int highestInvoiceNumber = 100001;
        String sql = "SELECT MAX(id_invoice) as id_invoice FROM invoice";
        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                highestInvoiceNumber = rs.getInt("id_invoice") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return highestInvoiceNumber;
    }

    @Override
    public ArrayList<HashMap<String, String>> getInvoicesForCustomer(String email) {
        ArrayList<HashMap<String, String>> invoice = new ArrayList();
        String sql = "SELECT invoice.id_invoice, invoice.email, invoice.order_date, lineItems.id_top, lineItems.id_bot, lineItems.amount "
                + "FROM invoice inner join lineItems ON invoice.id_invoice = lineItems.id_invoice WHERE invoice.email='" + email + "'";
        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("id_invoice", rs.getString("id_invoice"));
                map.put("date", rs.getString("order_date"));
                map.put("id_topping", rs.getString("id_top"));
                map.put("id_bottom", rs.getString("id_bot"));
                map.put("amount", rs.getString("amount"));
                invoice.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return invoice;
    }

    @Override
    public ArrayList<HashMap<String, String>> getInvoicesForAdmin() {
        ArrayList<HashMap<String, String>> invoice = new ArrayList();
        String sql = "SELECT invoice.id_invoice, invoice.email, invoice.order_date, lineItems.id_top, lineItems.id_bot, lineItems.amount "
                + "FROM invoice inner join lineItems ON invoice.id_invoice = lineItems.id_invoice";
        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("id_invoice", rs.getString("id_invoice"));
                map.put("email", rs.getString("email"));
                map.put("date", rs.getString("order_date"));
                map.put("id_topping", rs.getString("id_top"));
                map.put("id_bottom", rs.getString("id_bot"));
                map.put("amount", rs.getString("amount"));
                invoice.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return invoice;
    }

    @Override
    public void uploadInvoice(Invoice invoice) {
        // add the invoice
        String sql = "INSERT INTO invoice (id_invoice, email, order_date) VALUES (" + invoice.getInvoiceID() + ", '"
                + invoice.getUser().getEmail() + "', '" + invoice.getDate() + "')";
        try {
            DB.getConnection().prepareStatement(sql).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        // add the lineItems
        for (LineItem lineItem : invoice.getLineItems()) {
            sql = "INSERT INTO lineItems (id_invoice, id_top, id_bot, amount) VALUES ("
                    + invoice.getInvoiceID() + ", " + lineItem.getCupcakeTopping().getCupcakeToppingID() + ", "
                    + lineItem.getCupcakeBottom().getCupcakeBottomID() + ", " + lineItem.getAmount() + ")";
            try {
                DB.getConnection().prepareStatement(sql).executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
