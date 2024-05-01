package tech.investment.project.constraint;

import lombok.RequiredArgsConstructor;
import tech.investment.project.service.StockService;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueStockId.Validator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueStockId {

    String message() default "Essa Ação/Fii já esta cadastrado!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<UniqueStockId, String> {

        private final StockService stockService;

        public boolean isValid(String ticker, ConstraintValidatorContext context) {
            var stock = stockService.findById(ticker);
            return stock == null;
        }
    }
}

