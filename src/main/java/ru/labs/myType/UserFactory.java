package ru.labs.myType;

import ru.labs.interfaces.UserType;

import java.util.ArrayList;

public class UserFactory {
    private ArrayList<UserType> userTypes;

    public UserFactory(ArrayList<UserType> userTypes) {
        this.userTypes = userTypes != null ? new ArrayList<>(userTypes) : new ArrayList<>();
    }

    public ArrayList<String> getTypeNameList() {
        ArrayList<String> typeNameList = new ArrayList<>();
        for (UserType userType : userTypes) {
            typeNameList.add(userType.typeName());
        }
        return typeNameList;
    }

    public UserType getBuilderByName(String name) {
        for (UserType userType : userTypes) {
            if (userType.typeName().equals(name)) {
                return (UserType) userType.clone();
            }
        }
        return null; // или бросьте исключение, если тип данных не найден
    }
}