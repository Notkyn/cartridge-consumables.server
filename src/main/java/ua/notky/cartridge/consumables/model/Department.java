package ua.notky.cartridge.consumables.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department extends AbstractNameEntity {
    private static final long serialVersionUID = -8823196802859515157L;

    @Getter
    @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id_cartridge", nullable = false)
    private Cartridge cartridge;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "departments")
    private List<WorkingDay> workingDays = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        super(name);
    }

    public Department(Integer id, String name) {
        super(id, name);
    }
}
