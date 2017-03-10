package ru.itis.servlet;

import org.springframework.context.ApplicationContext;
import ru.itis.service.CountryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Иван on 09.03.2017.
 */
public class ShowCountriesServlet extends HttpServlet {
    //страница для показа всех кортежей таблицы Countries.

    private RequestDispatcher requestDispatcher;

    CountryService countryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = (ApplicationContext)config.getServletContext().getAttribute("springContext");
        countryService = context.getBean(CountryService.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        req.setAttribute("countries",countryService.findAll());

        requestDispatcher = getServletContext().getRequestDispatcher("/showCountries.jsp");

        requestDispatcher.forward(req, resp);

    }
}
