/**
 * sms client wrapper
 */
package com.mg.api.core.helper;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.mg.api.common.util.StringUtil;
import com.mg.api.core.configuration.AliConfiguration;

public class AliSmsClientWrapper {

	/**
	 * send message
	 * @param config
	 * @param receiver
	 * @param tplCode
	 * @param param
	 * @throws ClientException
	 */
	public void send(AliConfiguration config, String receiver, String tplCode, String param) throws ClientException {
		IClientProfile profile = DefaultProfile.getProfile(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
		
		DefaultProfile.addEndpoint(config.getEndpoint(), config.getEndpoint(), "Sms", "sms.aliyuncs.com");
		
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendSmsRequest request = new SingleSendSmsRequest();
		request.setSignName(config.getSmsSignName());
		request.setTemplateCode(tplCode);
		if(!StringUtil.isEmpty(param)) {
			request.setParamString(param);
		}
		request.setRecNum(receiver);
		client.getAcsResponse(request);
	}
}