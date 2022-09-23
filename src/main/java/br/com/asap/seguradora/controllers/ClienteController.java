package br.com.asap.seguradora.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.asap.seguradora.controllers.dto.ClienteDto;
import br.com.asap.seguradora.controllers.dto.ClienteDtoOutput;
import br.com.asap.seguradora.services.ClienteServiceImpl;
import br.com.asap.seguradora.utils.ValidaObjId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/clientes")
@Api(value = "API REST Clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteServiceImpl clienteServiceImpl;

	@Autowired
	private ValidaObjId validaObjId;

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de clientes")
	public ResponseEntity<List<ClienteDtoOutput>> findAll() {
		return ResponseEntity.ok().body(clienteServiceImpl.findAll());
	}

	@GetMapping(value = "{id}")
	@ApiOperation(value = "Retorna um único cliente")
	public ResponseEntity<ClienteDtoOutput> findById(@PathVariable String id) {
		// valida se o id é do tipo ObjectId
		ObjectId objId = validaObjId.validaObjIdCliente(id);
		return ResponseEntity.ok().body(clienteServiceImpl.findById(objId));
	}

	@PostMapping
	@ApiOperation(value = "Salva um cliente")
	public ResponseEntity<Void> create(@RequestBody @Valid ClienteDto form, UriComponentsBuilder uriBuilder) {
		ObjectId id = clienteServiceImpl.create(form);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza um cliente")
	public ResponseEntity<ClienteDto> update(@PathVariable String id, @RequestBody @Valid ClienteDto form) {
		ObjectId objId = validaObjId.validaObjIdCliente(id);
		ClienteDto clienteDto = clienteServiceImpl.update(objId, form);
		return ResponseEntity.ok(clienteDto);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Deleta um cliente")
	public ResponseEntity<?> delete(@PathVariable String id) {
		ObjectId objId = validaObjId.validaObjIdCliente(id);
		clienteServiceImpl.delete(objId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
