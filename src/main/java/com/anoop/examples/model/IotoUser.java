package com.anoop.examples.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IotoUser {

    private String userId;
    private String userName;
    private String clientId;
    private String companyId;
    private boolean companyUser;
    private final List<String> roles;
    private String manufacturerId;
}
