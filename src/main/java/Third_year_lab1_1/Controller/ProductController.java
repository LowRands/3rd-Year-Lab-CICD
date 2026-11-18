package Third_year_lab1_1.Controller;

import Third_year_lab1_1.Model.Product;
import Third_year_lab1_1.Service.ProductService;
import Third_year_lab1_1.dto.CreateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) {
        return productService.getById(id);
    }

    @GetMapping("/min-price")
    public List<Product> getByMinimumPrice(
            @RequestParam("price") BigDecimal price) {
        return productService.getByMinimumPrice(price);
    }
    @GetMapping("/by-category")
    public List<Product> getByCategory(
            @RequestParam("name") String categoryName) {
        return productService.getByCategoryName(categoryName);
    }
}