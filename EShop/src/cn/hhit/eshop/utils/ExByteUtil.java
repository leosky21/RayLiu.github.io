package cn.hhit.eshop.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.codec.binary.Base64;

import cn.hhit.eshop.model.Product;
import cn.hhit.eshop.model.ProductBean;
//import cn.hhit.eshop.model.ProductBean;
import cn.hhit.eshop.model.ProductTest;

public class ExByteUtil {
	/**
	 * 把Blob类型转换为byte数组类型
	 * 
	 * @param blob
	 * @return
	 */
	public static byte[] blobToBytes(Blob blob) {
		BufferedInputStream is = null;
		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				return null;
			}
		}
	}

	public static byte[] InputStreamToByte(InputStream is) throws IOException {

		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();

		return imgdata;
	}

	/**
	 * TODO byte[]转String，编码与乱码问题，以及Base64编码
	 */
	public static List<ProductBean> ProductFormBean2(List<Product> productList) throws UnsupportedEncodingException {
		// 循环遍历,填充formBean
		ListIterator<Product> it = productList.listIterator();
		List<ProductBean> pbList = new ArrayList<ProductBean>();
		Product p;
		while (it.hasNext()) {
			p = it.next();
			pbList.add(new ProductBean(p.getId(), p.getCategory(), p.getName(), p.getPrice(),
					ExByteUtil.blobToBytes(p.getPic()), p.getRemark(), p.getXremark(), p.getDate(), p.getCommend(),
					p.getOpen()));
		}
		return pbList;
	}

	public static List<ProductTest> ProductFormBean(List<Product> productList) throws UnsupportedEncodingException {
		// 循环遍历,填充formBean
		ListIterator<Product> it = productList.listIterator();
		List<ProductTest> pbList = new ArrayList<ProductTest>();
		Product p;
		/**
		 * TODO byte[]转String，编码与乱码问题，以及Base64编码
		 */
		while (it.hasNext()) {
			p = it.next();
			System.out.println("ExByteUtil:::" + Base64.encodeBase64String(ExByteUtil.blobToBytes(p.getPic())));

			pbList.add(new ProductTest(p.getId(), p.getCategory(), p.getName(), p.getPrice(),
					// Convert.ToBase64String(ExByteUtil.blobToBytes(p.getPic())),
					Base64.encodeBase64String(ExByteUtil.blobToBytes(p.getPic())), p.getRemark(), p.getXremark(),
					p.getDate(), p.getCommend(), p.getOpen()));
		}
		return pbList;
	}

	/**
	 * @param p product
	 * @return ProductTest blob 解码转换成BaseString
	 * @throws UnsupportedEncodingException
	 */
	public static ProductTest ProductFormBean(Product p) throws UnsupportedEncodingException {
		/**
		 * TODO byte[]转String，编码与乱码问题，以及Base64编码
		 */
//		System.out.println("ExByteUtil:::" + Base64.encodeBase64String(ExByteUtil.blobToBytes(p.getPic())));

		return new ProductTest(p.getId(), null, p.getName(), p.getPrice(),
				Base64.encodeBase64String(ExByteUtil.blobToBytes(p.getPic())), p.getRemark(), p.getXremark(),
				p.getDate(), p.getCommend(), p.getOpen());
	}

	
}
