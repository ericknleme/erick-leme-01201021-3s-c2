package br.com.bandtec.ericklemeac2.repositorio;

import br.com.bandtec.ericklemeac2.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {

    @Query("SELECT l FROM Lutador l ORDER BY l.forcaGolpe DESC")
    List<Lutador> findAllOrderByForcaGolpe();

    @Query("SELECT COUNT(l) l FROM Lutador l WHERE l.vivo=false")
    long contarMortos();

    @Query("SELECT COUNT(l) FROM Lutador l WHERE l.vivo=true")
    long contarVivos();
}
