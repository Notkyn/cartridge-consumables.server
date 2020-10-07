package ua.notky.cartridge.consumables.model.parts;

import ua.notky.cartridge.consumables.model.AbstractNameEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "primery_charge_shaft")
public class PrimaryChargeShaft extends AbstractNameEntity {
    private static final long serialVersionUID = 5001293431140742098L;
}
