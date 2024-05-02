package tech.investment.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.investment.project.dto.AccountStockRetrieve;
import tech.investment.project.entity.AccountStock;

@Mapper(componentModel = "spring")
public interface AccountStockMapper {

    @Mapping(target = "stockId", source = "id.stockId")
    @Mapping(target = "currentPrice", source = "stock.currentPrice")
    AccountStockRetrieve fromEntity(AccountStock accountStock);
}
