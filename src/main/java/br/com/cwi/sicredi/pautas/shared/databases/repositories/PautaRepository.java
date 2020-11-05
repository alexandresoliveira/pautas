package br.com.cwi.sicredi.pautas.shared.databases.repositories;

import br.com.cwi.sicredi.pautas.shared.databases.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PautaRepository extends JpaRepository<Pauta, UUID> {
}
