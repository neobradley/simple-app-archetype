package lab.neobradley.simple.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class BeanConvertUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 复制对像信息到指定类型对像中
     */
    public static <T> T copyBean(Object target, Class<T> cls) {
        log.debug("copyBean {}===>{}", target, cls);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (target == null) return null;
        try {
            return mapper.readValue(mapper.writeValueAsString(target), cls);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 复制对像信息到指定类型对像中
     */
    public static <T> List<T> copyListBean(Object target, Class<T> cls) {
        log.debug("copyListBean===> {}===>{}", target, cls);
        if (target == null) return null;

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(mapper.writeValueAsString(target), mapper.getTypeFactory().constructCollectionType(List.class, cls));
        } catch (IOException e) {
            return null;
        }
    }

    public static void copyPageCommonProp(IPage page, Page result) {
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        result.setSearchCount(page.isSearchCount());
        result.setOrders(page.orders());
    }
}
