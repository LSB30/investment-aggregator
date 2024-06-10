package belato.lucas.agregadordeinvestimentos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_accountstocks")
public class AccountStock {

    @EmbeddedId
    private AccountStockId accountStockId;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;

    public AccountStock(){}

    public AccountStock(AccountStockId accountStockId, Account account, Stock stock, Integer quantity) {
        this.accountStockId = accountStockId;
        this.account = account;
        this.stock = stock;
        this.quantity = quantity;
    }

    public AccountStockId getId() {
        return accountStockId;
    }

    public void setId(AccountStockId id) {
        this.accountStockId = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
