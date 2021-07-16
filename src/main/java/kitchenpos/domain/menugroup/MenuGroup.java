package kitchenpos.domain.menugroup;

import org.springframework.data.annotation.Id;

public class MenuGroup {
    @Id
    private Long id;
    private String name;

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

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
