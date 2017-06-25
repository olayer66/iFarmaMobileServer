package es.ucm.fdi.iw.controller;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import es.ucm.fdi.iw.model.Farmacia;
import es.ucm.fdi.iw.model.Medicamento;
import es.ucm.fdi.iw.model.Medico;
import es.ucm.fdi.iw.model.Mensaje;
import es.ucm.fdi.iw.model.Paciente;
import es.ucm.fdi.iw.model.Tratamiento;
import es.ucm.fdi.iw.model.Usuario;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;



@RestController
@RequestMapping("/mobile")
public class MobileController {

	private static final Logger log = Logger.getLogger(MobileController.class);

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	//entra el usuario y la password
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONObject LoginAction(@RequestBody JSONObject entrada) {
		JSONObject resultado= new JSONObject();
		String usuario=entrada.get("usuario").toString();
		String contra=entrada.get("password").toString();
		log.info("Usuario: "+usuario+" contraseña: "+contra);
		try{
			Usuario usu= entityManager.createNamedQuery("Usuario.login", Usuario.class)
										  .setParameter("ssoId", usuario)
										  .setParameter("password", contra)
										  .getSingleResult();
			if(usu.getEstado()==1)
			{
				resultado.put("role", usu.getRole());
				if(usu.getRole().equals("MED")){
					resultado.put("error", "");
					resultado.put("usuario", medicoToJSON(entityManager.find(Medico.class, usu.getId()),0));
				}
				else if(usu.getRole().equals("PAC")){
					resultado.put("error", "");
					resultado.put("usuario", pacienteToJSON(entityManager.find(Paciente.class, usu.getId()),0));
				}else{
					resultado.put("error", "fallo");
					resultado.put("usuario", "");
				}
				
			}
			else
			{
				resultado.put("error", "estado");
				resultado.put("role", "");
				resultado.put("usuario", "");
			}
		}catch (NoResultException e) {
			resultado.put("error", "fallo");
			resultado.put("role", "");
			resultado.put("usuario", "");
		}
		return resultado;
	}
	
