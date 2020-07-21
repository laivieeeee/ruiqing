package com.ruiqing.jmx;

import com.alibaba.fastjson.JSON;
import com.ruiqing.common.email.Email;
import com.ruiqing.common.email.EmailUtil;
import com.ruiqing.common.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统性能监控任务
 * 
 * @author HUANGLIFEI5
 *
 */
public class JmxJob {
	/*private static Logger _log = LoggerFactory.getLogger(JmxJob.class);
	private static int SECONDS = 15;
	private SysValueSetItemService sysValueSetItemService;
	private Email email;
	private Map<String, Object> mapRatio;

	public void notes() {
		try {
			Map<String, Object> map = JmxUtils.getJvmInfo();
			JmxUtils.jvmName = map.get("runtimeName").toString();

			RedisUtil.set(JmxUtils.jvmName, "true", SECONDS);

			List<Map<String, Object>> newList = new ArrayList<>();
			String rList = null;
			if (null != RedisUtil.getStr("RUNTIMENAME")) {
				rList = RedisUtil.getStr("RUNTIMENAME");
			}
			if (null != rList) {
				for (Map<String, Object> s : JSON.parseArray(rList, HashMap.class)) {
					if (StringUtils.equals(JmxUtils.jvmName, s.get("runtimeName").toString()))
						continue;
					if (StringUtils.isNotBlank(RedisUtil.getStr(s.get("runtimeName").toString())))
						newList.add(s);
				}
			}
			newList.add(map);
			RedisUtil.set("RUNTIMENAME", JSON.toJSONString(newList), SECONDS);

			map = JmxUtils.getClassLoading();
			RedisUtil.set(JmxUtils.jvmName + "@CLASSLOADING", JSON.toJSONString(map), SECONDS);

			List<Map<String, Object>> list = JmxUtils.getMemoryPool();
			RedisUtil.set(JmxUtils.jvmName + "@MEMORYPOOL", JSON.toJSONString(list), SECONDS);

			map = JmxUtils.getThread();
			RedisUtil.set(JmxUtils.jvmName + "@THREAD", JSON.toJSONString(map), SECONDS);

			map = JmxUtils.getSystem();
			RedisUtil.set(JmxUtils.jvmName + "@SYSTEM", JSON.toJSONString(map), SECONDS);

			list = JmxUtils.getGC();
			RedisUtil.set(JmxUtils.jvmName + "@GC", JSON.toJSONString(list), SECONDS);

//			if (null == sysValueSetItemService)
//				sysValueSetItemService = (SysValueSetItemService) DubboUtil.refer(SysValueSetItemService.class);
			if (null != sysValueSetItemService) {
				List<ValueSetItemDTO> items = sysValueSetItemService.getEffectiveByVsCode("zh-CN", "JMX_ALERT");
				email = new Email();
				mapRatio = new HashMap<>();
				if (null == items || items.size() == 0) {
					_log.error("请检查资源监控警告参数设置(JMX_ALERT):");
				} else {
					for (ValueSetItemDTO t : items) {
						if (StringUtils.isBlank(t.getVsiValue()))
							continue;
						switch (t.getVsiKey()) {
							case "CPU_RATIO":
							if (Double.parseDouble(t.getVsiValue()) <= Double.parseDouble(map.get("systemCpuLoad").toString()) * 100)
								mapRatio.put(t.getVsiKey(), map.get("systemCpuLoad"));
							break;
							case "JVM_MEMORY_RATIO":
								if (Double.parseDouble(t.getVsiValue()) <= Double.parseDouble(map.get("jvmMemoryyLoad").toString()) * 100)
									mapRatio.put(t.getVsiKey(), map.get("jvmMemoryyLoad"));
								break;
							case "OS_MEMORY_RATIO":
								if (Double.parseDouble(t.getVsiValue()) <= Double.parseDouble(map.get("physicalMemoryLoad").toString()) * 100)
									mapRatio.put(t.getVsiKey(), map.get("physicalMemoryLoad"));
								break;
							case "ALERT_EMAIL":
								if (StringUtils.isNotBlank(t.getVsiValue()))
									email.setSendTo(new String[] { t.getVsiValue() });
								break;
							default:
								break;
						}
					}
				}
			}

		} catch (Exception e) {
			_log.error("记录JMX信息出错:" + e.getMessage());
		}

		if (null != mapRatio && mapRatio.size() > 0 && email.getSendTo().length > 0) {
			mapRatio.put("RUNTIMENAME", JmxUtils.jvmName);
			email.setTemplateModel(mapRatio);
			email.setMailfmTemplate("jmxMailTemplate.fml");
			email.setTopic("资源监控警告");
			try {
				EmailUtil.sendEmail(email);
			} catch (Exception e) {
				_log.error("资源监控警告发送邮件出错:" + e.getMessage());
			}
		}
	}*/
}
