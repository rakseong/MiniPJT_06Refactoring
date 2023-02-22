package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	//Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	//Constructor
	public ProductDaoImpl() {
		System.out.println("ProductDaoImpl default »ý¼ºÀÚ");
	}
	
	//Method
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("ProductDaoImpl setter");
		this.sqlSession = sqlSession;
	}

	public Product getProduct(int productNo) throws Exception {
		return sqlSession.selectOne("ProdMapper.getProd",productNo);
	}

	public List<Object> getProductList(Search search) throws Exception {
		System.out.println(search);
		return sqlSession.selectList("ProdMapper.getProdList",search);
	}

	public void addProduct(Product product) throws Exception {
		System.out.println(product);
		sqlSession.insert("ProdMapper.addProd",product);
	}

	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProdMapper.updateProd",product);
	}

	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProdMapper.getTotalCount",search);
	}

}
