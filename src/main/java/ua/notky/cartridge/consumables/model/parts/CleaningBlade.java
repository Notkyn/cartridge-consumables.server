package ua.notky.cartridge.consumables.model.parts;

import lombok.Getter;
import lombok.Setter;
import ua.notky.cartridge.consumables.model.AbstractNameEntity;
import ua.notky.cartridge.consumables.model.Cartridge;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
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
}
