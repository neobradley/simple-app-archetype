package lab.neobradley.simple.application.config.ds;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 动态数据源上下文
 *
 * @author Louis
 * @date Jun 17, 2019
 */
public class DynamicDataSourceContextHolder {

    /**
     * 数据源的 key集合，用于切换时判断数据源是否存在
     */
    private static final Set<Object> DATA_SOURCE_KEYS = new HashSet<>();

    /**
     * 切换数据源
     *
     * @param key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 重置数据源
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    public static boolean deleteDataSourceKey(String key) {
        return DATA_SOURCE_KEYS.remove(key);
    }

    /**
     * 判断是否包含数据源
     *
     * @param key 数据源key
     * @return
     */
    public static boolean containDataSourceKey(String key) {
        return DATA_SOURCE_KEYS.contains(key);
    }

    /**
     * 添加数据源keys
     *
     * @param keys
     * @return
     */
    public static boolean addDataSourceKeys(Collection<? extends Object> keys) {
        return DATA_SOURCE_KEYS.addAll(keys);
    }

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>() {
        /**
         * 将 master 数据源的 key作为默认数据源的 key
         */
        @Override
        protected String initialValue() {
            return "master";
        }
    };

}
