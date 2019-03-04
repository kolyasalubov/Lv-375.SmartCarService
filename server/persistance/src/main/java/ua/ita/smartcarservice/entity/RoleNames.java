package ua.ita.smartcarservice.entity;

public enum RoleNames {
    ROLE_CAR_OWNER("ROLE_CAR_OWNER"),
    ROLE_SALES_MANAGER("ROLE_SALES_MANAGER"),
    ROLE_TECHNICAL_MANAGER("ROLE_TECHNICAL_MANAGER"),
    ROLE_DEALER("ROLE_DIELER"),
    ROLE_WORKER("ROLE_WORKER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    RoleNames(String name) {
        this.name = name;
    }
}
