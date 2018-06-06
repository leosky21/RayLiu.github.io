package cn.hhit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Controller
@RequestMapping("/box")
public class MedBoxController {
	@Resource
	private MedBoxService medBoxService;

	@RequestMapping(value = "/BoxControl.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> test() {
		Map<String, Object> map = new HashMap<String, Object>();
		resultTest resultTest = new resultTest();
		resultTest.setStatusA("1");
		resultTest.setStatusB("1");
		resultTest.setStatusC("1");
		resultTest.setStatusD("0");
		resultTest.setStatusE("1");
		resultTest.setStatusF("1");
		resultTest.setStatusG("2");
		resultTest.setStatusH("2");
		map.put("results", resultTest);

		return map;
	}
}

class resultTest{
	@JsonProperty("statusA")
	private String statusA;@JsonProperty("statusB")
	private String statusB;@JsonProperty("statusC")
	private String statusC;@JsonProperty("statusD")
	private String statusD;@JsonProperty("statusE")
	private String statusE;@JsonProperty("statusF")
	private String statusF;@JsonProperty("statusG")
	private String statusG;@JsonProperty("statusH")
	private String statusH;
	
	@JsonIgnoreProperties
	public String getStatusA() {
		return statusA;
	}@JsonIgnoreProperties
	public void setStatusA(String statusA) {
		this.statusA = statusA;
	}@JsonIgnoreProperties
	public String getStatusB() {
		return statusB;
	}@JsonIgnoreProperties
	public void setStatusB(String statusB) {
		this.statusB = statusB;
	}@JsonIgnoreProperties
	public String getStatusC() {
		return statusC;
	}@JsonIgnoreProperties
	public void setStatusC(String statusC) {
		this.statusC = statusC;
	}@JsonIgnoreProperties
	public String getStatusD() {
		return statusD;
	}@JsonIgnoreProperties
	public void setStatusD(String statusD) {
		this.statusD = statusD;
	}@JsonIgnoreProperties
	public String getStatusE() {
		return statusE;
	}@JsonIgnoreProperties
	public void setStatusE(String statusE) {
		this.statusE = statusE;
	}@JsonIgnoreProperties
	public String getStatusF() {
		return statusF;
	}@JsonIgnoreProperties
	public void setStatusF(String statusF) {
		this.statusF = statusF;
	}@JsonIgnoreProperties
	public String getStatusG() {
		return statusG;
	}@JsonIgnoreProperties
	public void setStatusG(String statusG) {
		this.statusG = statusG;
	}@JsonIgnoreProperties
	public String getStatusH() {
		return statusH;
	}@JsonIgnoreProperties
	public void setStatusH(String statusH) {
		this.statusH = statusH;
	}
	
}
