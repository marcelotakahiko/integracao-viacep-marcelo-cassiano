package com.example.crud.service;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.address.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductAvailabilityService {
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductAvailabilityService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.restTemplate = new RestTemplate();
    }

    public boolean isProductAvailable(String productId, String cep) {
        //Consulta o produto no banco
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        //Consulta a API ViaCep para pegar a cidade do cep
        Address address = restTemplate.getForObject(
                "https://viacep.com.br/ws/" + cep + "/json/",
                Address.class
        );

        if (address == null || address.getLocalidade() == null) {
            throw new RuntimeException("CEP inválido ou cidade não encontrada");
        }

        //Compara a cidade do CEP com o distributionCenter do produto
        return address.getLocalidade().equalsIgnoreCase(product.getDistributionCenter());
    }
}
