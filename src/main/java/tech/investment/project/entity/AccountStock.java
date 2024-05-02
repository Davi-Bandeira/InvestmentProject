package tech.investment.project.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.investment.project.dto.AccountStockDTO;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts_stocks")
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Integer quantity;

    private BigDecimal avgPrice;

    private BigDecimal percentWallet;

    private BigDecimal totalCost;

    private BigDecimal totalValue;

    public AccountStock(AccountStockId id, Account account, Stock stock, AccountStockDTO accountStockDTO) {
        this.id = id;
        this.stock = stock;
        this.account = account;
        this.percentWallet = BigDecimal.valueOf(100);
        this.quantity = accountStockDTO.getQuantity();
        this.avgPrice = accountStockDTO.getUnitPrice();
        this.totalCost = BigDecimal.valueOf(this.quantity).multiply(this.avgPrice);
        this.totalValue = BigDecimal.valueOf(this.quantity).multiply(stock.getCurrentPrice());
    }
}
