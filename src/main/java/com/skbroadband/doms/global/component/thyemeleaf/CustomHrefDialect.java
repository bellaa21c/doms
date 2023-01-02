package com.skbroadband.doms.global.component.thyemeleaf;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.component
 * @File : CustomHrefDialect
 * @Program :
 * @Date : 2022-12-20
 * @Comment :
 */
public class CustomHrefDialect extends AbstractProcessorDialect {
    public static final String NAME = "ExtraLink";
    public static final String DEFAULT_PREFIX = "th";
    public static final int PROCESSOR_PRECEDENCE = 800;
    private String charset;

    public CustomHrefDialect(String charset) {
        super("ExtraLink", "cu", 800);
        this.charset = charset;
    }

    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(new CustomHrefAttributeTagProcessor(TemplateMode.HTML,
                dialectPrefix, this.charset));
        return processors;
    }
}
