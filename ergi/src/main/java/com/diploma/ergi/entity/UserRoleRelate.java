package com.diploma.ergi.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Data
@Table(name = "user_role_relate")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRoleRelate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties(value = {"users"})
    private Roles role;

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
