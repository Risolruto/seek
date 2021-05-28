package yook.shop.goods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import yook.common.map.CommandMap;
import yook.shop.basket.BasketService;
import yook.shop.goods.GoodsService;
import yook.shop.order.OrderService;
import yook.admin.review.AdminReviewService;
import yook.admin.agoods.AgoodsServiceImpl;

@Controller
public class GoodsController {

	Logger log = Logger.getLogger(this.getClass()); // 로그

	@Resource(name = "goodsService")
	private GoodsService goodsService;

	@Resource(name = "basketService")
	private BasketService basketService;

	@Resource(name = "orderService")
	private OrderService orderService;
	
	@Resource(name = "reviewService")
	private AdminReviewService reviewService;

	/*
	 * @RequestMapping(value = "/shop/newGoodsList.do") public ModelAndView
	 * newGoodsList(CommandMap commandMap) throws Exception { // NewItem 리스트 출력
	 * 
	 * ModelAndView mv = new ModelAndView("jsonView");
	 * 
	 * List<Map<String, Object>> list =
	 * goodsService.newGoodsList(commandMap.getMap());
	 * 
	 * mv.addObject("list", list); mv.addObject("TITLEMAIN", "새상품");
	 * 
	 * return mv;
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/main.do") // 상품 전체보기
	public ModelAndView main(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("main");

		List<Map<String, Object>> list = goodsService.bestGoodsList(commandMap.getMap());

		mv.addObject("list", list);
		

		return mv;

	}
	
	@RequestMapping(value = "/GoodsListAll.do") // 상품 전체보기
	public ModelAndView GoodsListAll(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("GoodsListAll");

		List<Map<String, Object>> list = goodsService.GoodsListAll(commandMap.getMap());

		mv.addObject("list", list);
		

		return mv;

	}
	
	@RequestMapping(value = "/GoodsListPig.do") // 상품 전체보기
	public ModelAndView GoodsListPig(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("GoodsListPig");

		List<Map<String, Object>> list = goodsService.GoodsListPig(commandMap.getMap());

		mv.addObject("list", list);
		System.out.println("list"+list);
		

		return mv;

	}
	
	@RequestMapping(value = "/GoodsListCow.do") // 상품 전체보기
	public ModelAndView GoodsListCow(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("GoodsListCow");

		List<Map<String, Object>> list = goodsService.GoodsListCow(commandMap.getMap());

		mv.addObject("list", list);
		

		return mv;

	}
	
	@RequestMapping(value = "/GoodsListChicken.do") // 상품 전체보기
	public ModelAndView GoodsListChicken(CommandMap commandMap) throws Exception { 

		ModelAndView mv = new ModelAndView("GoodsListChicken");

		List<Map<String, Object>> list = goodsService.GoodsListChicken(commandMap.getMap());

		mv.addObject("list", list);
		

		return mv;

	}

	
	/*
	 * @RequestMapping(value = "/shop/goodsList/{cate}/{orderBy}.do") public
	 * ModelAndView openGoodsList(@PathVariable String cate, @PathVariable String
	 * orderBy, CommandMap commandMap,
	 * 
	 * @RequestParam(value = "GOODS_CATE", defaultValue = "") String GOODS_CATE,
	 * HttpServletRequest request) // 카테고리별 상품리스트 throws Exception { ModelAndView mv
	 * = new ModelAndView("goods"); commandMap.put("cate", cate);
	 * request.setAttribute("GOODS_CATE", GOODS_CATE);
	 * System.out.println("카테고리 검색확인=" + commandMap.getMap());
	 * System.out.println("orderby=" + orderBy);
	 * 
	 * mv.addObject("IDX", commandMap.getMap().get("IDX")); if
	 * ("favorite".equals(orderBy)) { // 인기상품 commandMap.put("orderBy",
	 * "GOODS_COUNT"); commandMap.put("orderSort", "DESC"); } else if
	 * ("low".equals(orderBy)) { // 낮은가격순 commandMap.put("orderBy", "GOODS_PRICE");
	 * commandMap.put("orderSort", "ASC"); } else if ("high".equals(orderBy)) { //
	 * 높은가격순 commandMap.put("orderBy", "GOODS_PRICE"); commandMap.put("orderSort",
	 * "DESC"); }
	 * 
	 * mv.addObject("category", cate); String filePath_temp =
	 * request.getContextPath() + "/file/"; mv.addObject("path", filePath_temp);
	 * request.setAttribute("path", filePath_temp); return mv; }
	 * 
	 * @RequestMapping(value = "/shop/cateGoodsList/{cate}/{orderBy}.do") public
	 * ModelAndView selectGoodsList(@PathVariable String cate, @PathVariable String
	 * orderBy, CommandMap commandMap,
	 * 
	 * @RequestParam(value = "GOODS_CATE", defaultValue = "") String GOODS_CATE,
	 * HttpServletRequest request) // 카테고리별 리스트 제이슨 throws Exception {
	 * 
	 * ModelAndView mv = new ModelAndView("jsonView"); List<Map<String, Object>>
	 * list = null;
	 * 
	 * System.out.println("카테고리 = " + cate); System.out.println("카테고리 순서 =" +
	 * orderBy); System.out.println("중분류 =" + GOODS_CATE);
	 * 
	 * // category commandMap.put("cate", cate); commandMap.put("orderBy", orderBy);
	 * 
	 * // order by if ("favorite".equals(orderBy)) { // 인기상품
	 * commandMap.put("orderBy", "GOODS_COUNT"); commandMap.put("orderSort",
	 * "DESC"); } else if ("low".equals(orderBy)) { // 낮은가격순
	 * commandMap.put("orderBy", "GOODS_PRICE"); commandMap.put("orderSort", "ASC");
	 * } else if ("high".equals(orderBy)) { // 높은가격순 commandMap.put("orderBy",
	 * "GOODS_PRICE"); commandMap.put("orderSort", "DESC"); }
	 * 
	 * list = goodsService.cateGoodsList(commandMap.getMap(), GOODS_CATE);
	 * 
	 * //System.out.println("토탈카운트" + list.get(0).get("TOTAL_COUNT"));
	 * 
	 * mv.addObject("list", list); if (list.size() > 0) { mv.addObject("TOTAL",
	 * list.get(0).get("TOTAL_COUNT")); } else { mv.addObject("TOTAL", 0); } //
	 * mv.addObject("category", cate);
	 * 
	 * return mv; }
	 */ 
	 
