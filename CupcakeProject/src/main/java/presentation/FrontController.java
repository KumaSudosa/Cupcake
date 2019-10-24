package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CupcakeBottom;
import logic.CupcakeTopping;
import logic.User;
import persistence.mappers.CupcakeMapper;
import persistence.mappers.ICupcakeMapper;
import persistence.mappers.IUserMapper;
import persistence.mappers.UserMapper;

 /*
 @author Gruppe 3
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {
    
    private static boolean needSetup = true;
    
    public static void setup(){
        IUserMapper userMapper = new UserMapper();
        User.setupMapper(userMapper);
        
        ICupcakeMapper cupcakeMapper = new CupcakeMapper();
        CupcakeBottom.setupMapper(cupcakeMapper);
        CupcakeTopping.setupMapper(cupcakeMapper);
        
        needSetup = false;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(needSetup){
            setup();
        }
        try {
            Command cmd = Command.from(request);
            String view = cmd.execute(request, response);
            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/errorpage.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}