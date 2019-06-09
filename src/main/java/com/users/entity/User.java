/*
 * A user entity should have at least the following attributes: firstname, lastname, email,
 * birthday and password.
 */
package com.users.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nkengasong
 */
@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "lastname")
    private String lastname;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "password")
    private String password;

    @Past
    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "registeredDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;

    @PrePersist
    private void prePersist() {
        // set registration date to current date 
        setRegisteredDate(new Date()); 
    } 

    /* 
         * Validate user password with regular expression
         * Password must be between 6 to 20 characters having at least one 
         * digit, a lowercase and a uppercase character and a special symbol
     */
    public void setPassword(String password) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new ValidationException("Password must be between 6 to 20 characters long, have atleast a digit, a special symbol and a lowercase and uppercase character");
        }
        
        // very very basic hash for user password. Not advisable to use during production
        this.password = ((Integer) password.hashCode()).toString();
    }
}
