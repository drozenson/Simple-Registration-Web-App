package com.architech.regstration;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    StringBuilder csvSkills = new StringBuilder();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    final String ERROR_PAGE = "error.jsp";
    final String CONFIRM_PAGE = "confirm.jsp";

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hdnParam = request.getParameter("pagename");
       
        if (hdnParam.equals("new")) {
            response.sendRedirect(ERROR_PAGE);
        }
        if (hdnParam.equals("confirm")) {
            try {
                DbManager.close();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RegServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (hdnParam.equals("register")) {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            GetsSets sets = new GetsSets();

            sets.setUsername(username);
            sets.setPassword(password);

            ArrayList<String> messages = validateSets(sets);
            if (!messages.isEmpty()) {

                String errorPage = ERROR_PAGE + "?username=" + URLEncoder.encode(username, "UTF-8") + "&";

                int i = 0;
                for (String message : messages) {
                    if (message != null) {
                        if (i != 0) {
                            errorPage = errorPage + "&";
                        }
                        errorPage = errorPage + "message" + i + "=" + message;
                        i++;
                    }
                }

                response.sendRedirect(errorPage);;

            } else {
                try {
                    if (DbManager.checkUser(sets) == 0) {
                        DbManager.Insert(sets);
                        response.sendRedirect(CONFIRM_PAGE + "?username=" + URLEncoder.encode(username, "UTF-8") + "&message0=" + username + " has been registered");

                    } else {
                        String errorPage = ERROR_PAGE + "?username=" + URLEncoder.encode(username, "UTF-8") + "&message0= username already exists";
                        response.sendRedirect(errorPage);

                    }
                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println(e.toString());
                }

            }
        }

    }

    public ArrayList<String> validateSets(GetsSets sets) {
        ArrayList<String> message = new ArrayList<>();

        String username = sets.getUsername();
        String password = sets.getPassword();
        final int USERNAME_LENGTH = 5;
        final int PASSWORD_LENGTH = 8;

        if (!username.matches("[A-Za-z0-9]+")) {
            message.add("username  must contain only numbers and letters");
        }

        if (username.length() < USERNAME_LENGTH) {
            message.add("username  must be " + USERNAME_LENGTH + " charcaters or greater");

        }

        if (!Pattern.compile(".*[A-Z].").matcher(password).find()) {
            message.add("password  must contain at least one uppercase letter");

        }

        if (!Pattern.compile(".*[a-z].*").matcher(password).find()) {
            message.add("password  must contain at least one lowercase letter");

        }

        if (!Pattern.compile(".*[0-9].*").matcher(password).find()) {
            message.add("password  must contain at least one number");

        }

        if (password.length() < PASSWORD_LENGTH) {
            message.add("password  must be " + PASSWORD_LENGTH + " charcaters or greater");

        }

        return message;

    }

}
