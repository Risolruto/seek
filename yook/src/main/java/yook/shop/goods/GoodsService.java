package yook.shop.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import yook.common.map.CommandMap;

public interface GoodsService {
	
	/*
	 * List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws
	 * Exception; // �ֽ� ��ǰ ����Ʈ
	 */	
	
	List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception; // ����Ʈ ��ǰ ����Ʈ
	
	List<Map<String, Object>> GoodsListAll(Map<String, Object> map) throws Exception; // ����Ʈ ��ǰ ����Ʈ
	
	List<Map<String, Object>> GoodsListPig(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> GoodsListCow(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> GoodsListChicken(Map<String, Object> map) throws Exception;
		
	/*
	 * List<Map<String, Object>> cateGoodsList(Map<String, Object> map, String
	 * GOODS_CATEGORY2) throws Exception; // ī�װ��� ��ǰ��
	 */	
	void insertGoods(Map<String, Object> map, HttpServletRequest request) throws Exception; 
	
	void updateGoods(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	Map<String, Object> selectGoodsDetail(Map<String,Object> map, HttpServletRequest request) throws Exception; // ��ǰ ������
	
	Map<String, Object> selectGoodsAtt(Map<String,Object> map) throws Exception; // ��ǰ�Ӽ� ������
	
	void insertBasket(Map<String,Object> map, HttpServletRequest request) throws Exception; // ��ٱ��� �߰�
	
	void gumeListDelete(Map<String, Object> map) throws Exception; // ���Ÿ���Ʈ �ʱ�ȭ
	
	List<Map<String, Object>> selectBasketNo(Map<String, Object> map) throws Exception; // �����Ҷ� �������� ��������

	public Object cateList() throws Exception;
	
}
