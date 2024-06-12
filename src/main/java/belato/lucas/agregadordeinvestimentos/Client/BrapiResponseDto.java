package belato.lucas.agregadordeinvestimentos.Client;

import belato.lucas.agregadordeinvestimentos.controller.Dto.StockDto;

import java.util.List;

public record BrapiResponseDto(List<StockDto> results) {
}
