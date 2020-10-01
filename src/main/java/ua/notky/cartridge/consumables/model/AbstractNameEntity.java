package ua.notky.cartridge.consumables.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public abstract class AbstractNameEntity extends AbstractBaseEntity{
    private static final long serialVersionUID = 3677815587569656130L;

    @Getter
    @Setter
    private String name;

    public AbstractNameEntity() {
    }

    public AbstractNameEntity(String name) {
        this.name = name;
    }

    public AbstractNameEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbstractNameEntity that = (AbstractNameEntity) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", " + name;
    }
}
