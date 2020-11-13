package ua.notky.cartridge.consumables.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "working_day")
public class WorkingDay extends AbstractBaseEntity {
    private static final long serialVersionUID = 2387474522123375775L;

    @Getter
    @Setter
    @Column(name = "date", nullable = false, unique = true)
    private LocalDate date;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "working_day_department_megre",
            joinColumns = {@JoinColumn(name = "_id_working_day_megre", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "_id_department_megre", nullable = false)})
    private List<Department> departments;

    public WorkingDay() {
    }

    public WorkingDay(LocalDate date) {
        this.date = date;
    }

    public WorkingDay(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }


}
