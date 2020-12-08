
package acme.features.authenticated.specificationSheet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.SpecificationSheet;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/specification-sheet/")
public class AuthenticatedSpecificationSheetController extends AbstractController<Authenticated, SpecificationSheet> {

	@Autowired
	private AuthenticatedSpecificationSheetListService	listService;

	@Autowired
	private AuthenticatedSpecificationSheetShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
