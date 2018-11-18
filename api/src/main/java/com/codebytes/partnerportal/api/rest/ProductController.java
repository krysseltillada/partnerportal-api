package com.codebytes.partnerportal.api.rest;

import com.codebytes.partnerportal.api.domain.Product;
import com.codebytes.partnerportal.api.domain.rest.ResponseStatus;
import com.codebytes.partnerportal.api.domain.rest.product.CreateProductRequest;
import com.codebytes.partnerportal.api.domain.rest.product.CreateProductResponse;
import com.codebytes.partnerportal.api.domain.rest.product.DeleteProductByIdRequest;
import com.codebytes.partnerportal.api.domain.rest.product.DeleteProductByIdResponse;
import com.codebytes.partnerportal.api.domain.rest.product.GetAllProductRequest;
import com.codebytes.partnerportal.api.domain.rest.product.GetAllProductResponse;
import com.codebytes.partnerportal.api.domain.rest.product.GetProductByIdRequest;
import com.codebytes.partnerportal.api.domain.rest.product.GetProductByIdResponse;
import com.codebytes.partnerportal.api.domain.rest.product.UpdateProductByIdRequest;
import com.codebytes.partnerportal.api.domain.rest.product.UpdateProductByIdResponse;
import com.codebytes.partnerportal.api.rest.repository.ProductRepository;
import com.codebytes.partnerportal.api.rest.repository.StoreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Api(tags = { "Product API" }, description="Operations pertaining to products in PartnerPortal Open API", basePath = "/product")
public class ProductController
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository mStoreRepository;

    @ApiOperation(value = "Create a product",
                  response = CreateProductResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PostMapping("/product/create")
    public @ResponseBody
    CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
        System.out.println(createProductRequest);

        Product product = new Product();

        product.setSku(createProductRequest.getProductSKU());
        product.setName(createProductRequest.getName());
        product.setBrand(createProductRequest.getBrand());
        product.setDescription(createProductRequest.getDescription());
        product.setDiscountType(createProductRequest.getDiscountType());
        product.setDiscount(createProductRequest.getDiscount());
        product.setMainImage(createProductRequest.getMainImage());
        product.setSubImages(createProductRequest.getSubImages());
        product.setModel(createProductRequest.getModel());
        product.setPrice(createProductRequest.getPrice());
        product.setQuantity(createProductRequest.getQuantity());
        product.setDateCreated(LocalDateTime.now());

        CreateProductResponse createProductResponse = new CreateProductResponse();

        try {
            productRepository.save(product);

            createProductResponse.setApiKey(createProductRequest.getApiKey());
            createProductResponse.setAppId(createProductRequest.getAppId());
            createProductResponse.setUserId(createProductRequest.getUserId());

            ResponseStatus responseStatus = new ResponseStatus();

            responseStatus.setStatus("SUCCESS");
            responseStatus.setMessage("Product Posted");

            createProductResponse.setMResponseStatus(responseStatus);

            createProductResponse.setResponseDateTime(LocalDateTime.now());

        } catch (DataAccessException e) {
            createProductResponse.setApiKey(createProductRequest.getApiKey());
            createProductResponse.setAppId(createProductRequest.getAppId());
            createProductResponse.setUserId(createProductRequest.getUserId());
            createProductResponse.setResponseDateTime(LocalDateTime.now());

            ResponseStatus responseStatus = new ResponseStatus();

            responseStatus.setStatus("ERROR");
            responseStatus.setMessage("Failed to post product");

            createProductResponse.setMResponseStatus(responseStatus);
        }

        return createProductResponse;
    }

    @ApiOperation(value = "Gets all the products by a pageNo and pageCount fields",
                  response = GetAllProductResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PostMapping("/product/getAll")
    //@RequestParam("index") String index, @RequestParam("count") String count
    public @ResponseBody
    GetAllProductResponse getAllProduct(@RequestBody GetAllProductRequest getAllProductRequest) {
        GetAllProductResponse getAllProductResponse = new GetAllProductResponse();

        getAllProductResponse.setAppId(getAllProductRequest.getAppId());
        getAllProductResponse.setApiKey(getAllProductRequest.getApiKey());
        getAllProductResponse.setUserId(getAllProductRequest.getUserId());
        getAllProductResponse.setResponseDateTime(LocalDateTime.now());

        List<Product> productList = productRepository.findAll(new PageRequest(getAllProductRequest.getPageNo(), getAllProductRequest.getPageCount())).getContent();

        getAllProductResponse.setProductList(productList);
        getAllProductResponse.setProductCount(productList.size());

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setStatus("SUCCESS");

        getAllProductResponse.setMResponseStatus(responseStatus);

        return getAllProductResponse;
    }

    @ApiOperation(value = "Gets a product by a product Id",
                  response = GetProductByIdResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PostMapping("/product/getProductById")
    public @ResponseBody
    GetProductByIdResponse getProductById(@RequestBody
                                                  GetProductByIdRequest getProductByIdRequest) {
        GetProductByIdResponse getProductByIdResponse = new GetProductByIdResponse();

        getProductByIdResponse.setAppId(getProductByIdRequest.getAppId());
        getProductByIdResponse.setApiKey(getProductByIdRequest.getApiKey());
        getProductByIdResponse.setUserId(getProductByIdRequest.getUserId());
        getProductByIdResponse.setResponseDateTime(LocalDateTime.now());

        Product product = productRepository.findById(getProductByIdRequest.getProductId()).get();

        getProductByIdResponse.setProduct(product);

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setStatus("SUCCESS");

        getProductByIdResponse.setMResponseStatus(responseStatus);

        return getProductByIdResponse;
    }

    @ApiOperation(value = "Deletes a product by a product Id",
                  response = DeleteProductByIdResponse.class,
                  produces = "application/json", protocols = "http/https")
    @DeleteMapping("/product/delete")
    public @ResponseBody
    DeleteProductByIdResponse deleteProductById(@RequestBody DeleteProductByIdRequest deleteProductByIdRequest) {
        DeleteProductByIdResponse getProductByIdResponse = new DeleteProductByIdResponse();

        getProductByIdResponse.setAppId(deleteProductByIdRequest.getAppId());
        getProductByIdResponse.setApiKey(deleteProductByIdRequest.getApiKey());
        getProductByIdResponse.setUserId(deleteProductByIdRequest.getUserId());
        getProductByIdResponse.setResponseDateTime(LocalDateTime.now());

        ResponseStatus responseStatus = new ResponseStatus();

        productRepository.deleteById(deleteProductByIdRequest.getProductId());

        responseStatus.setStatus("SUCCESS");
        responseStatus.setMessage("Product id " + deleteProductByIdRequest.getProductId() + " is Deleted");

        getProductByIdResponse.setMResponseStatus(responseStatus);

        return getProductByIdResponse;
    }

    @ApiOperation(value = "Updates a product by a product Id",
                  response = UpdateProductByIdResponse.class,
                  produces = "application/json", protocols = "http/https")
    @PutMapping("/product/update")
    public @ResponseBody
    UpdateProductByIdResponse updateProduct(@RequestBody UpdateProductByIdRequest updateProductByIdRequest) {

        UpdateProductByIdResponse updateProductByIdResponse = new UpdateProductByIdResponse();

        updateProductByIdResponse.setAppId(updateProductByIdRequest.getAppId());
        updateProductByIdResponse.setApiKey(updateProductByIdRequest.getApiKey());
        updateProductByIdResponse.setUserId(updateProductByIdRequest.getUserId());
        updateProductByIdResponse.setResponseDateTime(LocalDateTime.now());

        Product product = productRepository.findById(updateProductByIdRequest.getProductId()).get();

        product.setName(updateProductByIdRequest.getName());
        product.setBrand(updateProductByIdRequest.getBrand());
        product.setDescription(updateProductByIdRequest.getDescription());
        product.setModel(updateProductByIdRequest.getModel());
        product.setQuantity(product.getQuantity());
        product.setPrice(updateProductByIdRequest.getPrice());
        product.setDiscountType(updateProductByIdRequest.getDiscountType());
        product.setDiscount(updateProductByIdRequest.getDiscount());

        product.setMainImage(updateProductByIdRequest.getMainImage());
        product.setSubImages(updateProductByIdRequest.getSubImages());

        productRepository.save(product);

        updateProductByIdResponse.setProductId(updateProductByIdRequest.getProductId());

        ResponseStatus responseStatus = new ResponseStatus();

        responseStatus.setStatus("SUCCESS");
        responseStatus.setMessage("Product id " + updateProductByIdRequest.getProductId() + " Updated.");

        updateProductByIdResponse.setMResponseStatus(responseStatus);

        return updateProductByIdResponse;
    }
}
