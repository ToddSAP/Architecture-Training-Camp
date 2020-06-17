package cache;

import cache.impl.ApplicationClass;
import cache.impl.SysConfig;
import cache.impl.SystemManagementClass;
import cache.impl.Cache;

public class Client {
    public static void main(String[] args) {
        ApplicationClass app = new ApplicationClass(new Cache());
        SystemManagementClass sys = new SystemManagementClass(new Cache(), new SysConfig());

        app.queryBySomeBusinessKey(new Object());
        app.deleteBySomeBusinessKey(new Object());

        sys.reBuildSysConf();
    }
}
