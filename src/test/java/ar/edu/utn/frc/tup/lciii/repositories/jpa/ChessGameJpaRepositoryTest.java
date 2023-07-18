package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.controllers.PlayerController;
import ar.edu.utn.frc.tup.lciii.entities.ChessMatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChessGameJpaRepositoryTest {

    @Autowired
    private ChessGameJpaRepository chessGameJpaRepository;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PlayerController playerController;

//    @Test
//    public void testFindById_ExistingId_ReturnsChessMatchEntity() {
//        // Arrange
//        Long id = 1L;
//        ChessMatchEntity matchEntity = new ChessMatchEntity();
//        chessGameJpaRepository.save(matchEntity);
//
//        // Act
//        Optional<ChessMatchEntity> result = chessGameJpaRepository.findById(id);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals(matchEntity, result.get());
//    }
//
//    @Test
//    public void testFindById_NonExistingId_ReturnsEmptyOptional() {
//        // Arrange
//        Long id = 1L;
//
//        // Act
//        Optional<ChessMatchEntity> result = chessGameJpaRepository.findById(id);
//
//        // Assert
//        assertFalse(result.isPresent());
//    }

    @Test
    public void getReferenceById(){
        ChessMatchEntity chessMatchEntity = new ChessMatchEntity();
        chessMatchEntity.setId(1L);
        chessMatchEntity.setMatchInfo(" ");
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(1L);
        playerEntity.setNamePlayer("Azul");
        entityManager.persist(playerEntity);
        entityManager.persist(chessMatchEntity);
        entityManager.flush();

        ChessMatchEntity chessMatchSaved = chessGameJpaRepository.getReferenceById(1L);
        assertEquals(Optional.of(1L), chessMatchSaved.getId());
    }
}