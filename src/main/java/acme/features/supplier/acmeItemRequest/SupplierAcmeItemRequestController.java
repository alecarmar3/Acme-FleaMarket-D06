
package acme.features.supplier.acmeItemRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.AcmeItemRequest;
import acme.entities.roles.Supplier;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/supplier/acme-item-request/")
public class SupplierAcmeItemRequestController extends AbstractController<Supplier, AcmeItemRequest> {

	@Autowired
	private SupplierAcmeItemRequestListToMineService	listToMineService;

	@Autowired
	private SupplierAcmeItemRequestShowService			showService;

	@Autowired
	private SupplierAcmeItemRequestUpdateAcceptService	updateAcceptService;

	@Autowired
	private SupplierAcmeItemRequestUpdateRejectService	updateRejectService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_TO_MINE, BasicCommand.LIST, this.listToMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.UPDATE_ACCEPT, BasicCommand.UPDATE, this.updateAcceptService);
		super.addCustomCommand(CustomCommand.UPDATE_REJECT, BasicCommand.UPDATE, this.updateRejectService);
	}
}
