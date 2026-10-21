package Third_year_lab1_1.Service;

import Third_year_lab1_1.Model.Category;
import Third_year_lab1_1.Model.Product;
import Third_year_lab1_1.Repo.CategoryRepository;
import Third_year_lab1_1.Repo.ProductRepo;
import Third_year_lab1_1.dto.CreateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepo productRepo, CategoryRepository categoryRepository) {
        this.productRepo = productRepo;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product create(Product product) {
        product.setId(null);
        return productRepo.save(product);
    }

    public Product getById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));
    }
    public List<Product> getByMinimumPrice(BigDecimal minPrice) {
        return productRepo.findByMinimumPrice(minPrice);
    }
    public Product createProduct(CreateProductRequest request) {
        if (request.categoryId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "categoryId is required");
        }

        Category category = categoryRepository
                .findById(request.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Category does not exist"));

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setCategory(category);

        return productRepo.save(product);
    }
    public List<Product> getByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
}

