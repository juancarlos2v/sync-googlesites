/**package com.tecba.adsitios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "nombre_cuenta")
    private String accountName;

    @Column(name = "estado")
    private String verificationState;

    @Column(name = "numero_cuenta")
    private  String accountNumber;

}**/
