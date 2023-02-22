package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao prodDao;
	
	public void setProductDao(ProductDao productDao) {
		System.out.println("ProductServiceImpl setter");
		this.prodDao = productDao;
	}

	public ProductServiceImpl() {
		System.out.println("ProductServiceImpl default »ý¼ºÀÚ");
	}

	@Override
	public Product addProduct(Product product) throws Exception {
		prodDao.addProduct(product);
		return product;
	}

	@Override
	public Product getProduct(int productNo) throws Exception {
		return prodDao.getProduct(productNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(search);
		map.put("list", prodDao.getProductList(search));   
		map.put("totalCount",prodDao.getTotalCount(search));
		return map;
	}

	@Override
	public Product updateProduct(Product product) throws Exception {
		prodDao.updateProduct(product);
		return product;
	}

}
