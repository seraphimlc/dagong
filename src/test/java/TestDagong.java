import com.dagong.mapper.JobMapper;
import com.dagong.pojo.Job;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by liuchang on 16/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/base/all.xml")
public class TestDagong {
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    private JobMapper jobMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void simple() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/job/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

//    @Test
    public void testJob() {
        Job job = jobMapper.selectByPrimaryKey("1");
        System.out.println("job = " + job);
        for (int i = 100; i < 500; i++) {
            job.setId(i + 2 + "");
            job.setJobType(487 - i%5);
            jobMapper.insert(job);
        }
    }

}
