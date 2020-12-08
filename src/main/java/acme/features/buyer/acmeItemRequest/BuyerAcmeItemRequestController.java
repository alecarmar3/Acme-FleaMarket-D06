
package acme.features.buyer.acmeItemRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.AcmeItemRequest;
import acme.entities.roles.Buyer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/buyer/acme-item-request/")
public class BuyerAcmeItemRequestController extends AbstractController<Buyer, AcmeItemRequest> {

	@Autowired
	private BuyerAcmeItemRequestListMineService	listMineService;

	@Autowired
	private BuyerAcmeItemRequestShowService		showService;

	@Autowired
	private BuyerAcmeItemRequestCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
