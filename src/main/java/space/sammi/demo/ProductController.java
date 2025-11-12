package space.sammi.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponseDto> create(@RequestBody ProductRequestDto dto) {
        return service.createProduct(ProductMapper.toDomain(dto))
            .map(ProductMapper::toResponse);
    }

    @GetMapping("/{id}")
    public Mono<ProductResponseDto> getById(@PathVariable String id) {
        return service.getProductById(id)
            .map(ProductMapper::toResponse);
    }

    @GetMapping
    public Flux<ProductResponseDto> getAll() {
        return service.getAllProducts()
            .map(ProductMapper::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return service.deleteProduct(id);
    }
}
