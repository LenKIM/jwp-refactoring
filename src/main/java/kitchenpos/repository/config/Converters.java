package kitchenpos.repository.config;

import kitchenpos.domain.product.Name;
import kitchenpos.domain.product.Price;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigDecimal;

public class Converters {

    @WritingConverter
    public enum NameToString implements Converter<Name, String> {
        INSTANCE;

        @Override
        public String convert(Name source) {
            return source.getValue();
        }
    }

    @ReadingConverter
    public enum StringToName implements Converter<String, Name> {
        INSTANCE;

        @Override
        public Name convert(String source) {
            return Name.of(source);
        }
    }

    @WritingConverter
    public enum PriceToDecimal implements Converter<Price, BigDecimal> {
        INSTANCE;

        @Override
        public BigDecimal convert(Price source) {
            return source.getValue();
        }
    }

    @ReadingConverter
    public enum DecimalToPrice implements Converter<BigDecimal, Price> {
        INSTANCE;

        @Override
        public Price convert(BigDecimal source) {
            return Price.of(source);
        }
    }

    @WritingConverter
    public enum MenuPriceToDecimal implements Converter<kitchenpos.domain.menu.Price, BigDecimal> {
        INSTANCE;

        @Override
        public BigDecimal convert(kitchenpos.domain.menu.Price source) {

            return source.getValue();
        }
    }

    @ReadingConverter
    public enum DecimalToMenuPrice implements Converter<BigDecimal, kitchenpos.domain.menu.Price> {
        INSTANCE;

        @Override
        public kitchenpos.domain.menu.Price convert(BigDecimal source) {
            return kitchenpos.domain.menu.Price.of(source);
        }
    }
}
