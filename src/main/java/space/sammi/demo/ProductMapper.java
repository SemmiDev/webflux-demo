package space.sammi.demo;


public class ProductMapper {
    public static Product toDomain(ProductRequestDto dto) {
        return new Product(null, dto.name(), dto.price());
    }

    public static ProductResponseDto toResponse(Product product) {
        return new ProductResponseDto(product.id(), product.name(), product.price());
    }
}
