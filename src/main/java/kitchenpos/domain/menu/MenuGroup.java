package kitchenpos.domain.menu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // for jpa
    public MenuGroup() {
    }

    private MenuGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MenuGroup of(String name) {
        return new MenuGroup(null, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
