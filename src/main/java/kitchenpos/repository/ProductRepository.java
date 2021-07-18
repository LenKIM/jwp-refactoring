package kitchenpos.repository;

import kitchenpos.domain.product.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    <S extends Product> S save(S entity);

    default Product findProductById(Long id){
        return this.findById(id).orElseThrow(IllegalArgumentException::new);
    };

    List<Product> findAll();


}
