package cache.impl;

import cache.interfaces.CacheManagement;
import cache.interfaces.Config;

public class SystemManagementClass {
    // 可annotation注入
    private CacheManagement cacheManagement;
    // 可annotation注入
    private Config config;

    public SystemManagementClass (CacheManagement cacheManagement, Config config) {
        this.cacheManagement = cacheManagement;
        this.config = config;
    }

    public boolean reBuildSysConf () {
        try {
            // rebuild cache
            cacheManagement.reBuild(config);

            // rebuild other configurations
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
