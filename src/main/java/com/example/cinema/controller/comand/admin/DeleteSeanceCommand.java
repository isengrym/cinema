package com.example.cinema.controller.comand.admin;

import com.example.cinema.controller.AccessStatus;
import com.example.cinema.controller.ConfigurationManager;
import com.example.cinema.controller.comand.ActionCommand;
import com.example.cinema.controller.comand.common.GenerateMoviesPage;
import com.example.cinema.controller.comand.common.GenerateSeancesPage;
import com.example.cinema.model.entity.Role;
import com.example.cinema.model.entity.User;
import com.example.cinema.service.MovieSeanceService;

import javax.servlet.http.HttpServletRequest;

/**
 * The command that is responsible for deleting seances
 *
 */
public class DeleteSeanceCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        User user = (User)req.getSession().getAttribute("user");
        if (!AccessStatus.isAdmin(user)) {
            page = ConfigurationManager.getProperty("path.page.index");
            return page;
        }
        MovieSeanceService service = MovieSeanceService.getInstance();

        service.deleteSeance(Integer.parseInt(req.getParameter("seanceId")));

        GenerateSeancesPage command = new GenerateSeancesPage();
        page = command.execute(req);
        return page;
    }
}
