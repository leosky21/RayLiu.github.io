package cn.ray.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.ray.pojo.Items;

@Service
public class ItemServiceImpl implements ItemService{

	@Override
	public List<Items> getItemList() {
		return null;
	}

	@Override
	public Items getItemById(int id) {
//		// 根据商品id查询商品信息
//	    Items items = itemsMapper.selectByPrimaryKey(id);
//	    return items;
		return null;
	}

}
