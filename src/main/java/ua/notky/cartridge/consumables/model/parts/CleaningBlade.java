package ua.notky.cartridge.consumables.model.parts;

import ua.notky.cartridge.consumables.model.AbstractNameEntity;
import ua.notky.cartridge.consumables.model.Cartridge;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cleaning_blade")
public class CleaningBlade extends AbstractNameEntity {
    private static final long serialVersionUID = -77875483920190370L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cleaningBlade")
    private Set<Cartridge> cartridges;

    public CleaningBlade() {
    }

    public CleaningBlade(String name) {
        super(name);
    }

    public CleaningBlade(Integer id, String name) {
        super(id, name);
    }

    public void setCartridges(Set<Cartridge> cartridges) {
        this.cartridges = cartridges;
    }

    public Set<Cartridge> getCartridges() {
        return cartridges;
    }
}
