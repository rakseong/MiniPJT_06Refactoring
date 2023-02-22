package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	//Field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	PurchaseDao purchaseDao;

	public PurchaseServiceImpl() {
		System.out.println("PurchaseServiceImpl default »ý¼ºÀÚ");
	}

	//Method
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	@Override
	public Purchase addPurchase(Purchase purchase) throws Exception {
		System.out.println(purchase);
		purchaseDao.insertPurchase(purchase);	
		return purchase;
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("list",purchaseDao.getPurchaseList(search, userId));
		map.put("totalCount",purchaseDao.getTotalCount(search,userId));
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		return null;
	}

	@Override
	public Purchase updatePurchase(Purchase pruchase) throws Exception {
		purchaseDao.updatePurchase(pruchase);
		return pruchase;
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
	}
	
	public void updateTranCodeByProd(Purchase purchase) throws Exception{
		purchaseDao.updateTranCodeByProd(purchase);
	}

}
