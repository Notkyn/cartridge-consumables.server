package ua.notky.cartridge.consumables.model;

import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.notky.cartridge.consumables.model.parts.CleaningBlade;

import javax.persistence.*;

@Entity
@Table(name = "cartridge")
public class Cartridge extends AbstractNameEntity {
    private static final long serialVersionUID = 4466775734839249457L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_cleaning_blade", nullable = false)
    private CleaningBlade cleaningBlade;

    public Cartridge() {
    }

    public Cartridge(String name) {
        super(name);
    }

    public Cartridge(Integer id, String name) {
        super(id, name);
    }

    public void setCleaningBlade(CleaningBlade cleaningBlade) {
        this.cleaningBlade = cleaningBlade;
    }

    public CleaningBlade getCleaningBlade() {
        return cleaningBlade;
    }
}
