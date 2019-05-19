package com.nfjokes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Joke extends BaseEntity {
    @ManyToOne
    private User user;
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotNull
    private Date date;
    @NotNull
    private Double rate;
}
