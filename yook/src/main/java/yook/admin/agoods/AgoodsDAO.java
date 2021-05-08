package yook.admin.agoods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import yook.common.dao.AbstractDAO;
import yook.common.dao.Pagination;

@Repository("agoodsDAO")
public class AgoodsDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> openAgoodsList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) openBoardList("aGoods.aGoodsList", map);
	}

	public void deleteAdminGoods(Map<String, Object> map) throws Exception {
		delete("aGoods.deleteAdminGoods", map);
	}

	public void deleteAdminGoodsATT(Map<String, Object> map) throws Exception {
		delete("aGoods.deleteAdminGoodsATT", map);
	}

	public void updateGoods(Map<String, Object> map) throws Exception { // ��ǰ ����
		update("aGoods.updateGoods", map);
	}

	public void attributeDelete(Map<String, Object> map) throws Exception { // ��ǰ�ɼ� ����
		insert("aGoods.attributeDelete", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> agoodsList(Pagination pagi) throws Exception {
		return (List<Map<String, Object>>) openBoardList("aGoods.listPage", pagi);
	}

	public int listCount() throws Exception {
		return (Integer) selectOne("aGoods.listCount");
	}

}
