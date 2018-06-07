package kz.edu.sdu.diploma.dto;


import lombok.Getter;

public enum GrantType {
    STATE_GRANT("SG [State Grant]"),
    UNKNOWN("");

    @Getter
    private String name;

    GrantType(String name) {
        this.name = name;
    }
}
