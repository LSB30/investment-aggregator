package belato.lucas.agregadordeinvestimentos.service;

import belato.lucas.agregadordeinvestimentos.Repository.AccountRepository;
import belato.lucas.agregadordeinvestimentos.Repository.AccountStockRepository;
import belato.lucas.agregadordeinvestimentos.Repository.StockRepository;
import belato.lucas.agregadordeinvestimentos.controller.Dto.AssociateAccountStockDto;
import belato.lucas.agregadordeinvestimentos.entity.AccountStock;
import belato.lucas.agregadordeinvestimentos.entity.AccountStockId;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private StockRepository stockRepository;

    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
    }

    public void associateStock(String accountId, AssociateAccountStockDto dto) {
        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());

        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );

        System.out.println(entity);
        accountStockRepository.save(entity);
    }

    public Object listStocks(String accountId) {
    }
}
