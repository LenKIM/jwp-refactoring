package kitchenpos.dto.menu;

import kitchenpos.domain.menugroup.MenuGroup;

public class MenuGroupResponse {

    private final Long id;
    private final String name;

    private MenuGroupResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MenuGroupResponse of(MenuGroup menuGroup) {
        return new MenuGroupResponse(menuGroup.getId(), menuGroup.getName().getValue());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}