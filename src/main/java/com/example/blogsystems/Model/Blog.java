package com.example.blogsystems.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "title should not be empty")
    @Size(min = 1,max = 20,message = "title should between 1 and 20 characters")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String title;
    @NotEmpty(message = "body should not be empty")
    @Size(min = 1,max = 500,message = "body should between 1 and 500 characters")
    @Column(columnDefinition = "VARCHAR(500) NOT NULL")
    private String body;

    @ManyToOne
    @JsonIgnore
    private User user;
}
