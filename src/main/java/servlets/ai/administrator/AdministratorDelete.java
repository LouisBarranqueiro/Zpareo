package servlets.ai.administrator;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.AdministratorDao;
import forms.AdministratorForm;

@SuppressWarnings("serial")
@WebServlet("/ai/administrator/delete")
public class AdministratorDelete extends HttpServlet {
    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String ADMINISTRATOR    = "administrator";
    private static final String VIEW             = "/WEB-INF/ai/administrator/delete.xhtml";
    private String           path;
    private AdministratorDao administratorDao;

    public AdministratorDelete() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.path = config.getServletContext().getContextPath();
        this.administratorDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getAdministratorDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdministratorForm   administratorForm = new AdministratorForm(this.administratorDao);
        beans.Administrator administrator     = administratorForm.get(request);

        request.setAttribute(ADMINISTRATOR, administrator);
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdministratorForm administratorForm = new AdministratorForm(this.administratorDao);

        administratorForm.delete(request);
        response.sendRedirect(this.path + "/ai/administrator");
    }

}
