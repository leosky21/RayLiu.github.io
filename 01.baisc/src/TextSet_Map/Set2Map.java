package TextSet_Map;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Set2Map<K,V> extends HashSet<UtilsSimpleEntry<K,V>> {



	/**
	 * 程序以HashSet 为父类派生了一个子类Set2Map。这将使得 Set2Map 扩展类完全被当成Map使用。
	 * 因此Set2Map 也提供了Map 集合的绝大部分方法
	 */
	private static final long serialVersionUID = 1L;
	// 获取该MAP中有多少的Key，Value 对
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}
	// 判断是否 包含 某个 key
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return super.contains(
				new UtilsSimpleEntry<K,V>(key,null));
	}
	// 判断是否包含 某个 value
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		for(UtilsSimpleEntry<K,V> se: this){
			if(se.getValue().equals(value)){
				return true;
			}
		}
		return false;
	}
	// 根据指定key 取出 对应的value
	public V get(Object key){
		for(UtilsSimpleEntry<K,V> se : this){
			if(se.getKey().equals(key)){
				return se.getValue();
			}
		}
		return null;
	}
	
	// 将指定的key value 放入集合中
	public V put(K key,V value){
		add(new UtilsSimpleEntry<K,V>(key,value));
		return value;
	}
	// 将另一个Map 的key-value对 放入该Map中
	public void putAll(Map<? extends K,? extends V> m){
		for(K key:m.keySet()){
			add(new UtilsSimpleEntry<K,V>
							(key , m.get(key)));
		}
	}
	// 根据指定的key删除指定的key-value对
	public V removeEntry(Object o) {
		for(Iterator<UtilsSimpleEntry<K,V>> it = this.iterator(); it.hasNext();){
			UtilsSimpleEntry<K,V> en = (UtilsSimpleEntry<K,V>) it.next();
			if(en.getKey().equals(o)){
				V v = en.getValue();
				it.remove();
				return v;
			}
		}
		
		return null;
	}
	// 清空 所有key-value对
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}

	
	
}
