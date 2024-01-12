import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.generation.entities.Supermarket;
import com.generation.factories.SupermarketRepositoryFactory;
import com.generation.interfaces.Repository;

public class SupermarketTest 
{
    @Test
    void testReadAll()
    {
        Repository<Supermarket> repo = SupermarketRepositoryFactory.make();
        List<Supermarket> allSupermarkets = repo.readAll();
        assertTrue(allSupermarkets.size()!=0);
    }

    @Test
    void testReadById()
    {
        Repository<Supermarket> repo = SupermarketRepositoryFactory.make();
        Supermarket Supermarket = repo.readById(1);
        assertTrue(Supermarket!=null);
    }

    @Test
    void testInsert()
    {
        Repository<Supermarket> repo = SupermarketRepositoryFactory.make();
        List<Supermarket> allSupermarketsOld = repo.readAll();
        Supermarket newSupermarket = new Supermarket();
        repo.insert(newSupermarket);
        List<Supermarket> allSupermarketsNew = repo.readAll();

        assertEquals(allSupermarketsOld.size()+1, allSupermarketsNew.size());

        repo.delete(allSupermarketsNew.get(allSupermarketsNew.size()-1).getId());
    }

    @Test
    void deleteTest()
    {
        Repository<Supermarket> repo = SupermarketRepositoryFactory.make();
        Supermarket newSupermarket = new Supermarket();
        repo.insert(newSupermarket);
        List<Supermarket> allSupermarketsOld = repo.readAll();
        repo.delete(allSupermarketsOld.get(allSupermarketsOld.size()-1).getId());
        List<Supermarket> allSupermarketsNew = repo.readAll();

        assertEquals(allSupermarketsOld.size()-1, allSupermarketsNew.size());
    }

    @Test
    void testUpdate()
    {
        Repository<Supermarket> repo = SupermarketRepositoryFactory.make();
        Supermarket newSupermarket = new Supermarket();
        repo.insert(newSupermarket);
        List<Supermarket> allSupermarketsNew = repo.readAll();
        newSupermarket = repo.readById(allSupermarketsNew.get(allSupermarketsNew.size()-1).getId());
        newSupermarket.setAddress("TEST");
        repo.update(newSupermarket);
        newSupermarket = repo.readById(newSupermarket.getId());
        assertEquals("TEST", newSupermarket.getAddress());

        repo.delete(newSupermarket);
    }
}
