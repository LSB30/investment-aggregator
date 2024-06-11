package belato.lucas.agregadordeinvestimentos.controller;

import belato.lucas.agregadordeinvestimentos.controller.Dto.CreateAccountDto;
import belato.lucas.agregadordeinvestimentos.controller.Dto.CreateStockDto;
import belato.lucas.agregadordeinvestimentos.entity.Stock;
import belato.lucas.agregadordeinvestimentos.entity.User;
import belato.lucas.agregadordeinvestimentos.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody CreateStockDto createStockDto ) {

        stockService.createStock(createStockDto);

        return ResponseEntity.ok().build();
    }
}
