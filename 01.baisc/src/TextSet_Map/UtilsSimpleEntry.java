package TextSet_Map;

import java.util.Map;



public class UtilsSimpleEntry<K,V> implements Map.Entry<K,V> ,java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6795261173353724579L;
	/**
	 * 为了把set扩展成Map，新增定义一个UtilsSimpleEntry，本类代表key-values 对；
	 * 		当set 集合的集合元素都是UtilsSimpleEntry 的对象的时候，该集合就能被当成Map使用；
	 */
	private final K key;
	private V value;
	
	public UtilsSimpleEntry(K key,V value){
		this.key = key;
		this.value = value;
	}
	public UtilsSimpleEntry(Map.Entry<? extends K,? extends V> entry){
		this.key = getKey();
		this.value = getValue();
	}
	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V setValue(V value) {
		// TODO Auto-generated method stub
		return null;
	}
	// 根据key 计算hascode
	@Override
	public int hashCode() {
		
		return key == null ? 0 : key.hashCode();
	}
	// 根据key 比较两个UtilsSimpleEntry是否相等
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() == obj.getClass()){
			@SuppressWarnings("rawtypes")
			UtilsSimpleEntry use= (UtilsSimpleEntry) obj;
			return use.getKey().equals(getKey());
		}
		return false;
	}
	public String  toString (){
		return key + "=" +value;
	}	
}









