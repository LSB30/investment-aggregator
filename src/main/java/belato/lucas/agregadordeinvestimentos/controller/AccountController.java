package belato.lucas.agregadordeinvestimentos.controller;

import belato.lucas.agregadordeinvestimentos.controller.Dto.AssociateAccountStockDto;
import belato.lucas.agregadordeinvestimentos.entity.Stock;
import belato.lucas.agregadordeinvestimentos.entity.User;
import belato.lucas.agregadordeinvestimentos.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<User>associateStock(@PathVariable("accountId") String accountId, @RequestBody AssociateAccountStockDto dto) {
        accountService.associateStock(accountId, dto);

        return ResponseEntity.ok().build();
    }


    @GetMapping("{accountId}/stocks")
    public ResponseEntity<List<Stock>> listStocks(@PathVariable("accountId") String accountId) {
        var stocks = accountService.listStocks(accountId);

        return ResponseEntity.ok().body(stocks);
    }
}
