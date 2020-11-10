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
@Table(name = "toner")
public class Toner extends AbstractNameEntity {
    private static final long serialVersionUID = 2623515376223772169L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toner")
    private Set<Cartridge> cartridges;

    public Toner() {
    }

    public Toner(String name) {
        super(name);
    }

    public Toner(Integer id, String name) {
        super(id, name);
    }
}
