package com.share.test.junit;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.share.core.util.JSONObject;
import com.share.core.util.RandomUtil;
import com.share.core.util.Time;
import com.share.test.db.AdminDbService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:share-test.xml" })
public class JunitTest {
	private final static Logger logger = LoggerFactory.getLogger(JunitTest.class);

	@Autowired
	private AdminDbService db;

	private String[] types = { "ad", "h5", "block", "string", "fuck" };

	@Test
	public void junitTest() throws Exception {
		String sql = "INSERT INTO `json`(`name`, `json`, `create_time`) VALUES (?,?,?)";
		String uuid = "INSERT INTO `uuid`(`id`, `name`, `type`, `create_time`) VALUES (?,?,?,?)";
		while (true) {
			JSONObject json = new JSONObject();
			json.put("name", RandomUtil.string(32));
			json.put("sex", RandomUtil.isLuck(50) ? 1 : 2);
			json.put("type", types[RandomUtil.rand(0, types.length - 1)]);
			db.update(sql, RandomUtil.string(64), json.toString(), Time.now());
			db.update(uuid, UUID.randomUUID().toString().replaceAll("-", ""), RandomUtil.string(32), RandomUtil.string(40), Time.now());
			logger.info(json.toString());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}