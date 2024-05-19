import org.testng.annotations.BeforeSuite;
import tripDemo.dictionaries.IPathEnum;
import tripDemo.service.ConfigQA;

import java.util.Map;

public class BaseTest {
    private Map<IPathEnum, String> serviceDataMap;

    @BeforeSuite
    public void initBase() {
        serviceDataMap = ConfigQA.getInstance().getServiceDataMap();
    }
}
