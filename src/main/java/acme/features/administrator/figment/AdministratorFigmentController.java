
package acme.features.administrator.figment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Figment;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/figment/")
public class AdministratorFigmentController extends AbstractController<Administrator, Figment> {

	@Autowired
	private AdministratorFigmentListService		listService;

	@Autowired
	private AdministratorFigmentShowService		showService;

	@Autowired
	private AdministratorFigmentCreateService	createService;

	@Autowired
	private AdministratorFigmentUpdateService	updateService;

	@Autowired
	private AdministratorFigmentDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
