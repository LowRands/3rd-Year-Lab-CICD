package Third_year_lab1_1.Repo;

import Third_year_lab1_1.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepo  extends JpaRepository<Product,Long> {
    @Query(" SELECT p FROM Product p WHERE p.price >= :minPrice ORDER BY p.price ASC")
    List<Product> findByMinimumPrice(
            @Param("minPrice") BigDecimal minPrice);

}
