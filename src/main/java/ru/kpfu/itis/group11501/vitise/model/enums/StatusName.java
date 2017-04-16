package ru.kpfu.itis.group11501.vitise.model.enums;

public enum StatusName {
    ADMIN("Администратор"),
    STUDENT("Студент"),
    TEACHER("Преподаватель"),
    DEANERY("Декант"),
    ASSISTANT("Лаборант"),
    RESEARCHER("Исследователь"),
    WORKER("Работник"),
    GROUP_HEAD("Староста"),
    CULTURAL_HEAD("Культорг");

    private final String nameOnRus;

    StatusName(String nameOnRus) {
        this.nameOnRus = nameOnRus;
    }

    public String getNameOnRus() {
        return nameOnRus;
    }
}
