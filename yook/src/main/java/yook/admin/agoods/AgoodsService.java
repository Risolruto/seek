package yook.admin.agoods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import yook.common.dao.Pagination;

public interface AgoodsService {
	
	void deleteAdminGoods(Map<String, Object> map) throws Exception;
	List<Map<String, Object>> openAgoodsList(Map<String, Object> map)throws Exception;
	
	void updateGoods(Map<String, Object> map, HttpServletRequest request) throws Exception; // ��ǰ ����
	
	public List<Map<String, Object>> agoodsList(Pagination pagi) throws Exception;
	
	public int listCount() throws Exception;
}
