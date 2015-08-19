package com.javahonk.emails;

import java.io.IOException;

import com.javahonk.model.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class JinzaFreeMarkerConfig {
	private static JinzaFreeMarkerConfig freeMarkerConfig = null;

	/**
	 * @return
	 */
	public static JinzaFreeMarkerConfig getInstance() {
		if (freeMarkerConfig == null) {
			freeMarkerConfig = new JinzaFreeMarkerConfig();
		}
		return freeMarkerConfig;
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static Template getTemplateByName(String fileName) {
		try {
			String filePath = Constants.RESOURCE_PATH + fileName;
			Configuration cfg = new Configuration();
			return cfg.getTemplate(filePath, "UTF-8");
//			return cfg.getTemplate(filePath);
		} catch (IOException e) {
			throw new RuntimeException("Error in loading ftl template: " + e.getMessage());
		}
	}
}
