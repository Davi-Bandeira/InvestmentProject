package tech.investment.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.investment.project.dto.StockDTO;
import tech.investment.project.entity.Stock;

@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "currentPrice", ignore = true)
    @Mapping(target = "bazinPrice", ignore = true)
    @Mapping(target = "id", source = "ticker")
    Stock fromDTO(StockDTO stockDTO);
}
