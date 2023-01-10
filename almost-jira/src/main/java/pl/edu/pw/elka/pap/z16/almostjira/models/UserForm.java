package pl.edu.pw.elka.pap.z16.almostjira.models;

import lombok.Builder;
import lombok.Getter;

@Builder
public record UserForm(
    @Getter
    String firstName,
    @Getter
    String lastName,
    @Getter
    String login,
    @Getter
    String password
) { }