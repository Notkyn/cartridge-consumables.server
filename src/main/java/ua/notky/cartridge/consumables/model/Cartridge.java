package ua.notky.cartridge.consumables.model;

import lombok.Getter;
import lombok.Setter;
import ua.notky.cartridge.consumables.model.parts.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cartridge")
public class Cartridge extends AbstractNameEntity {
    private static final long serialVersionUID = 4466775734839249457L;

    @Column(name = "coef_toner", nullable = false)
    private int coefToner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_toner", nullable = false)
    private Toner toner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_drum", nullable = false)
    private Drum drum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_cleaning_blade", nullable = false)
    private CleaningBlade cleaningBlade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_dispensing_blade", nullable = false)
    private DispensingBlade dispensingBlade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_magnetic_shaft", nullable = false)
    private MagneticShaft magneticShaft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_primary_charge_shaft", nullable = false)
    private PrimaryChargeShaft primaryChargeShaft;


    public Cartridge() {
    }

    public Cartridge(String name) {
        super(name);
    }

    public Cartridge(Integer id, String name) {
        super(id, name);
    }
}
