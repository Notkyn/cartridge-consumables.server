package ua.notky.cartridge.consumables.model.parts;

import ua.notky.cartridge.consumables.model.AbstractNameEntity;
import ua.notky.cartridge.consumables.model.Cartridge;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "drum")
public class Drum extends AbstractNameEntity {
    private static final long serialVersionUID = 5718901225574994067L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "drum")
    private Set<Cartridge> cartridges;

    public Drum() {
    }

    public Drum(String name) {
        super(name);
    }

    public Drum(Integer id, String name) {
        super(id, name);
    }

    public Set<Cartridge> getCartridges() {
        return cartridges;
    }

    public void setCartridges(Set<Cartridge> cartridges) {
        this.cartridges = cartridges;
    }
}
