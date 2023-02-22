package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("vo") Product prod) throws Exception{
		prod.setProTranCode("0");
		
		if(productService.addProduct(prod) != null) {
			System.out.println("상품등록 성공");
		}else {
			System.out.println("상품등록 실패");
		}
		
		return "forward:/product/addProduct.jsp";
	}

	
	@RequestMapping("/listProduct.do")
	public String listProduct(@ModelAttribute("search") Search search ,@RequestParam(value="currentPage", required = false) Integer currentPage
			,@RequestParam("menu") String menu, HttpServletRequest req) throws Exception{
		if(currentPage == null) {
			currentPage = 1;
		}
		search.setCurrentPage(currentPage);
		search.setPageSize(pageSize);
		
		Map<String,Object> map= productService.getProductList(search);
		
		Page resultPage = new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue()
				,pageUnit,pageSize);
		
		req.setAttribute("resultPage", resultPage);
		req.setAttribute("list", map.get("list"));
		
		return "forward:/product/listProduct.jsp?menu="+menu;
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("productNo") int prodNo,@RequestParam("menu") String menu, HttpServletRequest req) throws Exception{
		System.out.println("getProduct.do start...");
		Product vo = productService.getProduct(prodNo);
		req.setAttribute("vo", vo);
		System.out.println(vo.getProdNo());
		if(menu.equals("manage")) {
			return "forward:/updateProductView.do";
		}else {
			return "forward:/product/getProduct.jsp";
		}
	}
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("productNo") int prodNo,@RequestParam("menu") String menu
			,HttpServletRequest req) {
		System.out.println(prodNo);
		System.out.println("updateProductView.do start...");
		Product product = (Product)req.getAttribute("vo");
		
		req.setAttribute("product", product);
		System.out.println(menu);
		req.setAttribute("menu", menu);
		
		return "forward:/product/updateProduct.jsp";
	}
	
	@RequestMapping("/updateProduct.do")
	public String updateProduct(@ModelAttribute("vo") Product product,@RequestParam("prodNo") int prodNo,@RequestParam("menu") String menu) throws Exception {
		product.setProdNo(prodNo);
		
		productService.updateProduct(product);
		
		return "forward:/product/getProduct.jsp?productNo="+prodNo+"&menu="+menu;
	}
	

}
