package com.example.test.service;

import com.example.test.dto.ProductDto;
import com.example.test.enums.TypeOfProduct;
import com.example.test.exceptions.EntityNotFoundException;
import com.example.test.model.Category;
import com.example.test.model.Product;
import com.example.test.model.Seller;
import com.example.test.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final CategoryService categoryService;
    private final SellerService sellerService;
    public List<Product> getAllClass(){
        return productRepository.findAll();
    }


    public Optional<Product> getProductsByProductId(Long id){

        return productRepository.findProductsByProductId(id);
    }
    public List<Product> getByCategory(String categoryTitle){
        return productRepository.findByCategory(categoryTitle);
    }

    public Product save(ProductDto productDto, MultipartFile image) throws IOException {
        Category category = categoryService.getByCategoryTitle(productDto.getCategory().getCategoryName()).orElseThrow(() -> {
            throw new EntityNotFoundException("Category with '" + productDto.getCategory() + "' title not found");
        });
        Seller seller = sellerService.getBySellerId(productDto.getSeller().getSellerId()).orElseThrow(() -> {
            throw new EntityNotFoundException("Seller with '" + productDto.getSeller() + "' id not found");
        });

        TypeOfProduct type = weightOrQuantity(productDto.getSelectTypeOfProduct());
        Product product = Product.builder()
                .productName(productDto.getProductName())
                .description(productDto.getDescription())
                .category(category)
                .image(imageService.upload(image))
                .price(productDto.getPrice())
                .seller(seller)
                .typeOfProduct(type)
                .quantityOrWeight(productDto.getQuantityOrWeight())
                .build();

        return productRepository.save(product);
    }
    private TypeOfProduct weightOrQuantity(String weightOrQuantity){
        return weightOrQuantity.equalsIgnoreCase(TypeOfProduct.Weight.name()) ? TypeOfProduct.Weight : TypeOfProduct.Quantity;
    }
}