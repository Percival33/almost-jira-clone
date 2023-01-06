package pl.edu.pw.elka.pap.z16.almostjira.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record ProjectForm(
    @Getter
    String overseer_id,
    @Getter
    String project_name,
    @Getter
    List<String> tasks
    ) { }