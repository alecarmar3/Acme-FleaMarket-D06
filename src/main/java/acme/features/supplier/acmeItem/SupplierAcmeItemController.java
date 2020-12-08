
package acme.features.supplier.acmeItem;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.AcmeItem;
import acme.entities.roles.Supplier;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/supplier/acme-item/")
public class SupplierAcmeItemController extends AbstractController<Supplier, AcmeItem> {

	@Autowired
	private SupplierAcmeItemListMineService			listMineService;

	@Autowired
	private SupplierAcmeItemShowService				showService;

	@Autowired
	private SupplierAcmeItemCreateService			createService;

	@Autowired
	private SupplierAcmeItemUpdateService			updateService;

	@Autowired
	private SupplierAcmeItemDeleteService			deleteService;

	@Autowired
	private SupplierAcmeItemUpdateFinalModeService	updateFinalModeService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addCustomCommand(CustomCommand.UPDATE_FINAL_MODE, BasicCommand.UPDATE, this.updateFinalModeService);
	}
}
