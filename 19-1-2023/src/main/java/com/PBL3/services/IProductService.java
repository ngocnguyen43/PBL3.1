package com.PBL3.services;

import com.PBL3.dtos.ProductDTO;
import com.PBL3.dtos.pagination.ProductPaginationDTO;
import com.PBL3.utils.exceptions.dbExceptions.*;
import com.PBL3.utils.response.Message;

public interface IProductService {
    Message createNewProduct(ProductDTO dto) throws CreateFailedException;

    Message getAllProducts() throws UnexpectedException;

    Message getAllProducts(ProductPaginationDTO dto) throws UnexpectedException;

    Message getProduct(String id,String userId) throws UnexpectedException;

    Message updateProduct(ProductDTO dto) throws UpdateFailedException, NotFoundException;

    Message deleteProduct(String id) throws UpdateFailedException;

    Message getAllProducts(ProductPaginationDTO dto, String id) throws UnexpectedException;

    Message createNewProduct(ProductDTO dto, String id) throws CreateFailedException, InvalidPropertiesException;

}
