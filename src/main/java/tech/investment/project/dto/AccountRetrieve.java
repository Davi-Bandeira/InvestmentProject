package tech.investment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tech.investment.project.entity.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
public class AccountRetrieve {

    private Long id;
    private String description;
    private BigDecimal totalCost;
    private BigDecimal totalValue;
    private String variation;

    public AccountRetrieve(Account account) {
        this.id = account.getId();
        this.description = account.getDescription();
        this.totalCost = account.getTotalCost();
        this.totalValue = account.getTotalValue();
        this.variation = this.calcVariation();
    }

    private String calcVariation() {
        var different = totalValue.subtract(totalCost);
        var accountVariation = different.multiply(BigDecimal.valueOf(100))
                .divide(totalValue, 2, RoundingMode.HALF_DOWN);
        return accountVariation + "%";
    }
}
