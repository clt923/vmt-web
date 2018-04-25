package com.clt.framework.vmt.service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;
import com.clt.framework.vmt.constants.CommonConstants;
import com.clt.framework.vmt.utilities.CommonUtil;
import com.clt.tos.external.vmt.wp.control.VmtMachineControl;
import com.clt.tos.external.vmt.wp.control.VmtUserControl;
import com.clt.tos.external.vmt.wp.model.VmtLogin;
import com.clt.tos.external.vmt.wp.model.VmtMachine;
import com.clt.tos.external.vmt.wp.model.VmtResult;

public class HessianUserService {
	private VmtUserControl userControl;
	private VmtMachineControl machineControl;
	private static HessianProxyFactory _factory;
	public HessianUserService(String ipHessian) throws MalformedURLException{
		_factory = new HessianProxyFactory();
		userControl= (VmtUserControl)_factory.create(VmtUserControl.class,ipHessian + CommonConstants.URL_HESSIAN_USER); 
		machineControl=(VmtMachineControl)_factory.create(VmtMachineControl.class,ipHessian + CommonConstants.URL_HESSIAN_VMTMACHINECONTROL); 
	}
	
	public List<String> getUserAccessRole(String userId) {
		VmtLogin userGroup = userControl.getUserAccessRole(userId);
        if(!CommonUtil.isNull(userGroup)){
        	if(!CommonUtil.isNull(userGroup.getUsrGrp())){
        		return userGroup.getUsrGrp();
        	}
        }
        return new ArrayList<String>();
   }
	
	public VmtLogin setLogin4Machine(String userId,String password,String machineId,String userGroup,String trailerNo) {
			
		VmtLogin userLogin=new VmtLogin();
		userLogin.setUsrId(userId);
		userLogin.setUsrPasswd(password);
		List<String> lstGroup=new ArrayList<String>();
		lstGroup.add(userGroup);
		userLogin.setUsrGrp(lstGroup);
		userLogin.setMchnId(machineId);
		userLogin.setTrailNo("CT"+trailerNo);
		VmtLogin isLogin=userControl.setLogin4MachineWithExt(userLogin);
		
		return isLogin;
	}
	
	public boolean setLogout4Machine(String userId,String password,String machineId) {
		VmtResult result=userControl.setLogout4Machine(machineId, "YT", "", "", userId);
		if(result.getResultObj().equals("S1")){
			return true;
		}else{
			return false;
		}
	}
	
	public VmtResult setLogout4Machine(String machineId,String mchnTp,String blck,String bay,String usrId) {
		VmtResult result=machineControl.setLogout4Machine(machineId, mchnTp, blck, bay, usrId);
		return result;
	}
	
	public boolean setMachineStatus(String machineId,boolean status){
		try {
			VmtMachine vmtMachine=new VmtMachine();
			vmtMachine.setMchnId(machineId);
			vmtMachine.setChkOn(status);
			//machineControl.setMachineStatusChanged(vmtMachine);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
