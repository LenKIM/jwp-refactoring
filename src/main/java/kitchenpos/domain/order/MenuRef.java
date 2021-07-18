package kitchenpos.domain.order;

import java.util.Objects;

public class MenuRef {

    long menuId;

    private MenuRef(long menuId) {
        this.menuId = menuId;
    }

    public static MenuRef of(long menuId) {
        return new MenuRef(menuId);
    }

    public long getMenuId() {
        return menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuRef menuRef = (MenuRef) o;
        return menuId == menuRef.menuId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId);
    }

    @Override
    public String toString() {
        return "MenuRef{" +
                "menuId=" + menuId +
                '}';
    }
}
