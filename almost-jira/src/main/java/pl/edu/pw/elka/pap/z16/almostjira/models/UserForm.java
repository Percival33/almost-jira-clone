package pl.edu.pw.elka.pap.z16.almostjira.models;

import lombok.Builder;

@Builder
public record UserForm(
    String firstName,
    String lastName,
    String password
) { }