package net.tomoyamkung.library.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tomoyamkung.library.util.beanutil.DummyCreateTestClass;
import net.tomoyamkung.library.util.beanutil.DummyExcludeAnnotation;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class BeanUtilTest {

	public static class GetFields {

		@Test
		public void 全てのフィールドを取得する() throws Exception {
			// Setup
			// Exercise
			List<Field> actual = BeanUtil.getFields(new DummyCreateTestClass());

			// Verify
			assertThat(actual.size(), is(4));
			for (Field f : actual) {
				StringUtil.isMatch(f.getName(), "s", "i", "d", "b");
			}
		}

		@Test
		public void 特定のアノテーションが付与されているフィールドを除外する() throws Exception {
			// Setup
			// Exercise
			List<Field> actual = BeanUtil.getFields(new DummyCreateTestClass(),
					DummyExcludeAnnotation.class);

			// Verify
			assertThat(actual.size(), is(3));
			for (Field f : actual) {
				StringUtil.isMatch(f.getName(), "s", "d", "b");
			}
		}
	}

	public static class Create {

		@Test
		public void 単一オブジェクトの場合() throws Exception {
			// Setup
			Map<String, Object> fieldValue = createFieldValue();

			// Exercise
			DummyCreateTestClass actual = BeanUtil.create(
					DummyCreateTestClass.class, fieldValue);

			// Verify
			assertThat(actual.getS(), is(fieldValue.get("s")));
			assertThat(actual.getI(), is(fieldValue.get("i")));
			assertThat(actual.getD(), is(fieldValue.get("d")));
			assertThat(actual.getB(), is(fieldValue.get("b")));
		}

		@Test
		public void 複数オブジェクトの場合() throws Exception {
			// Setup
			Map<String, Object> fieldValue = createFieldValue();
			List<Map<String, Object>> fieldValues = new ExtArrayList<Map<String, Object>>()
					.addThis(fieldValue).addThis(fieldValue)
					.addThis(fieldValue);

			// Exercise
			List<DummyCreateTestClass> actual = BeanUtil.creates(
					DummyCreateTestClass.class, fieldValues);

			// Verify
			assertThat(actual.size(), is(3));
			for (DummyCreateTestClass dummyCreateTestClass : actual) {
				assertThat(dummyCreateTestClass.getS(), is(fieldValue.get("s")));
				assertThat(dummyCreateTestClass.getI(), is(fieldValue.get("i")));
				assertThat(dummyCreateTestClass.getD(), is(fieldValue.get("d")));
				assertThat(dummyCreateTestClass.getB(), is(fieldValue.get("b")));
			}
		}

		private Map<String, Object> createFieldValue() {
			Map<String, Object> fieldValue = new HashMap<String, Object>();
			fieldValue.put("s", "STRING");
			fieldValue.put("i", new Integer(1));
			fieldValue.put("d", new Date());
			fieldValue.put("b", Boolean.TRUE);
			return fieldValue;
		}
	}

	public static class Copy {

		@Test
		public void test() throws Exception {
			// Setup
			CopySrc src = new CopySrc(1, "a", 10.1, new Date(), Boolean.TRUE,
					10, "A", 20.2, new Date(), Boolean.FALSE, 100, "あ", 300.3,
					new Date(), Boolean.TRUE, new ExtArrayList<Integer>()
							.addThis(1).addThis(2), new ExtArrayList<String>()
							.addThis("!").addThis("@"),
					new ExtArrayList<Date>().addThis(new Date()));
			CopyDest dest = new CopyDest();

			// Exercise
			BeanUtil.copy(src, dest);

			// Verify
			assertThat(src.integerValue, is(dest.integer_value));
			assertThat(src.string_value, is(dest.stringValue));
			assertThat(src.double_value, is(dest.double_value));
			assertThat(src.dateValue, is(dest.dateValue));
			assertThat(src.booleanValue, is(dest.booleanValue));

			assertThat(src.parentIntegerValue, is(dest.parent_integer_value));
			assertThat(src.parent_string_value, is(dest.parentStringValue));
			assertThat(src.parent_double_value, is(dest.parent_double_value));
			assertThat(src.parentDateValue, is(dest.parentDateValue));
			assertThat(src.parentBooleanValue, is(dest.parentBooleanValue));

			assertThat(src.grandfatherIntegerValue,
					is(dest.grandfather_integer_value));
			assertThat(src.grandfather_string_value,
					is(dest.grandfatherStringValue));
			assertThat(src.grandfather_double_value,
					is(dest.grandfather_double_value));
			assertThat(src.grandfatherDateValue, is(dest.grandfatherDateValue));
			assertThat(src.grandfatherBooleanValue,
					is(dest.grandfatherBooleanValue));

			assertThat(src.integerList.size(), is(dest.integerList.size()));
			for (int i = 0; i < src.integerList.size(); i++) {
				assertThat(src.integerList.get(i), is(dest.integerList.get(i)));
			}
			assertThat(src.parent_string_list.size(),
					is(dest.parent_string_list.size()));
			for (int i = 0; i < src.parent_string_list.size(); i++) {
				assertThat(src.parent_string_list.get(i),
						is(dest.parent_string_list.get(i)));
			}
			assertThat(src.grandfatherDateList.size(),
					is(dest.grandfather_date_list.size()));
			for (int i = 0; i < src.grandfatherDateList.size(); i++) {
				assertThat(src.grandfatherDateList.get(i),
						is(dest.grandfather_date_list.get(i)));
			}
		}

		public class CopySrc extends ParentCopySrc {

			private Integer integerValue;
			private String string_value;
			private Double double_value;
			private Date dateValue;
			private Boolean booleanValue;
			private List<Integer> integerList;

			public CopySrc(Integer integerValue, String stringValue,
					double doubleValue, Date dateValue, Boolean booleanValue,
					Integer parentIntegerValue, String parentStringValue,
					double parentDoubleValue, Date parentDateValue,
					Boolean parentBooleanValue,
					Integer grandfatherIntegerValue,
					String grandfatherStringValue,
					double grandfatherDoubleValue, Date grandfatherDateValue,
					Boolean grandfatherBooleanValue, List<Integer> integerList,
					List<String> parent_string_list,
					List<Date> grandfatherDateList) {
				super(parentIntegerValue, parentStringValue, parentDoubleValue,
						parentDateValue, parentBooleanValue,
						grandfatherIntegerValue, grandfatherStringValue,
						grandfatherDoubleValue, grandfatherDateValue,
						grandfatherBooleanValue, parent_string_list,
						grandfatherDateList);

				this.integerValue = integerValue;
				this.string_value = stringValue;
				this.double_value = doubleValue;
				this.dateValue = dateValue;
				this.booleanValue = booleanValue;
				this.integerList = integerList;
			}

		}

		public class ParentCopySrc extends GrandfatherCopySrc {
			protected Integer parentIntegerValue;
			protected String parent_string_value;
			protected double parent_double_value;
			protected Date parentDateValue;
			protected Boolean parentBooleanValue;
			protected List<String> parent_string_list;

			public ParentCopySrc(Integer parentIntegerValue,
					String parentStringValue, double parentDoubleValue,
					Date parentDateValue, Boolean parentBooleanValue,
					Integer grandfatherIntegerValue,
					String grandfatherStringValue,
					double grandfatherDoubleValue, Date grandfatherDateValue,
					Boolean grandfatherBooleanValue,
					List<String> parent_string_list,
					List<Date> grandfatherDateList) {
				super(grandfatherIntegerValue, grandfatherStringValue,
						grandfatherDoubleValue, grandfatherDateValue,
						grandfatherBooleanValue, grandfatherDateList);

				this.parentIntegerValue = parentIntegerValue;
				this.parent_string_value = parentStringValue;
				this.parent_double_value = parentDoubleValue;
				this.parentDateValue = parentDateValue;
				this.parentBooleanValue = parentBooleanValue;
				this.parent_string_list = parent_string_list;
			}
		}

		public class GrandfatherCopySrc {
			protected Integer grandfatherIntegerValue;
			protected String grandfather_string_value;
			protected double grandfather_double_value;
			protected Date grandfatherDateValue;
			protected Boolean grandfatherBooleanValue;
			protected List<Date> grandfatherDateList;

			public GrandfatherCopySrc(Integer grandfatherIntegerValue,
					String grandfatherStringValue,
					double grandfatherDoubleValue, Date grandfatherDateValue,
					Boolean grandfatherBooleanValue,
					List<Date> grandfatherDateList) {
				this.grandfatherIntegerValue = grandfatherIntegerValue;
				this.grandfather_string_value = grandfatherStringValue;
				this.grandfather_double_value = grandfatherDoubleValue;
				this.grandfatherDateValue = grandfatherDateValue;
				this.grandfatherBooleanValue = grandfatherBooleanValue;
				this.grandfatherDateList = grandfatherDateList;
			}
		}

		public class CopyDest {

			private Integer integer_value;
			private String stringValue;
			private Double double_value;
			private Date dateValue;
			private Boolean booleanValue;

			private Integer parent_integer_value;
			private String parentStringValue;
			private double parent_double_value;
			private Date parentDateValue;
			private Boolean parentBooleanValue;

			private Integer grandfather_integer_value;
			private String grandfatherStringValue;
			private double grandfather_double_value;
			private Date grandfatherDateValue;
			private Boolean grandfatherBooleanValue;

			private List<Integer> integerList;
			private List<String> parent_string_list;
			private List<Date> grandfather_date_list;
		}
	}

}
