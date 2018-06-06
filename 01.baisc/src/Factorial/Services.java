package Factorial;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Services{
	private Services(){}	// prevents instantiation 

	// maps service names to services
	private static final Map<String ,Provider> providers =
			  new ConcurrentHashMap<String,Provider> ();
	public  static final String DEFAULT_PROVIDER_NAME = "<def>";
	// provider registeration API
	public static void registerDefaultProvider(Provider p){
		registerDefaultProvider(DEFAULT_PROVIDER_NAME,p);
	}
	public static  void registerDefaultProvider(String name ,Provider p){
		providers.put(name,p);
	}

	//Service access APi
	 public static Service newInstance(){
	 	return  newInstance(DEFAULT_PROVIDER_NAME);
	 }
	 public static Service newInstance(String name){
	 	Provider p = providers.get(name);
	 	if(p==null)
	 			throw new IllegalArgumentException(
	 				"no Provider registered with name"+name);
	 		return p.newService();
	 }
}