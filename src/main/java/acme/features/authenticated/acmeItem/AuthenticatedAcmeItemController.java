
package acme.features.authenticated.acmeItem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.AcmeItem;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/acme-item/")
public class AuthenticatedAcmeItemController extends AbstractController<Authenticated, AcmeItem> {

	@Autowired
	private AuthenticatedAcmeItemListService	listService;

	@Autowired
	private AuthenticatedAcmeItemShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
