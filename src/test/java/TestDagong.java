import com.dagong.service.EvaluationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by liuchang on 16/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/base/all.xml")
public class TestDagong {
//    private MockMvc mockMvc;
//    @Autowired
//    protected WebApplicationContext wac;
    @Autowired
    private EvaluationService evaluationService;

//    @Before
//    public void setup() {
//        this.mockMvc = webAppContextSetup(this.wac).build();
//    }
//
//    public void simple() throws Exception {
//        mockMvc.perform(get("/hello.do"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("hello"));
//    }

    @Test
    public void testNull() {
        evaluationService.deleteJobEvaluation(null, null, null);
    }

}