	@RequestMapping(value = "/adminGoodsWrite.do") // url public ModelAndView
	public ModelAndView goodsWriteForm(CommandMap commandMap) throws Exception { // 상품등록 폼
	 
	 ModelAndView mv = new ModelAndView("adminGoodsWrite"); mv.addObject("type",
	 "write"); mv.addObject("title", "상품등록"); return mv;
	 
	 }  
	 
	@RequestMapping(value = "/goodsWrite.do", method = RequestMethod.POST)
	public ModelAndView goodsWrite(CommandMap commandMap, HttpServletRequest request) throws Exception { // 상품등록

		ModelAndView mv = new ModelAndView("redirect:/adminGoodsList.do");

		System.out.println(commandMap.getMap());

		commandMap.put("GOODS_IMAGE", request.getSession().getAttribute("GOODS_IMAGE"));

		goodsService.insertGoods(commandMap.getMap(), request);
		System.out.println("글쓰기입니당" + commandMap.getMap());
		return mv;

	}

	@RequestMapping(value = "/goodsDetail.do")
	public ModelAndView goodsDetail(CommandMap commandMap, HttpServletRequest request) throws Exception { // 상품디테일
		
		
		ModelAndView mv = new ModelAndView("goods_detail");
		/*
		 * HttpSession session = request.getSession(); commandMap.put("MEM_NUM",
		 * ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
		 */
		
		Map<String, Object> map = goodsService.selectGoodsDetail(commandMap.getMap(), request);
		System.out.println("IDX = " + commandMap.getMap());
		Map<String, Object> IDX = commandMap.getMap();
		System.out.println("map = " + map);

		
		mv.addObject("map", map); // 상품의 PK값
		mv.addObject("list", map); // 상품 상세 정보입니다

		Map<String, Object> map1 = goodsService.selectGoodsAtt(commandMap.getMap());
		
		List<Map<String, Object>> list1 = reviewService.adminReviewList(commandMap.getMap());
		
		System.out.println("map1=" + map1);
		System.out.println("list1=" + list1);

		String Weight = map1.get("GOODS_WEIGHT").toString();
		
		System.out.println("Weight=" + Weight);
		String[] WeightList = Weight.split(",");

		System.out.println("WeightList" + WeightList.toString());

		ArrayList<String> arrWeight = new ArrayList<String>();

		
		for (String data : WeightList) {
			if (!arrWeight.contains(data))
				arrWeight.add(data);
		}

		System.out.println("arrWeight=" + arrWeight);

		int WeightSize = arrWeight.size();

		mv.addObject("Weight", arrWeight); // 컬러 총 갯수
		System.out.println("Weight=" + Weight);
		mv.addObject("WeightSize", WeightSize); // 컬러 종류
		System.out.println("WeightSize=" + WeightSize);
		mv.addObject("list1", list1);
		return mv;

	}

	@RequestMapping(value = "/shop/basketPopUp.do", method = RequestMethod.GET)
	public ModelAndView basketPopUp(CommandMap commandMap) throws Exception { // 상품디테일 장바구니 클릭시 팝업창
		ModelAndView mv = new ModelAndView("/basket/basketPopUp");

		return mv;
	}

