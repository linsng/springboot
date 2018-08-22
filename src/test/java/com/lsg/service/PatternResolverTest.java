package com.lsg.service;

import com.lsg.BaseTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * @author lsg
 * @version 1.0
 * @date 2018/7/23
 * @since 1.0
 */
public class PatternResolverTest extends BaseTest{

    @Test
    public void getResources() throws Throwable {
        ResourcePatternResolver resolver =  new PathMatchingResourcePatternResolver();
        Resource resources[] = resolver.getResources("classpath*:**/application.properties");

        assertNotNull(resources);
        for(Resource resource: resources) {
            EncodedResource encRes = new EncodedResource(resource, "UTF-8");
            String content = FileCopyUtils.copyToString(encRes.getReader());
            System.out.println(content);
            System.out.println(resource.getDescription());
        }

    }
}
