package com.sc.tradmaster.controller.machine;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.tradmaster.bean.SignRecords;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.bean.VisitRecords;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.agent.AgentVisitRecordService;
import com.sc.tradmaster.service.agent.impl.visitDTO.Customer;
import com.sc.tradmaster.service.user.UserService;
import com.sc.tradmaster.service.visitRecords.VisitRecordsService;
import com.sc.tradmaster.utils.Page;

/**
 * 2017-03-02
 * @author grl
 *
 */
@Controller
public class MachineController extends BaseController {
	
	@Resource(name = "agentVisitRecordService")
	private AgentVisitRecordService agentVisitRecordService;

	@Resource(name = "visitRecordsService")
	private VisitRecordsService visitRecordsService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 立屏机未登录访问时跳转接口
	 */
	@ResponseBody
	@RequestMapping("/machine_error_out_info")
	public void machineErrorOutInfo(){
		Map map = new HashMap<>();
		map.put("ReturnCode", 401);
		this.outMachineObjectString(map, null);
	}
	
	/**
	 * 获取置业顾问列表
	 */
	@ResponseBody
	@RequestMapping("/project.getAgentList")
	public void getAllAgent(String projectId) {
		List list = agentVisitRecordService.findAgents(projectId);
		Map map = new HashMap();
		String userListStr = this.outMachineListString(list);

		map.put("returnCode", 200);
		map.put("users", userListStr);
		this.outMachineObjectString(map, null);
	}

	/**
	 * 签到签退
	 * @param type
	 * @param projectId
	 * @param userId
	 * @param time
	 */
	@ResponseBody
	@RequestMapping("/project.sign")
	public void signInOrSignOutFun(Integer type,String projectId,String userId,String time) {
		Map map = new HashMap<>();
		// 获取session中的用户信息
		//User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		User user = userService.findById(userId);
		//User user = new User();
		//user.setUserId(userId);
		agentVisitRecordService.addSignInAndUpdateSignOut(user,type,projectId,userId,time);
		map.put("returncode", 200);
		this.outMachineObjectString(map, null);
	}

	/**
	 * 获取签到签退记录
	 * 
	 * @param version
	 * @param os
	 * @param projectId
	 * @param checkTime
	 */
	@ResponseBody
	@RequestMapping("/project.getSignRecords")
	public void getSignAndOutRecord(String version, String os, String projectId, String checkTime) {
		Map map = new HashMap<>();
		// 获取session中的用户信息
		//User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		//User user = userService.findById(u.getUserId());
		List<SignRecords> signInAndOutList = agentVisitRecordService.findAllSignAndOutRecordList(null, projectId, checkTime);
		if (signInAndOutList != null) {
			map.put("returnCode", 200);
			map.put("records", this.outMachineListString(signInAndOutList));
			this.outMachineObjectString(map, null);
		}
	}

	/**
	 * 上传到访记录
	 * 
	 * @param vrs
	 */
	@ResponseBody
	@RequestMapping("/project.uploadVisitReocrd")
	public void uploadVisitReocrd(String visitToken ,VisitRecords vrs) {
		Map map = new HashMap<>();
		// 获取session中的用户信息
		//User u = (User) this.request.getSession().getAttribute("userInfo");
		// 获取持久化用户对象
		//User user = userService.findById(u.getUserId());
		Integer visitNo = agentVisitRecordService.addOrUpdateVisitReocrdFromExcel(vrs);
		map.put("returnCode", 200);
		map.put("visitNo", visitNo);
		this.outMachineObjectString(map, null);
	}
	
	/**
	 * 获取客户到访记录
	 */
	@RequestMapping("/project.getCustomer")
	public void getCustomerVisitRecord(String projectId, String phone) {
		
		Customer c = agentVisitRecordService.findCustomerVisitRecord(projectId, phone);
		Map<String, Object> map = new HashMap<>();
		if (c != null) {
			map.put("returnCode", 200);
			map.put("customer", c);
		} else {
			map.put("returnCode", 400);
			map.put("customer", "该客户没有到访记录");
		}
		this.outMachineObjectString(map, null);
	}
	
	/**
	 * 下载到访记录
	 * @throws ParseException 
	 */
	@RequestMapping(value="/project.getVisitRecords",produces = {"application/vnd.ms-excel;charset=UTF-8"})
	public void downloadVisit(String projectId, String arriveTime,HttpServletRequest request,HttpServletResponse response, Integer page,Integer pageSize) throws ParseException {

		Page p = new Page();
		p.setStart(page*pageSize);
		p.setLimit(pageSize);
		
		String fileName = "excel文件";
		visitRecordsService.findVisit(projectId, arriveTime,p);
		
		this.outMachinePageString(p);
		
			/*String[] titles = new String[]{"记录号","接访的置业顾问编码","案场编码","到访状态","到访人数","客户姓名","客户电话","备案号","到访时间","接待时间","送别时间",
					"指定接待的置业顾问编码","客户编码","预留的标签字段","预留的说明字段","该记录的填写状态"};
			String[] fieldNames = new String[]{"visitNo","userId","projectId","visitStatus","customerCount","customerName","phone","recordNo",
					"arriveTime","receptTime","leaveTime","appointUserId","customerId","tags","description","writeState"};
			
			try {	
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				try {
					File file1 = new File("E:\\JXL2003.xls");
					ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
					eh1.writeExcel(VisitRecords.class, vList);
					eh1.writeExcel(VisitRecords.class, vList, fieldNames, titles);
					
					List<VisitRecords> vList2 = eh1.readExcel(VisitRecords.class, fieldNames, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				byte[] content = os.toByteArray();
				InputStream is = new ByteArrayInputStream(content);
				// 设置response参数，可以打开下载页面
	            response.reset();
	            response.setContentType("application/vnd.ms-excel;charset=utf-8");
	            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	            ServletOutputStream out = response.getOutputStream();
	            BufferedInputStream bis = null;
	            BufferedOutputStream bos = null;
	            try {
	                bis = new BufferedInputStream(is);
	                bos = new BufferedOutputStream(out);
	                byte[] buff = new byte[2048];
	                int bytesRead;
	                // Simple read/write loop.
	                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                    bos.write(buff, 0, bytesRead);
	                }
	            } catch (final IOException e) {
	                throw e;
	            } finally {
	                if (bis != null)
	                    bis.close();
	                if (bos != null)
	                    bos.close();
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			
	//		Map map = new HashMap<>();
	//		map.put("ReturnCode", 200);
		//	map.put("Visitreocds", this.outMachineListString(vList));
			
	 }
	
	
}
