package com.PBL3.services;

import com.PBL3.dtos.ProductDTO;
import com.PBL3.utils.response.Message;

public interface IProductService {
    Message createNewProduct(ProductDTO dto);

    Message getAllProducts();

    Message updateProduct(ProductDTO dto);

    Message deleteProduct(String id);
}
