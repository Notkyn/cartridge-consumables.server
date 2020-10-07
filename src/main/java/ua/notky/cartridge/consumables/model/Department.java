package ua.notky.cartridge.consumables.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department extends AbstractNameEntity {
    private static final long serialVersionUID = -8823196802859515157L;
}
