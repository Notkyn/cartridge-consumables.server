package ua.notky.cartridge.consumables.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import ua.notky.cartridge.consumables.model.AbstractBaseEntity;
import ua.notky.cartridge.consumables.util.exception.HasDependencyException;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.IllegalRequestDataException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ValidationUtil {
    public static <T extends AbstractBaseEntity> void checkNew(T entity) {
        log.info("Check Entity is New - id must be null: [id={}]", entity.getId());
        if (!entity.isNew()) {
            throw new IllegalEntityException(entity + " must be new (id=null)");
        }
    }

    public static <T extends AbstractBaseEntity> void checkUpdated(T entity) {
        log.info("Check Entity is Updated - must have id: [id={}]", entity.getId());
        if (entity.isNew()) {
            throw new IllegalEntityException(entity + " must have id");
        }
    }

    public static <T> void checkNotNull(T object){
        log.info("Check Not Null Object=[{}]", object);
        Assert.notNull(object, "object must be not null");
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        log.info("Check Not Found With Id - [found={}], [id={}]", found, id);
        checkNotFound(found, "id=" + id);
    }

    private static void checkNotFound(boolean found, String arg) {
        log.info("Check Not Found - [found={}], [msg={}]", found, arg);
        if (!found) {
            throw new NotFoundDataException(arg);
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        log.info("Check Not Found With Id - [object={}], [id={}]", object, id);
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFoundWithName(T object, String name) {
        log.info("Check Not Found With Name - [object={}], [name={}]", object, name);
        return checkNotFound(object, "name=" + name);
    }

    private static <T> T checkNotFound(T object, String msg) {
        log.info("Check Not Found - [object={}], [msg={}]", object, msg);
        checkNotFound(object != null, msg);
        return object;
    }

    public static <T extends AbstractBaseEntity,
            K extends AbstractBaseEntity> void checkDependencySet(T entity, Set<K> dependencies){
        log.info("Check Dependencies of the Entity - [entity={}]", entity);

        dependencies = dependencies.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        checkDependency(dependencies.size() > 0, "id=" + entity.getId());
    }

    public static <T extends AbstractBaseEntity,
            K extends AbstractBaseEntity> void checkDependencyList(T entity, List<K> dependencies){
        log.info("Check Dependencies of the Entity - [entity={}]", entity);

        dependencies = dependencies.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        checkDependency(dependencies.size() > 0, "id=" + entity.getId());
    }

    private static void checkDependency(boolean dependency, String msg){
        log.info("Check Dependency - [dependency={}], [msg={}]", dependency, msg);
        if(dependency){
            throw new HasDependencyException(msg);
        }
    }

    public static <T extends AbstractBaseEntity> void checkNewBean(T bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }
}
