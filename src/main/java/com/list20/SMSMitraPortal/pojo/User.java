package com.list20.SMSMitraPortal.pojo;


import com.list20.SMSMitraPortal.request.UserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(
        name = "usr"
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long id;
    @Column(
            unique = true
    )
    private String name;
    private String email;
    private String password;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "bankCode",
            nullable = false
    )
    private BankMaster bankMaster;
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "branchCode",
            nullable = false
    )
    private BranchMaster branchMaster;

    public User(String name, String email, BankMaster bankMaster, BranchMaster branchMaster) {
        this.name = name;
        this.email = email;
        this.bankMaster = bankMaster;
        this.branchMaster = branchMaster;
    }

    public static User fromRequest(UserRequest userRequest, BankMaster bankMaster, BranchMaster branchMaster) {
        return new User(userRequest.getUserName(), userRequest.getEmail(), bankMaster, branchMaster);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet();
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.name;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public BankMaster getBankMaster() {
        return this.bankMaster;
    }

    public BranchMaster getBranchMaster() {
        return this.branchMaster;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setBankMaster(final BankMaster bankMaster) {
        this.bankMaster = bankMaster;
    }

    public void setBranchMaster(final BranchMaster branchMaster) {
        this.branchMaster = branchMaster;
    }

    public User() {
    }
}

