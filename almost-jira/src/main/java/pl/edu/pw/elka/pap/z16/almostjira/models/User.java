package pl.edu.pw.elka.pap.z16.almostjira.models;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "users")
public class User {
    @Id
    private final String id;
    @Getter
    private String username;
}