	//Sin entrada solo salida de los medicamentos
	@RequestMapping(value="/medicamentos", method=RequestMethod.GET)
	public JSONObject medicamentosAction() {
		JSONObject resultado= new JSONObject();
		JSONArray listaMed= new JSONArray();
		TypedQuery<Medicamento> query= entityManager.createNamedQuery("Medicamento.findAll", Medicamento.class);
		for(Medicamento medicamento: query.getResultList())
		{
			listaMed.add(medicamentoToJSON(medicamento));
		}
		resultado.put("medicamentos", listaMed);
		return resultado;
	}
	//entra el mensaje
	@Transactional
	@RequestMapping(value="/nuevoMensaje", method=RequestMethod.POST)
	public JSONObject nuevoMensajeAction(@RequestBody JSONObject entrada) {
		JSONObject respuesta= new JSONObject();
		String error="";
		String msg="ok";
		Mensaje mensaje=JsonToMensaje((JSONObject) entrada);
		long idRem= mensaje.getRemitente().getId();
		long idDes=mensaje.getDestinatario().getId();
		Usuario remitente = null;
		Usuario destiniatario = null;
		try{
		remitente= entityManager.find(Usuario.class, idRem);
		destiniatario= entityManager.find(Usuario.class, idDes);
		}catch (NoResultException e) {
			error="fallo";
			msg="Resultados no encontrados";
		}		
		remitente.getMensajesEnviados().add(mensaje);
		destiniatario.getMensajesRecibidos().add(mensaje);
		entityManager.persist(destiniatario);
		entityManager.persist(remitente);
		entityManager.persist(mensaje);	
		respuesta.put("error", error);
		respuesta.put("mensaje", msg);
		return respuesta;
	}
	//entra el id del mensaje
	@Transactional
	@RequestMapping(value="/actMensaje", method=RequestMethod.POST)
	public String actMensajeAction(@RequestParam("id") Long id) {
		Mensaje mensaje=entityManager.find(Mensaje.class, id);
		long idRem= mensaje.getDestinatario().getId();
		Usuario remitente;
		try{
		remitente= entityManager.find(Usuario.class, idRem);
		}catch (NoResultException e) {
			return "fallo";
		}		
		remitente.getMensajesRecibidos().remove(mensaje);
		mensaje.setLeido(true);
		remitente.getMensajesRecibidos().add(mensaje);
		entityManager.persist(remitente);
		entityManager.persist(mensaje);	
		
		return "ok";
	}
	//devuelve el nuevo listado de mensajes
	@RequestMapping(value="/actListaMensajes", method=RequestMethod.POST)
	public JSONObject actListaMensajesAction(@RequestBody JSONObject entrada) {
		JSONObject listaMen= new JSONObject();
		long id= Long.parseLong(entrada.get("id").toString());
		Usuario usuario = entityManager.find(Usuario.class,id);
		JSONArray listaM= new JSONArray();
		for(Mensaje mensaje: usuario.getMensajesRecibidos())
		{
			listaM.add(mensajeToJSON(mensaje));
		}
		listaMen.put("error", "");
		listaMen.put("mensajes",listaM);
		return listaMen;
	}
	/*---TRATAMIENTOS----*/
	@Transactional
	@RequestMapping(value="/nuevoTratamiento", method=RequestMethod.POST)
	public JSONObject nuevoTratamientoAction(@RequestBody JSONObject entrada) {
		JSONObject respuesta= new JSONObject();
		Tratamiento tratamiento= JsonToTratamiento(entrada);
		
		Paciente paciente= entityManager.find(Paciente.class, tratamiento.getPaciente().getId());
		paciente.getTratamiento().add(tratamiento);
		
		entityManager.persist(paciente);
		entityManager.persist(tratamiento);
		respuesta.put("error", "");
		respuesta.put("mensaje", "ok");
		return respuesta;
	}
	@Transactional
	@RequestMapping(value="/eliminarTratamiento", method=RequestMethod.POST)
	public JSONObject eliminarTratamientoAction(@RequestBody JSONObject entrada) {
		String id= entrada.get("id").toString();
		Tratamiento tratamiento = entityManager.find(Tratamiento.class, Long.parseLong(id));
		Paciente paciente = entityManager.find(Paciente.class, tratamiento.getPaciente().getId());
		paciente.getTratamiento().remove(tratamiento);
		entityManager.persist(paciente);
		entityManager.remove(tratamiento);	
		return new JSONObject();
	}
	@Transactional
	@RequestMapping(value="/toma", method=RequestMethod.GET)
	public String tomaAction(@RequestBody JSONObject entrada) {
		String id= entrada.get("id").toString();
		Tratamiento tratamiento = entityManager.find(Tratamiento.class, id);
		tratamiento.setNumDosisDia(tratamiento.getNumDosisDia()+1);
		entityManager.persist(tratamiento);
		return "ok";
	}
	
	
	//JSON de la entity medico
	private JSONObject medicoToJSON(Medico entrada, int tipo){
		JSONObject medico= new JSONObject();
		medico.put("id", entrada.getId());
		medico.put("nombre", entrada.getNombre());
		medico.put("apellidos", entrada.getApellidos());
		medico.put("telefono", entrada.getTelefono());
		medico.put("email", entrada.getEmail());
		medico.put("numColMedico", entrada.getNumColMedico());
		medico.put("centroTrabajo", entrada.getCentroTrabajo());
		if(tipo==0)
		{
			JSONArray listaMen=new JSONArray();
			for(Mensaje mensaje: entrada.getMensajesRecibidos())
			{
				listaMen.add(mensajeToJSON(mensaje));
			}
			medico.put("listaMensajes",listaMen);
			JSONArray listaPac=new JSONArray();
			for(Paciente paciente: entrada.getPacientes())
			{
				listaPac.add(pacienteToJSON(paciente,1));
			}
			medico.put("listaPacientes",listaPac);
		}
		return medico;
	}
	//JSON de la entity paciente
	private JSONObject pacienteToJSON(Paciente entrada, int tipo){
		JSONObject paciente= new JSONObject();
		paciente.put("id", entrada.getId());
		paciente.put("nombre", entrada.getNombre());
		paciente.put("apellidos", entrada.getApellidos());
		paciente.put("telefono", entrada.getTelefono());
		paciente.put("email", entrada.getEmail());
		paciente.put("direccion", entrada.getDireccion());
		paciente.put("ciudad", entrada.getCiudad());
		paciente.put("codPostal", entrada.getCodPostal());
		paciente.put("provincia", entrada.getProvincia());
		paciente.put("comAutonoma", entrada.getComAutonoma());
		if(tipo==0)
		{
			JSONArray listaMen=new JSONArray();
			for(Mensaje mensaje: entrada.getMensajesRecibidos())
			{
				listaMen.add(mensajeToJSON(mensaje));
			}
			paciente.put("listaMensajes",listaMen);
			paciente.put("medCabecera", medicoToJSON(entrada.getMedCabecera(),1));
			paciente.put("farmacia", farmaciaToJSON(entrada.getFarmacia()));
		}
		JSONArray listaTrat=new JSONArray();
		for(Tratamiento tratamiento: entrada.getTratamiento())
		{
			listaTrat.add(tratamientoToJSON(tratamiento));
		}
		paciente.put("tratamientos",listaTrat);
		return paciente;
	}	
	//JSON de la entity farmacia
	private JSONObject farmaciaToJSON(Farmacia entrada)
	{
		JSONObject farmacia= new JSONObject();
		farmacia.put("id", entrada.getId());
		farmacia.put("nombre",entrada.getNombre());
		farmacia.put("direccion", entrada.getDireccion());
		farmacia.put("ciudad", entrada.getCiudad());
		farmacia.put("codPostal", entrada.getCodPostal());
		farmacia.put("provincia", entrada.getProvincia());
		farmacia.put("comAutonoma", entrada.getComAutonoma());
		return farmacia;
	}
	//JSON de la entity Tratamiento
	private JSONObject tratamientoToJSON(Tratamiento entrada)
	{
		JSONObject tratamiento= new JSONObject();
		tratamiento.put("id", entrada.getId());
		tratamiento.put("medicamento", medicamentoToJSON(entrada.getMedicamento()));
		tratamiento.put("fechaInicio", entrada.getFechaInicio());
		tratamiento.put("fechaFin", entrada.getFechaFin());
		tratamiento.put("periodicidad", entrada.getPerioicidad());
		tratamiento.put("numDosis", entrada.getNumDosis());
		return tratamiento;
	}
	//JSON de la entity medicamento
	private JSONObject medicamentoToJSON(Medicamento entrada)
	{
		JSONObject medicamento= new JSONObject();
		medicamento.put("id", entrada.getId());
		medicamento.put("nombre", entrada.getNombre());
		medicamento.put("descripcion", entrada.getDescripcion());
		medicamento.put("laboratorio", entrada.getLaboratorio());
		medicamento.put("precio", entrada.getPrecio());
		return medicamento;
	}
	//JSON de la entity mensaje
	private JSONObject mensajeToJSON(Mensaje entrada){
		JSONObject mensaje= new JSONObject();
		mensaje.put("id", entrada.getid());
		mensaje.put("asunto", entrada.getAsunto());
		mensaje.put("mensaje", entrada.getMensaje());
		mensaje.put("fecha", entrada.getFechaMensaje().toString());
		mensaje.put("remitente", usuarioToJSON(entrada.getRemitente()));
		mensaje.put("destinatario", usuarioToJSON(entrada.getDestinatario()));
		mensaje.put("leido", entrada.isLeido());
		return mensaje;
	}
	//JSON de la entity usuario
	private JSONObject usuarioToJSON(Usuario entrada){
		JSONObject usuario= new JSONObject();
		usuario.put("id", entrada.getId());
		usuario.put("nombre", entrada.getNombre());
		usuario.put("apellidos", entrada.getApellidos());
		usuario.put("telefono", entrada.getTelefono());
		usuario.put("email", entrada.getEmail());
		return usuario;
	}

