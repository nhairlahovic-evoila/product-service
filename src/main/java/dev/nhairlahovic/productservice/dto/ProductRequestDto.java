package dev.nhairlahovic.productservice.dto;

import dev.nhairlahovic.productservice.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(1)
    private double price;

    public static Product toEntity(ProductRequestDto requestDto) {
        return new Product(requestDto.getId(), requestDto.getName(), requestDto.getDescription(), requestDto.getPrice());
    }

}
