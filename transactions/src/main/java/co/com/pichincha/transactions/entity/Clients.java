package co.com.pichincha.transactions.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Clients extends Persons {

    @NotEmpty(message = "It cant be empty")
    @Column(nullable = false, unique = true)
    @Size(min = 10, max = 10, message = "It should be into 10 and 10 characters")
    private String clientId;

    @NotEmpty(message = "It cant be empty")
    @Column(nullable = false)
    @Size(min = 4, max = 15, message = "It should be into 8 and 15 characters")
    private String password;

    @Column(nullable = false)
    private Boolean status;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
