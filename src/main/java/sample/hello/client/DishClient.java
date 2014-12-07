package sample.hello.client;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.innovaee.eorder.module.entity.Dish;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class DishClient {

	public static void main(String[] args) {
		Client c = Client.create();
		WebResource r = c
				.resource("http://localhost:8080/eorder-ws/rest/dishs");

		System.out.println("===== Get One =====");
		getOneDish(r, "7");

//		System.out.println("===== Create foo =====");
//		postForm(r, "foo", "bar");
//
//		Dish cnt = new Dish(1, "Guo Qing");
//
//		System.out.println("===== Create guoqing =====");
//		putOneDish(r, cnt);
//
//		System.out.println("===== All Dishs =====");
//		getDishs(r);
//
//		System.out.println("===== Delete foo =====");
//		deleteOneDish(r, "foo");
//
//		System.out.println("===== All Dishs =====");
//		getDishs(r);
	}

	public static void getDishs(WebResource r) {
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
		GenericType<List<Dish>> genericType = new GenericType<List<Dish>>() {
		};
		List<Dish> dishs = r.accept(MediaType.APPLICATION_XML).get(
				genericType);
		System.out.println("No. of Dishs: " + dishs.size());
		Dish dish = dishs.get(0);
		System.out.println(dish.getDishId() + ": "
				+ dish.getDishName());
	}

	public static void getOneDish(WebResource r, String id) {
		GenericType<JAXBElement<Dish>> generic = new GenericType<JAXBElement<Dish>>() {
		};
		JAXBElement<Dish> jaxbDish = r.path(id)
				.accept(MediaType.APPLICATION_XML).get(generic);
		Dish dish = jaxbDish.getValue();
		System.out.println(dish.getDishId() + ": "
				+ dish.getDishName());
	}

	public static void postForm(WebResource r, String dishId, String name) {
		Form form = new Form();
		form.add("dishId", dishId);
		form.add("name", name);
		ClientResponse response = r.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		System.out.println(response.getEntity(String.class));
	}

	public static void putOneDish(WebResource r, Dish c) {
		ClientResponse response = r.path(c.getDishId().toString())
				.accept(MediaType.APPLICATION_XML).put(ClientResponse.class, c);
		System.out.println(response.getStatus());
	}

	public static void deleteOneDish(WebResource r, String dishId) {
		ClientResponse response = r.path(dishId).delete(
				ClientResponse.class);
		System.out.println(response.getStatus());
	}
}