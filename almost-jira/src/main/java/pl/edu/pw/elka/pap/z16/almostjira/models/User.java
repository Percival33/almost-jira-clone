package pl.edu.pw.elka.pap.z16.almostjira.models;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Builder
@Document(collection = "users")
public record User(
        @Id
        String id,
        @CreatedDate Date createdAt,
        @LastModifiedDate
        Date lastModified,

        String firstName,
        String lastName,
        String password,
        List<Project> projects
) { }