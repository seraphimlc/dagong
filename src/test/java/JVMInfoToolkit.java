
import com.dagong.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.nio.charset.Charset;
import java.util.*;

/**
 * JRE环境查看工具
 *
 * @author Administrator 2009-11-28 17:48:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/base/all.xml")
public class JVMInfoToolkit {

    @Autowired
    private CompanyService companyService;


    @Test
    public void test() throws Exception {
        String a = new String("刘畅");
        System.out.println("a = " + a);
        companyService.createCompany(a, "");
//        readFromExcel();
//        testSendValidateCode();
//        testRegister();
//        generateJobType();
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("idGenerator = " + idGenerator.generate(User.class.getSimpleName()));
//        }
    }
    public static void main(String[] args) {
        System.out.println("当前JRE：" + System.getProperty("java.version"));
        System.out.println("当前JVM的默认字符集：" + Charset.defaultCharset());
        System.out.println("当前JRE可用的字符集列表：\n" + genJVMCharset());
        System.out.println("当前JVM运行时系统属性列表\n：" + genJVMProperties());

    }

    /**
     * 获取JVM所支持的字符集列表（格式为：字符集标准名称:[别名，别名...]
     *
     * @return 字符集列表
     */
    public static String genJVMCharset() {
        StringBuilder sb = new StringBuilder();
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (Map.Entry<String, Charset> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue().aliases()).append("\n");
        }
        return sb.toString();
    }

    /**
     * 获取当前JVM运行时系统属性信息,并按照名称进行排序
     *
     * @return 系统属性信息
     */
    public static String genJVMProperties() {
        StringBuilder sb = new StringBuilder();
        Properties props = System.getProperties();
        List<String> keylist = new ArrayList<String>();
        for (Object o : props.keySet()) {
            keylist.add(o.toString());
        }
        Collections.sort(keylist, String.CASE_INSENSITIVE_ORDER);
        for (String s : keylist) {
            sb.append(s).append("=").append(props.get(s)).append("\n");
        }
        return sb.toString();
    }
} 