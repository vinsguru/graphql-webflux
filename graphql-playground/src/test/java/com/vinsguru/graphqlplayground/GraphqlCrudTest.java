package com.vinsguru.graphqlplayground;

import com.vinsguru.graphqlplayground.lec16.dto.CustomerDto;
import com.vinsguru.graphqlplayground.lec16.dto.DeleteResponseDto;
import com.vinsguru.graphqlplayground.lec16.dto.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
@TestPropertySource(properties = "lec=lec16")
public class GraphqlCrudTest {

	@Autowired
	private HttpGraphQlTester client;

	@Test
	public void allCustomersTest(){
		var doc = """
					query{
						customers{
							name
							age
						}
					}
				""";
		this.client.document(doc)
				.execute()
				.path("customers").entityList(Object.class).hasSizeGreaterThan(2)
				.path("customers.[0].name").entity(String.class).isEqualTo("sam");
	}

	@Test
	public void customerByIdTest(){
		this.client.documentName("crud-operations")
				.variable("id", 1)
				.operationName("GetCustomerById")
				.execute()
				.path("response.id").entity(Integer.class).isEqualTo(1)
			    .path("response.name").entity(String.class).isEqualTo("sam")
			    .path("response.age").entity(Integer.class).isEqualTo(10);
	}

	@Test
	public void createCustomerTest(){
		this.client.documentName("crud-operations")
				   .variable("customer", CustomerDto.create(null, "michael", 55, "seattle"))
				   .operationName("CreateCustomer")
				   .execute()
				   .path("response.id").entity(Integer.class).isEqualTo(5)
				   .path("response.name").entity(String.class).isEqualTo("michael")
				   .path("response.age").entity(Integer.class).isEqualTo(55);
	}

	@Test
	public void updateCustomerTest(){
		this.client.documentName("crud-operations")
				   .variable("id", 2)
				   .variable("customer", CustomerDto.create(null, "obie", 45, "dallas"))
				   .operationName("UpdateCustomer")
				   .execute()
				   .path("response.id").entity(Integer.class).isEqualTo(2)
				   .path("response.name").entity(String.class).isEqualTo("obie")
				   .path("response.city").entity(String.class).isEqualTo("dallas")
				   .path("response").entity(Object.class).satisfies(System.out::println);
	}

	@Test
	public void deleteCustomerTest(){
		this.client.documentName("crud-operations")
				   .variable("id", 3)
				   .operationName("DeleteCustomer")
				   .execute()
				   .path("response").entity(DeleteResponseDto.class).satisfies(r -> {
						Assertions.assertThat(r.getId()).isEqualTo(3);
						Assertions.assertThat(r.getStatus()).isEqualTo(Status.SUCCESS);
			});
	}


}
