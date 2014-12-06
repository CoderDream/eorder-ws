package sample.hello.client;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import sample.hello.bean.Address;

import com.innovaee.eorder.module.entity.Category;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class CategoryClient {

	public static void main(String[] args) {
		Client c = Client.create();
		WebResource r = c
				.resource("http://localhost:8080/eorder-ws/rest/categorys");

//		System.out.println("===== Get huangyim =====");
//		getOneCategory(r, "1");

//		System.out.println("===== Create foo =====");
//		postForm(r, "foo", "bar");
//
//		Category cnt = new Category(1, "Guo Qing");
//
//		System.out.println("===== Create guoqing =====");
//		putOneCategory(r, cnt);
//
//		System.out.println("===== All Categorys =====");
//		getCategorys(r);
//
//		System.out.println("===== Delete foo =====");
//		deleteOneCategory(r, "foo");
//
		System.out.println("===== All Categorys =====");
		getCategorys(r);
	}

	public static void getCategorys(WebResource r) {
		// 1, get response as plain text
		String jsonRes = r.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(jsonRes);

		String xmlRes = r.accept(MediaType.APPLICATION_XML).get(String.class);
		System.out.println(xmlRes);

		// 2, get response and headers etc, wrapped in ClientResponse
		ClientResponse response = r.get(ClientResponse.class);
		System.out.println(response.getStatus());
		System.out.println(response.getHeaders().get("Content-Type"));
		String entity = response.getEntity(String.class);
		System.out.println(entity);

		// 3, get JAXB response
		GenericType<List<Category>> genericType = new GenericType<List<Category>>() {
		};
		List<Category> categorys = r.accept(MediaType.APPLICATION_XML).get(
				genericType);
		System.out.println("No. of Categorys: " + categorys.size());
		Category category = categorys.get(0);
		System.out.println(category.getCategoryId() + ": "
				+ category.getCategoryName());
	}

	public static void getOneCategory(WebResource r, String categoryId) {
		GenericType<JAXBElement<Category>> generic = new GenericType<JAXBElement<Category>>() {
		};
		JAXBElement<Category> jaxbCategory = r.path(categoryId)
				.accept(MediaType.APPLICATION_XML).get(generic);
		Category category = jaxbCategory.getValue();
		System.out.println(category.getCategoryId() + ": "
				+ category.getCategoryName());
	}

	public static void postForm(WebResource r, String categoryId, String name) {
		Form form = new Form();
		form.add("categoryId", categoryId);
		form.add("name", name);
		ClientResponse response = r.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		System.out.println(response.getEntity(String.class));
	}

	public static void putOneCategory(WebResource r, Category c) {
		ClientResponse response = r.path(c.getCategoryId().toString())
				.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, c);
		System.out.println(response.getStatus());
	}

	public static void deleteOneCategory(WebResource r, String categoryId) {
		ClientResponse response = r.path(categoryId).delete(
				ClientResponse.class);
		System.out.println(response.getStatus());
	}
}