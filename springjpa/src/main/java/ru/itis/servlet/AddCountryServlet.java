package ru.itis.servlet;

import org.springframework.context.ApplicationContext;
import ru.itis.models.Country;
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
public class AddCountryServlet extends HttpServlet {

    CountryService countryService;

    RequestDispatcher requestDispatcher;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = (ApplicationContext)config.getServletContext().getAttribute("springContext");
        countryService = context.getBean(CountryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("countries",countryService.findAll());

        requestDispatcher = getServletContext().getRequestDispatcher("/addCountry.jsp");

        requestDispatcher.forward(req,resp);



    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String countryName = req.getParameter("name");
        String presidentName = req.getParameter("president");
        String continent = req.getParameter("continent");
        Boolean isfederation = true;
        if (req.getParameter("isfederation").equals("confederation")){
            isfederation = false;
        }
        countryService.save(new Country(countryName,continent,presidentName,isfederation));
        resp.sendRedirect("/showCountries");

    }
}
