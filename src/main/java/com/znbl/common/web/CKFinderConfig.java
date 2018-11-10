/**
 * Copyright &copy; 2012-2014 cnjson.attendance All rights reserved.
 */
package com.znbl.common.web;

import com.ckfinder.connector.configuration.Configuration;
import com.ckfinder.connector.data.AccessControlLevel;
import com.ckfinder.connector.utils.AccessControlUtil;
import com.znbl.common.config.Global;
import com.znbl.common.utils.FileUtils;
import com.znbl.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.znbl.modules.sys.utils.UserUtils;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

/**
 * CKFinder配置
 * @author Gray
 * @version 2014-06-25
 */
public class CKFinderConfig extends Configuration {

	public CKFinderConfig(ServletConfig servletConfig) {
        super(servletConfig);  
    }
	
	@Override
    protected Configuration createConfigurationInstance() {
		Principal principal = (Principal) UserUtils.getPrincipal();
		if (principal == null){
			return new CKFinderConfig(this.servletConf);
		}
		boolean isView = true;//UserUtils.getSubject().isPermitted("cms:ckfinder:view");
		boolean isUpload = true;//UserUtils.getSubject().isPermitted("cms:ckfinder:upload");
		boolean isEdit = true;//UserUtils.getSubject().isPermitted("cms:ckfinder:edit");
		AccessControlLevel alc = this.getAccessConrolLevels().get(0);
		alc.setFolderView(isView);
		alc.setFolderCreate(isEdit);
		alc.setFolderRename(isEdit);
		alc.setFolderDelete(isEdit);
		alc.setFileView(isView);
		alc.setFileUpload(isUpload);
		alc.setFileRename(isEdit);
		alc.setFileDelete(isEdit);

		AccessControlUtil.getInstance(this).loadACLConfig();
		try {
			this.baseURL = FileUtils.path(Servlets.getRequest().getContextPath() + Global.USERFILES_BASE_URL);
			this.baseDir = "/" + FileUtils.path(Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new CKFinderConfig(this.servletConf);
    }

    @Override  
    public boolean checkAuthentication(final HttpServletRequest request) {
        return UserUtils.getPrincipal()!=null;
    }

}
