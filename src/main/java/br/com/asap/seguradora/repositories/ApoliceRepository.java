package br.com.asap.seguradora.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.asap.seguradora.documents.Apolice;

@Repository
public interface ApoliceRepository extends MongoRepository<Apolice, ObjectId> {

	public Optional<Apolice> findByNumero(Integer numero);

	public List<Apolice> findByCliente(ObjectId cliente);

}
