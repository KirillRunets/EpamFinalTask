package by.runets.buber.presentation.command.impl.statItics;

import by.runets.buber.application.service.stats.StatisticsService;
import by.runets.buber.infrastructure.constant.JspPagePath;
import by.runets.buber.presentation.command.Command;
import by.runets.buber.presentation.controller.Router;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class PassengerTripJsonDataCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(PassengerTripJsonDataCommand.class);
    private StatisticsService statisticsService;

    public PassengerTripJsonDataCommand(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse res) {
        Router router = new Router();

        List<Integer> data = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
        String json = new Gson().toJson(data);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        req.setAttribute("JSON", json);

        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(JspPagePath.PASSENGER_HOME_PAGE);

        return router;
    }
}
