
package acme.features.administrator.suggestion;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Suggestion;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/suggestion/")
public class AdministratorSuggestionController extends AbstractController<Administrator, Suggestion> {

	@Autowired
	private AdministratorSuggestionListService		listService;

	@Autowired
	private AdministratorSuggestionShowService		showService;

	@Autowired
	private AdministratorSuggestionCreateService	createService;

	@Autowired
	private AdministratorSuggestionUpdateService	updateService;

	@Autowired
	private AdministratorSuggestionDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
