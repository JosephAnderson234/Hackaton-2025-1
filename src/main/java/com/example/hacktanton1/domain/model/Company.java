package com.example.hacktanton1.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String ruc;

    @Column(name = "affiliation_date", nullable = false)
    private LocalDate affiliationDate;

    @Column(nullable = false)
    private boolean active;

    //cada empresa tiene un administrador princiapl ( se lo toma como rol)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_admin_id", referencedColumnName = "id")
    private Usuario companyAdmin;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyRestriction> companyRestrictions;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Usuario> users;

}
