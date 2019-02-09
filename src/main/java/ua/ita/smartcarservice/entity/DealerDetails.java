package ua.ita.smartcarservice.entity;

import javax.persistence.*;


@Entity
@Table(name = "DealerDetails")
public class DealerDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = false)
    private String address;

    @Column(length = 100, nullable = false, unique = true)
    private String edr;

    @Column(length = 100, nullable = false, unique = true)
    private Long idUser;





}
