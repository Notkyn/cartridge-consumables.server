package ua.notky.cartridge.consumables.model.parts;

import lombok.Getter;
import lombok.Setter;
import ua.notky.cartridge.consumables.model.AbstractNameEntity;
import ua.notky.cartridge.consumables.model.Cartridge;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "magnetic_shaft")
public class MagneticShaft extends AbstractNameEntity {
    private static final long serialVersionUID = -1248491681459750775L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "magneticShaft")
    private Set<Cartridge> cartridges;

    public MagneticShaft() {
    }

    public MagneticShaft(String name) {
        super(name);
    }

    public MagneticShaft(Integer id, String name) {
        super(id, name);
    }
}
