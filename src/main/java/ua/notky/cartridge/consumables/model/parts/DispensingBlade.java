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
@Table(name = "dispensing_blade")
public class DispensingBlade extends AbstractNameEntity {
    private static final long serialVersionUID = 3198359677083935885L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dispensingBlade")
    private Set<Cartridge> cartridges;

    public DispensingBlade() {
    }

    public DispensingBlade(String name) {
        super(name);
    }

    public DispensingBlade(Integer id, String name) {
        super(id, name);
    }
}
