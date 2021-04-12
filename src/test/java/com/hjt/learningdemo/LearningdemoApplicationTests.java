package com.hjt.learningdemo;

import com.hjt.learningdemo.model.AyUser;
import com.hjt.learningdemo.repository.AyUserRepository;
import com.mysql.cj.protocol.Resultset;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LearningdemoApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void mySqlTest(){
        String sql = "select id ,name , password from ay_user";
        List<AyUser> userList = (List<AyUser>) jdbcTemplate.query(sql, new RowMapper<AyUser>() {
            @Override
            public AyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                AyUser user = new AyUser();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        System.out.println("查询成功");
        for(AyUser user:userList){
            System.out.println("[id]:" + user.getId() + ";[name]" + user.getName());
        }
    }
    @Test
    void contextLoads() {
    }

    @Resource
    private AyUserRepository ayUserService;

    @Test
    public void testRepository(){
        List<AyUser> userList = ayUserService.findAll();
        System.out.println("findAll:" + userList.size());

        List<AyUser> userList1 = ayUserService.findByName("阿兰");
        Assert.isTrue(userList1.get(0).getName().equals("阿兰"),"find no target data");

        List<AyUser> userList2 = ayUserService.findByNameLike("%兰%");
        Assert.isTrue(userList2.get(0).getName().equals("阿兰"),"find no target data");

        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        List<AyUser> userList3 = ayUserService.findByIdIn(ids);
        System.out.println("findbyids:"+userList3.size());

        PageRequest pageRequest = PageRequest.of(0,10);
        Page<AyUser> userList5 = ayUserService.findAll(pageRequest);
        System.out.println("Page findAll():" + userList5.getTotalPages()+"/"+userList5.getSize());

        AyUser ayUser = new AyUser();
        ayUser.setId("3");
        ayUser.setName("test");
        ayUser.setPassword("123");
        ayUserService.save(ayUser);
        ayUserService.deleteById("3");

    }

}
