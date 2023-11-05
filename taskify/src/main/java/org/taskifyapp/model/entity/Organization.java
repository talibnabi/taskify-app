package org.taskifyapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organization_taskify")
public class Organization implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "organization_sequence", sequenceName = "organization_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_sequence")
    @Column(name = "organization_id")
    private Long id;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @OneToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) && Objects.equals(organizationName, that.organizationName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationName, phoneNumber, address);
    }
}
