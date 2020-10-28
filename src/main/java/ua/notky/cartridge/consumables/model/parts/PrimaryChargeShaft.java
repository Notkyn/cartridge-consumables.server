package ua.notky.cartridge.consumables.model.parts;

import ua.notky.cartridge.consumables.model.AbstractNameEntity;
import ua.notky.cartridge.consumables.model.Cartridge;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "primary_charge_shaft")
public class PrimaryChargeShaft extends AbstractNameEntity {
    private static final long serialVersionUID = 5001293431140742098L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryChargeShaft")
    private Set<Cartridge> cartridges;

    public PrimaryChargeShaft() {
    }

    public PrimaryChargeShaft(String name) {
        super(name);
    }

    public PrimaryChargeShaft(Integer id, String name) {
        super(id, name);
    }

    public Set<Cartridge> getCartridges() {
        return cartridges;
    }

    public void setCartridges(Set<Cartridge> cartridges) {
        this.cartridges = cartridges;
    }
}