	/*----cambios de JSON a entitys-----*/
	//Json a mensaje
	private Mensaje JsonToMensaje(JSONObject entrada){
		Mensaje mensaje= new Mensaje();
		mensaje.setAsunto(entrada.get("asunto").toString());
		mensaje.setLeido(false);
		mensaje.setMensaje(entrada.get("mensaje").toString());
		mensaje.setFechaMensaje(entrada.get("fecha").toString());
		mensaje.setDestinatario(JsonToUsuario(entrada.get("destinatario").toString()));
		mensaje.setRemitente(JsonToUsuario(entrada.get("remitente").toString()));
		return mensaje;
	}
	private Usuario JsonToUsuario(String id){
		Usuario usuario= entityManager.find(Usuario.class, Long.parseLong(id));
		return usuario;
	}
	private Tratamiento JsonToTratamiento(JSONObject entrada){
		Tratamiento tratamiento= new Tratamiento();
		tratamiento.setMedicamento(JsonToMedicamento(entrada.get("medicamento").toString()));
		tratamiento.setPaciente(JsonToPaciente(entrada.get("paciente").toString()));
		tratamiento.setFechaInicio(entrada.get("fechaInicio").toString());
		tratamiento.setFechaFin(entrada.get("fechaFin").toString());
		tratamiento.setNumDosis(Integer.parseInt(entrada.get("numDosis").toString()));
		tratamiento.setPerioicidad(entrada.get("periodicidad").toString());
		tratamiento.setNumDosisDia(0);
		return tratamiento;
	}
	private Medicamento JsonToMedicamento(String id){
		return entityManager.find(Medicamento.class,Long.parseLong(id));
	}
	private Paciente JsonToPaciente(String id)
	{
		return entityManager.find(Paciente.class, Long.parseLong(id));
	}

}