package com.sc.tradmaster.controller.excelFiles;

import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.house.impl.dto.HouseExportDTO;
import com.sc.tradmaster.service.projectcustomer.ProjectCustomerService;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.utils.ExcelHelper;
import com.sc.tradmaster.utils.ExcelType;
import com.sc.tradmaster.utils.JxlExcelHelper;

/**
 * 
 * Excel转成数据的控制器
 * 
 * @author Administrator
 *
 */
@Controller("excelFileController")
public class ExcelfileController extends BaseController {

	@Resource(name = "houseService")
	private HouseService houseService;

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "projectCustomerService")
	private ProjectCustomerService projectCustomerService;

	/**
	 * 
	 * 将房源的|Excel表录入数据库
	 * 
	 * @param file
	 */
	@ResponseBody
	@RequestMapping("/import_house_excel")
	public Map<String, String> importHouseExcel(MultipartFile file) {
		Map<String, String> map = new HashMap<>();
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		if (file != null) {
			String info = houseService.addHouseExcel(user,file);
			map.put("data", info);
		}
		return map;
	}

	/**
	 * 房源导出
	 * @throws Exception 
	 */
	//@ResponseBody
	@RequestMapping("/export_house_info")
	public void exportHouseToExcel(String type,String[] selectedHIds,HttpServletResponse response) throws Exception {
		// 获取session中的用户信息
		User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(u.getUserId());
		ExcelType excelType = ExcelType.valueOf(type);
		Map<String, String> enName = excelType.enName();
		Set<Map.Entry<String, String>> entries = enName.entrySet();
		List<HouseExportDTO> houses = houseService.findThisProjectHouse(user.getParentId(),selectedHIds);
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("house");
		HSSFRow row = sheet.createRow(0);
		int i = 0;
		for (Map.Entry<String, String> entry : entries) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(entry.getValue());
			i++;
		}
		int j = 1;
		for (HouseExportDTO house : houses) {
			HSSFRow houseRow = sheet.createRow(j);
			int k = 0;
			for (Map.Entry<String, String> entry : entries) {
				try {
					HSSFCell cell = houseRow.createCell(k);
					Class clazz = PropertyUtils.getPropertyType(house, entry.getKey());
					if (Double.class.equals(clazz) || Integer.class.equals(clazz) || Float.class.equals(clazz)) {
						cell.setCellValue(
								Double.valueOf(String.valueOf(PropertyUtils.getProperty(house, entry.getKey()))));
					} else {
						cell.setCellValue(String.valueOf(PropertyUtils.getProperty(house, entry.getKey())));
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					k++;
				}
			}
			j++;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		wb.write(out);
		byte[] bytes = out.toByteArray();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename=house.xls");
		response.setContentLength(bytes.length);
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	/**
	 * 
	 * 导入客户信息
	 * @param file
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_input_customer_excell")
	public Map<String, Object> importCustomerExcell(MultipartFile file, String projectId){
		Map<String, Object> map = new HashMap<>();
		
		if(file != null && file.getSize() > 0){
			Map<String, Object> info = projectCustomerService.addCustomerExcell(file, projectId);
			map.put("data", info);
		}
		
		return map;
	}
	
}
