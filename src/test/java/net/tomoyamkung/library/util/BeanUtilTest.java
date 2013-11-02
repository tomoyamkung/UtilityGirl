package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class BeanUtilTest {

	@Test
	public void test() throws Exception {
		// Setup
		CopySrc src = new CopySrc(1, "a", 10.1, new Date(), Boolean.TRUE,
				10, "A", 20.2, new Date(), Boolean.FALSE,
				100, "あ", 300.3, new Date(), Boolean.TRUE);
		CopyDest dest = new CopyDest();
		
		// Exercise
		BeanUtil.copy(src, dest);
		
		// Verify
		assertThat(src.integerValue, is(dest.integer_value));
		assertThat(src.string_value, is(dest.stringValue));
		assertThat(src.doubleValue, is(dest.doubleValue));
		assertThat(src.dateValue, is(dest.dateValue));
		assertThat(src.booleanValue, is(dest.booleanValue));
		
		assertThat(src.parentIntegerValue, is(dest.parent_integer_value));
		assertThat(src.parent_string_value, is(dest.parentStringValue));
		assertThat(src.parentDoubleValue, is(dest.parentDoubleValue));
		assertThat(src.parentDateValue, is(dest.parentDateValue));
		assertThat(src.parentBooleanValue, is(dest.parentBooleanValue));
		
		assertThat(src.grandfatherIntegerValue, is(dest.grandfather_integer_value));
		assertThat(src.grandfather_string_value, is(dest.grandfatherStringValue));
		assertThat(src.grandfatherDoubleValue, is(dest.grandfatherDoubleValue));
		assertThat(src.grandfatherDateValue, is(dest.grandfatherDateValue));
		assertThat(src.grandfatherBooleanValue, is(dest.grandfatherBooleanValue));
		
	}
	
	public class CopySrc extends ParentCopySrc {
		
		private Integer integerValue;
		private String string_value;
		private Double doubleValue;
		private Date dateValue;
		private Boolean booleanValue;
		
		public CopySrc(Integer integerValue, String stringValue, double doubleValue, Date dateValue, Boolean booleanValue,
				Integer parentIntegerValue, String parentStringValue, double parentDoubleValue, Date parentDateValue, Boolean parentBooleanValue,
				Integer grandfatherIntegerValue, String grandfatherStringValue, double grandfatherDoubleValue, Date grandfatherDateValue, Boolean grandfatherBooleanValue) {
			super(parentIntegerValue, parentStringValue, parentDoubleValue, parentDateValue, parentBooleanValue,
					grandfatherIntegerValue, grandfatherStringValue, grandfatherDoubleValue, grandfatherDateValue, grandfatherBooleanValue);
			
			this.integerValue = integerValue;
			this.string_value = stringValue;
			this.doubleValue = doubleValue;
			this.dateValue = dateValue;
			this.booleanValue = booleanValue;
		}

	}
	
	public class ParentCopySrc extends GrandfatherCopySrc {
		protected Integer parentIntegerValue;
		protected String parent_string_value;
		protected double parentDoubleValue;
		protected Date parentDateValue;
		protected Boolean parentBooleanValue;
		
		public ParentCopySrc(Integer parentIntegerValue, String parentStringValue, double parentDoubleValue, Date parentDateValue, Boolean parentBooleanValue,
				Integer grandfatherIntegerValue, String grandfatherStringValue, double grandfatherDoubleValue, Date grandfatherDateValue, Boolean grandfatherBooleanValue) {
			super(grandfatherIntegerValue, grandfatherStringValue, grandfatherDoubleValue, grandfatherDateValue, grandfatherBooleanValue);
			
			this.parentIntegerValue = parentIntegerValue;
			this.parent_string_value = parentStringValue;
			this.parentDoubleValue = parentDoubleValue;
			this.parentDateValue = parentDateValue;
			this.parentBooleanValue = parentBooleanValue;
		}
	}
	
	public class GrandfatherCopySrc {
		protected Integer grandfatherIntegerValue;
		protected String grandfather_string_value;
		protected double grandfatherDoubleValue;
		protected Date grandfatherDateValue;
		protected Boolean grandfatherBooleanValue;
		
		public GrandfatherCopySrc(Integer grandfatherIntegerValue, String grandfatherStringValue, double grandfatherDoubleValue, Date grandfatherDateValue, Boolean grandfatherBooleanValue) {
			this.grandfatherIntegerValue = grandfatherIntegerValue;
			this.grandfather_string_value = grandfatherStringValue;
			this.grandfatherDoubleValue = grandfatherDoubleValue;
			this.grandfatherDateValue = grandfatherDateValue;
			this.grandfatherBooleanValue = grandfatherBooleanValue;
		}
	}
	
	public class CopyDest {
		
		private Integer integer_value;
		private String stringValue;
		private Double doubleValue;
		private Date dateValue;
		private Boolean booleanValue;
		
		private Integer parent_integer_value;
		private String parentStringValue;
		private double parentDoubleValue;
		private Date parentDateValue;
		private Boolean parentBooleanValue;
		
		private Integer grandfather_integer_value;
		private String grandfatherStringValue;
		private double grandfatherDoubleValue;
		private Date grandfatherDateValue;
		private Boolean grandfatherBooleanValue;
		
	}

}
