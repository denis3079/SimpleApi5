package ru.mtuci.simpleapi1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.MappedSuperclass;

@Access(AccessType.FIELD)
// https://thorben-janssen.com/access-strategies-in-jpa-and-hibernate/
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractBaseEntity {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Long id;
    //public AbstractBaseEntity(Long id) { this.id = id; }
}
