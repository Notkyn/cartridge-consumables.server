package ua.notky.cartridge.consumables.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "refill_cartridge")
public class RefillCartridge extends AbstractBaseEntity {
    private static final long serialVersionUID = 2387474522123375775L;

    @Getter
    @Setter
    @NotNull
    @Column(name = "date", nullable = false, unique = true)
    private LocalDate date;

    @Getter
    @Setter
    @Column(name ="drum_use")
    private boolean useDrum;

    @Getter
    @Setter
    @Column(name ="magnetic_shaft_use")
    private boolean useMagneticShaft;

    @Getter
    @Setter
    @Column(name ="primary_charge_shaft_use")
    private boolean usePrimaryChargeShaft;

    @Getter
    @Setter
    @Column(name ="cleaning_blade__use")
    private boolean useCleaningBlade;

    @Getter
    @Setter
    @Column(name ="dispensing_blade_use")
    private boolean useDispensingBlade;

    @Getter
    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_department", nullable = false)
    private Department department;

    public RefillCartridge() {
    }

    public RefillCartridge(LocalDate date) {
        this.date = date;
    }

    public RefillCartridge(Integer id, LocalDate date) {
        this(id, date, false, false, false, false, false);
    }

    public RefillCartridge(LocalDate date,
                           boolean useDrum,
                           boolean useMagneticShaft,
                           boolean usePrimaryChargeShaft,
                           boolean useCleaningBlade,
                           boolean useDispensingBlade) {
        this.date = date;
        this.useDrum = useDrum;
        this.useMagneticShaft = useMagneticShaft;
        this.usePrimaryChargeShaft = usePrimaryChargeShaft;
        this.useCleaningBlade = useCleaningBlade;
        this.useDispensingBlade = useDispensingBlade;
    }

    public RefillCartridge(Integer id,
                           LocalDate date,
                           boolean useDrum,
                           boolean useMagneticShaft,
                           boolean usePrimaryChargeShaft,
                           boolean useCleaningBlade,
                           boolean useDispensingBlade) {
        super(id);
        this.date = date;
        this.useDrum = useDrum;
        this.useMagneticShaft = useMagneticShaft;
        this.usePrimaryChargeShaft = usePrimaryChargeShaft;
        this.useCleaningBlade = useCleaningBlade;
        this.useDispensingBlade = useDispensingBlade;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result
                + date.hashCode()
                + Objects.hash(useDrum, useMagneticShaft, usePrimaryChargeShaft, useCleaningBlade, useDispensingBlade);
        return result;
    }

    public String toString() {
        return String.format("%s, %td-%tB-%tY" +
                "\n\tparts{" +
                "\n\t\tdrum: %b," +
                "\n\t\tmagnetic shaft: %b," +
                "\n\t\tprimary charge shaft: %b," +
                "\n\t\tcleaning blade: %b," +
                "\n\t\tdispensing blade: %b}",
                super.toString(), date, date, date,
                useDrum, useMagneticShaft, usePrimaryChargeShaft, useCleaningBlade, useDispensingBlade);
    }
}
