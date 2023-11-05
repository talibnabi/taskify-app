package org.taskifyapp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.taskifyapp.model.enums.UserRole;
import org.taskifyapp.model.interfaces.UserManager;


import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_taskify")
public class User implements UserManager, UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "code_for_mail_sending")
    private String codeForMailSending;

    @ManyToMany
    @JoinTable(
            name = "task_assigned",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> userTasks;

    @OneToOne
    private Organization organization;

    @Override
    public Integer getUserRoleWithId() {
        return this.userRole.getId();
    }

    @Override
    public void setUserRoleWithId(Integer userRoleId) {
        this.userRole = UserRole.fromId(userRoleId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && userRole == user.userRole && Objects.equals(codeForMailSending, user.codeForMailSending) && Objects.equals(userTasks, user.userTasks) && Objects.equals(organization, user.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, userRole, codeForMailSending, userTasks, organization);
    }
}
