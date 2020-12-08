
package acme.features.supplier.specificationSheet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.SpecificationSheet;
import acme.entities.roles.Supplier;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/supplier/specification-sheet/")
public class SupplierSpecificationSheetController extends AbstractController<Supplier, SpecificationSheet> {

	@Autowired
	private SupplierSpecificationSheetListService	listService;

	@Autowired
	private SupplierSpecificationSheetShowService	showService;

	@Autowired
	private SupplierSpecificationSheetCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
