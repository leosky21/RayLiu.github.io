package smallTools;

public class AutoExpandArray {
	
	    private Object[] data ;
	    private int index=0;
	    public AutoExpandArray(int size){ //创建链表大小
	        data=new Object[10];
	    }
	    private void expand(){ //扩充
	        Object[] data2=new Object[data.length*2];
	        System.arraycopy(data,0,data2,0,data.length);
	        data=data2;
	    }
	    public void add(Object o){ //添加
	        if (data.length==index) expand();
	        data[index]=o;
	        index++;
	    }
	    public void add(int pos,Object o){ //在指定位置添加
	        if (data.length==index) expand();
	        for(int i=index;i>pos;i--){
	            data[i]=data[i-1];
	        }
	        data[pos]=o;
	        index++;
	    }
	     
	    public void change(int pos,Object o){ //替换
	        data[pos]=o;
	    }
	     
	    public Object remove(int pos){  //删除
	        Object o=data[pos];
	        index--;
	        for(int i=pos;i<index;i++){
	            data[i]=data[i+1];
	        }
	        return o;
	    }
	    public int size(){  //返回大小
	        return index;
	    }
	    public Object get(int pos){ //返回指定位置值
	        return data[pos];
	    }
	    public void clear(){   //清除 
	        index=0;
	    }
	    public boolean isEmpty(){ //置空
	        return index==0;
	    }
	    public Object[] toArray(){ //返回数组 
	        return data; 
	    }
	
}
