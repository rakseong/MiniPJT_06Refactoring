package com.model2.mvc.service.product;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {

	//상품 정보 조회
	public Product getProduct(int productNo) throws Exception;
	
	//상품 목록
	public List<Object> getProductList(Search search) throws Exception;
	
	//상품 등록
	public void addProduct(Product product) throws Exception;
	
	//상품 정보 수정
	public void updateProduct(Product product) throws Exception;
	
	//검색된 record 개수
	public int getTotalCount(Search search) throws Exception;
}
