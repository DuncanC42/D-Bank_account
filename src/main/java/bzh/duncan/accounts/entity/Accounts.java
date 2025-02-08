package bzh.duncan.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity {


    @Id
    private Long accountNumber;

    private Long customerId;

    private String accountType;

    private String branchAddress;

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
