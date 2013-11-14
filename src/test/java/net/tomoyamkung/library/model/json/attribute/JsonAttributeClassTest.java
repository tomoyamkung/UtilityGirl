package net.tomoyamkung.library.model.json.attribute;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.tomoyamkung.library.model.json.DummyJson;
import net.tomoyamkung.library.util.ExtArrayList;
import net.tomoyamkung.library.util.JsonUtil;
import net.tomoyamkung.library.validate.NotNullValidator;
import net.tomoyamkung.library.validate.Validator;
import net.tomoyamkung.library.validate.integer.IntegerGreaterThanValidator;
import net.tomoyamkung.library.validate.integer.IntegerValueValidator;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class JsonAttributeClassTest {
	
	public static class Validate {
		
		@Test
		public void 妥当性確認にパスする場合() throws Exception {
			// Setup
			JsonAttributeClass sut = new JsonAttributeClass(JsonUtil.serialize(DummyJson.newInstance()));
			List<Validator> validators = new ExtArrayList<Validator>().addThis(new NotNullValidator());
			List<String> errorMessages = new ExtArrayList<String>().addThis("xxx");
			
			// Exercise
			List<String> actualErrorMessage = sut.validate("attr1", validators, errorMessages);
			
			// Verify
			assertThat(actualErrorMessage.isEmpty(), is(true));
		}
		
		@Test
		public void 妥当性確認にパスしない場合() throws Exception {
			// Setup
			String errorMessage = "xxx";
			JsonAttributeClass sut = new JsonAttributeClass(JsonUtil.serialize(DummyJson.newInstance()));
			List<Validator> validators = new ExtArrayList<Validator>().addThis(new IntegerValueValidator());
			List<String> errorMessages = new ExtArrayList<String>().addThis(errorMessage);
			
			// Exercise
			List<String> actualErrorMessage = sut.validate("attr1", validators, errorMessages);
			
			// Verify
			assertThat(actualErrorMessage.size(), is(1));
			assertThat(actualErrorMessage.get(0), is(errorMessage));
		}
		
		@Test
		public void 妥当性確認にパスしない場合_Validatorが2つの場合() throws Exception {
			// Setup
			String errorMessage1 = "x";
			String errorMessage2 = "y";
			JsonAttributeClass sut = new JsonAttributeClass(JsonUtil.serialize(DummyJson.newInstance(), ""));
			List<Validator> validators = new ExtArrayList<Validator>().addThis(new IntegerValueValidator()).addThis(new IntegerGreaterThanValidator(1));
			List<String> errorMessages = new ExtArrayList<String>().addThis(errorMessage1).addThis(errorMessage2);
			
			// Exercise
			List<String> actualErrorMessage = sut.validate("attr1", validators, errorMessages);
			
			// Verify
			assertThat(actualErrorMessage.size(), is(2));
			assertThat(actualErrorMessage.get(0), is(errorMessage1));
			assertThat(actualErrorMessage.get(1), is(errorMessage2));
		}
		
		@Test(expected = NullPointerException.class)
		public void 属性名の指定を間違えた場合() throws Exception {
			// Setup
			JsonAttributeClass sut = new JsonAttributeClass(JsonUtil.serialize(DummyJson.newInstance()));
			List<Validator> validators = new ExtArrayList<Validator>().addThis(new NotNullValidator());
			List<String> errorMessages = new ExtArrayList<String>();
			
			// Exercise
			sut.validate("not_found", validators, errorMessages);
		}
	}

	@Test
	public void test() throws Exception {
		// Setup
		String json = JsonUtil.serialize(DummyJson.newInstance(), "");
		
		// Exercise
		JsonAttributeClass sut = new JsonAttributeClass(json);
				
		// Verify
		assertThat(sut.get("attr1").getValue(), is("value1"));
		assertThat(sut.get("attr2").getValue(), is("value2"));
		assertThat(sut.get("attr3").getValue(), is("value3"));
		assertThat("定義されていない属性名称を指定した場合は null になる",
				sut.get("x"), is(nullValue(JsonAttribute.class)));
	}

}
