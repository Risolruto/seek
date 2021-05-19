package yook.shop.goods;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import yook.common.map.CommandMap;
import yook.common.util.FileUtils;
import yook.shop.goods.GoodsDao;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	Logger log = Logger.getLogger(this.getClass()); // �α�

	@Resource(name = "goodsDao")
	private GoodsDao goodsDao;

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	/*
	 * @Override public List<Map<String, Object>> newGoodsList(Map<String, Object>
	 * map) throws Exception { // �ֽ� ��ǰ ����Ʈ
	 * 
	 * return goodsDao.newGoodsList(map); }
	 */
	
	@Override
	public List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception { // ����Ʈ ��ǰ ����Ʈ

		return goodsDao.bestGoodsList(map);
	}
	
	@Override
	public List<Map<String, Object>> GoodsListAll(Map<String, Object> map) throws Exception { // ����Ʈ ��ǰ ����Ʈ

		return goodsDao.GoodsListAll(map);
	}

	@Override
	public List<Map<String, Object>> GoodsListPig(Map<String, Object> map) throws Exception { // ����Ʈ ��ǰ ����Ʈ

		return goodsDao.GoodsListPig(map);
	}

	@Override
	public List<Map<String, Object>> GoodsListCow(Map<String, Object> map) throws Exception { // ����Ʈ ��ǰ ����Ʈ

		return goodsDao.GoodsListCow(map);
	}

	@Override
	public List<Map<String, Object>> GoodsListChicken(Map<String, Object> map) throws Exception { // ����Ʈ ��ǰ ����Ʈ

		return goodsDao.GoodsListChicken(map);
	}

	/*
	 * @Override public List<Map<String, Object>> cateGoodsList(Map<String, Object>
	 * map, String GOODS_CATE) throws Exception { // ī�װ��� ��ǰ ����Ʈ
	 * map.put("GOODS_CATE", GOODS_CATE); System.out.println(GOODS_CATE); return
	 * goodsDao.cateGoodsList(map); }
	 */

	@Override
	public void insertGoods(Map<String, Object> map, HttpServletRequest request) throws Exception { // ��ǰ���

		// ��ǰ���� ��� ���� ����
		goodsDao.insertGoods(map);
		System.out.println("****12132* " + map);

		map.get("GOODS_WEIGHT");
		System.out.println("������ �߷�=" + map.get("GOODS_WEIGHT"));

		String Weight = map.get("GOODS_WEIGHT").toString();
		System.out.println("Weight" + Weight);
		String WeightList[] = Weight.split(",");
		System.out.println("WeightList" + WeightList);
		System.out.println(WeightList.length);

		for (int i = 0; i <= WeightList.length - 1; i++) { // ��ǰ��Ͻ� ������ ������ �÷� �ϳ��ϳ��� ��ǰ�ɼ����̺� �������

			System.out.println("�迭�Դϴ�=" + WeightList[i]);
			map.put("GOODS_WEIGHT", WeightList[i]);
			goodsDao.goodsAttributeInsert(map);

		}

		System.out.println("�ɼǰ�=" + map);

	}

	@Override
	public void updateGoods(Map<String, Object> map, HttpServletRequest request) throws Exception { // ��ǰ���

		// ��ǰ���� ��� ���� ����
		goodsDao.insertGoods(map);
		System.out.println("****12132* " + map);

		map.get("GOODS_WEIGHT");
		System.out.println("������ �߷�=" + map.get("GOODS_WEIGHT"));

		String Weight = map.get("GOODS_WEIGHT").toString();

		String WeightList[] = Weight.split(",");
		System.out.println(WeightList.length);

		for (int i = 0; i <= WeightList.length - 1; i++) { // ��ǰ��Ͻ� ������ ������ �÷� �ϳ��ϳ��� ��ǰ�ɼ����̺� �������

			System.out.println("�迭�Դϴ�=" + WeightList[i]);
			map.put("GOODS_WEIGHT", WeightList[i]);
			goodsDao.goodsAttributeUpdate(map);

		}

		System.out.println("�ɼǰ�=" + map);

	}

	@Override
	public Map<String, Object> selectGoodsDetail(Map<String, Object> map, HttpServletRequest request) throws Exception { // ��ǰ
																															// ������
		System.out.println("map1111111=" + map.get("IDX"));

		/*
		 * if(map.get("IDX").getClass().getName().equals("java.lang.String")){ // PK����
		 * �Ϲ� ��Ʈ������ ���� �� Map<String,Object> map1 = new HashMap<String,Object>();
		 * 
		 * 
		 * map1.put("IDX", map.get("IDX")); goodsDao.goodsHitCnt(map1); //��ȸ��
		 * 
		 * }else { // PK���� �迭�� �Ѿ� ���� �� String[] Goods_No = (String[])map.get("IDX");
		 * map.put("IDX", Goods_No[0]);
		 * 
		 * 
		 * goodsDao.goodsHitCnt(map); //��ȸ��
		 * 
		 * }
		 */

		System.out.println("map=" + map);
		Map<String, Object> resultMap = goodsDao.selectGoodsDetail(map);

		System.out.println("RM=" + resultMap);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectGoodsAtt(Map<String, Object> map) throws Exception { // ��ǰ �󼼺��� �÷��� ������ ��������(�迭��
																							// ������)

		Map<String, Object> resultMap = goodsDao.selectGoodsAtt(map);

		return resultMap;
	}

	@Override
	public void insertBasket(Map<String, Object> map, HttpServletRequest request) throws Exception { // ��ǰ�����Ͽ��� ��ٱ��� �߰�
		map.remove("resultList");
		System.out.println("���񽺸�" + map);
		goodsDao.insertBasket(map);

	}

	@Override
	public void gumeListDelete(Map<String, Object> map) throws Exception {
		goodsDao.gumeListDelete(map);

	}

	@Override
	public List<Map<String, Object>> selectBasketNo(Map<String, Object> map) throws Exception { // ��ٱ��� PK�� ��������

		return goodsDao.selectBasketNo(map);
	}

	@Override
	public Object cateList() throws Exception {
		return goodsDao.cateList();
	}

	

}
