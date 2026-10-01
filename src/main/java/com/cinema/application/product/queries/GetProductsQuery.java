package com.cinema.application.product.queries;

import com.cinema.application.common.Query;
import com.cinema.application.contracts.product.ProductDto;
import java.util.List;

public record GetProductsQuery() implements Query<List<ProductDto>> {}