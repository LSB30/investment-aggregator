package belato.lucas.agregadordeinvestimentos.service;

import belato.lucas.agregadordeinvestimentos.Repository.StockRepository;
import belato.lucas.agregadordeinvestimentos.controller.Dto.CreateStockDto;
import belato.lucas.agregadordeinvestimentos.entity.Stock;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    public void createStock(CreateStockDto createStockDto) {
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
