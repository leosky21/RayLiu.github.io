package cn.hhit.eshop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.hhit.eshop.model.Category;
import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.ProductBean;
import cn.hhit.eshop.model.ProductTest;
import cn.hhit.eshop.service.CategoryService;
import cn.hhit.eshop.service.ProductService;
import cn.hhit.eshop.utils.ExByteUtil;

@Controller
@Scope("prototype")
@RequestMapping("/product")
public class ProductController {

	protected Map<String, Object> pageMap = null;
	List<ProductBean> pbList;
	List<ProductTest> pbListTest;
//	private Map<String, List<List<ProductTest>>> listTest = new HashMap<String, List<List<ProductTest>>>();
	private Map<String, Object> listTest = new HashMap<String, Object>();
	@Resource
	private ProductService productService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ServletContext application;
		
	@RequestMapping(value = "/m/QueryInitProduct.action", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> InitProduct(){
		List<Category> cs = categoryService.queryByHot(true);
		System.out.println("cs.size()::"+cs.size());
		Map<String,Object> listArray = new  HashMap<String ,Object>();
		int i=0;
		for (Category c : cs) {
			// 按类别查询热点商品
			List<Product> lst = productService.queryByCategoryId(c.getId());
			System.out.println(lst.size());
			List<ProductTest> list2;
			try {
				list2 = ExByteUtil.ProductFormBean(lst);
				/**
				 * TODO 在index jsp页面中将pic 的value 取出来,而不是取出对象
				 */

				listArray.put(i+"",list2);// 将不同category中的热点product装进来
				
			}catch (SecurityException e) {
				
				e.printStackTrace();
			}catch (UnsupportedEncodingException e1) {
				System.out.println("InitProductsTimeTask  转换成utf-8 失败");
				e1.printStackTrace();
			}
			i++;
		}
		listTest.put("categories", listArray);
		return listTest;
	}
	
	@RequestMapping(value = "/queryJoinCategory.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryJoinCategory(Product product, Integer page, Integer rows) throws UnsupportedEncodingException {
		// 用来存储分页的数据
		pageMap = new HashMap<String, Object>();
		// 根据关键字和分页的参数查询相应的数据
		List<Product> productList = productService.queryJoinCategory(product.getName(), page, rows);
//		// 循环遍历,填充formBean
//		ListIterator<Product> it = productList.listIterator();
//		pbList = new ArrayList<ProductBean>();
//		Product p;
//		while (it.hasNext()) {
//			p = it.next();
//			pbList.add(new ProductBean(p.getId(), p.getCategory(), p.getName(), p.getPrice(),
//					ExByteUtil.blobToBytes(p.getPic()), p.getRemark(), p.getXremark(), p.getDate(), p.getCommend(),
//					p.getOpen()));
//		}
		// 循环遍历,填充formBean
		pbList = ExByteUtil.ProductFormBean2(productList);
		//System.out.println(pbList.get(0).getPic());
		pageMap.put("rows", pbList); // 存储为JSON格式
		// 根据关键字查询总记录数
		Long total = productService.getCount(product.getName());
		// System.out.println(total);
		pageMap.put("total", total); // 存储为JSON格式
		return pageMap;
	}

	@RequestMapping("/deleteByIds.action")
	@ResponseBody
	public String deleteByIds(String ids) {
		System.out.println(ids);
		productService.deleteByIds(ids);
		// 如果删除成功就会往下执行，我们将"true"以流的形式传给前台
		try {
			return new String("true".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {

			try {
				return new String("error".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException();
			}
		}
	}
	
	@RequestMapping("/get.action")
	public String get(@RequestParam("id")String  pid,HttpServletRequest request) {
		Product product= productService.productJoinCategory(Integer.parseInt(pid));
		try {
			List<ProductTest> list = queryJoinProdcutCategory(product);
			request.setAttribute("product", ExByteUtil.ProductFormBean(product));
			request.setAttribute("ExpandProduct",list);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return "pages/user/detail";
	}	
	public List<ProductTest> queryJoinProdcutCategory(Product product) throws UnsupportedEncodingException {
		// 根据关键字和分页的参数查询相应的数据
		List<Product> productList = productService.queryProductByCategory(product.getCategory());
		// 循环遍历,填充formBean
		List<ProductTest> pbList = ExByteUtil.ProductFormBean(productList);
		//System.out.println(pbList.get(0).getPic());
//		pageMap.put("rows", pbList); // 存储为JSON格式
		return pbList;
	}
	
	@RequestMapping("/queryJoinProdcutCategory.action")
	@ResponseBody
	public List<ProductTest> queryJoinProdcutCategory() throws UnsupportedEncodingException {
		Product product = new Product();
		Category category = new Category();
		category.setType(new String("运动饮料".getBytes("UTF-8")));
		product.setCategory(category);
		// 根据关键字和分页的参数查询相应的数据
		List<Product> productList = productService.queryProductByCategory(product.getCategory());
		// 循环遍历,填充formBean
		List<ProductTest> pbList = ExByteUtil.ProductFormBean(productList);
		//System.out.println(pbList.get(0).getPic());
//		pageMap.put("rows", pbList); // 存储为JSON格式
		return pbList;
	}
	@RequestMapping("/queryTestProductTest.action")
	@ResponseBody
	public ProductTest queryJoinProdcut() throws UnsupportedEncodingException {
		Product product = productService.get(1);
		ProductTest pbList = ExByteUtil.ProductFormBean(product);
		return pbList;
	}
	@RequestMapping("/query.action")
	public String query(Product product) {
		System.out.println("----query----" + product);
		System.out.println(productService);
		// categoryService.save(category);
		return "product/query";
	}

	
	@RequestMapping(value = "/save.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public void save(
			@RequestParam("pic") CommonsMultipartFile pic,
			HttpServletRequest request) throws Exception {
		Map<String, String[]> map = request.getParameterMap();
		ProductBean product = new ProductBean();
		
		//product.setId(Integer.parseUnsignedInt(map.get("id")[0]));
		product.setCategory(new Category(Integer.parseUnsignedInt(map.get("category.id")[0])));
		product.setName(map.get("name")[0]);
		product.setCommend(Boolean.parseBoolean(map.get("commend")[0]));
		product.setPrice(Double.valueOf(map.get("price")[0]));
		product.setRemark(map.get("remark")[0]);
		product.setXremark(map.get("xremark")[0]);
		product.setDate(new Timestamp(System.currentTimeMillis()));
		product.setOpen(Boolean.parseBoolean(map.get("open")[0]));
		
		try {
			// 获得文件输入流
			product.setPic(
					ExByteUtil.InputStreamToByte(pic.getInputStream()));
			productService.save(product);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}

	// TODO 文件传过来是 文件名,非文件流
	@RequestMapping(value = "/update.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	// @MultipartConfig
	// alter table product modify pic blob ;/*@RequestParam("pic")
	// CommonsMultipartFile pic,HttpServletRequest request,*/
	public void update(/* ProductBean product, */@RequestParam("pic") CommonsMultipartFile pic,
			HttpServletRequest request) {

		/** 第三种方法 */
		// @SuppressWarnings("unused")
		// product.setDate(new Timestamp(System.currentTimeMillis()));
		// try {
		// productService.update(product);
		// } catch (IOException e) {
		// 
		// e.printStackTrace();
		// }
		// File newFile=new
		// File("/Users/liujun/Workspaces/myEclipse-Maven/.metadata/.me_tcat85/webapps/EShop/WEB-INF/classes/"+pic.getOriginalFilename());

		Map<String, String[]> map = request.getParameterMap();
		ProductBean product = new ProductBean();
		//
		product.setId(Integer.parseUnsignedInt(map.get("id")[0]));
		product.setCategory(new Category(Integer.parseUnsignedInt(map.get("category.id")[0])));
		product.setName(map.get("name")[0]);
		product.setCommend(Boolean.parseBoolean(map.get("commend")[0]));
		product.setPrice(Double.valueOf(map.get("price")[0]));
		product.setRemark(map.get("remark")[0]);
		product.setXremark(map.get("xremark")[0]);
		product.setDate(new Timestamp(System.currentTimeMillis()));
		product.setOpen(Boolean.parseBoolean(map.get("open")[0]));
		try {
			// 获得文件输入流
			product.setPic(
					ExByteUtil.InputStreamToByte(pic.getInputStream()));

			//			InputStream input = pic.getInputStream();
//			while (input.available() != 0)
//				System.out.println(input.read());

			// System.out.println(newFile.canRead()+"--"+newFile.getAbsolutePath());//
			// /Applications/MyEclipse 2017 CI/MyEclipse 2017
			// CI.app/Contents/MacOS/pic05.jpg
			// System.out.println(newFile.length()+"--"+ pic.getInputStream());
			// long endTime=System.currentTimeMillis();
			// System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");

			productService.update(product);
		} catch (IOException e) {

			e.printStackTrace();
		}
		// productService.update(product);
	}

}
