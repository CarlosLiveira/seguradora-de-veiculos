package br.com.asap.seguradora.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.asap.seguradora.documents.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, ObjectId> {

	public Optional<Cliente> findByCpf(Long cpf);
}
