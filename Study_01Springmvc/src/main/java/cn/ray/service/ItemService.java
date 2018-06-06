package cn.ray.service;

import java.util.List;

import cn.ray.pojo.Items;

public interface ItemService {
    List<Items> getItemList();
    Items getItemById(int id);
}