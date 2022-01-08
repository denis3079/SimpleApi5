package ru.mtuci.simpleapi1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Drivers extends AbstractBaseEntity {
    @NotBlank
    private String last_name;
    @NotBlank
    private String first_name;
    @NotBlank
    private String mid_name;
    @NotBlank
    private String phone;
    @NotNull
    private Integer rating;
}
