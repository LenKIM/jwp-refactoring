package kitchenpos.application;

import kitchenpos.domain.product.Price;
import kitchenpos.domain.product.Product;
import kitchenpos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productDao) {
        this.productRepository = productDao;
    }

    @Transactional
    public Product create(final Product product) {
        final Price price = product.getPrice();

        if (Objects.isNull(price) || price.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }

        return productRepository.save(product);
    }

    public List<Product> list() {
        return productRepository.findAll();
    }
}
