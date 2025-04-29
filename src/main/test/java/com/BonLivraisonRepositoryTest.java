import com.example.bonlivraison.model.BonLivraison;
import com.example.bonlivraison.repository.BonLivraisonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BonLivraisonRepositoryTest {

    @Autowired
    private BonLivraisonRepository repository;

    @Test
    public void testFindBonLivraisonParNumero() {
        // Préparation des données
        BonLivraison bon = new BonLivraison();
        bon.setNumero("BA-2025-006");
        bon.setAdresseLivraison("Rue 123");
        bon.setClientId(1L);
        bon.setDateLivraison("2025-04-29");

        repository.save(bon);
        // Test
        Optional<BonLivraison> result = repository.findByNumero("BA-2025-006");
        assertTrue(result.isPresent(), "Le bon de livraison n'a pas été trouvé.");
        assertEquals("BA-2025-006", result.get().getNumero());
        assertNotNull(result.get().getClientId(), "Client ID manquant.");
        assertNotNull(result.get().getAdresseLivraison(), "Adresse manquante.");
    }
}
