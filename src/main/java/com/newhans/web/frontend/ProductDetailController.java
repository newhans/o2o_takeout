package com.newhans.web.frontend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newhans.entity.Product;
import com.newhans.service.ProductService;
import com.newhans.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
	@Autowired
	private ProductService productService;
	
	/**
	 * 根据商品ID获取商品详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductDetailPageInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取前台传来的productId
		long productId = HttpServletRequestUtil.getLong(request, "productId");
		Product product = null;
		if (productId != -1L) {
			//根据productId获取商品信息，包含商品详情图列表
			product = productService.getProductById(productId);
			modelMap.put("product", product);
			modelMap.put("success", true);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty productId");
		}
		return modelMap;
	}
}
