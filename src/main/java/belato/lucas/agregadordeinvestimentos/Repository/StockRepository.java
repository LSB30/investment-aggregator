package belato.lucas.agregadordeinvestimentos.Repository;

import belato.lucas.agregadordeinvestimentos.entity.Account;
import belato.lucas.agregadordeinvestimentos.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
