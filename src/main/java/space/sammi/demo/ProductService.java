package space.sammi.demo;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Mono<Product> createProduct(Product product) {
        if (product.price() <= 0) {
            return Mono.error(new IllegalArgumentException("Price must be positive"));
        }
        return repository.save(product);
    }

    public Mono<Product> getProductById(String id) {
        return repository.findById(id);
    }

    public Flux<Product> getAllProducts() {
        return repository.findAll();
    }

    public Mono<Void> deleteProduct(String id) {
        return repository.deleteById(id);
    }
}
