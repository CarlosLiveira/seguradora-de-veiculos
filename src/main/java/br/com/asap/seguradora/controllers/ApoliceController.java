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

import br.com.asap.seguradora.controllers.dto.ApoliceDto;
import br.com.asap.seguradora.controllers.dto.ApoliceDtoOutput;
import br.com.asap.seguradora.services.ApoliceServiceImpl;
import br.com.asap.seguradora.utils.ValidaDadosDeEntrada;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/apolices")
@Api(value = "API REST Apolices")
@CrossOrigin(origins = "*")
public class ApoliceController {

	@Autowired
	ApoliceServiceImpl apoliceServiceImpl;

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de apólices")
	public ResponseEntity<List<ApoliceDtoOutput>> findAll() {
		return ResponseEntity.ok().body(apoliceServiceImpl.findAll());
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna uma única apólice")
	public ResponseEntity<ApoliceDtoOutput> findById(@PathVariable String id) {
		ObjectId objId = ValidaDadosDeEntrada.validaObjIdApolice(id);
		return ResponseEntity.ok().body(apoliceServiceImpl.findById(objId));
	}

	@PostMapping
	@ApiOperation(value = "Salva uma apólice")
	public ResponseEntity<Void> create(@RequestBody @Valid ApoliceDto form, UriComponentsBuilder uriBuilder) {
		ObjectId id = apoliceServiceImpl.create(form);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza uma apólice")
	public ResponseEntity<ApoliceDto> update(@PathVariable String id, @RequestBody ApoliceDto form) {
		ObjectId objId = ValidaDadosDeEntrada.validaObjIdApolice(id);
		ApoliceDto apoliceDto = apoliceServiceImpl.update(objId, form);
		return ResponseEntity.ok(apoliceDto);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Deleta uma apólice")
	public ResponseEntity<?> delete(@PathVariable String id) {
		ObjectId objId = ValidaDadosDeEntrada.validaObjIdApolice(id);
		apoliceServiceImpl.delete(objId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
