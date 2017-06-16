package shop;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.shop.www.util.StringUtils;

public class UtilsTest {
	Logger log = Logger.getLogger(UtilsTest.class);
	
	@Test
	public void test1(){
		String str ="aaaaa bbbbb ccccc";
		String blank = StringUtils.trimToNull(str);
		log.info(blank);
	}
}
