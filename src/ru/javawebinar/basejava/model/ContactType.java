package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Телефон"),
    MOBILE_PHONE("Мобильный телефон"),
    HOME_PHONE("Домашний телефон"),
    SKYPE("Skype"),
    EMAIL("Электронная почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}