package cn.hhit.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hhit.pojo.Medicine;
import cn.hhit.service.MedicineService;
import cn.hhit.utils.Msgutil;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
	@Resource
	private MedicineService medicineService;

	private Map<String, Object> pageMap;

	@RequestMapping(value = "/queryMedicine.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryMedicine(Medicine medicine, Integer page, Integer rows)
			throws UnsupportedEncodingException {
		// 用来存储分页的数据
		pageMap = new HashMap<String, Object>();
		// 根据关键字和分页的参数查询相应的数据
		List<Medicine> productList = medicineService.queryMedicine(medicine.getMedicinename(), page, rows);
		//List<Medicine> productList = medicineService.query();
		pageMap.put("rows", productList); // 存储为JSON格式
		// 根据关键字查询总记录数
		Long total = medicineService.getCount(medicine.getMedicinename());
		// System.out.println(total);
		pageMap.put("total", total); // 存储为JSON格式
		return pageMap;
	}

	@RequestMapping("/deleteByLicenseNumber.action")
	@ResponseBody
	public String deleteByLicenseNumber(@RequestParam("licensenumbers") String licensenumber) {
		System.out.println(licensenumber);
		medicineService.deleteByLicenseNumber(licensenumber);
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
	@ResponseBody
	public Medicine get(@RequestParam("licensenumber") String licenseNumber) {
		Map<String, Object> map = new HashMap<String, Object>();	
		return medicineService.findByLicenseNumber(licenseNumber);
	}

	@RequestMapping("/query.action")
	public String query(Medicine medicine) {
		return "medicine/query";
	}

	@RequestMapping(value = "/save.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public void save(Medicine medicine)  {
		medicineService.save(medicine);
	}

	@RequestMapping(value = "/update.action", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public Map<String, Object> update(Medicine medicine) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(medicine!=null){
			medicineService.update(medicine);
			map.put(Msgutil.keyResult.getMSG_Key(), 0);	
		}else{
			map.put(Msgutil.keyResult.getMSG_Key(), 1);
			map.put(Msgutil.errorInfo.getMSG_Key(), Msgutil.InfoIsNull.getMSG_Key());
		}
		return map;
	}	
}
