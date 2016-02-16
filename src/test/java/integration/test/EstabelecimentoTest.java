package integration.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;

import java.util.HashMap;
import java.util.Map;

import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import com.jayway.restassured.path.json.JsonPath;

import bean.Endereco;
import bean.EnumLogradouro;
import bean.Estabelecimento;

@RunWith(JUnit4ClassRunner.class)
public class EstabelecimentoTest {
			
	private Estabelecimento estab;
	
	public EstabelecimentoTest() {}

	@Before
	public void setup(){
		deveAdicionarUmEstabelecimento();
	}
	
	@Test
	public void deveRetornarTodosOsEstabelecimentos(){		
		given()
			.header("Accept", "application/json")
	        .contentType("application/json")
		.get("/estabelecimentos/todos")			
			.then()
				.assertThat()
					.body("estabelecimentos.findAll",not(empty()));
	}
		
	@Test
	public void deveRetornarEstabsDoBairroButanta(){
		String bairro = "Butanta";
		
		given()	        
			.when()
				.get("/estabelecimentos/bairro/{bairro}",bairro).prettyPrint();									
//					.then()						
//						.body("estabelecimentos.endereco.bairro", equalTo(bairro));	
	}
	
	@Test
	public void deveRetornarEstabsDoCEP_05350000(){
		String CEP = "05350000";

		given()
			.header("Accept", "application/json")
	        .contentType("application/json")
	    .param("CEP",CEP)
	        .get("/estabelecimentos/CEP/")
	        	.then()
	        		.assertThat()
	        			.body("estabelecimentos.endereco.CEP", equalTo(CEP));		
	}
	
	@Test
	public void deveRetornarEstabsDeSaoPaulo(){
		String nomeCidade = "São Paulo";
		
		given()
			.header("Accept", "application/json")
	        .contentType("application/json")
        .param("cidade",nomeCidade)
			.get("/estabelecimentos/cidade/")
				.then()
					.assertThat()
						.body("estabelecimentos.endereco.cidade", equalTo(nomeCidade));
	}
	
	@Test
	public void deveAdicionarUmEstabelecimento(){
		Endereco end = new Endereco("05350000",EnumLogradouro.AV,"Escola Politécnica",835,"","Butanta","São Paulo","SP");
		String linkLogo = "https://www.google.com.br/url?sa=i"
				+ "&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwiKx-eSuvrKAhUCvZAKHYZoAekQjRwIBw"
				+ "&url=http%3A%2F%2Fwww.salaovanite.com.br%2F&psig=AFQjCNEFWrbsGuyGHUWGYfEbr9S4ZIPNnA"
				+ "&ust=1455648997032448";
		
        estab = new Estabelecimento("EMBELEZA AQUI",end,"SEG-SAB 9-18",linkLogo);

        given()
            .header("Accept", "application/json")
            .contentType("application/json")
            .body(estab)            
        .expect()
            .statusCode(200)
        .when()
            .post("/estabelecimentos/incluir");          	
            
//        Estabelecimento resposta = retorno.getObject("estabelecimentos", Estabelecimento.class);
//        assertThat(resposta.getEndereco().getCEP(),equalTo("05350000"));        
	}
	
	@After
	public void tearDown(){
// se incluiu, deleta		
		if (estab != null){
			JsonPath retorno = 
	            given()
	                .header("Accept", "application/json")
	                .contentType("application/json")
	                .body(estab)
	            .expect()
	                .statusCode(200)
	            .when()
	                .post("/estabelecimentos/remover")
	            .andReturn()
	                .jsonPath();
		}
	}
}