	@RequestMapping(value = "/shop/insertBasket.do", method = RequestMethod.POST)
	public ModelAndView insertBasket(CommandMap commandMap, HttpServletRequest request) throws Exception { // 상품디테일에서
																											// 장바구니 추가
		ModelAndView mv = new ModelAndView("redirect:/goodsDetail.do");
		commandMap.put("IDX", request.getParameter("IDX"));
		Object MEM_NUM = "";
		
		  HttpSession session = request.getSession();
		
		  // 세션값 가져오기 HttpSession session = request.getSession(); // 세션 값으로 적용
		  if(((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM")!=null) {
			  
		  
		  commandMap.put("MEM_NUM", ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
		  System.out.println("MEM_NUM   " + ((Map)session.getAttribute("session_MEMBER")).get("MEM_NUM"));
		  }

		// 장바구니에 넣을 상품이 한개일때
		
			Map<String, Object> map = new HashMap<String, Object>();
			
			System.out.println("CommandMap1=" + commandMap.getMap());
			map.put("IDX", commandMap.get("IDX"));
			map.put("MEM_NUM", commandMap.get("MEM_NUM"));
			map.put("GOODS_WEIGHT", commandMap.get("GOODS_WEIGHT"));
			
			goodsService.insertBasket(map, request);
		
		mv.addObject("IDX", commandMap.getMap().get("IDX"));
		return mv;
	}

	@RequestMapping(value = "/shop/goodsOrder.do", method = RequestMethod.POST)
	public ModelAndView goodsOrder(CommandMap commandMap, HttpServletRequest request) throws Exception { // 상품디테일에서 구매
		ModelAndView mv = new ModelAndView("shop/orderForm");

		goodsService.gumeListDelete(commandMap.getMap());

		Object MEM_NUM = ""; // 세션값 가져오기
		HttpSession session = request.getSession();
		commandMap.put("MEM_NUM", ((Map) session.getAttribute("session_MEMBER")).get("MEM_NUM"));

		System.out.println("CommandMap=" + commandMap.getMap());
		commandMap.remove("resultList");

		if (commandMap.get("GOODS_COLOR").getClass().getName().equals("java.lang.String")) { // 일반 스트링으로 왔을 때
			Map<String, Object> map = new HashMap<String, Object>(); // 구매시 장바구니에 등록
			System.out.println("CommandMap1=" + commandMap.getMap());

			map.put("IDX", commandMap.get("IDX"));
			map.put("MEM_NUM", commandMap.get("MEM_NUM"));
			map.put("GOODS_SIZE", commandMap.get("GOODS_SIZE"));
			map.put("GOODS_COLOR", commandMap.get("GOODS_COLOR"));
			map.put("GOODS_ATT_AMOUNT", commandMap.get("GOODS_ATT_AMOUNT"));
			goodsService.insertBasket(map, request);
		} else { // 배열로 왔을 때
			System.out.println("CommandMap2=" + commandMap.getMap());
			String[] Size = (String[]) commandMap.getMap().get("GOODS_SIZE");
			String[] Color = (String[]) commandMap.getMap().get("GOODS_COLOR");
			String[] Amount = (String[]) commandMap.getMap().get("GOODS_ATT_AMOUNT");
			String[] Goods_Num = (String[]) commandMap.getMap().get("IDX");

			System.out.println("다중 사이즈0=" + Goods_Num[0]);
			System.out.println("다중 사이즈1=" + Goods_Num[1]);
			Map<String, Object> map1 = new HashMap<String, Object>();
			for (int j = 0; j <= Size.length - 1; j++) {
				map1.put("GOODS_SIZE", Size[j]);
				map1.put("GOODS_COLOR", Color[j]);
				map1.put("GOODS_ATT_AMOUNT", Amount[j]);
				map1.put("IDX", Goods_Num[j]);
				map1.put("MEM_NUM", commandMap.get("MEM_NUM"));
				System.out.println("Size1111=" + Size[j]);
				goodsService.insertBasket(map1, request);
			}
		}

		List<Map<String, Object>> list0 = goodsService.selectBasketNo(commandMap.getMap()); // 장바구니 PK값 가져오기
		System.out.println("장바구니넘버111111" + list0.get(0).get("BASKET_NUM"));

		commandMap.remove("SELECT_BASKET_NUM");
		commandMap.put("SELECT_BASKET_NUM", list0.get(0).get("BASKET_NUM"));

		List<Map<String, Object>> list = basketService.basketSelectList(commandMap, request); // 선택한 장바구니번호의 상품
		Map<String, Object> map = orderService.orderMemberInfo(commandMap, request); // 주문자정보

		mv.addObject("list", list);
		mv.addObject("map", map);
		System.out.println(list);
		return mv;
	}
	
	@RequestMapping(value = "/gd.do")
	public ModelAndView gd(CommandMap commandMap) throws Exception { // 상품디테일 장바구니 클릭시 팝업창
		ModelAndView mv = new ModelAndView("review");

		return mv;
	}
	
	@RequestMapping(value = "/delivery.do")
	public ModelAndView delivery(CommandMap commandMap) throws Exception { // 상품디테일 장바구니 클릭시 팝업창
		ModelAndView mv = new ModelAndView("delivery");

		return mv;
	}
	
	

}