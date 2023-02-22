package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	//Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	//Constructor
	public PurchaseDaoImpl() {
		System.out.println("PurchaseDaoImpl default »ý¼ºÀÚ");
	}
	
	//Method
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("PurchaseDaoImpl setter");
		this.sqlSession = sqlSession;
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase",tranNo);
	}

	@Override
	public List<Purchase> getPurchaseList(Search search, String userId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("search", search);
		map.put("userId",userId);
		return sqlSession.selectList("PurchaseMapper.getPurchaseList",map);
	}

	@Override
	public List<Purchase> getSaleList(Purchase purchase) throws Exception {
		return null;
	}

	@Override
	public void insertPurchase(Purchase purchase) throws Exception {
		System.out.println(purchase);
		sqlSession.insert("PurchaseMapper.addPurchase",purchase);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		System.out.println(purchase);
		sqlSession.update("PurchaseMapper.updatePurchase",purchase);
	}

	@Override
	public int getTotalCount(Search search,String userId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("search", search);
		map.put("userId",userId);
		return sqlSession.selectOne("PurchaseMapper.getTotalCount",map);
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		sqlSession.update("PurchaseMapper.updateTranCode",purchase);
	}
	
	public void updateTranCodeByProd(Purchase purchase) throws Exception{
		sqlSession.update("PurchaseMapper.updateTranCodeByProd",purchase);
	}

}
