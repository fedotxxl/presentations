package io.belov.presentations.jooq.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Created on 29.06.17.
 */
@Getter
@AllArgsConstructor
@Builder
public class User {
    private String mail;
    private String password;
    private boolean isAdmin;
}
