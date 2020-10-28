package ua.notky.cartridge.consumables.model;

import ua.notky.cartridge.consumables.model.parts.CleaningBlade;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;
import ua.notky.cartridge.consumables.model.parts.Drum;

import javax.persistence.*;

@Entity
@Table(name = "cartridge")
public class Cartridge extends AbstractNameEntity {
    private static final long serialVersionUID = 4466775734839249457L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_drum", nullable = false)
    private Drum drum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_cleaning_blade", nullable = false)
    private CleaningBlade cleaningBlade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_dispensing_blade", nullable = false)
    private DispensingBlade dispensingBlade;


    public Cartridge() {
    }

    public Cartridge(String name) {
        super(name);
    }

    public Cartridge(Integer id, String name) {
        super(id, name);
    }

    public Drum getDrum() {
        return drum;
    }

    public void setDrum(Drum drum) {
        this.drum = drum;
    }

    public void setCleaningBlade(CleaningBlade cleaningBlade) {
        this.cleaningBlade = cleaningBlade;
    }

    public CleaningBlade getCleaningBlade() {
        return cleaningBlade;
    }

    public DispensingBlade getDispensingBlade() {
        return dispensingBlade;
    }

    public void setDispensingBlade(DispensingBlade dispensingBlade) {
        this.dispensingBlade = dispensingBlade;
    }
}
